package com.kei.cisco.controller.api;

import com.kei.cisco.helpers.JsonHelper;
import com.kei.cisco.model.Lesson;
import com.kei.cisco.model.Test;
import com.kei.cisco.model.User;
import com.kei.cisco.service.LessonService;
import com.kei.cisco.service.TestService;
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
@RequestMapping("/api/lesson")
public class LessonApiController {

    @Autowired
    JsonHelper jsonHelper;

    @Autowired
    LessonService lessonService;

    @Autowired
    TestService testService;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllLessons() {
        List<Lesson> lessonsList = lessonService.findAll();
        JSONObject data = new JSONObject();
        data.put("lessonsList", lessonsList);
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/get", method = RequestMethod.GET)
    public String getLessonById(@PathVariable("id") int lessonId, ModelMap model) {
        Lesson lesson = lessonService.findById(lessonId);
        JSONObject data = new JSONObject();
        data.put("user", new JSONObject(lesson));
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editLesson(HttpServletRequest request) {
        JSONObject requestBody;
        Lesson lesson;
        try {
            requestBody = jsonHelper.getJsonBody(request);
            lesson = lessonService.findById(requestBody.getInt("name"));
            lesson.setName(requestBody.getString("name"));
            Test newTest = testService.findById(requestBody.getInt("addTestId"));
            Set<Test> newSetTest = lesson.getTests();
            newSetTest.add(newTest);
            lesson.setTests(newSetTest);
            lessonService.save(lesson);
        } catch (IOException e) {
            return jsonHelper.generateResponse(false,null,new JSONObject().put("error",e.getMessage()));
        }
        JSONObject data = new JSONObject();
        data.put("lesson", lesson);
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/del", method = RequestMethod.GET)
    public String deleteLesson(@PathVariable("id") int lessonId) {
        lessonService.delete(lessonId);
        return jsonHelper.generateResponse(true,null,null);
    }

    @ResponseBody
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createLesson() {
        Lesson newLesson = new Lesson();
        newLesson.setName("newLesson");
        lessonService.save(newLesson);
        return jsonHelper.generateResponse(true,null,null);
    }
}
