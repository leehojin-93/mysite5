DROP TABLE gallery;

DROP SEQUENCE sqc_gallery_no;

CREATE TABLE gallery (
    no         NUMBER,
    content    VARCHAR2(1000),
    file_path  VARCHAR2(500),
    org_name   VARCHAR2(200),
    save_name  VARCHAR2(500),
    file_size  NUMBER,
    user_no    NUMBER,
    PRIMARY KEY ( no ),
    CONSTRAINT gallery_fk FOREIGN KEY ( user_no )
        REFERENCES users ( no )
);

CREATE SEQUENCE sqc_gallery_no
START WITH 1
INCREMENT BY 1
NOCACHE;

SELECT
    g.no,
    g.content,
    g.file_path,
    g.org_name,
    g.save_name,
    g.file_size,
    u.no,
    u.name
FROM
    gallery g LEFT OUTER JOIN users u
    ON g.user_no = u.no
ORDER BY
    g.no DESC;

INSERT INTO
    gallery
VALUES
    (
        sqc_gallery_no.NEXTVAL,
        '첫 번째 사진 내용',
        'path',
        '원본이름',
        '서버에 저장되는 이름',
        1024,
        7
    );

DELETE FROM
    gallery
WHERE
    no = 3;