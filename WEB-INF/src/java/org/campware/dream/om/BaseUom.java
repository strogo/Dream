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
 * extended all references should be to Uom
 */
public abstract class BaseUom extends BaseObject
{
    /** The Peer class */
    private static final UomPeer peer =
        new UomPeer();

                  
        /**
         * The value for the uom_id field
         */
        private int uom_id;
              
        /**
         * The value for the uom_name field
         */
        private String uom_name;
              
        /**
         * The value for the uom_code field
         */
        private String uom_code;
      
      
        /**
         * Get the UomId
         *
         * @return int
         */
        public int getUomId()
        {
            return uom_id;
        }

                                                                              
        /**
         * Set the value of UomId
         *
         * @param v new value
         */
        public void setUomId(int v) throws TorqueException
        {
          


         if (this.uom_id != v)
        {
             this.uom_id = v;
            setModified(true);
        }

                  
                                                  
              // update associated Product
              if (collProducts != null)
              {
                  for (int i = 0; i < collProducts.size(); i++)
                  {
                      ((Product) collProducts.get(i))
                          .setUomId(v);
                  }
              }
                                   }


        /**
         * Get the UomName
         *
         * @return String
         */
        public String getUomName()
        {
            return uom_name;
        }

                                            
        /**
         * Set the value of UomName
         *
         * @param v new value
         */
        public void setUomName(String v) 
        {
          


         if (!ObjectUtils.equals(this.uom_name, v))
        {
             this.uom_name = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the UomCode
         *
         * @return String
         */
        public String getUomCode()
        {
            return uom_code;
        }

                                            
        /**
         * Set the value of UomCode
         *
         * @param v new value
         */
        public void setUomCode(String v) 
        {
          


         if (!ObjectUtils.equals(this.uom_code, v))
        {
             this.uom_code = v;
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
        l.setUom((Uom) this);
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
     * Otherwise if this Uom has previously
     * been saved, it will retrieve related Products from storage.
     * If this Uom is new, it will return
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
                   criteria.add(ProductPeer.UOM_ID, getUomId() );
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
                   criteria.add(ProductPeer.UOM_ID, getUomId());
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
     * Otherwise if this Uom has previously
     * been saved, it will retrieve related Products from storage.
     * If this Uom is new, it will return
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
                     criteria.add(ProductPeer.UOM_ID, getUomId());
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
                     criteria.add(ProductPeer.UOM_ID, getUomId());
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
             save(UomPeer.getMapBuilder()
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
                UomPeer.doInsert((Uom) this, con);
                setNew(false);
            }
            else
            {
                UomPeer.doUpdate((Uom) this, con);
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
     * @param  uom_id ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        throws TorqueException
    {
                    setUomId(((NumberKey) key).intValue());
            }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) throws TorqueException
    {
                    setUomId(Integer.parseInt(key));
            }


    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
        return SimpleKey.keyFor(getUomId());
    }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     * It then fills all the association collections and sets the
     * related objects to isNew=true.
     */
    public Uom copy() throws TorqueException
    {
        return copyInto(new Uom());
    }

    protected Uom copyInto(Uom copyObj) throws TorqueException
    {
        copyObj.setUomId(uom_id);
        copyObj.setUomName(uom_name);
        copyObj.setUomCode(uom_code);

  copyObj.setNew(false);
                                    
                
        List v = getProducts();
        for (int i = 0; i < v.size(); i++)
        {
            Product obj = (Product) v.get(i);
            copyObj.addProduct(obj.copy());
            ((Persistent) v.get(i)).setNew(true);
        }
                copyObj.setNew(true);

                      copyObj.setUomId(0);
                            return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public UomPeer getPeer()
    {
        return peer;
    }
}