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
public class SalesDistrictMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.dream.om.map.SalesDistrictMapBuilder";


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

        dbMap.addTable("SALES_DISTRICT");
        TableMap tMap = dbMap.getTable("SALES_DISTRICT");

                tMap.setPrimaryKeyMethod(TableMap.NATIVE);
        
                tMap.setPrimaryKeyMethodInfo("SALES_DISTRICT_SEQ");
        
                                      tMap.addPrimaryKey("SALES_DISTRICT.SALES_DISTRICT_ID", new Integer(0));
                                                        tMap.addForeignKey(
                "SALES_DISTRICT.SALES_AREA_ID", new Integer(0) , "SALES_AREA" ,
                    "SALES_AREA_ID");
                                                        tMap.addColumn("SALES_DISTRICT.SALES_DISTRICT_NAME", new String());
                              }
}
