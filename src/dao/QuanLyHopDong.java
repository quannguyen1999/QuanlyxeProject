package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.Account;
import entities.HopDong;
import entities.KhachHang;
import entities.NhanVien;

public class QuanLyHopDong {
	static HopDong hd0 = null;
	public static HopDong timMaHopDong(int ma) {
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			javax.persistence.Query query = manager.createQuery("from HopDong where maHopDong='"+ma+"'");
			List<?> list=query.getResultList();
			list.forEach(t->{
				hd0=(HopDong) t;
			});
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return hd0;
	}
//	static CTHopDong hd = null;
//	public static CTHopDong timMaTheoHopDong(int ma) {
//		
//		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
//		EntityTransaction transaction = null;
//		try {
//			transaction = manager.getTransaction();
//			transaction.begin();
//			javax.persistence.Query query = manager.createQuery("from CTHopDong where maHopDong='"+ma+"'");
//			List<?> list=query.getResultList();
//			list.forEach(t->{
//				hd=(CTHopDong) t;
//			});
//			transaction.commit();
//		} catch (Exception ex) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			ex.printStackTrace();
//		} finally {
//			manager.close();
//		}
//		return hd;
//	}
//	public static boolean xoaChiTietHopDong(String maHD) {
//		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
//		EntityTransaction transaction = null;
//		try {
//			transaction = manager.getTransaction();
//			transaction.begin();
//			CTHopDong acc=manager.find(CTHopDong.class, maHD);//			manager.r
//			manager.remove(acc);
//			transaction.commit();
//		} catch (Exception ex) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			ex.printStackTrace();
//			return false;
//		} finally {
//			manager.close();
//		}
//		return true;
//	}
	public static boolean xoaHopDong(int maHD) {
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			HopDong acc=manager.find(HopDong.class, maHD);//			manager.r
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
	public static boolean themHopDong(HopDong acc) {
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
//	public static boolean themChiTietHopDong(CTHopDong acc) {
//		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
//		EntityTransaction transaction = null;
//		try {
//			transaction = manager.getTransaction();
//			transaction.begin();
//			manager.merge(acc);
//			transaction.commit();
//		} catch (Exception ex) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			ex.printStackTrace();
//			return false;
//		} finally {
//			manager.close();
//		}
//		return true;
//	}
	public static List<HopDong> showTatCaHopDong() {
		List<HopDong> accs = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			accs=manager.createQuery("select s from HopDong s",HopDong.class).getResultList();
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
}
