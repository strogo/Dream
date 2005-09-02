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

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;

import org.campware.dream.om.LocationPeer;
import org.campware.dream.om.SalesDistrictPeer;
import org.campware.dream.om.DorderItemPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class DorderLocations extends CreamUtil
{

    protected void initScreen()
    {
        setModuleType(UTIL);
        setModuleName("UTIL");
    }


	public void doBuildTemplate(RunData data, Context context)
	{
		try
		{
			initScreen();
			int distributorId = data.getParameters().getInt("id1");
			int dorderId = data.getParameters().getInt("id2");
			int salesAreaId = data.getParameters().getInt("id3");
            
			if (distributorId>0)
			{

				try
				{

					if (dorderId>1000)
					{

						Criteria orditemcrit = new Criteria();
						orditemcrit.add(DorderItemPeer.DORDER_ID, dorderId);

						orditemcrit.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
						orditemcrit.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);


						if (salesAreaId>999)
						{
							orditemcrit.add(SalesDistrictPeer.SALES_AREA_ID, salesAreaId);
						}

						orditemcrit.add(LocationPeer.DISTRIBUTOR_ID, distributorId);
						orditemcrit.add(LocationPeer.STATUS, 30, Criteria.EQUAL);

						orditemcrit.addAscendingOrderByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
						orditemcrit.addAscendingOrderByColumn(LocationPeer.LOCATION_DISPLAY);
						context.put("orderlocations", DorderItemPeer.doSelect(orditemcrit));


						Criteria newloccrit = new Criteria();
						newloccrit.add(LocationPeer.DISTRIBUTOR_ID, distributorId);
						newloccrit.add(LocationPeer.STATUS, 30, Criteria.EQUAL);
	
						if (salesAreaId>999)
						{
							newloccrit.add(SalesDistrictPeer.SALES_AREA_ID, salesAreaId);
						}
						
						String inSelect= new String("LOCATION_ID NOT IN (SELECT LOCATION_ID FROM DORDER_ITEM WHERE DORDER_ID=" + new Integer(dorderId).toString() + ")");
						newloccrit.add(LocationPeer.LOCATION_ID, (Object)inSelect, Criteria.CUSTOM);
	
						newloccrit.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
						newloccrit.addAscendingOrderByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
						newloccrit.addAscendingOrderByColumn(LocationPeer.LOCATION_DISPLAY);
						context.put("newlocations", LocationPeer.doSelect(newloccrit));
						
					}
					else
					{
						Criteria allloccrit = new Criteria();
						allloccrit.add(LocationPeer.DISTRIBUTOR_ID, distributorId);
						allloccrit.add(LocationPeer.STATUS, 30, Criteria.EQUAL);

						if (salesAreaId>999)
						{
							allloccrit.add(SalesDistrictPeer.SALES_AREA_ID, salesAreaId);
						}
					
						allloccrit.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
						allloccrit.addAscendingOrderByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
						allloccrit.addAscendingOrderByColumn(LocationPeer.LOCATION_DISPLAY);
						context.put("alllocations", LocationPeer.doSelect(allloccrit));
						
					}


				}
				catch (Exception e)
				{
				}

				context.put("mode", "update");
			}

			context.put("df", new SimpleDateFormat ("dd.MM.yyyy"));
			context.put("dtf", new SimpleDateFormat ("dd.MM.yyyy HH:mm:ss"));
			context.put("dstf", new SimpleDateFormat ("dd.MM.yyyy HH:mm"));
            
			DecimalFormatSymbols symb= new DecimalFormatSymbols();
			symb.setDecimalSeparator('.');
            
			context.put("af", new DecimalFormat ("0.00", symb));
			context.put("rf", new DecimalFormat ("0.000000", symb));
			context.put("today", new Date());
			Date nowTime= new Date();
			nowTime.setHours(12);
			nowTime.setMinutes(5);
			context.put("now", nowTime);
		}
		catch (Exception e)
		{
			// log something ?
		}
	}


}
