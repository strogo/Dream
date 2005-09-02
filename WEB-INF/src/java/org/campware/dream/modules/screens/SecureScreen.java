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

import org.apache.turbine.modules.screens.VelocitySecureScreen;
import org.apache.turbine.util.RunData;
import org.apache.turbine.services.resources.TurbineResources;

import org.apache.velocity.context.Context;

/**
 * Grab all the records in a table using a Peer, and
 * place the Vector of data objects in the context
 * where they can be displayed by a #foreach loop.
 *
 * @author <a href="mailto:jvanzyl@periapt.com">Jason van Zyl</a>
 */
public class SecureScreen extends VelocitySecureScreen
{

    public static final int ENTITY=1001;
    public static final int DOCUMENT=1002;
    public static final int LOOKUP=1003;
    public static final int SYSTEM=1004;
    public static final int REPORT=1005;
    public static final int UTIL=1006;

    public void doBuildTemplate(RunData data, Context context)
    {
    }

    /**
     * Overide this method to perform the security check needed.
     *
     * @param data Turbine information.
     * @return True if the user is authorized to access the screen.
     * @exception Exception, a generic exception.
     */
    protected boolean isAuthorized( RunData data )  throws Exception
    {
        boolean isAuthorized = false;
        
        if (data.getUser().hasLoggedIn())
        {
            isAuthorized = true;
        }
        else
        {
//            data.setMessage("Please Login first!");
            data.setScreenTemplate(TurbineResources.getString("template.login"));

            isAuthorized = false;
        }
        
//        AccessControlList acl = data.getACL();

//        if (acl==null || ! acl.hasRole("turbine_root"))
//        {
//            data.setScreenTemplate(
//                TurbineResources.getString("template.login"));
//
//            isAuthorized = false;
//        }
//        else if(acl.hasRole("turbine_root"))
//        {
//            isAuthorized = true;
//        }
        return isAuthorized;
    }
}
