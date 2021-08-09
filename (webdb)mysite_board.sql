DROP TABLE board;

DROP SEQUENCE sqc_board_no;

CREATE TABLE board (
    no        NUMBER,
    title     VARCHAR2(500) NOT NULL,
    content   VARCHAR2(4000),
    hit       NUMBER,
    reg_date  DATE NOT NULL,
    user_no   NUMBER NOT NULL,
    PRIMARY KEY ( no ),
    CONSTRAINT board_fk FOREIGN KEY ( user_no )
        REFERENCES users ( no )
);

CREATE SEQUENCE sqc_board_no START WITH 1 INCREMENT BY 1 NOCACHE;

SELECT
    no,
    title,
    content,
    hit,
    reg_date,
    user_no
FROM
    board;

INSERT INTO board VALUES (
    sqc_board_no.NEXTVAL,
    '제목123',
    '내용123',
    0,
    sysdate,
    6
);

COMMIT;

DELETE FROM board;

SELECT
    board.no,
    board.title,
    users.name,
    board.hit,
    board.reg_date,
    board.user_no
FROM
    users,
    board
WHERE
    users.no = board.user_no
ORDER BY
    board.no DESC;

SELECT
    users.name,
    board.hit,
    board.reg_date,
    board.title,
    board.content,
    board.user_no
FROM
    users,
    board
WHERE
        users.no = board.user_no
    AND board.no = 10;

SELECT
    users.name,
    board.hit,
    board.reg_date,
    board.title,
    board.content,
    board.user_no
FROM
    users,
    board
WHERE
        users.no = board.user_no
    AND users.no = 2
    AND board.user_no = 2;

UPDATE board
SET
    hit = ( hit + 1 )
WHERE
    no = 4;

DELETE FROM board
WHERE
    no = 10;

SELECT
    board.no,
    board.title,
    users.name,
    board.hit,
    board.reg_date,
    board.user_no
FROM
    users
    FULL OUTER JOIN board ON users.no = board.user_no
WHERE
    board.no LIKE '%호%'
    OR board.title LIKE '%호%'
    OR users.name LIKE '%호%'
ORDER BY
    board.no DESC;

SELECT
    COUNT(no)
FROM
    (
        SELECT
            no
        FROM
            board
    );

SELECT
    board.no,
    board.title,
    users.name,
    board.hit,
    board.reg_date,
    board.user_no
FROM
    users
    RIGHT OUTER JOIN board ON users.no = board.user_no
ORDER BY
    board.no DESC;

SELECT
    b.no,
    b.title,
    b.content,
    b.hit,
    b.reg_date,
    b.user_no,
    u.name
FROM
    board  b,
    users  u
WHERE
        b.user_no = u.no
    AND b.no = 4;

SELECT
    b.no,
    b.title,
    b.hit,
    b.reg_date,
    b.user_no,
    u.name
FROM
    board  b
    LEFT OUTER JOIN users  u ON b.user_no = u.no
WHERE
    b.title LIKE '%셋%'
    OR u.name LIKE '%셋%'
ORDER BY
    b.no DESC;

UPDATE board
SET
    title = 'asd123',
    content = 'asd123'
WHERE
    no = 35;

SELECT
    COUNT(no)
FROM
    (
        SELECT
            no
        FROM
            board
    );

DELETE FROM board
WHERE
    no = 5;

SELECT
    rn,
    bu.no,
    bu.title,
    bu.name,
    bu.hit,
    bu.reg_date,
    bu.user_no
FROM
    (
        SELECT
            ROWNUM rn,
            b.no,
            b.title,
            u.name,
            b.hit,
            b.content,
            b.reg_date,
            b.user_no
        FROM
            board  b,
            users  u
        WHERE
            u.no = b.user_no
        ORDER BY
            b.no ASC
    ) bu
ORDER BY
    rn DESC;

SELECT
    COUNT(no)
FROM
    board;

SELECT
    COUNT(no)
FROM
    (
        SELECT
            b.no
        FROM
            board  b
            RIGHT OUTER JOIN users  u ON u.no = b.user_no
        WHERE
            b.title LIKE '%호%'
            OR u.name LIKE '%호%'
    );

UPDATE board
SET
    title = '제목입력',
    content = 'asd'
WHERE
    no = 21;


-- paging --
DELETE FROM board;

-- 1) CREATE ORDER TABLE board = orderboardtable odt
SELECT
    b.no,
    b.title,
    b.hit,
    b.reg_date,
    b.user_no,
    u.name
FROM
    board  b
    LEFT OUTER JOIN users  u ON b.user_no = u.no
ORDER BY
    b.no DESC;

-- 2) ROWNUM orderboardtable obt = rnodt
SELECT
    ROWNUM rn,
    odt.no,
    odt.title,
    odt.hit,
    odt.reg_date,
    odt.user_no,
    odt.name
FROM
    (
        SELECT
            b.no,
            b.title,
            b.hit,
            b.reg_date,
            b.user_no,
            u.name
        FROM
            board  b
            LEFT OUTER JOIN users  u ON b.user_no = u.no
        ORDER BY
            b.no DESC
    ) odt;

-- 3) ROWNUMorderboardtable = rnodt에 WHERE절 추가
SELECT
    rnodt.rn,
    rnodt.no,
    rnodt.title,
    rnodt.hit,
    rnodt.reg_date,
    rnodt.user_no,
    rnodt.name
FROM
    (
        SELECT
            ROWNUM rn,
            odt.no,
            odt.title,
            odt.hit,
            odt.reg_date,
            odt.user_no,
            odt.name
        FROM
            (
                SELECT
                    b.no,
                    b.title,
                    b.hit,
                    b.reg_date,
                    b.user_no,
                    u.name
                FROM
                    board  b
                    LEFT OUTER JOIN users  u ON b.user_no = u.no
                ORDER BY
                    b.no DESC
            ) odt
    ) rnodt
WHERE
        15 <= rn
    AND rn <= 28;


-- 전체 게시물 개수
SELECT
    COUNT(no)
FROM
    board;

-- search - keyword LIKE
SELECT
    b.no,
    b.title,
    b.hit,
    b.reg_date,
    b.user_no,
    u.name
FROM
    board  b
    LEFT OUTER JOIN users  u ON b.user_no = u.no
WHERE
    b.title LIKE '%5%'
ORDER BY
    b.no DESC;