package com.ejb.libro.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ejb.libro.entidades.Cliente;
import com.ejb.libro.entidades.DetalleFactura;
import com.ejb.libro.entidades.Factura;
import com.ejb.libro.entidades.Libro;
import com.ejb.libro.servicios.ServicioCliente;
import com.ejb.libro.servicios.ServicioDetalleFactura;
import com.ejb.libro.servicios.ServicioFactura;
import com.ejb.libro.servicios.ServicioLibro;

@ManagedBean
@ViewScoped
public class ControladorFactura {

	@EJB
	private ServicioFactura servicioFactura;

	@EJB
	private ServicioCliente servicioCliente;

	@EJB
	private ServicioLibro servicioLibro;

	@EJB
	private ServicioDetalleFactura servicioDetalleFactura;

	private Factura factura;
	private List<Factura> facturas;
	private List<Cliente> clientes;
	private List<DetalleFactura> detalleFacturas;
	private List<DetalleFactura> detalleFacturasNuevas;

	private String clienteSeleccionado;
	private int clienteSeleccionado2;

	private int libroSeleccionado;

	private DetalleFactura detalleFactura;

	private Cliente clienteEncontrado;
	private Libro libroEncontrado;

	/* Calculos */

	private double subtotalPorItem;
	private double iva;
	private double subtotal;
	private double total;

	/* Constructor */

	public ControladorFactura() {
		factura = new Factura();
		facturas = new ArrayList<Factura>();
		clientes = new ArrayList<Cliente>();
		detalleFactura = new DetalleFactura();
		detalleFacturasNuevas = new ArrayList<DetalleFactura>();
		detalleFacturas = new ArrayList<DetalleFactura>();
		libroEncontrado = new Libro();
		clienteEncontrado = new Cliente();

	}

	@PostConstruct
	public void ejecutar() {

	}

	/* Metodos */

	public void agregarDetalle() {

		/* Factura */
		factura.setCliente(servicioCliente.buscarByCedula(clienteSeleccionado));


		/* Detalle Factura */

		detalleFactura.setLibro(servicioLibro.buscarById(libroSeleccionado));
		detalleFactura.setFactura(factura);
		detalleFactura.setSubtotal(detalleFactura.getCantidad()
				* libroEncontrado.getPrecio());

		System.out.println("DETALLE" + detalleFactura.getId()
				+ detalleFactura.getFactura().getId());
		detalleFacturas.add(detalleFactura);		
		
		libroEncontrado = new Libro();
		detalleFactura = new DetalleFactura();
		subtotal = 0;
		for (DetalleFactura df : detalleFacturas) {
			subtotal = df.getSubtotal() + subtotal;
		}

		iva = subtotal * 0.12;
		total = subtotal + iva;
		// factura= new Factura();
	}

	public void grabarFactura() {

		try {

			if (libroSeleccionado == 0  ) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Ingrese el ID de Libro ",
								"Libro"));
			}
			
			else
			{
				
			
			servicioFactura.insertarFactura(factura);
			for (DetalleFactura df : detalleFacturas) {
				servicioDetalleFactura.insertarDetalleFactura(df);
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Se grabo exitosamente",
					"Factura"));

			factura = new Factura();
			libroEncontrado = new Libro();
			clienteEncontrado = new Cliente();
			detalleFactura = new DetalleFactura();
			detalleFacturas = new ArrayList<DetalleFactura>();
			libroSeleccionado = 0;
			clienteSeleccionado = "";
			subtotal = 0.0;
			iva = 0.0;
			total = 0.0;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void eliminarDetalle() {

		detalleFacturas.remove(detalleFactura);
		libroEncontrado = new Libro();
		detalleFactura = new DetalleFactura();
		libroSeleccionado = 0;
		clienteSeleccionado = "";
		subtotal = 0.0;
		iva = 0.0;
		total = 0.0;

	}

	public void buscarClienteById() {
		System.out.println("CLIEBTE" + clienteSeleccionado);
		clienteEncontrado = servicioCliente.buscarByCedula(clienteSeleccionado);

	}

	public void buscarLibroById() {
		System.out.println("CLIEBTE" + libroSeleccionado);
		libroEncontrado = servicioLibro.buscarById(libroSeleccionado);
	}

	public void seleccionar() {

	}

	/* Getters & Setters */

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(String clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public Cliente getClienteEncontrado() {
		return clienteEncontrado;
	}

	public void setClienteEncontrado(Cliente clienteEncontrado) {
		this.clienteEncontrado = clienteEncontrado;
	}

	public int getLibroSeleccionado() {
		return libroSeleccionado;
	}

	public void setLibroSeleccionado(int libroSeleccionado) {
		this.libroSeleccionado = libroSeleccionado;
	}

	public DetalleFactura getDetalleFactura() {
		return detalleFactura;
	}

	public void setDetalleFactura(DetalleFactura detalleFactura) {
		this.detalleFactura = detalleFactura;
	}

	public Libro getLibroEncontrado() {
		return libroEncontrado;
	}

	public void setLibroEncontrado(Libro libroEncontrado) {
		this.libroEncontrado = libroEncontrado;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

	public double getSubtotalPorItem() {
		return subtotalPorItem;
	}

	public void setSubtotalPorItem(double subtotalPorItem) {
		this.subtotalPorItem = subtotalPorItem;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public int getClienteSeleccionado2() {
		return clienteSeleccionado2;
	}

	public void setClienteSeleccionado2(int clienteSeleccionado2) {
		this.clienteSeleccionado2 = clienteSeleccionado2;
	}

	public List<DetalleFactura> getDetalleFacturasNuevas() {
		return detalleFacturasNuevas;
	}

	public void setDetalleFacturasNuevas(
			List<DetalleFactura> detalleFacturasNuevas) {
		this.detalleFacturasNuevas = detalleFacturasNuevas;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
