 

-----------------------------------------------------------------------------
-- TURBINE_PERMISSION
-----------------------------------------------------------------------------
DROP TABLE TURBINE_PERMISSION;
DROP SEQUENCE TURBINE_PERMISSION_SEQ;

CREATE SEQUENCE TURBINE_PERMISSION_SEQ;

CREATE TABLE TURBINE_PERMISSION
(
                            PERMISSION_ID integer DEFAULT nextval('TURBINE_PERMISSION_SEQ') NOT NULL,
                            PERMISSION_NAME varchar (99) NOT NULL,
                            OBJECTDATA bytea,
    PRIMARY KEY (PERMISSION_ID),
    CONSTRAINT TURBINE_PERMISSION_U_1 UNIQUE (PERMISSION_NAME)
);


-----------------------------------------------------------------------------
-- TURBINE_ROLE
-----------------------------------------------------------------------------
DROP TABLE TURBINE_ROLE;
DROP SEQUENCE TURBINE_ROLE_SEQ;

CREATE SEQUENCE TURBINE_ROLE_SEQ minvalue 1001;

CREATE TABLE TURBINE_ROLE
(
                            ROLE_ID integer DEFAULT nextval('TURBINE_ROLE_SEQ') NOT NULL,
                            ROLE_NAME varchar (99) NOT NULL,
                            OBJECTDATA bytea,
    PRIMARY KEY (ROLE_ID),
    CONSTRAINT TURBINE_ROLE_U_1 UNIQUE (ROLE_NAME)
);


-----------------------------------------------------------------------------
-- TURBINE_GROUP
-----------------------------------------------------------------------------
DROP TABLE TURBINE_GROUP;
DROP SEQUENCE TURBINE_GROUP_SEQ;

CREATE SEQUENCE TURBINE_GROUP_SEQ;

CREATE TABLE TURBINE_GROUP
(
                            GROUP_ID integer DEFAULT nextval('TURBINE_GROUP_SEQ') NOT NULL,
                            GROUP_NAME varchar (99) NOT NULL,
                            OBJECTDATA bytea,
    PRIMARY KEY (GROUP_ID),
    CONSTRAINT TURBINE_GROUP_U_1 UNIQUE (GROUP_NAME)
);


-----------------------------------------------------------------------------
-- TURBINE_ROLE_PERMISSION
-----------------------------------------------------------------------------
DROP TABLE TURBINE_ROLE_PERMISSION;


CREATE TABLE TURBINE_ROLE_PERMISSION
(
                              -- REFERENCES TURBINE_ROLE (ROLE_ID)
    ROLE_ID integer NOT NULL,
                              -- REFERENCES TURBINE_PERMISSION (PERMISSION_ID)
    PERMISSION_ID integer NOT NULL,
    PRIMARY KEY (ROLE_ID,PERMISSION_ID)
);


-----------------------------------------------------------------------------
-- TURBINE_USER
-----------------------------------------------------------------------------
DROP TABLE TURBINE_USER;
DROP SEQUENCE TURBINE_USER_SEQ;

CREATE SEQUENCE TURBINE_USER_SEQ minvalue 1001;

CREATE TABLE TURBINE_USER
(
                            USER_ID integer DEFAULT nextval('TURBINE_USER_SEQ') NOT NULL,
                            LOGIN_NAME varchar (32) NOT NULL,
                            PASSWORD_VALUE varchar (32) NOT NULL,
                            FIRST_NAME varchar (99) NOT NULL,
                            LAST_NAME varchar (99) NOT NULL,
                            EMAIL varchar (99),
                            CONFIRM_VALUE varchar (99),
                            MODIFIED timestamp,
                            CREATED timestamp,
                            LAST_LOGIN timestamp,
                            OBJECTDATA bytea,
    PRIMARY KEY (USER_ID),
    CONSTRAINT TURBINE_USER_U_1 UNIQUE (LOGIN_NAME)
);


-----------------------------------------------------------------------------
-- TURBINE_USER_GROUP_ROLE
-----------------------------------------------------------------------------
DROP TABLE TURBINE_USER_GROUP_ROLE;


