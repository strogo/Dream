
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

CREATE SEQUENCE TURBINE_ROLE_SEQ;

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

CREATE SEQUENCE TURBINE_USER_SEQ;

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

