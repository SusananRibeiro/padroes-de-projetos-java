-- Banco "padroes_projeto"
CREATE TABLE clientes (
	id serial PRIMARY KEY NOT NULL,	
	nome VARCHAR(50) NOT NULL,
	sobrenome VARCHAR(100) NOT NULL,
	cpf CHAR(11) NOT null UNIQUE, 
	data_nascimento DATE NULL,
	telefone VARCHAR(12) NULL,
	email VARCHAR(255) NULL
);

CREATE TABLE enderecos (
	id serial PRIMARY KEY NOT NULL,	
	rua VARCHAR(100) NOT NULL,
	numero VARCHAR(50) NULL,
	bairro VARCHAR(100) NOT NULL,
	cidade VARCHAR(100) NOT NULL,
	estado VARCHAR(2) NOT NULL,
	cep VARCHAR(8) NOT NULL,
	pais VARCHAR(100) NOT NULL,
	cliente_id INT NOT NULL,
	FOREIGN KEY (cliente_id) REFERENCES clientes (id)
);

CREATE TABLE produtos (
	id serial PRIMARY KEY NOT NULL,	
	nome VARCHAR(100) NOT NULL,
	descricao VARCHAR(255) NOT NULL
);

CREATE TABLE pedidos (
	id serial PRIMARY KEY NOT NULL,	
	data_criacao timestamp(6) NOT NULL,
	data_entrega DATE NOT NULL,
	valor_desconto DECIMAL(8, 2) NOT NULL,
	cliente_id INT NOT NULL,
	endereco_id INT NOT NULL,
	FOREIGN KEY (cliente_id) REFERENCES clientes (id),
	FOREIGN KEY (endereco_id) REFERENCES enderecos (id)
);

CREATE TABLE pedidos_itens (
	id serial PRIMARY KEY NOT NULL,
	valor_unitario DECIMAL(8, 2) NOT NULL,
	quantidade INT NOT NULL,
	pedido_id INT NOT NULL,
	produto_id INT NOT NULL,
	FOREIGN KEY (pedido_id) REFERENCES pedidos (id),
	FOREIGN KEY (produto_id) REFERENCES produtos (id)
);


-- Inserir Cliente
INSERT INTO clientes(nome, sobrenome, cpf, data_nascimento, telefone, email)
VALUES
    ('Maria', 'Oliveira', '98765432120', '1995-08-15', '047987654321', 'maria@mail.com.br'),
    ('Pedro', 'Almeida', '45678912345', '1988-05-20', '048912345678', 'pedro@mail.com.br'),
    ('Ana', 'Pereira', '78901234567', '1992-11-10', '047987654321', 'ana@mail.com.br'),
    ('Carlos', 'Souza', '23456789012', '1980-03-25', '048912345678', 'carlos@mail.com.br'),
    ('Fernanda', 'Silva', '34567890123', '1985-09-05', '047987654321', 'fernanda@mail.com.br'),
    ('Rafael', 'Oliveira', '56789012345', '1998-07-12', '048912345678', 'rafael@mail.com.br'),
    ('Juliana', 'Almeida', '67890123456', '1990-04-18', '047987654321', 'juliana@mail.com.br'),
    ('Luiz', 'Pereira', '89012345678', '1982-12-30', '048912345678', 'luiz@mail.com.br'),
    ('Camila', 'Souza', '12345678909', '1993-06-22', '047987654321', 'camila@mail.com.br'),
    ('André', 'Oliveira', '45678901234', '1987-02-08', '048912345678', 'andre@mail.com.br');



