package com.IJMpiloto.model;

import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.mysql.jdbc.Blob;

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
	
}
