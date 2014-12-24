package com.ejb.libro.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ejb.libro.datamanager.DataManager;
import com.ejb.libro.entidades.Rol;
import com.ejb.libro.entidades.Usuario;
import com.ejb.libro.servicios.ServicioRol;
import com.ejb.libro.servicios.ServicioUsuario;

@ManagedBean
@ViewScoped
public class ControladorUsuario {

	@EJB
	private ServicioUsuario servicioUsuario;

	@EJB
	private ServicioRol servicioRol;

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Rol> roles;
	private int rolSeleccionado;
	
	@ManagedProperty("#{dataManager}")
	private DataManager dataManager;



	/* Constructor */
	public ControladorUsuario() {
		usuario = new Usuario();
		usuarios = new ArrayList<Usuario>();
		roles = new ArrayList<Rol>();
		
	}
	
	@PostConstruct
	public void ejecutar(){
		roles= servicioRol.recuperarTodos();
		usuarios = servicioUsuario.recuperarTodos();
	}

	/* Metodos */
	
	public void seleccionar(){
		
	}
	public String login(){
		try {
			System.out.println(usuario.getUsername()+usuario.getPassword());
			Usuario  Unuevo = servicioUsuario.login(usuario.getUsername(), usuario.getPassword());
			dataManager.setUsuario(Unuevo);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Ingreso Exitoso",
					"Login"));
			return "Menu";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Usuario o Password Incorrectos ",
							"Login"));
			return "";
		}
	}
	public void insertarUsuario(){
		
		try {
			usuario.setRol(servicioRol.buscarById(rolSeleccionado));
			servicioUsuario.insertarUsuario(usuario);
			usuario = new Usuario();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se inserto exitosamente", "Usuario"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizarUsuario(){
		
		try {
			usuario.setRol(servicioRol.buscarById(rolSeleccionado));
			servicioUsuario.actualizarUsuario(usuario);
			usuario = new Usuario();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se actualizo exitosamente", "Usuario"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarUsuario(){
		try {
			
			servicioUsuario.eliminarUsuario(usuario);
			usuario = new Usuario();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se elimino exitosamente", "Usuario"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* Getters & Setters */
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public int getRolSeleccionado() {
		return rolSeleccionado;
	}

	public void setRolSeleccionado(int rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}
	public DataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}

}
