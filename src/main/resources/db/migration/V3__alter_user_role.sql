-- =============================================
-- Flyway Migration: V3
-- Adiciona campo de Perfil (Role) na tabela users
-- =============================================

ALTER TABLE users 
    ADD COLUMN role VARCHAR(20) NOT NULL DEFAULT 'CLIENTE'
    CHECK (role IN ('CLIENTE', 'VENDEDOR', 'FORNECEDOR', 'ADMIN'));