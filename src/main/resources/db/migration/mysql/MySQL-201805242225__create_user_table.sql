CREATE TABLE `USER` (
    `id`         BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`       VARCHAR(40)  NOT NULL COMMENT '名字',
    `username`   VARCHAR(15)  NOT NULL COMMENT '用户名',
    `email`      VARCHAR(40)  NOT NULL,
    `password`   VARCHAR(255) NOT NULL,
    `created_at` DATETIME              DEFAULT CURDATE(),
    `updated_at` DATETIME              DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_users_username` (`username`),
    UNIQUE KEY `uk_users_email` (`email`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8 COMMENT = 'This is just to test how to alter comments';;
