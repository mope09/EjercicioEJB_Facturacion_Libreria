package com.ejb.libro.controladores;





import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


import org.primefaces.model.menu.DefaultMenuItem;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.ejb.libro.datamanager.DataManager;
import com.ejb.libro.entidades.Usuario;

@ManagedBean
@ViewScoped
public class ControladorMenu {

	private MenuModel model;
	
	
	@ManagedProperty("#{dataManager}")
	private DataManager dataManager;
	
	
	public ControladorMenu() {
		
		construirMenu();
		
	}
	
	public void construirMenu(){
		model = new DefaultMenuModel();
		DefaultSubMenu firstSubmenu = new DefaultSubMenu("Facturacion");
		DefaultMenuItem item = new DefaultMenuItem("Factura");
		item.setUrl("/Factura.jsf");
		firstSubmenu.addElement(item);
		DefaultMenuItem itemC = new DefaultMenuItem("Clinte");
		itemC.setUrl("/Cliente.jsf");
		firstSubmenu.addElement(itemC);
		model.addElement(firstSubmenu);
		
		
		DefaultSubMenu secondSubmenu = new DefaultSubMenu("Mantenimiento");
		DefaultMenuItem item5 = new DefaultMenuItem("Nacionalidad");
		item5.setUrl("/Nacionalidad.jsf");
		secondSubmenu.addElement(item5);
		
		DefaultMenuItem item2 = new DefaultMenuItem("Categoria");
		item2.setUrl("/Categoria.jsf");
		secondSubmenu.addElement(item2);
		DefaultMenuItem item3 = new DefaultMenuItem("Autor");
		item3.setUrl("/Autor.jsf");
		secondSubmenu.addElement(item3);
		
		DefaultMenuItem item4 = new DefaultMenuItem("Libro");
		item4.setUrl("/Libro.jsf");
		secondSubmenu.addElement(item4);
				
		model.addElement(secondSubmenu);
		
		DefaultSubMenu terceroSubmenu = new DefaultSubMenu("Usuarios");
		DefaultMenuItem item6 = new DefaultMenuItem("Usuario");
		item6.setUrl("/Usuario.jsf");
		terceroSubmenu.addElement(item6);
		
		DefaultMenuItem item7 = new DefaultMenuItem("Rol");
		item7.setUrl("/Rol.jsf");
		terceroSubmenu.addElement(item7);
		
				
		model.addElement(terceroSubmenu);
		
		DefaultSubMenu cuartoSubmenu = new DefaultSubMenu("Log Out");
		DefaultMenuItem item8 = new DefaultMenuItem("Cerrar Sesion");
		item8.setUrl(cerrarSession());
		cuartoSubmenu.addElement(item8);
		
		model.addElement(cuartoSubmenu);
		
		/*DefaultMenuItem item3 = new DefaultMenuItem("Autor");
		item3.setUrl("/Autor.jsf");
		secondSubmenu.addElement(item3);
		model.addElement(secondSubmenu);
		
		DefaultMenuItem item4 = new DefaultMenuItem("Libro");
		item4.setUrl("/Libro.jsf");
		secondSubmenu.addElement(item4);
		model.addElement(secondSubmenu);
		
		DefaultMenuItem item5 = new DefaultMenuItem("Usuario");
		item5.setUrl("/Libro.jsf");
		secondSubmenu.addElement(item5);
		model.addElement(secondSubmenu);*/
		
		
		
		
//		 model = new DefaultMenuModel();
//		 
//	        //First submenu
//	        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");
//	 
//	        DefaultMenuItem item = new DefaultMenuItem("External");
//	        item.setUrl("http://www.primefaces.org");
//	        item.setIcon("ui-icon-home");
//	        firstSubmenu.addElement(item);
//	 
//	        model.addElement(firstSubmenu);
//	 
//	        //Second submenu
//	        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");
//	 
//	        item = new DefaultMenuItem("Save");
//	        item.setIcon("ui-icon-disk");
//	        item.setCommand("#{menuBean.save}");
//	        item.setUpdate("messages");
//	        secondSubmenu.addElement(item);
//	 
//	        item = new DefaultMenuItem("Delete");
//	        item.setIcon("ui-icon-close");
//	        item.setCommand("#{menuBean.delete}");
//	        item.setAjax(false);
//	        secondSubmenu.addElement(item);
//	 
//	        item = new DefaultMenuItem("Redirect");
//	        item.setIcon("ui-icon-search");
//	        item.setCommand("#{menuBean.redirect}");
//	        secondSubmenu.addElement(item);
//	 
//	        model.addElement(secondSubmenu);
	     
	}
	
	public String cerrarSession(){
		dataManager = new DataManager();
		dataManager.setUsuario(new Usuario());
		
		return "/Login.jsf";
		
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}
	public DataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}


}

