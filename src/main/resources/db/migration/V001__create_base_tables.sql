CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `is_admin` tinyint(1) DEFAULT '0',
  `senha` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `admin` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `enderecos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint(20) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `categorias` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `produtos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` text DEFAULT NULL,
  `marca` varchar(255) NOT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `valor_base` decimal(19,2) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `produtos_categorias` (
  `produto_id` bigint(20) NOT NULL,
  `categoria_id` bigint(20) NOT NULL,
  PRIMARY KEY (`produto_id`,`categoria_id`),
  FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`),
  FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fotos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_url` text NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tamanhos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `estoques` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acrescimo_valor` decimal(19,2) DEFAULT NULL,
  `qtd_estoque` bigint(20) DEFAULT NULL,
  `cor_id` bigint(20) DEFAULT NULL,
  `tamanho_id` bigint(20) DEFAULT NULL,
  `produto_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`tamanho_id`) REFERENCES `tamanhos` (`id`),
  FOREIGN KEY (`cor_id`) REFERENCES `cores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `compras` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `total` decimal(19,2) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  `endereco_id` bigint(20) NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `status` enum('PENDING', 'CONFIRMED', 'IN_TRANSPORT', 'DELIVERED') DEFAULT 'PENDING' NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  FOREIGN KEY (`endereco_id`) REFERENCES `enderecos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `itens_compra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome_cor` varchar(255) NOT NULL,
  `nome_tamanho` varchar(255) NOT NULL,
  `quantidade` bigint(20) NOT NULL,
  `subtotal` decimal(19,2) NOT NULL,
  `compra_id` bigint NOT NULL,
  `produto_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
 	FOREIGN KEY (`compra_id`) REFERENCES `compras` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



