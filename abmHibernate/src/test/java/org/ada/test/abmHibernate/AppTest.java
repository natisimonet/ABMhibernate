package org.ada.test.abmHibernate;

import java.util.List;

import org.ada.abmHibernate.DAO.PersonaDAO;
import org.ada.abmHibernate.dto.PersonaEntity;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class AppTest extends TestCase {
	
	PersonaDAO dao = new PersonaDAO();
	PersonaEntity per = new PersonaEntity();

	public void testListado() {
		System.out.println("Test 1");
		List<PersonaEntity> list = dao.getAllPersona();
		boolean tieneRegistros = list.size() > 0;
		assertTrue(tieneRegistros);

	}

	public void testAltaBajaYbusquedaNombre() {
		System.out.println("test 2");
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
		
		List <PersonaEntity> listPrueba = dao.getPersonaXnombre(name);
	
		PersonaEntity perso = null;
		perso = listPrueba.get(0);
		
		dao.deletePersona(perso);
		List<PersonaEntity> list3 = dao.getAllPersona();
		int total3 = list3.size();
		assertTrue(total3 == total1);
	
	}
	
	public void testPersonaXid () {
		per = dao.getPersona(9);
		assertTrue(per == null);
	}
}