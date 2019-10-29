package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Loaixe {
	@Id
	private String maloai;
	@Column(columnDefinition = "nvarchar(100)")
	private String loaixe;
	@Column(columnDefinition = "nvarchar(100)")
	private String tenxe;
	@Column(columnDefinition = "nvarchar(50)")
	private String mauson;
	@Column(columnDefinition = "nvarchar(100)")
	private String nuocSX;
	private String hinhanh;
	@Column(columnDefinition = "nvarchar(50)")
	private String nhanhieu;

	public String getMaloai() {
		return maloai;
	}

	public void setMaloai(String maloai) {
		this.maloai = maloai;
	}


	public String getLoaixe() {
		return loaixe;
	}

	public void setLoaixe(String loaixe) {
		this.loaixe = loaixe;
	}

	public String getTenxe() {
		return tenxe;
	}

	public void setTenxe(String tenxe) {
		this.tenxe = tenxe;
	}

	public String getMauson() {
		return mauson;
	}

	public void setMauson(String mauson) {
		this.mauson = mauson;
	}

	public String getNuocSX() {
		return nuocSX;
	}

	public void setNuocSX(String nuocSX) {
		this.nuocSX = nuocSX;
	}

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}

	public String getNhanhieu() {
		return nhanhieu;
	}

	public void setNhanhieu(String nhanhieu) {
		this.nhanhieu = nhanhieu;
	}

	

	public Loaixe(String maloai, String loaixe, String tenxe, String mauson, String nuocSX, String hinhanh,
			String nhanhieu) {
		super();
		this.maloai = maloai;
		this.loaixe = loaixe;
		this.tenxe = tenxe;
		this.mauson = mauson;
		this.nuocSX = nuocSX;
		this.hinhanh = hinhanh;
		this.nhanhieu = nhanhieu;
	}

	public Loaixe() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Loaixe [maloai=");
		builder.append(maloai);
		builder.append(", loaixe=");
		builder.append(loaixe);
		builder.append(", tenxe=");
		builder.append(tenxe);
		builder.append(", mauson=");
		builder.append(mauson);
		builder.append(", nuocSX=");
		builder.append(nuocSX);
		builder.append(", hinhanh=");
		builder.append(hinhanh);
		builder.append(", nhanhieu=");
		builder.append(nhanhieu);
		builder.append("]");
		return builder.toString();
	}

	
}