CREATE TABLE TURBINE_USER_GROUP_ROLE
(
                              -- REFERENCES TURBINE_USER (USER_ID)
    USER_ID integer NOT NULL,
                              -- REFERENCES TURBINE_GROUP (GROUP_ID)
    GROUP_ID integer NOT NULL,
                              -- REFERENCES TURBINE_ROLE (ROLE_ID)
    ROLE_ID integer NOT NULL,
    PRIMARY KEY (USER_ID,GROUP_ID,ROLE_ID)
);


-----------------------------------------------------------------------------
-- TURBINE_SCHEDULED_JOB
-----------------------------------------------------------------------------
DROP TABLE TURBINE_SCHEDULED_JOB;
DROP SEQUENCE TURBINE_SCHEDULED_JOB_SEQ;

CREATE SEQUENCE TURBINE_SCHEDULED_JOB_SEQ;

CREATE TABLE TURBINE_SCHEDULED_JOB
(
                            JOB_ID integer DEFAULT nextval('TURBINE_SCHEDULED_JOB_SEQ') NOT NULL,
                            SECOND integer default -1 NOT NULL,
                            MINUTE integer default -1 NOT NULL,
                            HOUR integer default -1 NOT NULL,
                            WEEK_DAY integer default -1 NOT NULL,
                            DAY_OF_MONTH integer default -1 NOT NULL,
                            TASK varchar (99) NOT NULL,
                            EMAIL varchar (99),
                            PROPERTY bytea,
    PRIMARY KEY (JOB_ID)
);


----------------------------------------------------------------------
-- TURBINE_SCHEDULED_JOB                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- TURBINE_PERMISSION                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- TURBINE_ROLE                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- TURBINE_GROUP                                                      
----------------------------------------------------------------------

ALTER TABLE TURBINE_ROLE_PERMISSION
    ADD CONSTRAINT TURBINE_ROLE_PERMISSION_FK_1 FOREIGN KEY (ROLE_ID)
    REFERENCES TURBINE_ROLE (ROLE_ID)
    ON DELETE CASCADE 
;
ALTER TABLE TURBINE_ROLE_PERMISSION
    ADD CONSTRAINT TURBINE_ROLE_PERMISSION_FK_2 FOREIGN KEY (PERMISSION_ID)
    REFERENCES TURBINE_PERMISSION (PERMISSION_ID)
;

----------------------------------------------------------------------
-- TURBINE_ROLE_PERMISSION                                                      
----------------------------------------------------------------------


----------------------------------------------------------------------
-- TURBINE_USER                                                      
----------------------------------------------------------------------

ALTER TABLE TURBINE_USER_GROUP_ROLE
    ADD CONSTRAINT TURBINE_USER_GROUP_ROLE_FK_1 FOREIGN KEY (USER_ID)
    REFERENCES TURBINE_USER (USER_ID)
    ON DELETE CASCADE 
;
ALTER TABLE TURBINE_USER_GROUP_ROLE
    ADD CONSTRAINT TURBINE_USER_GROUP_ROLE_FK_2 FOREIGN KEY (GROUP_ID)
    REFERENCES TURBINE_GROUP (GROUP_ID)
;
ALTER TABLE TURBINE_USER_GROUP_ROLE
    ADD CONSTRAINT TURBINE_USER_GROUP_ROLE_FK_3 FOREIGN KEY (ROLE_ID)
    REFERENCES TURBINE_ROLE (ROLE_ID)
;

----------------------------------------------------------------------
-- TURBINE_USER_GROUP_ROLE                                                      
----------------------------------------------------------------------



INSERT INTO TURBINE_GROUP (GROUP_ID,GROUP_NAME)
    VALUES (1,'global');
    

INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME)
    VALUES (1,'admin_users');
    

INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME)
    VALUES (2,'homepage_access');
    

INSERT INTO TURBINE_ROLE (ROLE_ID,ROLE_NAME)
    VALUES (1,'turbine_root');
    

INSERT INTO TURBINE_ROLE (ROLE_ID,ROLE_NAME)
    VALUES (2,'Site Administrator');
    

INSERT INTO TURBINE_ROLE_PERMISSION (ROLE_ID,PERMISSION_ID)
    VALUES (1,1);
    

