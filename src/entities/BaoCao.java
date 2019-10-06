package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BaoCao {
	@Id 
	private String maBC; 
	private int maNV;
	private LocalDate ngayLap;
	private String noiDung; 
	private String tenBC; 
	
	@ManyToOne
	@JoinColumn(name = "maNV",insertable = false,updatable = false)
	private NhanVien nhanVien;

	public BaoCao(String maBC, int maNV, LocalDate ngayLap, String noiDung, String tenBC) {
		super();
		this.maBC = maBC;
		this.maNV = maNV;
		this.ngayLap = ngayLap;
		this.noiDung = noiDung;
		this.tenBC = tenBC;
	}

	public BaoCao() {
		super();
	}

	public String getMaBC() {
		return maBC;
	}

	public void setMaBC(String maBC) {
		this.maBC = maBC;
	}

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getTenBC() {
		return tenBC;
	}

	public void setTenBC(String tenBC) {
		this.tenBC = tenBC;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaoCao [maBC=");
		builder.append(maBC);
		builder.append(", maNV=");
		builder.append(maNV);
		builder.append(", ngayLap=");
		builder.append(ngayLap);
		builder.append(", noiDung=");
		builder.append(noiDung);
		builder.append(", tenBC=");
		builder.append(tenBC);
		builder.append(", nhanVien=");
		builder.append(nhanVien);
		builder.append("]");
		return builder.toString();
	}
	
	
}
