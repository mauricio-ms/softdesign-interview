-- 1. Selecione todos os nomes e salários dos funcionários da tabela “employees”.
SELECT name, salary FROM employees;

-- 2. Liste os nomes dos funcionários e seus departamentos correspondentes da tabela “employees” e “departments”.
-- Primeiro é necessário criar a relação entre o departamento e o funcionário:
ALTER TABLE employees ADD department_id INTEGER;
ALTER TABLE employees
    ADD CONSTRAINT fk_employees_department FOREIGN KEY (department_id) REFERENCES departments (id);

SELECT e.name, d.name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id;

-- 3. Calcule a média salarial de todos os funcionários na tabela “employees”.
SELECT AVG(salary) FROM employees;