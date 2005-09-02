
-----------------------------------------------------------------------------
-- ID_TABLE
-----------------------------------------------------------------------------
DROP TABLE ID_TABLE;
DROP SEQUENCE ID_TABLE_SEQ;

CREATE SEQUENCE ID_TABLE_SEQ;

CREATE TABLE ID_TABLE
(
                            ID_TABLE_ID integer DEFAULT nextval('ID_TABLE_SEQ') NOT NULL,
                            TABLE_NAME varchar (255) NOT NULL,
                            NEXT_ID integer,
                            QUANTITY integer,
    PRIMARY KEY (ID_TABLE_ID),
    CONSTRAINT ID_TABLE_U_1 UNIQUE (TABLE_NAME)
);


----------------------------------------------------------------------
-- ID_TABLE                                                      
----------------------------------------------------------------------

