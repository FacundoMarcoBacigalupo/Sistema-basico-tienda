public class Ecommerce {
	private String nombreDelEcommerce;
	private Producto[] productos;
	private Usuario[] usuarios;
	private Producto[] carrito;
	private Producto[] historialDeCompras;
	private int indiceHistorial = 0;
	private Usuario usuarioActual = null;



	//Inicializacion del Ecommerce
	public Ecommerce(String nombre) {
		this.setNombreDelEcommerce(nombre);
		this.productos = new Producto[20];
		this.usuarios = new Usuario[100];
        this.carrito = new Producto[20];
        this.historialDeCompras = new Producto[100];
	}


	//Metodos
	public Usuario iniciarSesion(String nombre, String contrasenia) {
		Usuario usuarioIniciado = null;
		
		for (int i = 0; i < this.usuarios.length; i++) {
			if(this.usuarios[i] != null && this.usuarios[i].getNombre().equals(nombre) && this.usuarios[i].getContrasenia().equals(contrasenia)) {
				this.usuarioActual = this.usuarios[i];
				usuarioIniciado = this.usuarios[i];
				break;
			}
		}
		
		return usuarioIniciado;
	}


	public boolean registrarUsuario(String nombre, String contrasenia, double saldoDeUsuario) {
		boolean seRegistro = false;
		
		for (int i = 0; i < this.usuarios.length; i++) {
			if(i < this.usuarios.length && this.usuarios[i] == null) {
				this.usuarios[i] = new Usuario(nombre, contrasenia, saldoDeUsuario);
				seRegistro = true;
				break;
			}
		}
		
		return seRegistro;
	}

	public boolean esFuerte(String contrasenia) {
		//sea fuerte: 1 mayúsculas, +1 minúscula, 3 números
		boolean esFuerte = false;
		int numeros = 0;
		int mayusculas = 0;
		int minusculas = 0;
		
		//Comprobar por el codigo ASCII si contiene numeros,etc.
		for(int i=0; i<contrasenia.length(); i++) {
			if(contrasenia.charAt(i) >= 48 && contrasenia.charAt(i) <= 57) {
				numeros++;
			}
			
			if(contrasenia.charAt(i) >= 65 && contrasenia.charAt(i) <= 90) {
				mayusculas++;
			}
			
			if(contrasenia.charAt(i) >= 97 && contrasenia.charAt(i) <= 122) {
				minusculas++;
			}
		}
		
		esFuerte = numeros >= 3 && mayusculas >= 1 && minusculas > 1;
		return esFuerte;
	}



	public boolean crearProducto(String nombre, double precio, int stock) {
		boolean seCreo = false;
		
		for (int i = 0; i < this.productos.length; i++) {
			if(i < this.productos.length && this.productos[i] == null) {
				this.productos[i] = new Producto(nombre, precio, stock);
				seCreo = true;
				break;
			}
		}
		
		return seCreo;
	}


	public boolean agregarACarrito(String nombreDelProducto, int stockAgregar) {
		boolean seAgrego = false;
		
		Producto productoPorNombre = null;
		for (int i = 0; i < this.productos.length; i++) {
			if(this.productos[i] != null && this.productos[i].getNombre().toLowerCase().equals(nombreDelProducto)) {
				productoPorNombre = this.productos[i];
				break;
			}
		}
		
		
		if(productoPorNombre != null && productoPorNombre.getStock() >= stockAgregar) {
			int i = 0;
			while(i < this.carrito.length) {
				if(this.carrito[i] == null) {
		            this.carrito[i] = new Producto(productoPorNombre.getNombre(), productoPorNombre.getPrecio(), stockAgregar);
		            productoPorNombre.reducirStock(stockAgregar);
		            seAgrego = true;
		            break;
				}
				
				i++;
			}
		}
		
		return seAgrego;
	}


	public Producto[] verCarrito() {
		return this.carrito;
	}


	public boolean comprarCarrito() {
		boolean seCompro = false;
		
	    //Copia los elementos del carrito al historial de compras
		for (int i = 0; i < this.carrito.length; i++) {
			if(this.carrito[i] != null) {
				this.historialDeCompras[indiceHistorial++] = this.carrito[i];
			}
		}
		
		
        //Cambiar el saldo del usuario
		double saldoActual = this.usuarioActual.getSaldoDeUsuario();
		
		double sumaPreciosCarrito = 0.0;
		for (int i = 0; i < this.carrito.length; i++) {
			if(this.carrito[i] != null) {
				sumaPreciosCarrito += (this.carrito[i].getPrecio() * this.carrito[i].getStock());
			}
		}
		
		if(saldoActual >= sumaPreciosCarrito) {
			double nuevoSaldo = (saldoActual - sumaPreciosCarrito);
			this.usuarioActual.setSaldoDeUsuario(nuevoSaldo);		
			seCompro = true;
		}
		
		//Vaciar el carrito
        this.carrito = new Producto[20];
        
        return seCompro;
	}



	public Producto[] obtenerHistorialDeCompras() {
		return historialDeCompras;
	}


	public Usuario[] getUsuarios() {
		return usuarios;
	}



	public Producto[] getProductos(){
		return this.productos;
	}
	
	public void setProductos(Producto producto){
		int i = 0;
		while(i < this.productos.length) {
			if(this.productos[i] == null) {
				this.productos[i] = producto;
				break;
			}
			
			i++;
		}
	}


    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }
    
    public void cerrarSesion() {
        this.usuarioActual = null;
    }




	public String getNombreDelEcommerce() {
		return nombreDelEcommerce;
	}

	public void setNombreDelEcommerce(String nombreDelEcommerce) {
		this.nombreDelEcommerce = nombreDelEcommerce;
	}
	
	
	
	public Producto[] obtenerProductosobtenerProductosAscendentemente(){
		Producto[] productosOrdenadosAscendentemente = new Producto[this.productos.length];
		int k=0;
		for (int i = 0; i < this.productos.length; i++) {
			if(this.productos[i]!=null) {
				productosOrdenadosAscendentemente[k]=this.productos[i];
				k++;
			}
		}
		
		
		for (int i = 0; i < productosOrdenadosAscendentemente.length-1; i++) {
			for (int j = (i+1); j < productosOrdenadosAscendentemente.length; j++) {
				if(productosOrdenadosAscendentemente[i]!=null&&productosOrdenadosAscendentemente[j]!=null) {
					if(productosOrdenadosAscendentemente[i].getPrecio()<productosOrdenadosAscendentemente[j].getPrecio()) {
						Producto temp = productosOrdenadosAscendentemente[i];
						productosOrdenadosAscendentemente[i]=productosOrdenadosAscendentemente[j];
						productosOrdenadosAscendentemente[j]=temp;
					}
					
				}
			}
		}
		
		
		return productosOrdenadosAscendentemente;
	}
}