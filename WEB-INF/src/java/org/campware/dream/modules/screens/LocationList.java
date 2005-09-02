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

import java.util.List;
import org.apache.turbine.util.RunData;

import org.apache.torque.util.Criteria;
import org.campware.dream.om.LocationPeer;
import org.campware.dream.om.LocationCategoryPeer;
import org.campware.dream.om.CountryPeer;
import org.campware.dream.om.DistributorPeer;
import org.campware.dream.om.SalesDistrictPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class LocationList extends CreamList
{

    protected void initScreen()
    {
        setModuleType(ENTITY);
        setModuleName("LOCATION");
        setIdName(LocationPeer.LOCATION_ID);
        setDefOrderColumn(LocationPeer.LOCATION_DISPLAY);
    }

    protected String getSortColumn(int sortNo)
    {
        if (sortNo==1){
            return LocationPeer.LOCATION_CODE;
        } else if (sortNo==2){
            return LocationPeer.LOCATION_DISPLAY;
        } else if (sortNo==3){
            return DistributorPeer.DISTRIBUTOR_DISPLAY;
        } else if (sortNo==4){
            return SalesDistrictPeer.SALES_DISTRICT_NAME;
        }
        
        return "";
    }

    protected void setFilter(int filterNo, Criteria listCriteria, RunData data)
    {

        try
        {
            if (filterNo==1001){
                listCriteria.add(LocationPeer.LOCATION_TYPE, new Integer(10), Criteria.EQUAL);
            } else if (filterNo==1002){
                listCriteria.add(LocationPeer.LOCATION_TYPE, new Integer(20), Criteria.EQUAL);
            } else if (filterNo==1003){
                listCriteria.add(LocationPeer.STATUS, new Integer(10), Criteria.EQUAL);
            } else if (filterNo==1004){
                listCriteria.add(LocationPeer.STATUS, new Integer(30), Criteria.EQUAL);
            } else if (filterNo==1005){
                listCriteria.add(LocationPeer.STATUS, new Integer(50), Criteria.EQUAL);
            } else if (filterNo==1006){
                listCriteria.add(LocationPeer.CREATED_BY, (Object) data.getUser().getUserName(), Criteria.EQUAL);
            }
        }
        catch (Exception e)
        {
        }
    }

    protected void setFind(String findStr, Criteria listCriteria)
    {
        try
        {
            listCriteria.add(LocationPeer.LOCATION_DISPLAY, (Object)findStr, Criteria.LIKE);
        }
        catch (Exception e)
        {
        }
    }

    protected List getEntries(Criteria criteria)
    {
        try
        {
            criteria.addJoin(LocationCategoryPeer.LOCATION_CAT_ID, LocationPeer.LOCATION_CAT_ID);
			criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
			criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, LocationPeer.DISTRIBUTOR_ID);
            criteria.addJoin(CountryPeer.COUNTRY_ID, LocationPeer.COUNTRY_ID);
            return LocationPeer.doSelect(criteria);
        }
        catch (Exception e)
        {
            return null;
        }
    }

}
