package wd.pledge.guarantee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wd.pledge.guarantee.service.PledgeService;

@Controller
@RequestMapping("/")
public class PledgeController {

    @Autowired
    private PledgeService pledgeService;
}
