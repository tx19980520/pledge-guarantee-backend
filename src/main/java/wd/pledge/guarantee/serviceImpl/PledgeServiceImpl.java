package wd.pledge.guarantee.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wd.pledge.guarantee.entity.Location;
import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.entity.Record;
import wd.pledge.guarantee.repository.LocationRepository;
import wd.pledge.guarantee.repository.PledgeRepository;
import wd.pledge.guarantee.repository.RecordRepository;
import wd.pledge.guarantee.service.PledgeService;
import wd.pledge.guarantee.util.LogicalState;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;

@Service
public class PledgeServiceImpl implements PledgeService {

    @Autowired
    PledgeRepository pledgeRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    RecordRepository recordRepository;

    public String setInWarehousing(Integer pledgeId) {
        Optional<Pledge> pledgeOptional = pledgeRepository.findById(pledgeId);
        if (pledgeOptional.isPresent()) {
            Pledge pledge = pledgeOptional.get();
            if (pledge.getLogicalState() == LogicalState.INWAREHOUSING) {
                // 前置条件：质押物状态为“已入库”
                pledgeRepository.updatePledgeLogicalState(pledgeId, LogicalState.INWAREHOUSED);
                Date date = new Date();
                recordRepository.updateTnwarehousedTime(pledge.getRecord().getRecordId(), date.toString());
                return "质押物标记成功。";
            } else {
                return "质押物逻辑状态错误。";
            }
        }
        return "质押物不存在。";
    }

    public String setExWarehousing(Integer pledgeId) {
        Optional<Pledge> pledgeOptional = pledgeRepository.findById(pledgeId);
        if (pledgeOptional.isPresent()) {
            Pledge pledge = pledgeOptional.get();
            if (pledge.getLogicalState() == LogicalState.INWAREHOUSED) {
                // 前置条件：质押物状态为“已入库”
                pledgeRepository.updatePledgeLogicalState(pledgeId, LogicalState.EXWAREHOUSING);
                Date date = new Date();
                recordRepository.updateExwarehousingTime(pledge.getRecord().getRecordId(), date.toString());
                return "质押物标记成功。";
            } else {
                return "质押物逻辑状态错误。";
            }
        }
        return "质押物不存在。";
    }

    public String setExWarehoused(Integer pledgeId) {
        Optional<Pledge> pledgeOptional = pledgeRepository.findById(pledgeId);
        if (pledgeOptional.isPresent()) {
            Pledge pledge = pledgeOptional.get();
            if (pledge.getLogicalState() == LogicalState.EXWAREHOUSING) {
                // 前置条件：质押物状态为“可出库”
                pledgeRepository.updatePledgeLogicalState(pledgeId, LogicalState.EXWAREHOUSED);
                Date date = new Date();
                recordRepository.updateExwarehousedTime(pledge.getRecord().getRecordId(), date.toString());
                return "质押物标记成功。";
            } else {
                return "质押物逻辑状态错误。";
            }
        }
        return "质押物不存在。";
    }

    public String createPledge(JSONObject jsonObject)
    {
        Integer locationId = jsonObject.getInteger("locationId");
        if (locationId == null)
            return "没有提供locationId参数。";
        Optional<Location> locationOptional = locationRepository.findById(locationId);
        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            if (location.isUsed()) {
                return "位置已被占用。";
            }
            Pledge pledge = new Pledge();
            pledge.setPledgeId(jsonObject.getInteger("pledgeId"));
            pledge.setName(jsonObject.getString("name"));
            pledge.setValue(jsonObject.getFloatValue("value"));
            pledge.setLocation(location);
            pledge.setLogicalState(LogicalState.INWAREHOUSING);

            Record record = new Record();
            Date date = new Date();
            record.setInwarehousingTime(date.toString());
            recordRepository.save(record);

            pledge.setRecord(record);
            pledgeRepository.save(pledge);
            locationRepository.setLocationUsed(location.getLocationId(), true);
            return "质押物入库成功。";
        }
        else {
            return "位置ID不存在";
        }
        //pledgeRepository.save(pledge);
    }


    @Override
    public Pledge get_one_pledge_info(Integer pledgeId) {

        Optional<Pledge> pledgeOptional = pledgeRepository.findById(pledgeId);
        System.out.println("SERVICE HERE GET pledgeId: " + pledgeId);
        return pledgeOptional.orElse(null);
    }

    @Override
    public Iterable<Pledge> findAll() {
        System.out.println("SERVICE GET ALL  ");
        return pledgeRepository.findAll();
    }

    @Override
    public String confirmLocation(JSONObject jsonObject) {
        Integer locationId = jsonObject.getInteger("locationId");
        if (locationId == null)
            return "没有提供locationId参数。";
        Optional<Location> locationOptional = locationRepository.findById(locationId);
        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            if (location.isUsed()) {
                return "位置已被占用。";
            }
        }
        else {
            return "位置ID不存在";
        }
        return "位置确认";

    }

}
