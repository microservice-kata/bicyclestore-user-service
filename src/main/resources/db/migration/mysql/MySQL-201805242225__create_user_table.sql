CREATE TABLE `USER` (
    `id`         BIGINT(20)   UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'id',
    `name`       VARCHAR(40)  NOT NULL COMMENT '名字',
    `username`   VARCHAR(15)  NOT NULL COMMENT '用户名',
    `email`      VARCHAR(40)  NOT NULL,
    `password`   VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `updated_at` TIMESTAMP(3) NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_username` (`username`),
    UNIQUE KEY `uk_user_email` (`email`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8 COMMENT = 'This is just to test how to alter comments';;
