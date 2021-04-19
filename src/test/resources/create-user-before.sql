delete from user_roles;
delete from users;

insert into users(id, is_active, password, username) values
(1, true, '$2a$08$O6onEGcVuvORGqEccvtC0e0T9fH2pthTdEejxRrdp1OopwZemhMz2', 'admin'),
(2, true, '$2a$08$O6onEGcVuvORGqEccvtC0e0T9fH2pthTdEejxRrdp1OopwZemhMz2', 'qwe');

insert into user_roles(user_id, roles) values
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER');