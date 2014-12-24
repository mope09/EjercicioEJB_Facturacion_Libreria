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
import com.ejb.libro.entidades.Categoria;
import com.ejb.libro.entidades.Libro;
import com.ejb.libro.servicios.ServicioAutor;
import com.ejb.libro.servicios.ServicioCategoria;
import com.ejb.libro.servicios.ServicioLibro;

@ManagedBean
@SessionScoped
public class ControladorLibro {
	
	@EJB
	private ServicioCategoria servicioCategoria;
	
	@EJB
	private ServicioAutor servicioAutor;
	
	@EJB
	private ServicioLibro servicioLibro;
	
	private Libro libro;
	private List<Libro> libros;
	private List<Autor> autores;
	private List<Categoria> categorias;
	private int autorSeleccionado;
	private int categoriaSeleccionado;
	
	
	

	
	public ControladorLibro() {
		libro = new Libro();
		libros = new ArrayList<Libro>();
		categorias = new ArrayList<Categoria>();
	}
	
	@PostConstruct
	public void ejecutar(){
		libros = servicioLibro.recuperarTodos();
		autores = servicioAutor.recuperarTodos();
		categorias = servicioCategoria.recuperarTodos();
	}
	
	public void seleccionarlibro(){
		
	}
	
	public void insertarLibro(){
		
		try {
			libro.setAutor(servicioAutor.buscarById(autorSeleccionado));
			libro.setCategoria(servicioCategoria.buscarById(categoriaSeleccionado));
			servicioLibro.insertarLibro(libro);
			libro = new Libro();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();  	          
		    context.addMessage(null, new FacesMessage("Se inserto exitosamente", "Libro"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void actualizarLibro(){
		try {
			libro.setAutor(servicioAutor.buscarById(autorSeleccionado));
			libro.setCategoria(servicioCategoria.buscarById(categoriaSeleccionado));
			servicioLibro.actualizarLibro(libro);
			libro = new Libro();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();  	          
		    context.addMessage(null, new FacesMessage("Se actualizo exitosamente", "Libro"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void eliminarLibro(){
		try {
			servicioLibro.eliminarLibro(libro);
			libro = new Libro();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();  	          
		    context.addMessage(null, new FacesMessage("Se elimino exitosamente", "Libro"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*Getters & Setters*/
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	public List<Libro> getLibros() {
		return libros;
	}
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	public int getAutorSeleccionado() {
		return autorSeleccionado;
	}

	public void setAutorSeleccionado(int autorSeleccionado) {
		this.autorSeleccionado = autorSeleccionado;
	}

	public int getCategoriaSeleccionado() {
		return categoriaSeleccionado;
	}

	public void setCategoriaSeleccionado(int categoriaSeleccionado) {
		this.categoriaSeleccionado = categoriaSeleccionado;
	}
	
	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	

}
