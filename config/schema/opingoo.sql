
-- Author@Smruti Ranjan

-- 6 Jan 2019 --
CREATE USER 'op-session-mgr'@'localhost' IDENTIFIED BY 'opingoo_123';
GRANT ALL PRIVILEGES ON opingooDB . * TO 'op-session-mgr'@'localhost';