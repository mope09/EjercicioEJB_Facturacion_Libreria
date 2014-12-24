package com.ejb.libro.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.libro.entidades.Cliente;

@Stateless
public class ServicioCliente {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertarCliente(Cliente cliente){
		em.persist(cliente);
	}
	public void actualizarCliente(Cliente cliente){
		em.merge(cliente);
	}
	public void eliminarCliente(Cliente cliente){
		em.remove(em.merge(cliente));
	}
	
	public List<Cliente> recuperarTodos(){
		
		Query q1 = em.createQuery("select c from Cliente c");
		List clientes = q1.getResultList();
		return clientes;
	}
	
	public Cliente buscarByIdCliente(int id){
		
		Query q1 = em.createQuery("select c from Cliente c where c.id=:id");
		q1.setParameter("id", id);
		Cliente cliente = (Cliente) q1.getSingleResult();
		return cliente;
	}
	
	public Cliente buscarByCedula(String clienteSeleccionado){
		Query q2 =em.createQuery("select c from Cliente c where c.cedula=:cedula");
		q2.setParameter("cedula", clienteSeleccionado);
		Cliente cliente=(Cliente) q2.getSingleResult();
		return cliente;
	}

}
