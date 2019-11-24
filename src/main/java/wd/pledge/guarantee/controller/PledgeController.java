package wd.pledge.guarantee.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.entity.Record;
import wd.pledge.guarantee.service.PledgeService;
import wd.pledge.guarantee.service.RecordService;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Controller
@RequestMapping("/pledge")
public class PledgeController {

    @Autowired
    private PledgeService pledgeService;

    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "/exwarehousing")
    @ResponseBody
    public String setExWarehousing(@RequestParam("id") Integer id)
            throws IOException, ClassNotFoundException, SQLException {
        return pledgeService.setExWarehousing(id);
    }

    @RequestMapping(value = "/exwarehoused")
    @ResponseBody
    public String setExWarehoused(@RequestParam("id") Integer id)
            throws IOException, ClassNotFoundException, SQLException {
        return pledgeService.setExWarehoused(id);
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public String addPledge(@Valid @RequestBody JSONObject jsonObject)
            throws URISyntaxException {
        return pledgeService.createPledge(jsonObject);
    }

    @GetMapping(value = "/record")
    @ResponseBody
    public String getOnePledgeRecordJson(@RequestParam("id") Integer pledgeId) {
        Record record = recordService.getRecord(pledgeId);
        return JSON.toJSONString(record);
    }

    @GetMapping(value = "/getOneJson")
    @ResponseBody
    public String getOnePledgeJson(@RequestParam("pledgeID") Integer pledgeId) {
        System.out.println("Controller HERE GET pledgeId: " + pledgeId);
        Pledge pledge = pledgeService.get_one_pledge_info(pledgeId);
        return JSON.toJSONString(pledge);
    }

    @GetMapping(value = "/getOne")
    @ResponseBody
    public Pledge getOnePledge(@RequestParam("pledgeID") Integer pledgeId) {
        System.out.println("Controller HERE GET pledgeId: " + pledgeId);
        return pledgeService.get_one_pledge_info(pledgeId);
    }

    @RequestMapping(value = "/getAll")
    public @ResponseBody
    Iterable<Pledge> getAllPledge() {
        return pledgeService.findAll();
    }
}
