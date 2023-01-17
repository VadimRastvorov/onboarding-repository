CREATE TABLE public.employees
(
    id SERIAL PRIMARY KEY,
    last_name character varying(50),
    first_name character varying(50),
    middle_name character varying(50),
    "position" character varying(50),
    gender character varying(1),
    phone character varying(15),
    salary real,
    birthday date,
    start_date date,
    end_date date,
    description character varying(1000)
);
CREATE TABLE public.tasks
(
    id uuid NOT NULL,
    description character varying(1000),
    employee_id integer,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);
insert into employees (last_name, first_name, middle_name, position, gender, phone, salary, birthday, start_date, description) values
            ('Сидоров', 'Сидор', 'Сидрович','MANAGER', 'M','+77777777777','666666','1984-11-12','2001-01-02', ''),
            ('Иванов', 'Иван', 'Иванович','MANAGER', 'M','+77772222222','333333','1999-01-13','2001-01-02',''),
            ('Владимиров', 'Владимир', 'Вовкович','MANAGER', 'M','+77771111111','5555555','1980-11-11','2001-01-02',''),
            ('Иванова', 'Ивана', 'Ивановна','MANAGER', 'F','+77777777777','666666','1980-11-11','2001-01-02',''),
            ('Сидорова', 'Сидора', 'Сидровна','MANAGER', 'F','+77777777777','666666','1980-11-11','2001-01-02',''),
            ('Морок', 'Марго', 'Сидровна','MANAGER', 'F','+77777777777','666666','1980-11-11','2001-01-02','');

insert into tasks(id, description, employee_id) values
            ('d4189f5f-2ec9-4097-98c9-f6186c26b5da','task_1_employee_1',1),
            ('efa27c98-2ac5-40e7-b61e-dae4eff3a665','task_2_employee_1',1),
            ('2736f40c-e200-45de-8273-396c92fbd3a7','task_3_employee_1',1),
            ('58949a26-55a5-4942-8e01-62fd61789bda','task_4_employee_1',1),
            ('0aa48bb4-c03a-4184-a346-9219889b51dd','task_5_employee_1',1),
            ('31249236-7681-426f-880c-a6fac2adbc9e','task_1_employee_2',2),
            ('5af9c47c-1b4e-4bd3-a7ef-5aba10d6798c','task_2_employee_2',2),
            ('fa7ffb31-8851-40a5-bfd3-6e2db1a279ec','task_3_employee_2',2),
            ('eff86fd2-0e78-4eef-a5d8-2c78a5d1ab3e','task_4_employee_2',2),
            ('26bc2d33-a581-411c-8c6e-a246ed4f94e7','task_5_employee_2',2);