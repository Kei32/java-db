package com.kei.cisco.controller.api;

import com.kei.cisco.helpers.JsonHelper;
import com.kei.cisco.model.Test;
import com.kei.cisco.model.User;
import com.kei.cisco.service.UserService;
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

/**
 * Created by DenisTaras on 04.01.2017.
 */
@Controller
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    JsonHelper jsonHelper;

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllUser() {
        List<User> userList = userService.findAll();
        JSONObject data = new JSONObject();
        data.put("userList", userList);
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/get", method = RequestMethod.GET)
    public String getUserById(@PathVariable("id") int userId) {
        User user = userService.findById(userId);
        JSONObject data = new JSONObject();
        data.put("user", new JSONObject(user));
        return jsonHelper.generateResponse(true,null,data);
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(HttpServletRequest request) {
        JSONObject requestBody;
        User user;
        try {
            requestBody = jsonHelper.getJsonBody(request);
            user = userService.findById(requestBody.getInt("id"));
            user.setFirstName(requestBody.getString("firstName"));
            user.setLastName(requestBody.getString("lastName"));
            user.setEmail(requestBody.getString("email"));
            userService.save(user);
        } catch (IOException e) {
            return jsonHelper.generateResponse(false,null,new JSONObject().put("error",e.getMessage()));
        }
        JSONObject data = new JSONObject();
        data.put("user", user);
        return jsonHelper.generateResponse(true,null,data);
    }

}
