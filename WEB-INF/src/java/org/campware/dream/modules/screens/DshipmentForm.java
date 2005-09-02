package org.campware.dream.modules.screens;

/* ====================================================================
 * Copyright (C) 2003  Media Development Loan Fund
 *
 * Contact: contact@campware.org - http://www.campware.org
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

import org.apache.torque.util.Criteria;

import org.apache.velocity.context.Context;

import org.campware.dream.om.Dshipment;
import org.campware.dream.om.DshipmentPeer;
import org.campware.dream.om.LocationPeer;
import org.campware.dream.om.SalesDistrictPeer;
import org.campware.dream.om.DistributorPeer;
import org.campware.dream.om.DorderPeer;
import org.campware.dream.om.DorderItemPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class DshipmentForm extends CreamForm
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("DSHIPMENT");
        setIdName(DshipmentPeer.DSHIPMENT_ID);
        setFormIdName("dshipmentid");
    }

    protected boolean getEntry(Criteria criteria, Context context)
    {
        try
        {
            Dshipment entry = (Dshipment) DshipmentPeer.doSelect(criteria).get(0);
            context.put("entry", entry);

//			Criteria orditemcrit = new Criteria();
//			orditemcrit.add(DorderItemPeer.DORDER_ID, new Integer(entry.getDorderId()), Criteria.EQUAL);
//			context.put("entryitems", DorderItemPeer.doSelect(orditemcrit));

			Criteria orditemcrit = new Criteria();
			orditemcrit.add(DorderItemPeer.DORDER_ID, entry.getDorderId());

			orditemcrit.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
			orditemcrit.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);

			orditemcrit.addAscendingOrderByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
			orditemcrit.addAscendingOrderByColumn(LocationPeer.LOCATION_DISPLAY);
			context.put("entryitems", DorderItemPeer.doSelect(orditemcrit));


            Criteria ordcrit = new Criteria();
            Criteria.Criterion a1 = ordcrit.getNewCriterion(DorderPeer.DORDER_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion a2 = ordcrit.getNewCriterion(DorderPeer.DORDER_ID, new Integer(entry.getDorderId()), Criteria.EQUAL);
            Criteria.Criterion a3 = ordcrit.getNewCriterion(DorderPeer.DISTRIBUTOR_ID, new Integer(entry.getDistributorId()), Criteria.EQUAL);
            Criteria.Criterion a4 = ordcrit.getNewCriterion(DorderPeer.STATUS, new Integer(30), Criteria.EQUAL);
            ordcrit.add( a1.or( a2.or(a3.and(a4))));
            ordcrit.addAscendingOrderByColumn(DorderPeer.DORDER_CODE);
            context.put("orders", DorderPeer.doSelect(ordcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    protected boolean getNew(Context context)
    {
        try
        {
            Dshipment entry = new Dshipment();
            context.put("entry", entry);

            Criteria ordcrit = new Criteria();
            ordcrit.add(DorderPeer.DORDER_ID, 1000, Criteria.EQUAL);
            context.put("orders", DorderPeer.doSelect(ordcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    protected boolean getLookups(Context context)
    {
        try
        {
            Criteria custcrit = new Criteria();
            Criteria.Criterion b1 = custcrit.getNewCriterion(DistributorPeer.DISTRIBUTOR_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion b2 = custcrit.getNewCriterion(DistributorPeer.STATUS, new Integer(29), Criteria.GREATER_THAN);
            custcrit.add( b1.or( b2));
            custcrit.addAscendingOrderByColumn(DistributorPeer.DISTRIBUTOR_DISPLAY);
            context.put("distributors", DistributorPeer.doSelect(custcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


}
