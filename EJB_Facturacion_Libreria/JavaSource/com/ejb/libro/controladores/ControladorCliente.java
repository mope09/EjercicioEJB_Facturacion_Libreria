package com.ejb.libro.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ejb.libro.entidades.Cliente;
import com.ejb.libro.entidades.Nacionalidad;
import com.ejb.libro.servicios.ServicioCliente;
import com.ejb.libro.servicios.ServicioNacionalidad;

@ManagedBean
@SessionScoped
public class ControladorCliente {

	@EJB
	private ServicioCliente servicioCliente;

	@EJB
	private ServicioNacionalidad servicioNacionalidad;

	private Cliente cliente;
	private List<Cliente> clientes;

	private List<Nacionalidad> nacionalidades;
	private int nacionalidadSeleccionada;

	/* Constructor */

	public ControladorCliente() {
		cliente = new Cliente();
		clientes = new ArrayList<Cliente>();
		nacionalidades = new ArrayList<Nacionalidad>();
	}

	@PostConstruct
	public void ejecutar() {

		clientes = servicioCliente.recuperarTodos();
		nacionalidades = servicioNacionalidad.recuperarTodos();

	}

	/* MEtodos */

	public void insertarCliente() {

		try {
			System.out.println("LLEGA");
			cliente.setNacionalidad(servicioNacionalidad
					.buscarById(nacionalidadSeleccionada));
			servicioCliente.insertarCliente(cliente);
			cliente = new Cliente();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se inserto exitosamente", "Cliente"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizarCliente() {
		try {
			cliente.setNacionalidad(servicioNacionalidad
					.buscarById(nacionalidadSeleccionada));
			servicioCliente.actualizarCliente(cliente);
			cliente = new Cliente();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se actualizo exitosamente", "Cliente"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarCliente(){
		try {
			
			servicioCliente.eliminarCliente(cliente);
			cliente= new Cliente();
			ejecutar();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Se elimino exitosamente", "Cliente"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void seleccionar(){
		
	}
	/* Getters & Setters */

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
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
