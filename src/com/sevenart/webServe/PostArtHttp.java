package com.sevenart.webServe;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.sevenart.application.ArtApplication;
import com.sevenart.models.Bid;
import com.sevenart.models.Comment;
import com.sevenart.models.Login;
import com.sevenart.models.MyAuction;

/*
 * 该类是使用Post方法来发送和接受请求
 * */
public class PostArtHttp {

	public static String msg;// 输出错误信息

	private PostArtJson m_PostArtJson;
	private String m_ResultString;

	private Login m_Login; // 登录的实体变量
	private List<MyAuction> m_MyAuctions;
	private Comment mComment;
	private Bid mBid;

	public PostArtHttp() {
		m_PostArtJson = new PostArtJson();
		m_Login = new Login();
		m_MyAuctions = new ArrayList<MyAuction>();
		mComment = new Comment();
		mBid = new Bid();
	}

	// 添加关注
	public int postAddCareHttp(String fmid) {
		String uriAPI = "http://m.yi71.com/plus/mobile/apps/follow.php";
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		paramsList.add(new BasicNameValuePair("act", "save"));
		paramsList.add(new BasicNameValuePair("fmid", fmid));

		HttpPost httpPost = new HttpPost(uriAPI);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.setCookieStore(ArtApplication.mArtCookieStore);
		HttpResponse response = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(paramsList, HTTP.UTF_8));
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				m_ResultString = EntityUtils.toString(entity, HTTP.UTF_8);
			}
			try {
				JSONObject json = new JSONObject(m_ResultString);
				int code = json.getInt("code");
				if (code != 0)
					msg = json.getString("msg");
				return code;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}

	// 发表评论
	public void postCommentHttp(String comType, String aid, String fid,
			String msg) {
		String uriAPI = "http://m.yi71.com/plus/mobile/apps/feedback.php";

		HttpPost httpPost = new HttpPost(uriAPI);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("act", "send"));
		params.add(new BasicNameValuePair("comtype", comType));
		params.add(new BasicNameValuePair("aid", aid));
		params.add(new BasicNameValuePair("fid", fid));
		params.add(new BasicNameValuePair("msg", msg));

		DefaultHttpClient client = new DefaultHttpClient();
		client.setCookieStore(ArtApplication.mArtCookieStore);
		HttpResponse response = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			response = client.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				m_ResultString = EntityUtils.toString(entity, HTTP.UTF_8);
			}
			try {
				JSONObject json = new JSONObject(m_ResultString);
				mComment = m_PostArtJson.postCommentJson(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 使用Post方法登陆
	public int postLogin(String account, String userpwd) {

		String uriAPI = "http://m.yi71.com/plus/mobile/apps/login.php";

		HttpPost httpPost = new HttpPost(uriAPI);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("account", account));
		params.add(new BasicNameValuePair("userpwd", userpwd));
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			httpResponse = httpClient.execute(httpPost);
			List<Cookie> cookies = httpClient.getCookieStore().getCookies();
			for (int i = 0; i < cookies.size(); i++) {
				ArtApplication.mArtCookieStore.addCookie(cookies.get(i));
			}
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				m_ResultString = EntityUtils.toString(entity, HTTP.UTF_8);
			}
			try {
				JSONObject json = new JSONObject(m_ResultString);
				if (json.getInt("code") == 0) {
					m_Login = m_PostArtJson.PostEnterJson(json);
					ArtApplication.mLogin = m_Login;
				} else {
					msg = json.getString("msg");
					return 0;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public List<MyAuction> postMyAuction(String mod) {
		String uriAPI = "http://m.yi71.com/plus/mobile/apps/auction.php";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("act", "record"));
		params.add(new BasicNameValuePair("mod", mod));

		HttpPost httpPost = new HttpPost(uriAPI);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.setCookieStore(ArtApplication.mArtCookieStore);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				m_ResultString = EntityUtils.toString(entity, HTTP.UTF_8);
			}
			try {
				JSONObject json = new JSONObject(m_ResultString);
				m_MyAuctions = m_PostArtJson.PostMyAuction(json);
				return m_MyAuctions;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return m_MyAuctions;
	}

	public Bid postPriceHttp(String aid, String wid, String price) {

		String uriAPI = "http://m.yi71.com/plus/mobile/apps/auction.php";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("act", "confirm"));
		params.add(new BasicNameValuePair("aid", aid));
		params.add(new BasicNameValuePair("wid", wid));
		params.add(new BasicNameValuePair("price", price));

		HttpPost httpPost = new HttpPost(uriAPI);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.setCookieStore(ArtApplication.mArtCookieStore);
		HttpResponse response = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				m_ResultString = EntityUtils.toString(entity, HTTP.UTF_8);
			}
			try {
				JSONObject json = new JSONObject(m_ResultString);
				int code = json.getInt("code");
				if (code != 0)
					msg = json.getString("msg");
				else {
					mBid = m_PostArtJson.postBidJson(json);
					return mBid;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int postCollectionHttp(String aid) {

		String uriAPI = "http://m.yi71.com/plus/mobile/apps/stow.php";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("act", "save"));
		params.add(new BasicNameValuePair("aid", aid));

		HttpPost httpPost = new HttpPost(uriAPI);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.setCookieStore(ArtApplication.mArtCookieStore);
		HttpResponse response = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				m_ResultString = EntityUtils.toString(entity, HTTP.UTF_8);
			}
			try {
				JSONObject json = new JSONObject(m_ResultString);
				int code = json.getInt("code");
				return code;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public Comment getmComment() {
		return mComment;
	}

	public void setmComment(Comment mComment) {
		this.mComment = mComment;
	}

	public List<MyAuction> getM_MyAuctions() {
		return m_MyAuctions;
	}

	public void setM_MyAuctions(List<MyAuction> m_MyAuctions) {
		this.m_MyAuctions = m_MyAuctions;
	}

	public Login getM_Login() {
		return m_Login;
	}

	public void setM_Login(Login m_Login) {
		this.m_Login = m_Login;
	}

}
