DROP DATABASE IF EXISTS proj_data;


Create database proj_data;
use proj_data;

CREATE TABLE proj_data.Employees
(
    SOEID VARCHAR(10) NOT NULL,
	LastName VARCHAR(20) NOT NULL, 
	FirstName VARCHAR(10) NOT NULL, 
	Title VARCHAR(30) NULL, 
	Gender VARCHAR(10) NULL, 
	BirthDate DATE NULL, 
	HireDate DATE NULL, 
	Address VARCHAR(60) NULL, 
	City VARCHAR(15) NULL, 
	Region VARCHAR(15) NULL, 
	PostalCode VARCHAR(10) NULL, 
	Country VARCHAR(15) NULL, 
	HomePhone VARCHAR(24) NULL, 
	Extension VARCHAR(4) NULL, 
	ReportsTo VARCHAR(10) NULL, 
    Emp_Stat VARCHAR(10) NULL, 
    Levels int,
	PRIMARY KEY(SOEID)
);

insert into proj_data.employees values ('ND12345', 'Davolio', 'Nancy', 'Sales Representative', 'Female', 19481208, 19920501, 
'507 - 20th Ave. E.Apt. 2A', 'Seattle', 'WA', '98122', 'USA', '(206) 555-9857', '5467', 'AF12346','Active', 1 );
insert into proj_data.employees values ('AF12346', 'Fuller', 'Andrew', 'Vice President, Sales', 'Male', 19520219, 19920814, 
'908 W. Capital Way', 'Tacoma', 'WA', '98401', 'USA', '(206) 555-9482', '3457', 'self', 'Active', 2);
insert into proj_data.employees values ('JL12347', 'Leverling', 'Janet', 'Sales Representative', 'Female', 19630830, 19920401, 
'722 Moss Bay Blvd.', 'Kirkland', 'WA', '98033', 'USA', '(206) 555-3412', '3355', 'AF12346','Active', 1);
insert into proj_data.employees values ('MP12348', 'Peacock', 'Margaret', 'Sales Representative', 'Female', 19370919, 19930503,
'4110 Old Redmond Rd.', 'Redmond', 'WA', '98052', 'USA', '(206) 555-8122', '5176', 'AF12346','Active', 1);
insert into proj_data.employees values ('SB12349', 'Buchanan', 'Steven', 'Sales Manager', 'Male', 19550304, 19931017,
'14 Garrett Hill', 'London', NULL, 'SW1 8JR', 'UK', '(71) 555-4848', '3453', 'AF12346','Active', 1);
insert into proj_data.employees values ('MS12350', 'Suyama', 'Michael', 'Sales Representative', 'Male', 19630702, 19931017,
'Coventry HouseMiner Rd.', 'London', NULL, 'EC2 7JR', 'UK', '(71) 555-7773', '428', 'SB12349','Active',0);
insert into proj_data.employees values ('RK12351', 'King', 'Robert', 'Sales Representative', 'Male', 19600529, 19940102, 
'Edgeham Hollow Winchester Way', 'London', NULL, 'RG1 9SP', 'UK', '(71) 555-5598', '465', 'SB12349','Active',0);
insert into proj_data.employees values ('LC12352', 'Callahan', 'Laura', 'Inside Sales Coordinator', 'Female', 19580109, 19940305,
'4726 - 11th Ave. N.E.', 'Seattle', 'WA', '98105', 'USA', '(206) 555-1189', '2344', 'AF12346','Active',1);
insert into proj_data.employees values ('AD12353', 'Dodsworth', 'Anne', 'Sales Representative', 'Female', 19660127, 19941115, 
'7 Houndstooth Rd.', 'London', NULL, 'WG2 7LT', 'UK', '(71) 555-4444', '452', 'SB12349','Active',0);

select * from proj_data.employees;



create table proj_data.login
(
    SOEID varchar(10) primary key,
    password varchar(50) not null,
    FOREIGN KEY(SOEID) REFERENCES employees(SOEID)
);

insert into proj_data.login values ('ND12345', 'password');
insert into proj_data.login values ('AF12346', 'password');
insert into proj_data.login values ('JL12347', 'password');
insert into proj_data.login values ('MP12348', 'password');
insert into proj_data.login values ('SB12349', 'password');
insert into proj_data.login values ('MS12350', 'password');
insert into proj_data.login values ('RK12351', 'password');
insert into proj_data.login values ('LC12352', 'password');
insert into proj_data.login values ('AD12353', 'password');



select * from proj_data.login;


#drop table proj_data.leave_details;

create table proj_data.leave_details
(
    SOEID varchar(10) primary key,
    FOREIGN KEY(SOEID) REFERENCES employees(SOEID),
    CL_Total int,
    CL_Available int,
    SL_Total int,
    SL_Available int,
    ML_Total int,
    ML_Available int,
    PML_Total int,
    PML_Available int
);

insert into proj_data.leave_details values ('ND12345', 15, 15, 2, 2, 10, 10, 90, 90);
insert into proj_data.leave_details values ('AF12346', 15, 15, 2, 2, 10, 10, 10, 10);
insert into proj_data.leave_details values ('JL12347', 15, 15, 2, 2, 10, 10, 90, 90);
insert into proj_data.leave_details values ('MP12348', 15, 15, 2, 2, 10, 10, 90, 90);
insert into proj_data.leave_details values ('SB12349', 15, 15, 2, 2, 10, 10, 10, 10);
insert into proj_data.leave_details values ('MS12350', 15, 15, 2, 2, 10, 10, 10, 10);
insert into proj_data.leave_details values ('RK12351', 15, 15, 2, 2, 10, 10, 10, 10);
insert into proj_data.leave_details values ('LC12352', 15, 15, 2, 2, 10, 10, 90, 90);
insert into proj_data.leave_details values ('AD12353', 15, 15, 2, 2, 10, 10, 90, 90);

select * from proj_data.leave_details;

#drop table proj_data.leave_desc;

Create table proj_data.leave_desc
(
	leave_id int primary key auto_increment,
    SOEID varchar(10),
    FOREIGN KEY(SOEID) REFERENCES employees(SOEID),
    soeid_mgr varchar(10),
    num_days int not null,
    start_date date not null,
    category varchar(20),
    comments longtext null,
    stat varchar(20)
);


select * from proj_data.leave_desc;


#select start_date, date(start_date+num_days-1) from proj_data.leave_desc; 

#Select * from employees where soeid='ND12345';

#update proj_data.leave_desc set stat='pending';
#select date(start_date+num_days-1) from leave_desc;
#Select * from leave_desc where date(now());

#select * from leave_details as l inner join employees as e on l.soeid = e.soeid;