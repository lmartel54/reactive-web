package com.martel.proto.integration.controller;

import static org.springframework.http.MediaType.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.martel.proto.category.IntegrationTests;
import com.martel.proto.unit.service.InvoiceGasServiceTest;
import com.martel.proto.xxx.entity.Invoice;
import com.martel.proto.xxx.entity.InvoiceGas;
import com.martel.proto.xxx.entity.converter.InvoiceGasViewConverter;
import com.martel.proto.xxx.entity.view.InvoiceGasView;
import com.martel.proto.xxx.service.InvoiceGasService;
import com.martel.proto.xxx.service.InvoiceService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@Category(IntegrationTests.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InvoiceGasControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private InvoiceGasService invoiceGasService;

	@Test
	public void find_Success() {

		final Invoice invoice = new Invoice("invoice-gas-controller-find-IT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceGas = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGasView expected = initContext(invoice, invoiceGas);

		final FluxExchangeResult<InvoiceGasView> result = webTestClient.get()
																							.uri("/api/invoice/gas/{entityId}", expected.getId())
																							.exchange()
																							.expectStatus()
																							.isOk()
																							.expectHeader()
																							.contentType(TEXT_EVENT_STREAM + ";charset=UTF-8")
																							.returnResult(InvoiceGasView.class);

		StepVerifier.create(result.getResponseBody())
						.consumeNextWith(actual -> checkEntity(actual, expected))
						.verifyComplete();

		cleanContext(expected.getInvoiceId());
	}

	@Test
	public void findAll_Success() {

		final Invoice invoice1 = new Invoice("invoice1-gas-controller-findAll-IT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail1 = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGasView expected1 = initContext(invoice1, invoiceDetail1);

		final Invoice invoice2 = new Invoice("invoice2-gas-controller-findAll-IT", 2, 2, 2018, 12.1, "\\dir2");
		final InvoiceGas invoiceDetail2 = new InvoiceGas(1, 1, 2019, 201, 31, 12, 2019, 400, 365, true, 202, 302);
		final InvoiceGasView expected2 = initContext(invoice2, invoiceDetail2);

		final FluxExchangeResult<InvoiceGasView> result = webTestClient.get()
																							.uri("/api/invoice/gas")
																							.exchange()
																							.expectStatus()
																							.isOk()
																							.expectHeader()
																							.contentType(TEXT_EVENT_STREAM + ";charset=UTF-8")
																							.returnResult(InvoiceGasView.class);

		StepVerifier.create(result.getResponseBody())
						.consumeNextWith(actual -> checkEntity(actual, expected1))
						.consumeNextWith(actual -> checkEntity(actual, expected2))
						.thenCancel()
						.verify();

		cleanContext(expected1.getInvoiceId());
		cleanContext(expected2.getInvoiceId());
	}

	//===> rassembler les jeu de données ????????????????????????????????????????????????????????????????????????
	// suppression par invoice et non id invoice ???????????????????????????????????????????????????????????????
	
	@Test
	public void insert_Success() {

		final Invoice invoice = new Invoice("invoice-gas-controller-insert-IT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceGas = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGasView expected = InvoiceGasViewConverter.from(invoice, invoiceGas);

		final FluxExchangeResult<InvoiceGasView> result = webTestClient.post()
																							.uri("/api/invoice/gas")
																							.accept(MediaType.TEXT_EVENT_STREAM)
																							.body(Mono.just(expected), InvoiceGasView.class)
																							.exchange()
																							.expectStatus()
																							.isOk()
																							.expectHeader()
																							.contentType(TEXT_EVENT_STREAM + ";charset=UTF-8")
																							.returnResult(InvoiceGasView.class);

		StepVerifier.create(result.getResponseBody())
						.consumeNextWith(actual -> {
							expected.setId(actual.getId());
							expected.setInvoiceId(actual.getInvoiceId());
							checkEntity(actual, expected);
						})
						.thenCancel()
						.verify();

		cleanContext(expected.getInvoiceId());
	}

	@Test
	public void update_Success() {

		final Invoice invoice = new Invoice("invoice-gas-controller-update-IT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceGas = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGasView current = initContext(invoice, invoiceGas);

		current.setReference("invoice-gas-controller-updated-IT");

		final FluxExchangeResult<InvoiceGasView> result = webTestClient.post()
																							.uri("/api/invoice/gas")
																							.accept(MediaType.TEXT_EVENT_STREAM)
																							.body(Mono.just(current), InvoiceGasView.class)
																							.exchange()
																							.expectStatus()
																							.isOk()
																							.expectHeader()
																							.contentType(TEXT_EVENT_STREAM + ";charset=UTF-8")
																							.returnResult(InvoiceGasView.class);

		StepVerifier.create(result.getResponseBody())
						.consumeNextWith(actual -> checkEntity(actual, current))
						.thenCancel()
						.verify();

		cleanContext(current.getInvoiceId());
	}

	private InvoiceGasView initContext(final Invoice invoice, final InvoiceGas invoiceGas) {
		return invoiceGasService.save(InvoiceGasViewConverter.from(invoice, invoiceGas))
										.block();
	}

	private void cleanContext(UUID... ids) {
		for (UUID id : ids) {
			invoiceService .deleteCascade(id)
								.block();
		}
	}

	public static void checkEntity(final InvoiceGasView actual, final InvoiceGasView expected) {
		InvoiceGasServiceTest.checkEntity(actual, expected);
	}
}