package entidades;


public class Evaluacion {

	private int idEvaluacion;
	private int idNivel;
	private int idPeriodoPPP;
	private String url;
	private String plantilla;
	private int activo;
	private int estado;
	
	public int getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(int idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public int getIdNivel() {
		return idNivel;
	}
	public void setIdNivel(int idNivel) {
		this.idNivel = idNivel;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getIdPeriodoPPP() {
		return idPeriodoPPP;
	}
	public void setIdPeriodoPPP(int idPeriodoPPP) {
		this.idPeriodoPPP = idPeriodoPPP;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPlantilla() {
		return plantilla;
	}
	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	
}
