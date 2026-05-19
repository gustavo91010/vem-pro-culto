-- 1. Garante que não existam nulos se for transformar em chave de busca obrigatória
 ALTER TABLE usuario ALTER COLUMN auth_token SET NOT NULL; 

 -- 2. Cria o índice único para performance máxima em buscas por token/uuid
 CREATE UNIQUE INDEX idx_usuario_auth_token ON usuario (auth_token);

 -- 3. Adiciona um comentário para documentação no banco 
 COMMENT ON INDEX idx_usuario_auth_token IS 'Índice de performance para busca de usuário via UUID do serviço de Auth';

