package sa.growonline.footprint.network;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class GetPostSender extends NetworkUtils
{
	public String sendGet(String url, boolean isSaveCookie)
	{
		HttpURLConnection httpURLConnection;
		try
		{
			httpURLConnection = getUrlConnection(url, HTTP_GET, PLAIN_TEXT, "");

			httpURLConnection.connect();

			return getResponse(httpURLConnection, isSaveCookie);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String sendPostJSON(String url, String params, boolean isSaveCookie)
	{
		HttpURLConnection httpURLConnection;
		try 
		{
			if(ZuniConstants.isLogEnabled){
				ZuniUtils.LogEvent("URL:" + ZuniConstants.BASE_URL + url);
				ZuniUtils.LogEvent("Request Params:" + params);
			}
			
			httpURLConnection = getUrlConnection(ZuniConstants.BASE_URL + url, HTTP_POST, APPLICATION_JSON, "");
			httpURLConnection.connect();
			
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"),
	                true);
			writer.write(params);
			writer.flush();
	        String response = getResponse(httpURLConnection, isSaveCookie);
	        if(ZuniConstants.isLogEnabled)
	        {
	            ZuniUtils.LogEvent("Response:" + response);
			}
	        return response;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "false";
		}
	}
}