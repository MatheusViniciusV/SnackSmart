--
-- Banco de dados: `snacksmart`
--

CREATE DATABASE IF NOT EXISTS `snacksmart` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `snacksmart`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gerente`
--

DROP TABLE IF EXISTS `gerente`;
CREATE TABLE `gerente` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(256) NOT NULL,
    `senha` VARCHAR(256) NOT NULL,
    `cpf` CHAR(11) NOT NULL,
    `rg` CHAR(10) NULL,
    `telefone` VARCHAR(14) NULL,
    `email` VARCHAR(128) NULL,
    CONSTRAINT `uk_cpf` UNIQUE (`cpf`)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `locatario`
--

DROP TABLE IF EXISTS `locatario`;
CREATE TABLE `locatario` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(256) NOT NULL,
    `senha` VARCHAR(256) NOT NULL,
    `cpf` CHAR(11) NOT NULL,
    `rg` CHAR(10) NULL,
    `telefone` VARCHAR(14) NULL,
    `email` VARCHAR(128) NULL,
    CONSTRAINT `uk_cpf` UNIQUE (`cpf`)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
CREATE TABLE `fornecedor` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(256) NOT NULL,
    `telefone` VARCHAR(14) NULL,
    `email` VARCHAR(128) NULL
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `lote`
--

DROP TABLE IF EXISTS `lote`;
CREATE TABLE `lote` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `tipo_produto` VARCHAR(128) NOT NULL,
    `quantidade` INT NOT NULL,
    `preco_compra` FLOAT NOT NULL,
    `preco_venda` FLOAT NOT NULL,
    `fornecedor__fk` INT NOT NULL,
    `locatario__fk` INT NOT NULL,
    FOREIGN KEY (`fornecedor__fk`) REFERENCES `fornecedor` (`pk`),
    FOREIGN KEY (`locatario__fk`) REFERENCES `locatario` (`pk`)
);

-- Observação: Preço de compra e venda são relativos a uma unidade do produto

-- --------------------------------------------------------

--
-- Estrutura da tabela `maquina`
--

DROP TABLE IF EXISTS `maquina`;
CREATE TABLE `maquina` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(128) NULL,
    `codigo` INT, 
    `imagem` LONGBLOB NULL,
    `localizacao` VARCHAR(256) NULL,
    `aluguel` FLOAT NOT NULL,
    `locatario__fk` INT NULL,
    `status` ENUM('ALUGADA', 'EM_MANUTENCAO', 'AGUARDANDO_MANUTENCAO', 'DISPONIVEL', 'REMOVIDA') NOT NULL,
    `tipo` ENUM('REFRIGERADA', 'NAO_REFRIGERADA') NOT NULL,    
    FOREIGN KEY (`locatario__fk`) REFERENCES `locatario` (`pk`)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `contrato`
--

DROP TABLE IF EXISTS `contrato`;
CREATE TABLE `contrato` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `observacoes` VARCHAR(512) NULL,
    `data_inicio` DATE NOT NULL,
    `data_fim` DATE NOT NULL,
    `data_pagamento` DATE NOT NULL,
    `valor` FLOAT NOT NULL,
    `gerente__fk` INT NOT NULL,
    `locatario__fk` INT NOT NULL,
    `maquina__fk` INT NOT NULL,
    `estado` ENUM('VIGENTE', 'EXPIRADO', 'INATIVO', 'CANCELADO', 'CANCELAMENTO_SOLICITADO') NOT NULL,
    FOREIGN KEY (`gerente__fk`) REFERENCES `gerente` (`pk`),
    FOREIGN KEY (`locatario__fk`) REFERENCES `locatario` (`pk`),
    FOREIGN KEY (`maquina__fk`) REFERENCES `maquina` (`pk`)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `contatos_gerente`
--

DROP TABLE IF EXISTS `contatos_gerente`;
CREATE TABLE `contatos_gerente` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `gerente__fk` INT NOT NULL,
    `locatario__fk` INT NOT NULL,
    FOREIGN KEY (`gerente__fk`) REFERENCES `gerente` (`pk`),
    FOREIGN KEY (`locatario__fk`) REFERENCES `locatario` (`pk`)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `contatos_locatario`
--

DROP TABLE IF EXISTS `contatos_locatario`;
CREATE TABLE `contatos_locatario` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `fornecedor__fk` INT NOT NULL,
    `locatario__fk` INT NOT NULL,
    FOREIGN KEY (`locatario__fk`) REFERENCES `locatario` (`pk`),
    FOREIGN KEY (`fornecedor__fk`) REFERENCES `fornecedor` (`pk`)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `feedback`
--

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `codigo` INT NOT NULL, 
    `titulo` VARCHAR(2048) NOT NULL,
    `mensagem` VARCHAR(2048) NOT NULL,
    `tipoFeedback` ENUM('ERRO', 'COMENTARIO') NOT NULL
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `notas_locatario`
--

DROP TABLE IF EXISTS `notas_locatario`;
CREATE TABLE `notas_locatario` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `mensagem` VARCHAR(1024) NULL,
    `dia` DATE NOT NULL,
    `locatario__fk` INT NOT NULL,
    FOREIGN KEY (`locatario__fk`) REFERENCES `locatario` (`pk`)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `notas_gerente`
--

DROP TABLE IF EXISTS `notas_gerente`;
CREATE TABLE `notas_gerente` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `mensagem` VARCHAR(1024) NULL,
    `dia` DATE NOT NULL,
    `gerente__fk` INT NOT NULL,
    FOREIGN KEY (`gerente__fk`) REFERENCES `gerente` (`pk`)
);
