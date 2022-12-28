CREATE TABLE public.employees
(
    id SERIAL NOT NULL PRIMARY KEY,
    last_name character varying(50),
    first_name character varying(50),
    middle_name character varying(50),
    "position" character varying(50),
    gender character varying(1),
    phone character varying(15),
    salary numeric,
    birthday date,
    start_date date,
    end_date date,
    description character varying(1000)
)
