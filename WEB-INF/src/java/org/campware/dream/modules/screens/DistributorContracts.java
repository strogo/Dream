package org.campware.dream.modules.screens;

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

import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;

import org.campware.dream.om.DcontractPeer;
import org.campware.dream.om.LocationPeer;
import org.campware.dream.om.SalesDistrictPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class DistributorContracts extends CreamUtil
{

    protected void initScreen()
    {
        setModuleType(UTIL);
        setModuleName("UTIL");
    }


    protected boolean getDetail(int myId, Criteria criteria, Context context)
    {
        try
        {
            Criteria.Criterion a1 = criteria.getNewCriterion(DcontractPeer.DCONTRACT_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion a2 = criteria.getNewCriterion(DcontractPeer.DISTRIBUTOR_ID, new Integer(myId), Criteria.EQUAL);
            Criteria.Criterion a3 = criteria.getNewCriterion(DcontractPeer.STATUS, new Integer(30), Criteria.EQUAL);
            criteria.add( a1.or( a2.and(a3)));
            criteria.addAscendingOrderByColumn(DcontractPeer.DCONTRACT_CODE);

            context.put("contracts", DcontractPeer.doSelect(criteria));

			Criteria loccrit = new Criteria();
			loccrit.add(LocationPeer.DISTRIBUTOR_ID, myId);
			loccrit.add(LocationPeer.STATUS, 30, Criteria.EQUAL);
			loccrit.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
			loccrit.addAscendingOrderByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
			loccrit.addAscendingOrderByColumn(LocationPeer.LOCATION_DISPLAY);
			context.put("locations", LocationPeer.doSelect(loccrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
