package wd.pledge.guarantee.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.iot.api.message.entity.Message;
import com.aliyun.openservices.iot.api.message.entity.MessageToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;
import wd.pledge.guarantee.dto.AlertInfo;
import wd.pledge.guarantee.dto.DeviceMessage;
import wd.pledge.guarantee.dto.PhysicalMessage;
import wd.pledge.guarantee.repository.DeviceRepository;
import wd.pledge.guarantee.service.AlertService;
import wd.pledge.guarantee.service.RedisService;
import wd.pledge.guarantee.util.AlertType;

import javax.validation.constraints.Null;

@Service
public class AlertServiceImpl implements AlertService {

  @Autowired
  private RedisService redisService;
  @Autowired
  private DeviceRepository deviceRepository;
  private Lock lock;
  private Condition alertCondition;

  private List<JSONObject> imageList;

  @Autowired
  public void lock() {
    this.lock = new ReentrantLock();
    this.alertCondition = lock.newCondition();
  }


  @Autowired
  public void imageList() {
    this.imageList = new ArrayList<>();
  }

  public List<JSONObject> getImageList() {
    return imageList;
  }

  @Override
  public SseEmitter send(String name) throws IOException {
    SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
    ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
    sseMvcExecutor.execute(() -> {
      try {
        // Device device = deviceRepository.findByTopic(name);
        // check
        // find by value
        System.out.println("I'm after lock");
        do {
          lock.lock();
          System.out.println("I'm after await");
          SseEventBuilder event = SseEmitter.event()
              .data(new AlertInfo("thing id", AlertType.SHAKE))
              .name(name)
              .reconnectTime(1000L);
          emitter.send(event);
          alertCondition.await();
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
    lock.lock();

    Message message = messageToken.getMessage();

    System.out.println("Find where is the pic1: Just get message");
    System.out.println(new String(message.getPayload()));
    String topic = message.getTopic();
    // picture into list<js>
    if (topic.contains("picture")) {
      System.out.println("get picture");
      String imgInfo = new String(message.getPayload());
      System.out.println("ImageInfo:" + imgInfo);
      JSONObject jsonObject = JSONObject.parseObject(imgInfo);
      System.out.println("JSONOBJECT:" + jsonObject.toJSONString());
      if (imageList.size() == 6) {
        imageList.remove(0);
        imageList.add(jsonObject);
      }
      else {
        imageList.add(jsonObject);
      }
    }

    /*
    if (message.getTopic().contains("status")) {
      System.out.println("Find where is the pic2: have status");
      DeviceMessage deviceMessage = new DeviceMessage(new String (message.getPayload()));
      // can update device status
    } else {
      System.out.println("Find where is the pic3: Don't have status");
      PhysicalMessage physicalMessage = new PhysicalMessage(new String (message.getPayload()));
      System.out.println("Don't have status");
      System.out.println("getAlertType" + physicalMessage.getAlertType());

      redisService.set(message.getTopic(), physicalMessage.getAlertType().getType());
      alertCondition.signalAll();
    }
     */
    lock.unlock();
  }
}
