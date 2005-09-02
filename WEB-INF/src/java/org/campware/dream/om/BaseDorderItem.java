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
 * extended all references should be to DorderItem
 */
public abstract class BaseDorderItem extends BaseObject
{
    /** The Peer class */
    private static final DorderItemPeer peer =
        new DorderItemPeer();

                  
        /**
         * The value for the dorder_item_id field
         */
        private int dorder_item_id;
                                                                            
        /**
         * The value for the dorder_id field
         */
        private int dorder_id = 1000;
                                                                            
        /**
         * The value for the location_id field
         */
        private int location_id = 1000;
                                                                            
        /**
         * The value for the ordered_qty field
         */
        private int ordered_qty = 0;
                                                                            
        /**
         * The value for the shipped_qty field
         */
        private int shipped_qty = 0;
                                                                            
        /**
         * The value for the unsold_qty field
         */
        private int unsold_qty = 0;
                                                                            
        /**
         * The value for the lost_qty field
         */
        private int lost_qty = 0;
      
      
        /**
         * Get the DorderItemId
         *
         * @return int
         */
        public int getDorderItemId()
        {
            return dorder_item_id;
        }

                                            
        /**
         * Set the value of DorderItemId
         *
         * @param v new value
         */
        public void setDorderItemId(int v) 
        {
          


         if (this.dorder_item_id != v)
        {
             this.dorder_item_id = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the DorderId
         *
         * @return int
         */
        public int getDorderId()
        {
            return dorder_id;
        }

                                                      
        /**
         * Set the value of DorderId
         *
         * @param v new value
         */
        public void setDorderId(int v) throws TorqueException
        {
          


         if (this.dorder_id != v)
        {
             this.dorder_id = v;
            setModified(true);
        }

                                          
                if (aDorder != null && !(aDorder.getDorderId()==v))
                {
            aDorder = null;
        }
          
                       }


        /**
         * Get the LocationId
         *
         * @return int
         */
        public int getLocationId()
        {
            return location_id;
        }

                                                      
        /**
         * Set the value of LocationId
         *
         * @param v new value
         */
        public void setLocationId(int v) throws TorqueException
        {
          


         if (this.location_id != v)
        {
             this.location_id = v;
            setModified(true);
        }

                                          
                if (aLocation != null && !(aLocation.getLocationId()==v))
                {
            aLocation = null;
        }
          
                       }


        /**
         * Get the OrderedQty
         *
         * @return int
         */
        public int getOrderedQty()
        {
            return ordered_qty;
        }

                                            
        /**
         * Set the value of OrderedQty
         *
         * @param v new value
         */
        public void setOrderedQty(int v) 
        {
          


         if (this.ordered_qty != v)
        {
             this.ordered_qty = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the ShippedQty
         *
         * @return int
         */
        public int getShippedQty()
        {
            return shipped_qty;
        }

                                            
        /**
         * Set the value of ShippedQty
         *
         * @param v new value
         */
        public void setShippedQty(int v) 
        {
          


         if (this.shipped_qty != v)
        {
             this.shipped_qty = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the UnsoldQty
         *
         * @return int
         */
        public int getUnsoldQty()
        {
            return unsold_qty;
        }

                                            
        /**
         * Set the value of UnsoldQty
         *
         * @param v new value
         */
        public void setUnsoldQty(int v) 
        {
          


         if (this.unsold_qty != v)
        {
             this.unsold_qty = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the LostQty
         *
         * @return int
         */
        public int getLostQty()
        {
            return lost_qty;
        }

                                            
        /**
         * Set the value of LostQty
         *
         * @param v new value
         */
        public void setLostQty(int v) 
        {
          


         if (this.lost_qty != v)
        {
             this.lost_qty = v;
            setModified(true);
        }

                  
                       }


 
     
   
             
   
       private Dorder aDorder;

    /**
     * Declares an association between this object and a Dorder object
     *
     * @param v Dorder
     * @throws TorqueException
     */
    public void setDorder(Dorder v) throws TorqueException
    {
           if (v == null)
        {
                        setDorderId(1000);
                    }
        else
        {
            setDorderId(v.getDorderId());
        }
           aDorder = v;
    }

                 
    /**
     * Get the associated Dorder object
     *
     * @return the associated Dorder object
     * @throws TorqueException
     */
    public Dorder getDorder() throws TorqueException
    {
        if (aDorder == null && (this.dorder_id > 0))
        {
              aDorder = DorderPeer.retrieveByPK(SimpleKey.keyFor(this.dorder_id));
      
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Dorder obj = DorderPeer.retrieveByPK(this.dorder_id);
               obj.addDorderItems(this);
             */
        }
        return aDorder;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey.  e.g.
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
     */
    public void setDorderKey(ObjectKey key) throws TorqueException
    {
    
                                        setDorderId(((NumberKey) key).intValue());
                    }
 
   
             
   
       private Location aLocation;

    /**
     * Declares an association between this object and a Location object
     *
     * @param v Location
     * @throws TorqueException
     */
    public void setLocation(Location v) throws TorqueException
    {
           if (v == null)
        {
                        setLocationId(1000);
                    }
        else
        {
            setLocationId(v.getLocationId());
        }
           aLocation = v;
    }

                 
    /**
     * Get the associated Location object
     *
     * @return the associated Location object
     * @throws TorqueException
     */
    public Location getLocation() throws TorqueException
    {
        if (aLocation == null && (this.location_id > 0))
        {
              aLocation = LocationPeer.retrieveByPK(SimpleKey.keyFor(this.location_id));
      
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Location obj = LocationPeer.retrieveByPK(this.location_id);
               obj.addDorderItems(this);
             */
        }
        return aLocation;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey.  e.g.
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
     */
    public void setLocationKey(ObjectKey key) throws TorqueException
    {
    
                                        setLocationId(((NumberKey) key).intValue());
                    }
    
        
    
     


    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws Exception
     */
    public void save() throws Exception
    {
             save(DorderItemPeer.getMapBuilder()
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
                DorderItemPeer.doInsert((DorderItem) this, con);
                setNew(false);
            }
            else
            {
                DorderItemPeer.doUpdate((DorderItem) this, con);
            }
        }

              alreadyInSave = false;
      }
      }


                
    
    

        /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  dorder_item_id ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
                    setDorderItemId(((NumberKey) key).intValue());
            }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
                    setDorderItemId(Integer.parseInt(key));
            }


    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
        return SimpleKey.keyFor(getDorderItemId());
    }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     * It then fills all the association collections and sets the
     * related objects to isNew=true.
     */
    public DorderItem copy() throws TorqueException
    {
        return copyInto(new DorderItem());
    }

    protected DorderItem copyInto(DorderItem copyObj) throws TorqueException
    {
        copyObj.setDorderItemId(dorder_item_id);
        copyObj.setDorderId(dorder_id);
        copyObj.setLocationId(location_id);
        copyObj.setOrderedQty(ordered_qty);
        copyObj.setShippedQty(shipped_qty);
        copyObj.setUnsoldQty(unsold_qty);
        copyObj.setLostQty(lost_qty);

  copyObj.setNew(false);
      copyObj.setNew(true);

                      copyObj.setDorderItemId(0);
                                            return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public DorderItemPeer getPeer()
    {
        return peer;
    }
}
