-- 创建数据库 --
DROP DATABASE IF EXISTS trade;
CREATE DATABASE IF NOT EXISTS trade CHARACTER SET utf8;
-- 选中数据库 --
USE trade;
-- 创建用户表 --
CREATE TABLE IF NOT EXISTS user
(
    pk_id       INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(32) NOT NULL,
    gender      VARCHAR(32) NULL,
    password    VARCHAR(64) NOT NULL,
    phoneNumber VARCHAR(32) NULL,
    location    VARCHAR(64) NULL,
    status      INTEGER   DEFAULT 1,
    createTIme  TIMESTAMP DEFAULT now(),
    modTime     TIMESTAMP DEFAULT now()
);
-- 创建订单表 --
CREATE TABLE IF NOT EXISTS good
(
    pk_id       INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(32)  NOT NULL,
    image       VARCHAR(32)  NOT NULL,
    description VARCHAR(512) NULL,
    price       INTEGER      NOT NULL,
    phoneNumber VARCHAR(32)  NOT NULL,
    location    VARCHAR(64)  NULL,
    createTime  TIMESTAMP DEFAULT now(),
    owner       VARCHAR(32)  NOT NULL,
    status      INTEGER   DEFAULT 1
);
-- 创建公告表 --
CREATE TABLE IF NOT EXISTS notice
(
    pk_id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(64)  NOT NULL,
    description VARCHAR(512) NOT NULL,
    date        TIMESTAMP DEFAULT now(),
    status      INTEGER   DEFAULT 1
)