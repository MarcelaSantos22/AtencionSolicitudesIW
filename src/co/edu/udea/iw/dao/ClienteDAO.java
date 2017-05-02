package co.edu.udea.iw.dao;

/**
* @author Yuri Quejada
* @author Daniel Pelaez
* @author Jean Herrera
* @version 1.0
*/
import java.util.List;

import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.exception.MyException;

public interface ClienteDAO {
	/**
	 * Método que nos permite obtener todos los clientes.
	 * 
	 * @return Lista con los clientes.
	 * @throws MyException
	 */
	public List<Cliente> listarClientes() throws MyException;

	/**
	 * Método que nos permite obtener un ï¿½nico cliente.
	 * 
	 * @param Cédula con la cual se realizará la búsqueda.
	 * @return Retorna el cliente buscado.
	 * @throws MyException
	 */
	public Cliente obtenerCliente(int cedula) throws MyException;

	/**
	 * Método para obtener un único cliente por medio del correo
	 * @param correo
	 * @return
	 * @throws MyException
	 */
	public Cliente obtenerCliente(String email) throws MyException;
	
	/**
	 * Método que nos permite crear un nuevo cliente.
	 * 
	 * @param cliente que se va a crear en el sistema y almacenar en la BD.
	 * @throws MyException
	 */
	public void crearCliente(Cliente cliente) throws MyException;

	/**
	 * Método que nos permite eliminar un cliente de la base de datos. 
	 * @param cliente que se va a eliminar.
	 * @throws MyException
	 */
	public void eliminarCliente(Cliente cliente) throws MyException;

	/**
	 * Método para modificar un cliente existente.
	 * @param cliente que se desea modificar.
	 * @return 
	 * @throws MyException
	 */
	public Cliente modificarCliente(Cliente cliente) throws MyException;
}
