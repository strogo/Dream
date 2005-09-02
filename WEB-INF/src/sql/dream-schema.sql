
-----------------------------------------------------------------------------
-- DREAM_USER
-----------------------------------------------------------------------------
DROP TABLE DREAM_USER;


CREATE TABLE DREAM_USER
(
                            USER_ID integer NOT NULL,
                            LOGIN_NAME varchar (32) NOT NULL,
                            WELCOME varchar (32) NOT NULL,
                            DEFAULT_LIST integer default 1000 NOT NULL,
                            DINBOX_FID integer default 1000 NOT NULL,
                            DOUTBOX_FID integer default 1000 NOT NULL,
                            DNEWSLETTER_FID integer default 1000 NOT NULL,
                            DRETURN_FID integer default 1000 NOT NULL,
                            DSHIPMENT_FID integer default 1000 NOT NULL,
                            DORDER_FID integer default 1000 NOT NULL,
                            DCONTRACT_FID integer default 1000 NOT NULL,
                            PROJECT_FID integer default 1000 NOT NULL,
                            LOCATION_FID integer default 1000 NOT NULL,
                            DISTRIBUTOR_FID integer default 1000 NOT NULL,
                            PRODUCT_FID integer default 1000 NOT NULL,
    PRIMARY KEY (USER_ID),
    CONSTRAINT DREAM_USER_U_1 UNIQUE (LOGIN_NAME)
);


-----------------------------------------------------------------------------
-- PROJECT_CATEGORY
-----------------------------------------------------------------------------
DROP TABLE PROJECT_CATEGORY;
DROP SEQUENCE PROJECT_CATEGORY_SEQ;

CREATE SEQUENCE PROJECT_CATEGORY_SEQ;

CREATE TABLE PROJECT_CATEGORY
(
                            PROJECT_CAT_ID integer DEFAULT nextval('PROJECT_CATEGORY_SEQ') NOT NULL,
                            PROJECT_CAT_NAME varchar (50) NOT NULL,
    PRIMARY KEY (PROJECT_CAT_ID),
    CONSTRAINT PROJECT_CATEGORY_U_1 UNIQUE (PROJECT_CAT_NAME)
);


-----------------------------------------------------------------------------
-- PRODUCT_CATEGORY
-----------------------------------------------------------------------------
DROP TABLE PRODUCT_CATEGORY;
DROP SEQUENCE PRODUCT_CATEGORY_SEQ;

CREATE SEQUENCE PRODUCT_CATEGORY_SEQ;

CREATE TABLE PRODUCT_CATEGORY
(
                            PRODUCT_CAT_ID integer DEFAULT nextval('PRODUCT_CATEGORY_SEQ') NOT NULL,
                            PRODUCT_CAT_NAME varchar (50) NOT NULL,
    PRIMARY KEY (PRODUCT_CAT_ID),
    CONSTRAINT PRODUCT_CATEGORY_U_1 UNIQUE (PRODUCT_CAT_NAME)
);


-----------------------------------------------------------------------------
-- DISTRIBUTOR_CATEGORY
-----------------------------------------------------------------------------
DROP TABLE DISTRIBUTOR_CATEGORY;
DROP SEQUENCE DISTRIBUTOR_CATEGORY_SEQ;

CREATE SEQUENCE DISTRIBUTOR_CATEGORY_SEQ;

CREATE TABLE DISTRIBUTOR_CATEGORY
(
                            DISTRIBUTOR_CAT_ID integer DEFAULT nextval('DISTRIBUTOR_CATEGORY_SEQ') NOT NULL,
                            DISTRIBUTOR_CAT_NAME varchar (50) NOT NULL,
    PRIMARY KEY (DISTRIBUTOR_CAT_ID),
    CONSTRAINT DISTRIBUTOR_CATEGORY_U_1 UNIQUE (DISTRIBUTOR_CAT_NAME)
);


-----------------------------------------------------------------------------
-- LOCATION_CATEGORY
-----------------------------------------------------------------------------
DROP TABLE LOCATION_CATEGORY;
DROP SEQUENCE LOCATION_CATEGORY_SEQ;

CREATE SEQUENCE LOCATION_CATEGORY_SEQ;

CREATE TABLE LOCATION_CATEGORY
(
                            LOCATION_CAT_ID integer DEFAULT nextval('LOCATION_CATEGORY_SEQ') NOT NULL,
                            LOCATION_CAT_NAME varchar (50) NOT NULL,
    PRIMARY KEY (LOCATION_CAT_ID),
    CONSTRAINT LOCATION_CATEGORY_U_1 UNIQUE (LOCATION_CAT_NAME)
);


-----------------------------------------------------------------------------
-- COST_TYPE
-----------------------------------------------------------------------------
DROP TABLE COST_TYPE;
DROP SEQUENCE COST_TYPE_SEQ;

CREATE SEQUENCE COST_TYPE_SEQ;

CREATE TABLE COST_TYPE
(
                            COST_TYPE_ID integer DEFAULT nextval('COST_TYPE_SEQ') NOT NULL,
                            COST_TYPE_FUNCTION integer default 10 NOT NULL,
                            COST_TYPE_NAME varchar (50) NOT NULL,
    PRIMARY KEY (COST_TYPE_ID),
    CONSTRAINT COST_TYPE_U_1 UNIQUE (COST_TYPE_NAME)
);


