package com.martel.proto.service;

import java.util.UUID;

import com.martel.proto.entity.Invoice;

import reactor.core.publisher.Mono;

public interface InvoiceService {

	Mono<Invoice> save(Invoice invoice);

	Mono<Void> deleteCascade(UUID entityId);
}
