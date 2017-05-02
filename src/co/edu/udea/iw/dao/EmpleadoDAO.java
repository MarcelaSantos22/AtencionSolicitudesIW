package co.edu.udea.iw.dao;

/**
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

import java.util.List;

import co.edu.udea.iw.dto.Empleado;
import co.edu.udea.iw.exception.MyException;

public interface EmpleadoDAO {
	/**
	 * Método que nos permite obtener todos los empleados.
	 * 
	 * @return Lista con los empleados.
	 * @throws MyException
	 */
	public List<Empleado> listarEmpleados() throws MyException;

	/**
	 * Método que nos permite obtener un único empleado.
	 * 
	 * @param cédula con la cual se realizará la búsqueda.
	 *            
	 * @return Retorna el empleado buscado.
	 * @throws MyException
	 */
	public Empleado obtenerEmpleado(int cedula) throws MyException;

	/**
	 * Método que nos permite crear un nuevo empleado.
	 * 
	 * @param empleado que se va a crear en el sistema y almacenar en la BD.
	 * @throws MyException
	 */
	public void crearEmpleado(Empleado empleado) throws MyException;

	/**
	 * Método que nos permite eliminar un empleado de la base de datos. 
	 * @param empleado que se va a eliminar.
	 * @throws MyException
	 */
	public void eliminarEmpleado(Empleado empleado) throws MyException;

	/**
	 * Método para modificar un empleado existente.
	 * @param empleado que se desea modificar.
	 * @throws MyException
	 */
	public void modificarEmpleado(Empleado empleado) throws MyException;
	
}
