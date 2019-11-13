package wd.pledge.guarantee.serviceImpl;

import com.aliyun.openservices.iot.api.message.entity.Message;
import com.aliyun.openservices.iot.api.message.entity.MessageToken;
import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;
import wd.pledge.guarantee.dto.AlertInfo;
import wd.pledge.guarantee.dto.PhysicalMessage;
import wd.pledge.guarantee.entity.Device;
import wd.pledge.guarantee.repository.DeviceRepository;
import wd.pledge.guarantee.repository.DeviceRepository;
import wd.pledge.guarantee.service.AlertService;
import wd.pledge.guarantee.service.RedisService;
import wd.pledge.guarantee.util.AlertType;

@Service
public class AlertServiceImpl implements AlertService {

  @Autowired
  private RedisService redisService;
  @Autowired
  private DeviceRepository deviceRepository;


  @Override
  public SseEmitter send(String name) throws IOException {
    SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
    ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
    sseMvcExecutor.execute(() -> {
      try {
        // Device device = deviceRepository.findByTopic(name);
        // check
        // find by value;
        do {
          SseEventBuilder event = SseEmitter.event()
              .data(new AlertInfo("thing id", AlertType.SHAKE))
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

  @Override
  public void receiveHandler(MessageToken messageToken) {
    Message message = messageToken.getMessage();
    PhysicalMessage physicalMessage = new PhysicalMessage(new String (message.getPayload()));
    System.out.println(message.getTopic());
    System.out.println(physicalMessage.getAlertType().getType());
    redisService.set(message.getTopic(), physicalMessage.getAlertType().getType());
  }
}
