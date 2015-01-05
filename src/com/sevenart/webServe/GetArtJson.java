package com.sevenart.webServe;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.sevenart.models.Auction;
import com.sevenart.models.Care;
import com.sevenart.models.Column;
import com.sevenart.models.Commend;
import com.sevenart.models.Comment;
import com.sevenart.models.Details;
import com.sevenart.models.Details.PictureDetails;
import com.sevenart.models.Details.PictureDetails1;
import com.sevenart.models.Details.PictureSetDetails;
import com.sevenart.models.Details.ShowAndAuctionDetails;
import com.sevenart.models.Information;
import com.sevenart.models.Member;
import com.sevenart.models.PersonalPage;
import com.sevenart.models.PersonalPage.About;
import com.sevenart.models.Records;
import com.sevenart.models.Register;
import com.sevenart.models.Details.OrdinaryEssayDetails;
import com.sevenart.models.PersonalPage.Cmtype;

/*
 * 该类是用来解析从服务器中获取的Json数据
 * */

public class GetArtJson {

	public static String msg;// 输出错误信息
	public static String mid;

	private Details m_Details;
	private PersonalPage m_PersonalPage;
	private int m_count;

	private List<Column> m_ColumnList;
	private List<Commend> m_CommendList;
	private List<Information> m_InformationList;
	private List<Register> m_RegisterList;
	private PictureSetDetails m_PictureSetDetails;
	private ShowAndAuctionDetails m_ShowAndAuctionDetails;
	private List<Member> m_Menbers;
	private List<Cmtype> m_Cmtypes;
	private About m_Abouts;

	private List<Comment> mComments;

	private Auction mAuction;
	private List<Care> mCares;

	public GetArtJson() {
		m_ColumnList = new ArrayList<Column>();
		m_CommendList = new ArrayList<Commend>();
		m_InformationList = new ArrayList<Information>();
		m_RegisterList = new ArrayList<Register>();

		m_Details = new Details();
		m_PictureSetDetails = m_Details.getPictureSetDetails();
		m_ShowAndAuctionDetails = m_Details.getShowDetails();
		m_Menbers = new ArrayList<Member>();
		m_PersonalPage = new PersonalPage();
		m_Cmtypes = new ArrayList<PersonalPage.Cmtype>();
		m_Abouts = m_PersonalPage.getAbout();
		mComments = new ArrayList<Comment>();
		mAuction = new Auction();
		mCares = new ArrayList<Care>();
	}

	public List<Care> getCare(JSONObject json) throws JSONException {
		int code = json.getInt("code");
		if (code == 0) {
			JSONArray array = json.getJSONArray("data");
			m_count = array.length();
			for (int i = 0; i < m_count; i++) {
				Care mCare = new Care();
				mCare.setmMid(array.getJSONObject(i).getString("mid"));
				mCare.setUserId(array.getJSONObject(i).getString("userid"));
				mCare.setuName(array.getJSONObject(i).getString("uname"));
				mCare.setRank(array.getJSONObject(i).getString("rank"));
				mCare.setFace(array.getJSONObject(i).getString("face"));
				mCares.add(mCare);
			}
		}
		return mCares;
	}

