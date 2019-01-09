/** ta2user 생성 */
create table ta2user(
   user_id int primary key auto_increment,
   user_no int not null,
   email varchar(120) not null,
   password varchar(512) not null,
   name varchar(120) not null,
   address varchar(256),
   level_id int );

/** ta2car 생성 */
create table ta2car(
   car_id int primary key auto_increment,
   car_no int not null,
   description varchar(120) not null,
   weight int,
   weight_uom varchar(20),
   maker varchar(20) );


    /** ta2user_car 생성 */
    create table ta2user_car(
     id int primary key auto_increment,
     user_id int not null,
     car_id int not null);