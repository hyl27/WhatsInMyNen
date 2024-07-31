--------------------------------------------------------
--  파일이 생성됨 - 금요일-6월-07-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence INCREMENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "INCREMENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 18 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Procedure DELETE_ING
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "DELETE_ING" (
    p_member_id IN VARCHAR2,
    p_nen_id IN NUMBER,
    p_ingredient_name IN VARCHAR2,
    p_result OUT VARCHAR2
) AS
    v_count NUMBER := 0;
BEGIN
    -- my_nenjanggo 테이블에서 member_id와 nen_id 확인
    SELECT COUNT(*)
    INTO v_count
    FROM my_nenjanggo
    WHERE member_id = p_member_id AND nen_id = p_nen_id;

    IF v_count > 0 THEN
            DELETE FROM ingredients 
            WHERE name = p_ingredient_name;
            p_result := '재료 삭제 완료';
    ELSE
            p_result := '없는 재료 입니다.';
END IF;

END DELETE_ING;


/
--------------------------------------------------------
--  DDL for Procedure DELETE_MEMBER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "DELETE_MEMBER" (
    p_member_id      IN  VARCHAR2,
    p_update_status  OUT VARCHAR2
)
IS
BEGIN
    -- Delete records from my_nenjanggo
        DELETE FROM my_nenjanggo
        WHERE member_id = p_member_id;

        -- Delete records from reviews
        DELETE FROM reviews
        WHERE member_id = p_member_id;

        -- Delete member from members
        DELETE FROM members
        WHERE member_id = p_member_id;    
    COMMIT;

    p_update_status := '회원 탈퇴가 정상처리 되었습니다..';
EXCEPTION
    WHEN OTHERS THEN
        p_update_status := 'Error: ' || SQLERRM;

        ROLLBACK;
END DELETE_MEMBER;

/
--------------------------------------------------------
--  DDL for Procedure DELETE_NEN
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "DELETE_NEN" (
    p_id IN VARCHAR2,
    p_nen_id IN VARCHAR2,
    p_delete_nen OUT VARCHAR2
) AS
    v_count NUMBER := 0;
BEGIN
    -- Check if the nen_id belongs to the given member_id
    SELECT COUNT(*)
    INTO v_count
    FROM my_nenjanggo
    WHERE nen_id = p_nen_id;

    IF v_count > 0 THEN
        DELETE FROM my_nenjanggo 
        WHERE member_id = p_id AND nen_id = p_nen_id;

        p_delete_nen := '삭제 완료(냉장고)';
    ELSE
        p_delete_nen := '삭제 실패: 해당 냉장고 ID가 존재하지 않거나 권한이 없습니다.';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_delete_nen := '삭제 중 오류 발생';
END DELETE_NEN;


/
--------------------------------------------------------
--  DDL for Procedure DELETE_REVIEW
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "DELETE_REVIEW" (
    p_review_id IN REVIEWS.REVIEW_ID%TYPE
) IS
BEGIN
    -- 검색된 리뷰를 삭제합니다.
    DELETE FROM REVIEWS
    WHERE REVIEW_ID = p_review_id;
END DELETE_REVIEW;

/
--------------------------------------------------------
--  DDL for Procedure FIND_MENU
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "FIND_MENU" (
    p_search_term IN VARCHAR2,
    p_result OUT VARCHAR2,
    p_menu_names OUT SYS_REFCURSOR
) AS
    v_count NUMBER := 0;
BEGIN
   
    -- 주어진 단어를 포함하는 메뉴를 찾습니다.
    OPEN p_menu_names FOR
    SELECT menu_name
    FROM menus
    WHERE all_ingredient LIKE '%' || REPLACE(p_search_term, ',', '%') || '%';

   -- 메뉴의 수를 계산합니다.
    SELECT COUNT(*)
    INTO v_count
    FROM menus
    WHERE all_ingredient LIKE '%' || REPLACE(p_search_term, ',', '%') || '%';

    -- 결과 메시지 설정
    IF v_count = 0 THEN
        p_result := '해당 단어가 포함된 메뉴가 없습니다.';
    ELSE
        p_result := '해당 단어가 포함된 메뉴 발견!';
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_result := '입력한 단어는 냉장고에 담긴 재료 중 하나여야 합니다.';
    WHEN OTHERS THEN
        p_result := '오류: ' || SQLERRM;
END FIND_MENU;

/
--------------------------------------------------------
--  DDL for Procedure FIND_MENU_NAME
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "FIND_MENU_NAME" (
    p_search_term IN VARCHAR2,
    p_result OUT VARCHAR2,
    p_menu_names OUT SYS_REFCURSOR
) AS

BEGIN
    -- 주어진 단어가 ingredients 테이블의 name 열에 있는지 확인합니다.

    -- 주어진 단어를 포함하는 메뉴를 찾습니다.
    OPEN p_menu_names FOR
    SELECT menu_name
    FROM menus
    WHERE menu_name LIKE '%' || p_search_term || '%';

    -- 결과 메시지 설정
    IF p_menu_names%ISOPEN THEN
        p_result := '해당 단어가 포함된 메뉴 발견!';
    ELSE
        p_result := '해당 단어가 포함된 메뉴가 없습니다.';
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_result := '찾고자하는 단어를 입력하세요.';
    WHEN OTHERS THEN
        p_result := '오류: ' || SQLERRM;
