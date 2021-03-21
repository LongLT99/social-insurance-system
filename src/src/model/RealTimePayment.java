package src.model;

import java.util.Date;

public class RealTimePayment {
	private int id,nhanvienid,loaibhid,iddonvibh;
	private float sotien;
	private String tennganhang, magiaodich, noidunggd, phuongthuc;
	private Date thoigiandong, handong;
	public RealTimePayment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RealTimePayment(int id, int nhanvienid, int loaibhid, int iddonvibh, float sotien, String tennganhang,
			String magiaodich, String noidunggd, String phuongthuc, Date thoigiandong, Date handong) {
		super();
		this.id = id;
		this.nhanvienid = nhanvienid;
		this.loaibhid = loaibhid;
		this.iddonvibh = iddonvibh;
		this.sotien = sotien;
		this.tennganhang = tennganhang;
		this.magiaodich = magiaodich;
		this.noidunggd = noidunggd;
		this.phuongthuc = phuongthuc;
		this.thoigiandong = thoigiandong;
		this.handong = handong;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNhanvienid() {
		return nhanvienid;
	}
	public void setNhanvienid(int nhanvienid) {
		this.nhanvienid = nhanvienid;
	}
	public int getLoaibhid() {
		return loaibhid;
	}
	public void setLoaibhid(int loaibhid) {
		this.loaibhid = loaibhid;
	}
	public int getIddonvibh() {
		return iddonvibh;
	}
	public void setIddonvibh(int iddonvibh) {
		this.iddonvibh = iddonvibh;
	}
	public float getSotien() {
		return sotien;
	}
	public void setSotien(float sotien) {
		this.sotien = sotien;
	}
	public String getTennganhang() {
		return tennganhang;
	}
	public void setTennganhang(String tennganhang) {
		this.tennganhang = tennganhang;
	}
	public String getMagiaodich() {
		return magiaodich;
	}
	public void setMagiaodich(String magiaodich) {
		this.magiaodich = magiaodich;
	}
	public String getNoidunggd() {
		return noidunggd;
	}
	public void setNoidunggd(String noidunggd) {
		this.noidunggd = noidunggd;
	}
	public String getPhuongthuc() {
		return phuongthuc;
	}
	public void setPhuongthuc(String phuongthuc) {
		this.phuongthuc = phuongthuc;
	}
	public Date getThoigiandong() {
		return thoigiandong;
	}
	public void setThoigiandong(Date thoigiandong) {
		this.thoigiandong = thoigiandong;
	}
	public Date getHandong() {
		return handong;
	}
	public void setHandong(Date handong) {
		this.handong = handong;
	}

}
