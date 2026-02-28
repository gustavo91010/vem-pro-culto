CREATE TABLE igreja (
    id BIGSERIAL PRIMARY KEY,
    nome_fantasia VARCHAR(100),
    rezao_social VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    ativo BOOLEAN,
    cnpj VARCHAR(14),
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    registrado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Endereco (Embedded)
    logradouro VARCHAR(255),
    numero VARCHAR(50),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(100),
    cep VARCHAR(20),
    pais VARCHAR(100),
    latitude NUMERIC(10,8),
    longitude NUMERIC(11,8)
);

-- Telefones
CREATE TABLE igreja_telefone (
    igreja_id BIGINT NOT NULL,
    numero VARCHAR(50),
    tipo VARCHAR(50),

    CONSTRAINT fk_igreja_telefone
        FOREIGN KEY (igreja_id)
        REFERENCES igreja (id)
        ON DELETE CASCADE
);

-- Redes sociais
CREATE TABLE igreja_rede_social (
    igreja_id BIGINT NOT NULL,
    url VARCHAR(255),
    tipo VARCHAR(100),

    CONSTRAINT fk_igreja_rede_social
        FOREIGN KEY (igreja_id)
        REFERENCES igreja (id)
        ON DELETE CASCADE
);
