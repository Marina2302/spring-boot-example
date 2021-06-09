insert into employee (id, first_name, last_name, birth_day, salary)
values (1, 'Ivan', 'Ivanov', '2020-06-01', 1000),
       (2, 'Petr', 'Petrov', '2012-08-23', 2000);

SELECT setval('employee_id_seq', (SELECT MAX(id) FROM employee));