package com.IJMpiloto.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "\"supplier\"")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	@JsonIgnore
	private long id;

	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "[vVjJeE][0-9]*")
	@Column(name = "Code", nullable = false)
	private String code;

	@NotNull
	@Size(min = 5, max = 50)
	@Column(name = "Name", nullable = false)
	private String name;
	@JsonIgnore
	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "supplier", cascade = CascadeType.ALL)
	private Set<Product> products;

	@JsonProperty
	public long getId() {
		return id;
	}
	@JsonIgnore
	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}
	@JsonIgnore
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}