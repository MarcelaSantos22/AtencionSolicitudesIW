package dao;

import java.util.List;

import dto.Rol;
import exception.MyException;


/**
 * DAO Interfaz que define los metodos que 
 * va a proveer el la clase Rol.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */
public interface RolDAO {

	/**
	 * Obtiene la lista de roles almacenados en la tabla Rol de la base
	 * de datos.
	 * 
	 * @return lista de roles.
	 * @throws ExceptionDao cuando ocurre cualquier error en la comunicaci�n con la BD.
	 */
	public List<Rol> obtenerRoles() throws MyException;

	/**
	 * Obtiene el rol que corresponda al identificador ingresado como
	 * par�metro.
	 * 
	 * @param id identificador del rol.
	 * @return Rol con toda su informaci�n.
	 * @throws ExceptionDao cuando ocurre cualquier error en la comunicaci�n con la BD.
	 */
	public Rol obtenerRol(Integer id) throws MyException;

}
