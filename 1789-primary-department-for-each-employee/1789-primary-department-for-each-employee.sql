# Write your MySQL query statement below
select employee_id,department_id
from Employee
Group By employee_id
HAVING count(department_id)=1

UNION

select employee_id,department_id
from employee
where primary_flag='Y';