package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Query;

import entities.Account;
import entities.NhanVien;

public class QuanLyNhanVien {
	public static List<NhanVien> showTatCaNhanVien() {
		List<NhanVien> accs = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			accs=manager.createQuery("select s from NhanVien s",NhanVien.class).getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		accs.forEach(t->{
			System.out.println(t);
		});
		return accs;
	}
	public static NhanVien timMa(int ma) {
		NhanVien accs = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			accs=manager.find(NhanVien.class, ma);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		System.out.println(accs);
		return accs;
	}
	static NhanVien accs = null;
	public static NhanVien timMa2(String ma) {
		
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			javax.persistence.Query query = manager.createQuery("from NhanVien where userName='"+ma+"'");
			List<?> list=query.getResultList();
			list.forEach(t->{
				accs=(NhanVien) t;
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
		return accs;
	}
	boolean result=false;
	public int themNV(NhanVien acc) {
		
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		List<NhanVien> nvs=showTatCaNhanVien();
		nvs.forEach(t->{
			if(t.getAccount().getUserName().equals(acc.getAccount().getUserName())){
				result=true;
			}
		});
		if(result==false) {
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
				return 0;
			} finally {
				manager.close();
			}
			return 1;
		}
		return -1;
		
	}
	public boolean suaNV(NhanVien acc) {
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
	public static boolean xoaNV(int acc2) {
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			NhanVien acc=manager.find(NhanVien.class, acc2);//			manager.r
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
