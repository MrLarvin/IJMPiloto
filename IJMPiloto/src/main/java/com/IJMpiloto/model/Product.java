package com.IJMpiloto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.IJMpiloto.model.Supplier;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long id;

	@NotNull
	@Size(min = 3, max = 10)
	@Column(name = "Code", nullable = false)
	private String code;

	@NotNull
	@Size(min = 5, max = 50)
	@Column(name = "Description", nullable = false)
	private String description;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Supplier_Id")
	private Supplier supplier;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier2) {
		this.supplier = supplier2;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}