package com.kei.cisco.controller.api;

import com.kei.cisco.helpers.JsonHelper;
import com.kei.cisco.model.Course;
import com.kei.cisco.model.Lesson;
import com.kei.cisco.model.Test;
import com.kei.cisco.model.User;
import com.kei.cisco.service.CourseService;
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
@RequestMapping("/api/course")
public class CourseApiController {

    @Autowired
    JsonHelper jsonHelper;

    @Autowired
    CourseService courseService;

    @Autowired
    TestService testService;

    @Autowired
    LessonService lessonService;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllCourse() {
        List<Course> coursesList = courseService.findAll();
        JSONObject data = new JSONObject();
        data.put("coursesList", coursesList);
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/get", method = RequestMethod.GET)
    public String getCourseById(@PathVariable("id") int courseId, ModelMap model) {
        Course course = courseService.findById(courseId);
        JSONObject data = new JSONObject();
        data.put("course", new JSONObject(course));
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editCourse(HttpServletRequest request) {
        JSONObject requestBody;
        Course course;
        try {
            requestBody = jsonHelper.getJsonBody(request);
            course = courseService.findById(requestBody.getInt("name"));
            course.setName(requestBody.getString("name"));
            Lesson newLesson = lessonService.findById(requestBody.getInt("addLessonId"));
            Set<Lesson> newSetLesson = course.getLessons();
            newSetLesson.add(newLesson);
            course.setLessons(newSetLesson);
            Test newTest = testService.findById(requestBody.getInt("addTestId"));
            Set<Test> newSetTest = course.getTests();
            newSetTest.add(newTest);
            course.setTests(newSetTest);
            courseService.save(course);
        } catch (IOException e) {
            return jsonHelper.generateResponse(false,null,new JSONObject().put("error",e.getMessage()));
        }
        JSONObject data = new JSONObject();
        data.put("course", course);
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/del", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable("id") int courseId) {
        courseService.delete(courseId);
        return jsonHelper.generateResponse(true,null,null);
    }

    @ResponseBody
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createCourse() {
        Course newCourse = new Course();
        newCourse.setName("newCourse");
        courseService.save(newCourse);
        return jsonHelper.generateResponse(true,null,null);
    }
}
