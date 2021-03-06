package implDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import dao.RolDAO;
import dto.Empleado;
import dto.Rol;
import dto.Usuario;
import exception.MyException;

/**
 * Implementación de la interfaz RolDAO,
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */
public class RolDAOImpl implements RolDAO {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Rol> obtenerRoles() throws MyException {
		List<Rol> roles = new ArrayList<Rol>();
		session = null;
		Criteria criteria = null;

		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(Rol.class);
			criteria.addOrder(Order.asc("descripcion"));

			roles = criteria.list();

		} catch (HibernateException e) {
			throw new MyException(e);
		}
		return roles;
	}

	@Override
	public Rol obtenerRol(Integer id) throws MyException {
		Rol rol = new Rol();
		session = null;
		try {
			session = sessionFactory.getCurrentSession();
			rol = (Rol) session.get(Rol.class, id);
		} catch (HibernateException e) {
			throw new MyException("Ocurrio un error consultando los roles", e);
		}
		return rol;
	}

}
