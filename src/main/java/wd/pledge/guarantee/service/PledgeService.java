package wd.pledge.guarantee.service;

import wd.pledge.guarantee.entity.Pledge;

public interface PledgeService {
    Pledge get_one_pledge_info(Integer pledgeid);

    Iterable<Pledge> findAll();
}
