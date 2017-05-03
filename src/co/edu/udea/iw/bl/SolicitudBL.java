package co.edu.udea.iw.bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.SolicitudDAO;
import co.edu.udea.iw.dao.TipoSolicitudDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.dto.Empleado;
import co.edu.udea.iw.dto.Solicitud;
import co.edu.udea.iw.dto.Sucursal;
import co.edu.udea.iw.dto.TipoSolicitud;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;
import co.edu.udea.iw.exception.IWServiceException;
import co.edu.udea.iw.validation.Validaciones;

/**
 * Clase encargada de la logica de negocio para la clase Solicitud. Clase
 * transaccional con la BD.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

public class SolicitudBL {
	// Representan los Bean en el archivo de configuraci贸n, para poder realizar
	// la inyecci贸n de dependencia
	private SolicitudDAO solicitudDAO;
	private UsuarioDAO usuarioDAO;
	private TipoSolicitudDAO tipoSolicitudDAO;

	/**
	 * Metodo para guardar la solicitud realizada por el Cliente. Los campos
	 * necesarios para realizar la solicitud son los ingresados como parametros.
	 * 
	 *
	private String complejidad;
	private Date fechaRespuesta;
	private String respuestaSolicitud;
	private Sucursal sucursal;
	private Empleado responsable;
	 * 
	 * Los campos fechaRespuesta y respuestaSolicitud, se dejan vac韔s hasta que se
	 * responda la solicitud
	 * 
	 * @param descripcion
	 *            campo de texto, contiene el motivo de la solicitud.
	 *  @param complejidad
	 *            campo de texto, contiene la complejidad de la solicitud.           
	 * @param tiposolicitud
	 *            campo que contiene el tipo de solicitud.
	 * @param cliente
	 *            user del cliente.
	 * @param idSucursal
	 *            identificador de la sucursal.
	 * @param fechaSolicitud
	 *            fecha en que el cliente hizo la solitud.
	 * @throws MyException
	 *             cuando ocurre cualquier error en la comunicaci髇 con la BD.
	 * @throws IWServiceException
	 *             cuando ocurre cualquier error en la logica de negocio.
	 */
	public Solicitud guardarSolicitud(String descripcion, int tiposolicitud,
			String cliente, String producto, int idSucursal, Date fechaSolicitud)
			throws MyException, IWServiceException {

		TipoSolicitud tipoSolicitud = null;
		Usuario usuario;
		Solicitud solicitud;
		usuario = usuarioDAO.obtener(cliente);
		if (usuario == null) {
			throw new IWServiceException("El usuario no existe en el sistema");
		}
		if (!usuario.getRol().getNombre().equals("cliente")) {
			throw new IWServiceException(
					"No tiene permisos para realizar solicitudes");
		}

		tipoSolicitud = tipoSolicitudDAO.obtenerTipoSolicitud(tiposolicitud);
		if (tipoSolicitud == null) {
			throw new IWServiceException(
					"El tipo de solicitud no es valida en el sistema");
		}
		if (Validaciones.isTextoVacio(descripcion)) {
			throw new IWServiceException(
					"El campo descripcion no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(producto)) {
			throw new IWServiceException(
					"El campo producto no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(Integer.toString(idSucursal))) {
			throw new IWServiceException("Debe seleccionar una sucursal");
		}

		solicitud = new Solicitud();
		solicitud.setCliente(usuario);
		solicitud.setTipoSolicitud(tipoSolicitud);
		solicitud.setDescripcion(descripcion);
		solicitud.setProducto(producto);
		solicitud.setFechaSolicitud(fechaSolicitud);

		solicitudDAO.guardar(solicitud);

		return solicitud;

	}

	/**
	 * Metodo para asignar un responsable que se encargue de responder la
	 * solicitud.
	 * 
	 * @param idSolicitud
	 *            identificador de la solicitud.
	 * @param responsable
	 *            usuario del encargado de responder la solicitud.
	 * @throws ExceptionDao
	 *             Manejar las excepciones del DAO.
	 * @throws IWServiceException
	 *             Manejar las excepciones de la l贸gica del negocio.
	 */
	public Solicitud asignarResponsable(int idSolicitud,
			String usuarioResponsable, String usuarioGerente)
			throws ExceptionDao, IWServiceException {

		if (Validaciones.isTextoVacio(Integer.toString(idSolicitud))) {
			throw new IWServiceException(
					" El campo idSolicitud no debe ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(usuarioResponsable)) {
			throw new IWServiceException(
					" El campo usuarioResponsable no debe ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(usuarioGerente)) {
			throw new IWServiceException(
					" El campo usuarioGerente no debe ser nulo, ni una cadena de caracteres vacia");
		}
		Usuario usrGerente = usuarioDAO.obtenerUsuario(usuarioGerente);
		if (usrGerente == null) {
			throw new IWServiceException(
					"No tiene permisos para realizar esta accion");
		}
		if (!usrGerente.getRol().getNombre().equals("gerente")) {
			throw new IWServiceException(
					"No tiene permisos para realizar esta accion");
		}

		Usuario usrResponsable;
		Solicitud solicitud;

		usrResponsable = usuarioDAO.obtenerUsuario(usuarioResponsable);
		solicitud = solicitudDAO.obtenerSolicitud(idSolicitud);

		if (usrResponsable == null) {
			throw new IWServiceException(
					"El usuario al que le desea asignar la solicitud no se encuentra en el sistema");
		}
		if (usrResponsable.getRol().getNombre().equals("cliente")) {
			throw new IWServiceException(
					"No le puede asignar esta responsabilidad a un cliente");
		}

		if (solicitud == null) {
			throw new IWServiceException("No se encuentra la solicitud");
		}
		solicitud.setResponsable(usrResponsable);
		solicitudDAO.actualizar(solicitud);

		return solicitud;

	}

	/**
	 * Metodo para asignar la respuesta de la solicitud ingresada como
	 * parametro.
	 * 
	 * @param idSolicitud
	 *            identificador de la solicitud
	 * @param respuestaSolicitud
	 *            Campo de texto, contiene la respuesta por parte de la
	 *            organizacion al cliente.
	 * @param fechaRespuesta
	 *            fecha en que se respondio la solicitud
	 * @throws ExceptionDao
	 *             Manejar las excepciones del DAO.
	 * @throws IWServiceException
	 *             Manejar las excepciones de la l贸gica del negocio.
	 */
	public void responderSolicitud(int idSolicitud, String respuestaSolicitud,
			Date fechaRespuesta, String usuarioResponsable)
			throws ExceptionDao, IWServiceException {

		if (Validaciones.isTextoVacio(usuarioResponsable)) {
			throw new IWServiceException(
					" El campo usuarioResponsable no debe ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(respuestaSolicitud)) {
			throw new IWServiceException(
					" El campo respuesta no debe ser nulo, ni una cadena de caracteres vacia");
		}

		Usuario usrResponsable = usuarioDAO.obtenerUsuario(usuarioResponsable);

		if (usrResponsable == null) {
			throw new IWServiceException("No existe el usuario");
		}
		if (usrResponsable.getRol().getNombre().equals("cliente")) {
			throw new IWServiceException(
					"No tiene permisos para responder una solicitud");
		}

		Solicitud solicitud = solicitudDAO.obtenerSolicitud(idSolicitud);

		if (solicitud == null) {
			throw new IWServiceException("No se encuentra la solicitud");
		}

		solicitud.setRespuesta(respuestaSolicitud);
		solicitud.setFechaRespuesta(fechaRespuesta);
		solicitud.setResponsable(usrResponsable);

		solicitudDAO.actualizar(solicitud);

	}

	/**
	 * Metodo para obtener la lista de solicitudes
	 * 
	 * @return Lista de solicitudes
	 * @throws ExceptionDao
	 *             Manejar las excepciones del DAO.
	 * @throws IWServiceException
	 *             Manejar las excepciones de la l贸gica del negocio.
	 */
	public List<Solicitud> obtenerSolicitudes(String user) throws ExceptionDao,
			IWServiceException {
		Usuario usuario = usuarioDAO.obtenerUsuario(user);
		if (usuario == null) {
			throw new IWServiceException(
					"No existe usuario");
		}
		if (!usuario.getRol().getNombre().equals("gerente")) {
			throw new IWServiceException(
					"No tiene permisos para realizar esta acci锟絥");
		}
		return solicitudDAO.obtenerSolicitud();
	}

	/**
	 * Metodo para retornar una solicitud, dado su identificador
	 * 
	 * @param id
	 *            identificador de la solicitud
	 * @return Solicitud
	 * @throws ExceptionDao
	 *             Manejar las excepciones del DAO.
	 * @throws IWServiceException
	 *             Manejar las excepciones de la l贸gica del negocio.
	 */
	public Solicitud obtenerSolicitud(int id) throws MyException,
			IWServiceException {
		Solicitud solicitud;
		solicitud = solicitudDAO.obtenerSolicitud(id);
		if (solicitud == null) {
			throw new IWServiceException("No se encuentra la solicitud");
		}
		return solicitud;
	}

	/**
	 * Hacer seguimiento de fecha limite para responder una solicitud TERMINAR!!
	 * 
	 * @return lista de las solicitudes atrasadas.
	 */

	public List<Solicitud> seguimientoSolicitudes() throws MyException,
			IWServiceException {
		List<Solicitud> solicitudesAtrasadas = new ArrayList<Solicitud>();
		List<Solicitud> solicitudes;
		Date fechaActual = new Date();
		int dias = 0;
		Date fechaSolicitud;

		solicitudes = solicitudDAO.obtenerSolicitud();

		for (Solicitud solicitud : solicitudes) {
			if (solicitud.getRespuesta() == null) {
				fechaSolicitud = solicitud.getFechaSolicitud();
				dias = diferenciaEnDias(fechaActual, fechaSolicitud);
				if (dias >= 15) {
					solicitudesAtrasadas.add(solicitud);
				}

			}
		}
		return solicitudesAtrasadas;

	}

	/**
	 * Obtener los dias de diferencia entre dos fechas
	 * 
	 * @param fechaMayor
	 *            fecha mas actual
	 * @param fechaMenor
	 *            fecha mas antifua
	 * @return diferencia dias de diferencia entre las dos fechas
	 */
	private int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		long diferenciaDias;
		long dias;
		diferenciaDias = fechaMayor.getTime() - fechaMenor.getTime();
		dias = diferenciaDias / (1000 * 60 * 60 * 24);
		return (int) dias;
	}

	/**
	 * 
	 * @param idSolicitud
	 * @return Lista de solicitudes filtradas por tipo
	 * @throws IWServiceException
	 * @throws ExceptionDao
	 */
	public List<Solicitud> filtrarPorTipo(Integer idTipoSolicitud)
			throws IWServiceException, MyException {
		List<Solicitud> solicitudes;
		TipoSolicitud tipoS = tipoSolicitudDAO
				.obtenerTipoSolicitud(idTipoSolicitud);
		if (tipoS == null) {
			throw new IWServiceException("No existe el tipo de solicitud");
		}
		solicitudes = solicitudDAO.filtrarPorTipo(tipoS);
		return solicitudes;

	}

	public SolicitudDAO getSolicitudDAO() {
		return solicitudDAO;
	}

	public void setSolicitudDAO(SolicitudDAO solicitudDAO) {
		this.solicitudDAO = solicitudDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public TipoSolicitudDAO getTipoSolicitudDAO() {
		return tipoSolicitudDAO;
	}

	public void setTipoSolicitudDAO(TipoSolicitudDAO tipoSolicitudDAO) {
		this.tipoSolicitudDAO = tipoSolicitudDAO;
	}
}
