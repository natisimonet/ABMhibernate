package org.ada.test.abmHibernate.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.ada.test.abmHibernate.HibernateUtil;
import org.ada.test.abmHibernate.dto.PersonaEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class PersonaDAO {

	public void insertPersona(PersonaEntity per) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(per);
		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}

	public List<PersonaEntity> getAllPersona() {
		Session sesn = HibernateUtil.getSessionFactory().openSession();
		List<PersonaEntity> perso = new ArrayList<PersonaEntity>();
		try {
			perso = sesn.createQuery("From PersonaEntity").list();
			System.out.println("ID| Nombre| Edad | Fecha Nacimiento");
			for (PersonaEntity per : perso) {
				System.out
						.println(per.getPersonaId() + " " + per.getName() + " " + per.getEdad() + " " + per.getfNac());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}

		HibernateUtil.shutdown();
		return perso;
	}

	public void updatePersona(PersonaEntity per) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(per);
		session.getTransaction().commit();
		HibernateUtil.shutdown();

	}
}