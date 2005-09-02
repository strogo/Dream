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
import java.util.List;
import java.util.Iterator;
import org.apache.velocity.context.Context;

import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;
import java.sql.Connection;

import org.campware.dream.om.Dnewsletter;
import org.campware.dream.om.DnewsletterPeer;
import org.apache.turbine.util.velocity.VelocityHtmlEmail;
import org.apache.turbine.services.resources.TurbineResources;

import org.campware.dream.om.Distributor;
import org.campware.dream.om.DistributorPeer;

/**
 * This class provides a simple set of methods to
 * insert/update/delete records in a database.
 */
public class DnewsletterSQL extends CreamAction
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("DNEWSLETTER");
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
        Dnewsletter entry = new Dnewsletter();
        data.getParameters().setProperties(entry);

        String myCode=data.getParameters().getString("dnewslettercode");
		int myStatus= data.getParameters().getInt("status");
		
		boolean bSave=true;

		if (myStatus==30){
			bSave= sendEmail(data, context, entry);
			if (bSave) entry.setStatus(50);
		}

		if (bSave){

	        entry.setDnewsletterCode(getTempCode());
	
	        entry.setIssuedDate(parseDate(data.getParameters().getString("issueddate")));
	        entry.setClosedDate(parseDate(data.getParameters().getString("closeddate")));
	        entry.setCreatedBy(data.getUser().getUserName());
	        entry.setCreated(new Date());
	        entry.setModifiedBy(data.getUser().getUserName());
	        entry.setModified(new Date());
	        
	        Connection conn = Transaction.begin(DnewsletterPeer.DATABASE_NAME);
	        boolean success = false;
	        try {
	            entry.save(conn);
	            entry.setDnewsletterCode(getRowCode("NL", entry.getDnewsletterId()));
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
        Dnewsletter entry = new Dnewsletter();
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
	 * Send email to distributors
	 */
	private boolean sendEmail(RunData data, Context context, Dnewsletter emailEntry)
	throws Exception
	{

		int distributorCatId= emailEntry.getDistributorCatId();
		int distributorType= emailEntry.getDistributorType();
		int distLanguageId= emailEntry.getDistLanguageId();
		int distCountryId= emailEntry.getDistCountryId();
		
		Criteria criteria = new Criteria();
		
		if (distributorCatId>999){
			criteria.add(DistributorPeer.DISTRIBUTOR_CAT_ID, new Integer(distributorCatId), Criteria.EQUAL);
		}
		if (distributorType>1){
			criteria.add(DistributorPeer.DISTRIBUTOR_TYPE, new Integer(distributorType), Criteria.EQUAL);
		}
		if (distLanguageId>999){
			criteria.add(DistributorPeer.LANGUAGE_ID, new Integer(distLanguageId), Criteria.EQUAL);
		}
		if (distCountryId>999){
			criteria.add(DistributorPeer.COUNTRY_ID, new Integer(distCountryId), Criteria.EQUAL);
		}
		criteria.add(DistributorPeer.SEND_NEWS, new Integer(20), Criteria.EQUAL);
		criteria.add(DistributorPeer.EMAIL, (Object)"EMAIL is NOT NULL", Criteria.CUSTOM);		
		


		List receivers = DistributorPeer.doSelect(criteria);
		Iterator i = receivers.iterator();
		VelocityTool velTool= new VelocityTool(context);

		while (i.hasNext())
		{
			Distributor dist = (Distributor) i.next();

		  String sEmailAddress=dist.getEmail(); 

		  if (sEmailAddress.length()>1){
			VelocityHtmlEmail ve = new VelocityHtmlEmail(data); 
			ve.setCharset("UTF-8"); 
			ve.addTo( sEmailAddress, "");
			ve.setFrom(TurbineResources.getString("mail.smtp.from"), TurbineResources.getString("mail.smtp.from.name")); 
			ve.setSubject(emailEntry.getSubject()); 
			context.put("name", dist.getDistributorName1()); 
			context.put("display", dist.getDistributorDisplay()); 
			context.put("dear", dist.getDear()); 
			context.put("email", dist.getEmail()); 
			context.put("custom1", dist.getCustom1()); 
			context.put("custom2", dist.getCustom2()); 
			context.put("custom3", dist.getCustom3()); 
			context.put("custom4", dist.getCustom4()); 
			context.put("custom5", dist.getCustom5()); 
			context.put("custom6", dist.getCustom6()); 
			context.put("emailbody", velTool.evaluate(emailEntry.getBody())); 
			ve.setTextTemplate("screens/SendEmail.vm");
			ve.send(); 
		  }
		}

		return true; 
	}




    /**
     * Delete a record from the database using
     * the unique id gleaned from the web form.
     */
    public void doDelete(RunData data, Context context)
        throws Exception
    {
        Criteria criteria = new Criteria();
        criteria.add(DnewsletterPeer.DNEWSLETTER_ID, data.getParameters().getInt("dnewsletterid"));
        DnewsletterPeer.doDelete(criteria);
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
        criteria.addIn(DnewsletterPeer.DNEWSLETTER_ID, delIds);
        DnewsletterPeer.doDelete(criteria);
    }

}
