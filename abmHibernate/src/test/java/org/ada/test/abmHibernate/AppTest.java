package org.ada.test.abmHibernate;

import java.util.List;

import org.ada.abmHibernate.DAO.PersonaDAO;
import org.ada.abmHibernate.dto.PersonaEntity;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    public void AppTest () {
    System.out.println("Test 1");
    PersonaDAO dao = new PersonaDAO();
    List<PersonaEntity> list = dao.getAllPersona();    
    boolean tieneRegistros = list.size() > 0;
    assertTrue(tieneRegistros);
    	
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
