insert into instances(id, name)
values (1, 'employees'),
       (2, 'clients'),
       (3, 'employees-gateway'),
       (4, 'clients-gateway');

insert into roles(id, name)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_MANAGER'),
       (3, 'ROLE_DEV'),
       (4, 'ROLE_USER');

insert into instance_roles(role_id, instances_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (1, 3),
       (2, 3),
       (3, 3),
       (4, 4);

insert into role_permission(role_id, permission)
values (1, '/**'),
       (2, '/**'),
       (3, '/**'),
       (4, '/**');

insert into keys_settings(
    id,
    priority,
    access_token_expire_time,
    refresh_token_expire_time,
    access_token_header_name,
    access_token_cookie_name,
    refresh_token_cookie_name,
    role_id
) values (1, 10, 300000, 150000, 'default', 'access_token', 'refresh_token', 1),
         (2, 8, 300000, 150000, 'default', 'access_token', 'refresh_token', 2),
         (3, 10, 300000, 150000, 'default', 'access_token', 'refresh_token', 3),
         (4, 5, 300000, 150000, 'default', 'access_token', 'refresh_token', 4);