INSERT INTO TURBINE_ROLE_PERMISSION (ROLE_ID,PERMISSION_ID)
    VALUES (1,2);
    

INSERT INTO TURBINE_USER (USER_ID,LOGIN_NAME,PASSWORD_VALUE,FIRST_NAME,LAST_NAME)
    VALUES (1,'admin','admn00','Administrator','Adminic');
    

INSERT INTO TURBINE_USER_GROUP_ROLE (USER_ID,GROUP_ID,ROLE_ID)
    VALUES (1,1,1);
    

INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2010,'DINBOX_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2011,'DINBOX_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2020,'DOUTBOX_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2021,'DOUTBOX_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2030,'DNEWSLETTER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2031,'DNEWSLETTER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2040,'DRETURN_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2041,'DRETURN_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2050,'DSHIPMENT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2051,'DSHIPMENT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2060,'DORDER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2061,'DORDER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2070,'DCONTRACT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2071,'DCONTRACT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2080,'LOCATION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2081,'LOCATION_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2090,'DISTRIBUTOR_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2091,'DISTRIBUTOR_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1110,'PROJECT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1111,'PROJECT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1120,'PRODUCT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1121,'PRODUCT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1150,'LANGUAGE_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1151,'LANGUAGE_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1160,'COUNTRY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1161,'COUNTRY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1170,'REGION_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1171,'REGION_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2170,'SALES_DISTRICT_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2171,'SALES_DISTRICT_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2180,'SALES_AREA_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2181,'SALES_AREA_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2190,'COST_TYPE_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2191,'COST_TYPE_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1200,'UOM_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1201,'UOM_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1210,'VENDOR_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1211,'VENDOR_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2220,'DISTRIBUTOR_CATEGORY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2221,'DISTRIBUTOR_CATEGORY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1230,'PROJECT_CATEGORY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1231,'PROJECT_CATEGORY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1240,'PRODUCT_CATEGORY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1241,'PRODUCT_CATEGORY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2250,'LOCATION_CATEGORY_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (2251,'LOCATION_CATEGORY_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1260,'TURBINE_USER_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1261,'TURBINE_USER_MODIFY');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1270,'TURBINE_ROLE_VIEW');
INSERT INTO TURBINE_PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1271,'TURBINE_ROLE_MODIFY');

INSERT INTO TURBINE_SCHEDULED_JOB (SECOND, MINUTE, HOUR, WEEK_DAY, TASK) VALUES (0,15,-1,-1, 'Pop3Job');

grant all on turbine_scheduled_job to dreamuser;
grant all on turbine_role_permission to dreamuser;
grant all on turbine_permission to dreamuser;
grant all on turbine_group to dreamuser;
grant all on turbine_user_group_role to dreamuser;
grant all on turbine_role to dreamuser;
grant all on turbine_user to dreamuser;



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

CREATE SEQUENCE PROJECT_CATEGORY_SEQ minvalue 1001;

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

CREATE SEQUENCE PRODUCT_CATEGORY_SEQ minvalue 1001;

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

CREATE SEQUENCE DISTRIBUTOR_CATEGORY_SEQ minvalue 1001;

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

CREATE SEQUENCE LOCATION_CATEGORY_SEQ minvalue 1001;

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

CREATE SEQUENCE COST_TYPE_SEQ minvalue 1001;

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

CREATE SEQUENCE COUNTRY_SEQ minvalue 1001;

CREATE TABLE COUNTRY
(
                            COUNTRY_ID integer DEFAULT nextval('COUNTRY_SEQ') NOT NULL,
                            COUNTRY_NAME varchar (50) NOT NULL,
                            COUNTRY_CODE char (2) NOT NULL,
    PRIMARY KEY (COUNTRY_ID),
    CONSTRAINT COUNTRY_U_1 UNIQUE (COUNTRY_NAME),
    CONSTRAINT COUNTRY_U_2 UNIQUE (COUNTRY_CODE)
);


-----------------------------------------------------------------------------
-- REGION
-----------------------------------------------------------------------------
DROP TABLE REGION;
DROP SEQUENCE REGION_SEQ;

CREATE SEQUENCE REGION_SEQ minvalue 1001;

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

CREATE SEQUENCE SALES_AREA_SEQ minvalue 1001;

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

