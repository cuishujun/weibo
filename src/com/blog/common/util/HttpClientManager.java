package com.blog.common.util;

import java.io.IOException;

import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

public class HttpClientManager {
	
	private  static DefaultHttpClient client =null;
	
	//http连接池的总连接数
	public static int HTTP_MAX_TOTAL_CONNECTION=20;
	//http连接池对每个主机的最大连接数
	public static int HTTP_MAX_CONNECTION_PER_ROUTE=20;
	//http连接的超时时间
	public static int HTTP_CONNECTION_TIMEOUT=20000;
	//http连接传输数据的超时时间
	public static int HTTP_SO_TIMEOUT=120000;
	//http出错后重试次数
	public static int HTTP_RETRY_COUNT=5;
	
	
	public synchronized static HttpClient getInstance(){
		
		if(client!=null)
			return client;
		
		//Locale.setDefault(Locale.CHINESE);
		
		HttpParams params = new BasicHttpParams();
		
		
		//------------------连接池参数-----------------------
		// Increase max total connection to 20
		ConnManagerParams.setMaxTotalConnections(params, HTTP_MAX_TOTAL_CONNECTION);
		// Increase default max connection per route to 20
		ConnPerRouteBean connPerRoute = new ConnPerRouteBean(HTTP_MAX_CONNECTION_PER_ROUTE);
		ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
		
		//--------------------连接参数-------------------------
		//设置成false，然后通过后台进程来检查，可优化每次的访问30ms
		HttpConnectionParams.setStaleCheckingEnabled(params, true);
		HttpConnectionParams.setConnectionTimeout(params, HTTP_CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, HTTP_SO_TIMEOUT);
		
		HttpProtocolParams.setUserAgent(params, "HttpComponents Client 4.0");
		//HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_0);

		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(
		        new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		
		 final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
		 sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		
		schemeRegistry.register(new Scheme("https", sslSocketFactory, 443));

		ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
		client = new DefaultHttpClient(cm, params);
		
		
		//设置重试的条件,DefaluHttpRequestRestryHandler看看源码
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

			Log log = LogFactory.getLog(HttpRequestRetryHandler.class);
			
		    public boolean retryRequest(
		            IOException exception, 
		            int executionCount,
		            HttpContext context) {
		    	
		    	log.warn("retry http request("+executionCount+")...");
		    	
		        if (executionCount >= HTTP_RETRY_COUNT) {
		            // Do not retry if over max retry count
		            return false;
		        }
		        if (exception instanceof NoHttpResponseException) {
		            // Retry if the server dropped connection on us
		            return true;
		        }
		        if (exception instanceof SSLHandshakeException) {
		            // Do not retry on SSL handshake exception
		            return false;
		        }
		        HttpRequest request = (HttpRequest) context.getAttribute(
		                ExecutionContext.HTTP_REQUEST);
		        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest); 
		        if (idempotent) {
		            // Retry if the request is considered idempotent 
		            return true;
		        }
		        return false;
		    }

		};
		
		client.setHttpRequestRetryHandler(myRetryHandler);

		
		return client;

	}
}
