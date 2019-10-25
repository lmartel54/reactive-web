package com.martel.proto.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.martel.proto.entity.Invoice;

public interface InvoiceCrudRepository extends ReactiveCrudRepository<Invoice, UUID> {
}