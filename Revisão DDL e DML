-- Crie uma tabela chamada 'Universidade'
create schema Universidade;
use Universidade;

-- Uso desse codigo para liberar todos os comandos do WHERE:
SET SQL_SAFE_UPDATES = 0;

-- Criar tabela professores:
CREATE TABLE professores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    departamento VARCHAR(100),
    data_contratacao DATE
);

-- Altere a tabela 'disciplinas' para adicionar coluna salario(decimal com 10 dígitos e 2 casas decimais):
ALTER TABLE professores ADD COLUMN salario DECIMAL(10, 2);

-- Remover coluna departamento:
ALTER TABLE professores DROP COLUMN departamento;

-- Crie uma nova tabela chamada 'disciplinas'
CREATE TABLE disciplinas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    carga_horaria INT,
    id_professor INT,
    FOREIGN KEY (id_professor) REFERENCES professores(id)
);

-- Altere a tabela 'disciplinas' para adicionar uma coluna 'descricao' (varchar de 255 caracteres)
ALTER TABLE disciplinas ADD COLUMN descricao VARCHAR(255);

-- Exclua a tabela 'disciplinas'
DROP TABLE disciplinas;

-- Criar tabela 'departamentos'
CREATE TABLE departamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    bloco VARCHAR(100),
    data_criacao DATE
);

-- Inserção de Dados na tabela 'departamentos'
INSERT INTO departamentos (nome, bloco, data_criacao)
VALUES
    ('Engenharia', 'Bloco E', '2005-03-15'),
    ('Administração', 'Bloco F', '2010-06-20');

-- Exibir tabela com os dados inseridos
select * from departamentos;

-- Adicionar um novo empregado, implica em criar outra tabela chamada 'empregados'
CREATE TABLE empregados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    data_admissao DATE,
    salario DECIMAL(10, 2),
    departamento VARCHAR(100)
);

-- Adicionar novo empregado
INSERT INTO empregados (nome, data_admissao, salario, departamento)
VALUES ('Lucas Fernandes', '2022-09-01', 7500.00, 'Tecnologia da Informação');

-- Adicionar tabela 'bibliotecaLivro' para adicionar novo livro
CREATE TABLE bibliotecaLivro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    ano_publicacao INT,
    categoria VARCHAR(100),
    isbn VARCHAR(20),
    editora VARCHAR(100)
);

-- Adicionar novo livro na tabela 'bibliotecaLivro'
INSERT INTO bibliotecaLivro (nome, ano_publicacao, categoria, isbn, editora)
VALUES ('Banco de Dados Avançado', 2021, 'Banco de Dados', '123456789', 'Novatec');

-- Exibir tabela com os dados inseridos
select * from bibliotecaLivro;

-- Inserir 'Carlos Júnior' na tabela empregados
INSERT INTO empregados (nome, data_admissao, salario, departamento)
VALUES ('Carlos Júnior', '2023-01-01', 5000.00, 'Marketing');

-- Exibir tabela com os dados inseridos
select * from editora;

-- Atualize o salário do empregado 'Carlos Júnior' para 5500.00
UPDATE empregados
SET salario = 5300.00
WHERE nome = 'Carlos Júnior';

-- Criar tabela 'editora' para ter inserir dados
CREATE TABLE editora (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cidade VARCHAR(100)
);

-- Inserir dados da tabela 'editora'
INSERT INTO editora (nome, cidade)
VALUES ('Intrinseca', 'Rio de Janeiro');

-- Atualize a cidade da editora 'Intrinseca' para 'Brasília'
UPDATE editora
SET cidade = 'Brasília'
WHERE nome = 'Intrinseca';

-- Preciso inserir alguns na tabela 'bibliotecaLivro'
INSERT INTO bibliotecaLivro (nome, ano_publicacao, categoria, isbn, editora) 
VALUES 
    ('Introdução ao SQL', 2010, 'Banco de Dados', 9788575225000, 'Novatec'),
    ('Algoritmos e Estruturas de Dados', 2012, 'Programação', 9788595145001, 'Campus'),
    ('Machine Learning para Iniciantes', 2016, 'Inteligência Artificial', 9788536505002, 'Alta Books'),
    ('Desenvolvimento Web Moderno', 2018, 'Programação', 9788550805003, 'Casa do Código'),
    ('Big Data e Analytics', 2022, 'Banco de Dados', 9788547005004, 'Pearson');

-- Remova todos os livros da tabela 'bibliotecaLivro' que foram publicados antes de 2015
DELETE FROM bibliotecaLivro 
WHERE ano_publicacao < 2015;

