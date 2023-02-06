package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.entidad.Pasajero;
import modelo.negocio.DaoCocheMySql;
import modelo.negocio.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;

public class MenuPasajero {
	
	public void menu()throws Exception  {
		
	
		 boolean continuar = true;
	     Scanner sc = new Scanner(System.in);
	     String opcion = "";
	     
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
					System.out.println("8.Terminar el programa\n");
					
					opcion = sc.nextLine();
					
					
					
					
					Pasajero pasajero = new Pasajero();
					//List<Pasajero> listaPasajeros = new ArrayList<>();
					DaoCoche dc = new DaoCocheMySql();
					DaoPasajero dp = new DaoPasajeroMySql();
					switch(opcion) {
					
					
					
					case"1":
						System.out.println("Por favor, escribe el nombre del pasajero a añadir: ");
						String nombre = sc.nextLine();
						System.out.println("Por favor, escribe la edad del pasajero a añadir: ");
						int edad =sc.nextInt(); sc.nextLine();
						System.out.println("Por favor, escribe el peso del pasajero a añadir: ");
						float peso = sc.nextFloat(); sc.nextLine();
						System.out.println("Por favor, escribe el id de estos coches a añadir al pasajero:\n ");
						//Enseñamos la lista de coches para que el usuario pueda eligir
						dc.ListCoches();
						int idCoche =sc.nextInt(); sc.nextLine();
						
						pasajero.setNombre(nombre);
						pasajero.setEdad(edad);
						pasajero.setPeso(peso);
						pasajero.setIdCoche(idCoche);
	
						
						dp.addPasajero(pasajero);
	
						break;
						
					case"2":
						System.out.println("Por favor, escribe el id del pasajero a borrar: ");
						int id = sc.nextInt();sc.nextLine();
						if(dp.obtenerPasajero(id)==null){
							System.out.println("Este id del pasajero no existe en la base de datos\n");
						}else {
						    dp.deletePasajero(id);
						}
						break;
						
					case"3":
						System.out.println("Por favor, escribe el id del pasajero a consultar: ");
						id = sc.nextInt();sc.nextLine();
						if(dp.obtenerPasajero(id)==null){
							System.out.println("Este id del pasajero no existe en la base de datos\n");
						}else {
						System.out.println(dp.obtenerPasajero(id));
						}
						break;
						
					case"4":
						
						System.out.println(dp.listPasajeros()+"\n");
	
						break;
						
					case"5":
						System.out.println("Por favor, escribe el id del pasajero: ");
						id = sc.nextInt();sc.nextLine();
						
						if(dp.obtenerPasajero(id)==null){
							System.out.println("Este id del pasajero no existe en la base de datos\n");
						}else {
							System.out.println("Por favor, escribe el id del coche de la lista seguiente: ");
							dc.ListCoches();
							id = sc.nextInt();sc.nextLine();
							
							//pasajero.SetId(id);
							//dp.addPasajeroCoche(id);
						     }
						break;
						
					case"6":
						break;
					case"7":
						System.out.println("Saliendo.....");
						continuar=false;
						sc.close();
						System.out.println("Fin de sesión");
						
					    break;
					}
			    }while(continuar);
		    //Recogemos las excepciones con el 'catch'.	
		   }catch (Exception e) {
			   System.out.println("Error en la conexion");
			   e.printStackTrace();
		       }
		}

}
