/** ta2user 생성 */
create table ta2user(
   user_id int primary key auto_increment,
   user_no int not null,
   email varchar(120) not null,
   password varchar(512) not null,
   name varchar(120) not null,
   address varchar(256),
   level_id int );

   insert into ta2user(user_no,email,password,name)
   values ( '1', 'mrKim1@email.com','1234','Mr.Kim' );
   insert into ta2user(user_no,email,password,name)
   values ( '2', 'mrKim2@email.com','1234','Mr.Kim' );
   insert into ta2user(user_no,email,password,name)
   values ( '3', 'mrKim3@email.com','1234','Mr.Kim' );
   insert into ta2user(user_no,email,password,name)
   values ( '4', 'mrKim4@email.com','1234','Mr.Kim' );


/** ta2car 생성 */
create table ta2car(
   car_id int primary key auto_increment,
   car_no int not null,
   description varchar(120) not null,
   weight int,
   weight_uom varchar(20),
   maker varchar(20) );


   insert into ta2car(car_no,description,weight)
   values ( '1', 'Porter',1000 );
   insert into ta2car(car_no,description,weight)
   values ( '2', 'Bongo',2000 );
   insert into ta2car(car_no,description,weight)
   values ( '3', 'M3',1500 );

    /** ta2user_car 생성 */
    create table ta2user_car(
     user_car_id int primary key auto_increment,
     user_id int not null,
     car_id int not null);

   insert into ta2user_car(user_id,car_id)
   values ( 1,2);
   insert into ta2user_car(user_id,car_id)
   values ( 1,1);
   insert into ta2user_car(user_id,car_id)
   values ( 2,1);
   insert into ta2user_car(user_id,car_id)
   values ( 3,1);
   insert into ta2user_car(user_id,car_id)
   values ( 3,2);
   insert into ta2user_car(user_id,car_id)
   values ( 3,3);