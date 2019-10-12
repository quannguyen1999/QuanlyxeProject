package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Xe {
	@Id 
	private String maXe;
	
	private String donViTinh;
	private String moTa;
	private String nhaSX;
	private String tenXe;
	private String thongTinBaoHanh;
	private String mauXe;
	private String loaiXe;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "xe")
	private List<CTPhieuXuat> cTPhieuXuat;

	
	public Xe(String maXe, String donViTinh, String moTa, String nhaSX, String tenXe, String thongTinBaoHanh,
			String mauXe, String loaiXe) {
		super();
		this.maXe = maXe;
		this.donViTinh = donViTinh;
		this.moTa = moTa;
		this.nhaSX = nhaSX;
		this.tenXe = tenXe;
		this.thongTinBaoHanh = thongTinBaoHanh;
		this.mauXe = mauXe;
		this.loaiXe = loaiXe;
	}

	public Xe() {
		super();
	}

	public String getMaXe() {
		return maXe;
	}

	public void setMaXe(String maXe) {
		this.maXe = maXe;
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

	public String getNhaSX() {
		return nhaSX;
	}

	public void setNhaSX(String nhaSX) {
		this.nhaSX = nhaSX;
	}

	public String getTenXe() {
		return tenXe;
	}

	public void setTenXe(String tenXe) {
		this.tenXe = tenXe;
	}

	public String getThongTinBaoHanh() {
		return thongTinBaoHanh;
	}

	public void setThongTinBaoHanh(String thongTinBaoHanh) {
		this.thongTinBaoHanh = thongTinBaoHanh;
	}

	public List<CTPhieuXuat> getcTPhieuXuat() {
		return cTPhieuXuat;
	}

	public void setcTPhieuXuat(List<CTPhieuXuat> cTPhieuXuat) {
		this.cTPhieuXuat = cTPhieuXuat;
	}
	
	public String getMauXe() {
		return mauXe;
	}

	public void setMauXe(String mauXe) {
		this.mauXe = mauXe;
	}
	
	public String getLoaiXe() {
		return loaiXe;
	}

	public void setLoaiXe(String loaiXe) {
		this.loaiXe = loaiXe;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Xe [maXe=");
		builder.append(maXe);
		builder.append(", donViTinh=");
		builder.append(donViTinh);
		builder.append(", moTa=");
		builder.append(moTa);
		builder.append(", nhaSX=");
		builder.append(nhaSX);
		builder.append(", tenXe=");
		builder.append(tenXe);
		builder.append(", thongTinBaoHanh=");
		builder.append(thongTinBaoHanh);
		builder.append(", mauXe=");
		builder.append(mauXe);
		builder.append(", loaiXe=");
		builder.append(loaiXe);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
