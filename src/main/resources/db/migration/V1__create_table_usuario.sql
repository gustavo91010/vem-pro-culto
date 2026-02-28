CREATE TABLE usuario (
    usuario BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL,
    ativo BOOLEAN,
    auth_token UUID NOT NULL,
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

-- Telefones (ElementCollection)
CREATE TABLE usuario_telefone (
    usuario_id BIGINT NOT NULL,
    numero VARCHAR(50),
    tipo VARCHAR(50),

    CONSTRAINT fk_usuario_telefone
        FOREIGN KEY (usuario_id)
        REFERENCES usuario (usuario)
        ON DELETE CASCADE
);

-- Redes Sociais (ElementCollection)
CREATE TABLE usuario_rede_social (
    usuario_id BIGINT NOT NULL,
    url VARCHAR(255),
    tipo VARCHAR(100),

    CONSTRAINT fk_usuario_rede_social
        FOREIGN KEY (usuario_id)
        REFERENCES usuario (usuario)
        ON DELETE CASCADE
);
