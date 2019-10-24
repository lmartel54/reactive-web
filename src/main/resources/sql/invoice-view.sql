
create or replace view admin.invoice_gas_view
as 
 select *
 from admin.invoice 
 inner join admin.invoice_gas on (ivx_id_fk = ivx_id); 
