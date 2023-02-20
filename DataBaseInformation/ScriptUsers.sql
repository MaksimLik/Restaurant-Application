delimiter //
create procedure display_menu() 
begin 
select * from menu; 
end //

call display_menu;



DELIMITER  //
CREATE FUNCTION how_many_order (
 idCust int
) 
 RETURNS int DETERMINISTIC
BEGIN
declare IluZamowien int;
 SELECT COUNT(*)
 INTO IluZamowien
 FROM orders
 WHERE Customers_Users_ID_user = idCust;
 RETURN IluZamowien;
END; //

select how_many_order(3);



delimiter //
create procedure display_delivery() 
begin 
select id_dilivery, date, invoice, Orders_ID_order, nick_name from delivery inner join customers on Orders_Customers_Users_ID_user = Users_ID_user; 
end //

call display_delivery;



DELIMITER  //
CREATE FUNCTION how_much (
 idCust int
) 
 RETURNS int DETERMINISTIC
BEGIN
declare cost int;
 SELECT round(sum(menu.price) + sum(additions.price), 2) as price
 INTO cost
 FROM (orders left outer join additions on orders.Additions_ID_addition = additions.ID_addition) 
left join menu on orders.Menu_ID_food = menu.ID_food
 WHERE Customers_Users_ID_user = idCust;
 RETURN cost;
END; //

select how_much(4);

insert into users (name, surname, nick_name, password) values ('admin', 'admin', 'admin', 'admin');
insert into users (name, surname, nick_name, password) values ('manager', 'manager', 'manager', 'manager');

insert into employees (role, Users_ID_user, nick_name) values ('admin', 1, 'admin');
insert into employees (role, Users_ID_user, nick_name) values ('manager', 2, 'manager');
