package com.ejb.libro.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ejb.libro.entidades.Nacionalidad;
import com.ejb.libro.servicios.ServicioNacionalidad;

@ManagedBean
@SessionScoped
public class ControladorNacionalidad {

	@EJB
	private ServicioNacionalidad servicioNacionalidad;

	private Nacionalidad nacionalidad;
	private List<Nacionalidad> nacionalidades;

	/* Constructor */

	public ControladorNacionalidad() {
		nacionalidad = new Nacionalidad();
		nacionalidades = new ArrayList<Nacionalidad>();
	}

	@PostConstruct
	public void ejercutar() {

		nacionalidades = servicioNacionalidad.recuperarTodos();

	}

	/* Metodos */

	public void insertarNacionalidad() {
		try {
			servicioNacionalidad.insertarNacionalidad(nacionalidad);
			nacionalidad = new Nacionalidad();
			ejercutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se inserto exitosamente", "Nacionalidad"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarNacionalidad(){
		try {
			servicioNacionalidad.actualizarNacionalidad(nacionalidad);
			nacionalidad = new Nacionalidad();
			ejercutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se actualizo exitosamente", "NAcionalidad"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarNacionalidad(){
		try {
			servicioNacionalidad.eliminarrNacionalidad(nacionalidad);
			nacionalidad = new Nacionalidad();
			ejercutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se elimino exitosamente", "Nacionalidad"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void seleccionarNac(){
		
	}

	/* Getters & Setters */
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<Nacionalidad> getNacionalidades() {
		return nacionalidades;
	}

	public void setNacionalidades(List<Nacionalidad> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}

}
