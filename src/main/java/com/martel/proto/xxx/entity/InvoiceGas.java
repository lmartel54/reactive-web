package com.martel.proto.xxx.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("admin.invoice_gas")
public class InvoiceGas {

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

	public InvoiceGas(int startPeriodDay, int startPeriodMonth, int startPeriodYear, int startPeriodIndex, int endPeriodDay, int endPeriodMonth, int endPeriodYear, int endPeriodIndex,
	int periodDuration, boolean autoReport, int volume, int consumption) {
		super();
		this.startPeriodDay = startPeriodDay;
		this.startPeriodMonth = startPeriodMonth;
		this.startPeriodYear = startPeriodYear;
		this.startPeriodIndex = startPeriodIndex;
		this.endPeriodDay = endPeriodDay;
		this.endPeriodMonth = endPeriodMonth;
		this.endPeriodYear = endPeriodYear;
		this.endPeriodIndex = endPeriodIndex;
		this.periodDuration = periodDuration;
		this.autoReport = autoReport;
		this.volume = volume;
		this.consumption = consumption;
	}
}