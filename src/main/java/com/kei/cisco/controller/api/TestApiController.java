package com.kei.cisco.controller.api;

import com.kei.cisco.helpers.JsonHelper;
import com.kei.cisco.model.Test;
import com.kei.cisco.service.TestService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by DenisTaras on 04.01.2017.
 */
@Controller
@RequestMapping("/api/test")
public class TestApiController {

    @Autowired
    JsonHelper jsonHelper;

    @Autowired
    TestService testService;

    @ResponseBody
    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public String getAllTests() {
        List<Test> testsList = testService.findAll();
        JSONObject data = new JSONObject();
        data.put("testsList", testsList);
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/get", method = RequestMethod.GET)
    public String getTestById(@PathVariable("id") int testId) {
        Test test = testService.findById(testId);
        JSONObject data = new JSONObject();
        data.put("test", new JSONObject(test));
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editTest(HttpServletRequest request) {
        JSONObject requestBody;
        Test test;
        try {
            requestBody = jsonHelper.getJsonBody(request).getJSONObject("test");
            test = testService.findById(requestBody.getInt("id"));
            test.setName(requestBody.getString("name"));
            testService.updateTest(test);
        } catch (IOException e) {
            return jsonHelper.generateResponse(false,null,new JSONObject().put("error",e.getMessage()));
        }
        JSONObject data = new JSONObject();
        data.put("test", test);
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/del", method = RequestMethod.GET)
    public String deleteTest(@PathVariable("id") int testId) {
        testService.deleteById(testId);
        return jsonHelper.generateResponse(true,null,null);
    }

    @ResponseBody
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createTest() {
        Test newTest = new Test();
        newTest.setName("newTest");
        testService.save(newTest);
        return jsonHelper.generateResponse(true,null,null);
    }
}
