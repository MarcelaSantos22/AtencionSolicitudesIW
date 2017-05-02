package co.edu.udea.iw.dao;

/**
* @author Yuri Quejada
* @author Daniel Pelaez
* @author Jean Herrera
* @version 1.0
*/
import java.util.List;

import co.edu.udea.iw.dto.RespuestaEncuesta;
import co.edu.udea.iw.exception.MyException;

public interface RespuestaEncuestaDAO {
	/**
	 * M�todo que nos permite obtener todos las respuestas.
	 * 
	 * @return Lista con las respuestas.
	 * @throws MyException
	 */
	public List<RespuestaEncuesta> listarRespuestas() throws MyException;

	/**
	 * M�todo que nos permite obtener una unica respuesta.
	 * 
	 * @param idrespuesta
	 *            con la cual se realizar� la b�squeda.
	 * @return Retorna la evaluacion buscada.
	 * @throws MyException
	 */
	public RespuestaEncuesta obtenerRespuesta(int id) throws MyException;

	/**
	 * M�todo que nos permite crear una nueva respuesta.
	 * 
	 * @param respuesta
	 *            que se va a crear en el sistema y almacenar en la BD.
	 * @throws MyException
	 */
	public void crearRespuesta(RespuestaEncuesta respuesta) throws MyException;

	/**
	 * M�todo que nos permite eliminar una respuesta de la base de datos. 
	 * @param respuesta que se va a eliminar.
	 * @throws MyException
	 */
	public void eliminarRespuesta(RespuestaEncuesta respuesta) throws MyException;

	/**
	 * M�todo para modificar una respuesta existente.
	 * @param respuesta que se desea modificar.
	 * @throws MyException
	 */
	public void modificarRespuesta(RespuestaEncuesta respuesta) throws MyException;
	
}