CREATE SEQUENCE SALES_DISTRICT_SEQ minvalue 1001;

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

CREATE SEQUENCE LANGUAGE_SEQ minvalue 1001;

CREATE TABLE LANGUAGE
(
                            LANGUAGE_ID integer DEFAULT nextval('LANGUAGE_SEQ') NOT NULL,
                            LANGUAGE_NAME varchar (50) NOT NULL,
                            LANGUAGE_CODE char (2) NOT NULL,
    PRIMARY KEY (LANGUAGE_ID),
    CONSTRAINT LANGUAGE_U_1 UNIQUE (LANGUAGE_NAME),
    CONSTRAINT LANGUAGE_U_2 UNIQUE (LANGUAGE_CODE)
);


-----------------------------------------------------------------------------
-- VENDOR
-----------------------------------------------------------------------------
DROP TABLE VENDOR;
DROP SEQUENCE VENDOR_SEQ;

CREATE SEQUENCE VENDOR_SEQ minvalue 1001;

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

CREATE SEQUENCE UOM_SEQ minvalue 1001;

CREATE TABLE UOM
(
                            UOM_ID integer DEFAULT nextval('UOM_SEQ') NOT NULL,
                            UOM_NAME varchar (50) NOT NULL,
                            UOM_CODE char (3) NOT NULL,
    PRIMARY KEY (UOM_ID),
    CONSTRAINT UOM_U_1 UNIQUE (UOM_NAME),
    CONSTRAINT UOM_U_2 UNIQUE (UOM_CODE)
);


-----------------------------------------------------------------------------
-- DISTRIBUTOR
-----------------------------------------------------------------------------
DROP TABLE DISTRIBUTOR;
DROP SEQUENCE DISTRIBUTOR_SEQ;

CREATE SEQUENCE DISTRIBUTOR_SEQ minvalue 1001;

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
    CONSTRAINT DISTRIBUTOR_U_1 UNIQUE (DISTRIBUTOR_CODE),
    CONSTRAINT DISTRIBUTOR_U_2 UNIQUE (DISTRIBUTOR_DISPLAY)
);
CREATE INDEX DISTRIBUTOR_I_1 ON DISTRIBUTOR (EMAIL);
CREATE INDEX DISTRIBUTOR_I_2 ON DISTRIBUTOR (CITY);


-----------------------------------------------------------------------------
-- LOCATION
-----------------------------------------------------------------------------
DROP TABLE LOCATION;
DROP SEQUENCE LOCATION_SEQ;

CREATE SEQUENCE LOCATION_SEQ minvalue 1001;

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
    CONSTRAINT LOCATION_U_1 UNIQUE (LOCATION_CODE),
    CONSTRAINT LOCATION_U_2 UNIQUE (LOCATION_DISPLAY)
);
CREATE INDEX LOCATION_I_2 ON LOCATION (CITY);


-----------------------------------------------------------------------------
-- PRODUCT
-----------------------------------------------------------------------------
DROP TABLE PRODUCT;
DROP SEQUENCE PRODUCT_SEQ;

CREATE SEQUENCE PRODUCT_SEQ minvalue 1001;

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
    CONSTRAINT PRODUCT_U_1 UNIQUE (PRODUCT_CODE),
    CONSTRAINT PRODUCT_U_2 UNIQUE (PRODUCT_DISPLAY)
);
CREATE INDEX PRODUCT_I_2 ON PRODUCT (BASE_PRICE);


-----------------------------------------------------------------------------
-- PROJECT
-----------------------------------------------------------------------------
DROP TABLE PROJECT;
DROP SEQUENCE PROJECT_SEQ;

CREATE SEQUENCE PROJECT_SEQ minvalue 1001;

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
    CONSTRAINT PROJECT_U_1 UNIQUE (PROJECT_CODE),
    CONSTRAINT PROJECT_U_2 UNIQUE (PROJECT_NAME)
);
CREATE INDEX PROJECT_I_1 ON PROJECT (START_DATE);
CREATE INDEX PROJECT_I_2 ON PROJECT (EXPENSES);
CREATE INDEX PROJECT_I_3 ON PROJECT (REVENUES);


