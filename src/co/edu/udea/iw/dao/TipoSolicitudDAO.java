package co.edu.udea.iw.dao;

/**
* @author Yuri Quejada
* @author Daniel Pelaez
* @author Jean Herrera
* @version 1.0
*/

import java.util.List;
import co.edu.udea.iw.dto.TipoSolicitud;
import co.edu.udea.iw.exception.MyException;

/**
 * DAO Interfaz que define los metodos que va a proveer
 * la clase TipoSolicitud.
 * 
 *
 * @version 1.0
 */
public interface TipoSolicitudDAO {

	/**
	 * Obtiene la lista de los tipos de solicitud almacenados en la 
	 * tabla TipoSolicitud de la base de datos.
	 * 
	 * @return lista de los tipos de solicitud.
	 * @throws ExceptionDao cuando ocurre cualquier error en la comunicaci�n con la BD.
	 */
	public List<TipoSolicitud> obtenerTipoSolicitudes() throws MyException;

	/**
	 * Obtiene el tipo Solicitud que corresponda al identificador ingresado como par�metro.
	 * 
	 * @param id identificador del tipo de solicitud.
	 * @return TipoSolicitud con toda su informaci�n.
	 * @throws ExceptionDao cuando ocurre cualquier error en la comunicaci�n con la BD.
	 */
	public TipoSolicitud obtenerTipoSolicitud(Integer id) throws MyException;

}
