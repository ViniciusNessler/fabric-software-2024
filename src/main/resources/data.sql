-- Inserindo Professores
INSERT INTO professor (id, nome, email, telefone)
SELECT 1, 'Prof. João da Silva', 'joao.silva@example.com', '123456789'
    WHERE NOT EXISTS (SELECT 1 FROM professor WHERE id = 1);

INSERT INTO professor (id, nome, email, telefone)
SELECT 2, 'Prof. Maria Oliveira', 'maria.oliveira@example.com', '987654321'
    WHERE NOT EXISTS (SELECT 1 FROM professor WHERE id = 2);

-- Inserindo Alunos
INSERT INTO aluno (id, nome, data_nascimento, email, telefone)
SELECT 1, 'Ana Costa', '2000-05-15', 'ana.costa@example.com', '111222333'
    WHERE NOT EXISTS (SELECT 1 FROM aluno WHERE id = 1);

INSERT INTO aluno (id, nome, data_nascimento, email, telefone)
SELECT 2, 'Bruno Santos', '1999-08-21', 'bruno.santos@example.com', '444555666'
    WHERE NOT EXISTS (SELECT 1 FROM aluno WHERE id = 2);

INSERT INTO aluno (id, nome, data_nascimento, email, telefone)
SELECT 3, 'Carla Souza', '2001-02-10', 'carla.souza@example.com', '777888999'
    WHERE NOT EXISTS (SELECT 1 FROM aluno WHERE id = 3);

-- Inserindo Matérias
INSERT INTO materia (id, nome, descricao, professor_id)
SELECT 1, 'Matemática', 'Aulas de Matemática', 1
    WHERE NOT EXISTS (SELECT 1 FROM materia WHERE id = 1);

INSERT INTO materia (id, nome, descricao, professor_id)
SELECT 2, 'Português', 'Aulas de Português', 2
    WHERE NOT EXISTS (SELECT 1 FROM materia WHERE id = 2);

-- Relacionando Alunos com Matérias
INSERT INTO materia_aluno (materia_id, aluno_id)
SELECT 1, 1
    WHERE NOT EXISTS (SELECT 1 FROM materia_aluno WHERE materia_id = 1 AND aluno_id = 1);

INSERT INTO materia_aluno (materia_id, aluno_id)
SELECT 1, 2
    WHERE NOT EXISTS (SELECT 1 FROM materia_aluno WHERE materia_id = 1 AND aluno_id = 2);

INSERT INTO materia_aluno (materia_id, aluno_id)
SELECT 2, 2
    WHERE NOT EXISTS (SELECT 1 FROM materia_aluno WHERE materia_id = 2 AND aluno_id = 2);

INSERT INTO materia_aluno (materia_id, aluno_id)
SELECT 2, 3
    WHERE NOT EXISTS (SELECT 1 FROM materia_aluno WHERE materia_id = 2 AND aluno_id = 3);
