package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Xe {
	@Id
	private String maXe;

	@ManyToOne
	@JoinColumn(name = "maloai")
	private Loaixe lx;
	@Column(columnDefinition = "nvarchar(50)")
	private String donViTinh;
	@Column(columnDefinition = "nvarchar(100)")
	private String moTa;
	private String thongTinBaoHanh;
	private int soLuongLap;
	private double donGia;

	@OneToMany(mappedBy = "xe")
	private List<HopDong> dshd;

	public Xe() {
		super();
	}

	public Xe(String maXe, Loaixe lx, String donViTinh, String moTa, String thongTinBaoHanh, int soLuongLap,
			double donGia) {
		super();
		this.maXe = maXe;
		this.lx = lx;
		this.donViTinh = donViTinh;
		this.moTa = moTa;
		this.thongTinBaoHanh = thongTinBaoHanh;
		this.soLuongLap = soLuongLap;
		this.donGia = donGia;
	}

	public String getMaXe() {
		return maXe;
	}

	public void setMaXe(String maXe) {
		this.maXe = maXe;
	}

	public Loaixe getLx() {
		return lx;
	}

	public void setLx(Loaixe lx) {
		this.lx = lx;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getThongTinBaoHanh() {
		return thongTinBaoHanh;
	}

	public void setThongTinBaoHanh(String thongTinBaoHanh) {
		this.thongTinBaoHanh = thongTinBaoHanh;
	}

	public int getSoLuongLap() {
		return soLuongLap;
	}

	public void setSoLuongLap(int soLuongLap) {
		this.soLuongLap = soLuongLap;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public List<HopDong> getDshd() {
		return dshd;
	}

	public void setDshd(List<HopDong> dshd) {
		this.dshd = dshd;
	}

	@Override
	public String toString() {
		return "Xe [maXe=" + maXe + ", lx=" + lx + ", donViTinh=" + donViTinh + ", moTa=" + moTa + ", thongTinBaoHanh="
				+ thongTinBaoHanh + ", soLuongLap=" + soLuongLap + ", donGia=" + donGia + ", dshd=" + dshd + "]";
	}

	
}
