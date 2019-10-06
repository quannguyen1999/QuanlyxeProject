package entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(CTPhieuXuatPK.class)
public class CTPhieuXuat {
	@Id
	@ManyToOne 
	@JoinColumn(name = "maPX",foreignKey = @ForeignKey)
	private PhieuXuat phieuXuat; 
	
	@Id
	@ManyToOne
	@JoinColumn(name = "maXe",foreignKey = @ForeignKey)
	private Xe xe; 
	
	private double donGiaXuat;
	private int sLXuat;
	private double thue;
	public CTPhieuXuat() {
		super();
	}
	public PhieuXuat getPhieuXuat() {
		return phieuXuat;
	}
	public void setPhieuXuat(PhieuXuat phieuXuat) {
		this.phieuXuat = phieuXuat;
	}
	public Xe getXe() {
		return xe;
	}
	public void setXe(Xe xe) {
		this.xe = xe;
	}
	public double getDonGiaXuat() {
		return donGiaXuat;
	}
	public void setDonGiaXuat(double donGiaXuat) {
		this.donGiaXuat = donGiaXuat;
	}
	public int getsLXuat() {
		return sLXuat;
	}
	public void setsLXuat(int sLXuat) {
		this.sLXuat = sLXuat;
	}
	public double getThue() {
		return thue;
	}
	public void setThue(double thue) {
		this.thue = thue;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CTPhieuXuat [phieuXuat=");
		builder.append(phieuXuat);
		builder.append(", xe=");
		builder.append(xe);
		builder.append(", donGiaXuat=");
		builder.append(donGiaXuat);
		builder.append(", sLXuat=");
		builder.append(sLXuat);
		builder.append(", thue=");
		builder.append(thue);
		builder.append("]");
		return builder.toString();
	} 
	
	
}
