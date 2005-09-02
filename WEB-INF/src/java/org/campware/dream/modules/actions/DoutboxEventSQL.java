package org.campware.dream.modules.actions;

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

import java.util.Date;
import org.apache.velocity.context.Context;

import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;
import java.sql.Connection;

import org.campware.dream.om.DoutboxEvent;
import org.campware.dream.om.DoutboxEventPeer;
import org.apache.turbine.util.velocity.VelocityHtmlEmail;
import org.apache.turbine.services.resources.TurbineResources;
 
 
/**
 * This class provides a simple set of methods to
 * insert/update/delete records in a database.
 */
public class DoutboxEventSQL extends CreamAction
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("DOUTBOX");
    }

    /**
     * This simply takes an entry from the web form and
     * inserts it directly into the database.
     *
     * This would not be good in practice as the
     * data should be verified before being allowed
     * into the database. This is merely an
     * example of how to use peers, this certainly
     * wouldn't be secure.
     */
    public void doInsert(RunData data, Context context)
        throws Exception
    {
        DoutboxEvent entry = new DoutboxEvent();
        data.getParameters().setProperties(entry);

        String myCode=data.getParameters().getString("doutboxeventcode");
		int myStatus= data.getParameters().getInt("status");
		
		boolean bSave=true;

		if (myStatus==30){
			bSave= sendEmail(data, context, entry);
			if (bSave) entry.setStatus(50);
		}

		if (bSave){
	        entry.setDoutboxEventCode(getTempCode());
	
	        entry.setIssuedDate(parseDate(data.getParameters().getString("issueddate")));
	        entry.setClosedDate(parseDate(data.getParameters().getString("closeddate")));
	        entry.setCreatedBy(data.getUser().getUserName());
	        entry.setCreated(new Date());
	        entry.setModifiedBy(data.getUser().getUserName());
	        entry.setModified(new Date());
	        
	        Connection conn = Transaction.begin(DoutboxEventPeer.DATABASE_NAME);
	        boolean success = false;
	        try {
	            entry.save(conn);
	            entry.setDoutboxEventCode(getRowCode("OE", entry.getDoutboxEventId()));
	            entry.save(conn);
	            Transaction.commit(conn);
	            success = true;
	
	        } finally {
	            if (!success) Transaction.safeRollback(conn);
	        }
		}
    }

    /**
     * Update a record in the database with the
     * information present in the web form.
     *
     * Again, this is merely an example. The data
     * should be checked before being allowed
     * into the database.
     */
    public void doUpdate(RunData data, Context context)
        throws Exception
    {
        DoutboxEvent entry = new DoutboxEvent();
        data.getParameters().setProperties(entry);

		int myStatus= data.getParameters().getInt("status");
		
		boolean bSave=true;

		if (myStatus==30){
			bSave= sendEmail(data, context, entry);
			if (bSave) entry.setStatus(50);
		}

		if (bSave){
	        entry.setIssuedDate(parseDate(data.getParameters().getString("issueddate")));
	        entry.setClosedDate(parseDate(data.getParameters().getString("closeddate")));
	        entry.setCreated(parseDateTime(data.getParameters().getString("created")));
	        entry.setModifiedBy(data.getUser().getUserName());
	        entry.setModified(new Date());
	
	        entry.setModified(true);
	        entry.setNew(false);
	        entry.save();
		}
    }

	/**
	 * Send email to customer
	 */
	private boolean sendEmail(RunData data, Context context, DoutboxEvent emailEntry)
	throws Exception
	{
		String sEmailAddress=emailEntry.getDistributor().getEmail(); 

		if (sEmailAddress.length()>2){
//			Context emailContext = new org.apache.velocity.VelocityContext(); 
//			emailContext.put("emailbody", emailEntry.getBody()); 
//			VelocityEmail ve = new VelocityEmail(emailContext); 
//			ve.setTo("", sEmailAddress); 
//			ve.setFrom("",""); 
//			ve.setSubject(emailEntry.getSubject()); 
//			ve.setTemplate("screens/SendEmail.vm"); 
//			ve.setWordWrap(72); 
//			ve.send();

//			Context emailContext = new org.apache.velocity.VelocityContext(); 
//			emailContext.put("emailbody", emailEntry.getBody()); 

			VelocityHtmlEmail ve = new VelocityHtmlEmail(data); 
//			ve.setMailServer(TurbineResources.getString("mail.server")); 
			ve.setCharset("UTF-8"); 
			ve.addTo( sEmailAddress, "");
			ve.setFrom(TurbineResources.getString("mail.smtp.from"), TurbineResources.getString("mail.smtp.from.name")); 
			ve.setSubject(emailEntry.getSubject()); 
//			ve.setTextMsg(emailEntry.getBody()); 
			context.put("emailbody", emailEntry.getBody()); 
			ve.setTextTemplate("screens/SendEmail.vm");
			ve.send(); 

			return true; 
			
		}else{
			data.setMessage("Sorry, the email address is unknown!");
			data.setScreenTemplate("CreamError.vm");
			return false;
		}
		
	}



    /**
     * Delete a record from the database using
     * the unique id gleaned from the web form.
     */
    public void doDelete(RunData data, Context context)
        throws Exception
    {
        Criteria criteria = new Criteria();
        criteria.add(DoutboxEventPeer.DOUTBOX_EVENT_ID, data.getParameters().getInt("doutboxeventid"));
        DoutboxEventPeer.doDelete(criteria);
    }

    /**
     * Delete selected records from the database using
     * the unique ids gleaned from the web form.
     */
    public void doDeleteselected(RunData data, Context context)
        throws Exception
    {
        int[] delIds= data.getParameters().getInts("rowid");
        Criteria criteria = new Criteria();
        criteria.addIn(DoutboxEventPeer.DOUTBOX_EVENT_ID, delIds);
        DoutboxEventPeer.doDelete(criteria);
    }

}
