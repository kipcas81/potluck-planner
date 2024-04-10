BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,email,first_name,last_name) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'jfish@gmail.com', 'john', 'fish');
INSERT INTO users (username,password_hash,role,email,first_name,last_name) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'holyfuck@gmail.com', 'dog', 'doggie');

INSERT INTO potlucks(event_name,event_date,event_time,creator,potluck_dietary_restrictions,isRecurring,frequency_days,location,isPrivate) VALUES ('Two Prawn Approach','05/05/2022', '11:55 AM', '1', 'VEGAN', false, 0, 'here', true);

COMMIT TRANSACTION;
