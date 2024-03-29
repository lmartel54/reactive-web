package com.martel.proto.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martel.proto.entity.InvoiceGas;
import com.martel.proto.entity.converter.InvoiceGasViewConverter;
import com.martel.proto.entity.view.InvoiceGasView;
import com.martel.proto.repository.InvoiceGasCrudRepository;
import com.martel.proto.repository.InvoiceGasViewRepository;
import com.martel.proto.service.InvoiceGasService;
import com.martel.proto.service.InvoiceService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InvoiceGasServiceImpl implements InvoiceGasService {

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	InvoiceGasCrudRepository invoiceGasCrudRepo;

	@Autowired
	InvoiceGasViewRepository invoiceGasViewRepo;

	public Mono<InvoiceGasView> find(final UUID entityId) {
		return invoiceGasViewRepo.findById(entityId);
	}

	public Flux<InvoiceGasView> findAll() {
		return invoiceGasViewRepo.findAll();
	}

	public Mono<InvoiceGasView> save(final InvoiceGasView view) {

		final InvoiceGas invoiceGas = InvoiceGasViewConverter.getInvoiceGas(view);

		return invoiceService.save(InvoiceGasViewConverter.getInvoice(view))
									.flatMap(invoice -> {
										invoiceGas.setInvoiceId(invoice.getId());
										return invoiceGasCrudRepo.save(invoiceGas);
									})
									.map(result -> {
										view.setId(result.getId());
										view.setInvoiceId(result.getInvoiceId());
										return view;
									});
	}
}
