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

import org.campware.dream.om.DorderPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class DistributorProductOrders extends CreamUtil
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
			int productId = data.getParameters().getInt("id2");
            
			if (distributorId>0)
			{

				try
				{
					Criteria ordcrit = new Criteria();
					ordcrit.add(DorderPeer.DISTRIBUTOR_ID, distributorId);
					ordcrit.add(DorderPeer.PRODUCT_ID, productId);
					ordcrit.add(DorderPeer.STATUS, 50, Criteria.EQUAL);

					ordcrit.addDescendingOrderByColumn(DorderPeer.DORDER_CODE);
					context.put("orders", DorderPeer.doSelect(ordcrit));
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
