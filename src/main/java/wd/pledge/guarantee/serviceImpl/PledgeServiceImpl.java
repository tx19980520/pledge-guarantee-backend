package wd.pledge.guarantee.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.repository.PledgeRepository;
import wd.pledge.guarantee.service.PledgeService;

import javax.validation.constraints.Null;
import java.util.Optional;

@Service
public class PledgeServiceImpl implements PledgeService {

    @Autowired
    private PledgeRepository pledgeRepository;

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