-----------------------------------------------------------------------------
-- COUNTRY
-----------------------------------------------------------------------------
DROP TABLE COUNTRY;
DROP SEQUENCE COUNTRY_SEQ;

CREATE SEQUENCE COUNTRY_SEQ;

CREATE TABLE COUNTRY
(
                            COUNTRY_ID integer DEFAULT nextval('COUNTRY_SEQ') NOT NULL,
                            COUNTRY_NAME varchar (50) NOT NULL,
                            COUNTRY_CODE char (2) NOT NULL,
    PRIMARY KEY (COUNTRY_ID),
    CONSTRAINT COUNTRY_U_1 UNIQUE (COUNTRY_NAME),
    CONSTRAINT COUNTRY_U_1 UNIQUE (COUNTRY_CODE)
);


-----------------------------------------------------------------------------
-- REGION
-----------------------------------------------------------------------------
DROP TABLE REGION;
DROP SEQUENCE REGION_SEQ;

CREATE SEQUENCE REGION_SEQ;

CREATE TABLE REGION
(
                            REGION_ID integer DEFAULT nextval('REGION_SEQ') NOT NULL,
                            REGION_NAME varchar (50) NOT NULL,
    PRIMARY KEY (REGION_ID),
    CONSTRAINT REGION_U_1 UNIQUE (REGION_NAME)
);


-----------------------------------------------------------------------------
-- SALES_AREA
-----------------------------------------------------------------------------
DROP TABLE SALES_AREA;
DROP SEQUENCE SALES_AREA_SEQ;

CREATE SEQUENCE SALES_AREA_SEQ;

CREATE TABLE SALES_AREA
(
                            SALES_AREA_ID integer DEFAULT nextval('SALES_AREA_SEQ') NOT NULL,
                            SALES_AREA_NAME varchar (50) NOT NULL,
    PRIMARY KEY (SALES_AREA_ID),
    CONSTRAINT SALES_AREA_U_1 UNIQUE (SALES_AREA_NAME)
);


-----------------------------------------------------------------------------
-- SALES_DISTRICT
-----------------------------------------------------------------------------
DROP TABLE SALES_DISTRICT;
DROP SEQUENCE SALES_DISTRICT_SEQ;

CREATE SEQUENCE SALES_DISTRICT_SEQ;

CREATE TABLE SALES_DISTRICT
(
                            SALES_DISTRICT_ID integer DEFAULT nextval('SALES_DISTRICT_SEQ') NOT NULL,
                              -- REFERENCES SALES_AREA (SALES_AREA_ID)
    SALES_AREA_ID integer default 1000 NOT NULL,
                            SALES_DISTRICT_NAME varchar (50) NOT NULL,
    PRIMARY KEY (SALES_DISTRICT_ID),
    CONSTRAINT SALES_DISTRICT_U_1 UNIQUE (SALES_DISTRICT_NAME)
);


-----------------------------------------------------------------------------
-- LANGUAGE
-----------------------------------------------------------------------------
DROP TABLE LANGUAGE;
DROP SEQUENCE LANGUAGE_SEQ;

CREATE SEQUENCE LANGUAGE_SEQ;

CREATE TABLE LANGUAGE
(
                            LANGUAGE_ID integer DEFAULT nextval('LANGUAGE_SEQ') NOT NULL,
                            LANGUAGE_NAME varchar (50) NOT NULL,
                            LANGUAGE_CODE char (2) NOT NULL,
    PRIMARY KEY (LANGUAGE_ID),
    CONSTRAINT LANGUAGE_U_1 UNIQUE (LANGUAGE_NAME),
    CONSTRAINT LANGUAGE_U_1 UNIQUE (LANGUAGE_CODE)
);


-----------------------------------------------------------------------------
-- VENDOR
-----------------------------------------------------------------------------
DROP TABLE VENDOR;
DROP SEQUENCE VENDOR_SEQ;

CREATE SEQUENCE VENDOR_SEQ;

CREATE TABLE VENDOR
(
                            VENDOR_ID integer DEFAULT nextval('VENDOR_SEQ') NOT NULL,
                            VENDOR_NAME varchar (50) NOT NULL,
    PRIMARY KEY (VENDOR_ID),
    CONSTRAINT VENDOR_U_1 UNIQUE (VENDOR_NAME)
);


-----------------------------------------------------------------------------
-- UOM
-----------------------------------------------------------------------------
DROP TABLE UOM;
DROP SEQUENCE UOM_SEQ;

CREATE SEQUENCE UOM_SEQ;

CREATE TABLE UOM
(
                            UOM_ID integer DEFAULT nextval('UOM_SEQ') NOT NULL,
                            UOM_NAME varchar (50) NOT NULL,
                            UOM_CODE char (3) NOT NULL,
    PRIMARY KEY (UOM_ID),
    CONSTRAINT UOM_U_1 UNIQUE (UOM_NAME),
    CONSTRAINT UOM_U_1 UNIQUE (UOM_CODE)
);


