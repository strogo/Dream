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
public class TurbineRolePermissionMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.dream.om.map.TurbineRolePermissionMapBuilder";


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

        dbMap.addTable("TURBINE_ROLE_PERMISSION");
        TableMap tMap = dbMap.getTable("TURBINE_ROLE_PERMISSION");

                tMap.setPrimaryKeyMethod("none");
        
        
                                      tMap.addForeignPrimaryKey(
                "TURBINE_ROLE_PERMISSION.ROLE_ID", new Integer(0) , "TURBINE_ROLE" ,
                    "ROLE_ID");
                                                        tMap.addForeignPrimaryKey(
                "TURBINE_ROLE_PERMISSION.PERMISSION_ID", new Integer(0) , "TURBINE_PERMISSION" ,
                    "PERMISSION_ID");
                              }
}
