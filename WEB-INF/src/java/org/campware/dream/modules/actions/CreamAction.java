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
import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import org.apache.velocity.context.Context;

import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.services.resources.TurbineResources;

/**
 * Cream Secure action.
 *
 * Always performs a Security Check that you've defined before
 * executing the doBuildtemplate().
 * @author <a href="mailto:pandzic@volny.cz">Nenad Pandzic</a>
 */
public class CreamAction extends VelocitySecureAction
{
    public static final int ENTITY=1001;
    public static final int DOCUMENT=1002;
    public static final int LOOKUP=1003;
    public static final int SYSTEM=1004;
    public static final int REPORT=1005;
    public static final int UTIL=1006;

    private int defModuleType;
    private String defModuleName=new String();

    protected void initScreen()
    {
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
        initScreen();
        boolean isAuthorized = false;

        AccessControlList acl = data.getACL();
        
        if (data.getUser().hasLoggedIn())
        {
			if (acl.hasPermission( defModuleName + "_MODIFY") || acl.hasRole("turbine_root"))
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

    protected Date parseDateTime(String d)
        throws Exception
    {
//        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).parse(d);
        SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(d, pos);
    }

	protected Date parseDateShortTime(String d)
		throws Exception
	{
//		  return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).parse(d);
		SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy HH:mm");
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

	protected String formatDate(Date d)
	{
		SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy");
		return formatter.format(d);
	}
    

    protected String getTempCode()
    {
        Date currDate= new Date();

        return Integer.toString(currDate.hashCode());
    }

    protected String getRowCode(String s, int i)
    {
        String is= new String();
        
        is= Integer.toString(i);
        while (is.length()<7)
        {
            is="0" + is;
        }

        is= s + is;
        return is;
    }

    protected void setModuleName(String name)
    {
            defModuleName=name;
    }

    protected void setModuleType(int modtype)
    {
            defModuleType=modtype;
    }

}
