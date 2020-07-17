DROP DATABASE IF EXISTS lince;
CREATE DATABASE IF NOT EXISTS lince;

USE lince;

DROP TABLE IF EXISTS contato;
CREATE TABLE IF NOT EXISTS contato(
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    fixo varchar(45) NOT NULL,
	celular_1 varchar(45) NOT NULL,
	celular_2 varchar(45) NOT NULL,
    email varchar(45) NOT NULL
);

DROP TABLE IF EXISTS endereco;
CREATE TABLE IF NOT EXISTS endereco(
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    pais varchar(45) NOT NULL,
    estado varchar(2) NOT NULL,
    cidade varchar(45) NOT NULL,
	bairro varchar(45) NOT NULL,
    rua varchar(45) NOT NULL,
    numero int,
    complemento varchar(45)
);

DROP TABLE IF EXISTS paciente;
CREATE TABLE IF NOT EXISTS paciente(
	id INT PRIMARY KEY AUTO_INCREMENT,
    peso VARCHAR(50) NOT NULL,
    grupo_manejo VARCHAR(45) NOT NULL,
    raca VARCHAR(45) NOT NULL
);

DROP TABLE IF EXISTS equipamento;
CREATE TABLE IF NOT EXISTS equipamento(
	id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL,
    marca VARCHAR(45) NOT NULL,
    modelo VARCHAR(45) NOT NULL,
    tipo_sinal VARCHAR(45) NOT NULL,
    escala_x VARCHAR(45) NOT NULL,
    escala_y VARCHAR(45) NOT NULL
);

DROP TABLE IF EXISTS tecnico;
CREATE TABLE IF NOT EXISTS tecnico(
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome varchar(45) NOT NULL,
    id_contato int NOT NULL UNIQUE,
    id_endereco int NOT NULL UNIQUE
);

DROP TABLE IF EXISTS empresa;
CREATE TABLE IF NOT EXISTS empresa(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    id_contato INT NOT NULL UNIQUE,
    id_endereco INT NOT NULL UNIQUE
);

DROP TABLE IF EXISTS sessao;
CREATE TABLE IF NOT EXISTS sessao(
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    dt_criacao_sessao DATETIME NOT NULL,
    id_equipamento INT NOT NULL,
    id_empresa INT NOT NULL,
    CONSTRAINT fk_sessao_id_equipamento FOREIGN KEY (id_equipamento) REFERENCES equipamento (id),
    CONSTRAINT fk_sessao_id_empresa 	FOREIGN KEY (id_empresa) REFERENCES empresa (id)
);

DROP TABLE IF EXISTS tecnico_sessao;
CREATE TABLE IF NOT EXISTS tecnico_sessao(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_tecnico INT NOT NULL,
    id_sessao INT NOT NULL,
    CONSTRAINT fk_tecnico_sessao_id_tecnico FOREIGN KEY (id_tecnico) REFERENCES tecnico (id),
    CONSTRAINT fk_tecnico_sessao_id_sessao 	FOREIGN KEY (id_sessao) REFERENCES sessao (id)
);

DROP TABLE IF EXISTS imagem;
CREATE TABLE IF NOT EXISTS imagem(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_paciente INT NOT NULL UNIQUE,
    tipo VARCHAR(45) NOT NULL,
    CONSTRAINT fk_imagem_id_paciente 	FOREIGN KEY (id_paciente) REFERENCES paciente (id)
);

DROP TABLE IF EXISTS imagem_sessao;
CREATE TABLE IF NOT EXISTS imagem_sessao(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_sessao INT NOT NULL,
    id_imagem INT NOT NULL,
	CONSTRAINT fk_imagem_id_sessao 		FOREIGN KEY (id_sessao) REFERENCES sessao (id),
    CONSTRAINT fk_imagem_id_imagem 		FOREIGN KEY (id_imagem) REFERENCES imagem (id)
);

INSERT INTO contato 		(fixo, celular_1, celular_2, email) 							VALUES ('35632386','19982037426', '19984168533', 'eletronica.bruno@hotmail.com');
INSERT INTO contato 		(fixo, celular_1, celular_2, email) 							VALUES ('35652535','19982032674', '19984163385', 'bruno.eletronica@hotmail.com');
INSERT INTO endereco 		(bairro, cidade, pais, estado, rua, numero, complemento) 		VALUES ('Vila Malaquias','Pirassununga','Brasil','SP','Osmarina Sedeh Padilha','305','Casa do Bruno');
INSERT INTO endereco 		(bairro, cidade, pais, estado, rua, numero, complemento) 		VALUES ('Vila Santa Teresinha','Pirassununga','Brasil','SP','Joaquim Cristóvão','195','Casa do Juresmildo');
INSERT INTO paciente 		(peso, grupo_manejo, raca) 										VALUES ('35', 'Turma A', 'Vaca Zebu');
INSERT INTO equipamento 	(descricao, marca, modelo, tipo_sinal, escala_x, escala_y) 		VALUES ('Furadeira','Bosch','M1 Abrams','Digital','10','-10');
INSERT INTO tecnico 		(nome, id_endereco, id_contato) 								VALUES ('Juresmildo Tarantino','1','1');
INSERT INTO empresa 		(nome, id_endereco, id_contato) 								VALUES ('Juresmildo e sobrinhos LTDA','2','2');
INSERT INTO sessao 			(dt_criacao_sessao, id_equipamento, id_empresa)	 				VALUES ('2020-06-11','1','1');
INSERT INTO imagem 			(id_paciente, tipo) 											VALUES ('1','Raio-X');
INSERT INTO imagem_sessao 	(id_imagem, id_sessao) 											VALUES ('1', '1');
INSERT INTO tecnico_sessao 	(id_tecnico, id_sessao) 										VALUES ('1', '1');

SELECT tecnico_sessao.id_tecnico, tecnico.nome FROM tecnico_sessao INNER JOIN tecnico ON tecnico_sessao.id_tecnico = tecnico.id;

SELECT tecnico_sessao.id AS 'tecnico_id' FROM tecnico_sessao WHERE tecnico_sessao.id_sessao = '1';

/*
	Fazendo select nas tabelas através da tabela N:N (tabela de relacionamento) entre tecnico e sessão para achar o nome do técnico pelo id da sessão.
*/
SELECT 	tecnico.nome 
FROM 	tecnico 
WHERE 	tecnico.id = (
	SELECT tecnico_sessao.id_tecnico FROM tecnico_sessao WHERE tecnico_sessao.id_sessao = '1'
);
