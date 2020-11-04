package lesson05.gateway.outbound.myhttpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    /**
     * httpPost
     */
    public static String httpPost(String url, String jsonParam , List<Map.Entry<String, String>> list) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, String> map:list) {
            httpPost.addHeader(map.getKey(),map.getValue());
        }
        if (true) {
            // 使参数体为空时,也可以进行post请求
            jsonParam = (null == jsonParam || jsonParam.isEmpty()) ? "" : jsonParam;

            // post请求的一些设置
            StringEntity entity = new StringEntity(jsonParam, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
        }
        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            HttpEntity entity = response.getEntity();
            // EntityUtils.consume(entity);
            String str = EntityUtils.toString(entity);
            EntityUtils.consume(entity); // 此句关闭了流
            return str;
        } finally {
            response.close();
        }
    }

    /**
     * httpGet
     */
    public static String httpGet(String url , List<Map.Entry<String, String>> list) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> map:list) {
            httpGet.addHeader(map.getKey(),map.getValue());
        }

        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            HttpEntity entity = response.getEntity();
            String strResult = EntityUtils.toString(entity);
            return strResult;
        } finally {
            response.close();
        }
    }
}
