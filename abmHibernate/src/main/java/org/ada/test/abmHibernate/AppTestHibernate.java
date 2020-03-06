package org.ada.test.abmHibernate;

import org.ada.test.abmHibernate.DAO.PersonaDAO;
import org.ada.test.abmHibernate.DAO.VentaDAO;
import org.ada.test.abmHibernate.dto.PersonaEntity;
import org.ada.test.abmHibernate.dto.VentaEntity;

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
			case 3:
				listado();
				break;
			case 4:
				baja();
				break;
			case 5:
				nuevaVenta();
			case 6:
				listadoVenta();
			}

			opcion = mostrarMenu();

		}

	}

	private static void listadoVenta() {
		VentaDAO ven1 = new VentaDAO();
		ven1.getAllventa();
		
	}

	private static void baja() {
		PersonaDAO per1 = new PersonaDAO();
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese ID a modificar");
		int personaId = scan.nextInt();
		PersonaEntity per = per1.getPersona(personaId);
		per1.deletePersona(per);

	}

	private static void nuevaVenta() {
		VentaEntity ven = new VentaEntity();
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese ID persona");
		int personaId = scan.nextInt();
		PersonaDAO per1 = new PersonaDAO();
		PersonaEntity per = per1.getPersona(personaId);
		System.out.println("La persona seleccionada es:" + per.getName());
		ven.setPersonaEntity(per);
		System.out.println("Ingrese Monto de la venta");
		float importe = scan.nextFloat();
		ven.setImporte(importe);
		Date fecha = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fecha2 = ft.format(fecha);
		ven.setFechaVenta(fecha2);
		
		VentaDAO ven1 = new VentaDAO();
		ven1.insertVenta(ven);

	}

	private static int mostrarMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU OPCIONES: 1 -Alta | 2-Modificación | 3- Listado | 4- Baja | 5- Venta | 6- Listado de Ventas");
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
		PersonaDAO per1 = new PersonaDAO();
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese ID a modificar");
		int personaId = scan.nextInt();
		PersonaEntity per = per1.getPersona(personaId);

		System.out.println("Elija columnas a modificar 1. Nombre, 2. Fecha_Nacimiento 3. Ambas");
		int respuesta = scan.nextInt();
		switch (respuesta) {
		case 1:

			System.out.println("Ingrese nuevo Nombre");
			String nombrecambiado = scan.next();
			per.setName(nombrecambiado);
			per1.updatePersona(per);
			break;
		case 2:
			System.out.println("Ingrese nuevo Nombre");
			nombrecambiado = scan.next();
			per.setName(nombrecambiado);
			System.out.println("Ingrese fecha de nacimiento YYYY-MM-DD");
			String fechadenac = scan.next();
			per.setfNac(fechadenac);
			SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
			int edad = 0;
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