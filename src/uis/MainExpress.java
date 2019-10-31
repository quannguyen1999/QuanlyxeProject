package uis;

import java.util.List;

import dao.QuanLyKhachHang;
import entities.KhachHang;

public class MainExpress {
	public static void main(String[] args) {
//		String text="H124513";
//		
//		System.out.println(text.matches("^H[0-9]+"));
		
		List<KhachHang> kh=QuanLyKhachHang.timTheoTen("f");
		kh.forEach(t->{
			System.out.println(t);
		});
	}
}
