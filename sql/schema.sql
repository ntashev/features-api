CREATE TABLE features (
	id MEDIUMINT NOT NULL AUTO_INCREMENT, 
	name CHAR(30) NOT NULL, 
	globally_enabled BOOLEAN NOT NULL,
	PRIMARY KEY (id)) ENGINE=INNODB;
	
CREATE TABLE users (
	id MEDIUMINT NOT NULL AUTO_INCREMENT, 
	first_name CHAR(30),
	last_name CHAR(30),
	PRIMARY KEY (id)) ENGINE=INNODB;
	
CREATE TABLE users_features (
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
	user_id MEDIUMINT NOT NULL, 
	feature_id MEDIUMINT NOT NULL, 
	enabled BOOLEAN NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id)
        REFERENCES users(id),
	FOREIGN KEY (feature_id)
        REFERENCES features(id),
    CONSTRAINT unique_user_feature UNIQUE (user_id, feature_id)) ENGINE=INNODB;

INSERT INTO users(first_name, last_name) VALUES ('John', 'Smith');
INSERT INTO users(first_name, last_name) VALUES ('Michael', 'Johnes');
INSERT INTO users(first_name, last_name) VALUES ('Ben', 'Johnes');
INSERT INTO features(name, globally_enabled) VALUES ('xero', 1);
INSERT INTO features(name, globally_enabled) VALUES ('credit', 1);
INSERT INTO users_features(feature_id, user_id, enabled) VALUES (1, 1, 1);
INSERT INTO users_features(feature_id, user_id, enabled) VALUES (2, 1, 1);
INSERT INTO users_features(feature_id, user_id, enabled) VALUES (1, 2, 0);
INSERT INTO users_features(feature_id, user_id, enabled) VALUES (2, 2, 0);
INSERT INTO users_features(feature_id, user_id, enabled) VALUES (1, 3, 0);
INSERT INTO users_features(feature_id, user_id, enabled) VALUES (2, 3, 1);
