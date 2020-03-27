package org.ada.test.abmHibernate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.ada.abmHibernate.DAO.PersonaDAO;
import org.ada.abmHibernate.DAO.VentaDAO;
import org.ada.abmHibernate.dto.PersonaEntity;
import org.ada.abmHibernate.dto.VentaEntity;

import hibernate.util.DateUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

	PersonaDAO dao = new PersonaDAO();

	public void testListado() {

		List<PersonaEntity> list = dao.getAllPersona();
		boolean tieneRegistros = list.size() > 0;
		assertTrue(tieneRegistros);

	}

	public void testAltaBajaYbusquedaNombre() throws ParseException {
		PersonaEntity per = new PersonaEntity();
		List<PersonaEntity> list = dao.getAllPersona();
		int total1 = list.size();
		String name = "PRUEBA";
		String fNac = "1990/08/10";
		int edad = 24;
		per.setEdad(edad);
		
		Date fechaparse = DateUtil.parse(DateUtil.PATTERN_Y4_M2_D2, fNac);
		per.setfNac(fechaparse);
		per.setName(name);
		dao.insertOrUpdatePersona(per);
		List<PersonaEntity> list2 = dao.getAllPersona();
		int total2 = list2.size();
		assertTrue(total2 > total1);

		List<PersonaEntity> listPrueba = dao.getPersonaXnombre(name);

		PersonaEntity perso = null;
		perso = listPrueba.get(0);

		dao.deletePersona(perso);
		List<PersonaEntity> list3 = dao.getAllPersona();
		int total3 = list3.size();
		assertTrue(total3 == total1);

	}

	public void testPersonaXid() {
		PersonaEntity per = new PersonaEntity();
		per = dao.getPersona(36);
		assertTrue(per.getName() != null & per.getEdad() != 0 & per.getfNac() != null);
	}

	public void testVentaAltaBajaYlistado() { // se carga la venta en negativo para anularla.
		VentaEntity ven = new VentaEntity();
		VentaDAO venDAO = new VentaDAO();
		PersonaEntity per = new PersonaEntity();
		List<VentaEntity> list = venDAO.getAllventa();
		int total1 = list.size();
		per = dao.getPersona(36);
		ven.setPersonaEntity(per);
		Date fecha = new Date();
		ven.setFechaVenta(fecha);
		float importe = 999999999;
		ven.setImporte(importe);
		venDAO.insertVenta(ven);
		List<VentaEntity> list2 = venDAO.getAllventa();
		int total2 = list2.size();
		assertTrue(total2 > total1);

		VentaEntity ven2 = new VentaEntity();
		ven2.setPersonaEntity(per);
		ven2.setFechaVenta(fecha);
		float importe2 = -999999999;
		ven2.setImporte(importe2);
		venDAO.insertVenta(ven2);
		List<VentaEntity> list3 = venDAO.getAllventa();
		int index = list3.size() -1;
		ven = list3.get(index);
		int id = ven.getVentaId();
		VentaEntity ven3 = new VentaEntity();
		ven3 = venDAO.getVentaXid(id);
		float importe3 = ven3.getImporte();
		assertTrue(importe3 == (importe * -1));

		//Otra opción es que la venta de Test sea con importe 0 o 0.01 y
		//no sería necesario borrarla :D.
	}

}