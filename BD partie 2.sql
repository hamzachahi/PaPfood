
-- Tablespace pour les données de table

CREATE TABLESPACE TBS_DATA,
DATAFILE '%ORACLE_BASE%\oracle\data\tbsdata.ora' , AUTOEXTEND ON,
EXTENT MANAGEMENT AUTOALLOCATE;

-- Tablespace pour les données d'index

CREATE TABLESPACE TBS_INDEX,
DATAFILE '%ORACLE_BASE%\oracle\data\tbsindex.ora' , AUTOEXTEND ON,
EXTENT MANAGEMENT AUTOALLOCATE;

-- Tablespace pour les segments temporaires

CREATE TEMPORARY TABLESPACE TBS_TEMP,
TEMPFILE '%ORACLE_BASE%\oracle\data\tbstemp.ora',
SIZE 20M,
EXTENT MANAGEMENT LOCAL UNIFORM SIZE 500K;

-- Création de l'utilisateur principal

CREATE USER youssef,
IDENTIFIED BY youssef2017,
DEFAULT TABLESPACE TBS_DATA, TBS_INDEX,
QUOTA UNLIMITED ON TBS_DATA,
TEMPORARY TABLESPACE TBS_TEMP;

-- Attributions des droits et rôles à l'utilisateur

GRANT CREATE SESSION to youssef WITH ADMIN OPTION
GRANT ALTER TABLESPACE to youssef,
GRANT CREATE ANY TABLE to youssef,

-- Création de toutes les tables et association des tablespace 


