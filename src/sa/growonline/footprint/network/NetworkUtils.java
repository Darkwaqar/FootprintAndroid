package sa.growonline.footprint.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class NetworkUtils {
    private static final String COOKIES_HEADER = "Set-Cookie";
    final String UTF8 = "UTF-8";

    final String HTTP_GET = "GET";
    final String HTTP_POST = "POST";

    final String CHANGE_LINE = "\r\n";
    final String END_REQUEST = "--";

    final String CONTENT_DISPOSITION = "Content-Disposition: ";
    final String CONTENT_TYPE = "Content-Type: ";

    final String FILE_NAME = " filename=";
    final String NAME = "name=";
    final String FORM_DATA = "form-data; ";
    final String IMAGE_JPEG = "image/jpeg";

    final String APPLICATION_MULTIPART = "multipart/form-data";
    final String APPLICATION_JSON = "application/json";
    public final static String PLAIN_TEXT = "text/plain";
    public static final String TEXT_HTML = "text/html";


    String mBoundary = END_REQUEST + END_REQUEST + "WebKitFormBoundaryflDIl9j7fMbC5CDw";

    NetworkUtils() {
    }

    HttpURLConnection getUrlConnection(String URL, String httpMethod,
                                       String contentType, String boundary) throws Exception {
        URL url = new URL(URL);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        if (httpMethod.equalsIgnoreCase(HTTP_POST)) {
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
        }
        urlConnection.setRequestMethod(httpMethod);

        if (contentType.equalsIgnoreCase(APPLICATION_MULTIPART)) {
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            urlConnection.setRequestProperty("Cache-Control", "no-cache");
        } else {
            urlConnection.setRequestProperty("Content-Type"
                    , contentType);
        }

        //Zuni Header
        String cookieString = ZuniApplication.getmAppPreferences().getString(ZuniConstants.COOKIE_HANDLER, "");
        urlConnection.setRequestProperty("Cookie", cookieString);

        if (!cookieString.equalsIgnoreCase(""))
            ZuniUtils.LogEvent("Cookie:" + cookieString);

        ZuniUtils.LogEvent("Cookie:" + cookieString + "/cookies");

        return urlConnection;
    }

    String getResponse(HttpURLConnection urlConnection, boolean isSaveCookie) throws Exception {
        String response = "";
        SharedPreferences mPrefs = ZuniApplication.getmAppPreferences();
        if (/*isSaveCookie &&*/ mPrefs.getString(ZuniConstants.IS_USER_LOGGED_IN, "true").equalsIgnoreCase(ZuniConstants.SIGNING_IN) || mPrefs.getString(ZuniConstants.IS_USER_LOGGED_IN, "true").equalsIgnoreCase("false")) {
            ZuniUtils.LogEvent("saving cookies...");
            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
            List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);

            if (cookiesHeader != null && isSaveCookie) {
                Editor editor = ZuniApplication.getmAppPrefEditor();
                String cookies = "";

                for (String cookie : cookiesHeader)
                    cookies = cookies + cookie + ";";

                editor.putString(ZuniConstants.COOKIE_HANDLER, cookies);
                ZuniUtils.LogEvent(cookies);
                editor.commit();
            } else {
                ZuniUtils.LogEvent("saving cookies cancelled...");
            }
        }

        int status = urlConnection.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response = response + line;
            }
            reader.close();
            urlConnection.disconnect();
        } else {
            return "Server returned non-OK status: " + status;
        }
        return response;
    }

}