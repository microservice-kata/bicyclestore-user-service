CREATE TABLE `USER_ROLE` (
    `user_id` BIGINT(20) NOT NULL,
    `role_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    KEY `fk_user_roles_role_id` (`role_id`),
    CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `ROLE` (`id`),
    CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
