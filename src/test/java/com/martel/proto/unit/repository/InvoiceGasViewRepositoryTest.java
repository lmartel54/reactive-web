package com.martel.proto.unit.repository;

import java.util.UUID;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.martel.proto.category.UnitTests;
import com.martel.proto.xxx.entity.Invoice;
import com.martel.proto.xxx.entity.InvoiceGas;
import com.martel.proto.xxx.entity.converter.InvoiceGasViewConverter;
import com.martel.proto.xxx.entity.view.InvoiceGasView;
import com.martel.proto.xxx.repository.InvoiceCrudRepository;
import com.martel.proto.xxx.repository.InvoiceGasCrudRepository;
import com.martel.proto.xxx.repository.InvoiceGasViewRepository;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@Category(UnitTests.class)
@SpringBootTest
public class InvoiceGasViewRepositoryTest {

	@Autowired
	private InvoiceCrudRepository invoiceCrudRepository;

	@Autowired
	private InvoiceGasCrudRepository invoiceGasCrudRepository;

	@Autowired
	private InvoiceGasViewRepository invoiceGasViewRepository;

	@Test
	public void find_Success() {

		final Invoice invoice = new Invoice("invoice-gas-view-find-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGas expected = initContext(invoice, invoiceDetail);

		StepVerifier.create(invoiceGasViewRepository.findById(expected.getId()))
						.assertNext(actual -> checkEntity(actual, invoice, expected))
						.verifyComplete();

		cleanContext(expected.getInvoiceId());
	}

	@Test
	public void findAll_Success() {

		final Invoice invoice1 = new Invoice("invoice1-gas-view-findAll-UT", 1, 1, 2017, 1145.10, "\\dir1");
		final InvoiceGas invoiceDetail1 = new InvoiceGas(1, 1, 2018, 0, 31, 12, 2018, 200, 365, true, 201, 301);
		final InvoiceGas expected1 = initContext(invoice1, invoiceDetail1);

		final Invoice invoice2 = new Invoice("invoice2-gas-view-findAll-UT", 2, 2, 2018, 12.1, "\\dir2");
		final InvoiceGas invoiceDetail2 = new InvoiceGas(1, 1, 2019, 201, 31, 12, 2019, 400, 365, true, 202, 302);
		final InvoiceGas expected2 = initContext(invoice2, invoiceDetail2);

		StepVerifier.create(invoiceGasViewRepository.findAll())
						.assertNext(actual -> checkEntity(actual, invoice1, expected1))
						.assertNext(actual -> checkEntity(actual, invoice2, expected2))
						.thenCancel()
						.verify();

		cleanContext(expected1.getInvoiceId());
		cleanContext(expected2.getInvoiceId());
	}

	private InvoiceGas initContext(final Invoice invoice, final InvoiceGas invoiceDetail) {

		invoiceDetail.setInvoiceId(invoiceCrudRepository.save(invoice)
																.block()
																.getId());

		return invoiceGasCrudRepository  .save(invoiceDetail)
											.block();
	}

	private void cleanContext(UUID... ids) {
		for (UUID id : ids) {
			invoiceCrudRepository.deleteById(id)
								.block();
		}
	}

	public static void checkEntity(final InvoiceGasView view, final Invoice expectedInvoice, final InvoiceGas expectedInvoiceGas) {
		InvoiceCrudRepositoryTest.checkEntity(InvoiceGasViewConverter.getInvoice(view), expectedInvoice);
		InvoiceGasCrudRepositoryTest.checkEntity(InvoiceGasViewConverter.getInvoiceGas(view), expectedInvoiceGas);
	}
}