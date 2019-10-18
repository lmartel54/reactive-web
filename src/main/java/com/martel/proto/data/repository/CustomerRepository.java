package com.martel.proto.data.repository;

import java.time.Duration;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.martel.proto.data.entity.Customer;

import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {

	default Flux<Customer> findAll2() {
		return findAll().delayElements(Duration.ofSeconds(2));
	}

//	@Query("select * from customer where name = $1 and age = $2")
//	Flux<Customer> findByNameAndAge(String name, Integer age);
	
	
}