package org.ada.test.abmHibernate.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.ada.test.abmHibernate.HibernateUtil;
import org.ada.test.abmHibernate.dto.PersonaEntity;
import org.ada.test.abmHibernate.dto.VentaEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class VentaDAO {

	public void insertVenta(VentaEntity ven) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(ven);
		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}

	public List<VentaEntity> getAllventa() {
		Session sesn = HibernateUtil.getSessionFactory().openSession();
		List<VentaEntity> venta = new ArrayList<VentaEntity>();
		try {
			venta = sesn.createQuery("From VentaEntity").list();
			System.out.println("ID_Venta| Fecha| Importe | ID_Persona");
			for (VentaEntity ven : venta) {
				PersonaEntity nuevo = ven.getPersonaEntity();
				int id = nuevo.getPersonaId();
				System.out
						.println(ven.getVentaId() + " " + ven.getFechaVenta() + " " + ven.getImporte() + " " + id);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}

		HibernateUtil.shutdown();
		return venta;
	}

}