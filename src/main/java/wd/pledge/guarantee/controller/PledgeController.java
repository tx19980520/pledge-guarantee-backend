package wd.pledge.guarantee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.service.PledgeService;

@Controller
@RequestMapping("/")
public class PledgeController {

    @Autowired
    private PledgeService pledgeService;

    @GetMapping(path="/getOne")
    @ResponseBody
    public Pledge getOnePledge(@RequestParam("pledgeID") Integer pledgeId) {
        System.out.println("Controller HERE GET pledgeId: " + pledgeId);
        return pledgeService.get_one_pledge_info(pledgeId);
    }



    @GetMapping(path="/getAll")
    public @ResponseBody Iterable<Pledge> getAllPledge() {
        return pledgeService.findAll();
    }

    @GetMapping(path="/test")
    public @ResponseBody String test() {
        return "test";
    }
}
