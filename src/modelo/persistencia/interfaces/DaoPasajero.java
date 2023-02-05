package modelo.persistencia.interfaces;

import java.util.List;
import modelo.entidad.Pasajero;

public interface DaoPasajero {
	
	/**
	 * Método que da de alta un pasajero en la BBDD. Se generará el ID de manera
	 * automática.
	 * @param p el pasajero a dar de alta
	 * @return true en caso de que se haya dado de alta. false en caso de error
	 * con la BBDD.
	 */
	boolean addPasajero(Pasajero p);
	/**
	 * Método que borra un pasajero en la BBDD por su id.
	 * @param id del pasajero a borrar.
	 * @return true en caso de que se haya borrado. false en caso de error
	 * con la BBDD.
	 */
	boolean deletePasajero(int id);

	/**
	 * Método que obtiene un pasajero en la BBDD por su id.
	 * @param id del pasajero a obtener.
	 * @return pasajero solicitado
	 */
	Pasajero getPasajero(int id);
	/**
	 * Método que obtiene la lista de pasajeros en la BBDD.
	 * @param no necsita parámetro.
	 * @return lista de pasajeros que hay en la BBDD.
	 */
	List<Pasajero> listPasajeros();
	/**
	 * Método que añade un pasajero a un coche en la BBDD por el id de cada uno .
	 * @param id del pasajero a añadir al coche.
	 * @param  idCoche, id del coche que al que se le va añadir el pasajero
	 * @return true en caso de que se haya añadido. False en caso de error
	 * con la BBDD.
	 */
	boolean addPasajeroCoche(int id,int idCoche);
	/**
	 * Método que elimina un pasajero a un coche en la BBDD por el id de cada uno .
	 * @param id del pasajero a borrar del coche.
	 * @param  idCoche, id del coche que al que se le va eliminar el pasajero
	 * @return true en caso de que se haya eliminado. False en caso de error
	 * con la BBDD.
	 */
	boolean deletePasajeroCoche(int id,Pasajero idCoche);
	/**
	 * Método que obtiene la lista de pasajeros de un coche en la BBDD 
	 * mediate el id del coche.
	 * @param idCoche, id del coche al que queremos ver sus pasajeros.
	 * @return lista de pasajeros de un coche.
	 */
	List<Pasajero> listPasajeros(int idCoche);

}
