package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.edu.udea.iw.dao.TipoSolicitudDAO;
import co.edu.udea.iw.dto.TipoSolicitud;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Implementación de la interfaz TipoSolicitudDao, 
 * esta se basara en Hibernate,
 * en esta clase se hereda de HibernateDaoSupport 
 * para adquirir la funcionalidad de Hibernate.
 * 
 * @author Yuri Quejada
 * @author Jean Herrera
 * @author Daniel Pelaez
 * @version 1.0
 */
public class TipoSolicitudDAOImpl extends HibernateDaoSupport implements TipoSolicitudDAO{

	@Override
	public List<TipoSolicitud> obtenerTipoSolicitudes() throws MyException {
		List<TipoSolicitud> tiposolicitudes = new ArrayList<TipoSolicitud>();
		Session session = null;
		
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(TipoSolicitud.class);
			tiposolicitudes = criteria.list();
		} catch (DataAccessException e) {
			throw new MyException(e);
		}
		return tiposolicitudes;
	}

	@Override
	public TipoSolicitud obtenerTipoSolicitud(Integer id) throws MyException {
		TipoSolicitud tiposolicitud;
		Session session;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			tiposolicitud = (TipoSolicitud)session.get(TipoSolicitud.class, id);
		} catch (DataAccessException e) {
			throw new MyException(e);
		}
		return tiposolicitud;
	}

}