package com.kei.cisco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DenisTaras on 04.01.2017.
 */
@Controller
@RequestMapping("/workspace")
public class WorkspaceController {

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public String workspace(ModelMap model) {
        return "workspace";
    }

    @ResponseBody
    @RequestMapping(value = "/api/v1_users", method = RequestMethod.GET)
    public String api(ModelMap model) {
        return "{\"data\": [\"a\",\"b\",\"c\"]}";
    }
}
