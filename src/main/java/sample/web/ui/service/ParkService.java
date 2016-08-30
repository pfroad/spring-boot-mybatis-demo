package sample.web.ui.service;

import com.s515.rpc.invoker.data.Request;
import com.s515.rpc.service.annotation.Path;
import com.s515.rpc.service.annotation.Service;

@Service("park")
public interface ParkService {
//	@Path(path = "get")
	String get(Request request);
}