BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,email,first_name,last_name) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'jfish@gmail.com', 'john', 'fish');
INSERT INTO users (username,password_hash,role,email,first_name,last_name) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'holyfuck@gmail.com', 'dog', 'doggie');

INSERT INTO potlucks(event_name, description, event_date, event_time, user_id, potluck_dietary_restrictions, is_recurring, frequency_days, location, is_private, is_completed)
VALUES ('Two Prawn Approach', 'this is a new potluck','05/25/2024', '0700', '1', 'vegan', false, 0, 'here', true,false);
COMMIT TRANSACTION;
