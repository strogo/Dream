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
import java.text.ParsePosition;
import java.util.*;
import java.text.ParseException;
import org.apache.turbine.util.*;

import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.modules.screens.TemplateScreen;
import org.apache.turbine.util.RunData;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.util.security.AccessControlList;


import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.apache.velocity.context.Context;

import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.BasePeer;

import com.workingdogs.village.Record;

import org.campware.dream.om.DistributorPeer;
import org.campware.dream.om.LocationPeer;
import org.campware.dream.om.ProductPeer;
import org.campware.dream.om.ProjectPeer;
import org.campware.dream.om.DorderPeer;
import org.campware.dream.om.DorderItemPeer;
import org.campware.dream.om.DcontractPeer;
import org.campware.dream.om.DcontractItemPeer;
import org.campware.dream.om.SalesDistrictPeer;
import org.campware.dream.om.SalesAreaPeer;
import org.campware.dream.om.CountryPeer;
import org.campware.dream.om.CostTypePeer;

/**
 * This class provides a simple set of methods to
 * insert/update/delete records in a database.
 */
public class ReportSQL extends VelocitySecureAction
{
    /**
     * Show report data based on
     * filter parameters and report choosen
     */
    public void doReport(RunData data, Context context)
        throws Exception
    {
		int reportid= data.getParameters().getInt("reportid", 0);
		Criteria criteria = new Criteria();
		
//		String query = "SELECT COUNT(*) FROM ORDER_ITEM WHERE ORDER_ID = " + orderId + ";";
//			Vector qresult = BasePeer.executeQuery(query);
//			Record rec = (Record) qresult.firstElement();
//			int recordCount = rec.getValue(1).asInt();


        try
        {

            if (reportid==1){
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
                criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
                criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
                criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
                criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
    
				criteria.addSelectColumn("AVG("  + DorderItemPeer.UNSOLD_QTY + ")");
				criteria.addSelectColumn(DorderItemPeer.LOCATION_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addSelectColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addSelectColumn(LocationPeer.LOCATION_DISPLAY);
                criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
                criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
    
                setDorderCriteria(data, criteria);
                setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
                setProjectCriteria(data, criteria);
                setProductCriteria(data, criteria);
                
                criteria.addGroupByColumn(DorderItemPeer.LOCATION_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addGroupByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addGroupByColumn(LocationPeer.LOCATION_DISPLAY);
                criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
                criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
    
				criteria.addAscendingOrderByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addAscendingOrderByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addAscendingOrderByColumn(LocationPeer.LOCATION_DISPLAY);
                criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
                // stuff something into the Velocity Context
    
				String myQuery = BasePeer.createQueryString(criteria);
				String retPrc= new String( "("  + DorderItemPeer.UNSOLD_QTY + " + " + DorderItemPeer.LOST_QTY +  ") * 100.00 / " + DorderItemPeer.SHIPPED_QTY);
				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
//				context.put ("sqlstr", myQuery);

				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
                
				for (int i = 0; i < records.size(); i++) {
				  results.add( ((Record) records.get(i)) );
				}
				context.put ("entries", results);

            }else if (reportid==2){

				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
	    
//				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + " + " + DorderItemPeer.LOST_QTY + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + " + " + DorderItemPeer.LOST_QTY + ") * 100.00 / SUM("  + DorderItemPeer.SHIPPED_QTY + ")");
				criteria.addSelectColumn(SalesDistrictPeer.SALES_DISTRICT_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addSelectColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
	                
				criteria.addGroupByColumn(SalesDistrictPeer.SALES_DISTRICT_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addGroupByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				criteria.addAscendingOrderByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addAscendingOrderByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
	    
				String myQuery = BasePeer.createQueryString(criteria);
//				String retPrc= new String( DorderItemPeer.UNSOLD_QTY + " + " + DorderItemPeer.LOST_QTY);
//				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
	
				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
	                
				for (int i = 0; i < records.size(); i++) {
				  results.add( ((Record) records.get(i)) );
				}
				context.put ("entries", results);

            }else if (reportid==3){
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
	    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + " + " + DorderItemPeer.LOST_QTY + ") * 100.00 / SUM("  + DorderItemPeer.SHIPPED_QTY + ")");
				criteria.addSelectColumn(SalesDistrictPeer.SALES_AREA_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
	                
				criteria.addGroupByColumn(SalesDistrictPeer.SALES_AREA_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				criteria.addAscendingOrderByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
	    
				String myQuery = BasePeer.createQueryString(criteria);
	
				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
	                
				for (int i = 0; i < records.size(); i++) {
				  results.add( ((Record) records.get(i)) );
				}
				context.put ("entries", results);

            }else if (reportid==4){
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(CountryPeer.COUNTRY_ID, LocationPeer.COUNTRY_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
	    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + " + " + DorderItemPeer.LOST_QTY + ") * 100.00 / SUM("  + DorderItemPeer.SHIPPED_QTY + ")");
				criteria.addSelectColumn(LocationPeer.COUNTRY_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(CountryPeer.COUNTRY_NAME);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
	                
				criteria.addGroupByColumn(LocationPeer.COUNTRY_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(CountryPeer.COUNTRY_NAME);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				criteria.addAscendingOrderByColumn(CountryPeer.COUNTRY_NAME);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
	    
				String myQuery = BasePeer.createQueryString(criteria);
	
				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
	                
				for (int i = 0; i < records.size(); i++) {
				  results.add( ((Record) records.get(i)) );
				}
				context.put ("entries", results);

            }else if (reportid==5){
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
	    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + " + " + DorderItemPeer.LOST_QTY + ") * 100.00 / SUM("  + DorderItemPeer.SHIPPED_QTY + ")");
				criteria.addSelectColumn(DorderPeer.DISTRIBUTOR_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(DistributorPeer.DISTRIBUTOR_DISPLAY);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
	                
				criteria.addGroupByColumn(DorderPeer.DISTRIBUTOR_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(DistributorPeer.DISTRIBUTOR_DISPLAY);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				criteria.addAscendingOrderByColumn(DistributorPeer.DISTRIBUTOR_DISPLAY);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
	    
				String myQuery = BasePeer.createQueryString(criteria);
	
				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
	                
				for (int i = 0; i < records.size(); i++) {
				  results.add( ((Record) records.get(i)) );
				}
				context.put ("entries", results);

			}else if (reportid==6){
				
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(DcontractPeer.DCONTRACT_ID, DorderPeer.DCONTRACT_ID);
				criteria.addJoin(DcontractItemPeer.DCONTRACT_ID, DcontractPeer.DCONTRACT_ID);
				criteria.addJoin(CostTypePeer.COST_TYPE_ID, DcontractItemPeer.COST_TYPE_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " * "  + DorderPeer.UNIT_PRICE + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " * "  + DorderPeer.UNIT_WEIGHT + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ")");
				criteria.addSelectColumn(DorderItemPeer.LOCATION_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(CostTypePeer.COST_TYPE_NAME);
				criteria.addSelectColumn(DcontractItemPeer.COST_FUNCTION);
				criteria.addSelectColumn(DcontractItemPeer.COST_AMOUNT);
				criteria.addSelectColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addSelectColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addSelectColumn(LocationPeer.LOCATION_DISPLAY);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
                
				criteria.addGroupByColumn(DorderItemPeer.LOCATION_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(DcontractItemPeer.DCONTRACT_ITEM_ID);
				criteria.addGroupByColumn(DcontractItemPeer.COST_TYPE_ID);
				criteria.addGroupByColumn(CostTypePeer.COST_TYPE_NAME);
				criteria.addGroupByColumn(DcontractItemPeer.COST_FUNCTION);
				criteria.addGroupByColumn(DcontractItemPeer.COST_AMOUNT);
				criteria.addGroupByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addGroupByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addGroupByColumn(LocationPeer.LOCATION_DISPLAY);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
    
				criteria.addAscendingOrderByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addAscendingOrderByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addAscendingOrderByColumn(LocationPeer.LOCATION_DISPLAY);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addAscendingOrderByColumn(CostTypePeer.COST_TYPE_NAME);
				// stuff something into the Velocity Context
    
				String myQuery = BasePeer.createQueryString(criteria);
				String retPrc= new String( "("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ") * "  + DorderPeer.UNIT_PRICE);
				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
//				context.put ("sqlstr", myQuery);

				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
				BigDecimal oldCostAmount= BigDecimal.valueOf(0);
                
				for (int i = 0; i < records.size(); i++) {
					int costfunc= ((Record) records.get(i)).getValue(9).asInt();
					int locid= ((Record) records.get(i)).getValue(6).asInt();
					int prodid= ((Record) records.get(i)).getValue(7).asInt();
					BigDecimal costAmount=BigDecimal.valueOf(0);
				
					
					if (costfunc==10){
						BigDecimal soldAmount= ((Record) records.get(i)).getValue(1).asBigDecimal();
						BigDecimal costPerc= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costPerc= costPerc.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_CEILING);
						costAmount= costPerc.multiply(soldAmount);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==20){
						BigDecimal shippAmount= ((Record) records.get(i)).getValue(2).asBigDecimal();
						BigDecimal costPerc= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costPerc= costPerc.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_CEILING);
						costAmount= costPerc.multiply(shippAmount);
					}else if (costfunc==30){
						BigDecimal soldQty= ((Record) records.get(i)).getValue(5).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(soldQty);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==40){
						BigDecimal shippQty= ((Record) records.get(i)).getValue(4).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(shippQty);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==50){
						BigDecimal shippWeight= ((Record) records.get(i)).getValue(3).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(shippWeight);
					}else{
						costAmount=BigDecimal.valueOf(0);
					}

					oldCostAmount= oldCostAmount.add(costAmount) ;						

					if ((i+1) < records.size()){
						int nextlocid= ((Record) records.get(i+1)).getValue(6).asInt();
						int nextprodid= ((Record) records.get(i+1)).getValue(7).asInt();
						if (prodid!=nextprodid || locid!=nextlocid){
//							((Record) records.get(i)).setValue(2, oldCostAmount.divide(BigDecimal.valueOf(((Record) records.get(i)).getValue(4).asInt()), BigDecimal.ROUND_CEILING));
//							((Record) records.get(i)).setValue(3, oldCostAmount.divide(BigDecimal.valueOf(((Record) records.get(i)).getValue(5).asInt()), BigDecimal.ROUND_CEILING));
							((Record) records.get(i)).setValue(10, oldCostAmount);
							results.add( ((Record) records.get(i)));
							oldCostAmount=BigDecimal.valueOf(0);
						}
					}else{
//						((Record) records.get(i)).setValue(2, oldCostAmount.divide(BigDecimal.valueOf(((Record) records.get(i)).getValue(4).asInt()), BigDecimal.ROUND_CEILING));
//						((Record) records.get(i)).setValue(3, oldCostAmount.divide(BigDecimal.valueOf(((Record) records.get(i)).getValue(5).asInt()), BigDecimal.ROUND_CEILING));
						((Record) records.get(i)).setValue(10, oldCostAmount);
						results.add( ((Record) records.get(i)));
						oldCostAmount=BigDecimal.valueOf(0);
					}
				  
				}
				context.put ("entries", results);

			}else if (reportid==7){
				
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(DcontractPeer.DCONTRACT_ID, DorderPeer.DCONTRACT_ID);
				criteria.addJoin(DcontractItemPeer.DCONTRACT_ID, DcontractPeer.DCONTRACT_ID);
				criteria.addJoin(CostTypePeer.COST_TYPE_ID, DcontractItemPeer.COST_TYPE_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " * "  + DorderPeer.UNIT_PRICE + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " * "  + DorderPeer.UNIT_WEIGHT + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ")");
				criteria.addSelectColumn(LocationPeer.SALES_DISTRICT_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(CostTypePeer.COST_TYPE_NAME);
				criteria.addSelectColumn(DcontractItemPeer.COST_FUNCTION);
				criteria.addSelectColumn(DcontractItemPeer.COST_AMOUNT);
				criteria.addSelectColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addSelectColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
                
				criteria.addGroupByColumn(LocationPeer.SALES_DISTRICT_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(DcontractItemPeer.DCONTRACT_ITEM_ID);
				criteria.addGroupByColumn(DcontractItemPeer.COST_TYPE_ID);
				criteria.addGroupByColumn(CostTypePeer.COST_TYPE_NAME);
				criteria.addGroupByColumn(DcontractItemPeer.COST_FUNCTION);
				criteria.addGroupByColumn(DcontractItemPeer.COST_AMOUNT);
				criteria.addGroupByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addGroupByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
    
				criteria.addAscendingOrderByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addAscendingOrderByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
    
				String myQuery = BasePeer.createQueryString(criteria);
				String retPrc= new String( "("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ") * "  + DorderPeer.UNIT_PRICE);
				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
//				context.put ("sqlstr", myQuery);

				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
				BigDecimal oldCostAmount= BigDecimal.valueOf(0);
                
				for (int i = 0; i < records.size(); i++) {
					int costfunc= ((Record) records.get(i)).getValue(9).asInt();
					int locid= ((Record) records.get(i)).getValue(6).asInt();
					int prodid= ((Record) records.get(i)).getValue(7).asInt();
					BigDecimal costAmount=BigDecimal.valueOf(0);
				
					
					if (costfunc==10){
						BigDecimal soldAmount= ((Record) records.get(i)).getValue(1).asBigDecimal();
						BigDecimal costPerc= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costPerc= costPerc.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_CEILING);
						costAmount= costPerc.multiply(soldAmount);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==20){
						BigDecimal shippAmount= ((Record) records.get(i)).getValue(2).asBigDecimal();
						BigDecimal costPerc= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costPerc= costPerc.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_CEILING);
						costAmount= costPerc.multiply(shippAmount);
					}else if (costfunc==30){
						BigDecimal soldQty= ((Record) records.get(i)).getValue(5).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(soldQty);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==40){
						BigDecimal shippQty= ((Record) records.get(i)).getValue(4).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(shippQty);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==50){
						BigDecimal shippWeight= ((Record) records.get(i)).getValue(3).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(shippWeight);
					}else{
						costAmount=BigDecimal.valueOf(0);
					}

					oldCostAmount= oldCostAmount.add(costAmount) ;						

					if ((i+1) < records.size()){
						int nextlocid= ((Record) records.get(i+1)).getValue(6).asInt();
						int nextprodid= ((Record) records.get(i+1)).getValue(7).asInt();
						if (prodid!=nextprodid || locid!=nextlocid){
							((Record) records.get(i)).setValue(10, oldCostAmount);
							results.add( ((Record) records.get(i)));
							oldCostAmount=BigDecimal.valueOf(0);
						}
					}else{
						((Record) records.get(i)).setValue(10, oldCostAmount);
						results.add( ((Record) records.get(i)));
						oldCostAmount=BigDecimal.valueOf(0);
					}
				  
				}
				context.put ("entries", results);

			}else if (reportid==8){
				
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(DcontractPeer.DCONTRACT_ID, DorderPeer.DCONTRACT_ID);
				criteria.addJoin(DcontractItemPeer.DCONTRACT_ID, DcontractPeer.DCONTRACT_ID);
				criteria.addJoin(CostTypePeer.COST_TYPE_ID, DcontractItemPeer.COST_TYPE_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " * "  + DorderPeer.UNIT_PRICE + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " * "  + DorderPeer.UNIT_WEIGHT + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ")");
				criteria.addSelectColumn(SalesDistrictPeer.SALES_AREA_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(CostTypePeer.COST_TYPE_NAME);
				criteria.addSelectColumn(DcontractItemPeer.COST_FUNCTION);
				criteria.addSelectColumn(DcontractItemPeer.COST_AMOUNT);
				criteria.addSelectColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
                
				criteria.addGroupByColumn(SalesDistrictPeer.SALES_AREA_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(DcontractItemPeer.DCONTRACT_ITEM_ID);
				criteria.addGroupByColumn(DcontractItemPeer.COST_TYPE_ID);
				criteria.addGroupByColumn(CostTypePeer.COST_TYPE_NAME);
				criteria.addGroupByColumn(DcontractItemPeer.COST_FUNCTION);
				criteria.addGroupByColumn(DcontractItemPeer.COST_AMOUNT);
				criteria.addGroupByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
    
				criteria.addAscendingOrderByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
    
				String myQuery = BasePeer.createQueryString(criteria);
				String retPrc= new String( "("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ") * "  + DorderPeer.UNIT_PRICE);
				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
//				context.put ("sqlstr", myQuery);

				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
				BigDecimal oldCostAmount= BigDecimal.valueOf(0);
                
				for (int i = 0; i < records.size(); i++) {
					int costfunc= ((Record) records.get(i)).getValue(9).asInt();
					int locid= ((Record) records.get(i)).getValue(6).asInt();
					int prodid= ((Record) records.get(i)).getValue(7).asInt();
					BigDecimal costAmount=BigDecimal.valueOf(0);
				
					
					if (costfunc==10){
						BigDecimal soldAmount= ((Record) records.get(i)).getValue(1).asBigDecimal();
						BigDecimal costPerc= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costPerc= costPerc.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_CEILING);
						costAmount= costPerc.multiply(soldAmount);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==20){
						BigDecimal shippAmount= ((Record) records.get(i)).getValue(2).asBigDecimal();
						BigDecimal costPerc= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costPerc= costPerc.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_CEILING);
						costAmount= costPerc.multiply(shippAmount);
					}else if (costfunc==30){
						BigDecimal soldQty= ((Record) records.get(i)).getValue(5).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(soldQty);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==40){
						BigDecimal shippQty= ((Record) records.get(i)).getValue(4).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(shippQty);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==50){
						BigDecimal shippWeight= ((Record) records.get(i)).getValue(3).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(shippWeight);
					}else{
						costAmount=BigDecimal.valueOf(0);
					}

					oldCostAmount= oldCostAmount.add(costAmount) ;						

					if ((i+1) < records.size()){
						int nextlocid= ((Record) records.get(i+1)).getValue(6).asInt();
						int nextprodid= ((Record) records.get(i+1)).getValue(7).asInt();
						if (prodid!=nextprodid || locid!=nextlocid){
							((Record) records.get(i)).setValue(10, oldCostAmount);
							results.add( ((Record) records.get(i)));
							oldCostAmount=BigDecimal.valueOf(0);
						}
					}else{
						((Record) records.get(i)).setValue(10, oldCostAmount);
						results.add( ((Record) records.get(i)));
						oldCostAmount=BigDecimal.valueOf(0);
					}
				  
				}
				context.put ("entries", results);

			}else if (reportid==9){
				
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(DcontractPeer.DCONTRACT_ID, DorderPeer.DCONTRACT_ID);
				criteria.addJoin(DcontractItemPeer.DCONTRACT_ID, DcontractPeer.DCONTRACT_ID);
				criteria.addJoin(CostTypePeer.COST_TYPE_ID, DcontractItemPeer.COST_TYPE_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(CountryPeer.COUNTRY_ID, LocationPeer.COUNTRY_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " * "  + DorderPeer.UNIT_PRICE + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " * "  + DorderPeer.UNIT_WEIGHT + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ")");
				criteria.addSelectColumn(LocationPeer.COUNTRY_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(CostTypePeer.COST_TYPE_NAME);
				criteria.addSelectColumn(DcontractItemPeer.COST_FUNCTION);
				criteria.addSelectColumn(DcontractItemPeer.COST_AMOUNT);
				criteria.addSelectColumn(CountryPeer.COUNTRY_NAME);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
                
				criteria.addGroupByColumn(LocationPeer.COUNTRY_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(DcontractItemPeer.DCONTRACT_ITEM_ID);
				criteria.addGroupByColumn(DcontractItemPeer.COST_TYPE_ID);
				criteria.addGroupByColumn(CostTypePeer.COST_TYPE_NAME);
				criteria.addGroupByColumn(DcontractItemPeer.COST_FUNCTION);
				criteria.addGroupByColumn(DcontractItemPeer.COST_AMOUNT);
				criteria.addGroupByColumn(CountryPeer.COUNTRY_NAME);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
    
				criteria.addAscendingOrderByColumn(CountryPeer.COUNTRY_NAME);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
    
				String myQuery = BasePeer.createQueryString(criteria);
				String retPrc= new String( "("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ") * "  + DorderPeer.UNIT_PRICE);
				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
//				context.put ("sqlstr", myQuery);

				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
				BigDecimal oldCostAmount= BigDecimal.valueOf(0);
                
				for (int i = 0; i < records.size(); i++) {
					int costfunc= ((Record) records.get(i)).getValue(9).asInt();
					int locid= ((Record) records.get(i)).getValue(6).asInt();
					int prodid= ((Record) records.get(i)).getValue(7).asInt();
					BigDecimal costAmount=BigDecimal.valueOf(0);
				
					
					if (costfunc==10){
						BigDecimal soldAmount= ((Record) records.get(i)).getValue(1).asBigDecimal();
						BigDecimal costPerc= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costPerc= costPerc.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_CEILING);
						costAmount= costPerc.multiply(soldAmount);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==20){
						BigDecimal shippAmount= ((Record) records.get(i)).getValue(2).asBigDecimal();
						BigDecimal costPerc= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costPerc= costPerc.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_CEILING);
						costAmount= costPerc.multiply(shippAmount);
					}else if (costfunc==30){
						BigDecimal soldQty= ((Record) records.get(i)).getValue(5).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(soldQty);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==40){
						BigDecimal shippQty= ((Record) records.get(i)).getValue(4).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(shippQty);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==50){
						BigDecimal shippWeight= ((Record) records.get(i)).getValue(3).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(shippWeight);
					}else{
						costAmount=BigDecimal.valueOf(0);
					}

					oldCostAmount= oldCostAmount.add(costAmount) ;						

					if ((i+1) < records.size()){
						int nextlocid= ((Record) records.get(i+1)).getValue(6).asInt();
						int nextprodid= ((Record) records.get(i+1)).getValue(7).asInt();
						if (prodid!=nextprodid || locid!=nextlocid){
							((Record) records.get(i)).setValue(10, oldCostAmount);
							results.add( ((Record) records.get(i)));
							oldCostAmount=BigDecimal.valueOf(0);
						}
					}else{
						((Record) records.get(i)).setValue(10, oldCostAmount);
						results.add( ((Record) records.get(i)));
						oldCostAmount=BigDecimal.valueOf(0);
					}
				  
				}
				context.put ("entries", results);

			}else if (reportid==10){
				
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(DcontractPeer.DCONTRACT_ID, DorderPeer.DCONTRACT_ID);
				criteria.addJoin(DcontractItemPeer.DCONTRACT_ID, DcontractPeer.DCONTRACT_ID);
				criteria.addJoin(CostTypePeer.COST_TYPE_ID, DcontractItemPeer.COST_TYPE_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " * "  + DorderPeer.UNIT_PRICE + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " * "  + DorderPeer.UNIT_WEIGHT + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + ")");
				criteria.addSelectColumn("SUM("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ")");
				criteria.addSelectColumn(DorderPeer.DISTRIBUTOR_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(CostTypePeer.COST_TYPE_NAME);
				criteria.addSelectColumn(DcontractItemPeer.COST_FUNCTION);
				criteria.addSelectColumn(DcontractItemPeer.COST_AMOUNT);
				criteria.addSelectColumn(DistributorPeer.DISTRIBUTOR_DISPLAY);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
                
				criteria.addGroupByColumn(DorderPeer.DISTRIBUTOR_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(DcontractItemPeer.DCONTRACT_ITEM_ID);
				criteria.addGroupByColumn(DcontractItemPeer.COST_TYPE_ID);
				criteria.addGroupByColumn(CostTypePeer.COST_TYPE_NAME);
				criteria.addGroupByColumn(DcontractItemPeer.COST_FUNCTION);
				criteria.addGroupByColumn(DcontractItemPeer.COST_AMOUNT);
				criteria.addGroupByColumn(DistributorPeer.DISTRIBUTOR_DISPLAY);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
    
				criteria.addAscendingOrderByColumn(DistributorPeer.DISTRIBUTOR_DISPLAY);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
    
				String myQuery = BasePeer.createQueryString(criteria);
				String retPrc= new String( "("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ") * "  + DorderPeer.UNIT_PRICE);
				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
//				context.put ("sqlstr", myQuery);

				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
				BigDecimal oldCostAmount= BigDecimal.valueOf(0);
                
				for (int i = 0; i < records.size(); i++) {
					int costfunc= ((Record) records.get(i)).getValue(9).asInt();
					int locid= ((Record) records.get(i)).getValue(6).asInt();
					int prodid= ((Record) records.get(i)).getValue(7).asInt();
					BigDecimal costAmount=BigDecimal.valueOf(0);
				
					
					if (costfunc==10){
						BigDecimal soldAmount= ((Record) records.get(i)).getValue(1).asBigDecimal();
						BigDecimal costPerc= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costPerc= costPerc.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_CEILING);
						costAmount= costPerc.multiply(soldAmount);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==20){
						BigDecimal shippAmount= ((Record) records.get(i)).getValue(2).asBigDecimal();
						BigDecimal costPerc= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costPerc= costPerc.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_CEILING);
						costAmount= costPerc.multiply(shippAmount);
					}else if (costfunc==30){
						BigDecimal soldQty= ((Record) records.get(i)).getValue(5).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(soldQty);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==40){
						BigDecimal shippQty= ((Record) records.get(i)).getValue(4).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(shippQty);
						((Record) records.get(i)).setValue(10, costAmount);
					}else if (costfunc==50){
						BigDecimal shippWeight= ((Record) records.get(i)).getValue(3).asBigDecimal();
						BigDecimal costUnitAmount= ((Record) records.get(i)).getValue(10).asBigDecimal();
						costAmount= costUnitAmount.multiply(shippWeight);
					}else{
						costAmount=BigDecimal.valueOf(0);
					}

					oldCostAmount= oldCostAmount.add(costAmount) ;						

					if ((i+1) < records.size()){
						int nextlocid= ((Record) records.get(i+1)).getValue(6).asInt();
						int nextprodid= ((Record) records.get(i+1)).getValue(7).asInt();
						if (prodid!=nextprodid || locid!=nextlocid){
							((Record) records.get(i)).setValue(10, oldCostAmount);
							results.add( ((Record) records.get(i)));
							oldCostAmount=BigDecimal.valueOf(0);
						}
					}else{
						((Record) records.get(i)).setValue(10, oldCostAmount);
						results.add( ((Record) records.get(i)));
						oldCostAmount=BigDecimal.valueOf(0);
					}
				  
				}
				context.put ("entries", results);

			}else if (reportid==11){
				
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + ")");
				criteria.addSelectColumn(DorderItemPeer.LOCATION_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addSelectColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addSelectColumn(LocationPeer.LOCATION_DISPLAY);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
                
				criteria.addGroupByColumn(DorderItemPeer.LOCATION_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addGroupByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addGroupByColumn(LocationPeer.LOCATION_DISPLAY);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
    
				criteria.addAscendingOrderByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addAscendingOrderByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addAscendingOrderByColumn(LocationPeer.LOCATION_DISPLAY);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
    
				String myQuery = BasePeer.createQueryString(criteria);
				String retPrc= new String( "("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ") * "  + DorderPeer.UNIT_PRICE);
				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
//				context.put ("sqlstr", myQuery);

				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
                
				for (int i = 0; i < records.size(); i++) {
				  results.add( ((Record) records.get(i)) );
				}
				context.put ("entries", results);

			}else if (reportid==12){

				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
	    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + ")");
				criteria.addSelectColumn(SalesDistrictPeer.SALES_DISTRICT_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addSelectColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
	                
				criteria.addGroupByColumn(SalesDistrictPeer.SALES_DISTRICT_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addGroupByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				criteria.addAscendingOrderByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addAscendingOrderByColumn(SalesDistrictPeer.SALES_DISTRICT_NAME);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
	    
				String myQuery = BasePeer.createQueryString(criteria);
				String retPrc= new String( "("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ") * "  + DorderPeer.UNIT_PRICE);
				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
	
				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
	                
				for (int i = 0; i < records.size(); i++) {
				  results.add( ((Record) records.get(i)) );
				}
				context.put ("entries", results);

			}else if (reportid==13){
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
	    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + ")");
				criteria.addSelectColumn(SalesDistrictPeer.SALES_AREA_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
	                
				criteria.addGroupByColumn(SalesDistrictPeer.SALES_AREA_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				criteria.addAscendingOrderByColumn(SalesAreaPeer.SALES_AREA_NAME);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
	    
				String myQuery = BasePeer.createQueryString(criteria);
				String retPrc= new String( "("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ") * "  + DorderPeer.UNIT_PRICE);
				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
	
				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
	                
				for (int i = 0; i < records.size(); i++) {
				  results.add( ((Record) records.get(i)) );
				}
				context.put ("entries", results);

			}else if (reportid==14){
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(CountryPeer.COUNTRY_ID, LocationPeer.COUNTRY_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
	    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + ")");
				criteria.addSelectColumn(LocationPeer.COUNTRY_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(CountryPeer.COUNTRY_NAME);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
	                
				criteria.addGroupByColumn(LocationPeer.COUNTRY_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(CountryPeer.COUNTRY_NAME);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				criteria.addAscendingOrderByColumn(CountryPeer.COUNTRY_NAME);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
				// stuff something into the Velocity Context
	    
				String myQuery = BasePeer.createQueryString(criteria);
				String retPrc= new String( "("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ") * "  + DorderPeer.UNIT_PRICE);
				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
	
				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
	                
				for (int i = 0; i < records.size(); i++) {
				  results.add( ((Record) records.get(i)) );
				}
				context.put ("entries", results);

			}else if (reportid==15){
				criteria.addJoin(LocationPeer.LOCATION_ID, DorderItemPeer.LOCATION_ID);
				criteria.addJoin(DorderPeer.DORDER_ID, DorderItemPeer.DORDER_ID);
				criteria.addJoin(DistributorPeer.DISTRIBUTOR_ID, DorderPeer.DISTRIBUTOR_ID);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
				criteria.addJoin(SalesAreaPeer.SALES_AREA_ID, SalesDistrictPeer.SALES_AREA_ID);
				criteria.addJoin(ProductPeer.PRODUCT_ID, DorderPeer.PRODUCT_ID);
				criteria.addJoin(ProjectPeer.PROJECT_ID, DorderPeer.PROJECT_ID);
	    
				criteria.addSelectColumn("SUM("  + DorderItemPeer.UNSOLD_QTY + ")");
				criteria.addSelectColumn(DorderPeer.DISTRIBUTOR_ID);
				criteria.addSelectColumn(DorderPeer.PRODUCT_ID);
				criteria.addSelectColumn(DistributorPeer.DISTRIBUTOR_DISPLAY);
				criteria.addSelectColumn(ProductPeer.PRODUCT_CODE);
				criteria.addSelectColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				setDorderCriteria(data, criteria);
				setDistributorCriteria(data, criteria);
				setLocationCriteria(data, criteria);
				setProjectCriteria(data, criteria);
				setProductCriteria(data, criteria);
	                
				criteria.addGroupByColumn(DorderPeer.DISTRIBUTOR_ID);
				criteria.addGroupByColumn(DorderPeer.PRODUCT_ID);
				criteria.addGroupByColumn(DistributorPeer.DISTRIBUTOR_DISPLAY);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_CODE);
				criteria.addGroupByColumn(ProductPeer.PRODUCT_DISPLAY);
	    
				criteria.addAscendingOrderByColumn(DistributorPeer.DISTRIBUTOR_DISPLAY);
				criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
	    
				String myQuery = BasePeer.createQueryString(criteria);
				String retPrc= new String( "("  + DorderItemPeer.SHIPPED_QTY + " - " + DorderItemPeer.UNSOLD_QTY + " - " + DorderItemPeer.LOST_QTY + ") * "  + DorderPeer.UNIT_PRICE);
				myQuery= myQuery.replaceFirst(DorderItemPeer.UNSOLD_QTY, retPrc);
	
				List records = BasePeer.executeQuery(myQuery);
				List results = new ArrayList();
	                
				for (int i = 0; i < records.size(); i++) {
				  results.add( ((Record) records.get(i)) );
				}
				context.put ("entries", results);

            }
            
            context.put ("reptitle", data.getParameters().getString("reptitle"));

            // general formating stuff into the context
            context.put("df", new SimpleDateFormat ("dd.MM.yyyy"));
            DecimalFormatSymbols symb= new DecimalFormatSymbols();
            symb.setDecimalSeparator('.');
            context.put("af", new DecimalFormat ("0.00", symb));
            context.put("rf", new DecimalFormat ("0.000000", symb));
            context.put("today", new Date());

        }
        catch (Exception e)
        {
			context.put ("sqlstr", criteria.toString());
            throw(e);
        }

    }

    private void setDorderCriteria(RunData data, Criteria criteria)
        throws Exception
    {
        Date fromDate= parseDate(data.getParameters().getString("fromdate"));
        Date toDate= parseDate(data.getParameters().getString("todate"));
        int status= data.getParameters().getInt("status", 30);
        
        Criteria.Criterion b1 = criteria.getNewCriterion(DorderPeer.SALES_DATE, fromDate, Criteria.GREATER_EQUAL);
        Criteria.Criterion b2 = criteria.getNewCriterion(DorderPeer.SALES_DATE, toDate, Criteria.LESS_THAN);
        criteria.add( b1.and( b2));

        criteria.add(DorderPeer.STATUS, new Integer(status), Criteria.EQUAL);
    }

    private void setDistributorCriteria(RunData data, Criteria criteria)
        throws Exception
    {
        int distributorId = data.getParameters().getInt("distributorid", 999);
        int distributorCatId = data.getParameters().getInt("distributorcatid", 999);
        int distributorType = data.getParameters().getInt("distributortype", 1);
        
        if (distributorId>999){
            criteria.add(DistributorPeer.DISTRIBUTOR_ID, new Integer(distributorId), Criteria.EQUAL);
        }else{
            
            if (distributorType>1){
                criteria.add(DistributorPeer.DISTRIBUTOR_TYPE, new Integer(distributorType), Criteria.EQUAL);
            }
            if (distributorCatId>999){
                criteria.add(DistributorPeer.DISTRIBUTOR_CAT_ID, new Integer(distributorCatId), Criteria.EQUAL);
            }
        }
        
    }

	private void setLocationCriteria(RunData data, Criteria criteria)
		throws Exception
	{
		int locationId = data.getParameters().getInt("locationid", 999);
		int locationCatId = data.getParameters().getInt("locationcatid", 999);
		int locationType = data.getParameters().getInt("locationtype", 1);
		int countryId = data.getParameters().getInt("countryid", 999);
		int regionId = data.getParameters().getInt("regionid", 999);
		int salesAreaId = data.getParameters().getInt("salesareaid", 999);
		int salesDistrictId = data.getParameters().getInt("salesdistrictid", 999);
        
		if (locationId>999){
			criteria.add(LocationPeer.LOCATION_ID, new Integer(locationId), Criteria.EQUAL);
		}else{
            
			if (locationType>1){
				criteria.add(LocationPeer.LOCATION_TYPE, new Integer(locationType), Criteria.EQUAL);
			}
			if (locationCatId>999){
				criteria.add(LocationPeer.LOCATION_CAT_ID, new Integer(locationCatId), Criteria.EQUAL);
			}
			if (countryId>999){
				criteria.add(LocationPeer.COUNTRY_ID, new Integer(countryId), Criteria.EQUAL);
			}
			if (regionId>999){
				criteria.add(LocationPeer.REGION_ID, new Integer(regionId), Criteria.EQUAL);
			}
			if (salesAreaId>999){
				criteria.add(SalesDistrictPeer.SALES_AREA_ID, new Integer(salesAreaId), Criteria.EQUAL);
				criteria.addJoin(SalesDistrictPeer.SALES_DISTRICT_ID, LocationPeer.SALES_DISTRICT_ID);
			}
			if (salesDistrictId>999){
				criteria.add(LocationPeer.SALES_DISTRICT_ID, new Integer(salesDistrictId), Criteria.EQUAL);
			}
		}
        
	}

    private void setProjectCriteria(RunData data, Criteria criteria)
        throws Exception
    {
        int projectId = data.getParameters().getInt("projectid", 999);
        int projectCatId = data.getParameters().getInt("projectcatid", 999);
        
        if (projectId>999){
            criteria.add(ProjectPeer.PROJECT_ID, new Integer(projectId), Criteria.EQUAL);
        }else{
            
            if (projectCatId>999){
                criteria.add(ProjectPeer.PROJECT_CAT_ID, new Integer(projectCatId), Criteria.EQUAL);
            }
        }
        
    }

    private void setProductCriteria(RunData data, Criteria criteria)
        throws Exception
    {
        
        int productId = data.getParameters().getInt("productid", 999);
        int productCatId = data.getParameters().getInt("productcatid", 999);
        int productType = data.getParameters().getInt("producttype", 1);
        int vendorId = data.getParameters().getInt("vendorid", 999);
        int uomId = data.getParameters().getInt("uomid", 999);
        
        if (productId>999){
            criteria.add(ProductPeer.PRODUCT_ID, new Integer(productId), Criteria.EQUAL);
        }else{
            
            if (productType>1){
                criteria.add(ProductPeer.PRODUCT_TYPE, new Integer(productType), Criteria.EQUAL);
            }
            if (productCatId>999){
                criteria.add(ProductPeer.PRODUCT_CAT_ID, new Integer(productCatId), Criteria.EQUAL);
            }
            if (vendorId>999){
                criteria.add(ProductPeer.VENDOR_ID, new Integer(vendorId), Criteria.EQUAL);
            }
            if (uomId>999 || (uomId<900 && uomId>100)){
                criteria.add(ProductPeer.UOM_ID, new Integer(uomId), Criteria.EQUAL);
            }
        }
    }

    
    /**
     * Implement this to add information to the context.
     *
     * @param data Turbine information.
     * @param context Context for web pages.
     * @exception Exception, a generic exception.
     */
    public void doPerform( RunData data,Context context )
        throws Exception
    {
        data.setMessage("Can't find the button!");
    }

    /**
     * This currently only checks to make sure that user is allowed to
     * view the storage area.  If you create an action that requires more
     * security then override this method.
     *
     * @param data Turbine information.
     * @return True if the user is authorized to access the screen.
     * @exception Exception, a generic exception.
     */
    protected boolean isAuthorized( RunData data ) throws Exception
    {
        int reportid= data.getParameters().getInt("reportid", 0);
        boolean isAuthorized = false;

        AccessControlList acl = data.getACL();
        
        if (reportid>=1 && reportid<=5 && data.getUser().hasLoggedIn() && (acl.hasPermission( "DORDER_VIEW") || acl.hasRole("turbine_root")))
        {
            isAuthorized = true;
        }
        else if (reportid>5 && data.getUser().hasLoggedIn() && ((acl.hasPermission( "DORDER_VIEW") && acl.hasPermission( "DCONTRACT_VIEW")) || acl.hasRole("turbine_root")))
        {
            isAuthorized = true;
        }
        else
        {
            data.setMessage("Sorry, you don't have permission for this operation!");
            this.setTemplate( data, "CreamError.vm");

            isAuthorized = false;
        }

        return isAuthorized;
    }

    protected Date parseDateTime(String d)
        throws Exception
    {
//        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).parse(d);
        SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy hh:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(d, pos);
    }

    protected Date parseDate(String d)
        throws Exception
    {
//        return DateFormat.getDateInstance(DateFormat.SHORT).parse(d);
        SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy");
        ParsePosition pos = new ParsePosition(0);
        try{
            Date myDate= formatter.parse(d, pos);
            return myDate;
        }
        catch (Exception e)
        {
            return null;
        }
            
    }

}
