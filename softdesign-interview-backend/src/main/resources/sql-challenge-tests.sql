-- create
CREATE TABLE employees (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  salary INTEGER NOT NULL
);

CREATE TABLE departments (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL
);

ALTER TABLE employees ADD department_id INTEGER;

ALTER TABLE employees
    ADD CONSTRAINT fk_employees_department FOREIGN KEY (department_id) REFERENCES departments (id);

-- insert
INSERT INTO departments VALUES (1, 'Sales');
INSERT INTO departments VALUES (2, 'IT');
INSERT INTO departments VALUES (3, 'Marketing');

INSERT INTO employees VALUES (1, 'Clark', 1000, 1);
INSERT INTO employees VALUES (2, 'Dave', 1500, 1);
INSERT INTO employees VALUES (3, 'Ava', 2000, 1);

-- fetch 
-- SELECT name, salary FROM employees
-- EXPLAIN SELECT AVG(salary) FROM employees;
EXPLAIN SELECT e.name, d.name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id