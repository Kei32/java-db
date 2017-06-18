package com.kei.cisco.helpers;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by DenisTaras on 05.01.2017.
 */
@Component
public class JsonHelper {

    public JSONObject getJsonBody(HttpServletRequest request) throws IOException {
        String line;
        StringBuilder sb = new StringBuilder();

        InputStream is;
        is = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return new JSONObject(sb.toString());
    }

    public String generateResponse(boolean success, JSONObject request, JSONObject data) {
        JSONObject response = new JSONObject();
        response.put("success", success);
        response.put("request", request);
        response.put("data", data);
        return response.toString();
    }
}
