CREATE SEQUENCE IF NOT EXISTS public."sequence_generator"
    INCREMENT 50,
    START 1;

-- Table: public.student

DROP TABLE IF EXISTS public.student;

CREATE TABLE IF NOT EXISTS public.student
(
    id bigint NOT NULL,
    first_name character varying(100) NOT NULL,
    last_name character varying(100),
    nik bigint NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (id),
    CONSTRAINT uq_nik UNIQUE (nik)
    );

INSERT INTO public.student (id, first_name, last_name, nik) VALUES
(1, 'Andra', 'Angjaya', 3201012023000001),
(2, 'Putu', 'Made', 3201012023000002),
(3, 'Siti', 'Nurhaliza', 3201012023000003),
(4, 'Budi', 'Santoso', 3201012023000004),
(5, 'Dewi', 'Lestari', 3201012023000005),
(6, 'Agus', 'Pratama', 3201012023000006),
(7, 'Rina', 'Wulandari', 3201012023000007),
(8, 'Joko', 'Susanto', 3201012023000008),
(9, 'Maya', 'Kartika', 3201012023000009),
(10, 'Fajar', 'Hidayat', 3201012023000010);

-- Table: public.session_history

DROP TABLE IF EXISTS public.session_history;

CREATE TABLE IF NOT EXISTS public.session_history
(
    session_id bigint NOT NULL,
    session integer,
    status character varying(12),
    time_started timestamp(6) with time zone NOT NULL,
    time_finished timestamp(6) with time zone,
   total_distraction integer,
   student_id bigint NOT NULL,
   CONSTRAINT session_history_pkey PRIMARY KEY (session_id),
    CONSTRAINT fk_student_id FOREIGN KEY (student_id)
    REFERENCES public.student (id),
    CONSTRAINT session_history_status_check CHECK (status::text = ANY (ARRAY['DONE'::character varying, 'IN_PROGRESS'::character varying]::text[]))
    )
