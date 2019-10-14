package entities;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KhachHang {
	@Id 
	private int maKH;
	private String diaChi;
	private String email;
	private String soDT;
	private String tenKH;
	

	public KhachHang() {
		super();
	}
	
	public KhachHang(int maKH, String diaChi, String email, String soDT, String tenKH) {
		super();
		this.maKH = maKH;
		this.diaChi = diaChi;
		this.email = email;
		this.soDT = soDT;
		this.tenKH = tenKH;
	}

	public int getMaKH() {
		return maKH;
	}

	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(maKH);
		return builder.toString();
	}
	

	
	
}
