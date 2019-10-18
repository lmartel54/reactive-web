package com.martel.proto.data.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.martel.proto.data.entity.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {

//	@Query("select * from customer where name = $1 and age = $2")
//	Flux<Customer> findByNameAndAge(String name, Integer age);
}