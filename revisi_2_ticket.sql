CREATE TABLE t_user(
	id serial,
	user_email varchar(255),
	user_username varchar(255),
	user_pass varchar(255),
	role_id int,
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

CREATE TABLE t_customer(
	id serial,
	first_name varchar(255),
	last_name varchar(255),
	phone_numb varchar(13),
	address text,
	company_id int,
	user_id int,
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

CREATE TABLE t_employee(
	id serial,
	full_name varchar(255),
	user_id int,
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

CREATE TABLE t_role(
	id serial,
	role_name varchar(30),
	role_code varchar(4),
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

CREATE TABLE t_m_product(
	id serial,
	product_name varchar(255),
	product_code varchar(4),
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

CREATE TABLE t_customer_product(
	id serial,
	customer_id int,
	product_id int, 
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

CREATE TABLE t_priority(
	id serial,
	priority_name varchar(10),
	priority_code varchar(10),
	priority_point int,
	duration int,
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

CREATE TABLE t_pic_to_customer(
	id serial,
	customer_id int,
	pic_id int,
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

CREATE TABLE t_file(
	id serial,
	file varchar(255),
	file_extension varchar(10),
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

CREATE TABLE t_thread_hdr(
	id serial,
	code varchar(20),
	description text,
	title varchar(255),
	pic_to_user_id int,
	status_id int,
	priority_id int,
	product_customer_id int,
	file_id int,
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

CREATE TABLE t_thread_dtl(
	id serial,
	hdr_id int,
	comment_text text,
	file_id int,
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

CREATE TABLE t_status(
	id serial,
	status_name varchar(10),
	status_code varchar(4),
	status_point int,
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);


CREATE TABLE t_company(
	id serial,
	company_name varchar(255),
	email varchar(30),
	address text,
	
	created_by int,
	created_at timestamp,
	updated_by int,
	updated_at timestamp,
	is_active boolean,
	"version" int
);

ALTER table t_user add constraint user_pk primary key(id);
ALTER table t_customer add constraint customer_pk primary key(id);
ALTER table t_employee add constraint employee_pk primary key(id);
ALTER table t_role add constraint role_pk primary key(id);
alter table t_m_product add constraint m_product_pk primary key(id);
alter table t_customer_product add constraint customer_product_pk primary key(id);
alter table t_priority add constraint priority_pk primary key(id);
alter table t_pic_to_customer add constraint ptc_pk primary key(id);
alter table t_thread_hdr add constraint thread_hdr_pk primary key(id);
ALTER table t_thread_dtl add constraint thread_dtl_pk primary key(id);
ALTER table t_status add constraint status_pk primary key(id);
ALTER table t_company add constraint company_pk primary key(id);
ALTER table t_file add constraint file_pk primary key(id);

alter table t_customer add constraint customer_phone_bk unique(phone_numb);
alter table t_role add constraint role_code_bk unique(role_code);
alter table t_m_product add constraint m_product_bk unique(product_code);
alter table t_priority add constraint priority_code_bk unique(priority_code);
ALTER table t_pic_to_customer add constraint ptc_customer_bk unique(customer_id);
ALTER table t_status add constraint status_code_bk unique(status_code);
ALTER table t_company add constraint company_ck unique(company_name, email);
ALTER table t_thread_hdr add constraint code_bk unique(code);
alter table t_user add constraint email_bk unique(user_email);

ALTER table t_user add constraint usr_role_fk foreign key(role_id) references t_role(id);
ALTER table t_customer add constraint customer_usr_fk foreign key(user_id) references t_user(id);
ALTER table t_customer add constraint customer_cmp_fk foreign key(company_id) references t_company(id);
ALTER table t_employee add constraint employee_usr_fk foreign key(user_id) references t_user(id);
ALTER table t_customer_product add constraint customer_product_cust_fk foreign key(customer_id) references t_customer(id);
ALTER table t_customer_product add constraint customer_product_fk foreign key(product_id) references t_m_product(id);
ALTER table t_pic_to_customer add constraint ptc_customer_fk foreign key(customer_id) references t_customer(id);
ALTER table t_pic_to_customer add constraint ptc_pic_fk foreign key(pic_id) references t_employee(id);
ALTER table t_thread_hdr add constraint thread_ptc_fk foreign key(pic_to_user_id) references t_pic_to_customer(id);
ALTER table t_thread_hdr add constraint thread_status_fk foreign key(status_id) references t_status(id);
ALTER table t_thread_hdr add constraint thread_priority_fk foreign key(priority_id) references t_priority(id);
ALTER table t_thread_hdr add constraint thread_product_fk foreign key(product_customer_id) references t_customer_product(id);
ALTER table t_thread_dtl add constraint thread_hdr_fk foreign key(hdr_id) references t_thread_hdr(id);
alter table t_thread_hdr add constraint thread_hdr_file_fk foreign key(file_id) references t_file(id);
alter table t_thread_dtl add constraint thread_dtl_file_fk foreign key(file_id) references t_file(id);

INSERT into t_priority (priority_name, priority_code, priority_point, created_by, created_at, updated_by, updated_at, is_active, "version") values 
	('High','H',3,null,now(),null,now(),true,0),
	('Medium','M',2,null,now(),null,now(),true,0),
	('Low','L',1,null,now(),null,now(),true,0);

INSERT into t_status (status_name, status_code, status_point, created_by, created_at, updated_by, updated_at, is_active, "version") values 
	('Open', 'OPN', 3, null, now(), null, now(), true, 0),
	('Closed', 'CLS', 2, null, now(), null, now(), true, 0),
	('Re-open', 'ROPN', 1, null, now(), null, now(), true, 0);

insert into t_role (role_name, role_code, created_by, created_at, updated_by, updated_at, is_active, "version") values 
	('Super_Admin','ESA',null,now(),null,now(),true,0),
	('Person_in_Charge','PIC',null,now(),null,now(),true,0),
	('Customer','CUST',null,now(),null,now(),true,0);	

INSERT into t_user (role_id, user_username, user_pass, created_by, created_at, updated_by, updated_at, is_active, "version") values 
	(1,'superadmin','superadmin',null,now(),null,now(),true,0);

INSERT into t_employee (full_name, user_id, created_by, created_at, updated_by, updated_at, is_active, "version") values 
	('Admin Super', 1, null, now(), null, now(), true, 0);

INSERT into t_company (company_name, email, address, created_by, created_at, updated_by, updated_at, is_active, "version") values 
	('PT Bulan', 'moon@company.com','Jl. moon', null, now(), null, now(), true, 0);
