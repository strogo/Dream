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
public class DnewsletterMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.dream.om.map.DnewsletterMapBuilder";


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

        dbMap.addTable("DNEWSLETTER");
        TableMap tMap = dbMap.getTable("DNEWSLETTER");

                tMap.setPrimaryKeyMethod(TableMap.NATIVE);
        
                tMap.setPrimaryKeyMethodInfo("DNEWSLETTER_SEQ");
        
                                      tMap.addPrimaryKey("DNEWSLETTER.DNEWSLETTER_ID", new Integer(0));
                                                        tMap.addColumn("DNEWSLETTER.DNEWSLETTER_CODE", new String());
                                                        tMap.addColumn("DNEWSLETTER.STATUS", new Integer(0));
                                                        tMap.addColumn("DNEWSLETTER.PRIORITY", new Integer(0));
                                                        tMap.addColumn("DNEWSLETTER.ISSUED_DATE", new Date());
                                                        tMap.addColumn("DNEWSLETTER.CLOSED_DATE", new Date());
                                                        tMap.addColumn("DNEWSLETTER.SENT_TIME", new Date());
                                                        tMap.addColumn("DNEWSLETTER.EMAIL_FORMAT", new Integer(0));
                                                        tMap.addForeignKey(
                "DNEWSLETTER.LANGUAGE_ID", new Integer(0) , "LANGUAGE" ,
                    "LANGUAGE_ID");
                                                        tMap.addForeignKey(
                "DNEWSLETTER.DISTRIBUTOR_CAT_ID", new Integer(0) , "DISTRIBUTOR_CATEGORY" ,
                    "DISTRIBUTOR_CAT_ID");
                                                        tMap.addColumn("DNEWSLETTER.DISTRIBUTOR_TYPE", new Integer(0));
                                                        tMap.addForeignKey(
                "DNEWSLETTER.DIST_LANGUAGE_ID", new Integer(0) , "LANGUAGE" ,
                    "LANGUAGE_ID");
                                                        tMap.addForeignKey(
                "DNEWSLETTER.DIST_COUNTRY_ID", new Integer(0) , "COUNTRY" ,
                    "COUNTRY_ID");
                                                        tMap.addForeignKey(
                "DNEWSLETTER.PROJECT_ID", new Integer(0) , "PROJECT" ,
                    "PROJECT_ID");
                                                        tMap.addForeignKey(
                "DNEWSLETTER.PRODUCT_ID", new Integer(0) , "PRODUCT" ,
                    "PRODUCT_ID");
                                                        tMap.addColumn("DNEWSLETTER.SUBJECT", new String());
                                                        tMap.addColumn("DNEWSLETTER.BODY", new String());
                                                        tMap.addColumn("DNEWSLETTER.NOTES", new String());
                                                        tMap.addColumn("DNEWSLETTER.CREATED", new Date());
                                                        tMap.addColumn("DNEWSLETTER.MODIFIED", new Date());
                                                        tMap.addColumn("DNEWSLETTER.CREATED_BY", new String());
                                                        tMap.addColumn("DNEWSLETTER.MODIFIED_BY", new String());
                              }
}
