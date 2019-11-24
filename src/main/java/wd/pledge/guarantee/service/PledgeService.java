package wd.pledge.guarantee.service;

import com.alibaba.fastjson.JSONObject;
import wd.pledge.guarantee.entity.Pledge;

public interface PledgeService {

    public String setInWarehousing(Integer pledgeId);

    public String setExWarehousing(Integer pledgeId);

    public String setExWarehoused(Integer pledgeId);

    public String createPledge(JSONObject jsonObject);

    Pledge get_one_pledge_info(Integer pledgeid);

    Iterable<Pledge> findAll();

    String confirmLocation(JSONObject jsonObject);

}