-----------------------------------------------------------------------------
-- DISTRIBUTOR
-----------------------------------------------------------------------------
DROP TABLE DISTRIBUTOR;
DROP SEQUENCE DISTRIBUTOR_SEQ;

CREATE SEQUENCE DISTRIBUTOR_SEQ;

CREATE TABLE DISTRIBUTOR
(
                            DISTRIBUTOR_ID integer DEFAULT nextval('DISTRIBUTOR_SEQ') NOT NULL,
                            DISTRIBUTOR_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            DISTRIBUTOR_NAME_1 varchar (70) NOT NULL,
                            DISTRIBUTOR_NAME_2 varchar (70),
                            DISTRIBUTOR_DISPLAY varchar (70) NOT NULL,
                            DEAR varchar (70),
                            DISTRIBUTOR_TYPE integer default 10 NOT NULL,
                            GENDER integer default 10 NOT NULL,
                              -- REFERENCES DISTRIBUTOR_CATEGORY (DISTRIBUTOR_CAT_ID)
    DISTRIBUTOR_CAT_ID integer default 1000 NOT NULL,
                              -- REFERENCES LANGUAGE (LANGUAGE_ID)
    LANGUAGE_ID integer default 1000 NOT NULL,
                            ADDRESS_1 varchar (55),
                            ADDRESS_2 varchar (55),
                            CITY varchar (35),
                            ZIP varchar (12),
                            STATE varchar (35),
                              -- REFERENCES COUNTRY (COUNTRY_ID)
    COUNTRY_ID integer default 1000 NOT NULL,
                              -- REFERENCES REGION (REGION_ID)
    REGION_ID integer default 1000 NOT NULL,
                            PHONE_1 varchar (30),
                            PHONE_2 varchar (30),
                            FAX varchar (30),
                            EMAIL varchar (70),
                            EMAIL_FORMAT integer default 10 NOT NULL,
                            SEND_NEWS integer default 20 NOT NULL,
                            WEB_URL varchar (70),
                            CUSTOM_1 varchar (55),
                            CUSTOM_2 varchar (55),
                            CUSTOM_3 varchar (55),
                            CUSTOM_4 varchar (55),
                            CUSTOM_5 varchar (55),
                            CUSTOM_6 varchar (55),
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (DISTRIBUTOR_ID),
    CONSTRAINT DISTRIBUTOR_U_3 UNIQUE (DISTRIBUTOR_CODE),
    CONSTRAINT DISTRIBUTOR_U_3 UNIQUE (DISTRIBUTOR_DISPLAY)
);
CREATE INDEX DISTRIBUTOR_I_3 ON DISTRIBUTOR (EMAIL);
CREATE INDEX DISTRIBUTOR_I_3 ON DISTRIBUTOR (CITY);


-----------------------------------------------------------------------------
-- LOCATION
-----------------------------------------------------------------------------
DROP TABLE LOCATION;
DROP SEQUENCE LOCATION_SEQ;

CREATE SEQUENCE LOCATION_SEQ;

CREATE TABLE LOCATION
(
                            LOCATION_ID integer DEFAULT nextval('LOCATION_SEQ') NOT NULL,
                            LOCATION_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            LOCATION_NAME_1 varchar (70) NOT NULL,
                            LOCATION_NAME_2 varchar (70),
                            LOCATION_DISPLAY varchar (70) NOT NULL,
                              -- REFERENCES SALES_DISTRICT (SALES_DISTRICT_ID)
    SALES_DISTRICT_ID integer default 1000 NOT NULL,
                            LOCATION_TYPE integer default 10 NOT NULL,
                            GENDER integer default 10 NOT NULL,
                              -- REFERENCES LOCATION_CATEGORY (LOCATION_CAT_ID)
    LOCATION_CAT_ID integer default 1000 NOT NULL,
                              -- REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
    DISTRIBUTOR_ID integer default 1000 NOT NULL,
                            ADDRESS_1 varchar (55),
                            ADDRESS_2 varchar (55),
                            CITY varchar (35),
                            ZIP varchar (12),
                            STATE varchar (35),
                              -- REFERENCES COUNTRY (COUNTRY_ID)
    COUNTRY_ID integer default 1000 NOT NULL,
                              -- REFERENCES REGION (REGION_ID)
    REGION_ID integer default 1000 NOT NULL,
                            PHONE_1 varchar (30),
                            PHONE_2 varchar (30),
                            FAX varchar (30),
                            CUSTOM_1 varchar (55),
                            CUSTOM_2 varchar (55),
                            CUSTOM_3 varchar (55),
                            CUSTOM_4 varchar (55),
                            CUSTOM_5 varchar (55),
                            CUSTOM_6 varchar (55),
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (LOCATION_ID),
    CONSTRAINT LOCATION_U_2 UNIQUE (LOCATION_CODE),
    CONSTRAINT LOCATION_U_2 UNIQUE (LOCATION_DISPLAY)
);
CREATE INDEX LOCATION_I_2 ON LOCATION (CITY);


-----------------------------------------------------------------------------
-- PRODUCT
-----------------------------------------------------------------------------
DROP TABLE PRODUCT;
DROP SEQUENCE PRODUCT_SEQ;

CREATE SEQUENCE PRODUCT_SEQ;

