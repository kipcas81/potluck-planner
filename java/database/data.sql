BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,email,first_name,last_name) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'jfish@gmail.com', 'john', 'fish');
INSERT INTO users (username,password_hash,role,email,first_name,last_name) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'holyfuck@gmail.com', 'dog', 'doggie');

COMMIT TRANSACTION;
