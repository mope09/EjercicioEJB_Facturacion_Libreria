package com.ejb.libro.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="detalle_factura")
public class DetalleFactura {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="df_id")
	private int id;
	
	@Column(name="df_cantidad")
	private int cantidad;
	
	@JoinColumn (name="df_id_factura")
	@ManyToOne
	private Factura factura;
	
	@JoinColumn(name="df_id_libro")
	@ManyToOne
	private Libro libro;
	
	@Transient
	private double subtotal;

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	
}
