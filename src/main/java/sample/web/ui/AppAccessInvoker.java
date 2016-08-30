package sample.web.ui;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.airparking.core.comm.utils.Signature;
import com.s515.rpc.invoker.http.HttpInvoker;

public class AppAccessInvoker extends HttpInvoker {

	@Override
	public List<NameValuePair> buildValuePair(Map<String, Object> params)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		addCommonParameters(params);
		List<NameValuePair> nvps = null;
        if (params == null || params.size() == 0) {
            return nvps;
        }

        nvps = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        nvps.add(new BasicNameValuePair("sign", Signature.sign(params, "f7ec49c46ff8b0349f97cd4a8737d471")));
        return nvps;
	}
	
	public void addCommonParameters(Map<String, Object> params) {
		params.put("appId", "apyAqKqNs2qxWZPWOQ");
		params.put("appVersion", "1.0");
		params.put("timestamp", System.currentTimeMillis());
		params.put("deviceId", "e0a83343b86f6595a32");
		params.put("clientType", 1);
		params.put("version", "1.0.0");
		params.put("userLng", 113.320519);
		params.put("userLat", 23.12248);
		params.put("token", "klt57KHjyyyVYLMUFhZKu1SbYEwXUgXC");
	}
	
}
