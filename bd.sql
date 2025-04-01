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

INSERT INTO public.clients ("name",kind_property,address,phone,contact) VALUES
	 ('Иванов Иван','Квартира','Москва, ул. Ленина, д. 1','+79161234567','ivanov@example.com'),
	 ('Сидоров Никита','Телефон','г.Москва, ул. Пушкина, д.17','+79181212222','sidorovn@mail.ru'),
	 ('Никитин Андрей','Автомобиль','Казань, ул. Ленина, д.3','+79128311311','sidorova11@example.com');

INSERT INTO public.kind_credit (name,conditions,rate,term) VALUES
	 ('Потребительский','Подтверждение дохода, возраст от 21 года',15.5,36),
	 ('Автокредит','Первоначальный взнос от 20%, КАСКО обязательно',9.9,60),
	 ('Рассрочка','Без превоначального взноса',1.0,6);

INSERT INTO public.credits (kind_credit_id,client_id,summa,"date") VALUES
	 (1,1,150000,'2024-02-08'),
	 (6,3,200000,'2025-03-15'),
	 (6,9,10000000,'2025-03-15');

