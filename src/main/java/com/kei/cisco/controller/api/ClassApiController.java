package com.kei.cisco.controller.api;

import com.kei.cisco.helpers.JsonHelper;
import com.kei.cisco.model.*;
import com.kei.cisco.model.Class;
import com.kei.cisco.service.ClassService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by DenisTaras on 04.01.2017.
 */
@Controller
@RequestMapping("/api/class")
public class ClassApiController {

    @Autowired
    JsonHelper jsonHelper;

    @Autowired
    ClassService classService;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllClass() {
        List<Class> classesList = classService.findAll();
        JSONObject data = new JSONObject();
        data.put("classesList", classesList);
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/get", method = RequestMethod.GET)
    public String getClassById(@PathVariable("id") int classId) {
        Class classes = classService.findById(classId);
        JSONObject data = new JSONObject();
        data.put("classes", new JSONObject(classes));
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editClass(HttpServletRequest request) {
        JSONObject requestBody;
        Class classes;
        try {
            requestBody = jsonHelper.getJsonBody(request);
            classes = classService.findById(requestBody.getInt("name"));
            classes.setName(requestBody.getString("name"));
            classService.save(classes);
        } catch (IOException e) {
            return jsonHelper.generateResponse(false,null,new JSONObject().put("error",e.getMessage()));
        }
        JSONObject data = new JSONObject();
        data.put("classes", classes);
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/del", method = RequestMethod.GET)
    public String deleteClass(@PathVariable("id") int classId) {
        classService.delete(classId);
        return jsonHelper.generateResponse(true,null,null);
    }

    @ResponseBody
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createClass() {
        Class newClass = new Class();
        newClass.setName("newClass");
        newClass.setDescription("newClassDescription");
        classService.save(newClass);
        return jsonHelper.generateResponse(true,null,null);
    }
}
