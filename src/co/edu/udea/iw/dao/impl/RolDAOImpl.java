package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.edu.udea.iw.dao.RolDAO;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.exception.MyException;

/**
 * Implementación de la interfaz RolDAO, 
 * en esta clase se hereda HibernateDaoSupport 
 * para adquirir la funcionalidad de Hibernate.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */
public class RolDAOImpl extends HibernateDaoSupport implements RolDAO{

	@Override
	public List<Rol> obtenerRoles() throws MyException {
		List<Rol> roles = new ArrayList<Rol>();
		Session session = null;
		
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(Rol.class);
			roles = criteria.list();
		} catch (HibernateException e) {
			throw new MyException(e);
		}
		return roles;
	}

	@Override
	public Rol obtenerRol(Integer id) throws MyException {
		Rol rol=null;
		Session session=null;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			rol = (Rol)session.get(Rol.class, id);
		} catch (HibernateException e) {
			throw new MyException(e);
		}
		return rol;
	}

}