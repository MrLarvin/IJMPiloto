package com.IJMpiloto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "\"productimage\"")
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private long id;
	@Lob
	@Column(name = "image", nullable = false)
	private byte[] picture;
	
	@Column(name = "checksum",nullable = false)
	private String checksum;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public byte[] getPicture() {
		return this.picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	} 
}
