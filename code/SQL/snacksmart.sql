--
-- Banco de dados: `snacksmart`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `gerente`
--

CREATE TABLE `gerente` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `usuario` VARCHAR(64) NOT NULL,
    `nome` VARCHAR(256) NOT NULL,
    `senha` VARCHAR(256) NOT NULL,
    `cpf` CHAR(11) NOT NULL,
    `telefone` VARCHAR(14) NULL,
    `email` VARCHAR(128) NULL
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `locatario`
--

CREATE TABLE `locatario` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `usuario` VARCHAR(64) NOT NULL,
    `nome` VARCHAR(256) NOT NULL,
    `senha` VARCHAR(256) NOT NULL,
    `cpf` CHAR(11) NOT NULL,
    `rg` CHAR(10) NOT NULL,
    `telefone` VARCHAR(14) NULL,
    `email` VARCHAR(128) NULL
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

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

CREATE TABLE `lote` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `tipo_produto` VARCHAR(128) NOT NULL,
    `quantidade` INT NOT NULL,
    `preco_compra` FLOAT NOT NULL,
    `preco_venda` FLOAT NOT NULL,
    `fornecedor__fk` INT NOT NULL,
    `locatario__fk` INT NOT NULL,
    FOREIGN KEY (`tipo_produto__fk`) REFERENCES `tipo_produto` (`pk`),
    FOREIGN KEY (`fornecedor__fk`) REFERENCES `fornecedor` (`pk`),
    FOREIGN KEY (`locatario__fk`) REFERENCES `locatario` (`pk`)
);

-- Observação: Preço de compra e venda são relativos a uma unidade do produto

-- --------------------------------------------------------

--
-- Estrutura da tabela `maquina`
--

CREATE TABLE `maquina` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `aluguel` FLOAT NOT NULL,
    `locatario__fk` INT NULL,
    `estado` ENUM('ALUGADA', 'EM_MANUTENCAO', 'AGUARDANDO_MANUTENCAO', 'DISPONIVEL') NOT NULL,
    FOREIGN KEY (`locatario__fk`) REFERENCES `locatario` (`pk`)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `contrato`
--

CREATE TABLE `contrato` (
    `pk` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `data_inicio` DATE NOT NULL,
    `data_fim` DATE NOT NULL,
    `data_pagamento` DATE NOT NULL,
    `valor` INT NOT NULL,
    `gerente__fk` INT NOT NULL,
    `locatario__fk` INT NOT NULL,
    `maquina__fk` INT NOT NULL,
    `estado` ENUM('VIGENTE', 'EXPIRADO', 'INATIVO', 'CANCELADO') NOT NULL,
    FOREIGN KEY (`gerente__fk`) REFERENCES `gerente` (`pk`),
    FOREIGN KEY (`locatario__fk`) REFERENCES `locatario` (`pk`),
    FOREIGN KEY (`maquina__fk`) REFERENCES `maquina` (`pk`)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `contatos_gerente`
--

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

CREATE TABLE `feedback` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `mensagem` VARCHAR(2048) NULL,
    `gerente__fk` INT NOT NULL,
    `locatario__fk` INT NOT NULL,
    FOREIGN KEY (`gerente__fk`) REFERENCES `gerente` (`pk`),
    FOREIGN KEY (`locatario__fk`) REFERENCES `locatario` (`pk`)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `notas_locatario`
--

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

CREATE TABLE `notas_gerente` (
    `pk` INT AUTO_INCREMENT PRIMARY KEY,
    `mensagem` VARCHAR(1024) NULL,
    `dia` DATE NOT NULL,
    `gerente__fk` INT NOT NULL,
    FOREIGN KEY (`gerente__fk`) REFERENCES `gerente` (`pk`)
);
```
