package org.campware.dream.om;


import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.torque.TorqueException;
import org.apache.torque.om.BaseObject;
import org.apache.torque.om.ComboKey;
import org.apache.torque.om.DateKey;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.om.StringKey;
import org.apache.torque.om.Persistent;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;


/**
 * This class was autogenerated by Torque on:
 *
 * [Thu Jul 29 08:38:49 CEST 2004]
 *
 * You should not use this class directly.  It should not even be
 * extended all references should be to ProjectCategory
 */
public abstract class BaseProjectCategory extends BaseObject
{
    /** The Peer class */
    private static final ProjectCategoryPeer peer =
        new ProjectCategoryPeer();

                  
        /**
         * The value for the project_cat_id field
         */
        private int project_cat_id;
              
        /**
         * The value for the project_cat_name field
         */
        private String project_cat_name;
      
      
        /**
         * Get the ProjectCatId
         *
         * @return int
         */
        public int getProjectCatId()
        {
            return project_cat_id;
        }

                                                                              
        /**
         * Set the value of ProjectCatId
         *
         * @param v new value
         */
        public void setProjectCatId(int v) throws TorqueException
        {
          


         if (this.project_cat_id != v)
        {
             this.project_cat_id = v;
            setModified(true);
        }

                  
                                                  
              // update associated Project
              if (collProjects != null)
              {
                  for (int i = 0; i < collProjects.size(); i++)
                  {
                      ((Project) collProjects.get(i))
                          .setProjectCatId(v);
                  }
              }
                                   }


        /**
         * Get the ProjectCatName
         *
         * @return String
         */
        public String getProjectCatName()
        {
            return project_cat_name;
        }

                                            
        /**
         * Set the value of ProjectCatName
         *
         * @param v new value
         */
        public void setProjectCatName(String v) 
        {
          


         if (!ObjectUtils.equals(this.project_cat_name, v))
        {
             this.project_cat_name = v;
            setModified(true);
        }

                  
                       }


 
        
                
      
    /**
     * Collection to store aggregation of collProjects
     */
    protected List collProjects;

    /**
     * Temporary storage of collProjects to save a possible db hit in
     * the event objects are add to the collection, but the
     * complete collection is never requested.
     */
    protected void initProjects()
    {
        if (collProjects == null)
        {
            collProjects = new ArrayList();
        }
    }

    /**
     * Method called to associate a Project object to this object
     * through the Project foreign key attribute
     *
     * @param l Project
     * @throws TorqueException
     */
    public void addProject(Project l) throws TorqueException
    {
        getProjects().add(l);
        l.setProjectCategory((ProjectCategory) this);
    }

