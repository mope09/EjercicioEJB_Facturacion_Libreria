package com.ejb.libro.datamanager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ejb.libro.entidades.Usuario;

@SessionScoped
@ManagedBean
public class DataManager {

	private Usuario usuario;

	public DataManager() {
		// TODO Auto-generated constructor stub
		usuario = new Usuario();
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
