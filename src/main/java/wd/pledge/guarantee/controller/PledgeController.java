package wd.pledge.guarantee.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wd.pledge.guarantee.entity.Location;
import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.service.PledgeService;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Collection;

@Controller
@RequestMapping("/")
public class PledgeController {

    @Autowired
    private PledgeService pledgeService;

    @RequestMapping(value = "/pledge/exwarehousing")
    @ResponseBody
    public String setExWarehousing(@RequestParam("id") Integer id)
            throws IOException, ClassNotFoundException, SQLException
    {
        return pledgeService.setExWarehousing(id);
    }

    @RequestMapping(value = "/pledge/exwarehoused")
    @ResponseBody
    public String setExWarehoused(@RequestParam("id") Integer id)
            throws IOException, ClassNotFoundException, SQLException
    {
        return pledgeService.setExWarehoused(id);
    }

    @PostMapping(value = "/pledge/add")
    @ResponseBody
    public String addPledge(@Valid @RequestBody JSONObject jsonObject)
            throws URISyntaxException
    {
        return pledgeService.createPledge(jsonObject);
    }
      
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
