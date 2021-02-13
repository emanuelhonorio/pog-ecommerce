ALTER TABLE `compras` ADD COLUMN `tipo_pagamento` enum('CARTAO', 'A_VISTA', 'CARTAO_PRESENCIAL') NOT NULL;
ALTER TABLE `compras` ADD COLUMN `troco_para` decimal(19,2) NULL;
