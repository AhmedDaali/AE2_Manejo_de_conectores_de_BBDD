package modelo.negocio;

import java.util.Scanner;

import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;
import modelo.persistencia.mysql.DaoCocheMySql;
import modelo.persistencia.mysql.DaoPasajeroMySql;

public class GestorPasajeros {
	
	Scanner sc = new Scanner(System.in);
	Pasajero pasajero = new Pasajero();
	DaoPasajero dp = new DaoPasajeroMySql();
	DaoCoche dc = new DaoCocheMySql();
	
	
	public void anadirPasajeroGestor() {
		System.out.println("Por favor, escribe el nombre del pasajero a añadir: ");
		String nombre = sc.nextLine();
		System.out.println("Por favor, escribe la edad del pasajero a añadir: ");
		int edad = Integer.parseInt(sc.nextLine());
		System.out.println("Por favor, escribe el peso del pasajero a añadir: ");
		float peso = Float.parseFloat(sc.nextLine());
		
		pasajero.setNombre(nombre);
		pasajero.setEdad(edad);
		pasajero.setPeso(peso);

		dp.addPasajero(pasajero);
	}
	
	public void borrarPasjeroGestor () {
		System.out.println("Por favor, escribe el id del pasajero a borrar: ");
		int id = Integer.parseInt(sc.nextLine());
		if(dp.obtenerPasajero(id)==null){
			System.out.println("Este id del pasajero no existe en la base de datos\n");
		}else {
		    dp.deletePasajero(id);
		}
	}
	
	public void obtenerPasajeroGestor() {
		System.out.println("Por favor, escribe el id del pasajero a consultar: ");
		int id = Integer.parseInt(sc.nextLine());
		if(dp.obtenerPasajero(id)==null){
			System.out.println("Este id del pasajero no existe en la base de datos\n");
		}else {
		System.out.println(dp.obtenerPasajero(id)+"\n");
		}	
	}
	
	public void andirCochePasGestor () {
		System.out.println("Por favor, escribe el id del pasajero: ");
		int id = Integer.parseInt(sc.nextLine());
		
		if(dp.obtenerPasajero(id)==null){
			System.out.println("Este id del pasajero no existe en la base de datos\n");
		}else {
			System.out.println("Por favor, escoja el id del coche de la lista seguiente: \n");
			dc.ListCoches();
			int idCoche = sc.nextInt();sc.nextLine();
			
			pasajero.setId(id);
			pasajero.setIdCoche(idCoche);
			dp.addPasajeroCoche(pasajero);
		     }
	}
	public void borrarCochePasGestor () {
		System.out.println("Por favor, escribe el id del pasajero: ");
		int id = sc.nextInt();sc.nextLine();
		
		if(dp.obtenerPasajero(id)==null){
			System.out.println("Este id del pasajero no existe en la base de datos\n");
		}else {
			
			pasajero.setId(id);
			dp.deletePasajeroCoche(pasajero);
		     }
	}
	
	public void ListCochePasGestor () {
		System.out.println("Por favor, escribe el id del coche: ");
		int idCoche = sc.nextInt();sc.nextLine();
		
		if(dc.obtenerCoche(idCoche)==null){
			System.out.println("Este id del coche no existe en la base de datos\n");
		}else {
			dp.listPasajerosCoche(idCoche);
		}
	}

}
