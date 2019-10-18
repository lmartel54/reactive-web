package com.martel.proto.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table("customer")
public class Customer {

	@Id
	public Long id;
	public String name;
	public Integer age;

	public Customer(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
}