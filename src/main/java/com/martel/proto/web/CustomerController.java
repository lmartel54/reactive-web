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

import com.martel.proto.data.entity.Customer;
import com.martel.proto.data.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/customer", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class CustomerController {

	@Autowired
	CustomerRepository repo;

	@GetMapping("/id/{entityId}")
	public Mono<Customer> find(@PathVariable Long entityId) {
		return repo.findById(entityId);
	}

	@GetMapping("/all")
	public Flux<Customer> findAll() {
		return repo.findAll();
	}

	@PostMapping(value = "/save")
	public Mono<Customer> save(@RequestBody Customer customer) {
		return repo.save(customer);
	}

	@DeleteMapping(value = "/id/{entityId}")
	public Mono<Void> delete(@PathVariable Long entityId) {
		return repo.deleteById(entityId);
	}
}