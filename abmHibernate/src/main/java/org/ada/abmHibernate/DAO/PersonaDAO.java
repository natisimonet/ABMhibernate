package org.ada.abmHibernate.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.ada.abmHibernate.dto.PersonaEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class PersonaDAO {

	public void insertOrUpdatePersona(PersonaEntity per) {
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

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}

		HibernateUtil.shutdown();
		return perso;
	}

	public PersonaEntity getPersona(int personaId) {
		Session sesn = HibernateUtil.getSessionFactory().openSession();
		PersonaEntity perso = null;
		List<PersonaEntity> perso1 = new ArrayList<PersonaEntity>();
		try {
			perso1 = sesn.createQuery("From PersonaEntity Where ID =" + personaId).list();
			if (!perso1.isEmpty()) {
				perso = perso1.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}

		HibernateUtil.shutdown();
		return perso;
	}

	public List<PersonaEntity> getPersonaXnombre(String name) { 
		Session sesn = HibernateUtil.getSessionFactory().openSession();
		PersonaEntity perso = null;
		List<PersonaEntity> perso1 = new ArrayList<PersonaEntity>();
		try {
			perso1 = sesn.createQuery("From PersonaEntity Where NOMBRE ='" + name + "'" ).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}

		HibernateUtil.shutdown();
		return perso1;
	}

	public void deletePersona(PersonaEntity per) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(per);
		session.getTransaction().commit();
		HibernateUtil.shutdown();

	}
}