	public Auction getAuction(JSONObject json) throws JSONException {
		int code = json.getInt("code");
		if (code == 0) {
			JSONObject jsonObject = json.getJSONObject("data");
			mAuction.setWidString(jsonObject.getString("wid"));
			mAuction.setAidString(jsonObject.getString("aid"));
			mAuction.setTypeidString(jsonObject.getString("typeid"));
			mAuction.setIntroductString(jsonObject.getString("introduce"));
			mAuction.setPagepicnum(jsonObject.getInt("pagepicnum"));
			JSONArray jsonArray1 = jsonObject.getJSONArray("imgurls");
			int count = jsonArray1.length();
			String[] strArray = new String[count];
			if (count != 0) {
				for (int j = 0; j < count; j++) {
					strArray[j] = jsonArray1.getString(j);
				}
				mAuction.setImgurlStrings(strArray);
			} else {
				mAuction.setImgurlStrings(strArray);
			}
			mAuction.setBasePrice(jsonObject.getInt("baseprice"));
			mAuction.setAmplitude(jsonObject.getInt("amplitude"));
			mAuction.setCashdepositString(jsonObject.getString("cashdeposit"));
			mAuction.setCurpriceString(jsonObject.getString("curprice"));
			mAuction.setAddtimeString(jsonObject.getString("addtime"));
			mAuction.setSuccessmidString(jsonObject.getString("successmid"));
			mAuction.setSuccessunameString(jsonObject.getString("successuname"));
			mAuction.setUseripString(jsonObject.getString("userip"));
			mAuction.setNumber(jsonObject.getInt("number"));
			mAuction.setEndTimesTamp(jsonObject.getInt("endtimestamp"));
			mAuction.setLeftTimesTamp(jsonObject.getInt("lefttimestamp"));

			JSONArray jsonArray2 = jsonObject.getJSONArray("records");
			count = jsonArray2.length();
			List<Records> recordsList = new ArrayList<Records>();
			if (count != 0) {
				for (int j = 0; j < count; j++) {
					Records records = new Records();
					JSONObject object = jsonArray2.getJSONObject(j);
					records.setId(object.getString("id"));
					records.setAid(object.getString("aid"));
					records.setWid(object.getString("wid"));
					records.setMid(object.getString("mid"));
					records.setUserId(object.getString("userid"));
					records.setPrice(object.getInt("price"));
					records.setStatus(object.getString("status"));
					records.setFace(object.getString("face"));
					records.setTime(object.getString("time"));
					recordsList.add(records);
				}
				mAuction.setRecords(recordsList);
			} else {
				mAuction.setRecords(recordsList);
			}
		}
		return mAuction;
	}

	// 获得栏目列表
	public List<Column> getColumnJson(JSONObject json) throws JSONException {
		m_ColumnList = new ArrayList<Column>();
		JSONArray jsonArray = json.getJSONArray("data");
		m_count = jsonArray.length();
		for (int i = 0; i < m_count; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Column column = new Column();
			column.setTypeId(jsonObject.getInt("typeid"));
			column.setTypeName(jsonObject.getString("typename"));
			column.setChannel(jsonObject.getInt("channel"));
			column.setChannelName(jsonObject.getString("channelname"));
			if (jsonObject.getInt("channel") == -3) {
				mid = jsonObject.getString("mid");
			}
			m_ColumnList.add(column);
		}
		return m_ColumnList;
	}

	// 解析推荐数据
	public List<Commend> getCommendJson(JSONObject json) throws JSONException {
		m_CommendList = new ArrayList<Commend>();
		JSONArray jsonArray = json.getJSONArray("commend");
		m_count = jsonArray.length();
		for (int i = 0; i < m_count; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Commend commend = new Commend();
			commend.setId(jsonObject.getString("id"));
			commend.setTypeId(jsonObject.getString("typeid"));
			commend.setFlag(jsonObject.getString("flag"));
			commend.setChannel(jsonObject.getString("channel"));
			commend.setArcRank(jsonObject.getString("arcrank"));
			commend.setTitle(jsonObject.getString("title"));
			commend.setLitpic(jsonObject.getString("litpic"));
			commend.setChannelName(jsonObject.getString("channelname"));
			commend.setRedireCuturl(jsonObject.getString("redirecturl"));
			m_CommendList.add(commend);
		}
		return m_CommendList;
	}

