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
public class DreamUserMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.dream.om.map.DreamUserMapBuilder";


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

        dbMap.addTable("DREAM_USER");
        TableMap tMap = dbMap.getTable("DREAM_USER");

                tMap.setPrimaryKeyMethod("none");
        
        
                                      tMap.addPrimaryKey("DREAM_USER.USER_ID", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.LOGIN_NAME", new String());
                                                        tMap.addColumn("DREAM_USER.WELCOME", new String());
                                                        tMap.addColumn("DREAM_USER.DEFAULT_LIST", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.DINBOX_FID", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.DOUTBOX_FID", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.DNEWSLETTER_FID", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.DRETURN_FID", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.DSHIPMENT_FID", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.DORDER_FID", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.DCONTRACT_FID", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.PROJECT_FID", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.LOCATION_FID", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.DISTRIBUTOR_FID", new Integer(0));
                                                        tMap.addColumn("DREAM_USER.PRODUCT_FID", new Integer(0));
                              }
}
