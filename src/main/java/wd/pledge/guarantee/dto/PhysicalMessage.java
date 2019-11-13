package wd.pledge.guarantee.dto;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import wd.pledge.guarantee.util.AlertType;

public class PhysicalMessage implements Serializable {
  private JSONObject physicalMessage;
  public PhysicalMessage(String payload) {
    JSONObject physicalMessage = (JSONObject) JSONObject.parse(payload);
  }

  public JSONObject getPhysicalMessage() {
    return physicalMessage;
  }

  public AlertType getAlertType() {
    return AlertType.resolve(physicalMessage.getInteger("alertType"));
  }
}
