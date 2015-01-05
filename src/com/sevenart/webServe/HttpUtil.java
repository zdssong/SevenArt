package com.sevenart.webServe;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	// private static final String BOUNDARY = UUID.randomUUID().toString(); //
	// 边界标识
	// // 随机生成
	// private static final String PREFIX = "--";
	// private static final String LINE_END = "\r\n";
	// private static final String CONTENT_TYPE = "multipart/form-data"; // 内容类型
	//
	// private static int readTimeOut = 10 * 1000; // 读取超时
	// private static int connectTimeout = 10 * 1000; // 超时时间
	//
	// private static final String CHARSET = "utf-8"; // 设置编码
	//
	// public static String post(String RequestURL, NameValuePair... param) {
	//
	// try {
	// URL url = new URL(RequestURL);
	// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	// conn.setReadTimeout(readTimeOut);
	// conn.setConnectTimeout(connectTimeout);
	// conn.setDoInput(true); // 允许输入流
	// conn.setDoOutput(true); // 允许输出流
	// conn.setUseCaches(false); // 不允许使用缓存
	// conn.setRequestMethod("POST"); // 请求方式
	// conn.setRequestProperty("Charset", CHARSET); // 设置编码
	// conn.setRequestProperty("connection", "keep-alive");
	// conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
	// + BOUNDARY);
	//
	// DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
	// StringBuffer sb = null;
	// String params = "";
	//
	// for (int i = 0; i < param.length; i++) {
	// String key = param[i].getName();
	// String value = param[i].getValue();
	// if (key.contains("face")) {
	// postPicture(value, dos);
	// continue;
	// }
	// sb = null;
	// sb = new StringBuffer();
	// sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
	// sb.append("Content-Disposition: form-data; name=\"")
	// .append(key).append("\"").append(LINE_END)
	// .append(LINE_END);
	// sb.append(value).append(LINE_END);
	// params = sb.toString();
	// dos.write(params.getBytes());
	// }
	//
	// byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
	// .getBytes();
	// dos.write(end_data);
	// dos.flush();
	// String result;
	// InputStream input = conn.getInputStream();
	// StringBuffer sb1 = new StringBuffer();
	// int ss;
	// while ((ss = input.read()) != -1) {
	// sb1.append((char) ss);
	// }
	// result = new String(sb1.toString());
	// return result;
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// return "";
	// } catch (IOException e) {
	// e.printStackTrace();
	// return "";
	// }
	// }
	//
	// public static void postPicture(String path, DataOutputStream dos)
	// throws IOException {
	// String params = "";
	// StringBuffer sb = new StringBuffer();
	// File file = new File(path);
	// sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
	// sb.append("Content-Disposition:form-data; name=\"" + "face"
	// + "\"; filename=\"" + file.getName() + "\"" + LINE_END);
	// sb.append("Content-Type:image/jpg" + LINE_END); // 这里配置的Content-type很重要的
	// // ，用于服务器端辨别文件的类型的
	// sb.append(LINE_END);
	// params = sb.toString();
	// sb = null;
	//
	// dos.write(params.getBytes());
	// /** 上传文件 */
	// InputStream is = new FileInputStream(file);
	// byte[] bytes = new byte[1024];
	// int len = 0;
	// while ((len = is.read(bytes)) != -1) {
	// dos.write(bytes, 0, len);
	// }
	// is.close();
	// dos.write(LINE_END.getBytes());
	// }
	public static String post(String url, NameValuePair... nameValuePairs)
			throws Exception {

		String result = "";

		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 1000 * 20;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, 30 * 1000);

		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpPost httpPost = new HttpPost(url);

		try {
			if (nameValuePairs != null) {
				MultipartEntity entity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);

				for (int index = 0; index < nameValuePairs.length; index++) {
					String name = nameValuePairs[index].getName();
					String value = nameValuePairs[index].getValue();
					if (name.equalsIgnoreCase("face")) {
						// If the key equals to "image", we use FileBody to
						// transfer the data
						entity.addPart(name, new FileBody(new File(value)));
					} else {
						// Normal string data
						entity.addPart(
								name,
								new StringBody(value, Charset
										.forName(HTTP.UTF_8)));
					}
				}
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost, localContext);
			result = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
