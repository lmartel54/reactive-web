package com.martel.proto.data.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.martel.proto.data.entity.Invoice;

public interface InvoiceRepository extends ReactiveCrudRepository<Invoice, Long> {

}