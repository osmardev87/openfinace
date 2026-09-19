-- =============================================
-- Flyway Migration: V6
-- Adiciona campo de empresa na tabela users
-- Adiciona campo de logo na tabela users
-- =============================================

ALTER TABLE users 
    ADD COLUMN empresa VARCHAR(100) NULL ,
    ADD COLUMN logo VARCHAR(255) NULL