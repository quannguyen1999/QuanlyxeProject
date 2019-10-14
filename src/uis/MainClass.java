package uis;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dao.QuanLyAccount;
import dao.QuanLyKhachHang;
import dao.QuanLyNhanVien;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
import entities.Account;
import entities.CTPhieuXuat;
import entities.KhachHang;
import entities.NhanVien;
import entities.PhieuXuat;
import entities.Xe;


public class MainClass {
	public static void main(String[] args) {
		NhanVien nv=QuanLyNhanVien.timMa(113);
		KhachHang kh=QuanLyKhachHang.timMa(114);
		Xe xe=QuanLyXe.timMa("114");
		System.out.println(xe);
		PhieuXuat px=new PhieuXuat(112, nv, kh, LocalDate.of(1999, 11, 1));
//		QuanLyPhieuXuat.themPhieuXuat(px);
//		CTPhieuXuat ctPX1=new CTPhieuXuat(phieuXuat, xe, donGiaXuat, sLXuat, thue)
//		CTPhieuXuat ctPX=new CTPhieuXuat(px, xe, 11111, 10, 10);
//		if(QuanLyPhieuXuat.themChiTietPhieuXuat(ctPX)==true) {
//			System.out.println("thêm thành công");
//		}else{
//			System.out.println("thêm không thành công");
//		};
		
		QuanLyPhieuXuat.showTatCaPhieuXuat().forEach(t->{
			System.out.println(t);
		});
		
//		Account acc=new Account("admin", "123", "Admin");
//		if(QuanLyAccount.suaAcc(acc)==true) {
//			System.out.println("ok");
//		}else {
//			System.out.println("false");
//		}
	}
	
	public static List<Account> showTatCaAccount() {
		List<Account> accs = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			accs=manager.createQuery("select s from Account s",Account.class).getResultList();
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
