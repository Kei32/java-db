package com.kei.cisco.controller;

import com.kei.cisco.helpers.JsonHelper;
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
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    JsonHelper jsonHelper;

    @ResponseBody
    @RequestMapping(value = "/revers", method = RequestMethod.POST)
    public String api3(ModelMap model, HttpServletRequest request) {

        JSONObject requestBody = null;
        try {
            requestBody = jsonHelper.getJsonBody(request);
        } catch (IOException e) {
            return jsonHelper.generateResponse(false, requestBody, null);
        }

        return jsonHelper.generateResponse(true, requestBody, null);
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
