package wd.pledge.guarantee.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deviceId;

    @Size(max = 256)
    @Column(name = "type")
    private String type;

    @Size(max = 256)
    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pledge_id", nullable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Pledge pledge;

    public Device() {}

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
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
}
