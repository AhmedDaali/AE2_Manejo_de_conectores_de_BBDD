package vista;

import java.util.Scanner;
import modelo.entidad.Coche;
import modelo.negocio.DaoCocheMySql;
import modelo.persistencia.interfaces.DaoCoche;

public class Aplicacion {

	public static void main(String[] args)throws Exception  {
		
        boolean continuar = true;
        Scanner sc = new Scanner(System.in);
        
        try{
        	while(continuar){
		        System.out.println("******Elige el número de la opción que desea efectuar*****\n");
		        System.out.println("1.Añadir coche\n");
		        System.out.println("2.Borrar coche por ID\n");
				System.out.println("3.Consulta coche por ID\n");
				System.out.println("4.Modificar coche por ID\n");
				System.out.println("5.Listado de coches\n");
				System.out.println("6.Gestión de pasajeros\n");
				System.out.println("7.Terminar el programa\n");
				
				
				Coche coche = new Coche();
				DaoCoche dc = new DaoCocheMySql();
				MenuPasajero menuPas = new MenuPasajero();
				
				
				try {
					int opcion = Integer.parseInt(sc.nextLine());;
					if(opcion<1 || opcion>7) {
						System.out.println("Opción no válida\n");
						
					}else {
						switch(opcion) {
						
						case 1:
							System.out.println("Por favor, escribe la matrícula del coche a añadir: ");
							String matricula = sc.nextLine();
							System.out.println("Por favor, escribe la marca del coche a añadir: ");
							String marca = sc.nextLine();
							System.out.println("Por favor, escribe el modelo del coche a añadir: ");
							String modelo = sc.nextLine();
							System.out.println("Por favor, escribe el color del coche a añadir: ");
							String color = sc.nextLine();
						
							coche.setMatricula(matricula);
							coche.setMarca(marca);
							coche.setModelo(modelo);
							coche.setColor(color);
							
							System.out.println(dc.addCoche(coche));
		
							break;
							
						case 2:
							System.out.println("Por favor, escribe el id del coche a borrar: ");
							int id = Integer.parseInt(sc.nextLine());
							
							if(dc.obtenerCoche(id)==null){
								System.out.println("Este id del coche no existe en la base de datos\n");
							}else {
								dc.deleteCoche(id);
							}
							break;
							
						case 3:
							System.out.println("Por favor, escribe el id del coche a consultar: ");
							id = Integer.parseInt(sc.nextLine());
							
							if(dc.obtenerCoche(id)==null){
								System.out.println("Este id del coche no existe en la base de datos\n");
							}else {
							      System.out.println(dc.obtenerCoche(id));
							}
							break;
							
						case 4:
							System.out.println("Por favor, escribe el id del coche a modificar: ");
							id = Integer.parseInt(sc.nextLine());
							
							if(dc.obtenerCoche(id)==null){
								System.out.println("Este id del coche no existe en la base de datos\n");
							}else {
								System.out.println("Por favor, escribe la matrícula del coche a modificar: ");
								matricula = sc.nextLine();
								System.out.println("Por favor, escribe la marca del coche a modificar: ");
								marca = sc.nextLine();
								System.out.println("Por favor, escribe el modelo del coche a modificar: ");
								modelo = sc.nextLine();
								System.out.println("Por favor, escribe el color del coche a modificar: ");
								color = sc.nextLine();
								
								coche.setId(id);
								coche.setMatricula(matricula);
								coche.setMarca(marca);
								coche.setModelo(modelo);
								coche.setColor(color);
								
								dc.updateCoche(coche);
							}
							break;
							
						case 5:
							dc.ListCoches();
							break;
							
						case 6:
							menuPas.menu();
							break;
							
						case 7:
							System.out.println("Saliendo.....");
							continuar=false;
							sc.close();
							System.out.println("Fin de sesión");
							
						    break;
						}
					}	
			    }catch(NumberFormatException e) {
				    System.out.println("Dato no válido\n");
			    }
        	}		
		//Recogemos las excepciones con el 'catch'.
	    }catch (Exception e) {
		     System.out.println("Error en la conexion");
		    e.printStackTrace();
        }	
	}
}
