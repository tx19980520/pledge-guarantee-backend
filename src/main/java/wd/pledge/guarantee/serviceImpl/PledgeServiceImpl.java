package wd.pledge.guarantee.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.repository.PledgeRepository;
import wd.pledge.guarantee.service.PledgeService;
import wd.pledge.guarantee.util.LogicalState;

import java.util.Optional;

import javax.validation.constraints.Null;
import java.util.Optional;

@Service
public class PledgeServiceImpl implements PledgeService {

    @Autowired
    PledgeRepository pledgeRepository;

    public String setExWarehousing(Integer pledgeId)
    {
        Optional<Pledge> pledgeOptional = pledgeRepository.findById(pledgeId);
        if (pledgeOptional.isPresent()) {
            Pledge pledge = pledgeOptional.get();
            if (pledge.getLogicalState() == LogicalState.INWAREHOUSED) {
                // 前置条件：质押物状态为“已入库”
                pledgeRepository.updatePledgeLogicalState(pledgeId, LogicalState.EXWAREHOUSING);
                return "质押物标记成功。";
            }
            else {
                return "质押物逻辑状态错误。";
            }
        }
        return "质押物不存在。";
    }

    public String setExWarehoused(Integer pledgeId)
    {
        Optional<Pledge> pledgeOptional = pledgeRepository.findById(pledgeId);
        if (pledgeOptional.isPresent()) {
            Pledge pledge = pledgeOptional.get();
            if (pledge.getLogicalState() == LogicalState.EXWAREHOUSING) {
                // 前置条件：质押物状态为“可出库”
                pledgeRepository.updatePledgeLogicalState(pledgeId, LogicalState.EXWAREHOUSED);
                return "质押物标记成功。";
            }
            else {
                return "质押物逻辑状态错误。";
            }
        }
        return "质押物不存在。";
    }

    public void createPledge(Pledge pledge)
    {
        pledgeRepository.save(pledge);
    }
  
    @Override
    public Pledge get_one_pledge_info(Integer pledgeId) {
        Optional<Pledge> optionalPledge = pledgeRepository.findById(pledgeId);
        System.out.println("SERVICE HERE GET pledgeId: " + pledgeId);
        return optionalPledge.orElse(null);
    }

    @Override
    public Iterable<Pledge> findAll() {
        /*
        Pledge pledge = pledgeRepository.findByName("test1");
        if (pledge == null) {
            System.out.println("NULL ");
        }
        else {
            System.out.println("HAVE");
        }
        */
        System.out.println("SERVICE GET ALL ALL ALL ");
        return pledgeRepository.findAll();
    }
}
