package entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class HopDong {
	@Id
	private int maHopDong;

	@ManyToOne
	@JoinColumn(name = "maXe")
	private Xe xe;

	private LocalDate ngayLap;
	// bên bán
	@Column(columnDefinition = "nvarchar(50)")
	private String maNV;
	@Column(columnDefinition = "nvarchar(50)")
	private String tenNguoiBan;
	@Column(columnDefinition = "nvarchar(50)")
	private String CMNDNB;
	@Column(columnDefinition = "nvarchar(50)")
	private String NoiONB;
	@Column(columnDefinition = "nvarchar(50)")
	private String soDTNB;

	@Column(columnDefinition = "nvarchar(50)")
	private String trangThai;

	// bên mua
	@Column(columnDefinition = "nvarchar(50)")
	private String maKH;
	@Column(columnDefinition = "nvarchar(50)")
	private String tenNguoiMua;
	@Column(columnDefinition = "nvarchar(50)")
	private String CMNDNM;
	@Column(columnDefinition = "nvarchar(50)")
	private String NoiONM;
	@Column(columnDefinition = "nvarchar(50)")
	private String soDTNM;
	private Double tienDatThanhToan;

	private int SoLuongMua;
	
	@OneToMany(mappedBy = "hopDong")
	private List<PhieuXuat> dspx;

	
	
	
	

	public HopDong(int maHopDong, Xe xe, LocalDate ngayLap, String maNV, String tenNguoiBan, String cMNDNB,
			String noiONB, String soDTNB, String trangThai, String maKH, String tenNguoiMua, String cMNDNM,
			String noiONM, String soDTNM, Double tienDatThanhToan, int soLuongMua) {
		super();
		this.maHopDong = maHopDong;
		this.xe = xe;
		this.ngayLap = ngayLap;
		this.maNV = maNV;
		this.tenNguoiBan = tenNguoiBan;
		CMNDNB = cMNDNB;
		NoiONB = noiONB;
		this.soDTNB = soDTNB;
		this.trangThai = trangThai;
		this.maKH = maKH;
		this.tenNguoiMua = tenNguoiMua;
		CMNDNM = cMNDNM;
		NoiONM = noiONM;
		this.soDTNM = soDTNM;
		this.tienDatThanhToan = tienDatThanhToan;
		SoLuongMua = soLuongMua;
	}



	public String getTrangThai() {
		return trangThai;
	}



	public int getSoLuongMua() {
		return SoLuongMua;
	}



	public void setSoLuongMua(int soLuongMua) {
		SoLuongMua = soLuongMua;
	}



	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}



	public HopDong() {
		super();
	}

	public int getMaHopDong() {
		return maHopDong;
	}

	public void setMaHopDong(int maHopDong) {
		this.maHopDong = maHopDong;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getTenNguoiBan() {
		return tenNguoiBan;
	}

	public void setTenNguoiBan(String tenNguoiBan) {
		this.tenNguoiBan = tenNguoiBan;
	}

	public String getCMNDNB() {
		return CMNDNB;
	}

	public void setCMNDNB(String cMNDNB) {
		CMNDNB = cMNDNB;
	}

	public String getNoiONB() {
		return NoiONB;
	}

	public void setNoiONB(String noiONB) {
		NoiONB = noiONB;
	}

	public String getSoDTNB() {
		return soDTNB;
	}

	public void setSoDTNB(String soDTNB) {
		this.soDTNB = soDTNB;
	}

	public String getTenNguoiMua() {
		return tenNguoiMua;
	}

	public void setTenNguoiMua(String tenNguoiMua) {
		this.tenNguoiMua = tenNguoiMua;
	}

	public String getCMNDNM() {
		return CMNDNM;
	}

	public void setCMNDNM(String cMNDNM) {
		CMNDNM = cMNDNM;
	}

	public String getNoiONM() {
		return NoiONM;
	}

	public void setNoiONM(String noiONM) {
		NoiONM = noiONM;
	}

	public String getSoDTNM() {
		return soDTNM;
	}

	public void setSoDTNM(String soDTNM) {
		this.soDTNM = soDTNM;
	}

	public Double getTienDatThanhToan() {
		return tienDatThanhToan;
	}

	public void setTienDatThanhToan(Double tienDatThanhToan) {
		this.tienDatThanhToan = tienDatThanhToan;
	}

	public List<PhieuXuat> getDspx() {
		return dspx;
	}

	public void setDspx(List<PhieuXuat> dspx) {
		this.dspx = dspx;
	}

	public Xe getXe() {
		return xe;
	}

	public void setXe(Xe xe) {
		this.xe = xe;
	}

	@Override
	public String toString() {
		return "HopDong [maHopDong=" + maHopDong + ", xe=" + xe + ", ngayLap=" + ngayLap + ", maNV=" + maNV
				+ ", tenNguoiBan=" + tenNguoiBan + ", CMNDNB=" + CMNDNB + ", NoiONB=" + NoiONB + ", soDTNB=" + soDTNB
				+ ", maKH=" + maKH + ", tenNguoiMua=" + tenNguoiMua + ", CMNDNM=" + CMNDNM + ", NoiONM=" + NoiONM
				+ ", soDTNM=" + soDTNM + ", tienDatThanhToan=" + tienDatThanhToan + "]";
	}

}