	// 解析资讯数据
	public List<Information> getInformationJson(JSONObject json)
			throws JSONException {
		m_InformationList = new ArrayList<Information>();
		int code = json.getInt("code");
		if (code == 0) {
			JSONArray jsonArray = json.getJSONArray("data");
			m_count = jsonArray.length();
			for (int i = 0; i < m_count; i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Information information = new Information();
				information.setId(jsonObject.getString("id"));
				information.setTypeId(jsonObject.getString("typeid"));
				information.setSendDate(jsonObject.getString("senddate"));
				information.setFlag(jsonObject.getString("flag"));
				information.setIsMake(jsonObject.getString("ismake"));
				information.setChannel(jsonObject.getString("channel"));
				information.setArcRank(jsonObject.getString("arcrank"));
				information.setClick(jsonObject.getString("click"));
				information.setTitle(jsonObject.getString("title"));
				information.setColor(jsonObject.getString("color"));
				information.setLitpic(jsonObject.getString("litpic"));
				information.setPubdate(jsonObject.getString("pubdate"));
				information.setMid(jsonObject.getString("mid"));
				information.setTypeName(jsonObject.getString("typename"));
				information.setUrl(jsonObject.getString("url"));
				information.setChannelName(jsonObject.getString("channelname"));
				information.setRedirecturl(jsonObject.getString("redirecturl"));
				JSONArray jsonArray1 = jsonObject.getJSONArray("imgurls");
				int count = jsonArray1.length();
				String[] strArray = new String[count];
				if (count != 0) {
					for (int j = 0; j < count; j++) {
						strArray[j] = jsonArray1.getString(j);
					}
					information.setImgurls(strArray);
				} else {
					information.setImgurls(strArray);
				}
				information.setFc(jsonObject.getString("fc"));
				m_InformationList.add(information);
			}
		}
		return m_InformationList;
	}

	// 解析资讯数据
	public List<Information> getMenberInformationJson(JSONObject json)
			throws JSONException {
		m_InformationList = new ArrayList<Information>();
		int code = json.getInt("code");
		if (code == 0) {
			JSONArray jsonArray = json.getJSONArray("data");
			m_count = jsonArray.length();
			for (int i = 0; i < m_count; i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Information information = new Information();
				information.setId(jsonObject.getString("id"));
				information.setTypeId(jsonObject.getString("typeid"));
				information.setSendDate(jsonObject.getString("senddate"));
				information.setFlag(jsonObject.getString("flag"));
				information.setIsMake(jsonObject.getString("ismake"));
				information.setChannel(jsonObject.getString("channel"));
				information.setArcRank(jsonObject.getString("arcrank"));
				information.setClick(jsonObject.getString("click"));
				information.setTitle(jsonObject.getString("title"));
				information.setColor(jsonObject.getString("color"));
				information.setLitpic(jsonObject.getString("litpic"));
				information.setPubdate(jsonObject.getString("pubdate"));
				information.setMid(jsonObject.getString("mid"));
				information.setTypeName(jsonObject.getString("typename"));
				information.setUrl(jsonObject.getString("url"));
				information.setChannelName(jsonObject.getString("channelname"));
				information.setRedirecturl(jsonObject.getString("redirecturl"));
				JSONArray jsonArray1 = jsonObject.getJSONArray("imgurls");
				int count = jsonArray1.length();
				String[] strArray = new String[count];
				if (count != 0) {
					for (int j = 0; j < count; j++) {
						strArray[j] = jsonArray1.getString(j);
					}
					information.setImgurls(strArray);
				} else {
					information.setImgurls(strArray);
				}
				information.setFc(jsonObject.getString("fc"));
				m_InformationList.add(information);
			}
		}
		return m_InformationList;
	}

	// 获得收藏列表
	public List<Information> getCollectionJson(JSONObject json)
			throws JSONException {
		m_InformationList = new ArrayList<Information>();
		int code = json.getInt("code");
		if (code == 0) {
			JSONArray jsonArray = json.getJSONArray("data");
			m_count = jsonArray.length();
			for (int i = 0; i < m_count; i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Information information = new Information();
				information.setId(jsonObject.getString("id"));
				information.setTypeId(jsonObject.getString("typeid"));
				information.setSendDate(jsonObject.getString("senddate"));
				information.setFlag(jsonObject.getString("flag"));
				information.setIsMake(jsonObject.getString("ismake"));
				information.setChannel(jsonObject.getString("channel"));
				information.setArcRank(jsonObject.getString("arcrank"));
				information.setClick(jsonObject.getString("click"));
				information.setTitle(jsonObject.getString("title"));
				information.setColor(jsonObject.getString("color"));
				information.setLitpic(jsonObject.getString("litpic"));
				information.setPubdate(jsonObject.getString("pubdate"));
				information.setMid(jsonObject.getString("mid"));
				information.setTypeName(jsonObject.getString("typename"));
				information.setChannelName(jsonObject.getString("channelname"));
				information.setRedirecturl(jsonObject.getString("redirecturl"));
				information.setFc(jsonObject.getString("fc"));
				m_InformationList.add(information);
			}
		}
		return m_InformationList;
	}

