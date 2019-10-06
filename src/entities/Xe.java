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
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "xe")
	private List<CTPhieuXuat> cTPhieuXuat;

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
		builder.append(", thongTinBaoHanh=");
		builder.append(thongTinBaoHanh);
		builder.append(", cTPhieuXuat=");
		builder.append(cTPhieuXuat);
		builder.append("]");
		return builder.toString();
	}
	
	
}
