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

import org.campware.dream.om.TurbineUser;
import org.campware.dream.om.TurbineUserPeer;
import org.campware.dream.om.TurbineRole;
import org.campware.dream.om.TurbineRolePeer;
import org.campware.dream.om.DreamUser;
import org.campware.dream.om.DreamUserPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class UserForm extends CreamForm
{
    protected void initScreen()
    {
        setModuleType(LOOKUP);
        setModuleName("TURBINE_USER");
        setIdName(TurbineUserPeer.USER_ID);
        setFormIdName("userid");
    }

    protected boolean getEntry(Criteria criteria, Context context)
    {
        try
        {
            TurbineUser entry = (TurbineUser) TurbineUserPeer.doSelect(criteria).get(0);

            Criteria prefcrit= new Criteria();
            prefcrit.add(DreamUserPeer.LOGIN_NAME, entry.getLoginName());
            DreamUser prefs = (DreamUser) DreamUserPeer.doSelect(prefcrit).get(0);
            
            context.put("entry", entry);
            context.put("entryitems", entry.getTurbineUserGroupRoles());
            context.put("prefs", prefs);
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
            TurbineUser entry = new TurbineUser();
            DreamUser prefs = new DreamUser();
            context.put("entry", entry);
            context.put("prefs", prefs);

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
            Criteria rolecrit = new Criteria();
            rolecrit.add(TurbineRolePeer.ROLE_ID, 999, Criteria.GREATER_THAN);
            rolecrit.addAscendingOrderByColumn(TurbineRolePeer.ROLE_NAME);
            context.put("roles", TurbineRolePeer.doSelect(rolecrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    
}
