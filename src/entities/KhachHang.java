package entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class KhachHang {
	@Id
	private int maKH;
	@Column(columnDefinition = "nvarchar(100)")
	private String diaChi;
	private String CMND;
	private String soDT;
	@Column(columnDefinition = "nvarchar(100)")
	private String tenKH;
	private LocalDate ngaySinh;
	@OneToMany(mappedBy = "khachHang")
	private List<PhieuXuat> dspx;

	public KhachHang() {
		super();
	}

	
	public KhachHang(int maKH, String diaChi, String cMND, String soDT, String tenKH, LocalDate ngaySinh) {
		super();
		this.maKH = maKH;
		this.diaChi = diaChi;
		CMND = cMND;
		this.soDT = soDT;
		this.tenKH = tenKH;
		this.ngaySinh = ngaySinh;
	}


	public int getMaKH() {
		return maKH;
	}

	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}


	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}


	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public List<PhieuXuat> getDspx() {
		return dspx;
	}

	public void setDspx(List<PhieuXuat> dspx) {
		this.dspx = dspx;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", diaChi=" + diaChi + ", CMND=" + CMND + ", soDT=" + soDT + ", tenKH="
				+ tenKH + "]";
	}

	

}
