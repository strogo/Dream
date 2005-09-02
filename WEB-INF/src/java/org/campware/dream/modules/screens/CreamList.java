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
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;

import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.services.resources.TurbineResources;

/**
 * Grab all the records in a table using a Peer, and
 * place the Vector of data objects in the context
 * where they can be displayed by a #foreach loop.
 *
 * @author <a href="mailto:pandzic@volny.cz">Nenad Pandzic</a>
 */
public class CreamList extends SecureScreen
{

    private int defModuleType;
    private String defModuleName=new String();
    private String defIdName=new String();
    private String defOrderColumn=new String();

    protected void initScreen()
    {
    }
    
    /**
     * Place all the data object in the context
     * for use in the template.
     */
    public void doBuildTemplate(RunData data, Context context)
    {

        int filterNo = 0;
        int sortNo = 0;
        String orderStr = new String();
        String findStr = new String();

        Locale myLocale = new Locale("ENGLISH", "UK");
        data.setLocale(myLocale);
        
        filterNo = data.getParameters().getInt("filter", 0);
        sortNo = data.getParameters().getInt("sortcol", 0);
        orderStr = data.getParameters().getString("sortord", "ASC");
        findStr = data.getParameters().getString("find", "");

        Criteria criteria = new Criteria();
        criteria.add(defIdName, 1000, Criteria.GREATER_THAN);

        if (filterNo>0)
        {
            setFilter(filterNo, criteria, data);
        }
        else
        {
            if (findStr.length()>0)
            {
                findStr= findStr + "%";
                setFind(findStr, criteria);
            }
        }

        if (sortNo>0)
        {
            String sortCol= new String(getSortColumn(sortNo));
            if (sortCol.length()>0){
                if (orderStr.equals("ASC")){
                    criteria.addAscendingOrderByColumn(sortCol);
                }else{
                    criteria.addDescendingOrderByColumn(sortCol);
                }
            }
        }

        criteria.setIgnoreCase(true);

        context.put("entries", getEntries(criteria));
        context.put("df", new SimpleDateFormat ("dd.MM.yyyy"));

        DecimalFormatSymbols symb= new DecimalFormatSymbols();
        symb.setDecimalSeparator('.');
        
        context.put("af", new DecimalFormat ("0.00", symb));
        context.put("rf", new DecimalFormat ("0.000000", symb));
        
    }

    protected boolean isAuthorized( RunData data )  throws Exception
    {

        initScreen();
        boolean isAuthorized = false;
        AccessControlList acl = data.getACL();
        
        if (data.getUser().hasLoggedIn()) 
        {
        	if (acl.hasPermission( defModuleName + "_VIEW") || acl.hasRole("turbine_root"))
        	{
				isAuthorized = true;
        	}
        	else
        	{
				data.setMessage("Sorry, you don't have permission for this operation!");
				data.setScreenTemplate("CreamError.vm");

				isAuthorized = false;
        	}
        }
        else
        {
			data.setScreenTemplate(TurbineResources.getString("template.login"));

            isAuthorized = false;
        }
        
        return isAuthorized;
    }

    protected void setFilter(int filterNo, Criteria listCriteria, RunData data)
    {
    }

    protected void setSort(int sortNo, Criteria listCriteria)
    {
    }

    protected String getSortColumn(int sortNo)
    {
        return "";
    }

    protected void setFind(String findStr, Criteria listCriteria)
    {
    }

    protected void setDefOrderColumn(String name)
    {
            defOrderColumn=name;
    }

    protected void setIdName(String name)
    {
            defIdName=name;
    }

    protected void setModuleName(String name)
    {
            defModuleName=name;
    }

    protected void setModuleType(int modtype)
    {
            defModuleType=modtype;
    }

    /**
     * This will return all the records in
     * the database but they have been mapped to
     * objects so they can be directly used
     * in the Velocity template.
     */
    protected List getEntries(Criteria criteria)
    {
            return null;
    }


}
