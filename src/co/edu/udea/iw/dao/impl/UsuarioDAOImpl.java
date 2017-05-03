package co.edu.udea.iw.dao.impl; 

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Usuario;
import exception.MyException;

/**
 * Implementación de los metodos
 *  de la interfaz UsuarioDAO.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

public class UsuarioDAOImpl implements UsuarioDAO{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Usuario obtener(String user) throws MyException {
		Session session = null;
		Usuario usuario = new Usuario();

		try {
			session = sessionFactory.getCurrentSession();
			usuario = (Usuario) session.get(Usuario.class, user);

		} catch (HibernateException e) {
			throw new MyException("Ocurrio un error consultando el usuario", e);

		} 
		return usuario;
	}

}