package com.martel.proto.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martel.proto.service.InvoiceService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/invoice", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@DeleteMapping(value = "/{entityId}")
	public Mono<Void> delete(@PathVariable UUID entityId) {
		return invoiceService.deleteCascade(entityId);
	}
}