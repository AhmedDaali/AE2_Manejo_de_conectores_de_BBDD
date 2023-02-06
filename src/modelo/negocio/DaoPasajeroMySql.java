package modelo.negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero{
	
	private Connection conexion;
	
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/ae2";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean addPasajero(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into pasajero (nombre, edad, peso, id_coche) "
				+ " values(?,?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setFloat(3, p.getPeso());
			ps.setInt(4, p.getIdCoche());
			
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
			System.out.println("Pasajero añadido\n");
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + p);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}

	@Override
	public boolean deletePasajero(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "DELETE FROM pasajero WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			//sustituimos la primera interrgante por la id
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
			System.out.println("Pasajero borrado \n");
			
		} catch (SQLException e) {
			borrado = false;
			System.out.println("baja -> No se ha podido dar de baja"
					+ " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public Pasajero obtenerPasajero(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Pasajero pasajero = null;
		
		String query = "SELECT id, nombre,edad,peso,id_coche FROM pasajero "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
				pasajero.setIdCoche(rs.getInt(5));
			}
			//System.out.println(pasajero+"\n");
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el "
					+ "coche con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return pasajero;
	}

	@Override
	public List<Pasajero> listPasajeros() {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "SELECT id, nombre,edad,peso,id_coche FROM pasajero ";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
				pasajero.setIdCoche(rs.getInt(5));
				
				listaPasajeros.add(pasajero);	
			}
			//System.out.println(listaPasajeros+"\n");
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los pasajeros");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaPasajeros;
	}

	@Override
	public boolean addPasajeroCoche(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean agregado = true;
		String query = "UPDATE pasajero SET id_coche=? WHERE id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, p.getIdCoche());
			ps.setInt(2, p.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				agregado = false;
			System.out.println("El coche modificado: "+ p.toString()+"\n");
		} catch (SQLException e) {
			
			System.out.println("modificar -> error al modificar el "
					+ " coche " + p);
			agregado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return agregado;
	}

	@Override
	public boolean deletePasajeroCoche(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean borrado = true;
		String query = "UPDATE pasajero SET id_coche=? WHERE id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, (Integer) null);
			ps.setInt(2, p.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
			
			System.out.println("Coche eliminado \n");
			
		} catch (SQLException e) {
			
			System.out.println("modificar -> error al modificar el "
					+ " coche " + p);
			borrado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return borrado;
	}

	@Override
	public List<Pasajero> listPasajerosCoche(int idCoche) {
		// TODO Auto-generated method stub
		return null;
	}

}
