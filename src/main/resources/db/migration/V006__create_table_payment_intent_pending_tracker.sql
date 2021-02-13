CREATE TABLE `payment_intent_pending_tracker` (
	`payment_intent_id` varchar(255) NOT NULL,
    `compra_id` bigint(20) NOT NULL,
    `created_at` datetime NOT NULL,
    
    FOREIGN KEY(`compra_id`) REFERENCES `compras`(`id`) ON DELETE CASCADE
) Engine = InnoDB DEFAULT charset=utf8;
