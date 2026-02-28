CREATE TABLE atividade (
    id BIGSERIAL PRIMARY KEY,

    igreja_id BIGINT NOT NULL,

    tipo VARCHAR(30) NOT NULL,
    descricao VARCHAR(200),
    horario TIMESTAMP NOT NULL,

    CONSTRAINT fk_atividade_igreja
        FOREIGN KEY (igreja_id)
        REFERENCES igreja (id)
        ON DELETE CASCADE
);
