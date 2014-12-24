package com.ejb.libro.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.libro.entidades.Categoria;

@Stateless
public class ServicioCategoria {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertarCategoria(Categoria categoria){
		em.persist(categoria);
		
	}
	public void actualizarCategoria(Categoria categoria){
		em.merge(categoria);
		
	}
	public void eliminarCategoria(Categoria categoria){
		em.remove(em.merge(categoria));
		
	}
	public Categoria  buscarById(int id){
		Query q1 = em.createQuery("select c from Categoria c where c.id=:id");
		q1.setParameter("id", id);
		Categoria categoria = (Categoria) q1.getSingleResult();
		return categoria;
	}
	
	public List<Categoria> recuperarTodos(){
		Query q1 = em.createQuery("select c from Categoria c ");		
		List categorias = q1.getResultList();
		return categorias;
	}

}
