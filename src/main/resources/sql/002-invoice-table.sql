create schema admin;

create table if not exists admin.invoice (
   ivx_id uuid primary key default gen_random_uuid(),
   ivx_reference varchar(50) not null,
   ivx_day int not null,
   ivx_month int not null,
   ivx_year int not null,
   ivx_amount decimal(7,2) not null,
   ivx_file varchar(255) not null
);

create table if not exists admin.invoice_gas (
   ivx_gas_id uuid primary key default gen_random_uuid(),
   ivx_id_fk uuid not null,
   ivx_gas_start_period_day int not null,
   ivx_gas_start_period_month int not null,
   ivx_gas_start_period_year int not null,
   ivx_gas_start_period_index int not null,
   ivx_gas_end_period_day int not null,
   ivx_gas_end_period_month int not null,
   ivx_gas_end_period_year int not null,
   ivx_gas_end_period_index int not null,
   ivx_gas_period_duration int not null,
   ivx_gas_auto_report boolean not null,
   ivx_gas_volume int not null,
   ivx_gas_consumption int not null,
   foreign key (ivx_id_fk) references admin.invoice (ivx_id) on delete cascade
);

--create table if not exists admin.invoice_electricity (
--   ivx_elec_id uuid primary key default gen_random_uuid(),
--   ivx_id_fk uuid not null,
--   ivx_elec_XXX varchar(50) not null,
--   foreign key (ivx_id_fk) references invoice (ivx_id)
--);
