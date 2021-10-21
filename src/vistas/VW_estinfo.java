package vistas;

public class VW_estinfo {

	public String nombre;
	public String correo;
	public String celular;
	public String condicion;
	public String sexo;
	public String coordinacion;
	public String usuario;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCoordinacion() {
		return coordinacion;
	}
	public void setCoordinacion(String coordinacion) {
		this.coordinacion = coordinacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public VW_estinfo(String nombre, String correo, String celular,
			String condicion, String sexo, String coordinacion, String usuario) {
		super();
		this.nombre=nombre;
		this.correo=correo;
		this.celular=celular;
		this.condicion=condicion;
		this.sexo=sexo;
		this.coordinacion=coordinacion;
		this.usuario=usuario;
	}
}
