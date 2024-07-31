-------- MEMBERS

CREATE TABLE MEMBERS (
    member_id VARCHAR2(255) NOT NULL,
    member_pw VARCHAR2(255),
    name      VARCHAR2(255),
    gender    CHAR(5),
    birthdate VARCHAR2(255),
    email     VARCHAR2(255)
);

ALTER TABLE members ADD CONSTRAINT members_pk PRIMARY KEY ( member_id );


-------- MY_NENJANGGO

CREATE TABLE my_nenjanggo (
    nen_id            INTEGER NOT NULL,
    member_id VARCHAR2(255) NOT NULL
);

ALTER TABLE my_nenjanggo 
ADD CONSTRAINT my_nenjanggo_pk PRIMARY KEY ( nen_id );

ALTER TABLE my_nenjanggo 
ADD CONSTRAINT my_nenjanggo_members_fk FOREIGN KEY ( member_id )
REFERENCES members ( member_id )
ON DELETE CASCADE;

CREATE SEQUENCE increment_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE OR REPLACE TRIGGER auto_increment
BEFORE INSERT ON my_nenjanggo
FOR EACH ROW
WHEN (NEW.nen_id IS NULL)
BEGIN
  SELECT increment_seq.NEXTVAL INTO :NEW.nen_id FROM dual;
END;

-------- INGREDIENTS

CREATE TABLE ingredients (
    ingredient_id       INTEGER NOT NULL,
    name                VARCHAR2(255),
    nen_id INTEGER NOT NULL
);


ALTER TABLE ingredients ADD CONSTRAINT ingredients_pk PRIMARY KEY ( ingredient_id );

ALTER TABLE ingredients
    ADD CONSTRAINT ingredients_my_nenjanggo_fk FOREIGN KEY ( nen_id )
        REFERENCES my_nenjanggo ( nen_id )
   ON DELETE CASCADE;

CREATE OR REPLACE TRIGGER auto_increment_ingredient
BEFORE INSERT ON ingredients
FOR EACH ROW
WHEN (NEW.ingredient_id IS NULL)
BEGIN
  SELECT increment_seq.NEXTVAL INTO :NEW.ingredient_id FROM dual;
END;


---------- MENUS

CREATE TABLE menus (
    menu_id           INTEGER NOT NULL,
    menu_name         VARCHAR2(255) NOT NULL,
    all_ingredient    CLOB NOT NULL
);

ALTER TABLE menus ADD CONSTRAINT menus_pk PRIMARY KEY ( menu_id );



----------- REVIEWS

CREATE TABLE reviews (
    review_id         INTEGER NOT NULL,
    content           CLOB,
    review_date       DATE,
    rating            INTEGER,
    url            CLOB,
    member_id   VARCHAR2(255) NOT NULL,
    menus_menu_id INTEGER NOT NULL
);

ALTER TABLE reviews ADD CONSTRAINT reviews_pk PRIMARY KEY ( review_id );

ALTER TABLE reviews
    ADD CONSTRAINT reviews_members_fk FOREIGN KEY (member_id )
        REFERENCES members ( member_id );

ALTER TABLE reviews
    ADD CONSTRAINT reviews_menus_fk FOREIGN KEY ( menus_menu_id )
        REFERENCES menus ( menu_id );
        