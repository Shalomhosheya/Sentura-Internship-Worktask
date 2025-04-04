package lk.Ijse.Sentura_Work_task.services;


import lk.Ijse.Sentura_Work_task.dto.UserDto;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {
    private final OkHttpClient client = new OkHttpClient();

    public String createWeavyUser(UserDto user) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        String json = String.format("{\"uid\":\"%s\", \"display_name\":\"%s\"}",
                user.getUid(), user.getDisplay_name());

        RequestBody body = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url("https://your-weavy-cloud-url.com/api/users")
                .addHeader("Authorization", "bearer wys_iO6FOzlS1IIY63Bd2B5YaFaeaA8kyt0gz1GC")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

}