--------------------------------------------------------
--  Fichier créé - lundi-avril-24-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table COMMANDE
--------------------------------------------------------

  CREATE TABLE "youssef"."COMMANDE" 
   (	"ID" NUMBER(10,0), 
	"CODE" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"ID_CUSTOMER" NUMBER(10,0) DEFAULT NULL, 
	"DATE_ORDERING" TIMESTAMP (0) DEFAULT NULL, 
	"DATE_DELIVERED" TIMESTAMP (0) DEFAULT NULL, 
	"STATE" NUMBER(3,0) DEFAULT 0, 
	"SHIPPING_ADDRESS" VARCHAR2(50 BYTE), 
	"BILLING_ADDRESS" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table COMMANDE_PRODUCT
--------------------------------------------------------

  CREATE TABLE "youssef"."COMMANDE_PRODUCT" 
   (	"ID" NUMBER(10,0), 
	"ID_COMMANDE" NUMBER(10,0) DEFAULT NULL, 
	"ID_PRODUCT" NUMBER(10,0) DEFAULT NULL, 
	"QUANTITY" NUMBER(10,0) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table COMMANDE_SERVICE
--------------------------------------------------------

  CREATE TABLE "youssef"."COMMANDE_SERVICE" 
   (	"ID" NUMBER(10,0), 
	"ID_COMMANDE" NUMBER(10,0) DEFAULT NULL, 
	"ID_SERVICE" NUMBER(10,0) DEFAULT NULL, 
	"QUANTITY" NUMBER(10,0) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table COMMENTS
--------------------------------------------------------

  CREATE TABLE "youssef"."COMMENTS" 
   (	"ID" NUMBER(10,0), 
	"ID_AUTHOR" NUMBER(10,0) DEFAULT NULL, 
	"DATE_POSTED" TIMESTAMP (0) DEFAULT SYSTIMESTAMP, 
	"CONTENT" VARCHAR2(500 BYTE) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table COMMENTS_PRODUCT
--------------------------------------------------------

  CREATE TABLE "youssef"."COMMENTS_PRODUCT" 
   (	"ID" NUMBER(10,0), 
	"ID_COMMENT" NUMBER(10,0) DEFAULT NULL, 
	"ID_PRODUCT" NUMBER(10,0) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table COMMENTS_SERVICE
--------------------------------------------------------

  CREATE TABLE "youssef"."COMMENTS_SERVICE" 
   (	"ID" NUMBER(10,0), 
	"ID_COMMENT" NUMBER(10,0) DEFAULT NULL, 
	"ID_SERVICE" NUMBER(10,0) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table CONNECTION
--------------------------------------------------------

  CREATE TABLE "youssef"."CONNECTION" 
   (	"ID" NUMBER(10,0), 
	"LOGIN_TIME" TIMESTAMP (0) DEFAULT NULL, 
	"PERSON_ID" NUMBER(10,0) DEFAULT NULL, 
	"PERSON_ID_IP_ADDRESS" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"PERSON_TYPE" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"LOGOUT_TIME" TIMESTAMP (0) DEFAULT NULL
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table EVALUATION
--------------------------------------------------------

  CREATE TABLE "youssef"."EVALUATION" 
   (	"ID" NUMBER(10,0), 
	"ID_PERSON" NUMBER(10,0) DEFAULT NULL, 
	"ID_JURY" NUMBER(10,0) DEFAULT NULL, 
	"NOTE" BINARY_DOUBLE DEFAULT NULL, 
	"COMMENTS" VARCHAR2(500 BYTE) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table INVOICE
--------------------------------------------------------

  CREATE TABLE "youssef"."INVOICE" 
   (	"ID" NUMBER(10,0), 
	"CODE_INVOICE" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"CREATION_DATE" TIMESTAMP (0) DEFAULT NULL, 
	"DELIVERED_DATE" TIMESTAMP (0) DEFAULT NULL, 
	"TYPE" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"TOTAL_PRICE" BINARY_DOUBLE DEFAULT NULL, 
	"HEADER_MESSAGE" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"FOOTER_MESSAGE" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"LEGAL_MESSAGE" VARCHAR2(45 BYTE) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table INVOICE_PERSON
--------------------------------------------------------

  CREATE TABLE "youssef"."INVOICE_PERSON" 
   (	"ID" NUMBER(10,0), 
	"ID_INVOICE" NUMBER(10,0) DEFAULT NULL, 
	"ID_PERSON" NUMBER(10,0) DEFAULT NULL, 
	"PERSON_NAME" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"PERSON_ADDRESS" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"PERSON_SURNAME" VARCHAR2(45 BYTE) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table INVOICE_PRODUCT
--------------------------------------------------------

  CREATE TABLE "youssef"."INVOICE_PRODUCT" 
   (	"ID" NUMBER(10,0), 
	"ID_INVOICE" NUMBER(10,0) DEFAULT NULL, 
	"ID_PRODUCT" NUMBER(10,0) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table INVOICE_SERVICE
--------------------------------------------------------

  CREATE TABLE "youssef"."INVOICE_SERVICE" 
   (	"ID" NUMBER(10,0), 
	"ID_INVOICE" NUMBER(10,0) DEFAULT NULL, 
	"ID_SERVICE" NUMBER(10,0) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table MESSAGE
--------------------------------------------------------

  CREATE TABLE "youssef"."MESSAGE" 
   (	"ID" NUMBER(10,0), 
	"ID_SENDER" NUMBER(10,0) DEFAULT NULL, 
	"ID_RECEIVER" NUMBER(10,0) DEFAULT NULL, 
	"CONTENT" VARCHAR2(500 BYTE) DEFAULT NULL, 
	"SENT_DATE" TIMESTAMP (0) DEFAULT SYSTIMESTAMP, 
	"RECEIVE_DATE" TIMESTAMP (0) DEFAULT NULL, 
	"READ_DATE" TIMESTAMP (0) DEFAULT NULL
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table PERSON
--------------------------------------------------------

  CREATE TABLE "youssef"."PERSON" 
   (	"ID" NUMBER(*,0), 
	"NAME" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"SECOND_NAME" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"SURNAME" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"SECOND_SURNAME" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"PROFESSION" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"DATE_INSCRIPTION" TIMESTAMP (0) DEFAULT NULL, 
	"PASSWORD" VARCHAR2(200 BYTE) DEFAULT NULL, 
	"EMAIL" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"PHONE_NUMBER" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"TEL_NUMBER" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"FACEBOOK_ID" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"TWITTER_ID" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"INSTAGRAM_ID" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"LINKEDIN_ID" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"ACCOUNT_PICTURE" BLOB, 
	"STREET_NUMBER" NUMBER(10,0) DEFAULT NULL, 
	"STREET_NAME" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"CITY_NAME" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"COUNTRY_NAME" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"POSTAL_CODE" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"LAST_CONNECTION" NUMBER(10,0) DEFAULT NULL, 
	"FUNCTION" VARCHAR2(45 BYTE) DEFAULT NULL
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBS_DATA" 
 LOB ("ACCOUNT_PICTURE") STORE AS BASICFILE (
  TABLESPACE "TBS_DATA" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Table PRODUCT
--------------------------------------------------------

  CREATE TABLE "youssef"."PRODUCT" 
   (	"ID" NUMBER(10,0), 
	"CODE" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"NAME" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"DESCRIPTION" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"PRICE" BINARY_DOUBLE DEFAULT NULL, 
	"MAIN_IMAGE" BLOB, 
	"TYPE" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"ID_PROVIDER" NUMBER(20,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" 
 LOB ("MAIN_IMAGE") STORE AS BASICFILE (
  TABLESPACE "TBS_DATA" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING ) ;
--------------------------------------------------------
--  DDL for Table PRODUCT_COMPONENT
--------------------------------------------------------

  CREATE TABLE "youssef"."PRODUCT_COMPONENT" 
   (	"ID" NUMBER(10,0), 
	"ID_COMPONED" NUMBER(10,0) DEFAULT NULL, 
	"ID_COMPONENT" NUMBER(10,0) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table PRODUCT_SERVICE
--------------------------------------------------------

  CREATE TABLE "youssef"."PRODUCT_SERVICE" 
   (	"ID" NUMBER(10,0), 
	"ID_PRODUCT" NUMBER(10,0) DEFAULT NULL, 
	"ID_SERVICE" NUMBER(10,0) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table SERVICE
--------------------------------------------------------

  CREATE TABLE "youssef"."SERVICE" 
   (	"ID" NUMBER(10,0), 
	"CODE" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"NAME" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"DESCRIPTION" VARCHAR2(45 BYTE) DEFAULT NULL, 
	"PRICE" BINARY_DOUBLE DEFAULT NULL, 
	"MAIN_IMAGE" BLOB, 
	"ID_PROVIDER" NUMBER(20,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" 
 LOB ("MAIN_IMAGE") STORE AS BASICFILE (
  TABLESPACE "TBS_DATA" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING ) ;
--------------------------------------------------------
--  DDL for Table SERVICE_COMPONENT
--------------------------------------------------------

  CREATE TABLE "youssef"."SERVICE_COMPONENT" 
   (	"ID" NUMBER(10,0), 
	"ID_SERVICE" NUMBER(10,0) DEFAULT NULL, 
	"ID_COMPONENT" NUMBER(10,0) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Table SERVICE_PRODUCT
--------------------------------------------------------

  CREATE TABLE "youssef"."SERVICE_PRODUCT" 
   (	"ID" NUMBER(10,0), 
	"ID_SERVICE" NUMBER(10,0) DEFAULT NULL, 
	"ID_PRODUCT" NUMBER(10,0) DEFAULT NULL
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TBS_DATA" ;
--------------------------------------------------------
--  DDL for Sequence COMMANDE_PRODUCT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."COMMANDE_PRODUCT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence COMMANDE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."COMMANDE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence COMMANDE_SERVICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."COMMANDE_SERVICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence COMMENTS_PRODUCT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."COMMENTS_PRODUCT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence COMMENTS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."COMMENTS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence COMMENTS_SERVICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."COMMENTS_SERVICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence CONNECTION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."CONNECTION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence EVALUATION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."EVALUATION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence INVOICE_PERSON_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."INVOICE_PERSON_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence INVOICE_PRODUCT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."INVOICE_PRODUCT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence INVOICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."INVOICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence INVOICE_SERVICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."INVOICE_SERVICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence MESSAGE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."MESSAGE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 22 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence ORDER_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."ORDER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PERSON_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."PERSON_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 101 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PRODUCT_COMPONENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."PRODUCT_COMPONENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PRODUCT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."PRODUCT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PRODUCT_SERVICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."PRODUCT_SERVICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SERVICE_COMPONENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."SERVICE_COMPONENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SERVICE_PRODUCT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."SERVICE_PRODUCT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SERVICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "youssef"."SERVICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
REM INSERTING into youssef.COMMANDE
SET DEFINE OFF;
REM INSERTING into youssef.COMMANDE_PRODUCT
SET DEFINE OFF;
REM INSERTING into youssef.COMMANDE_SERVICE
SET DEFINE OFF;
REM INSERTING into youssef.COMMENTS
SET DEFINE OFF;
REM INSERTING into youssef.COMMENTS_PRODUCT
SET DEFINE OFF;
REM INSERTING into youssef.COMMENTS_SERVICE
SET DEFINE OFF;
REM INSERTING into youssef.CONNECTION
SET DEFINE OFF;
Insert into youssef.CONNECTION (ID,LOGIN_TIME,PERSON_ID,PERSON_ID_IP_ADDRESS,PERSON_TYPE,LOGOUT_TIME) values ('1',to_timestamp('16/04/17 12:57:01,000000000','DD/MM/RR HH24:MI:SSXFF'),'71','Noubissi','Mbengue',to_timestamp('16/04/17 12:57:25,000000000','DD/MM/RR HH24:MI:SSXFF'));
REM INSERTING into youssef.EVALUATION
SET DEFINE OFF;
REM INSERTING into youssef.INVOICE
SET DEFINE OFF;
REM INSERTING into youssef.INVOICE_PERSON
SET DEFINE OFF;
REM INSERTING into youssef.INVOICE_PRODUCT
SET DEFINE OFF;
REM INSERTING into youssef.INVOICE_SERVICE
SET DEFINE OFF;
REM INSERTING into youssef.MESSAGE
SET DEFINE OFF;
Insert into youssef.MESSAGE (ID,ID_SENDER,ID_RECEIVER,CONTENT,SENT_DATE,RECEIVE_DATE,READ_DATE) values ('2','71','59','blablabla',to_timestamp('17/04/17 05:43:09,000000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('17/04/17 07:48:52,000000000','DD/MM/RR HH24:MI:SSXFF'),null);
REM INSERTING into youssef.PERSON
SET DEFINE OFF;
Insert into youssef.PERSON (ID,NAME,SECOND_NAME,SURNAME,SECOND_SURNAME,PROFESSION,DATE_INSCRIPTION,PASSWORD,EMAIL,PHONE_NUMBER,TEL_NUMBER,FACEBOOK_ID,TWITTER_ID,INSTAGRAM_ID,LINKEDIN_ID,STREET_NUMBER,STREET_NAME,CITY_NAME,COUNTRY_NAME,POSTAL_CODE,LAST_CONNECTION,FUNCTION) values ('63','maf',null,null,null,null,to_timestamp('10/04/17 02:31:57,000000000','DD/MM/RR HH24:MI:SSXFF'),'maf','maf@maf.com',null,null,null,null,null,null,null,null,null,null,null,null,'admin');
Insert into youssef.PERSON (ID,NAME,SECOND_NAME,SURNAME,SECOND_SURNAME,PROFESSION,DATE_INSCRIPTION,PASSWORD,EMAIL,PHONE_NUMBER,TEL_NUMBER,FACEBOOK_ID,TWITTER_ID,INSTAGRAM_ID,LINKEDIN_ID,STREET_NUMBER,STREET_NAME,CITY_NAME,COUNTRY_NAME,POSTAL_CODE,LAST_CONNECTION,FUNCTION) values ('81','Testmaf',null,null,null,null,to_timestamp('16/04/17 16:17:47,000000000','DD/MM/RR HH24:MI:SSXFF'),'POdUMaBBOwKonfSiG2rFipzRgfdtzffaSx3jjnX6j3jzZjCeTVGE7g==','test@test.com',null,null,null,null,null,null,null,null,null,null,null,null,'User');
Insert into youssef.PERSON (ID,NAME,SECOND_NAME,SURNAME,SECOND_SURNAME,PROFESSION,DATE_INSCRIPTION,PASSWORD,EMAIL,PHONE_NUMBER,TEL_NUMBER,FACEBOOK_ID,TWITTER_ID,INSTAGRAM_ID,LINKEDIN_ID,STREET_NUMBER,STREET_NAME,CITY_NAME,COUNTRY_NAME,POSTAL_CODE,LAST_CONNECTION,FUNCTION) values ('87','Testmafazertyqsdfgui',null,null,null,null,to_timestamp('16/04/17 16:52:26,000000000','DD/MM/RR HH24:MI:SSXFF'),'VcyYm02YSdwY6Zsrlv3MJV0F4ziriFdXJEitd5we83ROADKSJm8xTg==','azerty@gmailazetyui.com',null,null,null,null,null,null,null,null,null,null,null,null,'User');
Insert into youssef.PERSON (ID,NAME,SECOND_NAME,SURNAME,SECOND_SURNAME,PROFESSION,DATE_INSCRIPTION,PASSWORD,EMAIL,PHONE_NUMBER,TEL_NUMBER,FACEBOOK_ID,TWITTER_ID,INSTAGRAM_ID,LINKEDIN_ID,STREET_NUMBER,STREET_NAME,CITY_NAME,COUNTRY_NAME,POSTAL_CODE,LAST_CONNECTION,FUNCTION) values ('57','maf',null,null,null,null,to_timestamp('10/04/17 02:03:06,000000000','DD/MM/RR HH24:MI:SSXFF'),'maf','maf@maf.com',null,null,null,null,null,null,null,null,null,null,null,null,'admin');
Insert into youssef.PERSON (ID,NAME,SECOND_NAME,SURNAME,SECOND_SURNAME,PROFESSION,DATE_INSCRIPTION,PASSWORD,EMAIL,PHONE_NUMBER,TEL_NUMBER,FACEBOOK_ID,TWITTER_ID,INSTAGRAM_ID,LINKEDIN_ID,STREET_NUMBER,STREET_NAME,CITY_NAME,COUNTRY_NAME,POSTAL_CODE,LAST_CONNECTION,FUNCTION) values ('64','rat',null,null,null,null,to_timestamp('10/04/17 03:39:27,000000000','DD/MM/RR HH24:MI:SSXFF'),'dZx57Z+UinUwXR5/xSyGw/dk/y9UeGmaB8kM7N0Xz+zMZWkWkKFnzg==','rat@rat.com',null,null,null,null,null,null,null,null,null,null,null,null,'User');
Insert into youssef.PERSON (ID,NAME,SECOND_NAME,SURNAME,SECOND_SURNAME,PROFESSION,DATE_INSCRIPTION,PASSWORD,EMAIL,PHONE_NUMBER,TEL_NUMBER,FACEBOOK_ID,TWITTER_ID,INSTAGRAM_ID,LINKEDIN_ID,STREET_NUMBER,STREET_NAME,CITY_NAME,COUNTRY_NAME,POSTAL_CODE,LAST_CONNECTION,FUNCTION) values ('71',null,null,null,null,null,to_timestamp('10/04/17 13:45:58,000000000','DD/MM/RR HH24:MI:SSXFF'),'Ap3WAywSu0ZFZHreZGPW4B/U+43L5uTk7C38JfA+xRCJjwJZ4pfaNg==',null,null,null,null,null,null,null,null,null,'Nice','France',null,'1','Admin');
Insert into youssef.PERSON (ID,NAME,SECOND_NAME,SURNAME,SECOND_SURNAME,PROFESSION,DATE_INSCRIPTION,PASSWORD,EMAIL,PHONE_NUMBER,TEL_NUMBER,FACEBOOK_ID,TWITTER_ID,INSTAGRAM_ID,LINKEDIN_ID,STREET_NUMBER,STREET_NAME,CITY_NAME,COUNTRY_NAME,POSTAL_CODE,LAST_CONNECTION,FUNCTION) values ('59','maf',null,null,null,null,to_timestamp('10/04/17 02:15:18,000000000','DD/MM/RR HH24:MI:SSXFF'),'maf','maf@maf.com',null,null,null,null,null,null,null,null,null,null,null,null,'admin');
Insert into youssef.PERSON (ID,NAME,SECOND_NAME,SURNAME,SECOND_SURNAME,PROFESSION,DATE_INSCRIPTION,PASSWORD,EMAIL,PHONE_NUMBER,TEL_NUMBER,FACEBOOK_ID,TWITTER_ID,INSTAGRAM_ID,LINKEDIN_ID,STREET_NUMBER,STREET_NAME,CITY_NAME,COUNTRY_NAME,POSTAL_CODE,LAST_CONNECTION,FUNCTION) values ('60','maf',null,null,null,null,to_timestamp('10/04/17 02:16:30,000000000','DD/MM/RR HH24:MI:SSXFF'),'maf','maf@maf.com',null,null,null,null,null,null,null,null,null,null,null,null,'admin');
Insert into youssef.PERSON (ID,NAME,SECOND_NAME,SURNAME,SECOND_SURNAME,PROFESSION,DATE_INSCRIPTION,PASSWORD,EMAIL,PHONE_NUMBER,TEL_NUMBER,FACEBOOK_ID,TWITTER_ID,INSTAGRAM_ID,LINKEDIN_ID,STREET_NUMBER,STREET_NAME,CITY_NAME,COUNTRY_NAME,POSTAL_CODE,LAST_CONNECTION,FUNCTION) values ('62','maf',null,null,null,null,to_timestamp('10/04/17 02:29:30,000000000','DD/MM/RR HH24:MI:SSXFF'),'maf','maf@maf.com',null,null,null,null,null,null,null,null,null,null,null,null,'admin');
REM INSERTING into youssef.PRODUCT
SET DEFINE OFF;
REM INSERTING into youssef.PRODUCT_COMPONENT
SET DEFINE OFF;
REM INSERTING into youssef.PRODUCT_SERVICE
SET DEFINE OFF;
REM INSERTING into youssef.SERVICE
SET DEFINE OFF;
REM INSERTING into youssef.SERVICE_COMPONENT
SET DEFINE OFF;
REM INSERTING into youssef.SERVICE_PRODUCT
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Trigger COMMANDE_PRODUCT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."COMMANDE_PRODUCT_SEQ_TR" 
 BEFORE INSERT ON youssef.commande_product FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.commande_product_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."COMMANDE_PRODUCT_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger COMMANDE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."COMMANDE_SEQ_TR" 
 BEFORE INSERT ON youssef.commande FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.commande_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."COMMANDE_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger COMMANDE_SERVICE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."COMMANDE_SERVICE_SEQ_TR" 
 BEFORE INSERT ON youssef.commande_service FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.commande_service_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."COMMANDE_SERVICE_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger COMMENTS_PRODUCT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."COMMENTS_PRODUCT_SEQ_TR" 
 BEFORE INSERT ON youssef.comments_product FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.comments_product_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."COMMENTS_PRODUCT_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger COMMENTS_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."COMMENTS_SEQ_TR" 
 BEFORE INSERT ON youssef.comments FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.comments_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."COMMENTS_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger COMMENTS_SERVICE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."COMMENTS_SERVICE_SEQ_TR" 
 BEFORE INSERT ON youssef.comments_service FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.comments_service_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."COMMENTS_SERVICE_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CONNECTION_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."CONNECTION_SEQ_TR" 
 BEFORE INSERT ON youssef.connection FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.connection_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."CONNECTION_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger EVALUATION_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."EVALUATION_SEQ_TR" 
 BEFORE INSERT ON youssef.evaluation FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.evaluation_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."EVALUATION_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger INVOICE_PERSON_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."INVOICE_PERSON_SEQ_TR" 
 BEFORE INSERT ON youssef.invoice_person FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.invoice_person_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."INVOICE_PERSON_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger INVOICE_PRODUCT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."INVOICE_PRODUCT_SEQ_TR" 
 BEFORE INSERT ON youssef.invoice_product FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.invoice_product_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."INVOICE_PRODUCT_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger INVOICE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."INVOICE_SEQ_TR" 
 BEFORE INSERT ON youssef.invoice FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.invoice_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."INVOICE_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger INVOICE_SERVICE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."INVOICE_SERVICE_SEQ_TR" 
 BEFORE INSERT ON youssef.invoice_service FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.invoice_service_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;

/
ALTER TRIGGER "youssef"."INVOICE_SERVICE_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger MESSAGE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."MESSAGE_SEQ_TR" 
 BEFORE INSERT ON youssef.message FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.message_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;

/
ALTER TRIGGER "youssef"."MESSAGE_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PERSON_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."PERSON_SEQ_TR" 
 BEFORE INSERT ON youssef.person FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.person_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."PERSON_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PRODUCT_COMPONENT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."PRODUCT_COMPONENT_SEQ_TR" 
 BEFORE INSERT ON youssef.product_component FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.product_component_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."PRODUCT_COMPONENT_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PRODUCT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."PRODUCT_SEQ_TR" 
 BEFORE INSERT ON youssef.product FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.product_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."PRODUCT_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PRODUCT_SERVICE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."PRODUCT_SERVICE_SEQ_TR" 
 BEFORE INSERT ON youssef.product_service FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.product_service_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."PRODUCT_SERVICE_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger SERVICE_COMPONENT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."SERVICE_COMPONENT_SEQ_TR" 
 BEFORE INSERT ON youssef.service_component FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.service_component_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."SERVICE_COMPONENT_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger SERVICE_PRODUCT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."SERVICE_PRODUCT_SEQ_TR" 
 BEFORE INSERT ON youssef.service_product FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.service_product_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."SERVICE_PRODUCT_SEQ_TR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger SERVICE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "youssef"."SERVICE_SEQ_TR" 
 BEFORE INSERT ON youssef.service FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT youssef.service_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
/
ALTER TRIGGER "youssef"."SERVICE_SEQ_TR" ENABLE;
--------------------------------------------------------
--  Constraints for Table INVOICE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "youssef"."INVOICE_PRODUCT" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."INVOICE_PRODUCT" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_PRODUCT" ADD CHECK (id_invoice > 0) ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_PRODUCT" ADD CHECK (id_product > 0) ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_PRODUCT" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table COMMENTS_PRODUCT
--------------------------------------------------------

  ALTER TABLE "youssef"."COMMENTS_PRODUCT" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."COMMENTS_PRODUCT" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMENTS_PRODUCT" ADD CHECK (id_comment > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMENTS_PRODUCT" ADD CHECK (id_product > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMENTS_PRODUCT" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table EVALUATION
--------------------------------------------------------

  ALTER TABLE "youssef"."EVALUATION" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."EVALUATION" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."EVALUATION" ADD CHECK (id_person > 0) ENABLE;
 
  ALTER TABLE "youssef"."EVALUATION" ADD CHECK (id_jury > 0) ENABLE;
 
  ALTER TABLE "youssef"."EVALUATION" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table COMMANDE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "youssef"."COMMANDE_PRODUCT" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."COMMANDE_PRODUCT" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE_PRODUCT" ADD CHECK (id_commande > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE_PRODUCT" ADD CHECK (id_product > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE_PRODUCT" ADD CHECK (quantity > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE_PRODUCT" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table PRODUCT_SERVICE
--------------------------------------------------------

  ALTER TABLE "youssef"."PRODUCT_SERVICE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."PRODUCT_SERVICE" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."PRODUCT_SERVICE" ADD CHECK (id_product > 0) ENABLE;
 
  ALTER TABLE "youssef"."PRODUCT_SERVICE" ADD CHECK (id_service > 0) ENABLE;
 
  ALTER TABLE "youssef"."PRODUCT_SERVICE" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table COMMANDE
--------------------------------------------------------

  ALTER TABLE "youssef"."COMMANDE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."COMMANDE" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE" ADD CHECK (id_customer > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE" MODIFY ("STATE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table COMMANDE_SERVICE
--------------------------------------------------------

  ALTER TABLE "youssef"."COMMANDE_SERVICE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."COMMANDE_SERVICE" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE_SERVICE" ADD CHECK (id_commande > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE_SERVICE" ADD CHECK (id_service > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE_SERVICE" ADD CHECK (quantity > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE_SERVICE" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table COMMENTS_SERVICE
--------------------------------------------------------

  ALTER TABLE "youssef"."COMMENTS_SERVICE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."COMMENTS_SERVICE" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMENTS_SERVICE" ADD CHECK (id_comment > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMENTS_SERVICE" ADD CHECK (id_service > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMENTS_SERVICE" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table INVOICE_SERVICE
--------------------------------------------------------

  ALTER TABLE "youssef"."INVOICE_SERVICE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."INVOICE_SERVICE" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_SERVICE" ADD CHECK (id_invoice > 0) ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_SERVICE" ADD CHECK (id_service > 0) ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_SERVICE" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table INVOICE_PERSON
--------------------------------------------------------

  ALTER TABLE "youssef"."INVOICE_PERSON" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."INVOICE_PERSON" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_PERSON" ADD CHECK (id_invoice > 0) ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_PERSON" ADD CHECK (id_person > 0) ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_PERSON" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table INVOICE
--------------------------------------------------------

  ALTER TABLE "youssef"."INVOICE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."INVOICE" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."INVOICE" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table COMMENTS
--------------------------------------------------------

  ALTER TABLE "youssef"."COMMENTS" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."COMMENTS" MODIFY ("DATE_POSTED" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."COMMENTS" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMENTS" ADD CHECK (id_author > 0) ENABLE;
 
  ALTER TABLE "youssef"."COMMENTS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CONNECTION
--------------------------------------------------------

  ALTER TABLE "youssef"."CONNECTION" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."CONNECTION" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."CONNECTION" ADD CHECK (person_id > 0) ENABLE;
 
  ALTER TABLE "youssef"."CONNECTION" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "youssef"."PERSON" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."PERSON" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."PERSON" ADD CHECK (street_number > 0) ENABLE;
 
  ALTER TABLE "youssef"."PERSON" ADD CHECK (last_connection > 0) ENABLE;
 
  ALTER TABLE "youssef"."PERSON" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SERVICE_COMPONENT
--------------------------------------------------------

  ALTER TABLE "youssef"."SERVICE_COMPONENT" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."SERVICE_COMPONENT" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."SERVICE_COMPONENT" ADD CHECK (id_service > 0) ENABLE;
 
  ALTER TABLE "youssef"."SERVICE_COMPONENT" ADD CHECK (id_component > 0) ENABLE;
 
  ALTER TABLE "youssef"."SERVICE_COMPONENT" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SERVICE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "youssef"."SERVICE_PRODUCT" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."SERVICE_PRODUCT" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."SERVICE_PRODUCT" ADD CHECK (id_service > 0) ENABLE;
 
  ALTER TABLE "youssef"."SERVICE_PRODUCT" ADD CHECK (id_product > 0) ENABLE;
 
  ALTER TABLE "youssef"."SERVICE_PRODUCT" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table MESSAGE
--------------------------------------------------------

  ALTER TABLE "youssef"."MESSAGE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."MESSAGE" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."MESSAGE" ADD CHECK (id_sender > 0) ENABLE;
 
  ALTER TABLE "youssef"."MESSAGE" ADD CHECK (id_receiver > 0) ENABLE;
 
  ALTER TABLE "youssef"."MESSAGE" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SERVICE
--------------------------------------------------------

  ALTER TABLE "youssef"."SERVICE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."SERVICE" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."SERVICE" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table PRODUCT
--------------------------------------------------------

  ALTER TABLE "youssef"."PRODUCT" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."PRODUCT" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."PRODUCT" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Constraints for Table PRODUCT_COMPONENT
--------------------------------------------------------

  ALTER TABLE "youssef"."PRODUCT_COMPONENT" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "youssef"."PRODUCT_COMPONENT" ADD CHECK (Id > 0) ENABLE;
 
  ALTER TABLE "youssef"."PRODUCT_COMPONENT" ADD CHECK (id_componed > 0) ENABLE;
 
  ALTER TABLE "youssef"."PRODUCT_COMPONENT" ADD CHECK (id_component > 0) ENABLE;
 
  ALTER TABLE "youssef"."PRODUCT_COMPONENT" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TBS_INDEX"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table COMMANDE
--------------------------------------------------------

  ALTER TABLE "youssef"."COMMANDE" ADD CONSTRAINT "FK_COMMANDE_1" FOREIGN KEY ("ID_CUSTOMER")
	  REFERENCES "youssef"."PERSON" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table COMMANDE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "youssef"."COMMANDE_PRODUCT" ADD CONSTRAINT "FK_COMMANDE_PRODUCT_1" FOREIGN KEY ("ID_COMMANDE")
	  REFERENCES "youssef"."COMMANDE" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE_PRODUCT" ADD CONSTRAINT "FK_COMMANDE_PRODUCT_2" FOREIGN KEY ("ID_PRODUCT")
	  REFERENCES "youssef"."PRODUCT" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table COMMANDE_SERVICE
--------------------------------------------------------

  ALTER TABLE "youssef"."COMMANDE_SERVICE" ADD CONSTRAINT "FK_COMMANDE_SERVICE_1" FOREIGN KEY ("ID_COMMANDE")
	  REFERENCES "youssef"."COMMANDE" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."COMMANDE_SERVICE" ADD CONSTRAINT "FK_COMMANDE_SERVICE_2" FOREIGN KEY ("ID_SERVICE")
	  REFERENCES "youssef"."SERVICE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table COMMENTS_PRODUCT
--------------------------------------------------------

  ALTER TABLE "youssef"."COMMENTS_PRODUCT" ADD CONSTRAINT "FK_COMMENTS_PRODUCT_1" FOREIGN KEY ("ID_COMMENT")
	  REFERENCES "youssef"."COMMENTS" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."COMMENTS_PRODUCT" ADD CONSTRAINT "FK_COMMENTS_PRODUCT_2" FOREIGN KEY ("ID_PRODUCT")
	  REFERENCES "youssef"."PRODUCT" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table COMMENTS_SERVICE
--------------------------------------------------------

  ALTER TABLE "youssef"."COMMENTS_SERVICE" ADD CONSTRAINT "FK_COMMENTS_SERVICE_1" FOREIGN KEY ("ID_COMMENT")
	  REFERENCES "youssef"."COMMENTS" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."COMMENTS_SERVICE" ADD CONSTRAINT "FK_COMMENTS_SERVICE_2" FOREIGN KEY ("ID_SERVICE")
	  REFERENCES "youssef"."SERVICE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CONNECTION
--------------------------------------------------------

  ALTER TABLE "youssef"."CONNECTION" ADD CONSTRAINT "FK_CONNECTION_1" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "youssef"."PERSON" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EVALUATION
--------------------------------------------------------

  ALTER TABLE "youssef"."EVALUATION" ADD CONSTRAINT "FK_EVALUATION_1" FOREIGN KEY ("ID_PERSON")
	  REFERENCES "youssef"."PERSON" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."EVALUATION" ADD CONSTRAINT "FK_EVALUATION_2" FOREIGN KEY ("ID_JURY")
	  REFERENCES "youssef"."PERSON" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table INVOICE_PERSON
--------------------------------------------------------

  ALTER TABLE "youssef"."INVOICE_PERSON" ADD CONSTRAINT "FK_INVOICE_PERSON_1" FOREIGN KEY ("ID_INVOICE")
	  REFERENCES "youssef"."INVOICE" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_PERSON" ADD CONSTRAINT "FK_INVOICE_PERSON_2" FOREIGN KEY ("ID_PERSON")
	  REFERENCES "youssef"."PERSON" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table INVOICE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "youssef"."INVOICE_PRODUCT" ADD CONSTRAINT "FK_INVOICE_PRODUCT_1" FOREIGN KEY ("ID_INVOICE")
	  REFERENCES "youssef"."INVOICE" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_PRODUCT" ADD CONSTRAINT "FK_INVOICE_PRODUCT_2" FOREIGN KEY ("ID_PRODUCT")
	  REFERENCES "youssef"."PRODUCT" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table INVOICE_SERVICE
--------------------------------------------------------

  ALTER TABLE "youssef"."INVOICE_SERVICE" ADD CONSTRAINT "FK_INVOICE_SERVICE_1" FOREIGN KEY ("ID_INVOICE")
	  REFERENCES "youssef"."INVOICE" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."INVOICE_SERVICE" ADD CONSTRAINT "FK_INVOICE_SERVICE_2" FOREIGN KEY ("ID_SERVICE")
	  REFERENCES "youssef"."SERVICE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MESSAGE
--------------------------------------------------------

  ALTER TABLE "youssef"."MESSAGE" ADD CONSTRAINT "FK_MESSAGE_1" FOREIGN KEY ("ID_SENDER")
	  REFERENCES "youssef"."PERSON" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."MESSAGE" ADD CONSTRAINT "FK_MESSAGE_2" FOREIGN KEY ("ID_RECEIVER")
	  REFERENCES "youssef"."PERSON" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "youssef"."PERSON" ADD CONSTRAINT "FK_PERSON_1" FOREIGN KEY ("LAST_CONNECTION")
	  REFERENCES "youssef"."CONNECTION" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PRODUCT
--------------------------------------------------------

  ALTER TABLE "youssef"."PRODUCT" ADD CONSTRAINT "FK_PRODUCT_1" FOREIGN KEY ("ID_PROVIDER")
	  REFERENCES "youssef"."PERSON" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PRODUCT_COMPONENT
--------------------------------------------------------

  ALTER TABLE "youssef"."PRODUCT_COMPONENT" ADD CONSTRAINT "FK_PRODUCT_COMPONENT_1" FOREIGN KEY ("ID_COMPONED")
	  REFERENCES "youssef"."PRODUCT" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."PRODUCT_COMPONENT" ADD CONSTRAINT "FK_PRODUCT_COMPONENT_2" FOREIGN KEY ("ID_COMPONENT")
	  REFERENCES "youssef"."PRODUCT" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PRODUCT_SERVICE
--------------------------------------------------------

  ALTER TABLE "youssef"."PRODUCT_SERVICE" ADD CONSTRAINT "FK_PRODUCT_SERVICE_1" FOREIGN KEY ("ID_PRODUCT")
	  REFERENCES "youssef"."PRODUCT" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."PRODUCT_SERVICE" ADD CONSTRAINT "FK_PRODUCT_SERVICE_2" FOREIGN KEY ("ID_SERVICE")
	  REFERENCES "youssef"."SERVICE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SERVICE
--------------------------------------------------------

  ALTER TABLE "youssef"."SERVICE" ADD CONSTRAINT "FK_SERVICE_1" FOREIGN KEY ("ID_PROVIDER")
	  REFERENCES "youssef"."PERSON" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SERVICE_COMPONENT
--------------------------------------------------------

  ALTER TABLE "youssef"."SERVICE_COMPONENT" ADD CONSTRAINT "FK_SERVICE_COMPONENT_1" FOREIGN KEY ("ID_SERVICE")
	  REFERENCES "youssef"."SERVICE" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."SERVICE_COMPONENT" ADD CONSTRAINT "FK_SERVICE_COMPONENT_2" FOREIGN KEY ("ID_COMPONENT")
	  REFERENCES "youssef"."SERVICE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SERVICE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "youssef"."SERVICE_PRODUCT" ADD CONSTRAINT "FK_SERVICE_PRODUCT_1" FOREIGN KEY ("ID_SERVICE")
	  REFERENCES "youssef"."SERVICE" ("ID") ENABLE;
 
  ALTER TABLE "youssef"."SERVICE_PRODUCT" ADD CONSTRAINT "FK_SERVICE_PRODUCT_2" FOREIGN KEY ("ID_PRODUCT")
	  REFERENCES "youssef"."PRODUCT" ("ID") ENABLE;




