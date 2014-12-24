package com.ejb.libro.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ejb.libro.entidades.Rol;
import com.ejb.libro.servicios.ServicioRol;

@ManagedBean
@SessionScoped
public class ControladorRol {

	@EJB
	private ServicioRol servicioRol;

	private Rol rol;
	private List<Rol> roles;

	/* Constructor */

	public ControladorRol() {
		rol = new Rol();
		roles = new ArrayList<Rol>();
	}

	@PostConstruct
	public void ejecutar(){
		
		roles = servicioRol.recuperarTodos();
		
	}
	/* Metodos */

	public void insertarRol(){
		try {
			servicioRol.insertarRol(rol);
			rol= new Rol();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se insertar exitosamente", "Rol"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarRol(){
		try {
			servicioRol.actualizarRol(rol);
			rol= new Rol();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se actualizo exitosamente", "Rol"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarRol(){
		try {
			servicioRol.eliminarRol(rol);
			rol= new Rol();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se elimino exitosamente", "Rol"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void seleccionar(){
		
	}
	/* Getters & Setters */

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

}
