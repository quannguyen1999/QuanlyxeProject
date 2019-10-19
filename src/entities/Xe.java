package entities;

import java.util.List;

import javax.persistence.CascadeType;
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
	private String donViTinh;
	@Column(columnDefinition = "nvarchar(100)")
	private String moTa;
	private String thongTinBaoHanh;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "xe")
	private List<CTPhieuXuat> cTPhieuXuat;

	
	public Xe() {
		super();
	}

	public Xe(String maXe, Loaixe lx, String donViTinh, String moTa, String thongTinBaoHanh) {
		super();
		this.maXe = maXe;
		this.lx = lx;
		this.donViTinh = donViTinh;
		this.moTa = moTa;
		this.thongTinBaoHanh = thongTinBaoHanh;
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
		builder.append(", lx=");
		builder.append(lx);
		builder.append(", donViTinh=");
		builder.append(donViTinh);
		builder.append(", moTa=");
		builder.append(moTa);
		builder.append(", thongTinBaoHanh=");
		builder.append(thongTinBaoHanh);
		builder.append("]");
		return builder.toString();
	}

	

	
	
	
	
	
	
}