-----------------------------------------------------------------------------
-- DCONTRACT
-----------------------------------------------------------------------------
DROP TABLE DCONTRACT;
DROP SEQUENCE DCONTRACT_SEQ;

CREATE SEQUENCE DCONTRACT_SEQ minvalue 1001;

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
CREATE INDEX DCONTRACT_I_1 ON DCONTRACT (ISSUED_DATE);
CREATE INDEX DCONTRACT_I_2 ON DCONTRACT (STATUS);


-----------------------------------------------------------------------------
-- DCONTRACT_ITEM
-----------------------------------------------------------------------------
DROP TABLE DCONTRACT_ITEM;
DROP SEQUENCE DCONTRACT_ITEM_SEQ;

CREATE SEQUENCE DCONTRACT_ITEM_SEQ minvalue 1001;

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
CREATE INDEX DCONTRACT_ITEM_I_1 ON DCONTRACT_ITEM (DCONTRACT_ID);
CREATE INDEX DCONTRACT_ITEM_I_2 ON DCONTRACT_ITEM (COST_TYPE_ID);


-----------------------------------------------------------------------------
-- DORDER
-----------------------------------------------------------------------------
DROP TABLE DORDER;
DROP SEQUENCE DORDER_SEQ;

CREATE SEQUENCE DORDER_SEQ minvalue 1001;

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
CREATE INDEX DORDER_I_1 ON DORDER (ISSUED_DATE);
CREATE INDEX DORDER_I_2 ON DORDER (STATUS);


-----------------------------------------------------------------------------
-- DORDER_ITEM
-----------------------------------------------------------------------------
DROP TABLE DORDER_ITEM;
DROP SEQUENCE DORDER_ITEM_SEQ;

CREATE SEQUENCE DORDER_ITEM_SEQ minvalue 1001;

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

CREATE SEQUENCE DSHIPMENT_SEQ minvalue 1001;

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
    CONSTRAINT DSHIPMENT_U_1 UNIQUE (DSHIPMENT_CODE),
    CONSTRAINT DSHIPMENT_U_2 UNIQUE (DORDER_ID)
);
CREATE INDEX DSHIPMENT_I_1 ON DSHIPMENT (ISSUED_DATE);
CREATE INDEX DSHIPMENT_I_2 ON DSHIPMENT (STATUS);


-----------------------------------------------------------------------------
-- DRETURN
-----------------------------------------------------------------------------
DROP TABLE DRETURN;
DROP SEQUENCE DRETURN_SEQ;

CREATE SEQUENCE DRETURN_SEQ minvalue 1001;

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
    CONSTRAINT DRETURN_U_1 UNIQUE (DRETURN_CODE),
    CONSTRAINT DRETURN_U_2 UNIQUE (DORDER_ID)
);
CREATE INDEX DRETURN_I_1 ON DRETURN (ISSUED_DATE);
CREATE INDEX DRETURN_I_2 ON DRETURN (STATUS);


-----------------------------------------------------------------------------
-- DINBOX_EVENT
-----------------------------------------------------------------------------
DROP TABLE DINBOX_EVENT;
DROP SEQUENCE DINBOX_EVENT_SEQ;

CREATE SEQUENCE DINBOX_EVENT_SEQ minvalue 1001;

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
CREATE INDEX DINBOX_EVENT_I_1 ON DINBOX_EVENT (ISSUED_DATE);
CREATE INDEX DINBOX_EVENT_I_2 ON DINBOX_EVENT (STATUS);
CREATE INDEX DINBOX_EVENT_I_3 ON DINBOX_EVENT (SUBJECT);


-----------------------------------------------------------------------------
-- DOUTBOX_EVENT
-----------------------------------------------------------------------------
DROP TABLE DOUTBOX_EVENT;
DROP SEQUENCE DOUTBOX_EVENT_SEQ;

CREATE SEQUENCE DOUTBOX_EVENT_SEQ minvalue 1001;

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
CREATE INDEX DOUTBOX_EVENT_I_1 ON DOUTBOX_EVENT (ISSUED_DATE);
CREATE INDEX DOUTBOX_EVENT_I_2 ON DOUTBOX_EVENT (STATUS);
CREATE INDEX DOUTBOX_EVENT_I_3 ON DOUTBOX_EVENT (SUBJECT);


