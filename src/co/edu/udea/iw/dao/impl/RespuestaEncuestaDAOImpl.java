package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.RespuestaEncuestaDAO;
import co.edu.udea.iw.dto.RespuestaEncuesta;
import co.edu.udea.iw.exception.MyException;

/**
 * Implementación de la interfaz RespuestaEncuestaDAO, 
 * en esta clase se hereda de HibernateDaoSupport 
 * para adquirir la funcionalidad de Hibernate.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

@Transactional
public class RespuestaEncuestaDAOImpl extends HibernateDaoSupport implements RespuestaEncuestaDAO {

	@Override
	public List<RespuestaEncuesta> listarRespuestas() throws MyException {
	List<RespuestaEncuesta> respuestas = new ArrayList<RespuestaEncuesta>();
		
		try{
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			Criteria criteria = session.createCriteria(RespuestaEncuesta.class);	
			respuestas = criteria.list();
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		return respuestas;
	}

	@Override
	public RespuestaEncuesta obtenerRespuesta(int id) throws MyException{
		RespuestaEncuesta respuesta = null;
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			respuesta = (RespuestaEncuesta)session.get(RespuestaEncuesta.class, id);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		return respuesta;

	}


	@Override
	public void crearRespuesta(RespuestaEncuesta respuesta) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.save(respuesta);
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public void eliminarRespuesta(RespuestaEncuesta respuesta) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			session.delete(respuesta);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public void modificarRespuesta(RespuestaEncuesta respuesta) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			session.update(respuesta);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}
	
}
