package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.edu.udea.iw.dao.SucursalDAO;
import co.edu.udea.iw.dto.Sucursal;
import co.edu.udea.iw.dto.TipoSolicitud;
import co.edu.udea.iw.exception.MyException;

/**
 * Implementación de la interfaz SucursalDAO, 
 * en esta clase se hereda HibernateDaoSupport 
 * para adquirir la funcionalidad de Hibernate.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */
public class SucursalDAOImpl extends HibernateDaoSupport implements SucursalDAO {

	@Override
	public List<Sucursal> obtenerSucursales() throws MyException {
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		Session session = null;
		
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(Sucursal.class);
			sucursales = criteria.list();
		} catch (DataAccessException e) {
			throw new MyException(e);
		}
		return sucursales;
	}

	@Override
	public Sucursal obtenerSucursal(Integer id) throws MyException {
		Sucursal sucursal;
		Session session;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			sucursal = (Sucursal)session.get(Sucursal.class, id);
		} catch (DataAccessException e) {
			throw new MyException(e);
		}
		return sucursal;
	}

}