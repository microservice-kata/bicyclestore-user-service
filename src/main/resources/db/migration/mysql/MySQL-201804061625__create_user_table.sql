CREATE TABLE USER (
  ID         BIGINT PRIMARY KEY    AUTO_INCREMENT,
  USERNAME   VARCHAR(32)  NOT NULL UNIQUE,
  CREATED_AT TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
);
