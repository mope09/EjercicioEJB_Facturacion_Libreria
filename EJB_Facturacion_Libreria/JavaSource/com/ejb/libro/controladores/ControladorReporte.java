package com.ejb.libro.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.ejb.libro.entidades.Libro;
import com.ejb.libro.servicios.ServicioLibro;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

@ManagedBean
public class ControladorReporte {

	@EJB
	private ServicioLibro servicioLibro;

	private List<Libro> libros;

	public ControladorReporte() {
		libros = new ArrayList<Libro>();
	}

	@PostConstruct
	public void ejecutar() {
		libros = servicioLibro.recuperarTodos();

	}

	/* Metodos */

	public void preProcessPDF(Object document) throws IOException,
			BadElementException, DocumentException {
		// Cast del parametro que recibe a la clase Document de
		// com.lowagie.text.Document;
		Document pdf = (Document) document;
		// Es necesario abrir el pdf.
		pdf.open();
		// Creamos una tabla con 3 columnas
		PdfPTable tabla = new PdfPTable(3);

		// Se crea una celda que va a contener una imagen
		PdfPCell celda = new PdfPCell();
		// Elegimos el numero de filas que va a ocupar esta celda
		celda.setRowspan(4);
		// Agregamos la celda a la tabla creada anteriormente.
		tabla.addCell(celda);

		// Se crea una celda que contiene texto en su interior.
		 celda = new PdfPCell(new Paragraph("Reporte por Proceso" + "\n"
				+ "" + "\n" + "Período: "
				+ "" + " a " + ""));
		celda.setRowspan(4);
		tabla.addCell(celda);

		celda = new PdfPCell(new Paragraph("HZ-R-RP-01"));
		celda.setRowspan(1);
		tabla.addCell(celda);

		celda = new PdfPCell(new Paragraph("Rev. 01"));
		celda.setRowspan(1);
		tabla.addCell(celda);

		celda = new PdfPCell(new Paragraph("Fecha de revisión: "
				+ ""));
		celda.setRowspan(1);
		tabla.addCell(celda);

		celda = new PdfPCell(new Paragraph("Responsable: "
				+ ""));
		celda.setRowspan(1);
		tabla.addCell(celda);
		// Agregamos la tabla al pdf
		pdf.add(tabla);
		// Agregamos unos saltos de pagina al pdf
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
	}

	/* Getters & Setters */
	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

}
