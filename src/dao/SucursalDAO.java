package dao;

/**
* @author Yuri Quejada
* @author Daniel Pelaez
* @author Jean Herrera
* @version 1.0
*/

import java.util.List;

import dto.Sucursal;
import exception.MyException;


/**
 * DAO Interfaz que define los metodos que va a 
 * proveer  la clase Sucursal.
 * @version 1.0
 */
public interface SucursalDAO {

	/**
	 * Obtiene la lista de sucursales almacenadas en la tabla
	 * Sucursal de la base de datos.
	 * 
	 * @return lista de las sucursales.
	 * @throws ExceptionDao cuando ocurre cualquier error en la comunicaci�n con la BD.
	 */
	public List<Sucursal> obtenerSucursales() throws MyException;

	/**
	 * Obtiene la sucursal que corresponda al identificador ingresado como par�metro.
	 * 
	 * @param id identificador de la sucursal.
	 * @return Sucursal con toda su informaci�n.
	 * @throws ExceptionDao cuando ocurre cualquier error en la comunicaci�n con la BD.
	 */
	public Sucursal obtenerSucursal(Integer id) throws MyException;

}
