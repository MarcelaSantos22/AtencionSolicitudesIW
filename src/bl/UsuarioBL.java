package bl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.EmpleadoDAO;
import dao.RolDAO;
import dao.UsuarioDAO;
import dto.Empleado;
import dto.Rol;
import dto.Usuario;
import exception.IWServiceException;
import exception.MyException;
import validation.Validaciones;

/**
 * Clase encargada de la logica de negocio para la clase Usuario. Clase
 * transaccional con la BD.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

@Transactional // Anotacion requerida para informarle a Spring que esta clase
// maneja transacciones
public class UsuarioBL {

	/**
	 * Beans para manejar los Dao
	 */
	private UsuarioDAO usuarioDAO;
	private RolDAO rolDAO;

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public RolDAO getRolDAO() {
		return rolDAO;
	}

	public void setRolDAO(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}

	/**
	 * Metodo para guardar un nuevo usuario.
	 * 
	 * @param user
	 *            username del usuario
	 * @param pws
	 *            password del usuario
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 * @throws IWServiceException
	 *             Manejar las excepciones de la l�gica del negocio.
	 */
	public String guardarUsuario(String user, String pws, String rolUser) throws MyException, IWServiceException {
		Usuario usuario = null;
		Rol rol = null;

		if (Validaciones.isTextoVacio(user)) {
			throw new IWServiceException("El campo username no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(pws)) {
			throw new IWServiceException("El campo password no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(rolUser)) {
			throw new IWServiceException("El campo rol no puede ser nulo, ni una cadena de caracteres vacia");
		}

		// Validar que el usuario no exista
		usuario = usuarioDAO.obtener(user);
		if (usuario != null) {
			throw new MyException("El usuario ya existe en el sistema, elija otro");
		}		
		
		// Asignar rol
		if (rolUser.equals("Cliente")) {
			rol = rolDAO.obtenerRol(2);
		}
		else if (rolUser.equals("Empleado")) {
			rol = rolDAO.obtenerRol(3);
		}
		else if (rolUser.equals("Gerente")) {
			rol = rolDAO.obtenerRol(1);
		}
		else {
			throw new MyException("EL rol no existe");
		}

		usuario = new Usuario();

		usuario.setUser(user);
		usuario.setPassword(pws);
		usuario.setRol(rol);

		usuarioDAO.guardar(usuario);
		return "Se ha creado el usuario exitosamente";

	}

	/**
	 * Metodo para verificar el usuario y contrasenia con la base de datos
	 * 
	 * @param user
	 *            identificador del usuario
	 * @param password
	 *            contrasenia del usuario
	 * @return Usuario
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 * @throws IWServiceException
	 *             Manejar las excepciones de la l�gica del negocio.
	 */
	public Usuario autenticarUsuario(String user, String password) throws MyException, IWServiceException {

		Usuario usuario = null;
		usuario = usuarioDAO.obtener(user);

		if (usuario == null) {
			throw new IWServiceException("Usuario o Contrase�a Incorrecta");
		}

		if (!usuario.getPassword().equals(password)) {
			throw new IWServiceException("Usuario o Contrase�a Incorrecta");
		}
		return usuario;

	}

	/**
	 * Metodo para obtener el listado de usuarios
	 * 
	 * @return Lista de usuarios
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 */
	public List<Usuario> obtener() throws MyException {
		return usuarioDAO.obtenerUsuarios();
	}

	/**
	 * Metodo para obtener el usuario del identificador enviado como parametro
	 * 
	 * @param user
	 *            identificador del usuario
	 * @return Usuario
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 * @throws IWServiceException
	 *             Manejar las excepciones de la logica de negocio.
	 */
	public Usuario obtener(String user) throws MyException, IWServiceException {
		if (Validaciones.isTextoVacio(user)) {
			throw new IWServiceException(
					"El campo user del cliente no puede ser nulo, ni una cadena de caracteres vacia");
		}

		return usuarioDAO.obtener(user);
	}

}
