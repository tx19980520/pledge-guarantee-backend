package wd.pledge.guarantee.repository;

import org.springframework.data.repository.CrudRepository;
import wd.pledge.guarantee.entity.Pledge;

public interface PledgeRepository extends CrudRepository<Pledge, Integer> {

}
