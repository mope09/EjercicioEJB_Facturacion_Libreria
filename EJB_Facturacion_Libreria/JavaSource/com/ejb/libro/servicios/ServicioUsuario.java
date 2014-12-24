package com.ejb.libro.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.libro.entidades.Rol;
import com.ejb.libro.entidades.Usuario;

@Stateless
public class ServicioUsuario {

	@PersistenceContext
	private EntityManager em;

	public void insertarUsuario(Usuario usuario) {

		em.persist(usuario);

	}

	public void actualizarUsuario(Usuario usuario) {

		em.merge(usuario);

	}

	public void eliminarUsuario(Usuario usuario) {

		em.remove(em.merge(usuario));

	}
	
	public Usuario  buscarById(int id){
		Query q1 = em.createQuery("select u from Usuario u where u.id=:id");
		q1.setParameter("id", id);
		Usuario usuario = (Usuario) q1.getSingleResult();
		return usuario;
	}
	
	public List<Usuario> recuperarTodos(){
		Query q1 = em.createQuery("select u from Usuario u ");		
		List usuarios = q1.getResultList();
		return usuarios;
	}
	public Usuario login(String username, String password){
		
		Query q1=em.createQuery("select u from Usuario u where u.username=:username and u.password=:password");
		q1.setParameter("username", username);
		q1.setParameter("password", password);
		Usuario usuario = (Usuario) q1.getSingleResult();
		return usuario;
	}

}
