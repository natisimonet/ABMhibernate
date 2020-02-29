package org.ada.test.abmHibernate;

import org.ada.test.abmHibernate.DAO.PersonaDAO;
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

public class AppTestHibernate {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		System.out.println("SISTEMA DE PERSONAS (ABM)");
		System.out.println("=========================");

		System.out.println("");

		int opcion = mostrarMenu();
		while (opcion != 0) {

			switch (opcion) {
			case 1:
				alta();
				break;
			case 2:
				modificacion();
				break;
			case 4:
				listado();
				break;

			}

			opcion = mostrarMenu();

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

	private static int mostrarMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"MENU OPCIONES: 1 -Alta | 2-Modificación | 3- Baja  | 4- Listado | 5 - Buscar | 6- Venta | 7- VentasXCliente |  0-Salir ");
		int opcion = sc.nextInt();
		return opcion;
	}

	private static void alta() {
		PersonaEntity per = new PersonaEntity();
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese Nombre");
		String name = scan.next();
		per.setName(name);
		System.out.println("Ingrese Fecha de Nacimiento en Formato YYYY-MM-DD");
		String fNac = scan.next();
		per.setfNac(fNac);
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
		int edad = 0;
		try {
			Date fechaNac = sfd.parse(fNac);
			edad = calcularEdad(fechaNac);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		per.setEdad(edad);

		PersonaDAO per1 = new PersonaDAO();
		per1.insertPersona(per);
	}

	private static void modificacion() {
		PersonaEntity per = new PersonaEntity();
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese ID a modificar");
		int personaId = scan.nextInt();
		per.setPersonaId(personaId);
		System.out.println("Elija columnas a modificar 1. Nombre, 2. Fecha_Nacimiento 3. Ambas");
		int respuesta = scan.nextInt();
		switch (respuesta) {
		case 1:

			System.out.println("Ingrese nuevo Nombre");
			String nombrecambiado = scan.next();
			Session session = HibernateUtil.getSessionFactory().openSession();
			per = (PersonaEntity) session.get(PersonaEntity.class, personaId);
			per.setName(nombrecambiado);
			session.beginTransaction();
			session.update(per);
			session.getTransaction().commit();
			HibernateUtil.shutdown();
			break;
		case 2:
			System.out.println("Ingrese nueva fecha de nacimiento YYYY-MM-DD");
			String fechadenac = scan.next();
			SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
			
			int edad = 0;
			try {
				Date fechaNac = sfd.parse(fechadenac);
				edad = calcularEdad(fechaNac);

			} catch (ParseException e1) {

				e1.printStackTrace();
			}
			session = HibernateUtil.getSessionFactory().openSession();
			per = (PersonaEntity) session.get(PersonaEntity.class, personaId);
			per.setEdad(edad);
			per.setfNac(fechadenac);
			session.beginTransaction();
			session.update(per);
			session.getTransaction().commit();
			HibernateUtil.shutdown();
			break;

		case 3:
			System.out.println("Ingrese nuevo Nombre");
			nombrecambiado = scan.next();
			per.setName(nombrecambiado);
			System.out.println("Ingrese fecha de nacimiento YYYY-MM-DD");
			fechadenac = scan.next();
			per.setfNac(fechadenac);
			sfd = new SimpleDateFormat("yyyy-MM-dd");
			edad = 0;
			try {
				Date fechaNac = sfd.parse(fechadenac);
				edad = calcularEdad(fechaNac);

			} catch (ParseException e1) {

				e1.printStackTrace();
			}
			per.setEdad(edad);
			PersonaDAO per3 = new PersonaDAO();
			per3.updatePersona(per);
			break;
		default:
			break;

		}

	}

	private static void listado() {
		PersonaDAO per1 = new PersonaDAO();
		per1.getAllPersona();
	}
}