package com.ejb.libro.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.ejb.libro.entidades.Factura;

@Stateless
public class ServicioFactura {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertarFactura(Factura factura)
	{
		em.persist(factura);
	}
	
	public void actualizarFactura(Factura factura)
	{
		em.merge(factura);
	}
	
	public void eliminarFactura(Factura factura)
	{
		em.remove(em.merge(factura));
	}
	
	public Factura  buscarById(int id){
		Query q1 = em.createQuery("select f from Factura f where f.id=:id");
		q1.setParameter("id", id);
		Factura factura = (Factura) q1.getSingleResult();
		return factura;
	}
	
	public List<Factura> recuperarTodos(){
		Query q1 = em.createQuery("select f from Factura f ");		
		List facturas = q1.getResultList();
		return facturas;
	}

}
