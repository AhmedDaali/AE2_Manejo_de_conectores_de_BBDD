package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.DaoCocheMySql;

import modelo.persistencia.interfaces.DaoCoche;


public class Aplicacion {

	public static void main(String[] args)throws Exception  {
		
        boolean continuar = true;
        Scanner sc = new Scanner(System.in);
        String opcion ="";
        
        try{
        
        
        	do{
		        System.out.println("******Elige el número de la opción que desea efectuar*****\n");
		        System.out.println("1.Añadir coche\n");
		        System.out.println("2.Borrar coche por ID\n");
				System.out.println("3.Consulta coche por ID\n");
				System.out.println("4.Modificar coche por ID\n");
				System.out.println("5.Listado de coches\n");
				System.out.println("6.Adminstrar pasajeros\n");
				System.out.println("7.Terminar el programa\n");
				
				opcion = sc.nextLine();
				
				
				
				
				Coche coche = new Coche();
				List<Coche> listaCoches = new ArrayList<>();
				DaoCoche dc = new DaoCocheMySql();
				switch(opcion) {
				
				
				
				case"1":
					System.out.println("Por favor, escribe la matrícula del coche a añadir: ");
					String matricula = sc.nextLine();
					System.out.println("Por favor, escribe el modelo del coche a añadir: ");
					String modelo = sc.nextLine();
					System.out.println("Por favor, escribe el color del coche a añadir: ");
					String color = sc.nextLine();
					
					
					coche.setMatricula(matricula);
					coche.setModelo(modelo);
					coche.setColor(color);
					
					dc.addCoche(coche);

					break;
					
				case"2":
					System.out.println("Por favor, escribe el id del coche a borrar: ");
					int id = sc.nextInt();sc.nextLine();
					dc.deleteCoche(id);
					
					break;
					
				case"3":
					System.out.println("Por favor, escribe el id del coche a consultar: ");
					id = sc.nextInt();sc.nextLine();
					dc.obtenerCoche(id);
					
					break;
				case"4":
					System.out.println("Por favor, escribe el id del coche a modificar: ");
					id = sc.nextInt();sc.nextLine();
					System.out.println("Por favor, escribe la matrícula del coche a modificar: ");
					matricula = sc.nextLine();
					System.out.println("Por favor, escribe el modelo del coche a modificar: ");
					modelo = sc.nextLine();
					System.out.println("Por favor, escribe el color del coche a modificar: ");
					color = sc.nextLine();
					
					//Coche coche2 = new Coche();
					coche.setId(id);
					coche.setMatricula(matricula);
					coche.setModelo(modelo);
					coche.setColor(color);
					
					dc.updateCoche(coche);
					

					break;
				case"5":
					dc.ListCoches();
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
