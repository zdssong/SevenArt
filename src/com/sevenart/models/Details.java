package com.sevenart.models;

import java.io.Serializable;
import java.util.List;

/*
 * 文章详细接口
 * */
public class Details implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PictureSetDetails getPictureSetDetails() {
		return new PictureSetDetails();
	}

	public OrdinaryEssayDetails getEssayDetailsInstance() {
		return new OrdinaryEssayDetails();
	}

	public PictureDetails getPictureDetails() {
		return new PictureDetails();
	}

	public PictureDetails1 getPictureDetails1() {
		return new PictureDetails1();
	}

	public OrdinaryEssayDetails getEssayDetails() {
		return new OrdinaryEssayDetails();
	}

	public ShowAndAuctionDetails getShowDetails() {
		return new ShowAndAuctionDetails();
	}

	public class PictureDetails1 implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String wId;
		private String aId;
		private String introduce;
		private String basePrice;
		private String amplitude;
		private String umgurls;

		public PictureDetails1() {
			super();
			// TODO Auto-generated constructor stub
		}

		public PictureDetails1(String wId, String aId, String introduce,
				String basePrice, String amplitude, String umgurls) {
			super();
			this.wId = wId;
			this.aId = aId;
			this.introduce = introduce;
			this.basePrice = basePrice;
			this.amplitude = amplitude;
			this.umgurls = umgurls;
		}

		public String getwId() {
			return wId;
		}

		public void setwId(String wId) {
			this.wId = wId;
		}

		public String getaId() {
			return aId;
		}

		public void setaId(String aId) {
			this.aId = aId;
		}

		public String getIntroduce() {
			return introduce;
		}

		public void setIntroduce(String introduce) {
			this.introduce = introduce;
		}

		public String getBasePrice() {
			return basePrice;
		}

		public void setBasePrice(String basePrice) {
			this.basePrice = basePrice;
		}

		public String getAmplitude() {
			return amplitude;
		}

		public void setAmplitude(String amplitude) {
			this.amplitude = amplitude;
		}

		public String getUmgurls() {
			return umgurls;
		}

		public void setUmgurls(String umgurls) {
			this.umgurls = umgurls;
		}

	}

	public class PictureDetails implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String text;
		private String img;

		public PictureDetails() {
			super();
			// TODO Auto-generated constructor stub
		}

		public PictureDetails(String text, String img) {
			super();
			this.text = text;
			this.img = img;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

	}

	// ====普通文章====
	public class OrdinaryEssayDetails implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String id;
		private String typeId;
		private String typeId2;
		private String flag;
		private String channel;
		private String click;
		private String title;
		private String shortTitle;
		private String color;
		private String writer;
		private String litpic;
		private String pubDate;
		private String sendDate;
		private String mId;
		private String keywords;
		private String description;
		private String channelName;
		private String fc;
		private String face;
		private String qrcode;
		private String shareUrl;

		public OrdinaryEssayDetails() {
			super();
			// TODO Auto-generated constructor stub
		}

		public OrdinaryEssayDetails(String id, String typeId, String typeId2,
				String flag, String channel, String click, String title,
				String shortTitle, String color, String writer, String litpic,
				String pubDate, String sendDate, String mId, String keywords,
				String description, String channelName, String fc, String face,
				String qrcode, String shareUrl) {
			super();
			this.id = id;
			this.typeId = typeId;
			this.typeId2 = typeId2;
			this.flag = flag;
			this.channel = channel;
			this.click = click;
			this.title = title;
			this.shortTitle = shortTitle;
			this.color = color;
			this.writer = writer;
			this.litpic = litpic;
			this.pubDate = pubDate;
			this.sendDate = sendDate;
			this.mId = mId;
			this.keywords = keywords;
			this.description = description;
			this.channelName = channelName;
			this.fc = fc;
			this.face = face;
			this.qrcode = qrcode;
			this.shareUrl = shareUrl;
		}

		public String getFc() {
			return fc;
		}

		public void setFc(String fc) {
			this.fc = fc;
		}

		public String getFace() {
			return face;
		}

		public void setFace(String face) {
			this.face = face;
		}

		public String getQrcode() {
			return qrcode;
		}

		public void setQrcode(String qrcode) {
			this.qrcode = qrcode;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTypeId() {
			return typeId;
		}

		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}

		public String getTypeId2() {
			return typeId2;
		}

		public void setTypeId2(String typeId2) {
			this.typeId2 = typeId2;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public String getClick() {
			return click;
		}

		public void setClick(String click) {
			this.click = click;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getShortTitle() {
			return shortTitle;
		}

		public void setShortTitle(String shortTitle) {
			this.shortTitle = shortTitle;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String getWriter() {
			return writer;
		}

		public void setWriter(String writer) {
			this.writer = writer;
		}

		public String getLitpic() {
			return litpic;
		}

		public void setLitpic(String litpic) {
			this.litpic = litpic;
		}

		public String getPubDate() {
			return pubDate;
		}

		public void setPubDate(String pubDate) {
			this.pubDate = pubDate;
		}

		public String getSendDate() {
			return sendDate;
		}

		public void setSendDate(String sendDate) {
			this.sendDate = sendDate;
		}

		public String getmId() {
			return mId;
		}

		public void setmId(String mId) {
			this.mId = mId;
		}

		public String getKeywords() {
			return keywords;
		}

		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getChannelName() {
			return channelName;
		}

		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}

		public String getShareUrl() {
			return shareUrl;
		}

		public void setShareUrl(String shareUrl) {
			this.shareUrl = shareUrl;
		}

	}

	// ====图片集====
	public class PictureSetDetails implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String id;
		private String typeId;
		private String typeId2;
		private String flag;
		private String channel;
		private String click;
		private String title;
		private String shortTitle;
		private String color;
		private String writer;
		private String litpic;
		private String pubDate;
		private String sendDate;
		private String mId;
		private String keywords;
		private String description;
		private String channelName;
		private List<PictureDetails> pictures;
		private String face;
		private String fc;
		private String qrcode;
		private String shareUrl;

		public PictureSetDetails() {
			super();
			// TODO Auto-generated constructor stub
		}

		public PictureSetDetails(String id, String typeId, String typeId2,
				String flag, String channel, String click, String title,
				String shortTitle, String color, String writer, String litpic,
				String pubDate, String sendDate, String mId, String keywords,
				String description, String channelName,
				List<PictureDetails> pictures, String face, String fc,
				String qrcode, String shareUrl) {
			super();
			this.id = id;
			this.typeId = typeId;
			this.typeId2 = typeId2;
			this.flag = flag;
			this.channel = channel;
			this.click = click;
			this.title = title;
			this.shortTitle = shortTitle;
			this.color = color;
			this.writer = writer;
			this.litpic = litpic;
			this.pubDate = pubDate;
			this.sendDate = sendDate;
			this.mId = mId;
			this.keywords = keywords;
			this.description = description;
			this.channelName = channelName;
			this.pictures = pictures;
			this.face = face;
			this.fc = fc;
			this.qrcode = qrcode;
			this.shareUrl = shareUrl;
		}

		public String getFc() {
			return fc;
		}

		public void setFc(String fc) {
			this.fc = fc;
		}

		public String getFace() {
			return face;
		}

		public void setFace(String face) {
			this.face = face;
		}

		public String getQrcode() {
			return qrcode;
		}

		public void setQrcode(String qrcode) {
			this.qrcode = qrcode;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTypeId() {
			return typeId;
		}

		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}

		public String getTypeId2() {
			return typeId2;
		}

		public void setTypeId2(String typeId2) {
			this.typeId2 = typeId2;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public String getClick() {
			return click;
		}

		public void setClick(String click) {
			this.click = click;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getShortTitle() {
			return shortTitle;
		}

		public void setShortTitle(String shortTitle) {
			this.shortTitle = shortTitle;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String getWriter() {
			return writer;
		}

		public void setWriter(String writer) {
			this.writer = writer;
		}

		public String getLitpic() {
			return litpic;
		}

		public void setLitpic(String litpic) {
			this.litpic = litpic;
		}

		public String getPubDate() {
			return pubDate;
		}

		public void setPubDate(String pubDate) {
			this.pubDate = pubDate;
		}

		public String getSendDate() {
			return sendDate;
		}

		public void setSendDate(String sendDate) {
			this.sendDate = sendDate;
		}

		public String getmId() {
			return mId;
		}

		public void setmId(String mId) {
			this.mId = mId;
		}

		public String getKeywords() {
			return keywords;
		}

		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getChannelName() {
			return channelName;
		}

		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}

		public List<PictureDetails> getPictures() {
			return pictures;
		}

		public void setPictures(List<PictureDetails> pictures) {
			this.pictures = pictures;
		}

		public String getShareUrl() {
			return shareUrl;
		}

		public void setShareUrl(String shareUrl) {
			this.shareUrl = shareUrl;
		}

	}

	// ====展览和拍卖=====
	public class ShowAndAuctionDetails implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String id;
		private String typeId;
		private String typeId2;
		private String flag;
		private String channel;
		private String click;
		private String title;
		private String shortTitle;
		private String color;
		private String writer;
		private String litpic;
		private String pubDate;
		private String sendDate;
		private String mId;
		private String keywords;
		private String description;
		private String channelName;
		private String exhibition;
		private String address;
		private String startTime;
		private String endTime;
		private String leftTime;
		private List<PictureDetails> pictures;
		private List<PictureDetails1> pictures1;
		private String fc;
		private String face;
		private String spaceName;
		private String qrcode;
		private String shareUrl;

		public ShowAndAuctionDetails() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ShowAndAuctionDetails(String id, String typeId, String typeId2,
				String flag, String channel, String click, String title,
				String shortTitle, String color, String writer, String litpic,
				String pubDate, String sendDate, String mId, String keywords,
				String description, String channelName, String exhibition,
				String address, String startTime, String endTime,
				String leftTime, List<PictureDetails> pictures,
				List<PictureDetails1> pictures1, String fc, String face,
				String spaceName, String qrcode, String shareUrl) {
			super();
			this.id = id;
			this.typeId = typeId;
			this.typeId2 = typeId2;
			this.flag = flag;
			this.channel = channel;
			this.click = click;
			this.title = title;
			this.shortTitle = shortTitle;
			this.color = color;
			this.writer = writer;
			this.litpic = litpic;
			this.pubDate = pubDate;
			this.sendDate = sendDate;
			this.mId = mId;
			this.keywords = keywords;
			this.description = description;
			this.channelName = channelName;
			this.exhibition = exhibition;
			this.address = address;
			this.startTime = startTime;
			this.endTime = endTime;
			this.leftTime = leftTime;
			this.pictures = pictures;
			this.pictures1 = pictures1;
			this.fc = fc;
			this.face = face;
			this.spaceName = spaceName;
			this.qrcode = qrcode;
			this.shareUrl = shareUrl;
		}

		public String getExhibition() {
			return exhibition;
		}

		public void setExhibition(String exhibition) {
			this.exhibition = exhibition;
		}

		public String getLeftTime() {
			return leftTime;
		}

		public void setLeftTime(String leftTime) {
			this.leftTime = leftTime;
		}

		public String getSpaceName() {
			return spaceName;
		}

		public void setSpaceName(String spaceName) {
			this.spaceName = spaceName;
		}

		public String getFc() {
			return fc;
		}

		public void setFc(String fc) {
			this.fc = fc;
		}

		public List<PictureDetails1> getPictures1() {
			return pictures1;
		}

		public void setPictures1(List<PictureDetails1> pictures1) {
			this.pictures1 = pictures1;
		}

		public String getFace() {
			return face;
		}

		public void setFace(String face) {
			this.face = face;
		}

		public String getQrcode() {
			return qrcode;
		}

		public void setQrcode(String qrcode) {
			this.qrcode = qrcode;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTypeId() {
			return typeId;
		}

		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}

		public String getTypeId2() {
			return typeId2;
		}

		public void setTypeId2(String typeId2) {
			this.typeId2 = typeId2;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public String getClick() {
			return click;
		}

		public void setClick(String click) {
			this.click = click;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getShortTitle() {
			return shortTitle;
		}

		public void setShortTitle(String shortTitle) {
			this.shortTitle = shortTitle;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String getWriter() {
			return writer;
		}

		public void setWriter(String writer) {
			this.writer = writer;
		}

		public String getLitpic() {
			return litpic;
		}

		public void setLitpic(String litpic) {
			this.litpic = litpic;
		}

		public String getPubDate() {
			return pubDate;
		}

		public void setPubDate(String pubDate) {
			this.pubDate = pubDate;
		}

		public String getSendDate() {
			return sendDate;
		}

		public void setSendDate(String sendDate) {
			this.sendDate = sendDate;
		}

		public String getmId() {
			return mId;
		}

		public void setmId(String mId) {
			this.mId = mId;
		}

		public String getKeywords() {
			return keywords;
		}

		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getChannelName() {
			return channelName;
		}

		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public List<PictureDetails> getPictures() {
			return pictures;
		}

		public void setPictures(List<PictureDetails> pictures) {
			this.pictures = pictures;
		}

		public String getShareUrl() {
			return shareUrl;
		}

		public void setShareUrl(String shareUrl) {
			this.shareUrl = shareUrl;
		}
	}
}
