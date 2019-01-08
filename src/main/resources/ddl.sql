/** ta2user_car 생성 */
create table ta2user_car(
   id int primary key auto_increment,
   user_id int not null,
   car_id int not null);