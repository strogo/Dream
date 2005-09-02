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
import java.util.Enumeration;
import java.math.BigDecimal;
import org.apache.velocity.context.Context;

import org.apache.turbine.util.RunData;
import org.apache.turbine.util.ParameterParser;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;
import java.sql.Connection;

import org.campware.dream.om.Dorder;
import org.campware.dream.om.DorderPeer;
import org.campware.dream.om.DorderItem;
import org.campware.dream.om.DorderItemPeer;

/**
 * This class provides a simple set of methods to
 * insert/update/delete records in a database.
 */
public class DorderSQL extends CreamAction
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("DORDER");
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
        Dorder entry = new Dorder();
        data.getParameters().setProperties(entry);

        entry.setDorderCode(getTempCode());

        entry.setIssuedDate(parseDate(data.getParameters().getString("issueddate")));
        entry.setClosedDate(parseDate(data.getParameters().getString("closeddate")));
		entry.setSalesDate(parseDate(data.getParameters().getString("salesdate")));
		entry.setExpectedTime(parseDateShortTime(data.getParameters().getString("expectedtime")));
		entry.setDescription(" ---");
        entry.setCreatedBy(data.getUser().getUserName());
        entry.setCreated(new Date());
        entry.setModifiedBy(data.getUser().getUserName());
        entry.setModified(new Date());
        
        ParameterParser pp= data.getParameters();
        Enumeration paramKeys= pp.keys();
		int qtyTotal= 0;
		double unitAmount = entry.getUnitPrice();
		double unitWeight = entry.getUnitWeight();
		BigDecimal totAmount = new BigDecimal(unitAmount);
		BigDecimal totWeight = new BigDecimal(unitWeight);
        
	    while(paramKeys.hasMoreElements()) {
	        String paramName = paramKeys.nextElement().toString();
	        if(paramName.startsWith("locationid")) {	
	            String suffix=paramName.substring(10, paramName.length());
	            DorderItem entryItem= new DorderItem();

	            entryItem.setLocationId(pp.getInt("locationid" + suffix));
	            int itmQuantity= pp.getInt("orderedqty" + suffix);
	            entryItem.setOrderedQty(itmQuantity);
				entryItem.setShippedQty(itmQuantity);
				entryItem.setUnsoldQty(0);
				entryItem.setLostQty(0);

	            qtyTotal= qtyTotal + itmQuantity;
	            entry.addDorderItem(entryItem);
            }
        }

		totAmount= totAmount.multiply(BigDecimal.valueOf(qtyTotal));
		totWeight= totWeight.multiply(BigDecimal.valueOf(qtyTotal));
        entry.setTotalQuantity(qtyTotal);
		entry.setTotalAmount(totAmount);
		entry.setTotalWeight(totWeight);

        Connection conn = Transaction.begin(DorderPeer.DATABASE_NAME);
        boolean success = false;
        try {
            entry.save(conn);
            entry.setDorderCode(getRowCode("DO", entry.getDorderId()));
            entry.save(conn);
            Transaction.commit(conn);
            success = true;

        } finally {
            if (!success) Transaction.safeRollback(conn);
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
        Dorder entry = new Dorder();
        data.getParameters().setProperties(entry);

        entry.setIssuedDate(parseDate(data.getParameters().getString("issueddate")));
        entry.setClosedDate(parseDate(data.getParameters().getString("closeddate")));
        entry.setCreated(parseDateTime(data.getParameters().getString("created")));
		entry.setSalesDate(parseDate(data.getParameters().getString("salesdate")));
		entry.setExpectedTime(parseDateShortTime(data.getParameters().getString("expectedtime")));
		entry.setDescription(" ---");
        entry.setModifiedBy(data.getUser().getUserName());
        entry.setModified(new Date());

		ParameterParser pp= data.getParameters();
		Enumeration paramKeys= pp.keys();
		int qtyTotal= 0;
		double unitAmount = entry.getUnitPrice();
		double unitWeight = entry.getUnitWeight();
		BigDecimal totAmount = new BigDecimal(unitAmount);
		BigDecimal totWeight = new BigDecimal(unitWeight);
        
		while(paramKeys.hasMoreElements()) {
			String paramName = paramKeys.nextElement().toString();
			if(paramName.startsWith("locationid")) {	
				String suffix=paramName.substring(10, paramName.length());
				DorderItem entryItem= new DorderItem();

				entryItem.setLocationId(pp.getInt("locationid" + suffix));
				int itmQuantity= pp.getInt("orderedqty" + suffix);
				entryItem.setOrderedQty(itmQuantity);
				entryItem.setShippedQty(itmQuantity);
				entryItem.setUnsoldQty(0);
				entryItem.setLostQty(0);

				qtyTotal= qtyTotal + itmQuantity;
				entry.addDorderItem(entryItem);
			}
		}

		totAmount= totAmount.multiply(BigDecimal.valueOf(qtyTotal));
		totWeight= totWeight.multiply(BigDecimal.valueOf(qtyTotal));
		entry.setTotalQuantity(qtyTotal);
		entry.setTotalAmount(totAmount);
		entry.setTotalWeight(totWeight);

        entry.setModified(true);
        entry.setNew(false);

        Criteria crit = new Criteria();
        crit.add(DorderItemPeer.DORDER_ID, entry.getDorderId());

        Connection conn = Transaction.begin(DorderPeer.DATABASE_NAME);
        boolean success = false;
        try {
            DorderItemPeer.doDelete(crit, conn);
            entry.save(conn);
            Transaction.commit(conn);
            success = true;

        } finally {
            if (!success) Transaction.safeRollback(conn);
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
        criteria.add(DorderPeer.DORDER_ID, data.getParameters().getInt("dorderid"));
        DorderPeer.doDelete(criteria);
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
        criteria.addIn(DorderPeer.DORDER_ID, delIds);
        DorderPeer.doDelete(criteria);
    }

}
