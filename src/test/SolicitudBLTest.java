package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import bl.SolicitudBL;
import dto.Solicitud;
import exception.MyException;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configuracionSpring.xml")
public class SolicitudBLTest {
	
	@Autowired
	SolicitudBL solicitudBL;

	@Test
	public void guardarSolicitud() throws exception.MyException {
		
		try {
			Solicitud solicitud=solicitudBL.guardarSolicitud("descr", 2, new Date(),"1146437892");
			System.out.println("Se guard� correctamente la solicitud con la siguiente informaci�n:");
			System.out.println("ID de Solicitud: "+solicitud.getId());
			System.out.println("Descripci�n: "+solicitud.getDescripcion());
			System.out.println("Tipo de Solicitud: "+solicitud.getTipoSolicitud());
			System.out.println("Fecha de Solicitud: "+solicitud.getFechaSolicitud());
			System.out.println("Cliente: "+solicitud.getCliente().getNombre());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mostrarSolicitudes() throws exception.MyException{
		try {
			List<Solicitud> solicitudes=solicitudBL.obtenerSolicitudes("1146437892");
			if(solicitudes.isEmpty()){
				System.out.println("No hay nada para mostrar");
			}
			for(Solicitud solicitud:solicitudes){
				System.out.println("ID solicitud: "+solicitud.getId());
				System.out.println("Desripci�n: "+solicitud.getDescripcion());
				System.out.println("Cliente: "+solicitud.getCliente().getNombre());
				System.out.println();
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Test
	public void responderSolicitud() throws exception.MyException{
		
		try {
			solicitudBL.responderSolicitud(1, "No podemos responder esta solicitud", new Date(),"Marcela18");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void asignarResponsable() throws MyException{
		
		try {
			Solicitud solicitid=solicitudBL.asignarResponsable(1,"Marcela18","Jean20");
			System.out.println("Se asign� correctamente el responsable...");
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
	
	@Test
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
	
	@Test
	public void nivelSatisfaccionCliente(){
		try {
			System.out.println();
		}catch (MyException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void infoTiemposRespuesta(){
		
		try{
			for(Integer solicitud:solicitudBL.infoTiemposRespuestas()){
				System.out.println("Tiempo respuesta: " );
			}
			
		}catch (MyException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void filtrarPorTipo(){
		
		try {
			for(Solicitud solicitud:solicitudBL.filtrarPorTipo(1)){
				System.out.println("ID Solicitud: "+solicitud.getId());
				System.out.println("Tipo Solicitud: "+solicitud.getTipoSolicitud().getDescripcion());
				System.out.println();
				
			}
		
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	

}