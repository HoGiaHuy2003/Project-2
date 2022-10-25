create table Category(
	id int primary key auto_increment,
	name varchar(15),
	created_at datetime,
	updated_at datetime
); 

create table Role(
	id int primary key auto_increment,
	name varchar(30), 
	password varchar(100)
);


create table Staff(
	id int primary key auto_increment,
	role_id int references Role(id),
	fullname varchar(15),
	birthday date,
	gender varchar(16),
	address varchar(50),
	phone_number int unique not null,
	email varchar(30) unique not null,
	password varchar(100),
	created_at datetime,
	updated_at datetime
);

create table Income(
	id int primary key auto_increment,
	staff_id int references Staff(id),
	salary_wages float,
	overtime_wages float,
	retroactive_pay float,
	commissions float,
	bonus float,
	tips float,
	penalty float,
	created_at datetime,
	updated_at datetime
);

create table Customer(
	id int primary key auto_increment,
	fullname varchar(15),
	birthday date,
	gender varchar(16),
	address varchar(50),
	phone_number int,
	email varchar(30),
	created_at datetime,
	updated_at datetime
);

create table Product(
	id int primary key auto_increment,
	category_id int references Category(id),
	title varchar(50),
	price float,
	quantity int, 
	seleable_number int,
	description varchar(100),
	thumbnail varchar(500),
	created_at datetime,
	updated_at datetime
);

create table sale_off(
	product_id int references product(id),
	quantity int,
	price float,
	date_start datetime,
	date_end datetime
);

create table Comment( 
	id int primary key auto_increment,
	customer_id int references Customer(id),
	product_id int references Product(id),
	rate int,
	evaluate varchar(100),
	created_at datetime,
	updated_at datetime
);

create table Order_(
	id int primary key auto_increment,
	customer_id int references Customer(id),
	staff_id int references Staff(id),
	created_at datetime,
	updated_at datetime
);

create table Order_detail(
	id int primary key auto_increment,
	order_id int references Order_(id),
	product_id int references Product(id),
	price float,
	quantity int,
	order_date datetime,
	created_at datetime,
	updated_at datetime
);

alter table sale_off add date_end datetime


