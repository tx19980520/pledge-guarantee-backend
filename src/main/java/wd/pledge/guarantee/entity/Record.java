package wd.pledge.guarantee.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "record")
public class Record implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @Column(name = "inwarehousing_time")
    private Time inwarehousingTime;

    @Column(name = "inwarehoused_time")
    private Time inwarehousedTime;

    @Column(name = "exwarehousing_time")
    private Time exwarehousingTime;

    @Column(name = "exwarehoused_time")
    private Time exwarehousedTime;

    public Record(){}

    public void setInwarehousingTime(Time inwarehousingTime) {
        this.inwarehousingTime = inwarehousingTime;
    }

    public Integer getRecordId() {
        return recordId;
    }
}
