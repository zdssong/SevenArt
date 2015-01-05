package com.sevenart.models;

import java.io.Serializable;

/*
 * 个人主页的实体类
 * */
public class PersonalPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7290798242994763953L;

	public Cmtype getCmtype() {
		return new Cmtype();
	}

	public About getAbout() {
		return new About();
	}

	// cmtype字段
	public class Cmtype {
		private String mtypeId;
		private String mtypeName;
		private String channelId;
		private String mid;

		public Cmtype() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Cmtype(String mtypeId, String mtypeName, String channelId,
				String mid) {
			super();
			this.mtypeId = mtypeId;
			this.mtypeName = mtypeName;
			this.channelId = channelId;
			this.mid = mid;
		}

		public String getMtypeId() {
			return mtypeId;
		}

		public void setMtypeId(String mtypeId) {
			this.mtypeId = mtypeId;
		}

		public String getMtypeName() {
			return mtypeName;
		}

		public void setMtypeName(String mtypeName) {
			this.mtypeName = mtypeName;
		}

		public String getChannelId() {
			return channelId;
		}

		public void setChannelId(String channelId) {
			this.channelId = channelId;
		}

		public String getMid() {
			return mid;
		}

		public void setMid(String mid) {
			this.mid = mid;
		}

	}

	// about字段
	public class About {
		private String title;
		private String description;
		private String telephone;
		private String email;
		private String address;
		private String qrcode;

		public About() {
			super();
			// TODO Auto-generated constructor stub
		}

		public About(String title, String description, String telephone,
				String email, String address, String qrcode) {
			super();
			this.title = title;
			this.description = description;
			this.telephone = telephone;
			this.email = email;
			this.address = address;
			this.qrcode = qrcode;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getQrcode() {
			return qrcode;
		}

		public void setQrcode(String qrcode) {
			this.qrcode = qrcode;
		}

	}

}
