DROP DATABASE IF EXISTS lince;
CREATE DATABASE IF NOT EXISTS lince;

USE lince;

DROP TABLE IF EXISTS contato;
CREATE TABLE IF NOT EXISTS contato(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    fixo varchar(45) NOT NULL,
    celular varchar(45) NOT NULL,
    email varchar(45) NOT NULL,
    log DATETIME DEFAULT current_timestamp
);



DROP TABLE IF EXISTS paciente;
CREATE TABLE IF NOT EXISTS paciente(
    id INT PRIMARY KEY AUTO_INCREMENT,
    peso VARCHAR(50) NOT NULL,
    grupo_manejo VARCHAR(45) NOT NULL,
    raca VARCHAR(45) NOT NULL,
    log DATETIME DEFAULT current_timestamp
);

DROP TABLE IF EXISTS equipamento;
CREATE TABLE IF NOT EXISTS equipamento(
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL,
    numero_serie varchar(20) NOT NULL,
    marca VARCHAR(45) NOT NULL,
    modelo VARCHAR(45) NOT NULL,
    tipo_sinal VARCHAR(45) NOT NULL,
    escala_x VARCHAR(45) NOT NULL,
    escala_y VARCHAR(45) NOT NULL,
    log DATETIME DEFAULT current_timestamp
);

DROP TABLE IF EXISTS tecnico;
CREATE TABLE IF NOT EXISTS tecnico(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome varchar(45) NOT NULL,
    cpf varchar(11) not null,
    id_contato int NOT NULL UNIQUE,
    id_endereco int NOT NULL UNIQUE,
    log DATETIME DEFAULT current_timestamp
);

DROP TABLE IF EXISTS empresa;
CREATE TABLE IF NOT EXISTS empresa(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    id_contato INT NOT NULL UNIQUE,
    id_endereco INT NOT NULL UNIQUE,
    log DATETIME DEFAULT current_timestamp
);

DROP TABLE IF EXISTS sessao;
CREATE TABLE IF NOT EXISTS sessao(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    dt_criacao_sessao DATETIME NOT NULL,
    id_equipamento INT NOT NULL,
    id_empresa INT NOT NULL,
    CONSTRAINT fk_sessao_id_equipamento FOREIGN KEY (id_equipamento) REFERENCES equipamento (id),
    CONSTRAINT fk_sessao_id_empresa 	FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    log DATETIME DEFAULT current_timestamp
);

DROP TABLE IF EXISTS tecnico_sessao;
CREATE TABLE IF NOT EXISTS tecnico_sessao(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_tecnico INT NOT NULL,
    id_sessao INT NOT NULL,
    CONSTRAINT fk_tecnico_sessao_id_tecnico FOREIGN KEY (id_tecnico) REFERENCES tecnico (id),
    CONSTRAINT fk_tecnico_sessao_id_sessao 	FOREIGN KEY (id_sessao) REFERENCES sessao (id),
    log DATETIME DEFAULT current_timestamp
);

DROP TABLE IF EXISTS imagem;
CREATE TABLE IF NOT EXISTS imagem(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_paciente INT NOT NULL UNIQUE,
    tipo VARCHAR(45) NOT NULL,
    CONSTRAINT fk_imagem_id_paciente 	FOREIGN KEY (id_paciente) REFERENCES paciente (id),
    log DATETIME DEFAULT current_timestamp
);

DROP TABLE IF EXISTS imagem_sessao;
CREATE TABLE IF NOT EXISTS imagem_sessao(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_sessao INT NOT NULL,
    id_imagem INT NOT NULL,
	CONSTRAINT fk_imagem_id_sessao 		FOREIGN KEY (id_sessao) REFERENCES sessao (id),
    CONSTRAINT fk_imagem_id_imagem 		FOREIGN KEY (id_imagem) REFERENCES imagem (id),
    log DATETIME DEFAULT current_timestamp
);

DROP TABLE IF EXISTS `pais`;
CREATE TABLE `pais` (
  `id` int(8) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `nome` varchar(100) NOT NULL
);

