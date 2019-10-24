package com.martel.proto.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/customer", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class CustomerController {

//	@Autowired
//	CustomerRepository repo;
//
//	@GetMapping("/id/{entityId}")
//	public Mono<Customer> find(@PathVariable Long entityId) {
//		return repo.findById(entityId);
//	}
//
//	@GetMapping("/all")
//	public Flux<Customer> findAll() {
//		return repo.findAll().delayElements(Duration.ofSeconds(1));
//	}
//
//	@PostMapping(value = "/save")
//	public Mono<Customer> save(@RequestBody Customer customer) {
//		return repo.save(customer);
//	}
//
//	@DeleteMapping(value = "/id/{entityId}")
//	public Mono<Void> delete(@PathVariable Long entityId) {
//		return repo.deleteById(entityId);
//	}
}