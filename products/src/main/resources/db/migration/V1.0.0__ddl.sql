create table public.brands
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    name varchar(100) not null,
    status varchar(255),
    update_at timestamp not null,
    primary key (id)
);

create table public.categories
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    name varchar(50) not null,
    status varchar(255),
    update_at timestamp not null,
    primary key (id)
);

create table public.comment
(
    id int8 generated by default as identity,
    comment varchar(255) not null,
    create_at timestamp not null,
    name varchar(255) not null,
    status varchar(255),
    update_at timestamp not null,
    comment_id int8,
    primary key (id)
);

create table public.criteria
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    status varchar(255),
    update_at timestamp not null,
    value varchar(100) not null,
    criteria_id int8,
    primary key (id)
);

create table public.filters
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    status varchar(255),
    title varchar(50) not null,
    update_at timestamp not null,
    filter_title_id int8,
    primary key (id)
);

create table public.products
(
    id int8 generated by default as identity,
    bought_count int4 not null,
    create_at timestamp not null,
    description varchar(255) not null,
    name varchar(100) not null,
    preview_image varchar(255) not null,
    price DECIMAL(8, 3),
    quantity int4 not null,
    update_at timestamp not null,
    status varchar(255),
    brand_id int8 not null,
    stock_id int8 not null,
    sub_category_id int8 not null,
    primary key (id)
);

create table public.specification
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    description varchar(255) not null,
    name varchar(50) not null,
    status varchar(255),
    update_at timestamp not null,
    specification_id int8,
    primary key (id)
);

create table public.stocks
(
    id int8 generated by default as identity,
    city varchar(255) not null,
    country varchar(255) not null,
    create_at timestamp not null,
    email varchar(255) not null,
    name varchar(255) not null,
    number varchar(255) not null,
    phone varchar(255) not null,
    region varchar(255) not null,
    status varchar(255),
    street varchar(255) not null,
    street_number varchar(255) not null,
    update_at timestamp not null,
    zip_code varchar(255) not null,
    primary key (id)
);

create table public.subcategories
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    name varchar(50) not null,
    status varchar(255),
    update_at timestamp not null,
    subcaretory_id int8,
    primary key (id)
);

create index brand_name_idx on public.brands (name);

alter table if exists public.brands drop constraint if exists uk_brands_name;

alter table if exists public.brands add constraint uk_brands_name unique (name);

create index category_name_idx on public.categories (name);

alter table if exists public.categories drop constraint if exists uk_category_name;

alter table if exists public.categories add constraint uk_category_name unique (name);

create index subcategory_name_idx on public.subcategories (name);

alter table if exists public.subcategories drop constraint if exists uk_subcategory_name;

alter table if exists public.subcategories add constraint uk_subcategory_name unique (name);

alter table if exists public.subcategories add column subcategory_id int8;

create table product_images (product_id int8 not null, images varchar(255));

create table products_criteria (product_id int8 not null, criteria_id int8 not null, primary key (product_id, criteria_id));

create table stock_staff_names (stock_id int8 not null, staff_names varchar(255));

alter table if exists public.comment add constraint product_comments_fk foreign key (comment_id) references public.products;

alter table if exists public.criteria add constraint filters_criteria_fk foreign key (criteria_id) references public.filters;

alter table if exists public.filters add constraint sub_category_filter_title_fk foreign key (filter_title_id) references public.subcategories;

alter table if exists public.products add constraint products_brand_fk foreign key (brand_id) references public.brands;

alter table if exists public.products add constraint products_stock_fk foreign key (stock_id) references public.stocks;

alter table if exists public.products add constraint products_subcategory_fk foreign key (sub_category_id) references public.subcategories;

alter table if exists public.specification add constraint products_specification_fk foreign key (specification_id) references public.products;

alter table if exists public.subcategories add constraint category_subcategory_fk foreign key (subcategory_id) references public.categories;

alter table if exists product_images add constraint product_images_fk foreign key (product_id) references public.products;

alter table if exists products_criteria add constraint criteria_products_fk foreign key (criteria_id) references public.criteria;

alter table if exists products_criteria add constraint products_criteria_fk foreign key (product_id) references public.products;

alter table if exists stock_staff_names add constraint stock_staff_names_fk foreign key (stock_id) references public.stocks;
