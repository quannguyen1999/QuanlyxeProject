package entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class NhanVien {
	@Id
	private int maNV;
	@Column(columnDefinition = "nvarchar(50)")
	private String chucVu;
	@Column(columnDefinition = "nvarchar(100)")
	private String diaChi;
	private String dienThoai;
	@Column(columnDefinition = "nvarchar(10)")
	private String gioiTinh;
	private String hinhAnh;
	private double luongCoBan;
	private LocalDate namSinh;
	@Column(columnDefinition = "nvarchar(100)")
	private String tenNV;
	@Column(columnDefinition = "nvarchar(100)")
	private String CMND;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userName")
	private Account account;

	@OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
	private List<PhieuXuat> dspx;

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

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
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

	public List<PhieuXuat> getDspx() {
		return dspx;
	}

	public void setDspx(List<PhieuXuat> dspx) {
		this.dspx = dspx;
	}
	
	
	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public NhanVien() {
		super();
	}
	
	public NhanVien(int maNV, String chucVu, String diaChi, String dienThoai, String gioiTinh,
			double luongCoBan, LocalDate namSinh, String tenNV, Account account, String hinhAnh, String CMND) {
		super();
		this.maNV = maNV;
		this.chucVu = chucVu;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.gioiTinh = gioiTinh;
		this.hinhAnh = hinhAnh;
		this.luongCoBan = luongCoBan;
		this.namSinh = namSinh;
		this.tenNV = tenNV;
		this.CMND = CMND;
		this.account = account;
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
		builder.append(", hinhAnh=");
		builder.append(hinhAnh);
		builder.append(", luongCoBan=");
		builder.append(luongCoBan);
		builder.append(", namSinh=");
		builder.append(namSinh);
		builder.append(", tenNV=");
		builder.append(tenNV);
		builder.append("]");
		return builder.toString();
	}
	
	

	
	
	
}
