package com.martel.proto.xxx.service;

import java.util.UUID;

import com.martel.proto.xxx.entity.Invoice;

import reactor.core.publisher.Mono;

public interface InvoiceService {

	Mono<Invoice> save(Invoice invoice);

	Mono<Void> deleteCascade(UUID entityId);
}
