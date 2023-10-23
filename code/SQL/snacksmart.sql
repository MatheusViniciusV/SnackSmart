--
-- Banco de dados: `snacksmart`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `gerente`
--

create table gerente {
    pk int auto_increment primary key,
    usuario  varchar(64)  not null,
    nome     varchar(256) not null,
    senha    varchar(256) not null,
    cpf      char(11)     not null,
    rg       char(10)     not null,
    telefone varchar(14)  null,
    email    varchar(128) null
};

-- --------------------------------------------------------

--
-- Estrutura da tabela `locatario`
--

create table locatario {
    pk int auto_increment primary key,
    usuario  varchar(64)  not null,
    nome     varchar(256) not null,
    senha    varchar(256) not null,
    cpf      char(11)     not null,
    rg       char(10)     not null,
    telefone varchar(14)  null,
    email    varchar(128) null
};

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

create table fornecedor {
    pk int auto_increment primary key,
    nome     varchar(256) not null,
    telefone varchar(14)  null,
    email    varchar(128) null
};

-- --------------------------------------------------------

--
-- Estrutura da tabela 'lote'
--

create table lote {
    pk int auto_increment primary key,
    tipo_produto   varchar(128) not null,
    quantidade     int          not null,
    preco_compra   float        not null,
    preco_venda    float        not null,
    fornecedor__fk int          not null,
    locatario__fk  int          not null,
    foreign key (tipo_produto__fk) references tipo_produto (pk),
    foreign key (fornecedor__fk)   references fornecedor   (pk),
    foreign key (locatario__fk)    references locatario    (pk)
};

-- Observação: Preço de compra e venda são relativos à uma unidade do produto

-- --------------------------------------------------------

--
-- Estrutura da tabela 'maquina'
--

create table maquina {
    pk int auto_increment primary key,
    aluguel       float not null,
    locatario__fk int   null,
    estado ENUM('ALUGADA','EM_MANUTENCAO','AGUARDANDO_MANUTENCAO','DISPONIVEL') not null,
    foreign key (locatario__fk) references locatario (pk)
};

-- --------------------------------------------------------

--
-- Estrutura da tabela 'contrato'
--

create table contrato {
    pk int auto_increment primary key,
    data_inicio   date not null,
    data_fim      date not null,
    gerente__fk   int  not null,
    locatario__fk int  not null,
    maquina__fk   int  not null,
    estado ENUM('VIGENTE', 'EXPIRADO', 'INATIVO', 'CANCELADO') not null,
    foreign key (gerente__fk)   references gerente   (pk),
    foreign key (locatario__fk) references locatario (pk),
    foreign key (maquina__fk)   references maquina   (pk)
};

-- --------------------------------------------------------

--
-- Estrutura da tabela 'contatos_gerente'
--

create table contatos_gerente {
    pk int auto_increment primary key,
    gerente__fk   int not null
    locatario__fk int not null,
    foreign key (gerente__fk)   references gerente   (pk),
    foreign key (locatario__fk) references locatario (pk)
}

-- --------------------------------------------------------

--
-- Estrutura da tabela 'contatos_locatario'
--

create table contatos_locatario {
    pk int auto_increment primary key,
    fornecedor__fk int  not null,
    locatario__fk  int  not null,
    foreign key (locatario__fk)  references locatario  (pk),
    foreign key (fornecedor__fk) references fornecedor (pk)
}


-- --------------------------------------------------------

--
-- Estrutura da tabela 'feedback'
--

create table feedback {
    pk int auto_increment primary key,
    mensagem      varchar(2048) null,
    gerente__fk   int           not null,
    locatario__fk int           not null,
    foreign key (gerente__fk)   references gerente   (pk),
    foreign key (locatario__fk) references locatario (pk),
}

-- --------------------------------------------------------

--
-- Estrutura da tabela 'notas_locatario'
--

create table notas_locatario {
    pk int auto_increment primary key,
    mensagem      varchar(1024) null,
    dia           date          not null,
    locatario__fk int           not null,
    foreign key (locatario__fk) references locatario (pk)
}

-- --------------------------------------------------------

--
-- Estrutura da tabela 'notas_gerente'
--

create table notas_gerente {
    pk int auto_increment primary key,
    mensagem    varchar(1024) null,
    dia         date          not null,
    gerente__fk int           not null,
    foreign key (gerente__fk) references gerente (pk)
}
