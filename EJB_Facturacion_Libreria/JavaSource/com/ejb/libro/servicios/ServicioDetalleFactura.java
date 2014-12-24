package com.ejb.libro.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.libro.entidades.DetalleFactura;
import com.ejb.libro.entidades.Factura;


@Stateless
public class ServicioDetalleFactura {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertarDetalleFactura(DetalleFactura detalleFactura){
		em.persist(detalleFactura);
	}
	public void actualizarDetalleFactura(DetalleFactura detalleFactura){
		em.merge(detalleFactura);
	}
	public void eliminarDetalleFactura(DetalleFactura detalleFactura){
		em.remove(em.merge(detalleFactura));
	}
	public DetalleFactura  buscarById(int id){
		Query q1 = em.createQuery("select d from DetalleFactura d where d.id=:id");
		q1.setParameter("id", id);
		DetalleFactura detalleFactura = (DetalleFactura) q1.getSingleResult();
		return detalleFactura;
	}
	
	public List<DetalleFactura> recuperarTodos(){
		Query q1 = em.createQuery("select d from DetalleFactura d ");		
		List detalleFacturas = q1.getResultList();
		return detalleFacturas;
	}
	
	public List<DetalleFactura> buscarByIdFactura(Factura id){
		
		Query q1= em.createQuery("select d from DetalleFactura d where d.factura=:id");
		q1.setParameter("id", id);
		List detalles= q1.getResultList();
		return detalles;
	}
}
