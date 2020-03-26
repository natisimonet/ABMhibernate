package org.ada.abmHibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import org.ada.abmHibernate.DAO.PersonaDAO;
import org.ada.abmHibernate.DAO.VentaDAO;
import org.ada.abmHibernate.dto.PersonaEntity;
import org.ada.abmHibernate.dto.VentaEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import hibernate.util.DateUtil;

public class AppHibernate {
	static PersonaDAO perDAO = new PersonaDAO();
	static VentaDAO venDAO = new VentaDAO();
	static PersonaEntity per = new PersonaEntity();
	static Scanner scan = new Scanner(System.in);
	static VentaEntity ven = new VentaEntity();

	public static void main(String[] args) throws ParseException {

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
				busqueda();
				break;
			case 6:
				nuevaVenta();
				break;
			case 7:
				listadoVenta();
				break;
			case 8:
				anularVenta();
				break;
			}

			opcion = mostrarMenu();

		}

	}

	private static int mostrarMenu() {

		System.out.println("MENU OPCIONES");		
		System.out.println("1- Alta Nueva Persona | 2- Modificación Persona | 3- Listado Personas | 4- Baja Persona");
		System.out.println("5- Buscar x Nombre | 6- Nueva Venta | 7-Listado de Ventas | 8-Anular Venta");

		int opcion = scan.nextInt();
		return opcion;
	}

	private static void alta() throws ParseException {

		System.out.println("Ingrese Nombre");
		String name = scan.next();
		per.setName(name);
		System.out.println("Ingrese Fecha de Nacimiento en Formato YYYY/MM/DD");
		String fechaDeNac = scan.next();
		per.setfNac(fechaDeNac);
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
		int edad = 0;

		Date fechaparse = DateUtil.parse(DateUtil.PATTERN_Y4_M2_D2, fechaDeNac);
		edad = calcularEdad(fechaparse);

		per.setEdad(edad);
		perDAO.insertOrUpdatePersona(per);
	}

	private static void modificacion() throws ParseException {

		System.out.println("Ingrese ID a modificar");
		int personaId = scan.nextInt();
		per = perDAO.getPersona(personaId);
		if (per == null) {
			System.out.println("El ID no existe elija una nuevo ID");
			modificacion();
		} else {

			System.out.println("Elija columnas a modificar 1. Nombre, 2. Fecha_Nacimiento 3. Ambas");
			int respuesta = scan.nextInt();
			switch (respuesta) {
			case 1:

				System.out.println("Ingrese nuevo Nombre");
				String nombrecambiado = scan.next();
				per.setName(nombrecambiado);
				perDAO.insertOrUpdatePersona(per);
				break;
			case 2:
				System.out.println("Ingrese fecha de nacimiento YYYY/MM/DD");
				String fechaDeNac = scan.next();
				per.setfNac(fechaDeNac);
				SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
				int edad = 0;

				Date fechaparse = DateUtil.parse(DateUtil.PATTERN_Y4_M2_D2, fechaDeNac);
				edad = calcularEdad(fechaparse);

				per.setEdad(edad);

				perDAO.insertOrUpdatePersona(per);
				break;
			case 3:
				System.out.println("Ingrese nuevo Nombre");
				nombrecambiado = scan.next();
				per.setName(nombrecambiado);
				System.out.println("Ingrese fecha de nacimiento YYYY/MM/DD");
				fechaDeNac = scan.next();
				per.setfNac(fechaDeNac);

				edad = 0;

				fechaparse = DateUtil.parse(DateUtil.PATTERN_Y4_M2_D2, fechaDeNac);
				edad = calcularEdad(fechaparse);

				per.setEdad(edad);
				perDAO.insertOrUpdatePersona(per);
				break;
			default:
				break;

			}
		}
	}

	private static void listado() {

		List<PersonaEntity> listaPersona = perDAO.getAllPersona();

		System.out.println("ID| Nombre| Edad | Fecha Nacimiento");
		for (PersonaEntity per : listaPersona) {
			System.out.println(per.getPersonaId() + " " + per.getName() + " " + per.getEdad() + " " + per.getfNac());
		}
	}

	private static void baja() {

		System.out.println("Ingrese ID a Borrar");
		int personaId = scan.nextInt();
		per = perDAO.getPersona(personaId);
		if (per == null) {
			System.out.println("El ID no existe elija un nuevo ID");
			baja();
		} else {
			perDAO.deletePersona(per);
		}
	}

	private static void busqueda() {

		System.out.println("Ingrese el nombre a buscar");
		String name = scan.next();
		List<PersonaEntity> listaPersona = perDAO.getPersonaXnombre(name);
		System.out.println("ID_Venta| Fecha| Importe | ID_Persona | Nombre");
		for (PersonaEntity per : listaPersona) {
			System.out.println(per.getPersonaId() + " " + per.getName() + " " + per.getEdad() + " " + per.getfNac());
		}

	}

	private static void nuevaVenta() {
		System.out.println("Ingrese ID persona");
		int personaId = scan.nextInt();
		per = perDAO.getPersona(personaId);
		if (per == null) {
			System.out.println("El ID no existe elija un nuevo ID");
			nuevaVenta();
		} else {
			System.out.println("La persona seleccionada es:" + per.getName());
			ven.setPersonaEntity(per);
			System.out.println("Ingrese Monto de la venta");
			float importe = scan.nextFloat();
			ven.setImporte(importe);
			Date fecha = new Date();
			ven.setFechaVenta(fecha);

			venDAO.insertVenta(ven);
		}
	}

	private static void listadoVenta() {

		List<VentaEntity> listadoventa = venDAO.getAllventa();
		System.out.println("ID_Venta| Fecha| Importe | ID_Persona | Nombre");
		for (VentaEntity ven : listadoventa) {
			per = ven.getPersonaEntity();
			int id = per.getPersonaId();
			String nombre = per.getName();
			System.out.println(
					ven.getVentaId() + " " + ven.getFechaVenta() + " " + ven.getImporte() + " " + id + " " + nombre);

		}
	}

	private static void anularVenta() { // Anula la venta creando otra igual con importe en negativo ("NC")
		System.out.println("Ingrese el id de la venta a anular");
		int iDventa = scan.nextInt();
		ven = venDAO.getVentaXid(iDventa);
		if (ven == null) {
			System.out.println("El ID de Venta no existe elija una nuevo ID");
			anularVenta();
		} else {
			VentaEntity ven2 = new VentaEntity();
			float importe = ven.getImporte() * (-1);
			ven2.setImporte(importe);
			per = ven.getPersonaEntity();
			ven2.setPersonaEntity(per);
			Date fecha = new Date();
			ven2.setFechaVenta(fecha);
			venDAO.insertVenta(ven2);

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