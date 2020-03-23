package org.ada.abmHibernate.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.ada.abmHibernate.dto.PersonaEntity;
import org.ada.abmHibernate.dto.VentaEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

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
	
	public void deleteVenta(VentaEntity ven) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(ven);
		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}
	
	public VentaEntity getVentaXid(int ventaId) {
		Session sesn = HibernateUtil.getSessionFactory().openSession();
		VentaEntity ven = null;
		List<VentaEntity> ven1 = new ArrayList<VentaEntity>();
		try {
			ven1 = sesn.createQuery("From VentaEntity Where VentaId =" + ventaId).list();
			if (!ven1.isEmpty()) {
				ven = ven1.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}
		HibernateUtil.shutdown();
		return ven;
	}
	
	public List<VentaEntity> getVentaxImporte(float importe) { 
		Session sesn = HibernateUtil.getSessionFactory().openSession();
		VentaEntity ven = null;
		List<VentaEntity> ven1 = new ArrayList<VentaEntity>();
		try {
			ven1 = sesn.createQuery("From VentaEntity Where Importe =" + importe).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}

		HibernateUtil.shutdown();
		return ven1;
	}
}