CREATE TABLE PRODUCT
(
                            PRODUCT_ID integer DEFAULT nextval('PRODUCT_SEQ') NOT NULL,
                            PRODUCT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRODUCT_TYPE integer default 10 NOT NULL,
                              -- REFERENCES PRODUCT_CATEGORY (PRODUCT_CAT_ID)
    PRODUCT_CAT_ID integer default 1000 NOT NULL,
                            PRODUCT_DESCRIPTION varchar (254) NOT NULL,
                            PRODUCT_DISPLAY varchar (70) NOT NULL,
                            BASE_PRICE float default 0 NOT NULL,
                            BASE_WEIGHT float default 0 NOT NULL,
                              -- REFERENCES UOM (UOM_ID)
    UOM_ID integer default 1000 NOT NULL,
                            WEB_URL varchar (70),
                            SHOW_ON_PRICELIST integer default 20 NOT NULL,
                              -- REFERENCES VENDOR (VENDOR_ID)
    VENDOR_ID integer default 1000 NOT NULL,
                            VENDORS_CODE varchar (20),
                            EAN_UPC_CODE varchar (20),
                            LOCATION varchar (55),
                            CUSTOM_1 varchar (55),
                            CUSTOM_2 varchar (55),
                            CUSTOM_3 varchar (55),
                            CUSTOM_4 varchar (55),
                            CUSTOM_5 varchar (55),
                            CUSTOM_6 varchar (55),
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (PRODUCT_ID),
    CONSTRAINT PRODUCT_U_2 UNIQUE (PRODUCT_CODE),
    CONSTRAINT PRODUCT_U_2 UNIQUE (PRODUCT_DISPLAY)
);
CREATE INDEX PRODUCT_I_2 ON PRODUCT (BASE_PRICE);


-----------------------------------------------------------------------------
-- PROJECT
-----------------------------------------------------------------------------
DROP TABLE PROJECT;
DROP SEQUENCE PROJECT_SEQ;

CREATE SEQUENCE PROJECT_SEQ;

CREATE TABLE PROJECT
(
                            PROJECT_ID integer DEFAULT nextval('PROJECT_SEQ') NOT NULL,
                            PROJECT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                              -- REFERENCES PROJECT_CATEGORY (PROJECT_CAT_ID)
    PROJECT_CAT_ID integer default 1000 NOT NULL,
                            PROJECT_NAME varchar (70) NOT NULL,
                            START_DATE date NOT NULL,
                            END_DATE date,
                            EXPENSES decimal (15,2) default 0 NOT NULL,
                            REVENUES decimal (15,2) default 0 NOT NULL,
                            CUSTOM_1 varchar (55),
                            CUSTOM_2 varchar (55),
                            CUSTOM_3 varchar (55),
                            CUSTOM_4 varchar (55),
                            CUSTOM_5 varchar (55),
                            CUSTOM_6 varchar (55),
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (PROJECT_ID),
    CONSTRAINT PROJECT_U_4 UNIQUE (PROJECT_CODE),
    CONSTRAINT PROJECT_U_4 UNIQUE (PROJECT_NAME)
);
CREATE INDEX PROJECT_I_4 ON PROJECT (START_DATE);
CREATE INDEX PROJECT_I_4 ON PROJECT (EXPENSES);
CREATE INDEX PROJECT_I_4 ON PROJECT (REVENUES);


-----------------------------------------------------------------------------
-- DCONTRACT
-----------------------------------------------------------------------------
DROP TABLE DCONTRACT;
DROP SEQUENCE DCONTRACT_SEQ;

CREATE SEQUENCE DCONTRACT_SEQ;

CREATE TABLE DCONTRACT
(
                            DCONTRACT_ID integer DEFAULT nextval('DCONTRACT_SEQ') NOT NULL,
                            DCONTRACT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                              -- REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
    DISTRIBUTOR_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                            START_DATE date NOT NULL,
                            END_DATE date NOT NULL,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (DCONTRACT_ID),
    CONSTRAINT DCONTRACT_U_3 UNIQUE (DCONTRACT_CODE)
);
CREATE INDEX DCONTRACT_I_3 ON DCONTRACT (ISSUED_DATE);
CREATE INDEX DCONTRACT_I_3 ON DCONTRACT (STATUS);


-----------------------------------------------------------------------------
-- DCONTRACT_ITEM
-----------------------------------------------------------------------------
DROP TABLE DCONTRACT_ITEM;
DROP SEQUENCE DCONTRACT_ITEM_SEQ;

CREATE SEQUENCE DCONTRACT_ITEM_SEQ;

CREATE TABLE DCONTRACT_ITEM
(
                            DCONTRACT_ITEM_ID integer DEFAULT nextval('DCONTRACT_ITEM_SEQ') NOT NULL,
                              -- REFERENCES DCONTRACT (DCONTRACT_ID)
    DCONTRACT_ID integer default 1000 NOT NULL,
                              -- REFERENCES COST_TYPE (COST_TYPE_ID)
    COST_TYPE_ID integer default 1000 NOT NULL,
                            COST_FUNCTION integer default 10 NOT NULL,
                            COST_AMOUNT decimal (15,2) default 0 NOT NULL,
    PRIMARY KEY (DCONTRACT_ITEM_ID)
);
CREATE INDEX DCONTRACT_ITEM_I_3 ON DCONTRACT_ITEM (DCONTRACT_ID);
CREATE INDEX DCONTRACT_ITEM_I_3 ON DCONTRACT_ITEM (COST_TYPE_ID);


