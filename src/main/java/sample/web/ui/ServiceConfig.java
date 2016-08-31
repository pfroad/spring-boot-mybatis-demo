package sample.web.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.s515.rpc.annotation.ServiceScan;
import com.s515.rpc.invoker.Invoker;
import com.s515.rpc.router.lbs.RandomLoadBalance;
import com.s515.rpc.router.server.Host;
import com.s515.rpc.router.server.HttpNode;
import com.s515.rpc.router.server.Node;

@Configuration
@ServiceScan(basePackages = {"sample.web.ui.service"})
public class ServiceConfig {
	@Value("${app.nodes.appId}")
	private String appId;
	@Value("${app.nodes.secretKey}")
	private String secretKey;
	@Value("${app.nodes.version}")
	private String version;
	@Value("${app.nodes}")
	private String appNodes;
	@Value("${app.name}")
	private String appName;
	
	@Bean(name = "httpClient")
	public CloseableHttpClient closeableHttpClient() {
		return HttpClients.createDefault();
	}
	
	@Bean(name = "requestInvoker")
	public Invoker invoker(@Qualifier("httpClient") CloseableHttpClient httpClient) {
		AppAccessInvoker invoker = new AppAccessInvoker();
		invoker.setHttpClient(httpClient);
		invoker.setAppId(appId);
		invoker.setSecretKey(secretKey);
		invoker.setVersion(version);
		
		invoker.setLoadBalance(new RandomLoadBalance());
		invoker.setNodes(getAppNodes());
		return invoker;
	}
	
	private List<Node> getAppNodes() {
		if (!StringUtils.isEmpty(appNodes)) {
			String[] nodes = appNodes.split(",");
			
			final List<Node> appNodes = new ArrayList<>();
			
			String[] nodeUrl;
			Node n;
			for (String node : nodes) {
				nodeUrl = node.trim().split(":");
				n = new HttpNode(appName, new Host(nodeUrl[0], nodeUrl.length > 1 ? Integer.valueOf(nodeUrl[1]) : 80));
				appNodes.add(n);
			}
			
			return appNodes;
		}
		
		return null;
	}
}
