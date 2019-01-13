/** ta2user 생성 */
create table ta2user(
   user_id int primary key auto_increment,
   user_no int not null,
   email varchar(120) not null,
   password varchar(512) not null,
   name varchar(120) not null,
   address varchar(256),
   level_id int );

alter table ta2user drop column address;
alter table ta2user add column image_path varchar(512);
alter table ta2user add column is_email_auth varchar(4);
alter table ta2user add column email_auth_key varchar(4);
alter table ta2user add column phone varchar(12);
alter table ta2user add column is_phone_auth varchar(4);
alter table ta2user add column phone_auth_key varchar(4);
alter table ta2user add column experience_value int;

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

    alter table ta2car add column width int;
    alter table ta2car add column width_uom varchar(20);
    alter table ta2car add column length int;
    alter table ta2car add column length_uom varchar(20);
    alter table ta2car drop column maker;
    alter table ta2car add column loadable_height int;
    alter table ta2car add column loadable_height_uom varchar(20);
    alter table ta2car add column loadable_weight int;
    alter table ta2car add column loadable_weight_uom varchar(20);
    alter table ta2car drop column weight;
    alter table ta2car drop column weight_uom;

   insert into ta2car(car_no,description,weight)
   values ( '1', 'Porter',1000 );
   insert into ta2car(car_no,description,weight)
   values ( '2', 'Bongo',2000 );
   insert into ta2car(car_no,description,weight)
   values ( '3', 'M3',1500 );

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


    /** ta2order 생성 */

