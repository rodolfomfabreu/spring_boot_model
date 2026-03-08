-- Script para corrigir a coluna user_id na tabela usuarios
-- Execute este script no MySQL antes de rodar a aplicação

USE mydb;

-- Primeiro, limpar dados corrompidos (se necessário)
DELETE FROM usuarios_roles;
DELETE FROM usuarios;

-- Alterar a coluna para VARCHAR(36)
ALTER TABLE usuarios MODIFY COLUMN user_id VARCHAR(36);

-- Se a tabela usuarios_roles também tiver user_id, alterar também
ALTER TABLE usuarios_roles MODIFY COLUMN user_id VARCHAR(36);
