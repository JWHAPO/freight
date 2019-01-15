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
   level_id int );
   delete from ta2user;
   insert into ta2user(user_no,email,password,name)
   values ( '1', 'jw.kim@email.com','1234','Mr.Kim' );
   insert into ta2user(user_no,email,password,name)
   values ( '2', 'jw.kim@email.com','1234','Mr.Kim' );
   insert into ta2user(user_no,email,password,name)
   values ( '3', 'jw.kim@email.com','1234','Mr.Kim' );
   insert into ta2user(user_no,email,password,name)
   values ( '4', 'jw.kim@email.com','1234','Mr.Kim' );


/** ta2car 생성 */
drop table  if exists ta2car;
create table ta2car(
   car_id int primary key auto_increment,
   car_no int not null,
   description varchar(120) not null );

    alter table ta2car add column width int;
    alter table ta2car add column width_uom varchar(20);
    alter table ta2car add column length int;
    alter table ta2car add column length_uom varchar(20);
    alter table ta2car add column loadable_height int;
    alter table ta2car add column loadable_height_uom varchar(20);
    alter table ta2car add column loadable_weight int;
    alter table ta2car add column loadable_weight_uom varchar(20);

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


    /** ta2email_auth 생성 */
drop table  if exists ta2email_auth;
    create table ta2email_auth(
     email_auth_id int primary key auto_increment,
     user_id int not null,
     email varchar(120) not null,
     auth_key varchar(512) not null,
     reg_date timestamp,
     expired_date datetime,
     is_auth varchar(4));

