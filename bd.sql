CREATE TABLE public.clients (
	client_id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	"name" varchar(255) NOT NULL,
	kind_property varchar(255) NOT NULL,
	address varchar(255) NOT NULL,
	phone varchar(20) NOT NULL,
	contact varchar(255) NOT NULL,
	CONSTRAINT clients_pkey PRIMARY KEY (client_id)
);

CREATE TABLE information_schema.kind_credit (
	kind_credit_id int4 NOT NULL,
	"name" varchar(255) NULL,
	conditions text NOT NULL,
	rate numeric NOT NULL,
	term int4 NOT NULL
);

CREATE TABLE public.credits (
	credit_id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	kind_credit_id int4 NOT NULL,
	client_id int4 NOT NULL,
	summa numeric NOT NULL,
	"date" date NOT NULL,
	CONSTRAINT credits_pkey PRIMARY KEY (credit_id)
);

ALTER TABLE public.credits ADD CONSTRAINT credits_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.clients(client_id);
ALTER TABLE public.credits ADD CONSTRAINT credits_kind_credit_id_fkey FOREIGN KEY (kind_credit_id) REFERENCES public.kind_credit(kind_credit_id);