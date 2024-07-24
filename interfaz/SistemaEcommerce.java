import java.util.Scanner;

public class SistemaEcommerce {
	public static final Scanner TECLADO = new Scanner(System.in);
	
	public static void main(String[] args) {
        System.out.println(Colores.VERDE + "Ingrese nombre del E-commerce" + Colores.RESET);
		String nombre = TECLADO.next();
		Ecommerce ecommerce = new Ecommerce(nombre);
		System.out.println(Colores.AZUL + Colores.TEXTO_GRANDE +"\n---Bienvenido a " + nombre + "---" + Colores.RESET);
				
		boolean salir = false;
		MenuPrincipal opcionElegida;
		
		do{
			System.out.println(Colores.TEXTO_GRANDE + "\n--- MENU PRINCIPAL ---" + Colores.RESET);
			opcionElegida = obtenerOpcionDeEnumParaMenuPrincipal();
			
	        switch(opcionElegida){
	        	case INICIAR_SESION:
	        		iniciarSesion(ecommerce);
	        	break;
	        	
	        	case REGISTRAR_USUARIO:
	        		registrarUsuario(ecommerce);
	        	break;
	        	
	        	case SALIR:
	        		System.out.print(Colores.AZUL + "\n¡Hasta luego!" + Colores.RESET);
	        		salir = true;
	        	break;
	        	
	        	default:
	        	System.out.println(Colores.ROJO + "Opción no válida. Intente de nuevo." + Colores.RESET);
	        }
        
		}while(!salir);
	}
	
	
	
	
	//Metodos
	private static void iniciarSesion(Ecommerce ecommerce) {
		System.out.println("Ingrese nombre del usuario (Sin espacios): ");
		String nombre = TECLADO.next().toLowerCase();
		
		System.out.println("Ingrese contraseña (Sin espacios): ");
		String contrasenia = TECLADO.next();
		
		Usuario usuarioIniciado = ecommerce.iniciarSesion(nombre, contrasenia);
		if(usuarioIniciado != null) {
			System.out.println(Colores.VERDE + "\nBienvenido " + usuarioIniciado.getNombre() + Colores.RESET);
			menuUsuario(ecommerce);
		}
		else {
			System.out.println(Colores.CRIMSON + "\nNo se encontro el usuario" + Colores.RESET);
		}
		
	}
	
	
	
	private static void registrarUsuario(Ecommerce ecommerce) {
		System.out.println("Ingrese nombre del usuario (Sin espacios): ");
		String nombre = TECLADO.next().toLowerCase();
		
		String contrasenia;
		boolean esFuerte = false;
		do {
			System.out.println("Ingrese contraseña (Sin espacios): ");
			contrasenia = TECLADO.next();
			esFuerte = ecommerce.esFuerte(contrasenia);
	        if (!esFuerte) {
	        	System.out.println("La contraseña no es lo suficientemente fuerte (tiene que tener: 1 mayúsculas, +1 minúscula, 3 números)");
	        }
		}while(!esFuerte);

		
		double saldoDeUsuario;
		do {
			System.out.println("Ingrese saldo del usuario: ");
			saldoDeUsuario = TECLADO.nextDouble();
		}while(saldoDeUsuario <= 0);
		
		
		boolean seRegistro = ecommerce.registrarUsuario(nombre, contrasenia, saldoDeUsuario);
		if(seRegistro) {
			System.out.println(Colores.VERDE + "\nSe registro con exito" + Colores.RESET);
		}
		else {
			System.out.println(Colores.CRIMSON + "\nNo se pudo registrar" + Colores.RESET);
		}
	}
	
	

	private static void crearProducto(Ecommerce ecommerce) {
		System.out.println("Ingrese nombre del producto (Sin espacios): ");
		String nombre = TECLADO.next();
		
		double precio = 0;
		do {
			System.out.println("Ingrese precio: ");
			precio = TECLADO.nextDouble();
		}while(precio <= 0);
		
		
		int stock = 0;
		do {
			System.out.println("Ingrese stock: ");
			stock = TECLADO.nextInt();	
		}while(stock <= 0);
		
		
		boolean seCreo = ecommerce.crearProducto(nombre, precio, stock);
		if(seCreo) {
			System.out.println(Colores.VERDE + "Se creo su producto con exito" + Colores.RESET);
		}
		else {
			System.out.println(Colores.CRIMSON + "No se pudo crear el producto" + Colores.RESET);
		}
	}
	
	
	
