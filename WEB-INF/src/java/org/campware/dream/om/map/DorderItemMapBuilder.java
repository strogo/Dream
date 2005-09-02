package org.campware.dream.om.map;

import java.util.Date;
import java.math.BigDecimal;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.TableMap;

/**
  *  This class was autogenerated by Torque on:
  *
  * [Thu Jul 29 08:38:49 CEST 2004]
  *
  */
public class DorderItemMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.dream.om.map.DorderItemMapBuilder";


    /**
     * The database map.
     */
    private DatabaseMap dbMap = null;

    /**
     * Tells us if this DatabaseMapBuilder is built so that we
     * don't have to re-build it every time.
     *
     * @return true if this DatabaseMapBuilder is built
     */
    public boolean isBuilt()
    {
        if (dbMap != null)
        {
            return true;
        }
        return false;
    }

    /**
     * Gets the databasemap this map builder built.
     *
     * @return the databasemap
     */
    public DatabaseMap getDatabaseMap()
    {
        return this.dbMap;
    }

    /**
     * The doBuild() method builds the DatabaseMap
     *
     * @throws TorqueException
     */
    public void doBuild() throws TorqueException
    {
        dbMap = Torque.getDatabaseMap("dream");

        dbMap.addTable("DORDER_ITEM");
        TableMap tMap = dbMap.getTable("DORDER_ITEM");

                tMap.setPrimaryKeyMethod(TableMap.NATIVE);
        
                tMap.setPrimaryKeyMethodInfo("DORDER_ITEM_SEQ");
        
                                      tMap.addPrimaryKey("DORDER_ITEM.DORDER_ITEM_ID", new Integer(0));
                                                        tMap.addForeignKey(
                "DORDER_ITEM.DORDER_ID", new Integer(0) , "DORDER" ,
                    "DORDER_ID");
                                                        tMap.addForeignKey(
                "DORDER_ITEM.LOCATION_ID", new Integer(0) , "LOCATION" ,
                    "LOCATION_ID");
                                                        tMap.addColumn("DORDER_ITEM.ORDERED_QTY", new Integer(0));
                                                        tMap.addColumn("DORDER_ITEM.SHIPPED_QTY", new Integer(0));
                                                        tMap.addColumn("DORDER_ITEM.UNSOLD_QTY", new Integer(0));
                                                        tMap.addColumn("DORDER_ITEM.LOST_QTY", new Integer(0));
                              }
}