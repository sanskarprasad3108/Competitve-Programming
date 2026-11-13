# Write your MySQL query statement below
select d.product_name , e.year,e.price from Sales e
inner join Product d on
e.product_id=d.product_id