-- Preciso inserir alguns na tabela 'empregados'
INSERT INTO empregados (nome, data_admissao, salario, departamento) 
VALUES 
    ('Mariana Barbosa', '2019-11-15', 4800.00, 'Administração'),
    ('João Souza', '2015-07-10', 8200.00, 'Engenharia'),
    ('Ana Pereira', '2016-03-25', 6500.00, 'Marketing'),
	('Pedro Silva', '2024-01-10', 6000.00, 'Financeiro'),
    ('Laura Costa', '2024-02-05', 7200.00, 'TI');

-- Liste todos os empregados ordenados por salário de forma decrescente
SELECT * FROM empregados 
ORDER BY salario DESC;

-- Recupere todos os empregados cujo salário seja maior que 5000.00 e que tenham sido admitidos antes de 2020.
SELECT * FROM empregados
WHERE salario > 5000.00
AND data_admissao < '2020-01-01';

-- Liste todos os departamentos e a quantidade de empregados em cada um (use 'GROUP BY')
SELECT departamento, COUNT(*) AS quantidade_empregados 
FROM empregados 
GROUP BY departamento;

-- Exiba os nomes dos departamentos e o total de salários pagos por cada um
SELECT departamento, SUM(salario) AS total_salarios 
FROM empregados 
GROUP BY departamento;

-- Encontrar o maior salário entre os empregados
SELECT MAX(salario) AS maior_salario FROM empregados;

-- Inserir dados na tabela 'editora' sabendo da remocao dos livros publicados antes de 2015
INSERT INTO editora (nome, cidade) 
VALUES 
    ('Novatec', 'São Paulo'),
    ('Campus', 'Rio de Janeiro'),
    ('Alta Books', 'São Paulo'),
    ('Casa do Código', 'São Paulo'),
    ('Pearson', 'Londres');

-- Listar todos os livros e suas respectivas editoras (JOIN)


-- Primeiro crie tabela alunos e emprestimos e depois ensira os dados
CREATE TABLE alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE
);

CREATE TABLE emprestimos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_aluno INT,
    id_livro INT,
    data_emprestimo DATE,
    data_devolucao DATE,
    FOREIGN KEY (id_aluno) REFERENCES alunos(id),
    FOREIGN KEY (id_livro) REFERENCES bibliotecaLivro(id)
);

INSERT INTO alunos (nome, data_nascimento) 
VALUES 
    ('João Silva', '1995-05-10'),
    ('Maria Oliveira', '1993-03-25'),
    ('Carlos Souza', '2000-07-15');
    
INSERT INTO emprestimos (id_aluno, id_livro, data_emprestimo, data_devolucao) 
VALUES 
    (1, 1, '2023-12-01', '2023-12-15'),  -- João Silva pegou o livro 1 e devolveu em 15/12/2023
    (2, 4, '2023-11-20', '2023-12-05'),  -- Maria Oliveira pegou o livro 2 e devolveu em 05/12/2023
    (3, 6, '2023-12-10', '2023-12-25');  -- Carlos Souza pegou o livro 3 e devolveu em 25/12/2023

-- Liste todos os alunos que pegaram livros emprestados e a data de devolução desses empréstimos.
SELECT 
    a.nome AS aluno_nome,
    l.nome AS livro_nome,
    e.data_emprestimo,
    e.data_devolucao
FROM 
    emprestimos e
JOIN 
    alunos a ON e.id_aluno = a.id
JOIN 
    bibliotecaLivro l ON e.id_livro = l.id;

-- Liste os livros emprestados e o nome dos alunos que pegaram esses livros
SELECT 
    l.nome AS livro_nome,
    a.nome AS aluno_nome
FROM 
    emprestimos e
JOIN 
    alunos a ON e.id_aluno = a.id
JOIN 
    bibliotecaLivro l ON e.id_livro = l.id;

-- Exiba a média salarial dos empregados por departamento
SELECT 
    departamento,
    AVG(salario) AS media_salarial
FROM 
    empregados
GROUP BY 
    departamento;

-- Encontre os nomes dos autores que escreveram livros publicados pela editora Novatec
select * from bibliotecalivro;

ALTER TABLE bibliotecaLivro ADD COLUMN autor VARCHAR(100);

UPDATE bibliotecaLivro
SET autor = 'Luisa Melo'
WHERE id = 6;

SELECT DISTINCT autor
FROM bibliotecaLivro
WHERE editora = 'Novatec';
