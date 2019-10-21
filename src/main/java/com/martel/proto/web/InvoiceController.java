package com.martel.proto.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martel.proto.data.entity.Invoice;
import com.martel.proto.data.repository.InvoiceRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/invoice", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class InvoiceController {

	@Autowired
	InvoiceRepository repo;

	@GetMapping("/id/{entityId}")
	public Mono<Invoice> find(@PathVariable Long entityId) {
		return repo.findById(entityId);
	}

	@GetMapping("/all")
	public Flux<Invoice> findAll() {
		return repo.findAll();
	}

	@PostMapping(value = "/save")
	public Mono<Invoice> save(@RequestBody Invoice Invoice) {
		return repo.save(Invoice);
	}

	@DeleteMapping(value = "/id/{entityId}")
	public Mono<Void> delete(@PathVariable Long entityId) {
		return repo.deleteById(entityId);
	}
}