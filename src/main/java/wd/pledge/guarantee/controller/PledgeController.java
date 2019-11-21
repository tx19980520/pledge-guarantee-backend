package wd.pledge.guarantee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wd.pledge.guarantee.service.PledgeService;

import java.io.IOException;
import java.sql.SQLException;

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
}
