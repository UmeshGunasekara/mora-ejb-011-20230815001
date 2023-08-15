----------------------------------------------------------------------------------
-- DATABSE
----------------------------------------------------------------------------------

DROP DATABASE IF EXISTS DB_MYDB_03;
CREATE DATABASE DB_MYDB_03;
USE DB_MYDB_03;

DROP TABLE IF EXISTS DB_MYDB_03;

----------------------------------------------------------------------------------
-- TABLES
----------------------------------------------------------------------------------

DROP TABLE IF EXISTS DEPARTMENT;
CREATE TABLE DEPARTMENT
(
    dept_id Int NOT NULL AUTO_INCREMENT UNIQUE KEY,
    dept_name Varchar(30) NOT NULL,
    PRIMARY KEY (dept_id)
);

INSERT INTO DEPARTMENT (dept_id, dept_name) VALUES
(1, 'HR'),
(2, 'IT');
----------------------------------------------------------------------------------
-- STORED PROCEDURE
----------------------------------------------------------------------------------

-- SP_ADD_USER_CATEGORY --

CREATE DEFINER='root'@'localhost' PROCEDURE 'SP_ADD_USER_CATEGORY'(category_name varchar(255), is_default Int)
BEGIN

DECLARE v1_id int DEFAULT 0;

  /*CUSTOM SQLEXCEPTION*/
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
BEGIN
ROLLBACK;  /* rollback any changes made in the transaction*/
RESIGNAL;  /* raise again the sql exception to the caller*/
END;

START TRANSACTION;

INSERT INTO SB_USER_CATEGORY (
    user_category_name,
    user_category_code,
    user_category_is_default,
    raw_last_update_date_time,
    raw_last_update_log_id,
    update_user_account_id,
    raw_show_status,
    raw_update_status,
    raw_delete_status,
    raw_active_status)
VALUES (category_name, 1, is_default, now(), 1, 1, 1, 1, 1, 1);

SET v1_id = LAST_INSERT_ID();

	IF (v1_id = 0 OR v1_id IS NULL) THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "SB_USER_CATEGORY details are wrong.!", MYSQL_ERRNO = 300;
END IF;

--   ROLLBACK;
COMMIT;
SELECT v1_id;
-- SELECT 'ok';
/*  COMMIT;*/
END

call SP_ADD_USER_CATEGORY("Test Call", 1)

----------------------------------------------------------------------------------
-- FUNCTIONS
----------------------------------------------------------------------------------

SET GLOBAL log_bin_trust_function_creators = 1;

-- FN_GET_ORDERED_UUID --

CREATE DEFINER= `root`@`localhost` FUNCTION `FN_GET_ORDERED_UUID`(`uuid` binary(36)) RETURNS binary(16)
BEGIN
RETURN UNHEX(CONCAT(SUBSTR(uuid, 15, 4),SUBSTR(uuid, 10, 4),SUBSTR(uuid, 1, 8),SUBSTR(uuid, 20, 4),SUBSTR(uuid, 25)));
END

select FN_GET_ORDERED_UUID(uuid())

-- FN_GET_UNIX_TIMESTAMP_IN_MILLISECONDS --

CREATE DEFINER=`root`@`localhost` FUNCTION `FN_GET_UNIX_TIMESTAMP_IN_MILLISECONDS`() RETURNS bigint
BEGIN
  DECLARE milliseconds BIGINT DEFAULT 0;

SELECT CONV(
               CONCAT(
                       SUBSTRING(uid,16,3),
                       SUBSTRING(uid,10,4),
                       SUBSTRING(uid,1,8)),
               16,10)
           DIV 10000
	  - (141427 * 24 * 60 * 60 * 1000) INTO milliseconds
FROM (SELECT UUID() uid) AS alias;

RETURN milliseconds;
END

select FN_GET_UNIX_TIMESTAMP_IN_MILLISECONDS()

-- FN_UUID_TO_BIN --

CREATE DEFINER=`root`@`localhost` FUNCTION `FN_UUID_TO_BIN`(`uuid` binary(36)) RETURNS binary(16)
BEGIN
RETURN
    UNHEX(
            CONCAT(
                    SUBSTR(uuid, 15, 4),
                    SUBSTR(uuid, 10, 4),
                    SUBSTR(uuid, 1, 8),
                    SUBSTR(uuid, 20, 4),
                    SUBSTR(uuid, 25)));
END

-- FN_UUID_FROM_BIN --

CREATE DEFINER=`root`@`localhost` FUNCTION `FN_UUID_FROM_BIN`(`bin` binary(16)) RETURNS binary(36)
BEGIN
RETURN
    CONCAT_WS('-',
              HEX(SUBSTR(bin, 5, 4)),
              HEX(SUBSTR(bin, 3, 2)),
              HEX(SUBSTR(bin, 1, 2)),
              HEX(SUBSTR(bin, 9, 2)),
              HEX(SUBSTR(bin, 11)));
END


---------------------
-- Special
---------------------

SHOW VARIABLES LIKE '%timeout%';

SHOW VARIABLES LIKE '%connect%';