package entidades;

import java.sql.Date;

public class PeriodoPPP {
	
	private int idPeriodoPPP;
	private String descripcion;
	private String fecha_inicio;
	private String fecha_fin;
	private Date fecha_creacion;
	private Date fecha_edicion;
	private Date fecha_eliminacion;
	private int estado;
	
	public int getIdPeriodoPPP() {
		return idPeriodoPPP;
	}
	public void setIdPeriodoPPP(int idPeriodoPPP) {
		this.idPeriodoPPP = idPeriodoPPP;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
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
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
}
