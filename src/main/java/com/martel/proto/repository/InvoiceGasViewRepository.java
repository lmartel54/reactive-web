package com.martel.proto.repository;

import java.util.UUID;

import com.martel.proto.entity.view.InvoiceGasView;
import com.martel.proto.repository.core.ReactiveViewRepository;

public interface InvoiceGasViewRepository extends ReactiveViewRepository<InvoiceGasView, UUID> {
}