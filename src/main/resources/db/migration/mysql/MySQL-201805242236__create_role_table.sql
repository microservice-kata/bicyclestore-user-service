CREATE TABLE `ROLE` (
    `id`   BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(60)         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_name` (`name`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARSET = utf8;
