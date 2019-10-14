package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CTHopDong {
	@Id 
	private String CMND;
	private String diaChiKH;
	private String soDT; 
	private String tenKH; 
	
	@ManyToOne 
	@JoinColumn(name = "maHopDong")
	private HopDong hopDong;

	public CTHopDong() {
		super();
	}
	
	public CTHopDong(String cMND, String diaChiKH, String soDT, String tenKH, HopDong hopDong) {
		super();
		CMND = cMND;
		this.diaChiKH = diaChiKH;
		this.soDT = soDT;
		this.tenKH = tenKH;
		this.hopDong = hopDong;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public String getDiaChiKH() {
		return diaChiKH;
	}

	public void setDiaChiKH(String diaChiKH) {
		this.diaChiKH = diaChiKH;
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

	public HopDong getHopDong() {
		return hopDong;
	}

	public void setHopDong(HopDong hopDong) {
		this.hopDong = hopDong;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CTHopDong [CMND=");
		builder.append(CMND);
		builder.append(", diaChiKH=");
		builder.append(diaChiKH);
		builder.append(", soDT=");
		builder.append(soDT);
		builder.append(", tenKH=");
		builder.append(tenKH);
		builder.append(", hopDong=");
		builder.append(hopDong);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