-----------------------------------------------------------------------------
-- DORDER
-----------------------------------------------------------------------------
DROP TABLE DORDER;
DROP SEQUENCE DORDER_SEQ;

CREATE SEQUENCE DORDER_SEQ;

CREATE TABLE DORDER
(
                            DORDER_ID integer DEFAULT nextval('DORDER_SEQ') NOT NULL,
                            DORDER_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            SALES_DATE date NOT NULL,
                            EXPECTED_TIME timestamp NOT NULL,
                            CLOSED_DATE date,
                              -- REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
    DISTRIBUTOR_ID integer default 1000 NOT NULL,
                              -- REFERENCES DCONTRACT (DCONTRACT_ID)
    DCONTRACT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            DESCRIPTION varchar (254) NOT NULL,
                            UNIT_PRICE float default 0 NOT NULL,
                            UNIT_WEIGHT float default 0 NOT NULL,
                            TOTAL_QUANTITY integer default 0 NOT NULL,
                            TOTAL_AMOUNT decimal (15,2) default 0 NOT NULL,
                            TOTAL_WEIGHT decimal (15,2) default 0 NOT NULL,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (DORDER_ID),
    CONSTRAINT DORDER_U_3 UNIQUE (DORDER_CODE)
);
CREATE INDEX DORDER_I_3 ON DORDER (ISSUED_DATE);
CREATE INDEX DORDER_I_3 ON DORDER (STATUS);


-----------------------------------------------------------------------------
-- DORDER_ITEM
-----------------------------------------------------------------------------
DROP TABLE DORDER_ITEM;
DROP SEQUENCE DORDER_ITEM_SEQ;

CREATE SEQUENCE DORDER_ITEM_SEQ;

CREATE TABLE DORDER_ITEM
(
                            DORDER_ITEM_ID integer DEFAULT nextval('DORDER_ITEM_SEQ') NOT NULL,
                              -- REFERENCES DORDER (DORDER_ID)
    DORDER_ID integer default 1000 NOT NULL,
                              -- REFERENCES LOCATION (LOCATION_ID)
    LOCATION_ID integer default 1000 NOT NULL,
                            ORDERED_QTY integer default 0 NOT NULL,
                            SHIPPED_QTY integer default 0 NOT NULL,
                            UNSOLD_QTY integer default 0 NOT NULL,
                            LOST_QTY integer default 0 NOT NULL,
    PRIMARY KEY (DORDER_ITEM_ID),
    CONSTRAINT DORDER_ITEM_U_1 UNIQUE (DORDER_ID, LOCATION_ID)
);


-----------------------------------------------------------------------------
-- DSHIPMENT
-----------------------------------------------------------------------------
DROP TABLE DSHIPMENT;
DROP SEQUENCE DSHIPMENT_SEQ;

CREATE SEQUENCE DSHIPMENT_SEQ;

CREATE TABLE DSHIPMENT
(
                            DSHIPMENT_ID integer DEFAULT nextval('DSHIPMENT_SEQ') NOT NULL,
                            DSHIPMENT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            SHIPPING_TIME timestamp NOT NULL,
                            CLOSED_DATE date,
                              -- REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
    DISTRIBUTOR_ID integer default 1000 NOT NULL,
                              -- REFERENCES DORDER (DORDER_ID)
    DORDER_ID integer default 1000 NOT NULL,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (DSHIPMENT_ID),
    CONSTRAINT DSHIPMENT_U_3 UNIQUE (DSHIPMENT_CODE),
    CONSTRAINT DSHIPMENT_U_3 UNIQUE (DORDER_ID)
);
CREATE INDEX DSHIPMENT_I_3 ON DSHIPMENT (ISSUED_DATE);
CREATE INDEX DSHIPMENT_I_3 ON DSHIPMENT (STATUS);


-----------------------------------------------------------------------------
-- DRETURN
-----------------------------------------------------------------------------
DROP TABLE DRETURN;
DROP SEQUENCE DRETURN_SEQ;

CREATE SEQUENCE DRETURN_SEQ;

CREATE TABLE DRETURN
(
                            DRETURN_ID integer DEFAULT nextval('DRETURN_SEQ') NOT NULL,
                            DRETURN_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            RETURN_DATE date NOT NULL,
                            CLOSED_DATE date,
                              -- REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
    DISTRIBUTOR_ID integer default 1000 NOT NULL,
                              -- REFERENCES DORDER (DORDER_ID)
    DORDER_ID integer default 1000 NOT NULL,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (DRETURN_ID),
    CONSTRAINT DRETURN_U_3 UNIQUE (DRETURN_CODE),
    CONSTRAINT DRETURN_U_3 UNIQUE (DORDER_ID)
);
CREATE INDEX DRETURN_I_3 ON DRETURN (ISSUED_DATE);
CREATE INDEX DRETURN_I_3 ON DRETURN (STATUS);


