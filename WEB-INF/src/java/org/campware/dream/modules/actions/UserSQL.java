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

import org.apache.velocity.context.Context;

import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;

import org.apache.torque.util.Transaction;
import java.sql.Connection;
import org.apache.turbine.util.ParameterParser;
import java.util.Enumeration;

import org.campware.dream.om.TurbineUser;
import org.campware.dream.om.TurbineUserPeer;
import org.campware.dream.om.TurbineUserGroupRole;
import org.campware.dream.om.TurbineUserGroupRolePeer;
import org.campware.dream.om.DreamUser;
import org.campware.dream.om.DreamUserPeer;

/**
 * This class provides a simple set of methods to
 * insert/update/delete records in a database.
 */
public class UserSQL extends CreamAction
{
    protected void initScreen()
    {
        setModuleType(LOOKUP);
        setModuleName("TURBINE_USER");
    }

    /**
     * This simply takes an entry from the web form and
     * inserts it directly into the database.
     *
     * This would not be good in practice as the
     * data should be verified before being allowed
     * into the database. This is merely an
     * example of how to use peers, this certainly
     * wouldn't be secure.
     */
    public void doInsert(RunData data, Context context)
        throws Exception
    {
        TurbineUser entry = new TurbineUser();
        data.getParameters().setProperties(entry);
        DreamUser prefs = new DreamUser();
        data.getParameters().setProperties(prefs);

        ParameterParser pp= data.getParameters();
        Enumeration paramKeys= pp.keys();
        
	    while(paramKeys.hasMoreElements()) {
	        String paramName = paramKeys.nextElement().toString();
	        if(paramName.startsWith("roleid")) {	
	            String suffix=paramName.substring(6, paramName.length());
	            TurbineUserGroupRole entryItem= new TurbineUserGroupRole();

                entryItem.setGroupId(1);
	            entryItem.setRoleId(pp.getInt("roleid" + suffix));

	            entry.addTurbineUserGroupRole(entryItem);
            }
        }

        Connection conn = Transaction.begin(TurbineUserPeer.DATABASE_NAME);
        boolean success = false;
        try {
            entry.save(conn);
            int usrId=entry.getUserId();
            prefs.setUserId(usrId);
            prefs.save(conn);
            Transaction.commit(conn);
            success = true;

        } finally {
            if (!success) Transaction.safeRollback(conn);
        }
    }

    /**
     * Update a record in the database with the
     * information present in the web form.
     *
     * Again, this is merely an example. The data
     * should be checked before being allowed
     * into the database.
     */
    public void doUpdate(RunData data, Context context)
        throws Exception
    {
        TurbineUser entry = new TurbineUser();
        data.getParameters().setProperties(entry);
        DreamUser prefs = new DreamUser();
        data.getParameters().setProperties(prefs);

        ParameterParser pp= data.getParameters();
        Enumeration paramKeys= pp.keys();

	    while(paramKeys.hasMoreElements()) {
	        String paramName = paramKeys.nextElement().toString();
	        if(paramName.startsWith("roleid")) {	
	            String suffix=paramName.substring(6, paramName.length());
	            TurbineUserGroupRole entryItem= new TurbineUserGroupRole();

                entryItem.setGroupId(1);
	            entryItem.setRoleId(pp.getInt("roleid" + suffix));

	            entry.addTurbineUserGroupRole(entryItem);
            }
        }

        entry.setModified(true);
        entry.setNew(false);

        prefs.setModified(true);
        prefs.setNew(false);

        Criteria crit = new Criteria();
        crit.add(TurbineUserGroupRolePeer.USER_ID, entry.getUserId());

        Connection conn = Transaction.begin(TurbineUserPeer.DATABASE_NAME);
        boolean success = false;
        try {
            TurbineUserGroupRolePeer.doDelete(crit, conn);
            entry.save(conn);
            prefs.save(conn);
            Transaction.commit(conn);
            success = true;

        } finally {
            if (!success) Transaction.safeRollback(conn);
        }

    }

    /**
     * Delete a record from the database using
     * the unique id gleaned from the web form.
     */
    public void doDelete(RunData data, Context context)
        throws Exception
    {
        Criteria criteria = new Criteria();
        criteria.add(TurbineUserPeer.USER_ID, data.getParameters().getInt("userid"));

        Criteria prefcrit = new Criteria();
        prefcrit.add(DreamUserPeer.USER_ID, data.getParameters().getInt("userid"));

        Connection conn = Transaction.begin(TurbineUserPeer.DATABASE_NAME);
        boolean success = false;
        try {
            TurbineUserPeer.doDelete(criteria, conn);
            DreamUserPeer.doDelete(prefcrit, conn);
            Transaction.commit(conn);
            success = true;

        } finally {
            if (!success) Transaction.safeRollback(conn);
        }

    }

    /**
     * Delete selected records from the database using
     * the unique ids gleaned from the web form.
     */
    public void doDeleteselected(RunData data, Context context)
        throws Exception
    {
        int[] delIds= data.getParameters().getInts("rowid");

        Criteria criteria = new Criteria();
        criteria.addIn(TurbineUserPeer.USER_ID, delIds);
        Criteria prefcrit = new Criteria();
        prefcrit.addIn(DreamUserPeer.USER_ID, delIds);

        Connection conn = Transaction.begin(TurbineUserPeer.DATABASE_NAME);
        boolean success = false;
        try {
            TurbineUserPeer.doDelete(criteria, conn);
            DreamUserPeer.doDelete(prefcrit, conn);
            Transaction.commit(conn);
            success = true;

        } finally {
            if (!success) Transaction.safeRollback(conn);
        }

    }

}
