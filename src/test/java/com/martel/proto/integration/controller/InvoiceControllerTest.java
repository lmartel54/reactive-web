package com.martel.proto.integration.controller;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.martel.proto.category.IntegrationTests;
import com.martel.proto.entity.Invoice;
import com.martel.proto.entity.InvoiceGas;
import com.martel.proto.entity.converter.InvoiceGasViewConverter;
import com.martel.proto.entity.view.InvoiceGasView;
import com.martel.proto.service.InvoiceGasService;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@Category(IntegrationTests.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InvoiceControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private InvoiceGasService invoiceGasService;

	@Test
	public void delete_InvoiceGas_Success() {

		final Invoice invoice = new Invoice("invoice-gas-controller-find-IT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceGas = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGasView expected = initContext(invoice, invoiceGas);

		StepVerifier.create(invoiceGasService.find(expected.getId()))
						.expectNextCount(1)
						.verifyComplete();

		webTestClient  .delete()
							.uri("/api/invoice/{entityId}", expected.getInvoiceId())
							.exchange()
							.expectStatus()
							.isOk();

		StepVerifier.create(invoiceGasService.find(expected.getId()))
						.expectNextCount(0)
						.verifyComplete();
	}

	private InvoiceGasView initContext(final Invoice invoice, final InvoiceGas invoiceGas) {
		return invoiceGasService.save(InvoiceGasViewConverter.from(invoice, invoiceGas))
										.block();
	}
}