	private static void agregarACarrito(Ecommerce ecommerce) {
		Producto[] productos = ecommerce.getProductos();
			mostrarArrayProducto(productos);
			
			System.out.println("\nEscriba nombre del producto (Sin espacios): ");
			String nombreDelProducto = TECLADO.next().toLowerCase();
			
			
			int stockAgregar = 0;
			do {
				System.out.println("Cantidad a agregar al carrito: ");
				stockAgregar = TECLADO.nextInt();
			}while(stockAgregar <= 0);
			
			
			
			boolean seAgrego = ecommerce.agregarACarrito(nombreDelProducto, stockAgregar);
			if(seAgrego) {
				System.out.println(Colores.VERDE + "!Se agrego a al carrito¡" + Colores.RESET);
			}
			else {
				System.out.println(Colores.CRIMSON + "No se pudo agregar al carrito" + Colores.RESET);
			}
	}
	
	
	
	private static void verCarrito(Ecommerce ecommerce) {
		Producto[] carritoDeUsuario = ecommerce.verCarrito();
		
	    boolean carritoVacio = true;
	    if (carritoDeUsuario != null) {
	        for (Producto producto : carritoDeUsuario) {
	            if (producto != null) {
	                carritoVacio = false;
	                break;
	            }
	        }
	    }

	    if (carritoVacio) {
	        System.out.println(Colores.CRIMSON + "El carrito está vacío." + Colores.RESET);
	    } else {
	        mostrarArrayProducto(carritoDeUsuario);
	    }
	}
	
	
	
	private static void comprarCarrito(Ecommerce ecommerce) {
		boolean seCompro = ecommerce.comprarCarrito();
		if(seCompro) {
			System.out.println(Colores.VERDE + "¡Carrito comprado con exito!" + Colores.RESET);			
		}
		else {
			System.out.println(Colores.CRIMSON + "Falta de saldo del usuario" + Colores.RESET);
		}
	}
	
	
	
	private static void obtenerProductosobtenerProductosAscendentemente(Ecommerce ecommerce) {
		Producto[] productosOrdenadosAscendentemente= ecommerce.obtenerProductosobtenerProductosAscendentemente();
	    boolean productosVacios = true;

	    if (productosOrdenadosAscendentemente != null) {
	        for (Producto producto : productosOrdenadosAscendentemente) {
	            if (producto != null) {
	                productosVacios = false;
	                break;
	            }
	        }
	    }

	    if (productosVacios) {
	        System.out.println(Colores.CRIMSON + "No hay productos." + Colores.RESET);
	    } else {
	        mostrarArrayProducto(productosOrdenadosAscendentemente);
	    }
	}
	
	
	
	private static void obtenerUsuarios(Ecommerce ecommerce) {
		Usuario[] usuarios = ecommerce.getUsuarios();
		mostrarArrayUsuarios(usuarios);
	}
	

	
	
	private static void obtenerHistorialDeCompras(Ecommerce ecommerce) {
		Producto[] historialComprasDeUsuario = ecommerce.obtenerHistorialDeCompras();
	    boolean productosVacios = true;

	    if (historialComprasDeUsuario != null) {
	        for (Producto producto : historialComprasDeUsuario) {
	            if (producto != null) {
	                productosVacios = false;
	                break;
	            }
	        }
	    }

	    if (productosVacios) {
	        System.out.println(Colores.CRIMSON + "No hay compras realizadas." + Colores.RESET);
	    } else {
	        mostrarArrayProducto(historialComprasDeUsuario);
	    }
	}
	
	
	private static void agregarSaldoUsuario(Ecommerce ecommerce) {
		double saldoDeUsuario;
		do {
			System.out.println("\nIngrese saldo para el usuario: ");
			saldoDeUsuario = TECLADO.nextDouble();
		}while(saldoDeUsuario <= 0);
		
		Usuario usuarioActual = ecommerce.getUsuarioActual();
		
		double saldoActual = usuarioActual.getSaldoDeUsuario();
		usuarioActual.setSaldoDeUsuario(saldoActual + saldoDeUsuario);
		
		System.out.println(Colores.VERDE + "Se agrego saldo al usuario" + Colores.RESET);
	}
	

	
	
	

