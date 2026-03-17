CREATE SEQUENCE IF NOT EXISTS groups_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE features
(
    key         VARCHAR(200) NOT NULL,
    description VARCHAR(255),
    CONSTRAINT pk_features PRIMARY KEY (key)
);

CREATE TABLE group_feature
(
    group_feature_key VARCHAR(200) NOT NULL,
    group_id          INTEGER      NOT NULL,
    CONSTRAINT pk_group_feature PRIMARY KEY (group_feature_key, group_id)
);

CREATE TABLE groups
(
    id                    INTEGER NOT NULL,
    name                  VARCHAR(255),
    background_preference INTEGER,
    icon_preference       INTEGER,
    CONSTRAINT pk_groups PRIMARY KEY (id)
);

ALTER TABLE group_feature
    ADD CONSTRAINT fk_grofea_on_group FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE group_feature
    ADD CONSTRAINT fk_grofea_on_group_feature FOREIGN KEY (group_feature_key) REFERENCES features (key);