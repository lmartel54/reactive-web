package com.martel.proto.xxx.entity.view;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.martel.proto.xxx.entity.Invoice;
import com.martel.proto.xxx.entity.InvoiceGas;

import lombok.Data;

@Data
@Table("admin.invoice_gas_view")
public class InvoiceGasView {

	/**
	 * {@link Invoice}
	 */
	
	@Column("ivx_reference")
	private String reference;

	@Column("ivx_day")
	private int day;

	@Column("ivx_month")
	private int month;

	@Column("ivx_year")
	private int year;

	@Column("ivx_amount")
	private double amount;

	@Column("ivx_file")
	private String file;

	/**
	 * {@link InvoiceGas}
	 */
	
	@Id
	@Column("ivx_gas_id")
	private UUID id;

	@Column("ivx_id_fk")
	private UUID invoiceId;

	@Column("ivx_gas_start_period_day")
	private int startPeriodDay;

	@Column("ivx_gas_start_period_month")
	private int startPeriodMonth;

	@Column("ivx_gas_start_period_year")
	private int startPeriodYear;

	@Column("ivx_gas_start_period_index")
	private int startPeriodIndex;

	@Column("ivx_gas_end_period_day")
	private int endPeriodDay;

	@Column("ivx_gas_end_period_month")
	private int endPeriodMonth;

	@Column("ivx_gas_end_period_year")
	private int endPeriodYear;

	@Column("ivx_gas_end_period_index")
	private int endPeriodIndex;

	@Column("ivx_gas_period_duration")
	private int periodDuration;

	@Column("ivx_gas_auto_report")
	private boolean autoReport;

	@Column("ivx_gas_volume")
	private int volume;

	@Column("ivx_gas_consumption")
	private int consumption;
}