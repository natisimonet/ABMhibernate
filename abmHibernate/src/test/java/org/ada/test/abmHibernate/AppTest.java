package org.ada.test.abmHibernate;

import java.util.Date;
import java.util.List;

import org.ada.abmHibernate.DAO.PersonaDAO;
import org.ada.abmHibernate.DAO.VentaDAO;
import org.ada.abmHibernate.dto.PersonaEntity;
import org.ada.abmHibernate.dto.VentaEntity;

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

	public void testAltaBajaYbusquedaNombre() {
		PersonaEntity per = new PersonaEntity();
		List<PersonaEntity> list = dao.getAllPersona();
		int total1 = list.size();
		String name = "PRUEBA";
		String fNac = "1990/08/10";
		int edad = 24;
		per.setEdad(edad);
		per.setfNac(fNac);
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

	public void testVentaAltaBajaYlistado() {
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

		List<VentaEntity> listPrueba = venDAO.getVentaxImporte(importe);

		VentaEntity ven2 = null;
		ven2 = listPrueba.get(0);

		venDAO.deleteVenta(ven2);
		List<VentaEntity> list3 = venDAO.getAllventa();
		int total3 = list3.size();
		assertTrue(total3 == total1);

	}

}