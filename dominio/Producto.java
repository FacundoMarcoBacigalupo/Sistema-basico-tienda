public class Producto {
	private String nombre = "";
	private double precio = 0.0;
	private int stock = 0;



	//Creacion del producto
	public Producto(String nombre, double precio, int stock) {
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}



	//Metodos
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

	public double getPrecio() {
		return this.precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	

	public int getStock() {
		return this.stock;
	}
	
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	public void reducirStock(int cantidad) {
		if(cantidad <= this.stock) {
			this.stock -= cantidad;
		}
	}
	
	
	public void sumarStock(int cantidad) {
		this.stock += cantidad;
	}



	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + "]";
	}
}