-----------------------------------------------------------------------------
-- DINBOX_EVENT
-----------------------------------------------------------------------------
DROP TABLE DINBOX_EVENT;
DROP SEQUENCE DINBOX_EVENT_SEQ;

CREATE SEQUENCE DINBOX_EVENT_SEQ;

CREATE TABLE DINBOX_EVENT
(
                            DINBOX_EVENT_ID integer DEFAULT nextval('DINBOX_EVENT_SEQ') NOT NULL,
                            DINBOX_EVENT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                            RECEIVED_TIME timestamp,
                            SENT_TIME timestamp,
                            EVENT_CHANNEL integer default 20 NOT NULL,
                            EVENT_TYPE integer default 30 NOT NULL,
                            EMAIL_FORMAT integer default 10 NOT NULL,
                              -- REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
    DISTRIBUTOR_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            SENDER varchar (254),
                            SUBJECT varchar (254) NOT NULL,
                            BODY text,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (DINBOX_EVENT_ID),
    CONSTRAINT DINBOX_EVENT_U_4 UNIQUE (DINBOX_EVENT_CODE)
);
CREATE INDEX DINBOX_EVENT_I_4 ON DINBOX_EVENT (ISSUED_DATE);
CREATE INDEX DINBOX_EVENT_I_4 ON DINBOX_EVENT (STATUS);
CREATE INDEX DINBOX_EVENT_I_4 ON DINBOX_EVENT (SUBJECT);


-----------------------------------------------------------------------------
-- DOUTBOX_EVENT
-----------------------------------------------------------------------------
DROP TABLE DOUTBOX_EVENT;
DROP SEQUENCE DOUTBOX_EVENT_SEQ;

CREATE SEQUENCE DOUTBOX_EVENT_SEQ;

CREATE TABLE DOUTBOX_EVENT
(
                            DOUTBOX_EVENT_ID integer DEFAULT nextval('DOUTBOX_EVENT_SEQ') NOT NULL,
                            DOUTBOX_EVENT_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                            SENT_TIME timestamp,
                            EVENT_CHANNEL integer default 10 NOT NULL,
                            EVENT_TYPE integer default 20 NOT NULL,
                            EMAIL_FORMAT integer default 10 NOT NULL,
                              -- REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
    DISTRIBUTOR_ID integer default 1000 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            RECEIVER varchar (254),
                            SUBJECT varchar (254) NOT NULL,
                            BODY text,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (DOUTBOX_EVENT_ID),
    CONSTRAINT DOUTBOX_EVENT_U_4 UNIQUE (DOUTBOX_EVENT_CODE)
);
CREATE INDEX DOUTBOX_EVENT_I_4 ON DOUTBOX_EVENT (ISSUED_DATE);
CREATE INDEX DOUTBOX_EVENT_I_4 ON DOUTBOX_EVENT (STATUS);
CREATE INDEX DOUTBOX_EVENT_I_4 ON DOUTBOX_EVENT (SUBJECT);


-----------------------------------------------------------------------------
-- DNEWSLETTER
-----------------------------------------------------------------------------
DROP TABLE DNEWSLETTER;
DROP SEQUENCE DNEWSLETTER_SEQ;

CREATE SEQUENCE DNEWSLETTER_SEQ;

CREATE TABLE DNEWSLETTER
(
                            DNEWSLETTER_ID integer DEFAULT nextval('DNEWSLETTER_SEQ') NOT NULL,
                            DNEWSLETTER_CODE varchar (20) default 'AUTO' NOT NULL,
                            STATUS integer default 30 NOT NULL,
                            PRIORITY integer default 30 NOT NULL,
                            ISSUED_DATE date NOT NULL,
                            CLOSED_DATE date,
                            SENT_TIME timestamp,
                            EMAIL_FORMAT integer default 10 NOT NULL,
                              -- REFERENCES LANGUAGE (LANGUAGE_ID)
    LANGUAGE_ID integer default 1000 NOT NULL,
                              -- REFERENCES DISTRIBUTOR_CATEGORY (DISTRIBUTOR_CAT_ID)
    DISTRIBUTOR_CAT_ID integer default 999 NOT NULL,
                            DISTRIBUTOR_TYPE integer default 1 NOT NULL,
                              -- REFERENCES LANGUAGE (LANGUAGE_ID)
    DIST_LANGUAGE_ID integer default 999 NOT NULL,
                              -- REFERENCES COUNTRY (COUNTRY_ID)
    DIST_COUNTRY_ID integer default 999 NOT NULL,
                              -- REFERENCES PROJECT (PROJECT_ID)
    PROJECT_ID integer default 1000 NOT NULL,
                              -- REFERENCES PRODUCT (PRODUCT_ID)
    PRODUCT_ID integer default 1000 NOT NULL,
                            SUBJECT varchar (254) NOT NULL,
                            BODY text,
                            NOTES text,
                            CREATED timestamp NOT NULL,
                            MODIFIED timestamp NOT NULL,
                            CREATED_BY varchar (32) NOT NULL,
                            MODIFIED_BY varchar (32) NOT NULL,
    PRIMARY KEY (DNEWSLETTER_ID),
    CONSTRAINT DNEWSLETTER_U_4 UNIQUE (DNEWSLETTER_CODE)
);
CREATE INDEX DNEWSLETTER_I_4 ON DNEWSLETTER (ISSUED_DATE);
CREATE INDEX DNEWSLETTER_I_4 ON DNEWSLETTER (STATUS);
CREATE INDEX DNEWSLETTER_I_4 ON DNEWSLETTER (SUBJECT);


