package com.martel.proto.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martel.proto.entity.Invoice;
import com.martel.proto.repository.InvoiceCrudRepository;
import com.martel.proto.service.InvoiceService;

import reactor.core.publisher.Mono;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceCrudRepository invoiceCrudRepo;

	public Mono<Invoice> save(final Invoice invoice) {
		return invoiceCrudRepo.save(invoice);
	}
	
	public Mono<Void> deleteCascade(UUID entityId) {
		return invoiceCrudRepo.deleteById(entityId);
	}
}
