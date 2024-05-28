--------------------------member
//회원가입
create or replace PROCEDURE JOIN_MEMBER (
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
    
//로그인
create or replace PROCEDURE LOGIN (
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

--------------------------review

