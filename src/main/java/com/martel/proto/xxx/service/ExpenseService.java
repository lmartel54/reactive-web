package com.martel.proto.xxx.service;

import java.util.UUID;

import com.martel.proto.xxx.entity.InvoiceGas;
import com.martel.proto.xxx.entity.view.InvoiceGasView;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExpenseService {

	Mono<InvoiceGasView> find(UUID entityId);

	Flux<InvoiceGasView> findAll();

	Mono<InvoiceGas> save(InvoiceGasView expense);

//	Mono<Void> delete(UUID entityId);
	
	void deletAll();
}
