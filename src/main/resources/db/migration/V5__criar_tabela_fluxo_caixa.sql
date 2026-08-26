-- =============================================
-- Flyway Migration: V6
-- Tabela de Transações Financeiras (Fluxo de Caixa)
-- =============================================

CREATE TABLE IF NOT EXISTS transacoes_financeiras (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,                        -- Operador/Dono do registro
    parceiro_id UUID,                             -- Cliente ou Fornecedor associado (tabela users)
    entrada_nota_id UUID,                         -- Opcional: Se for pagamento de uma Nota Fiscal
    
    descricao VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL DEFAULT 'OUTROS', -- Ex: 'ESTOQUE', 'VENDA', 'SERVICO', 'PRO-LABORE'
    
    valor DECIMAL(12,2) NOT NULL CHECK (valor > 0),
    
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('RECEITA', 'DESPESA')), -- RECEITA (Entrada) ou DESPESA (Saída)
    
    status VARCHAR(15) NOT NULL DEFAULT 'PENDENTE' 
        CHECK (status IN ('PENDENTE', 'PAGO', 'CANCELADO')), -- PAGO/RECEBIDO ou PENDENTE
        
    forma_pagamento VARCHAR(20)
        CHECK (forma_pagamento IN ('PIX', 'BOLETO', 'DEPOSITO', 'DINHEIRO', 'CARTAO_CREDITO', 'CARTAO_DEBITO', 'OUTROS')),
        
    data_vencimento DATE NOT NULL,                -- Data limite de pagamento/recebimento
    data_pagamento TIMESTAMP,                     -- Quando de fato o dinheiro entrou/saiu (preenchido ao pagar)
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    -- Chaves Estrangeiras
    CONSTRAINT fk_transacao_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_transacao_parceiro
        FOREIGN KEY (parceiro_id) REFERENCES users(id) ON DELETE SET NULL,
        
    CONSTRAINT fk_transacao_nota
        FOREIGN KEY (entrada_nota_id) REFERENCES entrada_nota(id) ON DELETE SET NULL
);

-- Índices para melhorar a performance de relatórios financeiros por período
CREATE INDEX idx_transacoes_data_vencimento ON transacoes_financeiras(data_vencimento);
CREATE INDEX idx_transacoes_tipo_status ON transacoes_financeiras(tipo, status);