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
 * extended all references should be to LocationCategory
 */
public abstract class BaseLocationCategory extends BaseObject
{
    /** The Peer class */
    private static final LocationCategoryPeer peer =
        new LocationCategoryPeer();

                  
        /**
         * The value for the location_cat_id field
         */
        private int location_cat_id;
              
        /**
         * The value for the location_cat_name field
         */
        private String location_cat_name;
      
      
        /**
         * Get the LocationCatId
         *
         * @return int
         */
        public int getLocationCatId()
        {
            return location_cat_id;
        }

                                                                              
        /**
         * Set the value of LocationCatId
         *
         * @param v new value
         */
        public void setLocationCatId(int v) throws TorqueException
        {
          


         if (this.location_cat_id != v)
        {
             this.location_cat_id = v;
            setModified(true);
        }

                  
                                                  
              // update associated Location
              if (collLocations != null)
              {
                  for (int i = 0; i < collLocations.size(); i++)
                  {
                      ((Location) collLocations.get(i))
                          .setLocationCatId(v);
                  }
              }
                                   }


        /**
         * Get the LocationCatName
         *
         * @return String
         */
        public String getLocationCatName()
        {
            return location_cat_name;
        }

                                            
        /**
         * Set the value of LocationCatName
         *
         * @param v new value
         */
        public void setLocationCatName(String v) 
        {
          


         if (!ObjectUtils.equals(this.location_cat_name, v))
        {
             this.location_cat_name = v;
            setModified(true);
        }

                  
                       }


 
        
                
      
    /**
     * Collection to store aggregation of collLocations
     */
    protected List collLocations;

    /**
     * Temporary storage of collLocations to save a possible db hit in
     * the event objects are add to the collection, but the
     * complete collection is never requested.
     */
    protected void initLocations()
    {
        if (collLocations == null)
        {
            collLocations = new ArrayList();
        }
    }

    /**
     * Method called to associate a Location object to this object
     * through the Location foreign key attribute
     *
     * @param l Location
     * @throws TorqueException
     */
    public void addLocation(Location l) throws TorqueException
    {
        getLocations().add(l);
        l.setLocationCategory((LocationCategory) this);
    }

    /**
     * The criteria used to select the current contents of collLocations
     */
    private Criteria lastLocationsCriteria = null;

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getLocations(new Criteria())
     *
     * @throws TorqueException
     */
    public List getLocations() throws TorqueException
    {
        if (collLocations == null)
        {
            collLocations = getLocations(new Criteria(10));
        }
        return collLocations;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this LocationCategory has previously
     * been saved, it will retrieve related Locations from storage.
     * If this LocationCategory is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     *
     * @throws TorqueException
     */
    public List getLocations(Criteria criteria) throws TorqueException
    {
        if (collLocations == null)
        {
            if (isNew())
            {
               collLocations = new ArrayList();
            }
            else
            {
                   criteria.add(LocationPeer.LOCATION_CAT_ID, getLocationCatId() );
                   collLocations = LocationPeer.doSelect(criteria);
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
                   criteria.add(LocationPeer.LOCATION_CAT_ID, getLocationCatId());
                   if (!lastLocationsCriteria.equals(criteria))
                {
                    collLocations = LocationPeer.doSelect(criteria);
                }
            }
        }
        lastLocationsCriteria = criteria;

        return collLocations;
    }

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getLocations(new Criteria(),Connection)
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getLocations(Connection con) throws TorqueException
    {
        if (collLocations == null)
        {
            collLocations = getLocations(new Criteria(10), con);
        }
        return collLocations;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this LocationCategory has previously
     * been saved, it will retrieve related Locations from storage.
     * If this LocationCategory is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getLocations(Criteria criteria, Connection con)
            throws TorqueException
    {
        if (collLocations == null)
        {
            if (isNew())
            {
               collLocations = new ArrayList();
            }
            else
            {
                     criteria.add(LocationPeer.LOCATION_CAT_ID, getLocationCatId());
                     collLocations = LocationPeer.doSelect(criteria, con);
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
                     criteria.add(LocationPeer.LOCATION_CAT_ID, getLocationCatId());
                     if (!lastLocationsCriteria.equals(criteria))
                 {
                     collLocations = LocationPeer.doSelect(criteria, con);
                 }
             }
         }
         lastLocationsCriteria = criteria;

         return collLocations;
     }

       

     
      
         
          
                    
                
        
        
    
      
      
          
                    
                
        
        
    
      
      
          
                    
                
        
        
    
      
      
          
                    
                
        
        
    
      
      
          
                    
                
        
        
      



     
    
     


    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws Exception
     */
    public void save() throws Exception
    {
             save(LocationCategoryPeer.getMapBuilder()
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
                LocationCategoryPeer.doInsert((LocationCategory) this, con);
                setNew(false);
            }
            else
            {
                LocationCategoryPeer.doUpdate((LocationCategory) this, con);
            }
        }

                                    
                
          if (collLocations != null)
          {
              for (int i = 0; i < collLocations.size(); i++)
              {
                  ((Location) collLocations.get(i)).save(con);
              }
          }
                  alreadyInSave = false;
      }
      }


                        
    
    

        /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  location_cat_id ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        throws TorqueException
    {
                    setLocationCatId(((NumberKey) key).intValue());
            }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) throws TorqueException
    {
                    setLocationCatId(Integer.parseInt(key));
            }


    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
        return SimpleKey.keyFor(getLocationCatId());
    }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     * It then fills all the association collections and sets the
     * related objects to isNew=true.
     */
    public LocationCategory copy() throws TorqueException
    {
        return copyInto(new LocationCategory());
    }

    protected LocationCategory copyInto(LocationCategory copyObj) throws TorqueException
    {
        copyObj.setLocationCatId(location_cat_id);
        copyObj.setLocationCatName(location_cat_name);

  copyObj.setNew(false);
                                    
                
        List v = getLocations();
        for (int i = 0; i < v.size(); i++)
        {
            Location obj = (Location) v.get(i);
            copyObj.addLocation(obj.copy());
            ((Persistent) v.get(i)).setNew(true);
        }
                copyObj.setNew(true);

                      copyObj.setLocationCatId(0);
                        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public LocationCategoryPeer getPeer()
    {
        return peer;
    }
}
