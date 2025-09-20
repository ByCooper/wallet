-- liquibase formatted sql

--changeset akupriyanov:1
CREATE TABLE wallet (
id BIGINT NOT NULL AUTO_INCREMENT,
uuid VARCHAR(50) NOT NULL UNIQUE,
balance INTEGER,
PRIMARY KEY(id)
);

--changeset akupriyanov:2
CREATE TABLE pay_operation (
id BIGINT NOT NULL AUTO_INCREMENT,
uuid VARCHAR(50) NOT NULL UNIQUE,
operation_type VARCHAR(20),
amount INTEGER,
wallet_id VARCHAR(50),
PRIMARY KEY(id)
);

--changeset akupriyanov:3
ALTER TABLE pay_operation
ADD CONSTRAINT FK_wallet_pay_operation
FOREIGN KEY (wallet_id)
REFERENCES wallet (uuid);