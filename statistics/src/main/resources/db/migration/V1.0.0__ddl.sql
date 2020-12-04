create table public.clients
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    email varchar(255) not null,
    first_name varchar(150) not null,
    last_name varchar(150) not null,
    phone varchar(255) not null,
    status varchar(255),
    update_at timestamp not null,
    primary key (id)
);

create table public.logins
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    ip varchar(255) not null,
    status varchar(255),
    token varchar(255) not null,
    update_at timestamp not null,
    primary key (id)
);

create table public.unbooked_client_views
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    ip varchar(255) not null,
    product_id int8 not null,
    status varchar(255),
    update_at timestamp not null,
    primary key (id)
);

create index client_email_idx on public.clients (email);

alter table if exists public.clients drop constraint if exists uk_client_email_login;

alter table if exists public.clients add constraint uk_client_email_login unique (email);

create table login_information (login_id int8 not null, information varchar(255), information_key varchar(255) not null, primary key (login_id, information_key));

alter table if exists login_information add constraint login_information_fk foreign key (login_id) references public.logins;
