package modelo.negocio;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.mysql.DaoCocheMySql;

public class GestorCoches {
	
	Scanner sc = new Scanner(System.in);
	Coche coche = new Coche();
	DaoCoche dc = new DaoCocheMySql();
	
	public void anadirCocheGstor() {
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
		
		dc.addCoche(coche);
		
	}
	
	public void borrarCocheGestor () {
		System.out.println("Por favor, escribe el id del coche a borrar: ");
		int id = Integer.parseInt(sc.nextLine());
		
		if(dc.obtenerCoche(id)==null){
			System.out.println("Este id del coche no existe en la base de datos\n");
		}else {
			dc.deleteCoche(id);
		}
	}
	
	public void consultarCocheGestor() {
		System.out.println("Por favor, escribe el id del coche a consultar: ");
		int id = Integer.parseInt(sc.nextLine());
		
		if(dc.obtenerCoche(id)==null){
			System.out.println("Este id del coche no existe en la base de datos\n");
		}else {
		      System.out.println(dc.obtenerCoche(id)+"\n");
		}
	}
	
	public void modificarCocheGestor() {
		System.out.println("Por favor, escribe el id del coche a modificar: ");
		int id = Integer.parseInt(sc.nextLine());
		
		if(dc.obtenerCoche(id)==null){
			System.out.println("Este id del coche no existe en la base de datos\n");
		}else {
			System.out.println("Por favor, escribe la matrícula del coche a modificar: ");
			String matricula = sc.nextLine();
			System.out.println("Por favor, escribe la marca del coche a modificar: ");
			String marca = sc.nextLine();
			System.out.println("Por favor, escribe el modelo del coche a modificar: ");
			String modelo = sc.nextLine();
			System.out.println("Por favor, escribe el color del coche a modificar: ");
			String color = sc.nextLine();
			
			coche.setId(id);
			coche.setMatricula(matricula);
			coche.setMarca(marca);
			coche.setModelo(modelo);
			coche.setColor(color);
			
			dc.updateCoche(coche);
		}
	}

}
