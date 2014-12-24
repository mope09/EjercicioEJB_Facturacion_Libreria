package com.ejb.libro.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ejb.libro.entidades.Categoria;
import com.ejb.libro.servicios.ServicioCategoria;

@SessionScoped
@ManagedBean
public class ControladorCategoria {

	@EJB
	private ServicioCategoria servicioCategoria;

	private Categoria categoria;
	private List<Categoria> categorias;

	public ControladorCategoria() {
		categoria = new Categoria();
		categorias = new ArrayList<Categoria>();
	}

	@PostConstruct
	public void ejecutar() {
		categorias = servicioCategoria.recuperarTodos();

	}

	public void seleccionar() {

	}

	public void insertarCat() {

		try {
			servicioCategoria.insertarCategoria(categoria);
			categoria = new Categoria();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se inserto exitosamente", "Categoria"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void actualizarCat() {
		try {
			servicioCategoria.actualizarCategoria(categoria);
			categoria = new Categoria();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se actualizo exitosamente", "Categoria"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminarCat() {
		try {
			servicioCategoria.eliminarCategoria(categoria);
			categoria = new Categoria();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se elimino exitosamente", "Categoria"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Getters & Setters */
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
