ALTER TABLE `compras` MODIFY COLUMN `status` enum('PENDING', 'DONE', 'PREPARING', 'CONFIRMED', 'IN_TRANSPORT', 'IN_TRANSIT', 'DELIVERED');

UPDATE `compras` SET `status` = 'DONE' WHERE `status` = 'PENDING' AND `tipo_pagamento` = 'A_VISTA';
UPDATE `compras` SET `status` = 'PREPARING' WHERE `status` = 'CONFIRMED';
UPDATE `compras` SET `status` = 'IN_TRANSIT' WHERE `status` = 'IN_TRANSPORT';

ALTER TABLE `compras` MODIFY COLUMN `status` enum('PENDING', 'DONE', 'PREPARING', 'IN_TRANSIT', 'DELIVERED') NOT NULL;
