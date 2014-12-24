package com.ejb.libro.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ejb.libro.entidades.Autor;
import com.ejb.libro.entidades.Nacionalidad;
import com.ejb.libro.servicios.ServicioAutor;
import com.ejb.libro.servicios.ServicioNacionalidad;

@ManagedBean
@SessionScoped
public class ControladorAutor {

	@EJB
	private ServicioAutor servicioAutor;
	@EJB
	private ServicioNacionalidad servicioNacionalidad;

	private Autor autor;
	private List<Autor> autores;
	private List<Nacionalidad> nacionalidades;

	private int nacionalidadSeleccionada;

	/* COnstructor */
	public ControladorAutor() {
		autor = new Autor();
		autores = new ArrayList<Autor>();
		nacionalidades = new ArrayList<Nacionalidad>();
	}

	@PostConstruct
	public void ejecutar() {
		autores = servicioAutor.recuperarTodos();
		nacionalidades = servicioNacionalidad.recuperarTodos();
	}

	/* Metodos */
	public void insertarAutor() {

		try {
			autor.setNacionalidad(servicioNacionalidad
					.buscarById(nacionalidadSeleccionada));
			servicioAutor.insertarAutor(autor);
			autor = new Autor();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se inserto exitosamente", "Autor"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizarAutor() {
		try {
			autor.setNacionalidad(servicioNacionalidad
					.buscarById(nacionalidadSeleccionada));
			servicioAutor.actualizarAutor(autor);
			autor = new Autor();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se actualizo exitosamente", "Autor"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void eliminarAutor() {
		try {

			servicioAutor.eliminarAutor(autor);
			autor = new Autor();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se elimino exitosamente", "Autor"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void seleccionar() {

	}

	/* Getters & Setters */
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Nacionalidad> getNacionalidades() {
		return nacionalidades;
	}

	public void setNacionalidades(List<Nacionalidad> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}

	public int getNacionalidadSeleccionada() {
		return nacionalidadSeleccionada;
	}

	public void setNacionalidadSeleccionada(int nacionalidadSeleccionada) {
		this.nacionalidadSeleccionada = nacionalidadSeleccionada;
	}

}
