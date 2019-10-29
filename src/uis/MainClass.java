package uis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dao.QuanLyAccount;
import dao.QuanLyHopDong;
import dao.QuanLyKhachHang;
import dao.QuanLyLoaiXe;
import dao.QuanLyNhanVien;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
import entities.Account;
import entities.HopDong;
import entities.KhachHang;
import entities.Loaixe;
import entities.NhanVien;
import entities.PhieuXuat;
import entities.Xe;
import javafx.scene.image.Image;


public class MainClass {
	private final static int PREFIX_OFFSET = 5;
	private final static String[] PREFIX_ARRAY = {"f", "p", "n", "µ", "m", "", "k", "M", "G", "T"};

	public static String convert(double val, int dp)
	{
	   // If the value is zero, then simply return 0 with the correct number of dp
	   if (val == 0) return String.format("%." + dp + "f", 0.0);

	   // If the value is negative, make it positive so the log10 works
	   double posVal = (val<0) ? -val : val;
	   double log10 = Math.log10(posVal);

	   // Determine how many orders of 3 magnitudes the value is
	   int count = (int) Math.floor(log10/3);

	   // Calculate the index of the prefix symbol
	   int index = count + PREFIX_OFFSET;

	   // Scale the value into the range 1<=val<1000
	   val /= Math.pow(10, count * 3);

	   if (index >= 0 && index < PREFIX_ARRAY.length)
	   {
	      // If a prefix exists use it to create the correct string
	      return String.format("%." + dp + "f%s", val, PREFIX_ARRAY[index]);
	   }
	   else
	   {
	      // If no prefix exists just make a string of the form 000e000
	      return String.format("%." + dp + "fe%d", val, count * 3);
	   }
	}
	public static void main(String[] args) throws IOException {
		Account ad=new Account("AnhQuan99", "123456", "admin");
		Account nv=new Account("ViNam99", "123486", "Nhân viên");
		Account kt=new Account("ThanhTung99", "123406", "Nhân viên");
		
		QuanLyAccount.themAcc(ad);
		QuanLyAccount.themAcc(nv);
		QuanLyAccount.themAcc(kt);
		
		NhanVien nv_ad=new NhanVien(111, "Kế toán trưởng","33/16 đường huỳnh văn nghệ","0708821227",
				"Nam", 50000, LocalDate.of(1999, 11, 24),"Nguyễn đặng anh quân", ad, "image/"+111+".PNG", "0708821227");
		NhanVien nv_nv=new NhanVien(112, "Nhân viên", "45/13 hoa nguyễn", "071238394",
				"Nữ", 80000, LocalDate.of(1999, 11, 9), "Nhan vĩ nam", nv,"image/"+112+".PNG" ,"12344567");
		NhanVien nv_kt=new NhanVien(113, "Kế toán", "78/11 phạm văn hai", "01279877","Nam" ,400000
				, LocalDate.of(1999, 5, 5),"Lê thanh tùng",kt,"image/"+113+".PNG","1234556");
		
		File currentDirFile = new File("");
		String helper = currentDirFile.getAbsolutePath();
		String begin=kiemTraChuoi(helper);
		
		if(QuanLyNhanVien.themNV(nv_ad)==1) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"111.PNG"),new File("src/image/"+111+".PNG"));
		}else {
			System.out.println("Lỗi thêm Kế toán trưởng ");
		}
		if(QuanLyNhanVien.themNV(nv_nv)==1) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"112.PNG"),new File("src/image/"+112+".PNG"));
		}else {
			System.out.println("Lỗi thêm nhân viên");
		}
		if(QuanLyNhanVien.themNV(nv_kt)==1) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"113.PNG"),new File("src/image/"+113+".PNG"));
		}else {
			System.out.println("Lỗi thêm kế toán");
		}
		
		Loaixe lx1=new Loaixe("X0001","Xe số","SUBPERCLUB", "Xanh dương","American","image/"+"X0001"+".PNG" ,"po");
		Loaixe lx2=new Loaixe("X0002","Xe mô tô","Monkey", "Vàng","American","image/"+"X0002"+".PNG" ,"kl");
		Loaixe lx3=new Loaixe("X0003","Xe tay ga","PCX", "Đen","American","image/"+"X0003"+".PNG" ,"po");
		
		if(QuanLyLoaiXe.themXe(lx1)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0001.PNG"),new File("src/image/"+"X0001"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx2)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0002.PNG"),new File("src/image/"+"X0002"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx3)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0003.PNG"),new File("src/image/"+"X0003"+".PNG"));
		}
		
		Xe xe1=new Xe("HX000", lx1,"Chiếc", "Lái", "12 tháng", 50, Double.parseDouble(String.valueOf("800000")));
		Xe xe2=new Xe("HX001", lx2,"Chiếc", "Lái", "12 tháng", 50, Double.parseDouble(String.valueOf("800000")));
		Xe xe3=new Xe("HX002", lx3,"Chiếc", "Lái", "12 tháng", 50, Double.parseDouble(String.valueOf("800000")));
		
		if(QuanLyXe.themXe(xe1)==true) {
			System.out.println("thêm xe1 thành công");
		}
		
		if(QuanLyXe.themXe(xe2)==true) {
			System.out.println("thêm xe2 thành công");
		}
		
		if(QuanLyXe.themXe(xe3)==true) {
			System.out.println("thêm xe3 thành công");
		}
		
		KhachHang kh1=new KhachHang(101, "56/18 phạm văn hai", "1238787", "07461818", "Lê bảo", LocalDate.of(1999, 12, 1));
		if(QuanLyKhachHang.themAcc(kh1)==true) {
			System.out.println("Thêm khách hàng thành công");
		}else {
			System.out.println("Thêm khách hàng không thành công");
		}
		
//		HopDong hd1=new HopDong(56789,LocalDate.of(2019, 10, 11),String.valueOf(nv_kt.getMaNV()),nv_kt.getTenNV(),nv_kt.getCMND(), nv_kt.getDiaChi(), nv_kt.getDienThoai(),String.valueOf(kh1.getMaKH()),
//				kh1.getTenKH(), kh1.getCMND(), kh1.getDiaChi(), kh1.getSoDT(), Double.parseDouble("123333"));
//		
//		if(QuanLyHopDong.themHopDong(hd1)==true) {
//			System.out.println("Thêm hợp đồng hàng thành công");
//		}else {
//			System.out.println("Thêm hợp đồng không thành công");
//		}
//		
//		
//		PhieuXuat px=new PhieuXuat(890, nv_kt, kh1, hd1, LocalDate.of(2019, 11, 11));
//		
//		if(QuanLyPhieuXuat.themPhieuXuat(px)==true) {
//			System.out.println("Thêm phiếu xuất thành công");
//			CTPhieuXuat ctpx=new CTPhieuXuat(px, xe1, Double.parseDouble("123"), 12, 12);
//			if(QuanLyPhieuXuat.themChiTietPhieuXuat(ctpx)==false) {
//				System.out.println("Lỗi chi tiết phiếu xuất");
//			}
//		}else {
//			System.out.println("Thêm phiếu xuất không thành công");
//		}
	}
	
	private static String kiemTraChuoi(String text) {
		String newTextResult="";
		for(int i=0;i<=text.length()-1;i++) {
			if((int)text.charAt(i)==92) {
				newTextResult+="/";
			}else {
				newTextResult+=text.charAt(i);
			}
		}
		return newTextResult;
	}
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		}finally {
			is.close();
			os.close();
		}
	}
	
	static void xoaFile(String File) {
		System.out.println(File);
        File file = new File(File); 
          
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
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
