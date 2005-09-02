package org.campware.dream.om;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.TableMap;
import org.apache.torque.om.DateKey;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.StringKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.QueryDataSet;
import com.workingdogs.village.Record;

// Local classes
import org.campware.dream.om.map.*;


/**
  * This class was autogenerated by Torque on:
  *
  * [Thu Jul 29 08:38:49 CEST 2004]
  *
  */
public abstract class BaseDreamUserPeer
    extends BasePeer
{

    /** the default database name for this class */
    public static final String DATABASE_NAME = "dream";

     /** the table name for this class */
    public static final String TABLE_NAME = "DREAM_USER";

    /**
     * @return the map builder for this peer
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static MapBuilder getMapBuilder()
        throws TorqueException
    {
        return getMapBuilder(DreamUserMapBuilder.CLASS_NAME);
    }

    /** the column name for the USER_ID field */
    public static final String USER_ID;
    /** the column name for the LOGIN_NAME field */
    public static final String LOGIN_NAME;
    /** the column name for the WELCOME field */
    public static final String WELCOME;
    /** the column name for the DEFAULT_LIST field */
    public static final String DEFAULT_LIST;
    /** the column name for the DINBOX_FID field */
    public static final String DINBOX_FID;
    /** the column name for the DOUTBOX_FID field */
    public static final String DOUTBOX_FID;
    /** the column name for the DNEWSLETTER_FID field */
    public static final String DNEWSLETTER_FID;
    /** the column name for the DRETURN_FID field */
    public static final String DRETURN_FID;
    /** the column name for the DSHIPMENT_FID field */
    public static final String DSHIPMENT_FID;
    /** the column name for the DORDER_FID field */
    public static final String DORDER_FID;
    /** the column name for the DCONTRACT_FID field */
    public static final String DCONTRACT_FID;
    /** the column name for the PROJECT_FID field */
    public static final String PROJECT_FID;
    /** the column name for the LOCATION_FID field */
    public static final String LOCATION_FID;
    /** the column name for the DISTRIBUTOR_FID field */
    public static final String DISTRIBUTOR_FID;
    /** the column name for the PRODUCT_FID field */
    public static final String PRODUCT_FID;

    static
    {
    USER_ID = "DREAM_USER.USER_ID";
    LOGIN_NAME = "DREAM_USER.LOGIN_NAME";
    WELCOME = "DREAM_USER.WELCOME";
    DEFAULT_LIST = "DREAM_USER.DEFAULT_LIST";
    DINBOX_FID = "DREAM_USER.DINBOX_FID";
    DOUTBOX_FID = "DREAM_USER.DOUTBOX_FID";
    DNEWSLETTER_FID = "DREAM_USER.DNEWSLETTER_FID";
    DRETURN_FID = "DREAM_USER.DRETURN_FID";
    DSHIPMENT_FID = "DREAM_USER.DSHIPMENT_FID";
    DORDER_FID = "DREAM_USER.DORDER_FID";
    DCONTRACT_FID = "DREAM_USER.DCONTRACT_FID";
    PROJECT_FID = "DREAM_USER.PROJECT_FID";
    LOCATION_FID = "DREAM_USER.LOCATION_FID";
    DISTRIBUTOR_FID = "DREAM_USER.DISTRIBUTOR_FID";
    PRODUCT_FID = "DREAM_USER.PRODUCT_FID";

        if (Torque.isInit())
        {
            try
            {
                getMapBuilder();
            }
            catch (Exception e)
            {
                category.error("Could not initialize Peer", e);
            }
        }
        else
        {
            Torque.registerMapBuilder(DreamUserMapBuilder.CLASS_NAME);
        }
    }

 
    /** number of columns for this peer */
    public static final int numColumns =  15;

    /** A class that can be returned by this peer. */
    protected static final String CLASSNAME_DEFAULT =
        "org.campware.dream.om.DreamUser";

    /** A class that can be returned by this peer. */
    protected static final Class CLASS_DEFAULT = initClass(CLASSNAME_DEFAULT);

    /**
     * Class object initialization method.
     *
     * @param className name of the class to initialize
     * @return the initialized class
     */
    private static Class initClass(String className)
    {
        Class c = null;
        try
        {
            c = Class.forName(className);
        }
        catch (Throwable t)
        {
            category.error("A FATAL ERROR has occurred which should not "
                + "have happened under any circumstance.  Please notify "
                + "the Turbine developers <turbine-dev@jakarta.apache.org> "
                + "and give as many details as possible (including the error "
                + "stack trace).", t);

            // Error objects should always be propogated.
            if (t instanceof Error)
            {
                throw (Error) t.fillInStackTrace();
            }
        }
        return c;
    }


    /**
     * Get the list of objects for a ResultSet.  Please not that your
     * resultset MUST return columns in the right order.  You can use
     * getFieldNames() in BaseObject to get the correct sequence.
     *
     * @param results the ResultSet
     * @return the list of objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List resultSet2Objects(java.sql.ResultSet results)
            throws TorqueException
    {
        try
        {
            QueryDataSet qds = null;
            List rows = null;
            try
            {
                qds = new QueryDataSet(results);
                rows = getSelectResults(qds);
            }
            finally
            {
                if (qds != null)
                {
                    qds.close();
                }
            }

            return populateObjects(rows);
        }
        catch (SQLException e)
        {
            throw new TorqueException(e);
        }
        catch (DataSetException e)
        {
            throw new TorqueException(e);
        }
    }



    /**
     * Method to do inserts.
     *
     * @param criteria object used to create the INSERT statement.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static ObjectKey doInsert(Criteria criteria)
        throws TorqueException
    {
        return BaseDreamUserPeer
            .doInsert(criteria, (Connection) null);
    }

    /**
     * Method to do inserts.  This method is to be used during a transaction,
     * otherwise use the doInsert(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object used to create the INSERT statement.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static ObjectKey doInsert(Criteria criteria, Connection con)
        throws TorqueException
    {
                                                                                                                                                                                                                       
        // Set the correct dbName if it has not been overridden
        // criteria.getDbName will return the same object if not set to
        // another value so == check is okay and faster
        if (criteria.getDbName() == Torque.getDefaultDB())
        {
            criteria.setDbName(DATABASE_NAME);
        }
        if (con == null)
        {
            return BasePeer.doInsert(criteria);
        }
        else
        {
            return BasePeer.doInsert(criteria, con);
        }
    }

    /**
     * Add all the columns needed to create a new object.
     *
     * @param criteria object containing the columns to add.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void addSelectColumns(Criteria criteria)
            throws TorqueException
    {
            criteria.addSelectColumn(USER_ID);
            criteria.addSelectColumn(LOGIN_NAME);
            criteria.addSelectColumn(WELCOME);
            criteria.addSelectColumn(DEFAULT_LIST);
            criteria.addSelectColumn(DINBOX_FID);
            criteria.addSelectColumn(DOUTBOX_FID);
            criteria.addSelectColumn(DNEWSLETTER_FID);
            criteria.addSelectColumn(DRETURN_FID);
            criteria.addSelectColumn(DSHIPMENT_FID);
            criteria.addSelectColumn(DORDER_FID);
            criteria.addSelectColumn(DCONTRACT_FID);
            criteria.addSelectColumn(PROJECT_FID);
            criteria.addSelectColumn(LOCATION_FID);
            criteria.addSelectColumn(DISTRIBUTOR_FID);
            criteria.addSelectColumn(PRODUCT_FID);
        }


    /**
     * Create a new object of type cls from a resultset row starting
     * from a specified offset.  This is done so that you can select
     * other rows than just those needed for this object.  You may
     * for example want to create two objects from the same row.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static DreamUser row2Object(Record row,
                                             int offset,
                                             Class cls)
        throws TorqueException
    {
        try
        {
            DreamUser obj = (DreamUser) cls.newInstance();
            populateObject(row, offset, obj);
                            obj.setModified(false);
                        obj.setNew(false);

            return obj;
        }
        catch (InstantiationException e)
        {
            throw new TorqueException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new TorqueException(e);
        }
    }

    /**
     * Populates an object from a resultset row starting
     * from a specified offset.  This is done so that you can select
     * other rows than just those needed for this object.  You may
     * for example want to create two objects from the same row.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void populateObject(Record row,
                                      int offset,
                                      DreamUser obj)
        throws TorqueException
    {
        try
        {
                                        obj.setUserId(row.getValue(offset + 0).asInt());
                                            obj.setLoginName(row.getValue(offset + 1).asString());
                                            obj.setWelcome(row.getValue(offset + 2).asString());
                                            obj.setDefaultList(row.getValue(offset + 3).asInt());
                                            obj.setDinboxFid(row.getValue(offset + 4).asInt());
                                            obj.setDoutboxFid(row.getValue(offset + 5).asInt());
                                            obj.setDnewsletterFid(row.getValue(offset + 6).asInt());
                                            obj.setDreturnFid(row.getValue(offset + 7).asInt());
                                            obj.setDshipmentFid(row.getValue(offset + 8).asInt());
                                            obj.setDorderFid(row.getValue(offset + 9).asInt());
                                            obj.setDcontractFid(row.getValue(offset + 10).asInt());
                                            obj.setProjectFid(row.getValue(offset + 11).asInt());
                                            obj.setLocationFid(row.getValue(offset + 12).asInt());
                                            obj.setDistributorFid(row.getValue(offset + 13).asInt());
                                            obj.setProductFid(row.getValue(offset + 14).asInt());
                            }
        catch (DataSetException e)
        {
            throw new TorqueException(e);
        }
    }

    /**
     * Method to do selects.
     *
     * @param criteria object used to create the SELECT statement.
     * @return List of selected Objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelect(Criteria criteria) throws TorqueException
    {
        return populateObjects(doSelectVillageRecords(criteria));
    }

    /**
     * Method to do selects within a transaction.
     *
     * @param criteria object used to create the SELECT statement.
     * @param con the connection to use
     * @return List of selected Objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelect(Criteria criteria, Connection con)
        throws TorqueException
    {
        return populateObjects(doSelectVillageRecords(criteria, con));
    }

    /**
     * Grabs the raw Village records to be formed into objects.
     * This method handles connections internally.  The Record objects
     * returned by this method should be considered readonly.  Do not
     * alter the data and call save(), your results may vary, but are
     * certainly likely to result in hard to track MT bugs.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelectVillageRecords(Criteria criteria)
        throws TorqueException
    {
        return BaseDreamUserPeer
            .doSelectVillageRecords(criteria, (Connection) null);
    }

    /**
     * Grabs the raw Village records to be formed into objects.
     * This method should be used for transactions
     *
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelectVillageRecords(Criteria criteria, Connection con)
        throws TorqueException
    {
    
        if (criteria.getSelectColumns().size() == 0)
        {
            addSelectColumns(criteria);
        }

                                                                                                                                                                                                                       
        // Set the correct dbName if it has not been overridden
        // criteria.getDbName will return the same object if not set to
        // another value so == check is okay and faster
        if (criteria.getDbName() == Torque.getDefaultDB())
        {
            criteria.setDbName(DATABASE_NAME);
        }
        // BasePeer returns a List of Value (Village) arrays.  The array
        // order follows the order columns were placed in the Select clause.
        if (con == null)
        {
            return BasePeer.doSelect(criteria);
        }
        else
        {
            return BasePeer.doSelect(criteria, con);
        }
    }

    /**
     * The returned List will contain objects of the default type or
     * objects that inherit from the default.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List populateObjects(List records)
        throws TorqueException
    {
        List results = new ArrayList(records.size());

        // populate the object(s)
        for (int i = 0; i < records.size(); i++)
        {
            Record row = (Record) records.get(i);
            results.add(DreamUserPeer.row2Object(row, 1,
                DreamUserPeer.getOMClass()));
        }
        return results;
    }
 

    /**
     * The class that the Peer will make instances of.
     * If the BO is abstract then you must implement this method
     * in the BO.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static Class getOMClass()
        throws TorqueException
    {
            return CLASS_DEFAULT;
        }


    /**
     * Method to do updates.
     *
     * @param criteria object containing data that is used to create the UPDATE
     *        statement.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(Criteria criteria) throws TorqueException
    {
         BaseDreamUserPeer
            .doUpdate(criteria, (Connection) null);
    }

    /**
     * Method to do updates.  This method is to be used during a transaction,
     * otherwise use the doUpdate(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object containing data that is used to create the UPDATE
     *        statement.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(Criteria criteria, Connection con)
        throws TorqueException
    {
        Criteria selectCriteria = new Criteria(DATABASE_NAME, 2);
                                selectCriteria.put(USER_ID, criteria.remove(USER_ID));
                                                                                                                                                                                                                                                                                                                                                
        // Set the correct dbName if it has not been overridden
        // criteria.getDbName will return the same object if not set to
        // another value so == check is okay and faster
        if (criteria.getDbName() == Torque.getDefaultDB())
        {
            criteria.setDbName(DATABASE_NAME);
        }
        if (con == null)
        {
            BasePeer.doUpdate(selectCriteria, criteria);
        }
        else
        {
            BasePeer.doUpdate(selectCriteria, criteria, con);
        }
    }

    /**
     * Method to do deletes.
     *
     * @param criteria object containing data that is used DELETE from database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
     public static void doDelete(Criteria criteria) throws TorqueException
     {
         BaseDreamUserPeer
            .doDelete(criteria, (Connection) null);
     }

    /**
     * Method to do deletes.  This method is to be used during a transaction,
     * otherwise use the doDelete(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object containing data that is used DELETE from database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
     public static void doDelete(Criteria criteria, Connection con)
        throws TorqueException
     {
                                                                                                                                                                                                                       
        // Set the correct dbName if it has not been overridden
        // criteria.getDbName will return the same object if not set to
        // another value so == check is okay and faster
        if (criteria.getDbName() == Torque.getDefaultDB())
        {
            criteria.setDbName(DATABASE_NAME);
        }
        if (con == null)
        {
            BasePeer.doDelete(criteria);
        }
        else
        {
            BasePeer.doDelete(criteria, con);
        }
     }

    /**
     * Method to do selects
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelect(DreamUser obj) throws TorqueException
    {
        return doSelect(buildCriteria(obj));
    }

    /**
     * Method to do inserts
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(DreamUser obj) throws TorqueException
    {
                doInsert(buildCriteria(obj));
                obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * @param obj the data object to update in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(DreamUser obj) throws TorqueException
    {
        doUpdate(buildCriteria(obj));
        obj.setModified(false);
    }

    /**
     * @param obj the data object to delete in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(DreamUser obj) throws TorqueException
    {
        doDelete(buildCriteria(obj));
    }

    /**
     * Method to do inserts.  This method is to be used during a transaction,
     * otherwise use the doInsert(DreamUser) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to insert into the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(DreamUser obj, Connection con)
        throws TorqueException
    {
                doInsert(buildCriteria(obj), con);
                obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * Method to do update.  This method is to be used during a transaction,
     * otherwise use the doUpdate(DreamUser) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to update in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(DreamUser obj, Connection con)
        throws TorqueException
    {
        doUpdate(buildCriteria(obj), con);
        obj.setModified(false);
    }

    /**
     * Method to delete.  This method is to be used during a transaction,
     * otherwise use the doDelete(DreamUser) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to delete in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(DreamUser obj, Connection con)
        throws TorqueException
    {
        doDelete(buildCriteria(obj), con);
    }

    /**
     * Method to do deletes.
     *
     * @param pk ObjectKey that is used DELETE from database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(ObjectKey pk) throws TorqueException
    {
        BaseDreamUserPeer
           .doDelete(pk, (Connection) null);
    }

    /**
     * Method to delete.  This method is to be used during a transaction,
     * otherwise use the doDelete(ObjectKey) method.  It will take
     * care of the connection details internally.
     *
     * @param pk the primary key for the object to delete in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(ObjectKey pk, Connection con)
        throws TorqueException
    {
        doDelete(buildCriteria(pk), con);
    }

    /** Build a Criteria object from an ObjectKey */
    public static Criteria buildCriteria( ObjectKey pk )
    {
        Criteria criteria = new Criteria();
              criteria.add(USER_ID, pk);
          return criteria;
     }

    /** Build a Criteria object from the data object for this peer */
    public static Criteria buildCriteria( DreamUser obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
                                criteria.add(USER_ID, obj.getUserId());
                                criteria.add(LOGIN_NAME, obj.getLoginName());
                                criteria.add(WELCOME, obj.getWelcome());
                                criteria.add(DEFAULT_LIST, obj.getDefaultList());
                                criteria.add(DINBOX_FID, obj.getDinboxFid());
                                criteria.add(DOUTBOX_FID, obj.getDoutboxFid());
                                criteria.add(DNEWSLETTER_FID, obj.getDnewsletterFid());
                                criteria.add(DRETURN_FID, obj.getDreturnFid());
                                criteria.add(DSHIPMENT_FID, obj.getDshipmentFid());
                                criteria.add(DORDER_FID, obj.getDorderFid());
                                criteria.add(DCONTRACT_FID, obj.getDcontractFid());
                                criteria.add(PROJECT_FID, obj.getProjectFid());
                                criteria.add(LOCATION_FID, obj.getLocationFid());
                                criteria.add(DISTRIBUTOR_FID, obj.getDistributorFid());
                                criteria.add(PRODUCT_FID, obj.getProductFid());
                return criteria;
    }

 

    
    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static DreamUser retrieveByPK(int pk)
        throws TorqueException
    {
        return retrieveByPK(SimpleKey.keyFor(pk));
    }

    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static DreamUser retrieveByPK(ObjectKey pk)
        throws TorqueException
    {
        Connection db = null;
        DreamUser retVal = null;
        try
        {
            db = Torque.getConnection(DATABASE_NAME);
            retVal = retrieveByPK(pk, db);
        }
        finally
        {
            Torque.closeConnection(db);
        }
        return(retVal);
    }

    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static DreamUser retrieveByPK(ObjectKey pk, Connection con)
        throws TorqueException
    {
        Criteria criteria = buildCriteria(pk);
        List v = doSelect(criteria, con);
        if (v.size() != 1)
        {
            throw new TorqueException("Failed to select one and only one row.");
        }
        else
        {
            return (DreamUser)v.get(0);
        }
    }

    /**
     * Retrieve a multiple objects by pk
     *
     * @param pks List of primary keys
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List retrieveByPKs(List pks)
        throws TorqueException
    {
        Connection db = null;
        List retVal = null;
        try
        {
           db = Torque.getConnection(DATABASE_NAME);
           retVal = retrieveByPKs(pks, db);
        }
        finally
        {
            Torque.closeConnection(db);
        }
        return(retVal);
    }

    /**
     * Retrieve a multiple objects by pk
     *
     * @param pks List of primary keys
     * @param dbcon the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List retrieveByPKs( List pks, Connection dbcon )
        throws TorqueException
    {
        List objs = null;
        if (pks == null || pks.size() == 0)
        {
            objs = new LinkedList();
        }
        else
        {
            Criteria criteria = new Criteria();
              criteria.addIn( USER_ID, pks );
          objs = doSelect(criteria, dbcon);
        }
        return objs;
    }

 



    
 

  

    /**
     * Returns the TableMap related to this peer.  This method is not
     * needed for general use but a specific application could have a need.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static TableMap getTableMap()
        throws TorqueException
    {
        return Torque.getDatabaseMap(DATABASE_NAME).getTable(TABLE_NAME);
    }
 }