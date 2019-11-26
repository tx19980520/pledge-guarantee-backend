package wd.pledge.guarantee.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "device")
public class Device {

  @Id
  @Size(max = 20)
  private String deviceId;

  @Size(max = 20)
  @Column(name = "type")
  private String type;

  @Size(max = 150)
  @Column(name = "comment")
  private String comment;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "pledge_id", nullable = true)
  @OnDelete(action = OnDeleteAction.NO_ACTION)
  private Pledge pledge;

  @Size(max = 150)
  @Column(name = "topic", unique = true, nullable = false)
  private String topic;

  public Device() {
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Pledge getPledge() {
    return pledge;
  }

  public void setPledge(Pledge pledge) {
    this.pledge = pledge;
  }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
