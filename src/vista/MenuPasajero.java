package vista;

import java.util.Scanner;

import modelo.entidad.Pasajero;
import modelo.negocio.DaoCocheMySql;
import modelo.negocio.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;

public class MenuPasajero {
	
	public void menu()throws Exception  {
		
		 boolean continuarPas = true;
	     Scanner sc = new Scanner(System.in);
	     
	     try{     
	     	do{
		        System.out.println("******Elige el número de la opción que desea efectuar*****\n");
		        System.out.println("1.Añadir pasajero\n");
		        System.out.println("2.Borrar pasajero por ID\n");
				System.out.println("3.Consultar pasajero por ID\n");
				System.out.println("4.Listar todos los pasajeros\n");
				System.out.println("5.Añadir pasajero a un coche\n");
				System.out.println("6.Eliminar pasajero de un coche,\n");
				System.out.println("7.Listar todos los pasajeros de un coche\n");
				System.out.println("8.Volver al menú de coches \n");
				
				Pasajero pasajero = new Pasajero();
				DaoCoche dc = new DaoCocheMySql();
				DaoPasajero dp = new DaoPasajeroMySql();
				
				try {
					int opcion = Integer.parseInt(sc.nextLine());
					if(opcion<1 || opcion>7) {
						System.out.println("Opción no válida\n");
					}else {
				
						switch(opcion) {
									
						case 1:
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
		
							break;
							
						case 2:
							
							System.out.println("Por favor, escribe el id del pasajero a borrar: ");
							int id = Integer.parseInt(sc.nextLine());
							if(dp.obtenerPasajero(id)==null){
								System.out.println("Este id del pasajero no existe en la base de datos\n");
							}else {
							    dp.deletePasajero(id);
							}
							break;
							
						case 3:
							System.out.println("Por favor, escribe el id del pasajero a consultar: ");
							id = Integer.parseInt(sc.nextLine());
							if(dp.obtenerPasajero(id)==null){
								System.out.println("Este id del pasajero no existe en la base de datos\n");
							}else {
							System.out.println(dp.obtenerPasajero(id));
							}	
							break;
							
						case 4:
							System.out.println(dp.listPasajeros()+"\n");
							break;
							
						case 5:
							System.out.println("Por favor, escribe el id del pasajero: ");
							id = Integer.parseInt(sc.nextLine());
							
							if(dp.obtenerPasajero(id)==null){
								System.out.println("Este id del pasajero no existe en la base de datos\n");
							}else {
								System.out.println("Por favor, escoja el id del coche de la lista seguiente: ");
								dc.ListCoches();
								int idCoche = sc.nextInt();sc.nextLine();
								
								pasajero.setId(id);
								pasajero.setIdCoche(idCoche);
								dp.addPasajeroCoche(pasajero);
							     }	
							break;
							
						case 6:
							System.out.println("Por favor, escribe el id del pasajero: ");
							id = sc.nextInt();sc.nextLine();
							
							if(dp.obtenerPasajero(id)==null){
								System.out.println("Este id del pasajero no existe en la base de datos\n");
							}else {
								
								pasajero.setId(id);
								dp.deletePasajeroCoche(pasajero);
							     }
							break;
							
						case 7:
							System.out.println("Por favor, escribe el id del coche: ");
							int idCoche = sc.nextInt();sc.nextLine();
							
							if(dc.obtenerCoche(idCoche)==null){
								System.out.println("Este id del coche no existe en la base de datos\n");
							}else {
								dp.listPasajerosCoche(idCoche);
							}		
							break;
							
						case 8:
							continuarPas=false;
						    break;
						}
					}
				}catch(NumberFormatException e) {
					System.out.println("Dato no válido\n");
			    }	
		    }while(continuarPas);
        
	   //Recogemos las excepciones con el 'catch'.
	   }catch (Exception e) {
		   System.out.println("Error en la conexion");
		   e.printStackTrace();
	       }
		}
}

