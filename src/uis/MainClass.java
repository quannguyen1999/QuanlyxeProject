package uis;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dao.QuanLyAccount;
import dao.QuanLyHopDong;
import dao.QuanLyKhachHang;
import dao.QuanLyNhanVien;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
import entities.Account;
import entities.CTHopDong;
import entities.CTPhieuXuat;
import entities.HopDong;
import entities.KhachHang;
import entities.NhanVien;
import entities.PhieuXuat;
import entities.Xe;


public class MainClass {
	public static void main(String[] args) {
		System.out.println(QuanLyHopDong.timMaTheoHopDong(113));
//		NhanVien nv=QuanLyNhanVien.timMa(Integer.parseInt("114"));
//		HopDong hd=new HopDong(113, LocalDate.of(1999,11 ,1), Double.parseDouble("100"), Double.parseDouble("100"), nv);
//		if(QuanLyHopDong.themHopDong(hd)==true) {
//			System.out.println("asd");
//		}
//		CTHopDong cthd=new CTHopDong("0708821227", "33/13", "1231231", "as", hd);
//		if(QuanLyHopDong.themChiTietHopDong(cthd)==true) {
//			System.out.println("ok");
//		}else{
//			System.out.println("false");
//		};
//		QuanLyHopDong.xoaChiTietHopDong("0708821227");
//		QuanLyHopDong.xoaHopDong(113);
//		Xe xe=QuanLyXe.timMa("");
//		System.out.println(xe);
//		System.out.println(xe.getLoaiXe().equals("Xe tay ga"));
//		System.out.println(xe.getMauXe().equals("Trang"));
		//		PhieuXuat px=new PhieuXuat(112, nv, kh, LocalDate.of(1999, 11, 1));
		//		QuanLyPhieuXuat.themPhieuXuat(px);
		//		CTPhieuXuat ctPX1=new CTPhieuXuat(phieuXuat, xe, donGiaXuat, sLXuat, thue)
		//		CTPhieuXuat ctPX=new CTPhieuXuat(px, xe, 11111, 10, 10);
		//		if(QuanLyPhieuXuat.themChiTietPhieuXuat(ctPX)==true) {
		//			System.out.println("thêm thành công");
		//		}else{
		//			System.out.println("thêm không thành công");
		//		};
		//		
		//		QuanLyPhieuXuat.showTatCaPhieuXuat().forEach(t->{
		//			System.out.println(t);
		//		});
		//		
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
