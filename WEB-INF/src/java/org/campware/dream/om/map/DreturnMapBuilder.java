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
public class DreturnMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.dream.om.map.DreturnMapBuilder";


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

        dbMap.addTable("DRETURN");
        TableMap tMap = dbMap.getTable("DRETURN");

                tMap.setPrimaryKeyMethod(TableMap.NATIVE);
        
                tMap.setPrimaryKeyMethodInfo("DRETURN_SEQ");
        
                                      tMap.addPrimaryKey("DRETURN.DRETURN_ID", new Integer(0));
                                                        tMap.addColumn("DRETURN.DRETURN_CODE", new String());
                                                        tMap.addColumn("DRETURN.STATUS", new Integer(0));
                                                        tMap.addColumn("DRETURN.ISSUED_DATE", new Date());
                                                        tMap.addColumn("DRETURN.RETURN_DATE", new Date());
                                                        tMap.addColumn("DRETURN.CLOSED_DATE", new Date());
                                                        tMap.addForeignKey(
                "DRETURN.DISTRIBUTOR_ID", new Integer(0) , "DISTRIBUTOR" ,
                    "DISTRIBUTOR_ID");
                                                        tMap.addForeignKey(
                "DRETURN.DORDER_ID", new Integer(0) , "DORDER" ,
                    "DORDER_ID");
                                                        tMap.addColumn("DRETURN.NOTES", new String());
                                                        tMap.addColumn("DRETURN.CREATED", new Date());
                                                        tMap.addColumn("DRETURN.MODIFIED", new Date());
                                                        tMap.addColumn("DRETURN.CREATED_BY", new String());
                                                        tMap.addColumn("DRETURN.MODIFIED_BY", new String());
                              }
}
