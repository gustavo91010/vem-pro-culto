-- Remove constraint antiga (igreja_id, usuario_id)
ALTER TABLE igreja_usuario
DROP CONSTRAINT IF EXISTS uk_igreja_usuario;

-- Cria nova constraint incluindo papel
ALTER TABLE igreja_usuario
ADD CONSTRAINT uk_igreja_usuario 
UNIQUE (igreja_id, usuario_id, papel);
