CREATE TABLE TB_ROLE_MANAGE
(
    ROLE_CODE VARCHAR(50)  NOT NULL COMMENT '역할 코드',
    ROLE_NAME VARCHAR(100) NOT NULL COMMENT '역할명',
    DESCRIPTION     VARCHAR(255) NULL COMMENT '역할 설명',

    `CREATED_AT`    DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '[감사컬럼]\n생성일',
    `CREATED_BY`    BIGINT NULL COMMENT '[감사컬럼]\n생성자',
    `UPDATED_AT`    DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '[감사컬럼]\n수정일',
    `UPDATED_BY`    BIGINT NULL COMMENT '[감사컬럼]\n수정자',
    `DELETED_AT`    DATETIME(6) NULL COMMENT '[감사컬럼]\n삭제일',

    PRIMARY KEY (ROLE_CODE)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '권한 정의 테이블';

INSERT INTO TB_ROLE_MANAGE (ROLE_CODE,
                            ROLE_NAME,
                            DESCRIPTION)
VALUES
    ('USER', '일반 사용자', '일반 기능 이용'),
    ('ADMIN', '최고 관리자', '모든 관리 기능 이용'),
    ('MANAGER', '일반 관리자', '관리자 영역 접근'),
    ('USER_MANAGER', '회원 관리자', '회원 관리 기능 이용');