-----------------------------------------------------------------------------
-- DNEWSLETTER
-----------------------------------------------------------------------------
DROP TABLE DNEWSLETTER;
DROP SEQUENCE DNEWSLETTER_SEQ;

CREATE SEQUENCE DNEWSLETTER_SEQ minvalue 1001;

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
CREATE INDEX DNEWSLETTER_I_1 ON DNEWSLETTER (ISSUED_DATE);
CREATE INDEX DNEWSLETTER_I_2 ON DNEWSLETTER (STATUS);
CREATE INDEX DNEWSLETTER_I_3 ON DNEWSLETTER (SUBJECT);


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
INSERT INTO DREAM_USER VALUES (1,	'admin','Welcome Admin',2110,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000);

INSERT INTO REGION (REGION_ID,REGION_NAME)
    VALUES (1000,' ---');
INSERT INTO REGION (REGION_ID,REGION_NAME)
    VALUES (999,' (*)');
INSERT INTO VENDOR (VENDOR_ID,VENDOR_NAME)
    VALUES (1000,' ---');
INSERT INTO VENDOR (VENDOR_ID,VENDOR_NAME)
    VALUES (999,' (*)');
INSERT INTO COUNTRY (COUNTRY_ID,COUNTRY_NAME,COUNTRY_CODE)
    VALUES (1000,' ---', ' -');
INSERT INTO COUNTRY (COUNTRY_ID,COUNTRY_NAME,COUNTRY_CODE)
    VALUES (999,' (*)', ' *');
