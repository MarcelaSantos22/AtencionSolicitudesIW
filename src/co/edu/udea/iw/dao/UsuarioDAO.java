package co.edu.udea.iw.dao;

import co.edu.udea.iw.dto.Usuario;
import exception.MyException;

/**
 * DAO
 * Interfaz que define los metodos que va a proveer
 * la clase Usuario.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

public interface UsuarioDAO {
	
	/**
	 * Obtiene el usuario que corresponda al user ingresado como par�metro.
	 * 
	 * @param user, username del usuario.
	 * @return usuario con toda su informaci�n.
	 * @throws MyException cuando ocurre cualquier error en la comunicaci�n con la BD.
	 */
	public Usuario obtener(String user) throws MyException;

}