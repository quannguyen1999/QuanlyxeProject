package entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PhieuXuat {
	@Id
	private int maPX;

	@ManyToOne
	@JoinColumn(name = "maNV",foreignKey = @ForeignKey)
	private NhanVien nhanVien;

	@ManyToOne
	@JoinColumn(name = "maKH",foreignKey = @ForeignKey)
	private KhachHang khachHang;

	@ManyToOne
	@JoinColumn(name = "maHopDong",foreignKey = @ForeignKey)
	private HopDong hopDong;

	private LocalDate ngayXuat;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "phieuXuat")
	private List<CTPhieuXuat> cTPhieuXuat;

	
	
	

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

	public List<CTPhieuXuat> getcTPhieuXuat() {
		return cTPhieuXuat;
	}

	public void setcTPhieuXuat(List<CTPhieuXuat> cTPhieuXuat) {
		this.cTPhieuXuat = cTPhieuXuat;
	}

	

	public PhieuXuat(int maPX, NhanVien nhanVien, KhachHang khachHang, HopDong hopDong, LocalDate ngayXuat) {
		super();
		this.maPX = maPX;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.hopDong = hopDong;
		this.ngayXuat = ngayXuat;
	}

	public PhieuXuat() {
		super();
	}

	@Override
	public String toString() {
		return "PhieuXuat [maPX=" + maPX + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + ", hopDong="
				+ hopDong + ", ngayXuat=" + ngayXuat + ", cTPhieuXuat=" + cTPhieuXuat + "]";
	}

}
