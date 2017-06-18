package com.kei.cisco.controller.api;

import com.kei.cisco.helpers.JsonHelper;
import com.kei.cisco.model.User;
import com.kei.cisco.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DenisTaras on 04.01.2017.
 */
@Controller
@RequestMapping("/api/component")
public class ComponentApiController {

    @Autowired
    JsonHelper jsonHelper;

    @Autowired
    UserService userService;

    @Autowired
    private ServletContext servletContext;

    @ResponseBody
    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    public String getAllUser(@PathVariable("name") String componentName) {
        InputStream inputStream = null;
        String component;
        try {
            String path = "/static/vue/component/"+componentName+".html";
            inputStream = servletContext.getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            component = sb.toString();
        } catch (IOException e) {
            return jsonHelper.generateResponse(false,null,null);
        }
        JSONObject data = new JSONObject();
        data.put("component", component);
        return jsonHelper.generateResponse(true,null,data);
    }
}
