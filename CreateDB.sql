USE master; 
CREATE DATABASE cinema;
GO

USE cinema;

CREATE TABLE departments(
	PRIMARY KEY (id),
    id INT IDENTITY,
    position NVARCHAR(20) NOT NULL,
	UNIQUE (position),
    salary SMALLMONEY NOT NULL
);

CREATE TABLE staff(  
	PRIMARY KEY (id),
	id INT IDENTITY,
	last_name NVARCHAR(30) NOT NULL,
	first_name NVARCHAR(15) NOT NULL,
	middle_name NVARCHAR(30) NOT NULL,
	phone NVARCHAR(20) NOT NULL,
	UNIQUE (phone),
	gender CHAR(1) NOT NULL 
);

CREATE TABLE access(
	PRIMARY KEY(staff_id, department_id),
	staff_id INT,
	FOREIGN KEY (staff_id) REFERENCES staff (id) ON DELETE CASCADE,
	department_id INT,
	FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE CASCADE,
	[password] NVARCHAR(MAX) NOT NULL,	
);

CREATE TABLE working_hours(
	PRIMARY KEY(staff_id),
	staff_id INT,
	FOREIGN KEY (staff_id) REFERENCES staff (id) ON DELETE CASCADE,
	work_date DATETIME NOT NULL,
	start_time TIME NOT NULL,
	stop_time TIME NOT NULL
);

CREATE TABLE countries(
	PRIMARY KEY(id),
	id INT IDENTITY,
	country_name NVARCHAR(20) NOT NULL,
	UNIQUE (country_name)
);

CREATE TABLE genres(
	PRIMARY KEY(id),
	id INT IDENTITY,
	genre_name NVARCHAR(20) NOT NULL,
	UNIQUE (genre_name)
); 

CREATE TABLE films(
	PRIMARY KEY (id),
	id INT IDENTITY,
	film_name NVARCHAR(30) NOT NULL,
	UNIQUE (film_name),
	release_date INT NOT NULL,
	show_time INT NOT NULL,
	film_description NVARCHAR(MAX) NOT NULL,
	age_num INT NOT NULL,
	rating FLOAT NOT NULL 
);

CREATE TABLE countries_name(
	PRIMARY KEY(film_id, country_id),
	film_id INT,
    FOREIGN KEY (film_id) REFERENCES films (id) ON DELETE CASCADE,
	country_id INT,
	FOREIGN KEY (country_id) REFERENCES countries (id) ON DELETE CASCADE
);

CREATE TABLE genres_name(
	PRIMARY KEY(film_id, genre_id),
	film_id INT,
	FOREIGN KEY (film_id) REFERENCES films (id) ON DELETE CASCADE,
	genre_id INT,
	FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE CASCADE
);

CREATE TABLE halls(
	PRIMARY KEY(id),
	id INT IDENTITY,
	hall_name NVARCHAR(20) NOT NULL,
	UNIQUE(hall_name),
	row_total INT NOT NULL, 
	seat_total INT NOT NULL
);

CREATE TABLE seances(
	PRIMARY KEY (id),
	id INT IDENTITY,
	show_date DATE NOT NULL,
	show_time TIME NOT NULL,
	hall_id INT NOT NULL,
	FOREIGN KEY (hall_id) REFERENCES halls (id) ON DELETE CASCADE,
	film_id INT NOT NULL,
	FOREIGN KEY (film_id) REFERENCES films (id) ON DELETE CASCADE,
	screen VARCHAR(3)
);

INSERT INTO seances
VALUES('2004-11-24','16:00', 1, 1, '3D')

CREATE TABLE categories(
	PRIMARY KEY(id),
	id INT IDENTITY,
	category_name NVARCHAR(15) NOT NULL,
	UNIQUE(category_name)
);

CREATE TABLE tickets_price( 
	PRIMARY KEY (seance_id, category_id),
	seance_id INT,
	FOREIGN KEY (seance_id) REFERENCES seances (id) ON DELETE CASCADE,
	category_id INT,
	FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE,
	price SMALLMONEY DEFAULT 0,
);

INSERT INTO tickets_price
VALUES (2,1,5)
       