END FIND_MENU_NAME;

/
--------------------------------------------------------
--  DDL for Procedure INSERT_ING
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "INSERT_ING" (
    p_member_id IN VARCHAR2,
    p_nen_id IN NUMBER,
    p_ingredient_name IN VARCHAR2,
    p_result OUT VARCHAR2
) AS
    v_count NUMBER := 0;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM my_nenjanggo
    WHERE member_id = p_member_id AND nen_id = p_nen_id;

    IF v_count > 0 THEN
        INSERT INTO ingredients ( ingredient_id , name , nen_id)
        VALUES (increment_seq.NEXTVAL, p_ingredient_name, p_nen_id);
        p_result := '재료 추가 완료';

    ELSE
        p_result := '재료 추가 실패';

    END IF;
END INSERT_ING;


/
--------------------------------------------------------
--  DDL for Procedure INSERT_NEN
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "INSERT_NEN" (
    p_id IN VARCHAR2,
    p_insert_nen OUT VARCHAR2,
    p_nen_id OUT NUMBER
) AS
    v_member_count NUMBER := 0;
    v_nen_count NUMBER := 0;
BEGIN
    SELECT COUNT(*)
    INTO v_member_count
    FROM members
    WHERE member_id = p_id;

    IF v_member_count > 0 THEN
        SELECT COUNT(*)
        INTO v_nen_count
        FROM my_nenjanggo
        WHERE member_id = p_id;

        IF v_nen_count < 3 THEN
            INSERT INTO my_nenjanggo (nen_id, member_id) 
            VALUES (increment_seq.NEXTVAL, p_id) 
            RETURNING nen_id INTO p_nen_id;

            p_insert_nen := '냉장고 추가 완료';
        ELSE
            p_insert_nen := '추가 실패: 냉장고는 최대 3개까지 생성할 수 있습니다.';
            p_nen_id := NULL;
        END IF;
    ELSE
        p_insert_nen := '추가 실패: 존재하지 않는 회원 ID입니다.';
        p_nen_id := NULL;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_insert_nen := '추가 중 오류 발생';
        p_nen_id := NULL;
END INSERT_NEN;


/
--------------------------------------------------------
--  DDL for Procedure INSERT_REVIEW
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "INSERT_REVIEW" (
    R_CONTENT IN CLOB,
    R_REVIEW_DATE IN DATE,
    R_RATING IN NUMBER,
    R_MEMBER_ID MEMBERS.MEMBER_ID%TYPE,
    R_MENU_NAME IN VARCHAR2,
    R_URL IN CLOB,
    R_result OUT VARCHAR2
    
) IS
    v_menu_id NUMBER;
BEGIN
    -- 메뉴 이름으로 메뉴 ID 찾기
    SELECT menu_id INTO v_menu_id
    FROM menus
    WHERE menu_name = R_MENU_NAME;

    -- 리뷰 테이블에 메뉴 ID와 리뷰 저장
    INSERT INTO reviews (review_id, menus_menu_id, content, review_date, rating, member_id, url)
    VALUES (increment_seq.NEXTVAL, v_menu_id, R_CONTENT, R_REVIEW_DATE, R_RATING, R_MEMBER_ID, R_URL);
     R_result := '리뷰 추가 완료';

    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        R_result := '리뷰 데이터 없음';
    WHEN OTHERS THEN
        R_result :=  '리뷰 추가 실패 오류 '||SQLERRM;
END INSERT_REVIEW;

/
--------------------------------------------------------
--  DDL for Procedure JOIN_MEMBER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "JOIN_MEMBER" (
    p_id IN VARCHAR2,
    p_pw IN VARCHAR2, 
    p_name IN VARCHAR2,
    p_gender IN CHAR,
    p_bdate IN VARCHAR2,
    p_email  IN VARCHAR2,
    p_join_status OUT VARCHAR2
    ) AS
    
     v_count NUMBER := 0;


    BEGIN

        SELECT COUNT(*)
        INTO v_count
        FROM members
        WHERE member_id = P_ID;

        IF v_count > 0 THEN
            p_join_status := '사용 할 수 없는 아이디 입니다.';
        ELSE
            INSERT INTO members
            VALUES (p_id, p_pw, p_name, p_gender, p_bdate, p_email);
            p_join_status := '회원가입 성공!';
        END IF;

    END JOIN_MEMBER;


/
--------------------------------------------------------
--  DDL for Procedure LOGIN
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "LOGIN" (
    p_id IN VARCHAR2,
    p_pw IN VARCHAR2, 
    p_login_status OUT VARCHAR2
) AS
    v_count NUMBER :=0;

    BEGIN
        SELECT  count(*) INTO v_count
        FROM members
        WHERE member_id = p_id AND member_pw = p_pw;

       IF v_count > 0 THEN
        p_login_status := '로그인 성공';
    ELSE
        p_login_status := '아이디 또는 비밀번호가 일치하지 않습니다.';
    END IF;
