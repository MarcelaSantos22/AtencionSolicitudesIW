package implDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.RespuestaEncuestaDAO;
import dto.RespuestaEncuesta;
import exception.MyException;

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

public class RespuestaEncuestaDAOImpl implements RespuestaEncuestaDAO {

	
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
	public List<RespuestaEncuesta> listarRespuestas() throws MyException {
	List<RespuestaEncuesta> respuestas = new ArrayList<RespuestaEncuesta>();
		
	session = null;

	
		try{
			session = sessionFactory.getCurrentSession();
			
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
		session = null;
		try{
			session = sessionFactory.getCurrentSession();
			
			respuesta = (RespuestaEncuesta)session.get(RespuestaEncuesta.class, id);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		return respuesta;

	}


	@Override
	public void crearRespuesta(RespuestaEncuesta respuesta) throws MyException {
		session = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.save(respuesta);
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public void eliminarRespuesta(RespuestaEncuesta respuesta) throws MyException {
		session = null;
		try{
			session = sessionFactory.getCurrentSession();
			
			session.delete(respuesta);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public void modificarRespuesta(RespuestaEncuesta respuesta) throws MyException {
		session = null;
		try{
			session = sessionFactory.getCurrentSession();
			
			session.update(respuesta);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}
	
}
