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

import org.campware.dream.om.Product;
import org.campware.dream.om.ProductPeer;
import org.campware.dream.om.VendorPeer;
import org.campware.dream.om.ProductCategoryPeer;
import org.campware.dream.om.UomPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class ProductForm extends CreamForm
{
    protected void initScreen()
    {
        setModuleType(ENTITY);
        setModuleName("PRODUCT");
        setIdName(ProductPeer.PRODUCT_ID);
        setFormIdName("productid");
    }

    protected boolean getEntry(Criteria criteria, Context context)
    {
        try
        {
            Product entry = (Product) ProductPeer.doSelect(criteria).get(0);
            context.put("entry", entry);

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
            Product entry = new Product();
            context.put("entry", entry);
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
            Criteria vendorcrit = new Criteria();
            vendorcrit.add(VendorPeer.VENDOR_ID, 999, Criteria.GREATER_THAN);
            vendorcrit.addAscendingOrderByColumn(VendorPeer.VENDOR_NAME);
            context.put("vendors", VendorPeer.doSelect(vendorcrit));

            Criteria prodcatcrit = new Criteria();
            prodcatcrit.add(ProductCategoryPeer.PRODUCT_CAT_ID, 999, Criteria.GREATER_THAN);
            prodcatcrit.addAscendingOrderByColumn(ProductCategoryPeer.PRODUCT_CAT_NAME);
            context.put("productcats", ProductCategoryPeer.doSelect(prodcatcrit));

            Criteria uomcrit = new Criteria();
            uomcrit.add(UomPeer.UOM_ID, 900, Criteria.LESS_THAN);
            Criteria.Criterion criterion = uomcrit.getCriterion(UomPeer.UOM_ID);
            criterion.or(
                           uomcrit.getNewCriterion(
                                         criterion.getTable(),
                                         criterion.getColumn(),
                                         new Integer(999),
                                         Criteria.GREATER_THAN )
                           );

            uomcrit.addAscendingOrderByColumn(UomPeer.UOM_NAME);
            context.put("uoms", UomPeer.doSelect(uomcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
}
