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
public class LocationMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.dream.om.map.LocationMapBuilder";


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

        dbMap.addTable("LOCATION");
        TableMap tMap = dbMap.getTable("LOCATION");

                tMap.setPrimaryKeyMethod(TableMap.NATIVE);
        
                tMap.setPrimaryKeyMethodInfo("LOCATION_SEQ");
        
                                      tMap.addPrimaryKey("LOCATION.LOCATION_ID", new Integer(0));
                                                        tMap.addColumn("LOCATION.LOCATION_CODE", new String());
                                                        tMap.addColumn("LOCATION.STATUS", new Integer(0));
                                                        tMap.addColumn("LOCATION.LOCATION_NAME_1", new String());
                                                        tMap.addColumn("LOCATION.LOCATION_NAME_2", new String());
                                                        tMap.addColumn("LOCATION.LOCATION_DISPLAY", new String());
                                                        tMap.addForeignKey(
                "LOCATION.SALES_DISTRICT_ID", new Integer(0) , "SALES_DISTRICT" ,
                    "SALES_DISTRICT_ID");
                                                        tMap.addColumn("LOCATION.LOCATION_TYPE", new Integer(0));
                                                        tMap.addColumn("LOCATION.GENDER", new Integer(0));
                                                        tMap.addForeignKey(
                "LOCATION.LOCATION_CAT_ID", new Integer(0) , "LOCATION_CATEGORY" ,
                    "LOCATION_CAT_ID");
                                                        tMap.addForeignKey(
                "LOCATION.DISTRIBUTOR_ID", new Integer(0) , "DISTRIBUTOR" ,
                    "DISTRIBUTOR_ID");
                                                        tMap.addColumn("LOCATION.ADDRESS_1", new String());
                                                        tMap.addColumn("LOCATION.ADDRESS_2", new String());
                                                        tMap.addColumn("LOCATION.CITY", new String());
                                                        tMap.addColumn("LOCATION.ZIP", new String());
                                                        tMap.addColumn("LOCATION.STATE", new String());
                                                        tMap.addForeignKey(
                "LOCATION.COUNTRY_ID", new Integer(0) , "COUNTRY" ,
                    "COUNTRY_ID");
                                                        tMap.addForeignKey(
                "LOCATION.REGION_ID", new Integer(0) , "REGION" ,
                    "REGION_ID");
                                                        tMap.addColumn("LOCATION.PHONE_1", new String());
                                                        tMap.addColumn("LOCATION.PHONE_2", new String());
                                                        tMap.addColumn("LOCATION.FAX", new String());
                                                        tMap.addColumn("LOCATION.CUSTOM_1", new String());
                                                        tMap.addColumn("LOCATION.CUSTOM_2", new String());
                                                        tMap.addColumn("LOCATION.CUSTOM_3", new String());
                                                        tMap.addColumn("LOCATION.CUSTOM_4", new String());
                                                        tMap.addColumn("LOCATION.CUSTOM_5", new String());
                                                        tMap.addColumn("LOCATION.CUSTOM_6", new String());
                                                        tMap.addColumn("LOCATION.NOTES", new String());
                                                        tMap.addColumn("LOCATION.CREATED", new Date());
                                                        tMap.addColumn("LOCATION.MODIFIED", new Date());
                                                        tMap.addColumn("LOCATION.CREATED_BY", new String());
                                                        tMap.addColumn("LOCATION.MODIFIED_BY", new String());
                              }
}
