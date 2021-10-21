package entidades;

import java.util.Date;

public class RolOpciones 
{
	//Atributos
	private int id_rol_opciones;
	private Date fecha_creacion;
    private int estado;
    private int idOpcion;
	private Date fecha_edicion;
	private Date fecha_eliminacion;
    private int idRol;
	
	//Métodos
	public int getId_rol_opciones() {
		return id_rol_opciones;
	}
	public void setId_rol_opciones(int id_rol_opciones) {
		this.id_rol_opciones = id_rol_opciones;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(int idOpcion) {
		this.idOpcion = idOpcion;
	}
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public Date getFecha_edicion() {
		return fecha_edicion;
	}
	public void setFecha_edicion(Date fecha_edicion) {
		this.fecha_edicion = fecha_edicion;
	}
	public Date getFecha_eliminacion() {
		return fecha_eliminacion;
	}
	public void setFecha_eliminacion(Date fecha_eliminacion) {
		this.fecha_eliminacion = fecha_eliminacion;
	}
	
}
