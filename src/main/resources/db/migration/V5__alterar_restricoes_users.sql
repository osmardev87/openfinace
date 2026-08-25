-- =============================================
-- Flyway Migration: V5
-- Permite que Senha e CPF sejam Nulos (Para Clientes e Fornecedores)
-- =============================================

-- 1. Remove a obrigatoriedade da senha
ALTER TABLE users ALTER COLUMN senha DROP NOT NULL;

-- 2. Remove a obrigatoriedade do CPF
ALTER TABLE users ALTER COLUMN cpf DROP NOT NULL;

-- 3. Adiciona uma coluna opcional para CNPJ (útil para Fornecedores PJ)
ALTER TABLE users ADD COLUMN cnpj VARCHAR(14) UNIQUE;