package com.martel.proto.xxx.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.martel.proto.xxx.entity.InvoiceGas;

public interface InvoiceGasCrudRepository extends ReactiveCrudRepository<InvoiceGas, UUID> {
}