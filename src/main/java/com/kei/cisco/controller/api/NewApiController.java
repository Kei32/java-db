package com.kei.cisco.controller.api;

import com.kei.cisco.helpers.JsonHelper;
import com.kei.cisco.model.Trash;
import com.kei.cisco.service.TrashService;
import org.json.JSONArray;
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
@RequestMapping("/new_api")
public class NewApiController {

    @Autowired
    JsonHelper jsonHelper;

    @Autowired
    TrashService trashService;

    @ResponseBody
    @RequestMapping(value = "create/{type}", method = RequestMethod.POST)
    public String create(@PathVariable("type") String type, HttpServletRequest request) throws IOException {
        Trash newTrash = new Trash();
        newTrash.setType(type);
        newTrash.setRaw(jsonHelper.getJsonBody(request).toString());
        int id = trashService.create(newTrash);
        JSONObject data = new JSONObject();
        data.put(type + "_id", id);
        return jsonHelper.generateResponse(true, null, data);
    }

    @ResponseBody
    @RequestMapping(value = "read/{type}/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("type") String type, @PathVariable("id") String id) {
        if (id.equals("all")) {
            List<Trash> rawTrashList = trashService.read(type);
            JSONArray trashList = new JSONArray();
            rawTrashList.forEach((trash) -> {
                trashList.put(new JSONObject(trash.getRaw().toString()).put("id", trash.getId()));
            });
            JSONObject data = new JSONObject();
            data.put(type + "_list", trashList);
            return jsonHelper.generateResponse(true, null, data);
        } else {
            int intId = Integer.parseInt(id);
            Trash rawTrash = trashService.read(intId);
            JSONObject trash = new JSONObject(rawTrash.getRaw().toString()).put("id", rawTrash.getId());
            if (rawTrash.getType().equals("course")){
                JSONArray lessons = trash.getJSONArray("lessons");
                JSONArray newLessons = new JSONArray();
                lessons.forEach((lesson) ->{
                    int lessonId = Integer.parseInt(lesson.toString());
                    Trash rawLesson = trashService.read(lessonId);
                    JSONObject oneLesson = new JSONObject(rawLesson.getRaw().toString()).put("id", rawLesson.getId());
                    newLessons.put(oneLesson);
                });
                trash.put("lessons", newLessons);
            }
            JSONObject data = new JSONObject();
            data.put(type, trash);
            return jsonHelper.generateResponse(true, null, data);
        }
    }

    @ResponseBody
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, HttpServletRequest request) throws IOException {
        Trash trash = trashService.read(id);
        trash.setRaw(jsonHelper.getJsonBody(request).toString());
        return trashService.update(trash)
                ? jsonHelper.generateResponse(true, null, null)
                : jsonHelper.generateResponse(false, null, null);
    }

    @ResponseBody
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        Trash trash = trashService.read(id);
        if(trash.getType().equals("course")){
            JSONObject trashJson = new JSONObject(trash.getRaw());
            JSONArray lessons = trashJson.getJSONArray("lessons");
            lessons.forEach(item -> trashService.delete((int) item));
            return trashService.delete(id)
                    ? jsonHelper.generateResponse(true, null, null)
                    : jsonHelper.generateResponse(false, null, null);
        }
        return trashService.delete(id)
                ? jsonHelper.generateResponse(true, null, null)
                : jsonHelper.generateResponse(false, null, null);
    }

    @ResponseBody
    @RequestMapping(value = "link/{id_a}/{id_b}", method = RequestMethod.GET)
    public String link(@PathVariable("id_a") int idA, @PathVariable("id_b") int idB) {
        Trash rawTrash = trashService.read(idA);
        JSONObject trash = new JSONObject(rawTrash.getRaw());
        if (rawTrash.getType().equals("course")) {
            JSONArray lessons = trash.getJSONArray("lessons");
            lessons.put(idB);
            trash.put("lessons",lessons);
        }
        if (rawTrash.getType() == "lesson") {
            trash.get("tests");
        }

        rawTrash.setRaw(trash.toString());
        trashService.update(rawTrash);
        return jsonHelper.generateResponse(true, null, null);
    }

    @ResponseBody
    @RequestMapping(value = "unlink/{id_a}/{id_b}", method = RequestMethod.GET)
    public String unlink(@PathVariable("id_a") int idA, @PathVariable("id_b") int idB) {
        Trash rawTrash = trashService.read(idA);
        JSONObject trash = new JSONObject(rawTrash.getRaw());
        if (rawTrash.getType().equals("course")) {
            JSONArray lessons = trash.getJSONArray("lessons");
            for(int i = 0; i<lessons.length();i++) {
                if(lessons.getInt(i) == idB ){
                    lessons.remove(i);
                    i--;
                }
            }
            trash.put("lessons",lessons);
        }
        if (rawTrash.getType().equals("lesson")) {
            trash.get("tests");
        }

        rawTrash.setRaw(trash.toString());
        trashService.update(rawTrash);
        return jsonHelper.generateResponse(true, null, null);
    }

}