----------------------------------------------------------------------
-- DNEWSLETTER                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- DREAM_USER                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- PROJECT_CATEGORY                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- PRODUCT_CATEGORY                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- DISTRIBUTOR_CATEGORY                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- LOCATION_CATEGORY                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- COST_TYPE                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- COUNTRY                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- REGION                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- SALES_AREA                                                      
----------------------------------------------------------------------

ALTER TABLE SALES_DISTRICT
    ADD CONSTRAINT SALES_DISTRICT_FK_1 FOREIGN KEY (SALES_AREA_ID)
    REFERENCES SALES_AREA (SALES_AREA_ID)
;

----------------------------------------------------------------------
-- SALES_DISTRICT                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- LANGUAGE                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- VENDOR                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- UOM                                                      
----------------------------------------------------------------------

ALTER TABLE DISTRIBUTOR
    ADD CONSTRAINT DISTRIBUTOR_FK_1 FOREIGN KEY (DISTRIBUTOR_CAT_ID)
    REFERENCES DISTRIBUTOR_CATEGORY (DISTRIBUTOR_CAT_ID)
;
ALTER TABLE DISTRIBUTOR
    ADD CONSTRAINT DISTRIBUTOR_FK_2 FOREIGN KEY (COUNTRY_ID)
    REFERENCES COUNTRY (COUNTRY_ID)
;
ALTER TABLE DISTRIBUTOR
    ADD CONSTRAINT DISTRIBUTOR_FK_3 FOREIGN KEY (REGION_ID)
    REFERENCES REGION (REGION_ID)
;
ALTER TABLE DISTRIBUTOR
    ADD CONSTRAINT DISTRIBUTOR_FK_4 FOREIGN KEY (LANGUAGE_ID)
    REFERENCES LANGUAGE (LANGUAGE_ID)
;

----------------------------------------------------------------------
-- DISTRIBUTOR                                                      
----------------------------------------------------------------------

ALTER TABLE LOCATION
    ADD CONSTRAINT LOCATION_FK_1 FOREIGN KEY (LOCATION_CAT_ID)
    REFERENCES LOCATION_CATEGORY (LOCATION_CAT_ID)
;
ALTER TABLE LOCATION
    ADD CONSTRAINT LOCATION_FK_2 FOREIGN KEY (COUNTRY_ID)
    REFERENCES COUNTRY (COUNTRY_ID)
;
ALTER TABLE LOCATION
    ADD CONSTRAINT LOCATION_FK_3 FOREIGN KEY (REGION_ID)
    REFERENCES REGION (REGION_ID)
;
ALTER TABLE LOCATION
    ADD CONSTRAINT LOCATION_FK_4 FOREIGN KEY (SALES_DISTRICT_ID)
    REFERENCES SALES_DISTRICT (SALES_DISTRICT_ID)
;
ALTER TABLE LOCATION
    ADD CONSTRAINT LOCATION_FK_5 FOREIGN KEY (DISTRIBUTOR_ID)
    REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
;

----------------------------------------------------------------------
-- LOCATION                                                      
----------------------------------------------------------------------

ALTER TABLE PRODUCT
    ADD CONSTRAINT PRODUCT_FK_1 FOREIGN KEY (PRODUCT_CAT_ID)
    REFERENCES PRODUCT_CATEGORY (PRODUCT_CAT_ID)
;
ALTER TABLE PRODUCT
    ADD CONSTRAINT PRODUCT_FK_2 FOREIGN KEY (UOM_ID)
    REFERENCES UOM (UOM_ID)
;
ALTER TABLE PRODUCT
    ADD CONSTRAINT PRODUCT_FK_3 FOREIGN KEY (VENDOR_ID)
    REFERENCES VENDOR (VENDOR_ID)
;

----------------------------------------------------------------------
-- PRODUCT                                                      
----------------------------------------------------------------------

ALTER TABLE PROJECT
    ADD CONSTRAINT PROJECT_FK_1 FOREIGN KEY (PROJECT_CAT_ID)
    REFERENCES PROJECT_CATEGORY (PROJECT_CAT_ID)
;

----------------------------------------------------------------------
-- PROJECT                                                      
----------------------------------------------------------------------

ALTER TABLE DCONTRACT
    ADD CONSTRAINT DCONTRACT_FK_1 FOREIGN KEY (DISTRIBUTOR_ID)
    REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
;
ALTER TABLE DCONTRACT
    ADD CONSTRAINT DCONTRACT_FK_2 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;

----------------------------------------------------------------------
-- DCONTRACT                                                      
----------------------------------------------------------------------

ALTER TABLE DCONTRACT_ITEM
    ADD CONSTRAINT DCONTRACT_ITEM_FK_1 FOREIGN KEY (DCONTRACT_ID)
    REFERENCES DCONTRACT (DCONTRACT_ID)
    ON DELETE CASCADE 
