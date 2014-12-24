package com.ejb.libro.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.libro.entidades.Nacionalidad;

@Stateless
public class ServicioNacionalidad {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertarNacionalidad(Nacionalidad nacionalidad){
		em.persist(nacionalidad);
	}

	public void actualizarNacionalidad(Nacionalidad nacionalidad){
		em.merge(nacionalidad);
	}
	public void eliminarrNacionalidad(Nacionalidad nacionalidad){
		em.remove(em.merge(nacionalidad));
	}
	public List<Nacionalidad> recuperarTodos(){
		Query q1=em.createQuery("select n from Nacionalidad n");
		List nacionalidades = q1.getResultList();
		return nacionalidades;
	}
	
	public Nacionalidad buscarById(int id){
		System.out.println(id);
		Query q1=em.createQuery("select n from Nacionalidad n where n.id=:id");
		q1.setParameter("id", id);
		Nacionalidad nacionalidad = (Nacionalidad) q1.getSingleResult();
		return nacionalidad;
	}
}