	// 解析注册的Json
	public List<Register> getRegisterJson(JSONObject json) throws JSONException {
		m_RegisterList = new ArrayList<Register>();
		Register register = new Register();
		String code = json.getJSONObject("code").toString();
		Log.e("code", code);
		register.setCode(json.getJSONObject("code").toString());
		if (code == "0" || "0".equals(code)) {
			register.setMsg(json.getJSONObject("msg").toString());
			Log.e("msg", json.getJSONObject("msg").toString());
		}
		m_RegisterList.add(register);
		return m_RegisterList;
	}

	// 是否发送手机注册码
	public boolean getAuthCodeRequest(JSONObject json) throws JSONException {
		String code = json.getJSONObject("code").toString();
		if (code == "0" || "0".equals(code)) {
			return true;
		} else
			return false;
	}

	/**
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public OrdinaryEssayDetails getEssayDetails(JSONObject json)
			throws JSONException {
		OrdinaryEssayDetails essayDetail = m_Details.getEssayDetailsInstance();
		int code = json.getInt("code");
		if (code == 0) {
			JSONObject jsonObject = json.getJSONObject("data");
			essayDetail.setId(jsonObject.getString("id"));
			essayDetail.setTypeId(jsonObject.getString("typeid"));
			essayDetail.setTypeId2(jsonObject.getString("typeid2"));
			essayDetail.setFlag(jsonObject.getString("flag"));
			essayDetail.setChannel(jsonObject.getString("channel"));
			essayDetail.setClick(jsonObject.getString("click"));
			essayDetail.setTitle(jsonObject.getString("title"));
			essayDetail.setShortTitle(jsonObject.getString("shorttitle"));
			essayDetail.setColor(jsonObject.getString("color"));
			essayDetail.setWriter(jsonObject.getString("writer"));
			essayDetail.setLitpic(jsonObject.getString("litpic"));
			essayDetail.setPubDate(jsonObject.getString("pubdate"));
			essayDetail.setSendDate(jsonObject.getString("senddate"));
			essayDetail.setmId(jsonObject.getString("mid"));
			essayDetail.setKeywords(jsonObject.getString("keywords"));
			essayDetail.setDescription(jsonObject.getString("description"));
			essayDetail.setChannelName(jsonObject.getString("channelname"));
			essayDetail.setFc(jsonObject.getString("fc"));
			essayDetail.setFace(jsonObject.getString("face"));
			essayDetail.setQrcode(jsonObject.getString("qrcode"));
			essayDetail.setShareUrl(jsonObject.getString("shareUrl"));
		}
		return essayDetail;
	}

	public PictureSetDetails getPictureSetDetails(JSONObject json)
			throws JSONException {
		m_PictureSetDetails = m_Details.getPictureSetDetails();
		JSONObject jsonObject = json.getJSONObject("data");
		m_PictureSetDetails.setId(jsonObject.getString("id"));
		m_PictureSetDetails.setTypeId(jsonObject.getString("typeid"));
		m_PictureSetDetails.setTypeId2(jsonObject.getString("typeid2"));
		m_PictureSetDetails.setFlag(jsonObject.getString("flag"));
		m_PictureSetDetails.setChannel(jsonObject.getString("channel"));
		m_PictureSetDetails.setClick(jsonObject.getString("click"));
		m_PictureSetDetails.setTitle(jsonObject.getString("title"));
		m_PictureSetDetails.setShortTitle(jsonObject.getString("shorttitle"));
		m_PictureSetDetails.setColor(jsonObject.getString("color"));
		m_PictureSetDetails.setWriter(jsonObject.getString("writer"));
		m_PictureSetDetails.setLitpic(jsonObject.getString("litpic"));
		m_PictureSetDetails.setPubDate(jsonObject.getString("pubdate"));
		m_PictureSetDetails.setSendDate(jsonObject.getString("senddate"));
		m_PictureSetDetails.setmId(jsonObject.getString("mid"));
		m_PictureSetDetails.setKeywords(jsonObject.getString("keywords"));
		m_PictureSetDetails.setDescription(jsonObject.getString("description"));
		m_PictureSetDetails.setChannelName(jsonObject.getString("channelname"));
		List<PictureDetails> details = new ArrayList<Details.PictureDetails>();
		JSONArray jsonArray = jsonObject.getJSONArray("pictures");
		int count = jsonArray.length();
		for (int i = 0; i < count; i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			PictureDetails detail = m_Details.getPictureDetails();
			detail.setText(object.getString("text"));
			detail.setImg(object.getString("imgurl"));
			details.add(detail);
		}
		m_PictureSetDetails.setPictures(details);
		m_PictureSetDetails.setFc(jsonObject.getString("fc"));
		m_PictureSetDetails.setFace(jsonObject.getString("face"));
		m_PictureSetDetails.setQrcode(jsonObject.getString("qrcode"));
		m_PictureSetDetails.setShareUrl(jsonObject.getString("shareUrl"));
		return m_PictureSetDetails;
	}

	public ShowAndAuctionDetails getShowDetails(JSONObject json)
			throws JSONException {
		int code = json.getInt("code");
		if (code == 0) {
			JSONObject jsonObject = json.getJSONObject("data");
			m_ShowAndAuctionDetails.setId(jsonObject.getString("id"));
			m_ShowAndAuctionDetails.setTypeId(jsonObject.getString("typeid"));
			m_ShowAndAuctionDetails.setTypeId2(jsonObject.getString("typeid2"));
			m_ShowAndAuctionDetails.setFlag(jsonObject.getString("flag"));
			m_ShowAndAuctionDetails.setChannel(jsonObject.getString("channel"));
			m_ShowAndAuctionDetails.setClick(jsonObject.getString("click"));
			m_ShowAndAuctionDetails.setTitle(jsonObject.getString("title"));
			m_ShowAndAuctionDetails.setShortTitle(jsonObject
					.getString("shorttitle"));
			m_ShowAndAuctionDetails.setColor(jsonObject.getString("color"));
			m_ShowAndAuctionDetails.setWriter(jsonObject.getString("writer"));
			m_ShowAndAuctionDetails.setLitpic(jsonObject.getString("litpic"));
			m_ShowAndAuctionDetails.setPubDate(jsonObject.getString("pubdate"));
			m_ShowAndAuctionDetails.setSendDate(jsonObject
					.getString("senddate"));
			m_ShowAndAuctionDetails.setmId(jsonObject.getString("mid"));
			m_ShowAndAuctionDetails.setKeywords(jsonObject
					.getString("keywords"));
			m_ShowAndAuctionDetails.setDescription(jsonObject
					.getString("description"));
			m_ShowAndAuctionDetails.setChannelName(jsonObject
					.getString("channelname"));
			m_ShowAndAuctionDetails.setAddress(jsonObject.getString("address"));
			m_ShowAndAuctionDetails.setStartTime(jsonObject
					.getString("starttime"));
			m_ShowAndAuctionDetails.setEndTime(jsonObject.getString("endtime"));
			// m_ShowAndAuctionDetails.setLeftTime(jsonObject.getString("lefttime"));
			List<PictureDetails> details = new ArrayList<Details.PictureDetails>();
			JSONArray jsonArray = jsonObject.getJSONArray("pictures");
			int count = jsonArray.length();
			for (int i = 0; i < count; i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				PictureDetails detail = m_Details.getPictureDetails();
				detail.setText(object.getString("text"));
				detail.setImg(object.getString("imgurl"));
				details.add(detail);
			}
			m_ShowAndAuctionDetails.setPictures(details);
			m_ShowAndAuctionDetails.setFc(jsonObject.getString("fc"));
			m_ShowAndAuctionDetails.setFace(jsonObject.getString("face"));
			m_ShowAndAuctionDetails.setSpaceName(jsonObject
					.getString("spacename"));
			m_ShowAndAuctionDetails.setQrcode(jsonObject.getString("qrcode"));
			m_ShowAndAuctionDetails.setShareUrl(jsonObject
					.getString("shareUrl"));
		}
		return m_ShowAndAuctionDetails;
	}

	public ShowAndAuctionDetails getAuctionDetails(JSONObject json)
			throws JSONException {
		JSONObject object = json.getJSONObject("data");
		m_ShowAndAuctionDetails.setId(object.getString("id"));
		m_ShowAndAuctionDetails.setTypeId(object.getString("typeid"));
		m_ShowAndAuctionDetails.setTypeId2(object.getString("typeid2"));
		m_ShowAndAuctionDetails.setFlag(object.getString("flag"));
		m_ShowAndAuctionDetails.setChannel(object.getString("channel"));
		m_ShowAndAuctionDetails.setClick(object.getString("click"));
		m_ShowAndAuctionDetails.setTitle(object.getString("title"));
		m_ShowAndAuctionDetails.setShortTitle(object.getString("shorttitle"));
		m_ShowAndAuctionDetails.setColor(object.getString("color"));
		m_ShowAndAuctionDetails.setWriter(object.getString("writer"));
		m_ShowAndAuctionDetails.setLitpic(object.getString("litpic"));
		m_ShowAndAuctionDetails.setPubDate(object.getString("pubdate"));
		m_ShowAndAuctionDetails.setSendDate(object.getString("senddate"));
		m_ShowAndAuctionDetails.setmId(object.getString("mid"));
		m_ShowAndAuctionDetails.setKeywords(object.getString("keywords"));
		m_ShowAndAuctionDetails.setDescription(object.getString("description"));
		m_ShowAndAuctionDetails.setChannelName(object.getString("channelname"));
		m_ShowAndAuctionDetails.setStartTime(object.getString("starttime"));
		m_ShowAndAuctionDetails.setEndTime(object.getString("endtime"));
		m_ShowAndAuctionDetails.setAddress(object.getString("address"));
		List<PictureDetails1> details = new ArrayList<Details.PictureDetails1>();
		JSONArray jsonArray = object.getJSONArray("pictures");
		int count = jsonArray.length();
		for (int i = 0; i < count; i++) {
			JSONObject object1 = jsonArray.getJSONObject(i);
			PictureDetails1 detail = m_Details.getPictureDetails1();
			detail.setwId(object1.getString("wid"));
			detail.setaId(object1.getString("aid"));
			detail.setIntroduce(object1.getString("introduce"));
			detail.setBasePrice(object1.getString("baseprice"));
			detail.setAmplitude(object1.getString("amplitude"));
			detail.setUmgurls(object1.getString("imgurl"));
			details.add(detail);
		}
		m_ShowAndAuctionDetails.setPictures1(details);
		m_ShowAndAuctionDetails.setFace(object.getString("face"));
		m_ShowAndAuctionDetails.setQrcode(object.getString("qrcode"));
		m_ShowAndAuctionDetails.setShareUrl(object.getString("shareUrl"));
		return m_ShowAndAuctionDetails;
	}

	public List<Member> getMembersJson(JSONObject json) throws JSONException {
		m_Menbers = new ArrayList<Member>();
		int code = json.getInt("code");
		if (code == 0) {
			JSONArray jsonArray = json.getJSONArray("data");
			m_count = jsonArray.length();
			for (int i = 0; i < m_count; i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				Member member = new Member();
				member.setMid(object.getString("mid"));
				member.setUserid(object.getString("userid"));
				member.setUname(object.getString("uname"));
				member.setRank(object.getString("rank"));
				member.setFace(object.getString("face"));
				member.setAlpha(object.getString("alpha"));
				m_Menbers.add(member);
			}
		}
		return m_Menbers;
	}

	public List<Cmtype> getCmtype(JSONObject json) throws JSONException {
		m_Cmtypes = new ArrayList<PersonalPage.Cmtype>();
		JSONArray jsonArray = json.getJSONArray("mtypes");
		m_count = jsonArray.length();
		for (int i = 0; i < m_count; i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			Cmtype cmtype = m_PersonalPage.getCmtype();
			cmtype.setMtypeId(object.getString("mtypeid"));
			cmtype.setMtypeName(object.getString("mtypename"));
			cmtype.setChannelId(object.getString("channelid"));
			cmtype.setMid(object.getString("mid"));
			m_Cmtypes.add(cmtype);
		}
		return m_Cmtypes;
	}

	public About getAboutJson(JSONObject json) throws JSONException {
		JSONObject object = json.getJSONObject("about");
		m_Abouts.setTitle(object.getString("title"));
		m_Abouts.setDescription(object.getString("description"));
		m_Abouts.setTelephone(object.getString("telephone"));
		m_Abouts.setEmail(object.getString("email"));
		m_Abouts.setAddress(object.getString("address"));
		m_Abouts.setQrcode(object.getString("qrcode"));
		return m_Abouts;
	}

	public List<Information> getShowItemJson(JSONObject json)
			throws JSONException {
		m_InformationList = new ArrayList<Information>();
		int code = json.getInt("code");
		if (code == 0) {
			JSONArray jsonArray = json.getJSONArray("data");
			m_count = jsonArray.length();
			for (int i = 0; i < m_count; i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Information information = new Information();
				information.setId(jsonObject.getString("id"));
				information.setTypeId(jsonObject.getString("typeid"));
				information.setSendDate(jsonObject.getString("senddate"));
				information.setFlag(jsonObject.getString("flag"));
				information.setIsMake(jsonObject.getString("ismake"));
				information.setChannel(jsonObject.getString("channel"));
				information.setArcRank(jsonObject.getString("arcrank"));
				information.setClick(jsonObject.getString("click"));
				information.setTitle(jsonObject.getString("title"));
				information.setColor(jsonObject.getString("color"));
				information.setLitpic(jsonObject.getString("litpic"));
				information.setPubdate(jsonObject.getString("pubdate"));
				information.setMid(jsonObject.getString("mid"));
				information.setTypeName(jsonObject.getString("typename"));
				information.setChannelName(jsonObject.getString("channelname"));
				information.setRedirecturl(jsonObject.getString("redirecturl"));
				JSONArray jsonArray1 = jsonObject.getJSONArray("imgurls");
				int count = jsonArray1.length();
				String[] strArray = new String[count];
				if (count != 0) {
					for (int j = 0; j < count; j++) {
						strArray[j] = jsonArray1.getString(j);
					}
					information.setImgurls(strArray);
				} else {
					information.setImgurls(strArray);
				}
				information.setAddress(jsonObject.getString("address"));
				information.setLeftTime(jsonObject.getString("lefttime"));
				information.setStartTime(jsonObject.getString("starttime"));
				information.setEndTime(jsonObject.getString("endtime"));
				information.setRedirecturl(jsonObject.getString("redirecturl"));
				information.setFc(jsonObject.getString("fc"));
				m_InformationList.add(information);
			}
		}
		return m_InformationList;
	}

	public List<Comment> getCommentsJson(JSONObject json) throws JSONException {
		mComments = new ArrayList<Comment>();
		int code = json.getInt("code");
		if (code == 0) {
			JSONArray array = json.getJSONArray("data");
			m_count = array.length();
			for (int i = 0; i < m_count; i++) {
				JSONObject object = array.getJSONObject(i);
				Comment comment = new Comment();
				comment.setId(object.getString("id"));
				comment.setaId(object.getString("aid"));
				comment.setTypeId(object.getString("typeid"));
				comment.setUserName(object.getString("username"));
				comment.setIp(object.getString("ip"));
				comment.setdTime(object.getString("dtime"));
				comment.setmId(object.getString("mid"));
				comment.setBad(object.getString("bad"));
				comment.setGood(object.getString("good"));
				comment.setfType(object.getString("ftype"));
				comment.setMsg(object.getString("msg"));
				comment.setUserId(object.getString("userid"));
				comment.setmFace(object.getString("mface"));
				comment.setSpacesta(object.getString("spacesta"));
				comment.setDcores(object.getString("scores"));
				comment.setSex(object.getString("sex"));
				mComments.add(comment);
			}
		}
		return mComments;
	}
}
