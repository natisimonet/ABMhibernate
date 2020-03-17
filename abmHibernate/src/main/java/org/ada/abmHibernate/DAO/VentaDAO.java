package org.ada.abmHibernate.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.ada.abmHibernate.HibernateUtil;
import org.ada.abmHibernate.dto.PersonaEntity;
import org.ada.abmHibernate.dto.VentaEntity;
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
		
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}

		HibernateUtil.shutdown();
		return venta;
	}

}