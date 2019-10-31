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
		
		NhanVien nv_ad=new NhanVien(111, "Nhân viên","33/16 đường huỳnh văn nghệ","0708821227",
				"Nam", 50000, LocalDate.of(1999, 11, 24),"Nguyễn đặng anh quân", ad, "image/"+111+".PNG", "0708821227");
		NhanVien nv_nv=new NhanVien(112, "Nhân viên", "45/13 hoa nguyễn", "071238394",
				"Nữ", 80000, LocalDate.of(1999, 11, 9), "Nhan vĩ nam", nv,"image/"+112+".PNG" ,"12344567");
		NhanVien nv_kt=new NhanVien(113, "Nhân viên", "78/11 phạm văn hai", "01279877","Nam" ,400000
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
		
		Loaixe lx1=new Loaixe("X0001","Xe số","SUBPERCLUB", "Xanh dương","American","image/"+"X0001"+".PNG" ,"Super");
		Loaixe lx2=new Loaixe("X0002","Xe mô tô","Monkey", "Vàng","American","image/"+"X0002"+".PNG" ,"mokey");
		Loaixe lx3=new Loaixe("X0003","Xe tay ga","PCX", "Đen","American","image/"+"X0003"+".PNG" ,"pcx");

		Loaixe lx4=new Loaixe("X0004","Xe tay ga","PCX HIBRID", "Đen","American","image/"+"X0004"+".PNG" ,"hibrid");
		Loaixe lx5=new Loaixe("X0005","Xe tay ga","PCX 125cc", "Trắng","American","image/"+"X0005"+".PNG" ,"PCX");
		Loaixe lx6=new Loaixe("X0006","Xe tay ga","SH Mode 125cc", "Vàng","American","image/"+"X0006"+".PNG" ,"SH");
		Loaixe lx7=new Loaixe("X0007","Xe tay ga","SH 300cc", "Đen","American","image/"+"X0007"+".PNG" ,"SH");
		Loaixe lx8=new Loaixe("X0008","Xe tay ga","Air Blade 125cc", "Xanh dương","American","image/"+"X0008"+".PNG" ,"Blade");
		Loaixe lx9=new Loaixe("X0009","Xe tay ga","SH 125cc", "Đen","American","image/"+"X0009"+".PNG" ,"Sh");
		Loaixe lx10=new Loaixe("X0010","Xe tay ga","Lead 125cc", "Đỏ","American","image/"+"X0010"+".PNG" ,"Lead");
		Loaixe lx11=new Loaixe("X0011","Xe tay ga","Vision 110cc", "Xanh lá","American","image/"+"X0011"+".PNG" ,"Vision");
		
		Loaixe lx12=new Loaixe("X0012","Xe số","Future 125cc", "Đỏ","American","image/"+"X0012"+".PNG" ,"future");
		Loaixe lx13=new Loaixe("X0013","Xe số","Wave RSX FI 110cc", "Đỏ","American","image/"+"X0013"+".PNG" ,"wave");
		
//		Xe côn tay
		Loaixe lx14=new Loaixe("X0014","Xe côn tay","Winner X", "Đỏ","American","image/"+"X0014"+".PNG" ,"winner");
		
		//Xe mô tô
		Loaixe lx15=new Loaixe("X0015","Xe mô tô","CB300R", "Đỏ","American","image/"+"X0015"+".PNG" ,"CB");
		Loaixe lx16=new Loaixe("X0016","Xe mô tô","CB650R", "Đen","American","image/"+"X0016"+".PNG" ,"CB");
		
		
		if(QuanLyLoaiXe.themXe(lx1)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0001.PNG"),new File("src/image/"+"X0001"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx2)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0002.PNG"),new File("src/image/"+"X0002"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx3)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0003.PNG"),new File("src/image/"+"X0003"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx4)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0004.PNG"),new File("src/image/"+"X0004"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx5)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0005.PNG"),new File("src/image/"+"X0005"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx6)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0006.PNG"),new File("src/image/"+"X0006"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx7)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0007.PNG"),new File("src/image/"+"X0007"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx8)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0008.PNG"),new File("src/image/"+"X0008"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx9)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0009.PNG"),new File("src/image/"+"X0009"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx10)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0010.PNG"),new File("src/image/"+"X0010"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx11)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0011.PNG"),new File("src/image/"+"X0011"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx12)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0012.PNG"),new File("src/image/"+"X0012"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx13)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0013.PNG"),new File("src/image/"+"X0013"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx14)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0014.PNG"),new File("src/image/"+"X0014"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx15)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0015.PNG"),new File("src/image/"+"X0015"+".PNG"));
		}
		
		if(QuanLyLoaiXe.themXe(lx16)==true) {
			copyFileUsingStream(new File(begin+"/src/image/backup/"+"X0016.PNG"),new File("src/image/"+"X0016"+".PNG"));
		}
		
		
		
		Xe xe1=new Xe("HX000", lx1,"Chiếc", "Lái", "36 tháng", 50, Double.parseDouble(String.valueOf("800000")));
		Xe xe2=new Xe("HX001", lx2,"Chiếc", "Lái", "12 tháng", 80, Double.parseDouble(String.valueOf("100000")));
		Xe xe3=new Xe("HX003", lx3,"Chiếc", "Lái", "24 tháng", 150, Double.parseDouble(String.valueOf("200000")));
		Xe xe4=new Xe("HX004", lx4,"Chiếc", "Lái", "12 tháng", 40, Double.parseDouble(String.valueOf("400000")));
		Xe xe5=new Xe("HX005", lx5,"Chiếc", "Lái", "24 tháng", 120, Double.parseDouble(String.valueOf("800000")));
		Xe xe6=new Xe("HX006", lx6,"Chiếc", "Lái", "12 tháng", 130, Double.parseDouble(String.valueOf("700000")));
		Xe xe7=new Xe("HX007", lx7,"Chiếc", "Lái", "12 tháng", 140, Double.parseDouble(String.valueOf("400000")));
		Xe xe8=new Xe("HX008", lx8,"Chiếc", "Lái", "12 tháng", 150, Double.parseDouble(String.valueOf("900000")));
		Xe xe9=new Xe("HX009", lx9,"Chiếc", "Lái", "24 tháng", 20, Double.parseDouble(String.valueOf("1000000")));
		Xe xe10=new Xe("HX010", lx10,"Chiếc", "Lái", "12 tháng", 150, Double.parseDouble(String.valueOf("2000000")));
		Xe xe11=new Xe("HX011", lx11,"Chiếc", "Lái", "36 tháng", 450, Double.parseDouble(String.valueOf("400000")));
		Xe xe12=new Xe("HX012", lx12,"Chiếc", "Lái", "12 tháng", 750, Double.parseDouble(String.valueOf("500000")));
		Xe xe13=new Xe("HX013", lx13,"Chiếc", "Lái", "24 tháng", 250, Double.parseDouble(String.valueOf("800000")));
		Xe xe14=new Xe("HX014", lx14,"Chiếc", "Lái", "12 tháng", 40, Double.parseDouble(String.valueOf("7400000")));
		Xe xe15=new Xe("HX015", lx15,"Chiếc", "Lái", "24 tháng", 20, Double.parseDouble(String.valueOf("4500000")));
		Xe xe16=new Xe("HX016", lx16,"Chiếc", "Lái", "36 tháng", 30, Double.parseDouble(String.valueOf("4500000")));
		Xe xe17=new Xe("HX017", lx1,"Chiếc", "Lái", "36 tháng", 2, Double.parseDouble(String.valueOf("4500000")));
		Xe xe18=new Xe("HX018", lx2,"Chiếc", "Lái", "36 tháng", 9, Double.parseDouble(String.valueOf("4500000")));
		Xe xe19=new Xe("HX019", lx11,"Chiếc", "Lái", "36 tháng", 100, Double.parseDouble(String.valueOf("4500000")));
		Xe xe20=new Xe("HX020", lx16,"Chiếc", "Lái", "36 tháng", 10, Double.parseDouble(String.valueOf("8500000")));
		Xe xe21=new Xe("HX021", lx5,"Chiếc", "Lái", "36 tháng", 40, Double.parseDouble(String.valueOf("9500000")));
		Xe xe22=new Xe("HX022", lx8,"Chiếc", "Lái", "36 tháng", 30, Double.parseDouble(String.valueOf("4500000")));
		Xe xe23=new Xe("HX023", lx1,"Chiếc", "Lái", "36 tháng", 30, Double.parseDouble(String.valueOf("4500000")));
		Xe xe24=new Xe("HX024", lx2,"Chiếc", "Lái", "36 tháng", 130, Double.parseDouble(String.valueOf("4500000")));
		Xe xe25=new Xe("HX025", lx3,"Chiếc", "Lái", "36 tháng", 30, Double.parseDouble(String.valueOf("4500000")));
		Xe xe26=new Xe("HX026", lx4,"Chiếc", "Lái", "36 tháng", 230, Double.parseDouble(String.valueOf("2000000")));
		Xe xe27=new Xe("HX027", lx5,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("4500000")));
		Xe xe28=new Xe("HX028", lx6,"Chiếc", "Lái", "12 tháng", 50, Double.parseDouble(String.valueOf("4500000")));
		Xe xe29=new Xe("HX029", lx7,"Chiếc", "Lái", "36 tháng", 3, Double.parseDouble(String.valueOf("3000000")));
		Xe xe30=new Xe("HX030", lx8,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("1500000")));
		Xe xe31=new Xe("HX031", lx11,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("4500000")));
		Xe xe32=new Xe("HX032", lx10,"Chiếc", "Lái", "36 tháng", 120, Double.parseDouble(String.valueOf("4500000")));
		Xe xe33=new Xe("HX033", lx8,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("4500000")));
		Xe xe34=new Xe("HX034", lx1,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("2500000")));
		Xe xe35=new Xe("HX035", lx2,"Chiếc", "Lái", "36 tháng", 40, Double.parseDouble(String.valueOf("4500000")));
		Xe xe36=new Xe("HX036", lx12,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("4500000")));
		Xe xe37=new Xe("HX037", lx15,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("3500000")));
		Xe xe38=new Xe("HX038", lx16,"Chiếc", "Lái", "36 tháng", 80, Double.parseDouble(String.valueOf("4500000")));
		Xe xe39=new Xe("HX039", lx10,"Chiếc", "Lái", "36 tháng", 90, Double.parseDouble(String.valueOf("4500000")));
		Xe xe40=new Xe("HX040", lx12,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("800000")));
		Xe xe41=new Xe("HX041", lx3,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("500000")));
		Xe xe42=new Xe("HX042", lx4,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("9500000")));
		Xe xe43=new Xe("HX043", lx12,"Chiếc", "Lái", "36 tháng", 220, Double.parseDouble(String.valueOf("4500000")));
		Xe xe44=new Xe("HX044", lx10,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("1500000")));
		Xe xe45=new Xe("HX045", lx6,"Chiếc", "Lái", "36 tháng", 120, Double.parseDouble(String.valueOf("4500000")));
		Xe xe46=new Xe("HX046", lx4,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("5500000")));
		Xe xe47=new Xe("HX047", lx2,"Chiếc", "Lái", "36 tháng", 420, Double.parseDouble(String.valueOf("4500000")));
		Xe xe48=new Xe("HX048", lx6,"Chiếc", "Lái", "36 tháng", 20, Double.parseDouble(String.valueOf("8500000")));
		Xe xe49=new Xe("HX049", lx15,"Chiếc", "Lái", "36 tháng", 220, Double.parseDouble(String.valueOf("6500000")));
		Xe xe50=new Xe("HX050", lx11,"Chiếc", "Lái", "36 tháng", 320, Double.parseDouble(String.valueOf("7500000")));
		
		
		if(QuanLyXe.themXe(xe1)==true) {
			System.out.println("thêm xe1 thành công");
		}
		
		if(QuanLyXe.themXe(xe2)==true) {
			System.out.println("thêm xe2 thành công");
		}
		
		if(QuanLyXe.themXe(xe3)==true) {
			System.out.println("thêm xe3 thành công");
		}
		
		QuanLyXe.themXe(xe4);
		QuanLyXe.themXe(xe5);
		QuanLyXe.themXe(xe6);
		QuanLyXe.themXe(xe7);
		QuanLyXe.themXe(xe8);
		QuanLyXe.themXe(xe9);
		QuanLyXe.themXe(xe10);
		QuanLyXe.themXe(xe11);
		QuanLyXe.themXe(xe12);
		QuanLyXe.themXe(xe13);
		QuanLyXe.themXe(xe14);
		QuanLyXe.themXe(xe15);
		QuanLyXe.themXe(xe16);
		QuanLyXe.themXe(xe17);
		QuanLyXe.themXe(xe18);
		QuanLyXe.themXe(xe19);
		QuanLyXe.themXe(xe20);
		QuanLyXe.themXe(xe21);
		QuanLyXe.themXe(xe22);
		QuanLyXe.themXe(xe23);
		QuanLyXe.themXe(xe24);
		QuanLyXe.themXe(xe25);
		QuanLyXe.themXe(xe26);
		QuanLyXe.themXe(xe27);
		QuanLyXe.themXe(xe28);
		QuanLyXe.themXe(xe29);
		QuanLyXe.themXe(xe30);
		QuanLyXe.themXe(xe31);
		QuanLyXe.themXe(xe32);
		QuanLyXe.themXe(xe33);
		QuanLyXe.themXe(xe34);
		QuanLyXe.themXe(xe35);
		QuanLyXe.themXe(xe36);
		QuanLyXe.themXe(xe37);
		QuanLyXe.themXe(xe38);
		QuanLyXe.themXe(xe39);
		QuanLyXe.themXe(xe40);
		QuanLyXe.themXe(xe41);
		QuanLyXe.themXe(xe42);
		QuanLyXe.themXe(xe43);
		QuanLyXe.themXe(xe44);
		QuanLyXe.themXe(xe45);
		QuanLyXe.themXe(xe46);
		QuanLyXe.themXe(xe47);
		QuanLyXe.themXe(xe48);
		QuanLyXe.themXe(xe49);
		QuanLyXe.themXe(xe50);
		
