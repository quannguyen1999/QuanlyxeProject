package entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class NhanVien {
	@Id 
	private int maNV;
	private String chucVu;
	private String diaChi;
	private double dienThoai;
	private String gioiTinh;
	private double luongCoBan;
	private LocalDate namSinh;
	private String tenNV;
	
	@OneToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "userName")
	private Account account;
	
	@OneToMany(mappedBy = "nhanVien")
	private List<BaoCao> baoCao;
	
	@OneToMany(mappedBy = "nhanVien")
	private List<HopDong> hopDong;

	public NhanVien() {
		super();
	}

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public double getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(double dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public double getLuongCoBan() {
		return luongCoBan;
	}

	public void setLuongCoBan(double luongCoBan) {
		this.luongCoBan = luongCoBan;
	}

	public LocalDate getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(LocalDate namSinh) {
		this.namSinh = namSinh;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<BaoCao> getBaoCao() {
		return baoCao;
	}

	public void setBaoCao(List<BaoCao> baoCao) {
		this.baoCao = baoCao;
	}

	public List<HopDong> getHopDong() {
		return hopDong;
	}

	public void setHopDong(List<HopDong> hopDong) {
		this.hopDong = hopDong;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NhanVien [maNV=");
		builder.append(maNV);
		builder.append(", chucVu=");
		builder.append(chucVu);
		builder.append(", diaChi=");
		builder.append(diaChi);
		builder.append(", dienThoai=");
		builder.append(dienThoai);
		builder.append(", gioiTinh=");
		builder.append(gioiTinh);
		builder.append(", luongCoBan=");
		builder.append(luongCoBan);
		builder.append(", namSinh=");
		builder.append(namSinh);
		builder.append(", tenNV=");
		builder.append(tenNV);
		builder.append(", account=");
		builder.append(account);
		builder.append(", baoCao=");
		builder.append(baoCao);
		builder.append(", hopDong=");
		builder.append(hopDong);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
