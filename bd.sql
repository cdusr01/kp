CREATE TABLE public.clients (
	client_id int4 GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
	"name" varchar(255) NOT NULL,
	kind_property varchar(255) NOT NULL,
	address varchar(255) NOT NULL,
	phone varchar(20) NOT NULL,
	contact varchar(255) NOT NULL
);

CREATE TABLE public.kind_credit (
	kind_credit_id int4 GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
	"name" varchar(255) NULL,
	conditions text NOT NULL,
	rate numeric NOT NULL,
	term int4 NOT NULL
);

CREATE TABLE public.credits (
	credit_id int4 GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
	kind_credit_id int4 NOT NULL REFERENCES kind_credit(kind_credit_id) ON DELETE CASCADE,
	client_id int4 NOT NULL REFERENCES clients(client_id) ON DELETE CASCADE,
	summa numeric NOT NULL,
	"date" date NOT NULL
);
