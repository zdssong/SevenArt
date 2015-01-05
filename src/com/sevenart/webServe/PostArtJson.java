package com.sevenart.webServe;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sevenart.models.Bid;
import com.sevenart.models.Comment;
import com.sevenart.models.Login;
import com.sevenart.models.MyAuction;

public class PostArtJson {

	private Login m_Login;
	private List<MyAuction> m_MyAuctions;
	private Comment mComment;
	private Bid mBid;

	public PostArtJson() {
		m_Login = new Login();
		m_MyAuctions = new ArrayList<MyAuction>();
		mComment = new Comment();
		mBid = new Bid();
	}

	public Bid postBidJson(JSONObject json) throws JSONException {
		int code = json.getInt("code");
		if (code == 0) {
			JSONObject object = json.getJSONObject("data");
			mBid.setCurprice(object.getInt("curprice"));
			mBid.setPrice(object.getInt("price"));
			mBid.setUserId(object.getString("userid"));
			mBid.setFace(object.getString("face"));
		}
		return mBid;
	}

	public Comment postCommentJson(JSONObject json) throws JSONException {
		int code = json.getInt("code");
		if (code == 0) {
			JSONObject object = json.getJSONObject("data");
			mComment.setaId(object.getString("aid"));
			mComment.setTypeId(object.getString("typeid"));
			mComment.setUserName(object.getString("username"));
			mComment.setIp(object.getString("ip"));
			mComment.setdTime(object.getString("dtime"));
			mComment.setmId(object.getString("mid"));
			mComment.setMsg(object.getString("msg"));
			mComment.setUserId(object.getString("userid"));
			mComment.setmFace(object.getString("mface"));
		}
		return mComment;
	}

	public Login PostEnterJson(JSONObject json) throws JSONException {
		int code = json.getInt("code");
		if (code == 0) {
			JSONObject object = json.getJSONObject("data");
			m_Login.setMid(object.getString("mid"));
			m_Login.setUserId(object.getString("userid"));
			m_Login.setUname(object.getString("uname"));
			m_Login.setFace(object.getString("face"));
			m_Login.setLoginTime(object.getString("logintime"));
		}
		return m_Login;
	}

	public List<MyAuction> PostMyAuction(JSONObject json) throws JSONException {
		int code = json.getInt("code");
		if (code == 0) {
			JSONArray array = json.getJSONArray("data");
			int lenght = array.length();
			for (int i = 0; i < lenght; i++) {
				JSONObject object = array.getJSONObject(i);
				MyAuction myAuction = new MyAuction();
				myAuction.setWid(object.getString("wid"));
				myAuction.setAid(object.getString("aid"));
				myAuction.setIntroduce(object.getString("introduce"));
				myAuction.setBasePrice(object.getString("baseprice"));
				myAuction.setCurPrice(object.getString("curprice"));
				myAuction.setAddTime(object.getString("addtime"));
				myAuction.setImgurl(object.getString("imgurl"));
				m_MyAuctions.add(myAuction);
			}

		}
		return m_MyAuctions;
	}
}
