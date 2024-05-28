-------- MEMBERS

CREATE TABLE members (
    member_id VARCHAR2(255) NOT NULL,
    member_pw VARCHAR2(255),
    name      VARCHAR2(255),
    gender    CHAR(1),
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
ADD CONSTRAINT my_nenjanggo_members_fk FOREIGN KEY ( members_member_id )
REFERENCES members ( member_id );

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
/

-------- INGREDIENTS

CREATE TABLE ingredients (
    ingredient_id       INTEGER NOT NULL,
    name                VARCHAR2(255),
    nen_id INTEGER NOT NULL
);


ALTER TABLE ingredients ADD CONSTRAINT ingredients_pk PRIMARY KEY ( ingredient_id );

ALTER TABLE ingredients
    ADD CONSTRAINT ingredients_my_nenjanggo_fk FOREIGN KEY ( nen_id )
        REFERENCES my_nenjanggo ( nen_id );

CREATE OR REPLACE TRIGGER auto_increment_ingredient
BEFORE INSERT ON ingredients
FOR EACH ROW
WHEN (NEW.ingredient_id IS NULL)
BEGIN
  SELECT increment_seq.NEXTVAL INTO :NEW.ingredient_id FROM dual;
END;
/



---------- MENUS

CREATE TABLE menus (
    menu_id           INTEGER NOT NULL,
    menu_name         VARCHAR2(255) NOT NULL,
    all_ingredient    CLOB NOT NULL,
    member_id VARCHAR2(255) NOT NULL
);

ALTER TABLE menus ADD CONSTRAINT menus_pk PRIMARY KEY ( menu_id );

ALTER TABLE menus
    ADD CONSTRAINT menus_members_fk FOREIGN KEY ( member_id )
        REFERENCES members ( member_id );


----------- RECIPES

CREATE TABLE recipes (
    recipe_id     INTEGER NOT NULL,
    url           VARCHAR2(1000),
    menu_id INTEGER NOT NULL
);

ALTER TABLE recipes ADD CONSTRAINT recipes_pk PRIMARY KEY ( recipe_id );

ALTER TABLE recipes
    ADD CONSTRAINT recipes_menus_fk FOREIGN KEY ( menu_id )
        REFERENCES menus ( menu_id );


------------ DROP TABLE RECIPES
SELECT CONSTRAINT_NAME, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE R_CONSTRAINT_NAME IN (
    SELECT CONSTRAINT_NAME
    FROM USER_CONSTRAINTS
    WHERE TABLE_NAME = 'RECIPES'
);--제약조건 걸린거 확인

ALTER TABLE REVIEWS
DROP CONSTRAINT REVIEWS_RECIPES_FK; --FK걸린거 삭제

DROP TABLE RECIPES;--테이블삭제

        
        
----------- REVIEWS

CREATE TABLE reviews (
    review_id         INTEGER NOT NULL,
    content           CLOB,
    review_date              VARCHAR2(255),
    rating            SMALLINT,
    member_id   VARCHAR2(255) NOT NULL,
    recipe_id       INTEGER NOT NULL
);

ALTER TABLE reviews ADD CONSTRAINT reviews_pk PRIMARY KEY ( review_id );

ALTER TABLE reviews
    ADD CONSTRAINT reviews_members_fk FOREIGN KEY (member_id )
        REFERENCES members ( member_id );

ALTER TABLE reviews
    ADD CONSTRAINT reviews_recipes_fk FOREIGN KEY ( recipe_id )
        REFERENCES recipes ( recipe_id );

..

ALTER TABLE reviews
ADD (
    menus_menu_id INTEGER NOT NULL,
    url CLOB
); --RECIPES에 있던 컬럼 추가

ALTER TABLE REVIEWS
DROP COLUMN RECIPES_RECIPE_ID; --레시피와 연결되어있던 컬럼삭제 

ALTER TABLE reviews
    ADD CONSTRAINT reviews_menus_fk FOREIGN KEY ( menus_menu_id )
        REFERENCES menus ( menu_id ); --MENUID FK지정
        
        