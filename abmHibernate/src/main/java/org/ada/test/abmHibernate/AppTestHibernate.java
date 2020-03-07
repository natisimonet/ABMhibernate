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
	static PersonaDAO perDAO = new PersonaDAO();
	static VentaDAO venDAO = new VentaDAO();
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("SISTEMA DE PERSONAS (ABM)");
		System.out.println("=========================");

		System.out.println("");

		int opcion = mostrarMenu(scan);
		while (opcion != 0) {

			switch (opcion) {
			case 1:
				alta(perDAO, scan);
				break;
			case 2:
				modificacion(perDAO, scan);
				break;
			case 3:
				listado(perDAO);
				break;
			case 4:
				baja(perDAO, scan);
				break;
			case 5:
				nuevaVenta(venDAO, scan);
				break;
			case 6:
				listadoVenta(venDAO);
				break;
			}

			opcion = mostrarMenu(scan);

		}

	}

	private static int mostrarMenu(Scanner scan) {

		System.out.println(
				"MENU OPCIONES: 1 -Alta | 2-Modificación | 3- Listado | 4- Baja | 5- Venta | 6- Listado de Ventas");
		int opcion = scan.nextInt();
		return opcion;
	}

	private static void alta(PersonaDAO perDAO, Scanner scan) {
		PersonaEntity per = new PersonaEntity();
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
		perDAO.insertPersona(per);
	}

	private static void modificacion(PersonaDAO perDAO, Scanner scan) {

		System.out.println("Ingrese ID a modificar");
		int personaId = scan.nextInt();
		PersonaEntity per = perDAO.getPersona(personaId);
		if (per == null) {
			System.out.println("El ID no existe elija una nuevo ID");
			modificacion(perDAO, scan);
		} else {

			System.out.println("Elija columnas a modificar 1. Nombre, 2. Fecha_Nacimiento 3. Ambas");
			int respuesta = scan.nextInt();
			switch (respuesta) {
			case 1:

				System.out.println("Ingrese nuevo Nombre");
				String nombrecambiado = scan.next();
				per.setName(nombrecambiado);
				perDAO.updatePersona(per);
				break;
			case 2:
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

				perDAO.updatePersona(per);
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
				perDAO.updatePersona(per);
				break;
			default:
				break;

			}
		}
	}

	private static void listado(PersonaDAO perDAO) {

		List<PersonaEntity> listaPersona = perDAO.getAllPersona();

		System.out.println("ID| Nombre| Edad | Fecha Nacimiento");
		for (PersonaEntity per : listaPersona) {
			System.out.println(per.getPersonaId() + " " + per.getName() + " " + per.getEdad() + " " + per.getfNac());
		}
	}

	private static void baja(PersonaDAO perDAO, Scanner scan) {

		System.out.println("Ingrese ID a Borrar");
		int personaId = scan.nextInt();
		PersonaEntity per = perDAO.getPersona(personaId);
		if (per == null) {
			System.out.println("El ID no existe elija un nuevo ID");
			baja(perDAO, scan);
		} else {
			perDAO.deletePersona(per);
		}
	}

	private static void nuevaVenta(VentaDAO venDAO, Scanner scan) {
		VentaEntity ven = new VentaEntity();
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
		venDAO.insertVenta(ven);

	}

	private static void listadoVenta(VentaDAO venDAO) {

		List<VentaEntity> listadoventa = venDAO.getAllventa();
		System.out.println("ID_Venta| Fecha| Importe | ID_Persona | Nombre");
		for (VentaEntity ven : listadoventa) {
			PersonaEntity nuevo = ven.getPersonaEntity();
			int id = nuevo.getPersonaId();
			String nombre = nuevo.getName();
			System.out.println(
					ven.getVentaId() + " " + ven.getFechaVenta() + " " + ven.getImporte() + " " + id + " " + nombre);

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