//		LocalDate.of(year, month, dayOfMonth)
		KhachHang kh1=new KhachHang(100, "56/18 phạm văn hai", "159786524", "07461818", "Anh quân", LocalDate.of(1999, 12, 10));
		KhachHang kh2=new KhachHang(101, "80/20 Nguyễn văn lương", "852365478", "02461818", "Lê thị Nam", LocalDate.of(1998, 11, 12));
		KhachHang kh3=new KhachHang(102, "113/1 Huỳnh văn nghệ", "953621458", "03461818", "Nguyễn thị trường", LocalDate.of(1987, 10, 1));
		KhachHang kh4=new KhachHang(103, "82/17 Huỳnh tấn phát", "458965258", "01461818", "Đặng thanh tùng", LocalDate.of(1985, 12, 13));
		KhachHang kh5=new KhachHang(104, "16/18 Lê văn bảo", "563214895", "0905360857", "Phan quốc duy", LocalDate.of(1980, 1, 10));
		KhachHang kh6=new KhachHang(105, "558/8 hai bà trưng", "459852368", "0708821227", "Lê tấn bảo", LocalDate.of(1992, 2, 11));
		KhachHang kh7=new KhachHang(106, "56/15 ngã ba châu trinh", "589632587", "02461818", "Phan thi đào", LocalDate.of(1993, 5, 1));
		KhachHang kh8=new KhachHang(107, "58/18 Lê văn sỹ", "456985236", "01461818", "Nguyễn văn tuấn", LocalDate.of(1987, 12, 22));
		KhachHang kh9=new KhachHang(108, "123/18 trường trinh", "159632587", "07461818", "Lê văn bảo", LocalDate.of(1986, 10, 25));
		KhachHang kh10=new KhachHang(109, "56/100 cộng hòa", "012365478", "03461818", "nguyễn đặng anh quân", LocalDate.of(1999, 11,23));
		KhachHang kh11=new KhachHang(111, "56/110 cộng hòa", "159632587", "07462818", "nguyễn đặng tài", LocalDate.of(1982, 12, 1));
		KhachHang kh12=new KhachHang(112, "16/12 Lê văn bảo", "458963258", "05463818", "lê tấn tài", LocalDate.of(1980, 11, 10));
		KhachHang kh13=new KhachHang(113, "113/18 Huỳnh văn nghệ", "458962584", "04461818", "Phan thị duy", LocalDate.of(1984, 2, 1));
		KhachHang kh14=new KhachHang(114, "6/18 Lê tấn phát", "456985236", "02461518", "mai thị như anh", LocalDate.of(1981, 2, 10));
		KhachHang kh15=new KhachHang(115, "46/18 bình dương", "456985236", "01461518", "nguyễn tuấn anh", LocalDate.of(1999, 5, 15));
		KhachHang kh16=new KhachHang(116, "26/16 lê văn thọ", "456987412", "07461818", "nguyễn đặng anh hào", LocalDate.of(1991, 8, 10));
		KhachHang kh17=new KhachHang(117, "56/17 Huỳnh văn nghệ", "789652365", "06491818", "tam quốc quân", LocalDate.of(1994, 9, 11));
		KhachHang kh18=new KhachHang(118, "26/18 cộng hòa", "36547895", "07461818", "lê lâm", LocalDate.of(1975, 12, 1));
		KhachHang kh19=new KhachHang(119, "53/182 cộng hòa", "123658954", "08465818", "nguyễn kiệt", LocalDate.of(1995, 11, 24));
		KhachHang kh20=new KhachHang(120, "06/11 trường trinh", "456985236", "090821223", "nguyễn văn hào", LocalDate.of(1996, 10, 18));
		KhachHang kh21=new KhachHang(121, "11 cộng hòa", "789523657", "0408821223", "nguyễn thị hào", LocalDate.of(1996, 12, 18));
		KhachHang kh22=new KhachHang(122, "125/12/1 cộng hòa", "785412569", "0908821223", "mai thị như quỳnh", LocalDate.of(1999, 11, 24));
		KhachHang kh23=new KhachHang(123, "80/15 hai bà trưng hòa", "458963258", "0408821223", "lê hào", LocalDate.of(1996, 10, 18));
		KhachHang kh24=new KhachHang(124, "80/11 cộng hòa", "4785214569", "0708841223", "lê tuấn bảo", LocalDate.of(1994, 10, 17));
		KhachHang kh25=new KhachHang(125, "06/11 lê văn bảo", "456985236", "0708321223", "nhan vĩ nam", LocalDate.of(1999, 11, 9));
		
		
		
		
		if(QuanLyKhachHang.themAcc(kh1)==true) {
			System.out.println("Thêm khách hàng thành công");
		}else {
			System.out.println("Thêm khách hàng không thành công");
		}
		
		QuanLyKhachHang.themAcc(kh2);
		QuanLyKhachHang.themAcc(kh3);
		QuanLyKhachHang.themAcc(kh4);
		QuanLyKhachHang.themAcc(kh5);
		QuanLyKhachHang.themAcc(kh6);
		QuanLyKhachHang.themAcc(kh7);
		QuanLyKhachHang.themAcc(kh8);
		QuanLyKhachHang.themAcc(kh9);
		QuanLyKhachHang.themAcc(kh10);
		QuanLyKhachHang.themAcc(kh11);
		QuanLyKhachHang.themAcc(kh12);
		QuanLyKhachHang.themAcc(kh13);
		QuanLyKhachHang.themAcc(kh14);
		QuanLyKhachHang.themAcc(kh15);
		QuanLyKhachHang.themAcc(kh16);
		QuanLyKhachHang.themAcc(kh17);
		QuanLyKhachHang.themAcc(kh18);
		QuanLyKhachHang.themAcc(kh19);
		QuanLyKhachHang.themAcc(kh20);
		QuanLyKhachHang.themAcc(kh21);
		QuanLyKhachHang.themAcc(kh22);
		QuanLyKhachHang.themAcc(kh23);
		QuanLyKhachHang.themAcc(kh24);
		QuanLyKhachHang.themAcc(kh25);
		
		HopDong hd1=new HopDong(159, xe19, LocalDate.of(2019, 11, 1),String.valueOf(nv_nv.getMaNV()),nv_nv.getTenNV(),nv_nv.getCMND(), 
				nv_nv.getDiaChi(),nv_nv.getDienThoai(),"Xác nhận", String.valueOf(kh11.getMaKH()), kh11.getTenKH(), kh11.getCMND(), kh11.getDiaChi(), kh11.getSoDT(), Double.parseDouble(String.valueOf(xe19.getDonGia()*3)), 4);
		HopDong hd2=new HopDong(154, xe10, LocalDate.of(2018, 1, 15),String.valueOf(nv_nv.getMaNV()),nv_nv.getTenNV(),nv_nv.getCMND(), 
				nv_nv.getDiaChi(),nv_nv.getDienThoai(),"Chưa xác nhận", String.valueOf(kh11.getMaKH()), kh17.getTenKH(), kh17.getCMND(), kh17.getDiaChi(), kh17.getSoDT(), Double.parseDouble(String.valueOf(xe10.getDonGia()*2)), 4);
		PhieuXuat px1=new PhieuXuat(458, nv_nv, kh11, hd1, LocalDate.of(2019, 11, 1), hd1.getTienDatThanhToan(),3, 10);
		
		QuanLyHopDong.themHopDong(hd1);
		QuanLyHopDong.themHopDong(hd2);
		QuanLyPhieuXuat.themPhieuXuat(px1);
		
		
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