INSERT INTO DISTRIBUTOR_CATEGORY (DISTRIBUTOR_CAT_ID,DISTRIBUTOR_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO DISTRIBUTOR_CATEGORY (DISTRIBUTOR_CAT_ID,DISTRIBUTOR_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO LOCATION_CATEGORY (LOCATION_CAT_ID,LOCATION_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO LOCATION_CATEGORY (LOCATION_CAT_ID,LOCATION_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO SALES_AREA (SALES_AREA_ID,SALES_AREA_NAME)
    VALUES (1000,' ---');
INSERT INTO SALES_AREA (SALES_AREA_ID,SALES_AREA_NAME)
    VALUES (999,' (*)');
INSERT INTO SALES_DISTRICT (SALES_DISTRICT_ID,SALES_AREA_ID,SALES_DISTRICT_NAME)
    VALUES (1000,1000,' ---');
INSERT INTO SALES_DISTRICT (SALES_DISTRICT_ID,SALES_AREA_ID,SALES_DISTRICT_NAME)
    VALUES (999,999,' (*)');
INSERT INTO PROJECT_CATEGORY (PROJECT_CAT_ID,PROJECT_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO PROJECT_CATEGORY (PROJECT_CAT_ID,PROJECT_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO PRODUCT_CATEGORY (PRODUCT_CAT_ID,PRODUCT_CAT_NAME)
    VALUES (1000,' ---');
INSERT INTO PRODUCT_CATEGORY (PRODUCT_CAT_ID,PRODUCT_CAT_NAME)
    VALUES (999,' (*)');
INSERT INTO LANGUAGE (LANGUAGE_ID,LANGUAGE_NAME,LANGUAGE_CODE)
    VALUES (1000,' ---', ' -');
INSERT INTO LANGUAGE (LANGUAGE_ID,LANGUAGE_NAME,LANGUAGE_CODE)
    VALUES (999,' (*)', ' *');
INSERT INTO UOM (UOM_ID,UOM_NAME,UOM_CODE)
    VALUES (1000,' ---', '---');
INSERT INTO UOM (UOM_ID,UOM_NAME,UOM_CODE)
    VALUES (999,' (*)', ' *');
INSERT INTO COST_TYPE (COST_TYPE_ID,COST_TYPE_NAME,COST_TYPE_FUNCTION)
    VALUES (1000,' ---', 1);
INSERT INTO COST_TYPE (COST_TYPE_ID,COST_TYPE_NAME,COST_TYPE_FUNCTION)
    VALUES (999,' (*)', 1);

    
    
INSERT INTO DISTRIBUTOR (DISTRIBUTOR_ID,DISTRIBUTOR_CODE, STATUS, DISTRIBUTOR_TYPE, DISTRIBUTOR_NAME_1, DISTRIBUTOR_DISPLAY, SEND_NEWS, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY) VALUES (1000,' ---', 1, 1, ' ---', ' ---', 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO DISTRIBUTOR (DISTRIBUTOR_ID,DISTRIBUTOR_CODE, STATUS, DISTRIBUTOR_TYPE, DISTRIBUTOR_NAME_1, DISTRIBUTOR_DISPLAY, SEND_NEWS, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY) VALUES (999,' (*)', 1, 1, ' (*)', ' (*)', 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO LOCATION (LOCATION_ID,LOCATION_CODE, STATUS, LOCATION_TYPE, LOCATION_NAME_1, LOCATION_DISPLAY, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY) VALUES (1000,' ---', 1, 1, ' ---', ' ---', '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO LOCATION (LOCATION_ID,LOCATION_CODE, STATUS, LOCATION_TYPE, LOCATION_NAME_1, LOCATION_DISPLAY, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY) VALUES (999,' (*)', 1, 1, ' (*)', ' (*)', '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
    

INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CODE, STATUS, PRODUCT_TYPE, PRODUCT_CAT_ID, PRODUCT_DESCRIPTION, PRODUCT_DISPLAY, BASE_PRICE, UOM_ID, SHOW_ON_PRICELIST, VENDOR_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---', 1, 1, 1000, ' ---', ' ---', 0, 1000, 0, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_CODE, STATUS, PRODUCT_TYPE, PRODUCT_CAT_ID, PRODUCT_DESCRIPTION, PRODUCT_DISPLAY, BASE_PRICE, UOM_ID, SHOW_ON_PRICELIST, VENDOR_ID, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (999,' (*)', 1, 1, 1000, ' (*)', ' (*)', 0, 1000, 0, 1000, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO PROJECT (PROJECT_ID,PROJECT_CODE, STATUS, PROJECT_CAT_ID, PROJECT_NAME, START_DATE, EXPENSES, REVENUES, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---', 1, 1000, ' ---', '1990-1-1', 0, 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');
INSERT INTO PROJECT (PROJECT_ID,PROJECT_CODE, STATUS, PROJECT_CAT_ID, PROJECT_NAME, START_DATE, EXPENSES, REVENUES, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (999,' (*)', 1, 1000, ' (*)', '1990-1-1', 0, 0, '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');


INSERT INTO DCONTRACT (DCONTRACT_ID,DCONTRACT_CODE, STATUS, ISSUED_DATE, START_DATE, END_DATE, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---', 1, '1990-1-1', '1990-1-1', '2190-1-1', '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');

INSERT INTO DORDER (DORDER_ID,DORDER_CODE, DESCRIPTION, STATUS, ISSUED_DATE, SALES_DATE, EXPECTED_TIME, CREATED, MODIFIED, CREATED_BY, MODIFIED_BY)
    VALUES (1000,' ---', ' ---', 1, '1990-1-1', '1990-1-1', '1990-1-1 00:00', '1990-1-1 00:00', '1990-1-1 00:00', 'system', 'system');



grant all on dream_user to dreamuser;
grant all on cost_type to dreamuser;
grant all on uom to dreamuser;
grant all on vendor to dreamuser;
grant all on project_category to dreamuser;
grant all on product_category to dreamuser;
grant all on sales_district to dreamuser;
grant all on sales_area to dreamuser;
grant all on location_category to dreamuser;
grant all on distributor_category to dreamuser;
grant all on region to dreamuser;
grant all on country to dreamuser;
grant all on language to dreamuser;
grant all on dinbox_event to dreamuser;
grant all on doutbox_event to dreamuser;
grant all on dnewsletter to dreamuser;
grant all on dreturn to dreamuser;
grant all on dshipment to dreamuser;
grant all on dorder_item to dreamuser;
grant all on dorder to dreamuser;
grant all on dcontract_item to dreamuser;
grant all on dcontract to dreamuser;
grant all on location to dreamuser;
grant all on distributor to dreamuser;
grant all on product to dreamuser;
grant all on project to dreamuser;

