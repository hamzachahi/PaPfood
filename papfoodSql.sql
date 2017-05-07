--------------------------------------------------------
--  Fichier crÃ©Ã© - samedi-avril-01-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table COMMANDE
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."COMMANDE" ("ID" NUMBER(10,0), "CODE" VARCHAR2(45 BYTE) DEFAULT NULL, "ID_CUSTOMER" NUMBER(10,0) DEFAULT NULL, "DATE_ORDERING" TIMESTAMP (0) DEFAULT NULL, "DATE_DELIVERED" TIMESTAMP (0) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table COMMANDE_PRODUCT
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."COMMANDE_PRODUCT" ("ID" NUMBER(10,0), "ID_COMMANDE" NUMBER(10,0) DEFAULT NULL, "ID_PRODUCT" NUMBER(10,0) DEFAULT NULL, "QUANTITY" NUMBER(10,0) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table COMMANDE_SERVICE
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."COMMANDE_SERVICE" ("ID" NUMBER(10,0), "ID_COMMANDE" NUMBER(10,0) DEFAULT NULL, "ID_SERVICE" NUMBER(10,0) DEFAULT NULL, "QUANTITY" NUMBER(10,0) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table CONNECTION
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."CONNECTION" ("ID" NUMBER(10,0), "LOGIN_TIME" TIMESTAMP (0) DEFAULT NULL, "PERSON_ID" NUMBER(10,0) DEFAULT NULL, "PERSON_ID_IP_ADDRESS" VARCHAR2(45 BYTE) DEFAULT NULL, "PERSON_TYPE" VARCHAR2(45 BYTE) DEFAULT NULL, "LOGOUT_TIME" TIMESTAMP (0) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table EVALUATION
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."EVALUATION" ("ID" NUMBER(10,0), "ID_PERSON" NUMBER(10,0) DEFAULT NULL, "ID_JURY" NUMBER(10,0) DEFAULT NULL, "NOTE" BINARY_DOUBLE DEFAULT NULL, "COMMENTS" VARCHAR2(500 BYTE) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table INVOICE
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."INVOICE" ("ID" NUMBER(10,0), "CODE_INVOICE" VARCHAR2(45 BYTE) DEFAULT NULL, "CREATION_DATE" TIMESTAMP (0) DEFAULT NULL, "DELIVERED_DATE" TIMESTAMP (0) DEFAULT NULL, "TYPE" VARCHAR2(45 BYTE) DEFAULT NULL, "TOTAL_PRICE" BINARY_DOUBLE DEFAULT NULL, "HEADER_MESSAGE" VARCHAR2(45 BYTE) DEFAULT NULL, "FOOTER_MESSAGE" VARCHAR2(45 BYTE) DEFAULT NULL, "LEGAL_MESSAGE" VARCHAR2(45 BYTE) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table INVOICE_PERSON
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."INVOICE_PERSON" ("ID" NUMBER(10,0), "ID_INVOICE" NUMBER(10,0) DEFAULT NULL, "ID_PERSON" NUMBER(10,0) DEFAULT NULL, "PERSON_NAME" VARCHAR2(45 BYTE) DEFAULT NULL, "PERSON_ADDRESS" VARCHAR2(45 BYTE) DEFAULT NULL, "PERSON_SURNAME" VARCHAR2(45 BYTE) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table INVOICE_PRODUCT
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."INVOICE_PRODUCT" ("ID" NUMBER(10,0), "ID_INVOICE" NUMBER(10,0) DEFAULT NULL, "ID_PRODUCT" NUMBER(10,0) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table INVOICE_SERVICE
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."INVOICE_SERVICE" ("ID" NUMBER(10,0), "ID_INVOICE" NUMBER(10,0) DEFAULT NULL, "ID_SERVICE" NUMBER(10,0) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table PERSON
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."PERSON" ("ID" NUMBER(10,0), "NAME" VARCHAR2(45 BYTE) DEFAULT NULL, "SECOND_NAME" VARCHAR2(45 BYTE) DEFAULT NULL, "SURNAME" VARCHAR2(45 BYTE) DEFAULT NULL, "SECOND_SURNAME" VARCHAR2(45 BYTE) DEFAULT NULL, "PROFESSION" VARCHAR2(45 BYTE) DEFAULT NULL, "DATE_INSCRIPTION" TIMESTAMP (0) DEFAULT NULL, "PASSWORD" VARCHAR2(45 BYTE) DEFAULT NULL, "EMAIL" VARCHAR2(45 BYTE) DEFAULT NULL, "PHONE_NUMBER" VARCHAR2(45 BYTE) DEFAULT NULL, "TEL_NUMBER" VARCHAR2(45 BYTE) DEFAULT NULL, "FACEBOOK_ID" VARCHAR2(45 BYTE) DEFAULT NULL, "TWITTER_ID" VARCHAR2(45 BYTE) DEFAULT NULL, "INSTAGRAM_ID" VARCHAR2(45 BYTE) DEFAULT NULL, "LINKEDIN_ID" VARCHAR2(45 BYTE) DEFAULT NULL, "ACCOUNT_PICTURE" BLOB, "STREET_NUMBER" NUMBER(10,0) DEFAULT NULL, "STREET_NAME" VARCHAR2(45 BYTE) DEFAULT NULL, "CITY_NAME" VARCHAR2(45 BYTE) DEFAULT NULL, "COUNTRY_NAME" VARCHAR2(45 BYTE) DEFAULT NULL, "POSTAL_CODE" VARCHAR2(45 BYTE) DEFAULT NULL, "LAST_CONNECTION" NUMBER(10,0) DEFAULT NULL, "FUNCTION" VARCHAR2(45 BYTE) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table PRODUCT
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."PRODUCT" ("ID" NUMBER(10,0), "CODE" VARCHAR2(45 BYTE) DEFAULT NULL, "NAME" VARCHAR2(45 BYTE) DEFAULT NULL, "DESCRIPTION" VARCHAR2(45 BYTE) DEFAULT NULL, "PRICE" BINARY_DOUBLE DEFAULT NULL, "MAIN_IMAGE" BLOB, "TYPE" VARCHAR2(45 BYTE) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table PRODUCT_COMPONENT
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."PRODUCT_COMPONENT" ("ID" NUMBER(10,0), "ID_COMPONED" NUMBER(10,0) DEFAULT NULL, "ID_COMPONENT" NUMBER(10,0) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table PRODUCT_SERVICE
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."PRODUCT_SERVICE" ("ID" NUMBER(10,0), "ID_PRODUCT" NUMBER(10,0) DEFAULT NULL, "ID_SERVICE" NUMBER(10,0) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table SERVICE
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."SERVICE" ("ID" NUMBER(10,0), "CODE" VARCHAR2(45 BYTE) DEFAULT NULL, "NAME" VARCHAR2(45 BYTE) DEFAULT NULL, "DESCRIPTION" VARCHAR2(45 BYTE) DEFAULT NULL, "PRICE" BINARY_DOUBLE DEFAULT NULL, "MAIN_IMAGE" BLOB)
--------------------------------------------------------
--  DDL for Table SERVICE_COMPONENT
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."SERVICE_COMPONENT" ("ID" NUMBER(10,0), "ID_SERVICE" NUMBER(10,0) DEFAULT NULL, "ID_COMPONENT" NUMBER(10,0) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Table SERVICE_PRODUCT
--------------------------------------------------------

  CREATE TABLE "PAPFOOD"."SERVICE_PRODUCT" ("ID" NUMBER(10,0), "ID_SERVICE" NUMBER(10,0) DEFAULT NULL, "ID_PRODUCT" NUMBER(10,0) DEFAULT NULL)
--------------------------------------------------------
--  DDL for Sequence COMMANDE_PRODUCT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."COMMANDE_PRODUCT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence COMMANDE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."COMMANDE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence COMMANDE_SERVICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."COMMANDE_SERVICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence CONNECTION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."CONNECTION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence EVALUATION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."EVALUATION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence INVOICE_PERSON_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."INVOICE_PERSON_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence INVOICE_PRODUCT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."INVOICE_PRODUCT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence INVOICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."INVOICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence INVOICE_SERVICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."INVOICE_SERVICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence ORDER_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."ORDER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence PERSON_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."PERSON_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence PRODUCT_COMPONENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."PRODUCT_COMPONENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence PRODUCT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."PRODUCT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence PRODUCT_SERVICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."PRODUCT_SERVICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence SERVICE_COMPONENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."SERVICE_COMPONENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence SERVICE_PRODUCT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."SERVICE_PRODUCT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Sequence SERVICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PAPFOOD"."SERVICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
--------------------------------------------------------
--  DDL for Trigger COMMANDE_PRODUCT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."COMMANDE_PRODUCT_SEQ_TR" 
 BEFORE INSERT ON papfood.commande_product FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.commande_product_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."COMMANDE_PRODUCT_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger COMMANDE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."COMMANDE_SEQ_TR" 
 BEFORE INSERT ON papfood.commande FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.commande_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."COMMANDE_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger COMMANDE_SERVICE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."COMMANDE_SERVICE_SEQ_TR" 
 BEFORE INSERT ON papfood.commande_service FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.commande_service_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."COMMANDE_SERVICE_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger CONNECTION_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."CONNECTION_SEQ_TR" 
 BEFORE INSERT ON papfood.connection FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.connection_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."CONNECTION_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger EVALUATION_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."EVALUATION_SEQ_TR" 
 BEFORE INSERT ON papfood.evaluation FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.evaluation_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."EVALUATION_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger INVOICE_PERSON_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."INVOICE_PERSON_SEQ_TR" 
 BEFORE INSERT ON papfood.invoice_person FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.invoice_person_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."INVOICE_PERSON_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger INVOICE_PRODUCT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."INVOICE_PRODUCT_SEQ_TR" 
 BEFORE INSERT ON papfood.invoice_product FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.invoice_product_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."INVOICE_PRODUCT_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger INVOICE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."INVOICE_SEQ_TR" 
 BEFORE INSERT ON papfood.invoice FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.invoice_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."INVOICE_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger INVOICE_SERVICE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."INVOICE_SERVICE_SEQ_TR" 
 BEFORE INSERT ON papfood.invoice_service FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.invoice_service_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;

ALTER TRIGGER "PAPFOOD"."INVOICE_SERVICE_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger PERSON_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."PERSON_SEQ_TR" 
 BEFORE INSERT ON papfood.person FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.person_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."PERSON_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger PRODUCT_COMPONENT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."PRODUCT_COMPONENT_SEQ_TR" 
 BEFORE INSERT ON papfood.product_component FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.product_component_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."PRODUCT_COMPONENT_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger PRODUCT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."PRODUCT_SEQ_TR" 
 BEFORE INSERT ON papfood.product FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.product_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."PRODUCT_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger PRODUCT_SERVICE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."PRODUCT_SERVICE_SEQ_TR" 
 BEFORE INSERT ON papfood.product_service FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.product_service_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."PRODUCT_SERVICE_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger SERVICE_COMPONENT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."SERVICE_COMPONENT_SEQ_TR" 
 BEFORE INSERT ON papfood.service_component FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.service_component_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."SERVICE_COMPONENT_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger SERVICE_PRODUCT_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."SERVICE_PRODUCT_SEQ_TR" 
 BEFORE INSERT ON papfood.service_product FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.service_product_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."SERVICE_PRODUCT_SEQ_TR" ENABLE
--------------------------------------------------------
--  DDL for Trigger SERVICE_SEQ_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PAPFOOD"."SERVICE_SEQ_TR" 
 BEFORE INSERT ON papfood.service FOR EACH ROW
  WHEN (NEW.Id IS NULL) BEGIN
 SELECT papfood.service_seq.NEXTVAL INTO :NEW.Id FROM DUAL;
END;
ALTER TRIGGER "PAPFOOD"."SERVICE_SEQ_TR" ENABLE
--------------------------------------------------------
--  Constraints for Table SERVICE_COMPONENT
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."SERVICE_COMPONENT" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."SERVICE_COMPONENT" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."SERVICE_COMPONENT" ADD CHECK (id_service > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."SERVICE_COMPONENT" ADD CHECK (id_component > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."SERVICE_COMPONENT" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table INVOICE_PERSON
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."INVOICE_PERSON" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."INVOICE_PERSON" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_PERSON" ADD CHECK (id_invoice > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_PERSON" ADD CHECK (id_person > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_PERSON" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table COMMANDE
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."COMMANDE" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."COMMANDE" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE" ADD CHECK (id_customer > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table SERVICE
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."SERVICE" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."SERVICE" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."SERVICE" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table PRODUCT
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."PRODUCT" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."PRODUCT" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."PRODUCT" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table COMMANDE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."COMMANDE_PRODUCT" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."COMMANDE_PRODUCT" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE_PRODUCT" ADD CHECK (id_commande > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE_PRODUCT" ADD CHECK (id_product > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE_PRODUCT" ADD CHECK (quantity > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE_PRODUCT" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table COMMANDE_SERVICE
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."COMMANDE_SERVICE" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."COMMANDE_SERVICE" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE_SERVICE" ADD CHECK (id_commande > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE_SERVICE" ADD CHECK (id_service > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE_SERVICE" ADD CHECK (quantity > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE_SERVICE" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table SERVICE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."SERVICE_PRODUCT" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."SERVICE_PRODUCT" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."SERVICE_PRODUCT" ADD CHECK (id_service > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."SERVICE_PRODUCT" ADD CHECK (id_product > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."SERVICE_PRODUCT" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table EVALUATION
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."EVALUATION" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."EVALUATION" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."EVALUATION" ADD CHECK (id_person > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."EVALUATION" ADD CHECK (id_jury > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."EVALUATION" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table CONNECTION
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."CONNECTION" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."CONNECTION" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."CONNECTION" ADD CHECK (person_id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."CONNECTION" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table PRODUCT_SERVICE
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."PRODUCT_SERVICE" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."PRODUCT_SERVICE" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."PRODUCT_SERVICE" ADD CHECK (id_product > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."PRODUCT_SERVICE" ADD CHECK (id_service > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."PRODUCT_SERVICE" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table INVOICE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."INVOICE_PRODUCT" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."INVOICE_PRODUCT" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_PRODUCT" ADD CHECK (id_invoice > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_PRODUCT" ADD CHECK (id_product > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_PRODUCT" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table INVOICE_SERVICE
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."INVOICE_SERVICE" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."INVOICE_SERVICE" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_SERVICE" ADD CHECK (id_invoice > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_SERVICE" ADD CHECK (id_service > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_SERVICE" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table INVOICE
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."INVOICE" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."INVOICE" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."PERSON" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."PERSON" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."PERSON" ADD CHECK (street_number > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."PERSON" ADD CHECK (last_connection > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."PERSON" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table PRODUCT_COMPONENT
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."PRODUCT_COMPONENT" MODIFY ("ID" NOT NULL ENABLE)
 
  ALTER TABLE "PAPFOOD"."PRODUCT_COMPONENT" ADD CHECK (Id > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."PRODUCT_COMPONENT" ADD CHECK (id_componed > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."PRODUCT_COMPONENT" ADD CHECK (id_component > 0) ENABLE
 
  ALTER TABLE "PAPFOOD"."PRODUCT_COMPONENT" ADD PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table COMMANDE
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."COMMANDE" ADD CONSTRAINT "FK_COMMANDE_1" FOREIGN KEY ("ID_CUSTOMER") REFERENCES "PAPFOOD"."PERSON" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table COMMANDE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."COMMANDE_PRODUCT" ADD CONSTRAINT "FK_COMMANDE_PRODUCT_1" FOREIGN KEY ("ID_COMMANDE") REFERENCES "PAPFOOD"."COMMANDE" ("ID") ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE_PRODUCT" ADD CONSTRAINT "FK_COMMANDE_PRODUCT_2" FOREIGN KEY ("ID_PRODUCT") REFERENCES "PAPFOOD"."PRODUCT" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table COMMANDE_SERVICE
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."COMMANDE_SERVICE" ADD CONSTRAINT "FK_COMMANDE_SERVICE_1" FOREIGN KEY ("ID_COMMANDE") REFERENCES "PAPFOOD"."COMMANDE" ("ID") ENABLE
 
  ALTER TABLE "PAPFOOD"."COMMANDE_SERVICE" ADD CONSTRAINT "FK_COMMANDE_SERVICE_2" FOREIGN KEY ("ID_SERVICE") REFERENCES "PAPFOOD"."SERVICE" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table CONNECTION
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."CONNECTION" ADD CONSTRAINT "FK_CONNECTION_1" FOREIGN KEY ("PERSON_ID") REFERENCES "PAPFOOD"."PERSON" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table EVALUATION
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."EVALUATION" ADD CONSTRAINT "FK_EVALUATION_1" FOREIGN KEY ("ID_PERSON") REFERENCES "PAPFOOD"."PERSON" ("ID") ENABLE
 
  ALTER TABLE "PAPFOOD"."EVALUATION" ADD CONSTRAINT "FK_EVALUATION_2" FOREIGN KEY ("ID_JURY") REFERENCES "PAPFOOD"."PERSON" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table INVOICE_PERSON
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."INVOICE_PERSON" ADD CONSTRAINT "FK_INVOICE_PERSON_1" FOREIGN KEY ("ID_INVOICE") REFERENCES "PAPFOOD"."INVOICE" ("ID") ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_PERSON" ADD CONSTRAINT "FK_INVOICE_PERSON_2" FOREIGN KEY ("ID_PERSON") REFERENCES "PAPFOOD"."PERSON" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table INVOICE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."INVOICE_PRODUCT" ADD CONSTRAINT "FK_INVOICE_PRODUCT_1" FOREIGN KEY ("ID_INVOICE") REFERENCES "PAPFOOD"."INVOICE" ("ID") ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_PRODUCT" ADD CONSTRAINT "FK_INVOICE_PRODUCT_2" FOREIGN KEY ("ID_PRODUCT") REFERENCES "PAPFOOD"."PRODUCT" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table INVOICE_SERVICE
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."INVOICE_SERVICE" ADD CONSTRAINT "FK_INVOICE_SERVICE_1" FOREIGN KEY ("ID_INVOICE") REFERENCES "PAPFOOD"."INVOICE" ("ID") ENABLE
 
  ALTER TABLE "PAPFOOD"."INVOICE_SERVICE" ADD CONSTRAINT "FK_INVOICE_SERVICE_2" FOREIGN KEY ("ID_SERVICE") REFERENCES "PAPFOOD"."SERVICE" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."PERSON" ADD CONSTRAINT "FK_PERSON_1" FOREIGN KEY ("LAST_CONNECTION") REFERENCES "PAPFOOD"."CONNECTION" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table PRODUCT_COMPONENT
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."PRODUCT_COMPONENT" ADD CONSTRAINT "FK_PRODUCT_COMPONENT_1" FOREIGN KEY ("ID_COMPONED") REFERENCES "PAPFOOD"."PRODUCT" ("ID") ENABLE
 
  ALTER TABLE "PAPFOOD"."PRODUCT_COMPONENT" ADD CONSTRAINT "FK_PRODUCT_COMPONENT_2" FOREIGN KEY ("ID_COMPONENT") REFERENCES "PAPFOOD"."PRODUCT" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table PRODUCT_SERVICE
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."PRODUCT_SERVICE" ADD CONSTRAINT "FK_PRODUCT_SERVICE_1" FOREIGN KEY ("ID_PRODUCT") REFERENCES "PAPFOOD"."PRODUCT" ("ID") ENABLE
 
  ALTER TABLE "PAPFOOD"."PRODUCT_SERVICE" ADD CONSTRAINT "FK_PRODUCT_SERVICE_2" FOREIGN KEY ("ID_SERVICE") REFERENCES "PAPFOOD"."SERVICE" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table SERVICE_COMPONENT
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."SERVICE_COMPONENT" ADD CONSTRAINT "FK_SERVICE_COMPONENT_1" FOREIGN KEY ("ID_SERVICE") REFERENCES "PAPFOOD"."SERVICE" ("ID") ENABLE
 
  ALTER TABLE "PAPFOOD"."SERVICE_COMPONENT" ADD CONSTRAINT "FK_SERVICE_COMPONENT_2" FOREIGN KEY ("ID_COMPONENT") REFERENCES "PAPFOOD"."SERVICE" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table SERVICE_PRODUCT
--------------------------------------------------------

  ALTER TABLE "PAPFOOD"."SERVICE_PRODUCT" ADD CONSTRAINT "FK_SERVICE_PRODUCT_1" FOREIGN KEY ("ID_SERVICE") REFERENCES "PAPFOOD"."SERVICE" ("ID") ENABLE
 
  ALTER TABLE "PAPFOOD"."SERVICE_PRODUCT" ADD CONSTRAINT "FK_SERVICE_PRODUCT_2" FOREIGN KEY ("ID_PRODUCT") REFERENCES "PAPFOOD"."PRODUCT" ("ID") ENABLE