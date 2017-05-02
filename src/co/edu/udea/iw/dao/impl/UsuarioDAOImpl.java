package co.edu.udea.iw.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase Hibernate de Usuario que implementa la interfaz UsuarioDao
 * @author Jean Herrera 
 */
public class UsuarioDAOImpl implements UsuarioDAO {

	private SessionFactory sessionFactory; 
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<Usuario> obtener() throws MyException {
		Session session = null;
		List<Usuario> usuarios = null;
		
		try {
			session = sessionFactory.openSession();
			Criteria criteria =  session.createCriteria(Usuario.class);
			usuarios = criteria.list();
		} catch (HibernateException	 e) {
			throw new MyException(e);
		}
		
		return usuarios;
	}

	@Override
	public void guardar(Usuario usuario) throws MyException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(usuario); 
			transaction.commit();
		} catch (HibernateException	 e) {
			throw new MyException(e);
		}
		
	}

	@Override
	public void actualizar(Usuario usuario) throws MyException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(usuario);
			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new MyException(e);
		}
		
	}
	

}