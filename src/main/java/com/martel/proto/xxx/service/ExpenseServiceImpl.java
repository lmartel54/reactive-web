package com.martel.proto.xxx.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martel.proto.unit.repository.InvoiceCrudRepositoryTest;
import com.martel.proto.unit.repository.InvoiceGasCrudRepositoryTest;
import com.martel.proto.xxx.entity.Invoice;
import com.martel.proto.xxx.entity.InvoiceGas;
import com.martel.proto.xxx.entity.view.InvoiceGasView;
import com.martel.proto.xxx.repository.InvoiceCrudRepository;
import com.martel.proto.xxx.repository.InvoiceGasCrudRepository;
import com.martel.proto.xxx.repository.InvoiceGasViewRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	// TODO rendre tout asynchrone !!!

	@Autowired
	InvoiceCrudRepository invoiceRepo;

	@Autowired
	InvoiceGasCrudRepository expenseRepo;

	@Autowired
	InvoiceGasViewRepository expenseGasViewRepo;

	public Mono<InvoiceGasView> find(UUID entityId) {
		return expenseGasViewRepo.findById(entityId);
	}

	public Flux<InvoiceGasView> findAll() {
		return expenseGasViewRepo.findAll();
	}

//	public Mono<InvoiceGas> save(InvoiceGas expense, Invoice invoice) {
		
	public Mono<InvoiceGas> save(InvoiceGasView expense) {

		final Invoice invoice = new Invoice(expense.getReference(), expense.getDay(), expense.getMonth(), expense.getYear(), expense.getAmount(), expense.getFile());
		invoice.setId(expense.getInvoiceId());

		final InvoiceGas invoiceGas = new InvoiceGas(
		expense.getStartPeriodDay(),
		expense.getStartPeriodMonth(),
		expense.getStartPeriodYear(),
		expense.getStartPeriodIndex(),
		expense.getEndPeriodDay(),
		expense.getEndPeriodMonth(),
		expense.getEndPeriodYear(),
		expense.getEndPeriodIndex(),
		expense.getPeriodDuration(),
		expense.isAutoReport(),
		expense.getVolume(),
		expense.getConsumption());
		invoiceGas.setId(expense.getId());

		return invoiceRepo.save(invoice)
								.flatMap(inserted -> {
									expense.setInvoiceId(inserted.getId());
									return expenseRepo.save(expense);
								});
	}

	public void deletAll() {
		expenseRepo  .deleteAll()
							.block();
		invoiceRepo  .deleteAll()
							.block();
	}

//	public Mono<Void> delete(UUID entityId) {
//
//		return expenseRepo.findById(entityId)
//								.doOnSuccess(expense -> {
//									invoiceRepo .deleteById(expense.getInvoice_id())
//													.flatMap(Void -> 
//														return expenseRepo.deleteById(expense.getId());
//													);
//								});
//
////		return expenseRepo.deleteById(entityId);
////		
//	}
}
