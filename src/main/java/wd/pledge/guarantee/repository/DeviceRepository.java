package wd.pledge.guarantee.repository;

import org.springframework.data.repository.CrudRepository;
import wd.pledge.guarantee.entity.Device;

public interface DeviceRepository extends CrudRepository<Device, Integer> {
  Device findByTopic(String topic);
}