CREATE TABLE seats( 
	PRIMARY KEY (row_num, seat_num, hall_id),
	row_num INT,
	seat_num INT,
	hall_id INT,
	FOREIGN KEY (hall_id) REFERENCES halls (id) ON DELETE CASCADE,
	category_id INT NOT NULL,
	FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

CREATE TABLE tickets( -- ��������
	PRIMARY KEY (id),
	id INT IDENTITY,
	row_id INT NOT NULL,
	seat_id INT NOT NULL,
	hall_id INT NOT NULL,
	FOREIGN KEY (row_id, seat_id, hall_id) REFERENCES seats (row_num, seat_num, hall_id),
	seance_id INT NOT NULL,
	FOREIGN KEY (seance_id) REFERENCES seances (id) ON DELETE CASCADE,
	ticket_status NVARCHAR(20) DEFAULT '�������'
);

INSERT INTO tickets (row_id, seat_id, hall_id, seance_id)
VALUES (1,1,1,2)

CREATE TABLE product_types(
	PRIMARY KEY (id),
	id INT IDENTITY,
	product_name NVARCHAR(20) NOT NULL,
	UNIQUE (product_name),
);

CREATE TABLE products( 
	PRIMARY KEY (id),
	id INT IDENTITY,
	[type_id] INT NOT NULL,
	FOREIGN KEY ([type_id]) REFERENCES product_types (id) ON DELETE CASCADE,
	product_name NVARCHAR(30) NOT NULL,
	UNIQUE(product_name),
	amount INT DEFAULT 0 NOT NULL,
	price SMALLMONEY NOT NULL
);

CREATE TABLE payments(
	PRIMARY KEY (id),
	id INT IDENTITY,
	payment_name VARCHAR(20),
	UNIQUE(payment_name)
);

CREATE TABLE orders(
	PRIMARY KEY(id),
	id INT IDENTITY,
	payment_time DATETIME NOT NULL,
	payment_id INT NOT NULL,
	FOREIGN KEY (payment_id) REFERENCES payments (id) ON DELETE CASCADE,
	check_num BIGINT NOT NULL,
	price SMALLMONEY NOT NULL
);

CREATE TABLE order_tickets(
	PRIMARY KEY (order_id, ticket_id),
	order_id INT NOT NULL,
	FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
	ticket_id INT NOT NULL,
	FOREIGN KEY (ticket_id) REFERENCES tickets (id) ON DELETE CASCADE
);

CREATE TABLE order_products(
	PRIMARY KEY (order_id, product_id),
	order_id INT NOT NULL,
	FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
	product_id INT NOT NULL,
	FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
	amount_total INT NOT NULL
);

SELECT DISTINCT(order_id), COUNT(product_id) AS product_id,COUNT(amount_total) AS amount_total FROM order_products GROUP BY order_id, product_id

CREATE TABLE checks(
	PRIMARY KEY(staff_id, order_id),
	staff_id INT NOT NULL,
	FOREIGN KEY (staff_id) REFERENCES staff (id) ON DELETE CASCADE,
	order_id INT NOT NULL,
	FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

CREATE LOGIN programmer   
	WITH PASSWORD = 'programmer'; 
GO  

CREATE USER programmer FOR LOGIN programmer;  
GO  

ALTER ROLE db_owner
	ADD MEMBER programmer;
GO

INSERT INTO departments
	VALUES ('�����������',2100);
INSERT INTO departments
	VALUES ('�������������',1800);
INSERT INTO departments
	VALUES ('��������',1500);
INSERT INTO departments
	VALUES ('������ ����������',800);
INSERT INTO departments
	VALUES ('������ ������',600);
INSERT INTO departments
	VALUES ('�����������',320);

INSERT INTO staff
	VALUES ('���������','������','����������','8(029)-545-24-02','�');  --$2a$12$cknCNIM5tTNpW35zzdN43.f.Sumxt1SDzDPLeUuJO.7o0/WfOrE2G --1
INSERT INTO staff
	VALUES ('��������','�����','����������','8(033)-724-78-41','�');  --3
INSERT INTO staff
	VALUES ('�������','������','���������','8(029)-481-34-82','�'); --2
INSERT INTO staff
	VALUES ('����������','����','����������','8(033)-520-91-44','�'); --4
INSERT INTO staff
	VALUES ('������','������','���������','8(029)-852-02-86','�'); --4
INSERT INTO staff
	VALUES ('������','�������','������������','8(033)-395-68-13','�'); --5
INSERT INTO staff
	VALUES ('������','�������','�������','8(029)-795-42-89','�'); --6


INSERT INTO access
	VALUES (1,1,'$2a$12$cknCNIM5tTNpW35zzdN43.f.Sumxt1SDzDPLeUuJO.7o0/WfOrE2G');
INSERT INTO access
	VALUES (2,3,'$2a$12$cknCNIM5tTNpW35zzdN43.f.Sumxt1SDzDPLeUuJO.7o0/WfOrE2G');
INSERT INTO access
	VALUES (3,2,'$2a$12$cknCNIM5tTNpW35zzdN43.f.Sumxt1SDzDPLeUuJO.7o0/WfOrE2G');
INSERT INTO access
	VALUES (4,4,'$2a$12$cknCNIM5tTNpW35zzdN43.f.Sumxt1SDzDPLeUuJO.7o0/WfOrE2G');
INSERT INTO access
	VALUES (5,4,'$2a$12$cknCNIM5tTNpW35zzdN43.f.Sumxt1SDzDPLeUuJO.7o0/WfOrE2G');
INSERT INTO access
	VALUES (6,5,'$2a$12$cknCNIM5tTNpW35zzdN43.f.Sumxt1SDzDPLeUuJO.7o0/WfOrE2G');
INSERT INTO access
	VALUES (7,6,'$2a$12$cknCNIM5tTNpW35zzdN43.f.Sumxt1SDzDPLeUuJO.7o0/WfOrE2G');

INSERT INTO countries
	VALUES ('������');
INSERT INTO countries
	VALUES ('�������');
INSERT INTO countries
	VALUES ('�������');
INSERT INTO countries
	VALUES ('���');
INSERT INTO countries
	VALUES ('���������');
INSERT INTO countries
	VALUES ('��������������');
INSERT INTO countries
	VALUES ('�������');
INSERT INTO countries
	VALUES ('������');
INSERT INTO countries
	VALUES ('������');
INSERT INTO countries
	VALUES ('����� �����');
INSERT INTO countries
	VALUES ('�������');
INSERT INTO countries
	VALUES ('�������');
INSERT INTO countries
	VALUES ('�����');
INSERT INTO countries
	VALUES ('��������');
INSERT INTO countries
	VALUES ('�������');
INSERT INTO countries
	VALUES ('��������');

INSERT INTO genres
	VALUES ('���������');
INSERT INTO genres
	VALUES ('������');
INSERT INTO genres
	VALUES ('�������');
INSERT INTO genres
	VALUES ('�������');
INSERT INTO genres
	VALUES ('��������');
INSERT INTO genres
	VALUES ('��������');
INSERT INTO genres
	VALUES ('�����');
INSERT INTO genres
	VALUES ('������������');
INSERT INTO genres
	VALUES ('�������');
INSERT INTO genres
	VALUES ('��������');
INSERT INTO genres
	VALUES ('���������');
INSERT INTO genres
	VALUES ('����������');
INSERT INTO genres
	VALUES ('������');
INSERT INTO genres
	VALUES ('�����������');
INSERT INTO genres
	VALUES ('��������');
INSERT INTO genres
	VALUES ('C���������');
INSERT INTO genres
	VALUES ('�������');
INSERT INTO genres
	VALUES ('�����');
INSERT INTO genres
	VALUES ('����������');
INSERT INTO genres
	VALUES ('�������');

INSERT INTO categories
	VALUES ('VIP');
INSERT INTO categories
	VALUES ('��������');
INSERT INTO categories
	VALUES ('�������');

INSERT INTO halls -- ���� ��������
	VALUES ('���� 1',7,66);
INSERT INTO halls
	VALUES ('���� 2',7,66);
INSERT INTO halls
	VALUES ('���� 3',7,66);

INSERT INTO seats 
	VALUES (1,1,1,2);
INSERT INTO seats 
	VALUES (1,2,1,2);
INSERT INTO seats 
	VALUES (1,3,1,2);
INSERT INTO seats 
	VALUES (1,4,1,2);
INSERT INTO seats 
	VALUES (1,5,1,2);
INSERT INTO seats 
	VALUES (1,6,1,2);
INSERT INTO seats 
	VALUES (2,1,1,2);
INSERT INTO seats 
	VALUES (2,2,1,2);
INSERT INTO seats 
	VALUES (2,3,1,2);
INSERT INTO seats 
	VALUES (2,4,1,2);
INSERT INTO seats 
	VALUES (2,5,1,2);
INSERT INTO seats 
	VALUES (2,6,1,2);
INSERT INTO seats 
	VALUES (2,7,1,2);
INSERT INTO seats 
	VALUES (2,8,1,2);
INSERT INTO seats 
	VALUES (3,1,1,2);
INSERT INTO seats 
	VALUES (3,2,1,2);
INSERT INTO seats 
	VALUES (3,3,1,2);
INSERT INTO seats 
	VALUES (3,4,1,2);
INSERT INTO seats 
	VALUES (3,5,1,2);
INSERT INTO seats 
	VALUES (3,6,1,2);
INSERT INTO seats 
	VALUES (3,7,1,2);
INSERT INTO seats 
	VALUES (3,8,1,2);
INSERT INTO seats 
	VALUES (3,9,1,2);
INSERT INTO seats 
	VALUES (3,10,1,2);
INSERT INTO seats 
	VALUES (4,1,1,2);
INSERT INTO seats 
	VALUES (4,2,1,2);
INSERT INTO seats 
	VALUES (4,3,1,2);
INSERT INTO seats 
	VALUES (4,4,1,2);
INSERT INTO seats 
	VALUES (4,5,1,2);
INSERT INTO seats 
	VALUES (4,6,1,2);
INSERT INTO seats 
	VALUES (4,7,1,2);
INSERT INTO seats 
	VALUES (4,8,1,2);
INSERT INTO seats 
	VALUES (4,9,1,2);
INSERT INTO seats 
	VALUES (4,10,1,2);
INSERT INTO seats 
	VALUES (5,1,1,2);
INSERT INTO seats 
	VALUES (5,2,1,2);
INSERT INTO seats 
	VALUES (5,3,1,2);
INSERT INTO seats 
	VALUES (5,4,1,2);
INSERT INTO seats 
	VALUES (5,5,1,2);
INSERT INTO seats 
	VALUES (5,6,1,2);
INSERT INTO seats 
	VALUES (5,7,1,2);
INSERT INTO seats 
	VALUES (5,8,1,2);
INSERT INTO seats 
	VALUES (5,9,1,2);
INSERT INTO seats 
	VALUES (5,10,1,2);
INSERT INTO seats 
	VALUES (6,1,1,2);
INSERT INTO seats 
	VALUES (6,2,1,2);
INSERT INTO seats 
	VALUES (6,3,1,2);
INSERT INTO seats 
	VALUES (6,4,1,2);
INSERT INTO seats 
	VALUES (6,5,1,2);
INSERT INTO seats 
	VALUES (6,6,1,2);
INSERT INTO seats 
	VALUES (6,7,1,2);
INSERT INTO seats 
	VALUES (6,8,1,2);
INSERT INTO seats 
	VALUES (6,9,1,2);
INSERT INTO seats 
	VALUES (6,10,1,2);
INSERT INTO seats 
	VALUES (7,1,1,2);
INSERT INTO seats 
	VALUES (7,2,1,2);
INSERT INTO seats 
	VALUES (7,3,1,2);
INSERT INTO seats 
	VALUES (7,4,1,2);
INSERT INTO seats 
	VALUES (7,5,1,2);
INSERT INTO seats 
	VALUES (7,6,1,2);
INSERT INTO seats 
	VALUES (7,7,1,2);
INSERT INTO seats 
	VALUES (7,8,1,2);
INSERT INTO seats 
	VALUES (7,9,1,2);
INSERT INTO seats 
	VALUES (7,10,1,2);
	INSERT INTO seats 
	VALUES (7,11,1,2);
INSERT INTO seats 
	VALUES (7,12,1,2);

INSERT INTO product_types
	VALUES ('�������');
INSERT INTO product_types
	VALUES ('����');
INSERT INTO product_types
	VALUES ('�������');
INSERT INTO product_types
	VALUES ('�����');
INSERT INTO product_types
	VALUES ('������');
INSERT INTO product_types
	VALUES ('���������');
INSERT INTO product_types
	VALUES ('���������');

INSERT INTO products
	VALUES (1, '�����', 25, 3.81);

INSERT INTO films 
	VALUES ('������� �����������',2021,118,'����� ���� ����� ��� ����� ��������� � ��������� ������������� ������� ���� ������� 
	������� �� ��� ����, ���������� ������� � ������������ �������. � ��� ��� ��� ������ �������� �������, ���� � ������ XX ���� �� ���� 
	���������� ��� �� ���: ������ ���� ���� ������������ � ����� ����, ������ ���������� � ������� ���������, � ��������������� �������� ����������.',18,6.5);

INSERT INTO seances
	VALUES ('2021-11-29','17:40',1,1,'2D')

INSERT INTO genres_name
	VALUES (1,3)

	
INSERT INTO countries_name
	VALUES (1,2)

INSERT INTO payments
	VALUES ('���������');
INSERT INTO payments
	VALUES ('������');

DELETE FROM payments;
DBCC CHECKIDENT ('payments', RESEED, 0);

DELETE FROM films;
DBCC CHECKIDENT ('films', RESEED, 0);

DELETE FROM staff;
DBCC CHECKIDENT ('staff', RESEED, 0);

DELETE FROM access;
DBCC CHECKIDENT ('access', RESEED, 0);

DELETE FROM departments;
DBCC CHECKIDENT ('departments', RESEED, 0);

DELETE FROM countries;
DBCC CHECKIDENT ('countries', RESEED, 0);

DELETE FROM genres;
DBCC CHECKIDENT ('genres', RESEED, 0);

DELETE FROM genres_name;
DBCC CHECKIDENT ('genres_name', RESEED, 0);

DELETE FROM categories;
DBCC CHECKIDENT ('categories', RESEED, 0);

DELETE FROM seats;
DBCC CHECKIDENT ('seats', RESEED, 0);

DELETE FROM halls;
DBCC CHECKIDENT ('halls', RESEED, 0);

USE master; 
DROP DATABASE cinema;
DROP LOGIN programmer;
