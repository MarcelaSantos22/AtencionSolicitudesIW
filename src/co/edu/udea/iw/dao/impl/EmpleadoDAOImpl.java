package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.EmpleadoDAO;
import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.dto.Empleado;
import co.edu.udea.iw.exception.MyException;

@Transactional
public class EmpleadoDAOImpl extends HibernateDaoSupport implements EmpleadoDAO {

	@Override
	public List<Empleado> listarEmpleados() throws MyException {
	List<Empleado> empleados = new ArrayList<Empleado>();
		
		try{
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			Criteria criteria = session.createCriteria(Empleado.class);
			
			empleados = criteria.list();
		}catch(HibernateException e){
			throw new MyException(e);
		}
		return empleados;
	}

	@Override
	public Empleado obtenerEmpleado(int identificacion) throws MyException{
		Empleado empleado = null;
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			empleado = (Empleado)session.get(Empleado.class, identificacion);
			
		}catch(HibernateException e){
			throw new MyException(e);
			
		}
		return empleado;

	}

	@Override
	public void crearEmpleado(Empleado empleado) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.save(empleado);
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public void eliminarEmpleado(Empleado empleado) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			session.delete(empleado);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public void modificarEmpleado(Empleado empleado) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			session.update(empleado);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}
	
}
