-- 테이블 생성
CREATE TABLE TB_USER (
    id VARCHAR(255)  PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    pwd VARCHAR(255) NOT NULL,
    mobile VARCHAR(20) NOT NULL,
    create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 데이터 삽입
INSERT INTO TB_USER (id,name, email, pwd, mobile) VALUES
    ('user1','John Doe', 'john.doe@example.com', 'password123', '123-456-7890'),
    ('user2','Jane Smith', 'jane.smith@example.com', 'securepass', '987-654-3210');