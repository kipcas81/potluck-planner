BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,email,first_name,last_name) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'jfish@gmail.com', 'john', 'fish');
INSERT INTO users (username,password_hash,role,email,first_name,last_name) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'holyfuck@gmail.com', 'dog', 'doggie');

INSERT INTO potlucks(event_name, description, event_date,event_time,user_id,potluck_dietary_restrictions,is_recurring,frequency_days,location,is_private,is_completed)
VALUES ('Two Prawn Approach', 'this is a new potluck','05/05/2022', '11:55 AM', '1', '{"vegan"}', false, 0, 'here', true,false);

INSERT INTO users_friends(user_id,friend_first_name,friend_last_name,friend_email_address)
VALUES ('1', 'James', 'Camera', 'jcamera@yahoo.gov');
INSERT INTO users_friends(user_id,friend_first_name,friend_last_name,friend_email_address)
VALUES ('2', 'JG', 'Wentworth', '877CashNow@mywebsitethumbsup.com');

COMMIT TRANSACTION;
