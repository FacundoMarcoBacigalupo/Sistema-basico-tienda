public class Usuario {
	private String nombre = "";
	private String contrasenia = "";
	private double saldoDeUsuario = 0.0;

	
	//Creacion del usuario
	public Usuario(String nombre, String contrasenia, double saldoDeUsuario) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.saldoDeUsuario = saldoDeUsuario;
	}


	
	//Metodos
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	public String getContrasenia() {
		return this.contrasenia;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}




	public double getSaldoDeUsuario() {
		return saldoDeUsuario;
	}

	public void setSaldoDeUsuario(double saldoDeUsuario) {
		this.saldoDeUsuario = saldoDeUsuario;
	}
}