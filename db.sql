DROP SEQUENCE IF EXISTS sequence_generator;
DROP TABLE IF EXISTS public.distraction_history;
DROP TABLE IF EXISTS public.session_history;
DROP TABLE IF EXISTS public.student;


CREATE SEQUENCE IF NOT EXISTS public.sequence_generator
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Table: public.student



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
                                                                (1, 'Andra', 'Angjaya', 15327),
                                                                (2, 'Putu', 'Made', 15326),
                                                                (3, 'Siti', 'Nurhaliza', 3201012023000003),
                                                                (4, 'Budi', 'Santoso', 3201012023000004),
                                                                (5, 'Dewi', 'Lestari', 3201012023000005),
                                                                (6, 'Agus', 'Pratama', 3201012023000006),
                                                                (7, 'Rina', 'Wulandari', 3201012023000007),
                                                                (8, 'Joko', 'Susanto', 3201012023000008),
                                                                (9, 'Maya', 'Kartika', 3201012023000009),
                                                                (10, 'Fajar', 'Hidayat', 3201012023000010);

-- Table: public.session_history



CREATE TABLE IF NOT EXISTS public.session_history
(
    id bigint NOT NULL,
    session integer NOT NULL,
    status character varying(12),
    session_date timestamp(6) with time zone NOT NULL,
                                  time_started timestamp(6) with time zone NOT NULL,
                                  time_finished timestamp(6) with time zone,
                                  total_distraction integer,
                                  student_id bigint NOT NULL,
                                  CONSTRAINT session_history_pkey PRIMARY KEY (id),
    CONSTRAINT fk_student_id FOREIGN KEY (student_id)
    REFERENCES public.student (id),
    CONSTRAINT unique_studentSession UNIQUE (session_date, student_id, session ),
    CONSTRAINT session_history_status_check CHECK (status::text = ANY (ARRAY['DONE'::character varying, 'IN_PROGRESS'::character varying]::text[]))
    );

-- Table: public.distraction_history



CREATE TABLE IF NOT EXISTS public.distraction_history
(
    id bigint NOT NULL,
    session_history_id bigint NOT NULL,
    distractions integer NOT NULL,
    distraction_start timestamp with time zone,
    distraction_end timestamp with time zone,
    time_elapsed bigint NOT NULL,
    CONSTRAINT distraction_history_pkey PRIMARY KEY (id),
    CONSTRAINT fk_session_history FOREIGN KEY (session_history_id)
    REFERENCES public.session_history (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    );





-- select * from distraction_history


-- select * from session_history where session = 1 and student_id = 1 and time_started between '2025-08-28 00:00:00' and '2025-08-28 23:59:59'