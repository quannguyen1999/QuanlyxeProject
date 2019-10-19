package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CTPhieuXuatPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer phieuXuat;
	private String xe;
	
	public CTPhieuXuatPK(int phieuXuat, String xe) {
		super();
		this.phieuXuat = phieuXuat;
		this.xe = xe;
	}
	public CTPhieuXuatPK() {
		super();
	}
	public int getPhieuXuat() {
		return phieuXuat;
	}
	public void setPhieuXuat(int phieuXuat) {
		this.phieuXuat = phieuXuat;
	}
	public String getXe() {
		return xe;
	}
	public void setXe(String xe) {
		this.xe = xe;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CTPhieuXuatPK [phieuXuat=");
		builder.append(phieuXuat);
		builder.append(", xe=");
		builder.append(xe);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + phieuXuat;
		result = prime * result + ((xe == null) ? 0 : xe.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTPhieuXuatPK other = (CTPhieuXuatPK) obj;
		if (phieuXuat != other.phieuXuat)
			return false;
		if (xe == null) {
			if (other.xe != null)
				return false;
		} else if (!xe.equals(other.xe))
			return false;
		return true;
	}
	
	
	
}
