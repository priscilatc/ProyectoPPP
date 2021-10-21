package entidades;

import java.sql.Date;
public class PPP {
	
	//Atributos
	private int idPPP;
	private String area_laboral;
	private String campoprofesional;
	private String cargo;
	private String dias_laborales;
	private String fecha_inicio;
	private String fecha_fin;
	private String funciones;
	private String horario;
	private String comentario;
	private Date fecha_creacion;
	private Date fecha_edicion;
	private Date fecha_eliminacion;
	private int estado;
	private int idEstudiante;
	private int idOrg;
	
	//Metodos
	public int getIdPPP() {
		return idPPP;
	}
	public void setIdPPP(int idPPP) {
		this.idPPP = idPPP;
	}
	public String getArea_laboral() {
		return area_laboral;
	}
	public void setArea_laboral(String area_laboral) {
		this.area_laboral = area_laboral;
	}
	public String getCampoprofesional() {
		return campoprofesional;
	}
	public void setCampoprofesional(String campoprofesional) {
		this.campoprofesional = campoprofesional;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getDias_laborales() {
		return dias_laborales;
	}
	public void setDias_laborales(String dias_laborales) {
		this.dias_laborales = dias_laborales;
	}
	public String getFunciones() {
		return funciones;
	}
	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getIdEstudiante() {
		return idEstudiante;
	}
	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}
	public int getIdOrg() {
		return idOrg;
	}
	public void setIdOrg(int idOrg) {
		this.idOrg = idOrg;
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
