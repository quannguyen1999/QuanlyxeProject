package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.Account;
import entities.CTPhieuXuat;
import entities.HopDong;
import entities.NhanVien;
import entities.PhieuXuat;

public class QuanLyPhieuXuat {
	public static CTPhieuXuat timMaCTPhieuXuat(Integer ma) {
		CTPhieuXuat accs = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			accs=manager.find(CTPhieuXuat.class, ma);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return accs;
	}
	public static boolean themPhieuXuat(PhieuXuat acc) {
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(acc);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			return false;
		} finally {
			manager.close();
		}
		return true;
	}
	public static List<PhieuXuat> showTatCaPhieuXuat() {
		List<PhieuXuat> accs = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			accs=manager.createQuery("select s from PhieuXuat s",PhieuXuat.class).getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return accs;
	}
	public static List<CTPhieuXuat> showTatCaChiTietPhieuXuat() {
		List<CTPhieuXuat> accs = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			accs=manager.createQuery("select s from CTPhieuXuat s",CTPhieuXuat.class).getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return accs;
	}
	public static boolean themChiTietPhieuXuat(CTPhieuXuat acc) {
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(acc);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			return false;
		} finally {
			manager.close();
		}
		return true;
	}
	
	public static boolean xoaPhieuXuat(int ma) {
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			PhieuXuat acc=manager.find(PhieuXuat.class, ma);//			manager.r
			manager.remove(acc);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			return false;
		} finally {
			manager.close();
		}
		return true;
	}
}
