package com.ejb.libro.servicios;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.libro.entidades.Libro;

@Stateless
public class ServicioLibro {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertarLibro(Libro libro){
		em.persist(libro);
	}
	public void actualizarLibro(Libro libro){
		em.merge(libro);
	}
	public void eliminarLibro(Libro libro){
		em.remove(em.merge(libro));
	}
	public List<Libro> recuperarTodos(){
		Query q1 = em.createQuery("select l from Libro l");
		List libros =q1.getResultList(); 		
		return libros;
	}
	
	public Libro buscarById(int id){
		Query q1 = em.createQuery("select l from Libro l where l.id=:id");
		q1.setParameter("id", id);
		Libro libro =(Libro) q1.getSingleResult(); 		
		return libro;
	}

}