    /**
     * The criteria used to select the current contents of collProjects
     */
    private Criteria lastProjectsCriteria = null;

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getProjects(new Criteria())
     *
     * @throws TorqueException
     */
    public List getProjects() throws TorqueException
    {
        if (collProjects == null)
        {
            collProjects = getProjects(new Criteria(10));
        }
        return collProjects;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ProjectCategory has previously
     * been saved, it will retrieve related Projects from storage.
     * If this ProjectCategory is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     *
     * @throws TorqueException
     */
    public List getProjects(Criteria criteria) throws TorqueException
    {
        if (collProjects == null)
        {
            if (isNew())
            {
               collProjects = new ArrayList();
            }
            else
            {
                   criteria.add(ProjectPeer.PROJECT_CAT_ID, getProjectCatId() );
                   collProjects = ProjectPeer.doSelect(criteria);
            }
        }
        else
        {
            // criteria has no effect for a new object
            if (!isNew())
            {
                // the following code is to determine if a new query is
                // called for.  If the criteria is the same as the last
                // one, just return the collection.
                   criteria.add(ProjectPeer.PROJECT_CAT_ID, getProjectCatId());
                   if (!lastProjectsCriteria.equals(criteria))
                {
                    collProjects = ProjectPeer.doSelect(criteria);
                }
            }
        }
        lastProjectsCriteria = criteria;

        return collProjects;
    }

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getProjects(new Criteria(),Connection)
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getProjects(Connection con) throws TorqueException
    {
        if (collProjects == null)
        {
            collProjects = getProjects(new Criteria(10), con);
        }
        return collProjects;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this ProjectCategory has previously
     * been saved, it will retrieve related Projects from storage.
     * If this ProjectCategory is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getProjects(Criteria criteria, Connection con)
            throws TorqueException
    {
        if (collProjects == null)
        {
            if (isNew())
            {
               collProjects = new ArrayList();
            }
            else
            {
                     criteria.add(ProjectPeer.PROJECT_CAT_ID, getProjectCatId());
                     collProjects = ProjectPeer.doSelect(criteria, con);
             }
         }
         else
         {
             // criteria has no effect for a new object
             if (!isNew())
             {
                 // the following code is to determine if a new query is
                 // called for.  If the criteria is the same as the last
                 // one, just return the collection.
                     criteria.add(ProjectPeer.PROJECT_CAT_ID, getProjectCatId());
                     if (!lastProjectsCriteria.equals(criteria))
                 {
                     collProjects = ProjectPeer.doSelect(criteria, con);
                 }
             }
         }
         lastProjectsCriteria = criteria;

         return collProjects;
     }

   

     
      
         
          
                    
                
        
        
      



     
    
     


    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws Exception
     */
    public void save() throws Exception
    {
             save(ProjectCategoryPeer.getMapBuilder()
                .getDatabaseMap().getName());
     }

    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     * Note: this code is here because the method body is
     * auto-generated conditionally and therefore needs to be
     * in this file instead of in the super class, BaseObject.
     *
     * @param dbName
     * @throws TorqueException
     */
    public void save(String dbName) throws TorqueException
    {
        Connection con = null;
         try
        {
            con = Transaction.begin(dbName);
            save(con);
            Transaction.commit(con);
        }
        catch(TorqueException e)
        {
            Transaction.safeRollback(con);
            throw e;
        }

     }

      /** flag to prevent endless save loop, if this object is referenced
        by another object which falls in this transaction. */
    private boolean alreadyInSave = false;
      /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.  This method
     * is meant to be used as part of a transaction, otherwise use
     * the save() method and the connection details will be handled
     * internally
     *
     * @param con
     * @throws TorqueException
     */
    public void save(Connection con) throws TorqueException
    {
        if (!alreadyInSave)
      {
        alreadyInSave = true;



  
        // If this object has been modified, then save it to the database.
        if (isModified())
        {
            if (isNew())
            {
                ProjectCategoryPeer.doInsert((ProjectCategory) this, con);
                setNew(false);
            }
            else
            {
                ProjectCategoryPeer.doUpdate((ProjectCategory) this, con);
            }
        }

                                    
                
          if (collProjects != null)
          {
              for (int i = 0; i < collProjects.size(); i++)
              {
                  ((Project) collProjects.get(i)).save(con);
              }
          }
                  alreadyInSave = false;
      }
      }


                        
    
    

        /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  project_cat_id ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        throws TorqueException
    {
                    setProjectCatId(((NumberKey) key).intValue());
            }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) throws TorqueException
    {
                    setProjectCatId(Integer.parseInt(key));
            }


    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
        return SimpleKey.keyFor(getProjectCatId());
    }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     * It then fills all the association collections and sets the
     * related objects to isNew=true.
     */
    public ProjectCategory copy() throws TorqueException
    {
        return copyInto(new ProjectCategory());
    }

    protected ProjectCategory copyInto(ProjectCategory copyObj) throws TorqueException
    {
        copyObj.setProjectCatId(project_cat_id);
        copyObj.setProjectCatName(project_cat_name);

  copyObj.setNew(false);
                                    
                
        List v = getProjects();
        for (int i = 0; i < v.size(); i++)
        {
            Project obj = (Project) v.get(i);
            copyObj.addProject(obj.copy());
            ((Persistent) v.get(i)).setNew(true);
        }
                copyObj.setNew(true);

                      copyObj.setProjectCatId(0);
                        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public ProjectCategoryPeer getPeer()
    {
        return peer;
    }
}
