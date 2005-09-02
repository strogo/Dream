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

import org.campware.dream.om.Project;
import org.campware.dream.om.ProjectPeer;
import org.campware.dream.om.ProjectCategoryPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class ProjectForm extends CreamForm
{
    protected void initScreen()
    {
        setModuleType(ENTITY);
        setModuleName("PROJECT");
        setIdName(ProjectPeer.PROJECT_ID);
        setFormIdName("projectid");
    }

    protected boolean getEntry(Criteria criteria, Context context)
    {
        try
        {
            Project entry = (Project) ProjectPeer.doSelect(criteria).get(0);
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
            Project entry = new Project();
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
            Criteria projcatcrit = new Criteria();
            projcatcrit.add(ProjectCategoryPeer.PROJECT_CAT_ID, 999, Criteria.GREATER_THAN);
            projcatcrit.addAscendingOrderByColumn(ProjectCategoryPeer.PROJECT_CAT_NAME);
            context.put("projectcats", ProjectCategoryPeer.doSelect(projcatcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
}