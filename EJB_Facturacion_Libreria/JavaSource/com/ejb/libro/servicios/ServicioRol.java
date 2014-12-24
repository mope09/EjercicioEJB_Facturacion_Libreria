package com.ejb.libro.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.ejb.libro.entidades.Rol;

@Stateless
public class ServicioRol {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertarRol(Rol rol){
		em.persist(rol);
	}
	
	public void actualizarRol(Rol rol){
		em.merge(rol);
	}
	
	public void eliminarRol(Rol rol){
		em.remove(em.merge(rol));
	}
	
	public Rol  buscarById(int id){
		Query q1 = em.createQuery("select r from Rol r where r.id=:id");
		q1.setParameter("id", id);
		Rol rol = (Rol) q1.getSingleResult();
		return rol;
	}
	
	public List<Rol> recuperarTodos(){
		Query q1 = em.createQuery("select r from Rol r ");		
		List roles = q1.getResultList();
		return roles;
	}
	

}
