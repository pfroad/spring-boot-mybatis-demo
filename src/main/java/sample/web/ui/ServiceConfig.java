package sample.web.ui;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.s515.rpc.annotation.ServiceScan;
import com.s515.rpc.invoker.Invoker;

@Configuration
@ServiceScan(basePackages = {"sample.web.ui.service"})
public class ServiceConfig {
	@Bean(name = "httpClient")
	public CloseableHttpClient closeableHttpClient() {
		return HttpClients.createDefault();
	}
	
	@Bean(name = "requestInvoker")
	public Invoker invoker(@Qualifier("httpClient") CloseableHttpClient httpClient) {
		AppAccessInvoker invoker = new AppAccessInvoker();
		invoker.setHttpClient(httpClient);
		return invoker;
	}
}
