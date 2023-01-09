CREATE TABLE public.tasks
(
    id uuid NOT NULL,
    description character varying(1000),
    employee_id integer,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
)