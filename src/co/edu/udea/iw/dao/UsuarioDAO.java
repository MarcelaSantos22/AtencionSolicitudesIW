package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;
/**
 * Interface de Usuario 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 */
public interface UsuarioDAO {

	/** Obtiene todos los usuarios de la base de datos
	 * 
	 * @return
	 * @throws MyDaoExeption
	 */
	public List<Usuario> obtener() throws MyException;
	
	
	/**
	 * Guarda un nuevo usuario en la bd
	 * @param usuario
	 * @throws MyException
	 */
	public void guardar(Usuario usuario) throws MyException;
	
	
	/**
	 * Actualiza los datos de un usuario en la bd
	 * @param usuario
	 * @throws MyException
	 */
	public void actualizar(Usuario usuario) throws MyException;	
	
}
