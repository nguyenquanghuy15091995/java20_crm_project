CREATE DATABASE db_crm_java20;

-- DROP DATABASE db_crm_java20;

USE db_crm_java20;

CREATE TABLE tb_account_type (
	account_type_id int unsigned auto_increment not null,
    account_type_name nvarchar(1000) not null,
	account_type_description text,
    created_at datetime,
    updated_at datetime,
    is_deleted boolean,
    is_active boolean,
    primary key (account_type_id)
);

CREATE TABLE tb_account (
	account_id int unsigned auto_increment not null,
    email nvarchar(1000) not null,
	pass varchar(1000) not null,
    pass_salt varchar(1000) not null,
    full_name nvarchar(1000) not null,
	address nvarchar(2000),
	phone_number varchar(100) not null,
	account_type_id int unsigned not null,
    created_at datetime,
    updated_at datetime,
    is_deleted boolean,
    is_active boolean,
    primary key (account_id),
	foreign key (account_type_id) references tb_account_type(account_type_id)
);

CREATE TABLE tb_task_status (
	task_status_id int unsigned auto_increment not null,
    task_status_name nvarchar(1000) not null,
	task_status_description text,
	created_at datetime,
    updated_at datetime,
    is_deleted boolean,
    is_active boolean,
    primary key (task_status_id)
);

CREATE TABLE tb_project (
	project_id int unsigned auto_increment not null,
    project_name nvarchar(1000) not null,
	project_description text,
    start_date datetime,
    end_date datetime,
    manager_id int unsigned not null,
    created_at datetime,
    updated_at datetime,
    is_deleted boolean,
    is_active boolean,
    primary key (project_id),
	foreign key (manager_id) references tb_account(account_id)
);

CREATE TABLE tb_project_member (
	project_member_id int unsigned auto_increment not null,
    project_id int unsigned not null,
    member_id int unsigned not null,
	created_at datetime,
    updated_at datetime,
    is_deleted boolean,
    is_active boolean,
    primary key (project_member_id),
	foreign key (project_id) references tb_project(project_id),
    foreign key (member_id) references tb_account(account_id)
);

CREATE TABLE tb_task (
	task_id int unsigned auto_increment not null,
	task_name nvarchar(1000) not null,
	task_description text,
	start_date datetime,
    end_date datetime,
    task_status_id int unsigned not null,
    project_id int unsigned not null,
    member_id int unsigned not null,
	created_at datetime,
    updated_at datetime,
    is_deleted boolean,
    is_active boolean,
    primary key (task_id),
	foreign key (task_status_id) references tb_task_status(task_status_id),
	foreign key (project_id) references tb_project(project_id),
    foreign key (member_id) references tb_account(account_id)
);

insert into tb_account_type (account_type_name) values ("admin");
insert into tb_account (email, pass, pass_salt, full_name, address, phone_number, account_type_id) values ("admin@admin.admin", "admin", "xxx", "Admin", "123 house", "69696969", 1);
