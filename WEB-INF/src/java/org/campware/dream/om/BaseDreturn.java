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
 * extended all references should be to Dreturn
 */
public abstract class BaseDreturn extends BaseObject
{
    /** The Peer class */
    private static final DreturnPeer peer =
        new DreturnPeer();

                  
        /**
         * The value for the dreturn_id field
         */
        private int dreturn_id;
                                                                                      
        /**
         * The value for the dreturn_code field
         */
        private String dreturn_code = "AUTO";
                                                                            
        /**
         * The value for the status field
         */
        private int status = 30;
              
        /**
         * The value for the issued_date field
         */
        private Date issued_date;
              
        /**
         * The value for the return_date field
         */
        private Date return_date;
              
        /**
         * The value for the closed_date field
         */
        private Date closed_date;
                                                                            
        /**
         * The value for the distributor_id field
         */
        private int distributor_id = 1000;
                                                                            
        /**
         * The value for the dorder_id field
         */
        private int dorder_id = 1000;
              
        /**
         * The value for the notes field
         */
        private String notes;
              
        /**
         * The value for the created field
         */
        private Date created;
              
        /**
         * The value for the modified field
         */
        private Date modified;
              
        /**
         * The value for the created_by field
         */
        private String created_by;
              
        /**
         * The value for the modified_by field
         */
        private String modified_by;
      
      
        /**
         * Get the DreturnId
         *
         * @return int
         */
        public int getDreturnId()
        {
            return dreturn_id;
        }

                                            
        /**
         * Set the value of DreturnId
         *
         * @param v new value
         */
        public void setDreturnId(int v) 
        {
          


         if (this.dreturn_id != v)
        {
             this.dreturn_id = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the DreturnCode
         *
         * @return String
         */
        public String getDreturnCode()
        {
            return dreturn_code;
        }

                                            
        /**
         * Set the value of DreturnCode
         *
         * @param v new value
         */
        public void setDreturnCode(String v) 
        {
          


         if (!ObjectUtils.equals(this.dreturn_code, v))
        {
             this.dreturn_code = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the Status
         *
         * @return int
         */
        public int getStatus()
        {
            return status;
        }

                                            
        /**
         * Set the value of Status
         *
         * @param v new value
         */
        public void setStatus(int v) 
        {
          


         if (this.status != v)
        {
             this.status = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the IssuedDate
         *
         * @return Date
         */
        public Date getIssuedDate()
        {
            return issued_date;
        }

                                            
        /**
         * Set the value of IssuedDate
         *
         * @param v new value
         */
        public void setIssuedDate(Date v) 
        {
          


         if (!ObjectUtils.equals(this.issued_date, v))
        {
             this.issued_date = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the ReturnDate
         *
         * @return Date
         */
        public Date getReturnDate()
        {
            return return_date;
        }

                                            
        /**
         * Set the value of ReturnDate
         *
         * @param v new value
         */
        public void setReturnDate(Date v) 
        {
          


         if (!ObjectUtils.equals(this.return_date, v))
        {
             this.return_date = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the ClosedDate
         *
         * @return Date
         */
        public Date getClosedDate()
        {
            return closed_date;
        }

                                            
        /**
         * Set the value of ClosedDate
         *
         * @param v new value
         */
        public void setClosedDate(Date v) 
        {
          


         if (!ObjectUtils.equals(this.closed_date, v))
        {
             this.closed_date = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the DistributorId
         *
         * @return int
         */
        public int getDistributorId()
        {
            return distributor_id;
        }

                                                      
        /**
         * Set the value of DistributorId
         *
         * @param v new value
         */
        public void setDistributorId(int v) throws TorqueException
        {
          


         if (this.distributor_id != v)
        {
             this.distributor_id = v;
            setModified(true);
        }

                                          
                if (aDistributor != null && !(aDistributor.getDistributorId()==v))
                {
            aDistributor = null;
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
         * Get the Notes
         *
         * @return String
         */
        public String getNotes()
        {
            return notes;
        }

                                            
        /**
         * Set the value of Notes
         *
         * @param v new value
         */
        public void setNotes(String v) 
        {
          


         if (!ObjectUtils.equals(this.notes, v))
        {
             this.notes = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the Created
         *
         * @return Date
         */
        public Date getCreated()
        {
            return created;
        }

                                            
        /**
         * Set the value of Created
         *
         * @param v new value
         */
        public void setCreated(Date v) 
        {
          


         if (!ObjectUtils.equals(this.created, v))
        {
             this.created = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the Modified
         *
         * @return Date
         */
        public Date getModified()
        {
            return modified;
        }

                                            
        /**
         * Set the value of Modified
         *
         * @param v new value
         */
        public void setModified(Date v) 
        {
          


         if (!ObjectUtils.equals(this.modified, v))
        {
             this.modified = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the CreatedBy
         *
         * @return String
         */
        public String getCreatedBy()
        {
            return created_by;
        }

                                            
        /**
         * Set the value of CreatedBy
         *
         * @param v new value
         */
        public void setCreatedBy(String v) 
        {
          


         if (!ObjectUtils.equals(this.created_by, v))
        {
             this.created_by = v;
            setModified(true);
        }

                  
                       }


        /**
         * Get the ModifiedBy
         *
         * @return String
         */
        public String getModifiedBy()
        {
            return modified_by;
        }

                                            
        /**
         * Set the value of ModifiedBy
         *
         * @param v new value
         */
        public void setModifiedBy(String v) 
        {
          


         if (!ObjectUtils.equals(this.modified_by, v))
        {
             this.modified_by = v;
            setModified(true);
        }

                  
                       }


 
     
   
             
   
       private Distributor aDistributor;

    /**
     * Declares an association between this object and a Distributor object
     *
     * @param v Distributor
     * @throws TorqueException
     */
    public void setDistributor(Distributor v) throws TorqueException
    {
           if (v == null)
        {
                        setDistributorId(1000);
                    }
        else
        {
            setDistributorId(v.getDistributorId());
        }
           aDistributor = v;
    }

                 
    /**
     * Get the associated Distributor object
     *
     * @return the associated Distributor object
     * @throws TorqueException
     */
    public Distributor getDistributor() throws TorqueException
    {
        if (aDistributor == null && (this.distributor_id > 0))
        {
              aDistributor = DistributorPeer.retrieveByPK(SimpleKey.keyFor(this.distributor_id));
      
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Distributor obj = DistributorPeer.retrieveByPK(this.distributor_id);
               obj.addDreturns(this);
             */
        }
        return aDistributor;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey.  e.g.
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
     */
    public void setDistributorKey(ObjectKey key) throws TorqueException
    {
    
                                        setDistributorId(((NumberKey) key).intValue());
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
               obj.addDreturns(this);
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
    
        
    
     


    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws Exception
     */
    public void save() throws Exception
    {
             save(DreturnPeer.getMapBuilder()
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
                DreturnPeer.doInsert((Dreturn) this, con);
                setNew(false);
            }
            else
            {
                DreturnPeer.doUpdate((Dreturn) this, con);
            }
        }

              alreadyInSave = false;
      }
      }


                
    
    

        /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  dreturn_id ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
                    setDreturnId(((NumberKey) key).intValue());
            }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
                    setDreturnId(Integer.parseInt(key));
            }


    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
        return SimpleKey.keyFor(getDreturnId());
    }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     * It then fills all the association collections and sets the
     * related objects to isNew=true.
     */
    public Dreturn copy() throws TorqueException
    {
        return copyInto(new Dreturn());
    }

    protected Dreturn copyInto(Dreturn copyObj) throws TorqueException
    {
        copyObj.setDreturnId(dreturn_id);
        copyObj.setDreturnCode(dreturn_code);
        copyObj.setStatus(status);
        copyObj.setIssuedDate(issued_date);
        copyObj.setReturnDate(return_date);
        copyObj.setClosedDate(closed_date);
        copyObj.setDistributorId(distributor_id);
        copyObj.setDorderId(dorder_id);
        copyObj.setNotes(notes);
        copyObj.setCreated(created);
        copyObj.setModified(modified);
        copyObj.setCreatedBy(created_by);
        copyObj.setModifiedBy(modified_by);

  copyObj.setNew(false);
      copyObj.setNew(true);

                      copyObj.setDreturnId(0);
                                                                    return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public DreturnPeer getPeer()
    {
        return peer;
    }
}
