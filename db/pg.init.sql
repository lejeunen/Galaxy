-- kill all active db sessions
SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE pid <> pg_backend_pid() AND datname = '@dbName@';

-- drop database
DROP DATABASE IF EXISTS @dbName@;

-- re-create database
CREATE DATABASE @dbName@;
