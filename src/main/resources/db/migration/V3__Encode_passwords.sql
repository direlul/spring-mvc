CREATE extension if NOT EXISTS pgcrypto;

update users set password = crypt(password, gen_salt('bf', 8));