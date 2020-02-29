package org.ada.test.abmHibernate;

import org.ada.test.abmHibernate.dto.PersonaEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class TestHibernate {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// Add new Employee object
		System.out.println("Ingrese una nueva persona");
		PersonaEntity emp = new PersonaEntity();
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese Nombre");
		String name = scan.next();
		emp.setName(name);
		System.out.println("Ingrese Fecha de Nacimiento en Formato YYYY-MM-DD");
		String fnac = scan.next();

		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
		int edad = 0;
		try {
			Date fechaNac = sfd.parse(fnac);
			edad = calcularEdad(fechaNac);

		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		emp.setEdad(edad);
		emp.setfNac(fnac);

		session.saveOrUpdate(emp);
		// session.delete(emp);

		session.getTransaction().commit();

		listUsers();
		HibernateUtil.shutdown();
	}



	@SuppressWarnings("unchecked")
	private static void listUsers() {
		Session sesn = HibernateUtil.getSessionFactory().openSession();
		List<PersonaEntity> perso = new ArrayList<PersonaEntity>();
		try {
			perso = sesn.createQuery("From PersonaEntity").list();
			for (PersonaEntity per : perso) {
				System.out.println(per.getName() + " " + per.getEdad() + " " + per.getfNac());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}

	}
	
	private static int calcularEdad(Date fechaNac) {
		GregorianCalendar gc = new GregorianCalendar();
		GregorianCalendar hoy = new GregorianCalendar();
		gc.setTime(fechaNac);
		int anioActual = hoy.get(Calendar.YEAR);
		int anioNacim = gc.get(Calendar.YEAR);
		int mesActual = hoy.get(Calendar.MONTH);
		int mesNacim = gc.get(Calendar.MONTH);
		int diaActual = hoy.get(Calendar.DATE);
		int diaNacim = gc.get(Calendar.DATE);
		int dif = anioActual - anioNacim;
		if (mesActual < mesNacim) {
			dif = dif - 1;
		} else {
			if (mesActual == mesNacim && diaActual < diaNacim) {
				dif = dif - 1;
			}
		}

		return dif;
	}
}