package wd.pledge.guarantee;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import wd.pledge.guarantee.service.AlertService;


@SpringBootApplication
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600 * 24)
public class GuaranteeApplication implements CommandLineRunner {

	@Autowired
	private AlertService alertService;



	public static void main(String[] args) {
		SpringApplication.run(GuaranteeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String regionId = "cn-shanghai";
		String uid = "1004673557100937";
		String accessKey = "LTAI4FqAFhAqxcKkEfsYcQdc";
		String accessSecret = "HTWwfBGAqWPbnXcafKNdBFSorliYKx";
		String endPoint = "https://" + uid + ".iot-as-http2." + regionId + ".aliyuncs.com";
		// 连接配置
		Profile profile = Profile.getAccessKeyProfile(endPoint, regionId, accessKey, accessSecret);
		// 构造客户端
		MessageClient client = MessageClientFactory.messageClient(profile);
		// 数据接收
		client.connect(messageToken -> {
			alertService.receiveHandler(messageToken);
			return MessageCallback.Action.CommitSuccess;
		});
	}
}
