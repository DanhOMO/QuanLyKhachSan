package com.quanlykhachsan.entity;

import java.util.Objects;

public class Phong_ThietBi {
	private Phong phong;
	private ThietBi thietBi;
	public Phong_ThietBi(Phong phong, ThietBi thietBi) {
		super();
		this.phong = phong;
		this.thietBi = thietBi;
	}
	public Phong_ThietBi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	public ThietBi getThietBi() {
		return thietBi;
	}
	public void setThietBi(ThietBi thietBi) {
		this.thietBi = thietBi;
	}
	@Override
	public int hashCode() {
		return Objects.hash(phong, thietBi);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong_ThietBi other = (Phong_ThietBi) obj;
		return Objects.equals(phong, other.phong) && Objects.equals(thietBi, other.thietBi);
	}
	
	
}
