package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.Account;
import entities.KhachHang;
import entities.Loaixe;
import entities.NhanVien;
import entities.Xe;

public class QuanLyLoaiXe {
//	select hinhanh
//	from Loaixe 
//	where loaixe=N'xe số' and tenxe=N'asd' and mauson=N'Đen'
	public static List<Loaixe> timMaTraVeLoaiXe(String loaixe,String tenxe,String mauson){
		List<Loaixe> list = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			javax.persistence.Query query = manager.createQuery("from Loaixe where loaixe=:loaixe and tenxe=:tenxe and mauson=:mauson");
			query.setParameter("loaixe", loaixe);
			query.setParameter("tenxe", tenxe);
			query.setParameter("mauson", mauson);
			list=query.getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return list;
	}
	public static List<Loaixe> timTheoTen(String tenxe){
		List<Loaixe> list = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			javax.persistence.Query query = manager.createQuery("from Loaixe where tenxe=:tenxe");
			query.setParameter("tenxe", tenxe);
			list=query.getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return list;
	}

	public static List<String> timMa(String loaixe,String tenxe,String mauson){
		List<String> list = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			javax.persistence.Query query = manager.createQuery("select hinhanh from Loaixe where loaixe=:loaixe and tenxe=:tenxe and mauson=:mauson");
			query.setParameter("loaixe", loaixe);
			query.setParameter("tenxe", tenxe);
			query.setParameter("mauson", mauson);
			list=query.getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return list;
	}
	public static List<String> showHinhAnhCuaXe(String loaixe,String tenxe,String mauson){
		List<String> list = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			javax.persistence.Query query = manager.createQuery("select hinhanh from Loaixe where loaixe=:loaixe and tenxe=:tenxe and mauson=:mauson");
			query.setParameter("loaixe", loaixe);
			query.setParameter("tenxe", tenxe);
			query.setParameter("mauson", mauson);
			list=query.getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return list;
	}
	public static List<String> showMauXeCuaTenXe(String loaiXe,String tenXe){
		List<String> list = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			javax.persistence.Query query = manager.createQuery("select mauson from Loaixe where loaixe=:loaixe and tenxe=:tenxe  group by mauson");
			query.setParameter("loaixe", loaiXe);
			query.setParameter("tenxe", tenXe);
			list=query.getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return list;
	}
	public static List<String> showTenXe(String loaiXe) {
		List<String> list = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			javax.persistence.Query query = manager.createQuery("select tenxe from Loaixe where loaixe=:loaixe group by tenxe");
			query.setParameter("loaixe", loaiXe);
			list=query.getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return list;
	}
	public static List<String> showLoaiXe() {
		List<String> list = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			javax.persistence.Query query = manager.createQuery("select loaixe from Loaixe group by loaixe");
			list=query.getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return list;
	}
	public static Loaixe timTheoMaLoai(String ma) {
		Loaixe accs = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			accs=manager.find(Loaixe.class, ma);
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
	public static boolean suaLoaiXe(Loaixe acc) {
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

	public static Loaixe timMa(String ma) {
		Loaixe accs = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			accs=manager.find(Loaixe.class, ma);
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
	public static List<Loaixe> showTatCaLoaiXe() {
		List<Loaixe> accs = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			accs=manager.createQuery("select s from Loaixe s",Loaixe.class).getResultList();
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
	public static boolean themXe(Loaixe acc) {
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
	public static boolean xoaLoaiXe(String xe) {
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			Loaixe acc=manager.find(Loaixe.class, xe);//			manager.r
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
