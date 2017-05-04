package co.edu.udea.iw.bl.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.SolicitudBL;

import co.edu.udea.iw.dto.Solicitud;
import co.edu.udea.iw.exception.MyException;
import co.edu.udea.iw.exception.IWServiceException;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configuracion.xml")
public class SolicitudBLTest {
	
	@Autowired
	SolicitudBL solicitudBL;

	@Test
	public void guardarSolicitud() {
		
		try {
			Solicitud solicitud=solicitudBL.guardarSolicitud("descr", "Alta" , 2, new Date(), new Date(),
					"","123" , 1, "456");
			System.out.println("Se guardó correctamente la solicitud con la siguiente información:");
			System.out.println("ID de Solicitud: "+solicitud.getId());
			System.out.println("Descripción: "+solicitud.getDescripcion());
			System.out.println("Complejidad: "+solicitud.getComplejidad());
			System.out.println("Tipo de Solicitud: "+solicitud.getTipoSolicitud());
			System.out.println("Fecha de Solicitud: "+solicitud.getFechaSolicitud());
			System.out.println("Fecha de Respuesta: "+solicitud.getFechaRespuesta());
			System.out.println("Respuesta: "+solicitud.getRespuestaSolicitud());
			System.out.println("Cliente: "+solicitud.getCliente().getNombre());
			System.out.println("Sucursal: "+solicitud.getSucursal());
			System.out.println("Empleado: "+solicitud.getResponsable().getNombre());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
//	@Test
	public void mostrarSolicitudes(){
		try {
			List<Solicitud> solicitudes=(List<Solicitud>) solicitudBL.obtenerSolicitud(1);
			if(solicitudes.isEmpty()){
				System.out.println("No hay nada para mostrar");
			}
			for(Solicitud solicitud:solicitudes){
				System.out.println("ID solicitud: "+solicitud.getId());
				System.out.println("Desripción: "+solicitud.getDescripcion());
				System.out.println("Cliente: "+solicitud.getCliente().getNombre());
				System.out.println();
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
//	@Test
	public void responderSolicitud(){
		
		try {
			solicitudBL.responderSolicitud(1, "No podemos responder esta solicitud", new Date(),"diana");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
//	@Test
	public void asignarResponsable(){
		
		try {
			Solicitud solicitid=solicitudBL.asignarResponsable(1, "diana","bojaca");
			System.out.println("Se asignó correctamente el responsable...");
			System.out.println("ID de Solicitud: "+solicitid.getId());
			System.out.println("Descripcion: "+solicitid.getDescripcion());
			System.out.println("Responsable: "+solicitid.getResponsable().getNombre()+" "+
					solicitid.getResponsable().getApellido());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		} 
	}
	
//	@Test
	public void solicitudesAtrasadas(){
		try {
			for(Solicitud solicitud:solicitudBL.seguimientoSolicitudes()){
				
				System.out.println("Id Solicitud: "+solicitud.getId());
				System.out.println("Fecha de Solicitud:" +solicitud.getFechaSolicitud());
				System.out.println();
				
			}
		} catch (MyException e) {
			e.printStackTrace();
		} 
	}
	
	
//	@Test
	public void filtrarPorTipo(){
		
		try {
			for(Solicitud solicitud:solicitudBL.filtrarPorTipo(1)){
				System.out.println("ID Solicitud: "+solicitud.getId());
				System.out.println("Tipo Solicitud: "+solicitud.getTipoSolicitud().getDescripcion());
				System.out.println();
				
			}
		} catch (IWServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	

}