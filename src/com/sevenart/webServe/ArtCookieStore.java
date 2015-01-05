package com.sevenart.webServe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

public class ArtCookieStore implements CookieStore {
	
	private List<Cookie> mCookie;
	
	public ArtCookieStore(){
		mCookie = new ArrayList<Cookie>();
	}
	
	@Override
	public void addCookie(Cookie cookie) {
		// TODO Auto-generated method stub
		mCookie.add(cookie);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		mCookie.clear();
	}

	@Override
	public boolean clearExpired(Date date) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public List<Cookie> getCookies() {
		// TODO Auto-generated method stub
		return mCookie;
	}

}
