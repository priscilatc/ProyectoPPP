package entidades;

import java.sql.Date;

public class Docente {
	
	//Atributos
		private int idDocente;
		private String nombres;
		private String apellidos;
		private String direccion;
		private String celular;
		private String ciudad;
		private String cargo;
		private String trato;
		private String categoria;
		private String correo;
		private String carneUca;
		private String cedula;
		private int idCoordinacion;
		private int idUsuario;
		private Date fecha_creacion;
		private Date fecha_edicion;
		private Date fecha_eliminacion;
		private int estado;
		
		public int getIdDocente() {
			return idDocente;
		}
		public void setIdDocente(int idDocente) {
			this.idDocente = idDocente;
		}
		public String getNombres() {
			return nombres;
		}
		public void setNombres(String nombres) {
			this.nombres = nombres;
		}
		public String getApellidos() {
			return apellidos;
		}
		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}
		public String getDireccion() {
			return direccion;
		}
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		public String getCelular() {
			return celular;
		}
		public void setCelular(String celular) {
			this.celular = celular;
		}
		public String getCiudad() {
			return ciudad;
		}
		public void setCiudad(String ciudad) {
			this.ciudad = ciudad;
		}
		public String getCargo() {
			return cargo;
		}
		public void setCargo(String cargo) {
			this.cargo = cargo;
		}
		public String getTrato() {
			return trato;
		}
		public void setTrato(String trato) {
			this.trato = trato;
		}
		public String getCategoria() {
			return categoria;
		}
		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}
		public String getCorreo() {
			return correo;
		}
		public void setCorreo(String correo) {
			this.correo = correo;
		}
		public int getIdCoordinacion() {
			return idCoordinacion;
		}
		public void setIdCoordinacion(int idCoordinacion) {
			this.idCoordinacion = idCoordinacion;
		}
		public int getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(int idUsuario) {
			this.idUsuario = idUsuario;
		}
		public int getEstado() {
			return estado;
		}
		public void setEstado(int estado) {
			this.estado = estado;
		}
		public String getCarneUca() {
			return carneUca;
		}
		public void setCarneUca(String carneUca) {
			this.carneUca = carneUca;
		}
		public String getCedula() {
			return cedula;
		}
		public void setCedula(String cedula) {
			this.cedula = cedula;
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
		
}
