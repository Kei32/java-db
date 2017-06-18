package com.kei.cisco.controller.api;

import com.kei.cisco.helpers.JsonHelper;
import com.kei.cisco.model.Class;
import com.kei.cisco.service.ClassService;
import com.kei.cisco.service.impl.ClassServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by DenisTaras on 04.01.2017.
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    JsonHelper jsonHelper;

    @Autowired
    ClassService classService;

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserInfo(ModelMap model) {
        JSONObject response = new JSONObject();
        JSONObject user = new JSONObject();
        user.put("fullName", "Denis Taras")
                .put("icon", "/static/images/user1-128x128.jpg")
                .put("place", "Maser Web Developer")
                .put("member", "Nov. 1912");
        JSONObject data = new JSONObject();
        data.put("user", user);
        response.put("data", data);
        return response.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/init-test", method = RequestMethod.GET)
    public String getInitTest(ModelMap model) {
        JSONObject response = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("test", "Test value from server");
        response.put("data", data);
        return response.toString();
    }


    @ResponseBody
    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public String echo(ModelMap model, HttpServletRequest request) {

        JSONObject requestBody = null;
        try {
            requestBody = jsonHelper.getJsonBody(request);
        } catch (IOException e) {
            return jsonHelper.generateResponse(false, requestBody, null);
        }
        return jsonHelper.generateResponse(true, requestBody, requestBody);
    }
}
