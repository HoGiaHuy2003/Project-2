create table Category(
	id int primary key,
	name varchar(15)
); 
create table Product(
	id int primary key,
	category_id int references Category(id),
	title varchar(50),
	price float,
	quantity int, 
	description varchar(100),
	thumbnail varchar(500),
	created_at datetime,
	updated_at datetime
);
create table Customer(
	id int primary key,
	name varchar(15),
	age int not null,
	email varchar(30),
	phone_number int
);
create table Comment( 
	id int primary key,
	customer_id int references Customer(id),
	product_id int references Product(id),
	rate int,
	evaluate varchar(100)
);
create table Order_( 
	id int primary key, 
	customer_id int references Customer(id),
	product_id int references Product(id),
	product_price float
);
create table Order_detail(
	id int primary key,
	customer_id int references Customer(id),
	order_id int references Order_(id),
	total_price float,
	order_date datetime
);
create table Manager(
	id int primary key,
	name varchar(15),
	age int ,
	address varchar(50),
	phone_number int,
	email varchar(30),
	password varchar(30)
);
create table Staff(
	id int primary key,
	name varchar(15),
	age int,
	address varchar(50),
	phone_number int,
	email varchar(30),
	position varchar(30)
);
create table Salary(
	id int primary key, 
	staff_id int references Staff(id),
	wage float,
	hour int,
	salary float
);