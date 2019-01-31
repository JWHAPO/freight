/** ta2user 생성 */
drop table  if exists ta2user;
create table ta2user(
   user_id int primary key auto_increment,
   user_no int not null,
   email varchar(120) not null,
   password varchar(512) not null,
   name varchar(120) not null,
   phone varchar(12),
   image_path varchar(512),
   experience_value int,
   level_id int,
    created_time_at timestamp,
    updated_time_at timestamp);


/** ta2car 생성 */
    drop table  if exists ta2car;
    create table ta2car(
       car_id int primary key auto_increment,
       car_no int not null,
       description varchar(120) not null,
       width int,
       width_uom varchar(20),
       length int,
       length_uom varchar(20),
       loadable_height int,
       loadable_height_uom varchar(20),
       loadable_weight int,
       loadable_weight_uom varchar(20),
       created_time_at timestamp,
       updated_time_at timestamp
    );

   delete from ta2car;
   insert into ta2car(car_no,description,width,width_uom,length, length_uom,loadable_height,loadable_height_uom,loadable_weight,loadable_weight_uom)
   values ( '1', '1 톤',160,'cm',280,'cm',170,'cm',1.1,'ton');
   insert into ta2car(car_no,description,width,width_uom,length, length_uom,loadable_height,loadable_height_uom,loadable_weight,loadable_weight_uom)
   values ( '2', '1 탑차',155,'cm',280,'cm',170,'cm',1.1,'ton');
   insert into ta2car(car_no,description,width,width_uom,length, length_uom,loadable_height,loadable_height_uom,loadable_weight,loadable_weight_uom)
   values ( '3', '1 윙카',155,'cm',280,'cm',170,'cm',1.1,'ton');
   insert into ta2car(car_no,description,width,width_uom,length, length_uom,loadable_height,loadable_height_uom,loadable_weight,loadable_weight_uom)
   values ( '4', '1 톤(초장축)',160,'cm',280,'cm',170,'cm',1.1,'ton');
   insert into ta2car(car_no,description,width,width_uom,length, length_uom,loadable_height,loadable_height_uom,loadable_weight,loadable_weight_uom)
   values ( '5', '1.4 톤',163,'cm',310,'cm',170,'cm',1.54,'ton');
   insert into ta2car(car_no,description,width,width_uom,length, length_uom,loadable_height,loadable_height_uom,loadable_weight,loadable_weight_uom)
   values ( '6', '1.4 탑차',158,'cm',310,'cm',160,'cm',1.54,'ton');
   insert into ta2car(car_no,description,width,width_uom,length, length_uom,loadable_height,loadable_height_uom,loadable_weight,loadable_weight_uom)
   values ( '7', '1.4 윙카',158,'cm',310,'cm',170,'cm',1.54,'ton');
   insert into ta2car(car_no,description,width,width_uom,length, length_uom,loadable_height,loadable_height_uom,loadable_weight,loadable_weight_uom)
   values ( '8', '1.4톤(초장축)',163,'cm',340,'cm',170,'cm',1.54,'ton');
   insert into ta2car(car_no,description,width,width_uom,length, length_uom,loadable_height,loadable_height_uom,loadable_weight,loadable_weight_uom)
   values ( '9', '2.5 톤',192,'cm',435,'cm',210,'cm',2.75,'ton');
   insert into ta2car(car_no,description,width,width_uom,length, length_uom,loadable_height,loadable_height_uom,loadable_weight,loadable_weight_uom)
   values ( '10', '2.5 탑차',185,'cm',430,'cm',190,'cm',2.75,'ton');
   insert into ta2car(car_no,description,width,width_uom,length, length_uom,loadable_height,loadable_height_uom,loadable_weight,loadable_weight_uom)
   values ( '11', '2.5 윙카',185,'cm',430,'cm',200,'cm',2.75,'ton');


    /** ta2user_car 생성 */
drop table  if exists ta2user_car;
    create table ta2user_car(
     user_car_id int primary key auto_increment,
     user_id int not null,
     car_id int not null,
     created_time_at timestamp,
     updated_time_at timestamp);

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


    /** ta2email_auth 생성 */
drop table  if exists ta2email_auth;
    create table ta2email_auth(
     email_auth_id int primary key auto_increment,
     user_id int not null,
     email varchar(120) not null,
     auth_key varchar(512) not null,
     expired_date timestamp,
     is_auth varchar(4),
     created_time_at timestamp,
     updated_time_at timestamp);


    /** ta2order 생성 */
drop table  if exists ta2order;
    create table ta2order(
     order_id int primary key auto_increment,
     description varchar(512) not null,
     car_id int,
     departure_address varchar(512) not null,
     arrival_address varchar(512) not null,
     distance int not null,
     hope_date date,
     hope_time time,
     hope_price DECIMAL not null,
     is_mixed varchar(4) default 'N',
     remark varchar(512),
     status varchar(64) not null,
     created_time_at timestamp,
     updated_time_at timestamp);

     /** ta2user_role 생성 */
drop table  if exists ta2user_role;
create table ta2user_role(
     user_role_id int primary key auto_increment,
     user_id int not null,
     role_id int not null,
     created_time_at timestamp);

     /** ta2role 생성 */
drop table  if exists ta2role;
create table ta2role(
     role_id int primary key auto_increment,
     name varchar(60) not null,
     created_time_at timestamp,
     updated_time_at timestamp);



