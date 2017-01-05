package com.kei.cisco.controller;

import com.kei.cisco.helpers.ControllerHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by DenisTaras on 04.01.2017.
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ControllerHelper controllerHelper;

    @ResponseBody
    @RequestMapping(value = "/v1_users", method = RequestMethod.GET)
    public String api(ModelMap model) {
        return "{\"data\": [\"a\",\"b\",\"c\"]}";
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String api2(ModelMap model) {
        JSONObject response = new JSONObject();
        JSONArray itemList = new JSONArray();
        JSONObject item1 = new JSONObject();
        JSONObject item2 = new JSONObject();
        JSONObject item3 = new JSONObject();
        item1.put("text", "Item 1");
        item2.put("text", "Item 2");
        item3.put("text", "Item 3");
        itemList.put(item1);
        itemList.put(item2);
        itemList.put(item3);
        response.put("data", itemList);
        return response.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/revers", method = RequestMethod.POST)
    public String api3(ModelMap model, HttpServletRequest request) {

        JSONObject requestBody = null;
        try {
            requestBody = controllerHelper.getJsonBody(request);
        } catch (IOException e) {
            return controllerHelper.generateResponse(false, requestBody, null);
        }

        JSONArray groceryList = requestBody.getJSONArray("groceryList");

        JSONObject data = new JSONObject();
        JSONObject item1 = new JSONObject();
        JSONObject item2 = new JSONObject();
        JSONObject item3 = new JSONObject();
        item1.put("text", "Item 1");
        item2.put("text", "Item 2");
        item3.put("text", "Item 3");
        groceryList.put(item1);
        groceryList.put(item2);
        groceryList.put(item3);
        data.put("groceryList", groceryList);
        return controllerHelper.generateResponse(true, requestBody, data);
    }

    @ResponseBody
    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public String echo(ModelMap model, HttpServletRequest request) {

        JSONObject requestBody = null;
        try {
            requestBody = controllerHelper.getJsonBody(request);
        } catch (IOException e) {
            return controllerHelper.generateResponse(false, requestBody, null);
        }
        return controllerHelper.generateResponse(true, requestBody, requestBody);
    }
}