;
ALTER TABLE DCONTRACT_ITEM
    ADD CONSTRAINT DCONTRACT_ITEM_FK_2 FOREIGN KEY (COST_TYPE_ID)
    REFERENCES COST_TYPE (COST_TYPE_ID)
;

----------------------------------------------------------------------
-- DCONTRACT_ITEM                                                      
----------------------------------------------------------------------

ALTER TABLE DORDER
    ADD CONSTRAINT DORDER_FK_1 FOREIGN KEY (DISTRIBUTOR_ID)
    REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
;
ALTER TABLE DORDER
    ADD CONSTRAINT DORDER_FK_2 FOREIGN KEY (DCONTRACT_ID)
    REFERENCES DCONTRACT (DCONTRACT_ID)
;
ALTER TABLE DORDER
    ADD CONSTRAINT DORDER_FK_3 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE DORDER
    ADD CONSTRAINT DORDER_FK_4 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;

----------------------------------------------------------------------
-- DORDER                                                      
----------------------------------------------------------------------

ALTER TABLE DORDER_ITEM
    ADD CONSTRAINT DORDER_ITEM_FK_1 FOREIGN KEY (DORDER_ID)
    REFERENCES DORDER (DORDER_ID)
    ON DELETE CASCADE 
;
ALTER TABLE DORDER_ITEM
    ADD CONSTRAINT DORDER_ITEM_FK_2 FOREIGN KEY (LOCATION_ID)
    REFERENCES LOCATION (LOCATION_ID)
;

----------------------------------------------------------------------
-- DORDER_ITEM                                                      
----------------------------------------------------------------------

ALTER TABLE DSHIPMENT
    ADD CONSTRAINT DSHIPMENT_FK_1 FOREIGN KEY (DISTRIBUTOR_ID)
    REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
;
ALTER TABLE DSHIPMENT
    ADD CONSTRAINT DSHIPMENT_FK_2 FOREIGN KEY (DORDER_ID)
    REFERENCES DORDER (DORDER_ID)
;

----------------------------------------------------------------------
-- DSHIPMENT                                                      
----------------------------------------------------------------------

ALTER TABLE DRETURN
    ADD CONSTRAINT DRETURN_FK_1 FOREIGN KEY (DISTRIBUTOR_ID)
    REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
;
ALTER TABLE DRETURN
    ADD CONSTRAINT DRETURN_FK_2 FOREIGN KEY (DORDER_ID)
    REFERENCES DORDER (DORDER_ID)
;

----------------------------------------------------------------------
-- DRETURN                                                      
----------------------------------------------------------------------

ALTER TABLE DINBOX_EVENT
    ADD CONSTRAINT DINBOX_EVENT_FK_1 FOREIGN KEY (DISTRIBUTOR_ID)
    REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
;
ALTER TABLE DINBOX_EVENT
    ADD CONSTRAINT DINBOX_EVENT_FK_2 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE DINBOX_EVENT
    ADD CONSTRAINT DINBOX_EVENT_FK_3 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;

----------------------------------------------------------------------
-- DINBOX_EVENT                                                      
----------------------------------------------------------------------

ALTER TABLE DOUTBOX_EVENT
    ADD CONSTRAINT DOUTBOX_EVENT_FK_1 FOREIGN KEY (DISTRIBUTOR_ID)
    REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
;
ALTER TABLE DOUTBOX_EVENT
    ADD CONSTRAINT DOUTBOX_EVENT_FK_2 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE DOUTBOX_EVENT
    ADD CONSTRAINT DOUTBOX_EVENT_FK_3 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;

----------------------------------------------------------------------
-- DOUTBOX_EVENT                                                      
----------------------------------------------------------------------

ALTER TABLE DNEWSLETTER
    ADD CONSTRAINT DNEWSLETTER_FK_1 FOREIGN KEY (DISTRIBUTOR_CAT_ID)
    REFERENCES DISTRIBUTOR_CATEGORY (DISTRIBUTOR_CAT_ID)
;
ALTER TABLE DNEWSLETTER
    ADD CONSTRAINT DNEWSLETTER_FK_2 FOREIGN KEY (PROJECT_ID)
    REFERENCES PROJECT (PROJECT_ID)
;
ALTER TABLE DNEWSLETTER
    ADD CONSTRAINT DNEWSLETTER_FK_3 FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
;
ALTER TABLE DNEWSLETTER
    ADD CONSTRAINT DNEWSLETTER_FK_4 FOREIGN KEY (DIST_COUNTRY_ID)
    REFERENCES COUNTRY (COUNTRY_ID)
;
ALTER TABLE DNEWSLETTER
    ADD CONSTRAINT DNEWSLETTER_FK_5 FOREIGN KEY (DIST_LANGUAGE_ID)
    REFERENCES LANGUAGE (LANGUAGE_ID)
;
ALTER TABLE DNEWSLETTER
    ADD CONSTRAINT DNEWSLETTER_FK_6 FOREIGN KEY (LANGUAGE_ID)
    REFERENCES LANGUAGE (LANGUAGE_ID)
;
