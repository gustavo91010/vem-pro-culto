CREATE TABLE igreja_usuario (
    igreja_usuario_id BIGSERIAL PRIMARY KEY,

    igreja_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,

    papel VARCHAR(50) NOT NULL,

    CONSTRAINT fk_igreja_usuario_igreja
        FOREIGN KEY (igreja_id)
        REFERENCES igreja (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_igreja_usuario_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario (usuario)
        ON DELETE CASCADE,

    CONSTRAINT uk_igreja_usuario UNIQUE (igreja_id, usuario_id)
);
