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
import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.exception.MyException;

@Transactional
public class ClienteDAOImpl extends HibernateDaoSupport implements ClienteDAO {

	@Override
	public List<Cliente> listarClientes() throws MyException {
	List<Cliente> clientes = new ArrayList<Cliente>();
	Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			Criteria criteria = session.createCriteria(Cliente.class);
			
			clientes =criteria.list();
		}catch(HibernateException e){
			throw new MyException(e);
		}
		return clientes;
	}

	@Override
	public Cliente obtenerCliente(int identificacion) throws MyException{
		Cliente cliente = null;
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			cliente = (Cliente)session.get(Cliente.class, identificacion);
		}catch(HibernateException e){
			throw new MyException(e);
		}
	
		return cliente;

	}

	@Override
	public Cliente obtenerCliente(String correo) throws MyException{
		Cliente cliente = null;
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(Cliente.class)
					.add(Restrictions.eq("correo", correo));
			cliente = (Cliente) criteria.uniqueResult(); 
		}catch(HibernateException e){
			throw new MyException(e);
			
	}
	return cliente;
	}
	
	@Override
	public void crearCliente(Cliente cliente) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.save(cliente);
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public void eliminarCliente(Cliente cliente) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			session.delete(cliente);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public Cliente modificarCliente(Cliente cliente) throws MyException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			session.update(cliente);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
		return cliente;
		
	}

}