END LOGIN;


/
--------------------------------------------------------
--  DDL for Procedure MEMBER_INFO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "MEMBER_INFO" (
    p_id IN members.member_id%TYPE,
    p_result OUT SYS_REFCURSOR
)
IS
BEGIN
    -- 커서 열기
    OPEN p_result FOR
        SELECT member_id, member_pw, name, gender, birthdate, email
        FROM members
        WHERE member_id = p_id;
END MEMBER_INFO;

/
--------------------------------------------------------
--  DDL for Procedure SELECT_REVIEW_LIST
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SELECT_REVIEW_LIST" (
    p_member_id IN MEMBERS.MEMBER_ID%TYPE,
    o_review_cursor OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN o_review_cursor FOR
    SELECT R.REVIEW_ID, R.CONTENT, R.REVIEW_DATE, R.RATING, R.MEMBER_ID, M.MENU_NAME, R.URL
    FROM REVIEWS R
    JOIN MENUS M ON R.MENUS_MENU_ID = M.MENU_ID
    WHERE R.MEMBER_ID = p_member_id;
END SELECT_REVIEW_LIST;

/
--------------------------------------------------------
--  DDL for Procedure UPDATE_ING
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "UPDATE_ING" (
    p_member_id IN VARCHAR2,
    p_nen_id IN NUMBER,
    p_i_name IN VARCHAR2,
    p_new_name IN VARCHAR2,
    p_result OUT VARCHAR2
) AS
    v_count NUMBER := 0;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM my_nenjanggo
    WHERE member_id = p_member_id AND nen_id = p_nen_id;

    IF v_count > 0 THEN
        UPDATE ingredients 
        SET name = p_new_name
        WHERE name = p_i_name;
        p_result := '재료 수정 완료';

    ELSE
        p_result := '재료 수정 실패';

    END IF;
END UPDATE_ING;

/
--------------------------------------------------------
--  DDL for Procedure UPDATE_MEMBER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "UPDATE_MEMBER" (
    p_id IN VARCHAR2,
    p_new_pw IN VARCHAR2,
    p_new_email IN VARCHAR2,
    p_update_status OUT VARCHAR2
) AS
    v_count NUMBER := 0;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM members
    WHERE member_id = p_id;

    IF v_count > 0 THEN
        UPDATE members
        SET member_pw = p_new_pw, email = p_new_email
        WHERE member_id = p_id;

        p_update_status := '정보가 성공적으로 업데이트되었습니다.';
    ELSE
        p_update_status := '사용자를 찾을 수 없습니다.';
    END IF;
END UPDATE_MEMBER;

/
--------------------------------------------------------
--  DDL for Procedure UPDATE_REVIEW
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "UPDATE_REVIEW" (
    R_CONTENT IN CLOB,
    R_REVIEW_DATE IN DATE,
    R_RATING IN NUMBER,
    R_URL IN CLOB,
    p_review_id IN REVIEWS.REVIEW_ID%TYPE
) IS
BEGIN 
    -- 리뷰 업데이트
    UPDATE REVIEWS
    SET 
        CONTENT = R_CONTENT, 
        REVIEW_DATE = R_REVIEW_DATE, 
        RATING = R_RATING, 
        URL = R_URL
    WHERE REVIEW_ID = p_review_id; 
END UPDATE_REVIEW;

/
--------------------------------------------------------
--  DDL for Procedure VIEW_ING
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "VIEW_ING" (
    p_member_id IN VARCHAR2,
    p_nen_id IN NUMBER,
    p_result OUT SYS_REFCURSOR
) AS
    v_count NUMBER := 0;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM my_nenjanggo
    WHERE member_id = p_member_id AND nen_id = p_nen_id;

    IF v_count > 0 THEN
        OPEN p_result FOR
        SELECT name
        FROM ingredients
        WHERE nen_id = p_nen_id;
    ELSE
        OPEN p_result FOR
        SELECT '재료 보기 실패' AS name
        FROM dual;
    END IF;
END VIEW_ING;

/
--------------------------------------------------------
--  DDL for Procedure VIEW_NEN
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "VIEW_NEN" (
    p_id IN VARCHAR2,
    p_view_nen OUT VARCHAR2
) AS
    v_count NUMBER := 0;
    v_nen_ids VARCHAR2(1000);  

BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM members
    WHERE member_id = p_id;

    IF v_count > 0 THEN
        v_nen_ids := '';

        FOR rec IN (SELECT nen_id FROM my_nenjanggo WHERE member_id = p_id) LOOP
            v_nen_ids := v_nen_ids || rec.nen_id || ' ';
        END LOOP;

        IF LENGTH(v_nen_ids) > 0 THEN
            v_nen_ids := SUBSTR(v_nen_ids, 1, LENGTH(v_nen_ids) - 1);
        ELSE
            p_view_nen := '냉장고가 없습니다.';
        END IF;

        p_view_nen := v_nen_ids;
    ELSE
        p_view_nen := '해당 회원 ID가 존재하지 않습니다.';
    END IF;
END VIEW_NEN;


/
