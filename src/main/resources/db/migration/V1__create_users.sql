-- =============================================
-- Flyway Migration: V1
-- Criação da tabela de users
-- =============================================

CREATE TABLE IF NOT EXISTS users (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    nome VARCHAR(100) NOT NULL,

    email VARCHAR(150) NOT NULL UNIQUE,

    senha VARCHAR(255) NOT NULL,

    cpf VARCHAR(11) NOT NULL UNIQUE,

    telefone VARCHAR(20),

    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    ativo BOOLEAN NOT NULL DEFAULT TRUE

);