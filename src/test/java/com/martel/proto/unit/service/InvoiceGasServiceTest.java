package com.martel.proto.unit.service;

import java.util.UUID;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.martel.proto.category.UnitTests;
import com.martel.proto.unit.repository.InvoiceCrudRepositoryTest;
import com.martel.proto.unit.repository.InvoiceGasCrudRepositoryTest;
import com.martel.proto.xxx.entity.Invoice;
import com.martel.proto.xxx.entity.InvoiceGas;
import com.martel.proto.xxx.entity.converter.InvoiceGasViewConverter;
import com.martel.proto.xxx.entity.view.InvoiceGasView;
import com.martel.proto.xxx.service.InvoiceGasService;
import com.martel.proto.xxx.service.InvoiceService;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@Category(UnitTests.class)
@SpringBootTest
public class InvoiceGasServiceTest {

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private InvoiceGasService invoiceGasService;

	@Test
	public void find_Success() {

		final Invoice invoice = new Invoice("invoice-gas-service-find-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGasView expected = initContext(invoice, invoiceDetail);

		StepVerifier.create(invoiceGasService.find(expected.getId()))
						.assertNext(actual -> checkEntity(actual, expected))
						.verifyComplete();

		cleanContext(expected.getInvoiceId());
	}

	@Test
	public void findAll_Success() {

		final Invoice invoice1 = new Invoice("invoice1-gas-service-findAll-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail1 = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGasView expected1 = initContext(invoice1, invoiceDetail1);

		final Invoice invoice2 = new Invoice("invoice2-gas-service-findAll-UT", 2, 2, 2018, 12.1, "\\dir2");
		final InvoiceGas invoiceDetail2 = new InvoiceGas(1, 1, 2019, 201, 31, 12, 2019, 400, 365, true, 202, 302);
		final InvoiceGasView expected2 = initContext(invoice2, invoiceDetail2);

		StepVerifier.create(invoiceGasService.findAll())
						.assertNext(actual -> checkEntity(actual, expected1))
						.assertNext(actual -> checkEntity(actual, expected2))
						.thenCancel()
						.verify();

		cleanContext(expected1.getInvoiceId());
		cleanContext(expected2.getInvoiceId());
	}

	@Test
	public void save_Success() {

		final Invoice invoice = new Invoice("invoice-gas-service-insert-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGasView expected = initContext(invoice, invoiceDetail);

		StepVerifier.create(invoiceGasService.find(expected.getId()))
						.expectNextCount(1)
						.verifyComplete();

		cleanContext(expected.getInvoiceId());
	}

	@Test
	public void delete_Success() {

		final Invoice invoice = new Invoice("invoice-gas-service-delete-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGasView expected = initContext(invoice, invoiceDetail);

		StepVerifier.create(invoiceGasService.find(expected.getId()))
						.expectNextCount(1)
						.verifyComplete();

		invoiceService .deleteCascade(expected.getInvoiceId())
							.block();

		StepVerifier.create(invoiceGasService.find(expected.getId()))
						.expectNextCount(0)
						.verifyComplete();

		cleanContext(expected.getInvoiceId());
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
		InvoiceCrudRepositoryTest.checkEntity(InvoiceGasViewConverter.getInvoice(expected), InvoiceGasViewConverter.getInvoice(actual));
		InvoiceGasCrudRepositoryTest.checkEntity(InvoiceGasViewConverter.getInvoiceGas(expected), InvoiceGasViewConverter.getInvoiceGas(actual));
	}
}