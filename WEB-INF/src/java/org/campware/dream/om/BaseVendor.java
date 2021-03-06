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
 * extended all references should be to Vendor
 */
public abstract class BaseVendor extends BaseObject
{
    /** The Peer class */
    private static final VendorPeer peer =
        new VendorPeer();

                  
        /**
         * The value for the vendor_id field
         */
        private int vendor_id;
              
        /**
         * The value for the vendor_name field
         */
        private String vendor_name;
      
      
        /**
         * Get the VendorId
         *
         * @return int
         */
        public int getVendorId()
        {
            return vendor_id;
        }

                                                                              
        /**
         * Set the value of VendorId
         *
         * @param v new value
         */
        public void setVendorId(int v) throws TorqueException
        {
          


         if (this.vendor_id != v)
        {
             this.vendor_id = v;
            setModified(true);
        }

                  
                                                  
              // update associated Product
              if (collProducts != null)
              {
                  for (int i = 0; i < collProducts.size(); i++)
                  {
                      ((Product) collProducts.get(i))
                          .setVendorId(v);
                  }
              }
                                   }


        /**
         * Get the VendorName
         *
         * @return String
         */
        public String getVendorName()
        {
            return vendor_name;
        }

                                            
        /**
         * Set the value of VendorName
         *
         * @param v new value
         */
        public void setVendorName(String v) 
        {
          


         if (!ObjectUtils.equals(this.vendor_name, v))
        {
             this.vendor_name = v;
            setModified(true);
        }

                  
                       }


 
        
                
      
    /**
     * Collection to store aggregation of collProducts
     */
    protected List collProducts;

    /**
     * Temporary storage of collProducts to save a possible db hit in
     * the event objects are add to the collection, but the
     * complete collection is never requested.
     */
    protected void initProducts()
    {
        if (collProducts == null)
        {
            collProducts = new ArrayList();
        }
    }

    /**
     * Method called to associate a Product object to this object
     * through the Product foreign key attribute
     *
     * @param l Product
     * @throws TorqueException
     */
    public void addProduct(Product l) throws TorqueException
    {
        getProducts().add(l);
        l.setVendor((Vendor) this);
    }

    /**
     * The criteria used to select the current contents of collProducts
     */
    private Criteria lastProductsCriteria = null;

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getProducts(new Criteria())
     *
     * @throws TorqueException
     */
    public List getProducts() throws TorqueException
    {
        if (collProducts == null)
        {
            collProducts = getProducts(new Criteria(10));
        }
        return collProducts;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Vendor has previously
     * been saved, it will retrieve related Products from storage.
     * If this Vendor is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     *
     * @throws TorqueException
     */
    public List getProducts(Criteria criteria) throws TorqueException
    {
        if (collProducts == null)
        {
            if (isNew())
            {
               collProducts = new ArrayList();
            }
            else
            {
                   criteria.add(ProductPeer.VENDOR_ID, getVendorId() );
                   collProducts = ProductPeer.doSelect(criteria);
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
                   criteria.add(ProductPeer.VENDOR_ID, getVendorId());
                   if (!lastProductsCriteria.equals(criteria))
                {
                    collProducts = ProductPeer.doSelect(criteria);
                }
            }
        }
        lastProductsCriteria = criteria;

        return collProducts;
    }

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getProducts(new Criteria(),Connection)
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getProducts(Connection con) throws TorqueException
    {
        if (collProducts == null)
        {
            collProducts = getProducts(new Criteria(10), con);
        }
        return collProducts;
    }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Vendor has previously
     * been saved, it will retrieve related Products from storage.
     * If this Vendor is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getProducts(Criteria criteria, Connection con)
            throws TorqueException
    {
        if (collProducts == null)
        {
            if (isNew())
            {
               collProducts = new ArrayList();
            }
            else
            {
                     criteria.add(ProductPeer.VENDOR_ID, getVendorId());
                     collProducts = ProductPeer.doSelect(criteria, con);
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
                     criteria.add(ProductPeer.VENDOR_ID, getVendorId());
                     if (!lastProductsCriteria.equals(criteria))
                 {
                     collProducts = ProductPeer.doSelect(criteria, con);
                 }
             }
         }
         lastProductsCriteria = criteria;

         return collProducts;
     }

     

     
      
      
          
                    
                
        
        
    
      
      
          
                    
                
        
        
    
      
         
          
                    
                
        
        
      



     
    
     


    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws Exception
     */
    public void save() throws Exception
    {
             save(VendorPeer.getMapBuilder()
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
                VendorPeer.doInsert((Vendor) this, con);
                setNew(false);
            }
            else
            {
                VendorPeer.doUpdate((Vendor) this, con);
            }
        }

                                    
                
          if (collProducts != null)
          {
              for (int i = 0; i < collProducts.size(); i++)
              {
                  ((Product) collProducts.get(i)).save(con);
              }
          }
                  alreadyInSave = false;
      }
      }


                        
    
    

        /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  vendor_id ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        throws TorqueException
    {
                    setVendorId(((NumberKey) key).intValue());
            }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) throws TorqueException
    {
                    setVendorId(Integer.parseInt(key));
            }


    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
        return SimpleKey.keyFor(getVendorId());
    }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     * It then fills all the association collections and sets the
     * related objects to isNew=true.
     */
    public Vendor copy() throws TorqueException
    {
        return copyInto(new Vendor());
    }

    protected Vendor copyInto(Vendor copyObj) throws TorqueException
    {
        copyObj.setVendorId(vendor_id);
        copyObj.setVendorName(vendor_name);

  copyObj.setNew(false);
                                    
                
        List v = getProducts();
        for (int i = 0; i < v.size(); i++)
        {
            Product obj = (Product) v.get(i);
            copyObj.addProduct(obj.copy());
            ((Persistent) v.get(i)).setNew(true);
        }
                copyObj.setNew(true);

                      copyObj.setVendorId(0);
                        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public VendorPeer getPeer()
    {
        return peer;
    }
}
