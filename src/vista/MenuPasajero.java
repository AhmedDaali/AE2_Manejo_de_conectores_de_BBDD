package vista;

import java.util.Scanner;

import modelo.negocio.GestorPasajeros;
import modelo.persistencia.interfaces.DaoPasajero;
import modelo.persistencia.mysql.DaoPasajeroMySql;

public class MenuPasajero {
	
	public void menu()throws Exception  {
		
		boolean continuarPas = true;
		
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
				
				
			    Scanner sc = new Scanner(System.in);
				DaoPasajero dp = new DaoPasajeroMySql();
				GestorPasajeros gp = new GestorPasajeros();
						
				
				try {
					
					int opcion = Integer.parseInt(sc.nextLine());
					if(opcion<1 || opcion>8) {
						System.out.println("Opción no válida\n");
					}else {
				
						switch(opcion) {
									
						case 1:
							gp.anadirPasajeroGestor();
							break;
							
						case 2:
							gp.borrarPasjeroGestor();
							break;
							
						case 3:
							gp.obtenerPasajeroGestor();
							break;
							
						case 4:
							dp.listPasajeros();
							break;
							
						case 5:
							gp.andirCochePasGestor();
							break;
							
						case 6:
							gp.borrarCochePasGestor();
							break;
							
						case 7:
							gp.ListCochePasGestor();		
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

