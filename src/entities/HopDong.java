package entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HopDong {
	@Id 
	private int maHopDong;
	private LocalDate ngayLap;
	private Double tienDatThanhToan;
	private Double tienPhaiThanhToan;
	
	
	@ManyToOne 
	@JoinColumn(name = "maNV",foreignKey = @ForeignKey())
	private NhanVien nhanVien;

	public HopDong() {
		super();
	}
	

	public HopDong(int maHopDong, LocalDate ngayLap, Double tienDatThanhToan, Double tienPhaiThanhToan,
			NhanVien nhanVien) {
		super();
		this.maHopDong = maHopDong;
		this.ngayLap = ngayLap;
		this.tienDatThanhToan = tienDatThanhToan;
		this.tienPhaiThanhToan = tienPhaiThanhToan;
		this.nhanVien = nhanVien;
	}


	public int getMaHopDong() {
		return maHopDong;
	}

	public void setMaHopDong(int maHopDong) {
		this.maHopDong = maHopDong;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public Double getTienDatThanhToan() {
		return tienDatThanhToan;
	}

	public void setTienDatThanhToan(Double tienDatThanhToan) {
		this.tienDatThanhToan = tienDatThanhToan;
	}

	public Double getTienPhaiThanhToan() {
		return tienPhaiThanhToan;
	}

	public void setTienPhaiThanhToan(Double tienPhaiThanhToan) {
		this.tienPhaiThanhToan = tienPhaiThanhToan;
	}


	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}


	public HopDong(int maHopDong) {
		super();
		this.maHopDong = maHopDong;
	}

	
	
	
	
}
