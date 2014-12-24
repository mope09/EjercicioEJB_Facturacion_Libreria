package com.ejb.libro.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.libro.entidades.Autor;

@Stateless
public class ServicioAutor {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertarAutor (Autor autor){
		em.persist(autor);
	}
	
	public void actualizarAutor (Autor autor){
		em.merge(autor);
	}
	
	public void eliminarAutor (Autor autor){
		em.remove(em.merge(autor));
	}
	public List<Autor> recuperarTodos(){
		Query q1 = em.createQuery("select a from Autor a");
		List autores = q1.getResultList();
		return autores;
	}
	
	public Autor  buscarById(int id){
		Query q1 = em.createQuery("select a from Autor a where a.id=:id");
		q1.setParameter("id", id);
		Autor autore = (Autor) q1.getSingleResult();
		return autore;
	}
	
	

}
