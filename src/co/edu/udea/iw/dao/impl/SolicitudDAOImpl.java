package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.ClienteDAO;
import co.edu.udea.iw.dao.EmpleadoDAO;
import co.edu.udea.iw.dao.SolicitudDAO;
import co.edu.udea.iw.dto.Empleado;
import co.edu.udea.iw.dto.Solicitud;
import co.edu.udea.iw.dto.TipoSolicitud;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Implementación de la interfaz SolicitudDAO. 
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */
@Transactional
public class SolicitudDAOImpl extends HibernateDaoSupport implements SolicitudDAO
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> listarSolicitudes() throws MyException {
	List<Solicitud> solicitudes = new ArrayList<Solicitud>();
		
		try{
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			Criteria criteria = session.createCriteria(Solicitud.class);
			
			solicitudes = criteria.list();
		}catch(HibernateException e){
			throw new MyException(e);
		}
		return solicitudes;
	}

	@Override
	public Solicitud obtenerSolicitud(int id) throws MyException{
		Solicitud solicitud = null;
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			solicitud = (Solicitud)session.get(Solicitud.class, id);
			
		}catch(HibernateException e){
			throw new MyException(e);
			
	}
	
	return solicitud;

	}


	@Override
	public void crearSolicitud(Solicitud solicitud) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.save(solicitud);
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public void eliminarSolicitud(Solicitud solicitud) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			session.delete(solicitud);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public void modificarSolicitud(Solicitud solicitud) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			session.update(solicitud);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}
	
	
	@Override
	public List<Solicitud> filtrarSolicitudes(TipoSolicitud tipoSolicitud) throws MyException {
		Session session;
		List<Solicitud> solicitudes;
		try {
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(Solicitud.class);
			criteria.add(Restrictions.eq("tipoSolicitud", tipoSolicitud));
			solicitudes = criteria.list();
		} catch (HibernateException e) {
			throw new MyException(e);
		}
		return solicitudes;
	}	

}