-- Inserir Endereço
INSERT INTO enderecos(rua, bairro, cidade, estado, cep, pais, cliente_id)
VALUES
    ('Rua Manoel Cardoso', 'Flores', 'Criciúma', 'SC', '88800100', 'Brasil', 1),
    ('Rua das Flores', 'Centro', 'Criciúma', 'SC', '88800000', 'Brasil', 2),
    ('Avenida Brasil', 'Comerciário', 'Criciúma', 'SC', '88800200', 'Brasil', 3),
    ('Rua dos Pinheiros', 'Pinheirinho', 'Criciúma', 'SC', '88800300', 'Brasil', 4),
    ('Rua das Palmeiras', 'Próspera', 'Criciúma', 'SC', '88800400', 'Brasil', 5),
    ('Rua dos Lírios', 'São Luiz', 'Criciúma', 'SC', '88800500', 'Brasil', 6),
    ('Avenida das Acácias', 'Santa Bárbara', 'Criciúma', 'SC', '88800600', 'Brasil', 7),
    ('Rua das Orquídeas', 'Michel', 'Criciúma', 'SC', '88800700', 'Brasil', 8),
    ('Rua dos Girassóis', 'São Simão', 'Criciúma', 'SC', '88800800', 'Brasil', 9),
    ('Avenida dos Ipês', 'Nova Esperança', 'Criciúma', 'SC', '88800900', 'Brasil', 10);

-- Inserir Produto
INSERT INTO produtos (nome, descricao)
VALUES
    ('Camisa Branca', 'Camisa de manga curta branca masculina. Tamanhos: P, M, G e GG.'),
    ('Calça Jeans', 'Calça jeans azul clara com corte reto. Tamanhos: 30, 32, 34 e 36.'),
    ('Tênis Esportivo', 'Tênis esportivo preto e vermelho. Ideal para corridas. Tamanhos: 38 a 45.'),
    ('Relógio Analógico', 'Relógio de pulso com mostrador analógico e pulseira de couro marrom.'),
    ('Óculos de Sol', 'Óculos de sol estilo aviador com lentes polarizadas.'),
    ('Bolsa de Couro', 'Bolsa de ombro em couro marrom com compartimentos internos.'),
    ('Celular Smartphone', 'Smartphone com tela de 6 polegadas, câmera tripla e 128 GB de armazenamento.'),
    ('Fone de Ouvido Bluetooth', 'Fone de ouvido sem fio com cancelamento de ruído. Cor: preto.'),
    ('Notebook Ultrabook', 'Notebook ultraleve com processador Intel Core i7 e SSD de 512 GB.'),
    ('Cadeira Ergonômica', 'Cadeira giratória com apoio lombar ajustável para escritório ou home office.');

-- Inserir Pedido
INSERT INTO pedidos (data_criacao, data_entrega, valor_desconto, cliente_id, endereco_id)
VALUES
    ('2024-07-15', '2024-07-30', 20.99, 1, 1),
    ('2024-07-16', '2024-07-31', 15.50, 2, 10),
    ('2024-07-17', '2024-08-01', 10.00, 3, 2),
    ('2024-07-18', '2024-08-02', 5.75, 4, 3),
    ('2024-07-19', '2024-08-03', 30.20, 5, 4),
    ('2024-07-20', '2024-08-04', 12.80, 6, 5),
    ('2024-07-21', '2024-08-05', 18.30, 7, 6),
    ('2024-07-22', '2024-08-06', 25.00, 8, 7),
    ('2024-07-23', '2024-08-07', 8.50, 9, 8),
    ('2024-07-24', '2024-08-08', 22.75, 10, 9);


-- Inserir Pedido Itens
INSERT INTO pedidos_itens (valor_unitario, quantidade, pedido_id, produto_id)
VALUES
    (30.00, 4, 1, 1),
    (25.00, 3, 2, 2),
    (18.00, 2, 3, 3),
    (12.00, 1, 4, 4),
    (28.00, 5, 5, 5),
    (22.45, 4, 6, 6),
    (15.99, 3, 7, 7),
    (10.95, 2, 8, 8),
    (20.95, 1, 9, 9),
    (35.60, 6, 10, 10);


select * from clientes;
select * from enderecos;
select * from pedidos;
select * from pedidos_itens;
select * from produtos;


DELETE FROM pedidos_itens WHERE id = 2;
DELETE FROM pedidos_itens WHERE id = 9;
DELETE FROM pedidos_itens WHERE id = 10;
DELETE FROM pedidos WHERE id = 2;
DELETE FROM pedidos WHERE id = 9;
DELETE FROM pedidos WHERE id = 10;
DELETE FROM enderecos WHERE id = 10;
DELETE FROM enderecos WHERE id = 9;


/*
drop table pedidos_itens;
drop table pedidos;
drop table produtos;
drop table enderecos;
drop table clientes;

*/