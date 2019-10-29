package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PhieuXuat {
	@Id
	private int maPX;

	@ManyToOne
	@JoinColumn(name = "maNV", foreignKey = @ForeignKey)
	private NhanVien nhanVien;

	@ManyToOne
	@JoinColumn(name = "maKH", foreignKey = @ForeignKey)
	private KhachHang khachHang;

	@ManyToOne
	@JoinColumn(name = "maHopDong", foreignKey = @ForeignKey)
	private HopDong hopDong;

	private LocalDate ngayXuat;
	private double donGiaXuat;
	private int sLXuat;
	private double thue;

	public int getMaPX() {
		return maPX;
	}

	public void setMaPX(int maPX) {
		this.maPX = maPX;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public HopDong getHopDong() {
		return hopDong;
	}

	public void setHopDong(HopDong hopDong) {
		this.hopDong = hopDong;
	}

	public LocalDate getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(LocalDate ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public double getDonGiaXuat() {
		return donGiaXuat;
	}

	public void setDonGiaXuat(double donGiaXuat) {
		this.donGiaXuat = donGiaXuat;
	}

	public int getsLXuat() {
		return sLXuat;
	}

	public void setsLXuat(int sLXuat) {
		this.sLXuat = sLXuat;
	}

	public double getThue() {
		return thue;
	}

	public void setThue(double thue) {
		this.thue = thue;
	}

	public PhieuXuat(int maPX, NhanVien nhanVien, KhachHang khachHang, HopDong hopDong, LocalDate ngayXuat,
			double donGiaXuat, int sLXuat, double thue) {
		super();
		this.maPX = maPX;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.hopDong = hopDong;
		this.ngayXuat = ngayXuat;
		this.donGiaXuat = donGiaXuat;
		this.sLXuat = sLXuat;
		this.thue = thue;
	}

	public PhieuXuat() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(", donGiaXuat=");
		builder.append(donGiaXuat);
		return builder.toString();
	}

	
	
}
