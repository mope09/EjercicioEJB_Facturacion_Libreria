package com.ejb.libro.controladores;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class ReporteUsuarios {

	@ManagedProperty("#{generadorReportes}")
	private GeneradorReportes generadorJasper;
	private String nombreConsulta;

	private final String NOMBRE_REPORTE_JASPER = "LibrosReporte1.jasper";//Nombre del archivo generado por ireport
	private final String NOMBRE_REPORTE = "ReporteLibro";//Nombre de output de pdf

	public String getNombreConsulta() {
		return nombreConsulta;
	}

	public void setNombreConsulta(String nombreConsulta) {
		this.nombreConsulta = nombreConsulta;
	}

	public GeneradorReportes getGeneradorJasper() {
		return generadorJasper;
	}

	public void setGeneradorJasper(GeneradorReportes generadorJasper) {
		this.generadorJasper = generadorJasper;
	}

	public void exportarPdf() {
		try {
			//Mapa para colocar todos los parámetros que espera el reporte
			Map parametros = new HashMap();
			//Setea el nombre del archivo jasper y del nombre con el cual se genera el reporte
			generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
			generadorJasper.setNombreReporte(NOMBRE_REPORTE);
			
			//Agrega los parámetros al mapa
			parametros.put("cadena", nombreConsulta + "%");
			
			//Agrega los parámetros al reporte
			generadorJasper.setParametrosReporte(parametros);
			//Genera el reporte en PDF
			generadorJasper.generarPDF();
			//OTROS FORMATOS:
			//generadorJasper.generarDOCX();
			//generadorJasper.generarPPT();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
							.getMessage(), ""));
		}
	}
}
