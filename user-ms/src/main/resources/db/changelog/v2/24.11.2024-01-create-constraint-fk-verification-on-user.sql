ALTER TABLE verification
ADD CONSTRAINT FK_VERIFICATION_ON_USER FOREIGN KEY (user_id) REFERENCES _user (id);