DROP TABLE IF EXISTS `estado`;
CREATE TABLE `estado` (
  `id` int(8) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `nome` varchar(255) NOT NULL,
  `id_pais` int(8)  NOT NULL,
   CONSTRAINT `fk_estado_id_pais` FOREIGN KEY (id_pais) REFERENCES pais (id)
);

DROP TABLE IF EXISTS endereco;
CREATE TABLE IF NOT EXISTS endereco(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_pais INT NOT NULL,
    id_estado INT NOT NULL,
    cidade varchar(45) NOT NULL,
    bairro varchar(45) NOT NULL,
    rua varchar(45) NOT NULL,
    numero int,
    log DATETIME DEFAULT current_timestamp,
    CONSTRAINT `fk_endereco_id_estado` FOREIGN KEY (id_estado) REFERENCES estado (id),
    CONSTRAINT `fk_endereco_id_pais` FOREIGN KEY (id_pais) REFERENCES pais (id)
);


INSERT INTO `pais` (`id`, `nome`) VALUES
(11,'Argentina'),
(27,'Bolivia'),
(31,'Brasil'),
(48,'Colombia'),
(172,'Paraguai');

INSERT INTO `estado` (`nome`, `id_pais`) VALUES
("Buenos Aires", 11),
("Catamarca", 11),
("Chaco", 11),
("Chubut", 11),
("Córdoba", 11),
("Corrientes", 11),
("Entre Ríos", 11),
("Formosa", 11),
("Jujuy", 11),
("La Pampa", 11),
("La Rioja", 11),
("Mendoza", 11),
("Misiones", 11),
("Neuquén", 11),
("Río Negro", 11),
("Salta", 11),
("San Juan", 11),
("San Luis", 11),
("Santa Cruz", 11),
("Santa Fe", 11),
("Santiago del Estero", 11),
("Tierra del Fuego", 11),
("Tucumán", 11),
("Alto Paraguay", 172),
("Alto Paraná", 172),
("Amambay", 172),
("Boquerón", 172),
("Caaguazú", 172),
("Caazapá", 172),
("Canindeyú", 172),
("Central", 172),
("Concepción", 172),
("Cordillera", 172),
("Guairá", 172),
("Itapúa", 172),
("Misiones", 172),
("Ñeembucú", 172),
("Paraguarí", 172),
("Presidente Hayes", 172),
("San Pedro", 172),
("Beni", 27),
("Chuquisaca", 27),
("Cochabamba", 27),
("La Paz", 27),
("Oruro", 27),
("Pando", 27),
("Potosí", 27),
("Santa Cruz", 27),
("Tarija", 27),
("Acre", 31),
("Alagoas", 31),
("Amapá", 31),
("Amazonas", 31),
("Bahia", 31),
("Ceará", 31),
("Espírito Santo", 31),
("Distrito Federal", 31),
("Goiás", 31),
("Maranhão", 31),
("Mato Grosso do Sul", 31),
("Mato Grosso", 31),
("Minas Gerais", 31),
("Pará", 31),
("Paraíba", 31),
("Paraná", 31),
("Pernambuco", 31),
("Piauí", 31),
("Rio de Janeiro", 31),
("Rio Grande do Norte", 31),
("Rio Grande do Sul", 31),
("Rondônia", 31),
("Santa Catarina", 31),
("São Paulo", 31),
("Sergipe", 31),
("Tocantins", 31),
("Amazonas", 48),
("Antioquia", 48),
("Arauca", 48),
("Archipelago of Saint Andréws Providence and Saint Catalina", 48),
("Atlántico", 48),
("Bolívar", 48),
("Boyacá", 48),
("Caldas", 48),
("Caquetá", 48),
("Casanare", 48),
("Cauca",48),
("Cesar", 48),
("Chocó", 48),
("Córdoba", 48),
("Cundinamarca", 48),
("Guainía", 48),
("Guaviare", 48),
("La Guajira", 48),
("Magdalena", 48),
("Meta", 48),
("Nariño", 48),
("Norte de Santander", 48),
("Putumayo", 48),
("Quindío", 48),
("Risaralda", 48),
("Santander", 48),
("Sucre", 48),
("Tolima", 48),
("Valle del Cauca", 48),
("Vaupés", 48),
("Vichada", 48);

