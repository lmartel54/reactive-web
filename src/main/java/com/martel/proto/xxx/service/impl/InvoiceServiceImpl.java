package com.martel.proto.xxx.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martel.proto.xxx.entity.Invoice;
import com.martel.proto.xxx.repository.InvoiceCrudRepository;
import com.martel.proto.xxx.service.InvoiceService;

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
