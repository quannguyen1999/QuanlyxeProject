package entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PhieuXuat {
	@Id 
	private int maPX;
	
	@ManyToOne 
	@JoinColumn(name = "maNV")
	private NhanVien nhanVien; 
	
	@ManyToOne 
	@JoinColumn(name = "maKH")
	private KhachHang khachHang;
	
	private LocalDate ngayXuat;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "phieuXuat") 
	private List<CTPhieuXuat> cTPhieuXuat;

	public PhieuXuat() {
		super();
	}
	public PhieuXuat(int maPX, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayXuat) {
		super();
		this.maPX = maPX;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayXuat = ngayXuat;
	}
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

	public LocalDate getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(LocalDate ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public List<CTPhieuXuat> getcTPhieuXuat() {
		return cTPhieuXuat;
	}

	public void setcTPhieuXuat(List<CTPhieuXuat> cTPhieuXuat) {
		this.cTPhieuXuat = cTPhieuXuat;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhieuXuat [maPX=");
		builder.append(maPX);
		builder.append(", nhanVien=");
		builder.append(nhanVien.getMaNV());
		builder.append(", khachHang=");
		builder.append(khachHang.getMaKH());
		builder.append(", ngayXuat=");
		builder.append(ngayXuat);
		return builder.toString();
	}

	
	
	
}
