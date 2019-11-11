package wd.pledge.guarantee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@SpringBootApplication
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600 * 24)
public class GuaranteeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuaranteeApplication.class, args);
	}

}
