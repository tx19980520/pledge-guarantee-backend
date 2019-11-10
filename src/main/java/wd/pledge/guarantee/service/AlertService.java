package wd.pledge.guarantee.service;

import java.io.IOException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AlertService {

  SseEmitter send(String name) throws IOException;
}
