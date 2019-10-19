package entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class HopDong {
	@Id
	private int maHopDong;
	
	private LocalDate ngayLap;
	//bên bán 
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
	
	//bên mua
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

	@OneToMany(mappedBy = "hopDong")
	private List<PhieuXuat> dspx;

	

	public HopDong(int maHopDong, LocalDate ngayLap, String maNV, String tenNguoiBan, String cMNDNB, String noiONB,
			String soDTNB, String maKH, String tenNguoiMua, String cMNDNM, String noiONM, String soDTNM,
			Double tienDatThanhToan) {
		super();
		this.maHopDong = maHopDong;
		this.ngayLap = ngayLap;
		this.maNV = maNV;
		this.tenNguoiBan = tenNguoiBan;
		CMNDNB = cMNDNB;
		NoiONB = noiONB;
		this.soDTNB = soDTNB;
		this.maKH = maKH;
		this.tenNguoiMua = tenNguoiMua;
		CMNDNM = cMNDNM;
		NoiONM = noiONM;
		this.soDTNM = soDTNM;
		this.tienDatThanhToan = tienDatThanhToan;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HopDong [maHopDong=");
		builder.append(maHopDong);
		builder.append(", ngayLap=");
		builder.append(ngayLap);
		builder.append(", tenNguoiBan=");
		builder.append(tenNguoiBan);
		builder.append(", CMNDNB=");
		builder.append(CMNDNB);
		builder.append(", NoiONB=");
		builder.append(NoiONB);
		builder.append(", soDTNB=");
		builder.append(soDTNB);
		builder.append(", tenNguoiMua=");
		builder.append(tenNguoiMua);
		builder.append(", CMNDNM=");
		builder.append(CMNDNM);
		builder.append(", NoiONM=");
		builder.append(NoiONM);
		builder.append(", soDTNM=");
		builder.append(soDTNM);
		builder.append(", tienDatThanhToan=");
		builder.append(tienDatThanhToan);
		builder.append("]");
		return builder.toString();
	}

	

	

	

}