	private static void mostrarArrayProducto(Producto[] productos) {
		for (int i = 0; i < productos.length; i++) {
			if(productos[i] != null) {
				System.out.println(productos[i].toString());
			}
		}
	}
	
	
	private static void mostrarArrayUsuarios(Usuario[] usuarios) {
		for (int i = 0; i < usuarios.length; i++) {
			if(usuarios[i] != null) {
				System.out.println(usuarios[i].getNombre() + " - Saldo: " + usuarios[i].getSaldoDeUsuario());
			}
		}
	}

	
	
	
	

	//Menu Usuario---------------
	private static void menuUsuario(Ecommerce ecommerce) {
		MenuUsuario opcionMenuUsuario = null;
		
		boolean salir = false;
		do {
			opcionMenuUsuario = obtenerOpcionDeEnumParaMenuUsuario();
			
			switch(opcionMenuUsuario) {
	        	case CREAR_PRODUCTO:
	        		crearProducto(ecommerce);
	        	break;
	        	
	        	case AGREGAR_A_CARRITO:
	        		agregarACarrito(ecommerce);
	        	break;
	        	
	        	case VER_CARRITO:
	        		verCarrito(ecommerce);
	        	break;
	        	
	        	case COMPRAR_CARRITO:
	        		comprarCarrito(ecommerce);
	        	break;
	        	
	        	case OBTENER_PRODUCTOS_ASCENDENTEMENTE:
	        		obtenerProductosobtenerProductosAscendentemente(ecommerce);
	        	break;
	        	
	        	case OBTENER_USUARIOS:
	        		obtenerUsuarios(ecommerce);
	        	break;
	        	
	        	case OBTENER_HISTORIAL_DE_COMPRAS:
	        		obtenerHistorialDeCompras(ecommerce);
	        	break;
	        	
	        	case AGREGAR_SALDO_USUARIO:
	        		agregarSaldoUsuario(ecommerce);
	        	break;
	        	
	        	case CERRAR_SESION:
	        		System.out.print(Colores.AZUL + "\nSesion cerrada" + Colores.RESET);
	        		salir = true;
	        	break;
	        	
	        	default:
	        	System.out.println(Colores.CRIMSON + "Opción no válida. Intente de nuevo." + Colores.RESET);
			}
		}while(!salir);
	}
	
	
	private static void mostrarMenuUsuario() {
		String menu = Colores.TEXTO_GRANDE + "\n--- MENU USUARIO ---" + Colores.RESET;

		for (int i = 0; i < MenuUsuario.values().length; i++) {
			menu += "\n" + (i + 1) + ") " + MenuUsuario.values()[i].getDescripcion();
		}

		System.out.println(menu);
	}
	
	
	private static MenuUsuario obtenerOpcionDeEnumParaMenuUsuario() {
		int opcion = 0;

		do {
			mostrarMenuUsuario();
			System.out.println(Colores.VERDE + "\nIngrese opcion: " + Colores.RESET);
			opcion = TECLADO.nextInt();	
		} while (opcion < 1 || opcion > MenuUsuario.values().length);
		
		return  MenuUsuario.values()[opcion - 1];
	}
	
	
	
	

    
	//Menu Principal---------------
	private static void mostrarMenuPrincipal() {
		String menu = "";

		for (int i = 0; i < MenuPrincipal.values().length; i++) {
			menu += "\n" + (i + 1) + ") " + MenuPrincipal.values()[i].getDescripcion();
		}

		System.out.println(menu);
	}
	
	
	private static MenuPrincipal obtenerOpcionDeEnumParaMenuPrincipal() {
		int opcion = 0;

		do {
			mostrarMenuPrincipal();
			System.out.println(Colores.VERDE + "\nIngrese opcion: " + Colores.RESET);
			opcion = TECLADO.nextInt();
			
		} while (opcion < 1 || opcion > MenuPrincipal.values().length);
		
		return  MenuPrincipal.values()[opcion - 1];
	}
}