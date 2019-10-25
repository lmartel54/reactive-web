package com.martel.proto.xxx.service;

import java.util.UUID;

import com.martel.proto.xxx.entity.view.InvoiceGasView;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InvoiceGasService {

	Mono<InvoiceGasView> find(UUID entityId);

	Flux<InvoiceGasView> findAll();

	Mono<InvoiceGasView> save(InvoiceGasView invoice);
}
