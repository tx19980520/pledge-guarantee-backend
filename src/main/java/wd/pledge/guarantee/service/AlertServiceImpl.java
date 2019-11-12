package wd.pledge.guarantee.service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;
import wd.pledge.guarantee.dto.AlertInfo;
import wd.pledge.guarantee.util.AlertType;

@Service
public class AlertServiceImpl implements AlertService{

  @Override
  public SseEmitter send(String name) throws IOException {
    SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
    ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
    sseMvcExecutor.execute(() -> {
      try {
        do {
          SseEventBuilder event = SseEmitter.event()
              .data(new AlertInfo("device0", AlertType.SHAKE))
              .name(name)
              .reconnectTime(1000L);
          emitter.send(event);
          Thread.sleep(5000L);
        } while (true);
      } catch (Exception ex) {
        System.out.println(ex.toString());
        System.out.println(ex.getMessage());
        emitter.completeWithError(ex);
      }
    });
    return emitter;
  }
}
