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
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;

import org.apache.torque.util.Criteria;

import org.apache.velocity.context.Context;
import org.apache.turbine.services.resources.TurbineResources;

/**
 * Grabs a record from a database and makes
 * the data available in the template.
 *
 * @author <a href="mailto:pandzic@volny.cz">Nenad Pandzic</a>
 */
public class CreamForm extends SecureScreen
{

    private int defModuleType;
    private String defModuleName=new String();
    private String defIdName=new String();
    private String defFormIdName=new String();
    protected RunData myData;

    protected void initScreen()
    {
    }

    /**
     * Grab a record from the database based on the entry_id
     * found in the form. Make the data available in the
     * template.
     */
    public void doBuildTemplate(RunData data, Context context)
    {
        try
        {
            myData= data;
            int entry_id = data.getParameters().getInt(defFormIdName);
            
            if (entry_id>0)
            {
                Criteria criteria = new Criteria();
                criteria.add(defIdName, entry_id);
                getEntry(criteria, context);

                context.put("mode", "update");
            }
            else if (entry_id<0)
            {
				getNewRelated(entry_id * (-1), context);
				context.put("mode", "insert");
            }
            else
            {
                getNew(context);
                context.put("mode", "insert");
            }

            getLookups(context);
            
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

    protected boolean getEntry(Criteria criteria, Context context)
    {
            return false;
    }

	protected boolean getNewRelated(int relid, Context context)
	{
			return false;
	}

    protected boolean getNew(Context context)
    {
            return false;
    }

    protected boolean getLookups(Context context)
    {
            return false;
    }

    protected void setIdName(String name)
    {
            defIdName=name;
    }

    protected void setFormIdName(String name)
    {
            defFormIdName=name;
    }

    protected void setModuleName(String name)
    {
            defModuleName=name;
    }

    protected void setModuleType(int modtype)
    {
            defModuleType=modtype;
    }

    protected String formatDateTime(Date d)
    {
//        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(d);
        SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy HH:mm:ss");
        return formatter.format(d);
    }

    protected String formatDate(Date d)
    {
//        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy");
        return formatter.format(d);
    }
    
    
}
