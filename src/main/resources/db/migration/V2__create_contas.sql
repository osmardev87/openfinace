CREATE TABLE contas
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    descricao TEXT,

    valor NUMERIC(12,2) NOT NULL,

    data DATE,

    data_vencimento DATE,

    tipo VARCHAR(20) NOT NULL DEFAULT 'ENTRADA'
        CHECK (tipo IN ('ENTRADA', 'SAIDA')),

    forma_pagamento VARCHAR(20)
        CHECK (forma_pagamento IN ('PIX', 'BOLETO', 'DEPOSITO', 'DINHEIRO', 'OUTROS')),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_contas_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);