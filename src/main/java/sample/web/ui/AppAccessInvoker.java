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
	private String appId;
	private String secretKey;
	private String version;

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
        nvps.add(new BasicNameValuePair("sign", Signature.sign(params, this.secretKey)));
        return nvps;
	}
	
	public void addCommonParameters(Map<String, Object> params) {
		params.put("appId", this.appId);
		params.put("appVersion", "1.0");
		params.put("timestamp", System.currentTimeMillis());
		params.put("clientType", 1);
		params.put("version", this.version);
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
