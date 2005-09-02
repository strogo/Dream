package org.campware.dream.modules.scheduledjobs;

/* ====================================================================
 * Copyright (C)2003  Media Development Loan Fund
 *
 *  * contact: contact@campware.org - http://www.campware.org
 * Campware encourages further development. Please let us know.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

//JDK
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.util.*;
import java.text.ParseException;
import java.lang.System;
import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

//Turbine
import org.apache.turbine.modules.ScheduledJob;
import org.apache.turbine.services.schedule.JobEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.turbine.services.resources.TurbineResources;

import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;
import java.sql.Connection;

import org.campware.dream.om.DinboxEvent;
import org.campware.dream.om.DinboxEventPeer;
import org.campware.dream.om.Distributor;
import org.campware.dream.om.DistributorPeer;

/**
 * Pop3 Job.
 *
 * Retrieve new messages from POP3 server
 * @author <a href="mailto:pandzic@volny.cz">Nenad Pandzic</a>
 */
public class Pop3Job extends ScheduledJob
{
	/** Logging */
	private static Log log = LogFactory.getLog(Pop3Job.class);

	private int taskcount = 0;

	/**
	 * Constructor
	 */
	 public Pop3Job()
	 {
		 //do Task initialization here
	 }


	/**
	 * Run the Jobentry from the scheduler queue.
	 * From ScheduledJob.
	 *
	 * @param job The job to run.
	 */
	public void run( JobEntry job ) throws Exception
	{
		// First we resolve online subscriptions
		doReceiveMessages();
	}


	private void doReceiveMessages() throws Exception{

		String host = TurbineResources.getString("mail.pop3.host");
		String username = TurbineResources.getString("mail.pop3.user");
		String password = TurbineResources.getString("mail.pop3.password");

		// Create empty properties
		Properties props = new Properties();

		// Get session
		Session session = Session.getDefaultInstance(props, null);

		// Get the store
		Store store = session.getStore("pop3");

		// Connect to store
		store.connect(host, username, password);

		// Get folder
		Folder folder = store.getFolder("INBOX");

		// Open read-only
		folder.open(Folder.READ_WRITE);

		// Get attributes & flags for all messages
		//
		Message[] messages = folder.getMessages();
		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		fp.add(FetchProfile.Item.FLAGS);
		fp.add("X-Mailer");
		folder.fetch(messages, fp);

		// Process each message
		//
		Address fromAddress= new InternetAddress();
		String from= new String();
		String name= new String();
		String email= new String();
		String subject= new String();
		String content= new String();
		for (int i = 0; i < messages.length; i++)
		{
			email=  ((InternetAddress)messages[i].getFrom()[0]).getAddress(); 
			name=  ((InternetAddress)messages[i].getFrom()[0]).getPersonal();
			subject= messages[i].getSubject(); 
			content= messages[i].getContent().toString();

			DinboxEvent entry = new DinboxEvent();

			Criteria criteria = new Criteria();
			criteria.add(DistributorPeer.EMAIL, (Object)email, Criteria.EQUAL);
			if (DistributorPeer.doSelect(criteria).size()>0){
				Distributor myDistrib = (Distributor) DistributorPeer.doSelect(criteria).get(0);
				entry.setDistributorId(myDistrib.getDistributorId());
			}else{
				if (name!=null){
					entry.setBody("From: " + name + " " + email + "\n\n" + content);
				}else{
					entry.setBody("From: " + email + "\n\n" + content);
				}
			}

			entry.setDinboxEventCode(getTempCode());

			entry.setEventType(10);
			entry.setEventChannel(10);
			entry.setSubject(subject);
				
			entry.setIssuedDate(new Date());
			entry.setCreatedBy("scheduler");
			entry.setCreated(new Date());
			entry.setModifiedBy("scheduler");
			entry.setModified(new Date());
        
			Connection conn = Transaction.begin(DinboxEventPeer.DATABASE_NAME);
			boolean success = false;
			try {
				entry.save(conn);
				entry.setDinboxEventCode(getRowCode("IE", entry.getDinboxEventId()));
				entry.save(conn);
				Transaction.commit(conn);
				success = true;

			} finally {
				if (!success) Transaction.safeRollback(conn);
			}

			messages[i].setFlag(Flags.Flag.DELETED, true); 
		}

		// Close connection 
		folder.close(true);
		store.close();

	}

	private String getTempCode()
	{
		Date currDate= new Date();
		return Integer.toString(currDate.hashCode());
	}

	private String getRowCode(String s, int i)
	{
		String is= new String();
        
		is= Integer.toString(i);
		while (is.length()<7)
		{
			is="0" + is;
		}

		is= s + is;
		return is;
	}

	private String formatDate(Date d)
	{
		SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy");
		return formatter.format(d);
	}
    
	
}