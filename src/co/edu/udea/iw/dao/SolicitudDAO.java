package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.dto.Solicitud;
import co.edu.udea.iw.dto.TipoSolicitud;
import co.edu.udea.iw.exception.MyException;

public interface SolicitudDAO {
	/**
	 * Método que nos permite obtener todos las solicitudes.
	 * 
	 * @return Lista con las solicitudes.
	 * @throws MyException
	 */
	public List<Solicitud> listarSolicitudes() throws MyException;
	
	/**
	 * Método que nos permite obtener una unica solicitud.
	 * 
	 * @param idcodigo
	 *            con la cual se realizará la búsqueda.
	 * @return Retorna la solicitud buscada.
	 * @throws MyException
	 */
	public Solicitud obtenerSolicitud(int idcodigo) throws MyException;

	/**
	 * Método que nos permite crear una nueva solicitud.
	 * 
	 * @param solicitud
	 *            que se va a crear en el sistema y almacenar en la BD.
	 * @throws MyException
	 */
	public void crearSolicitud(Solicitud solicitud) throws MyException;

	/**
	 * Método que nos permite eliminar una solicitud de la base de datos. 
	 * @param solicitud que se va a eliminar.
	 * @throws MyException
	 */
	public void eliminarSolicitud(Solicitud solicitud) throws MyException;

	/**
	 * Método para modificar una solicitud existente.
	 * @param solicitud que se desea modificar.
	 * @throws MyException
	 */
	public void modificarSolicitud(Solicitud solicitud) throws MyException;
	
	/**
	 * Método para filtrar las solicitudes entre quejas, reclamos, sugerencias o preguntas
	 * @param tipo de solicitud
	 * @throws MyException
	 * 
	 */
	public List<Solicitud> filtrarSolicitudes(TipoSolicitud tipoSolicitud) throws MyException;
	
}
