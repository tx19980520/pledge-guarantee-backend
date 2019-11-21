package wd.pledge.guarantee.service;

import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.util.LogicalState;

public interface PledgeService {

    public String setExWarehousing(Integer pledgeId);

    public String setExWarehoused(Integer pledgeId);

    public void createPledge(Pledge pledge);
}
