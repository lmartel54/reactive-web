package com.martel.proto.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martel.proto.entity.view.InvoiceGasView;
import com.martel.proto.service.InvoiceGasService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/invoice/gas", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class InvoiceGasController {

	@Autowired
	InvoiceGasService invoiceGasService;

	@GetMapping
	public Flux<InvoiceGasView> findAll() {
		return invoiceGasService.findAll();
	}

	@GetMapping("/{entityId}")
	public Mono<InvoiceGasView> find(@PathVariable UUID entityId) {
		return invoiceGasService.find(entityId);
	}

	@PostMapping
	public Mono<InvoiceGasView> save(@RequestBody InvoiceGasView invoice) {
		return invoiceGasService.save(invoice);
	}
}