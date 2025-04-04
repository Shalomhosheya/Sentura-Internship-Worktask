package lk.Ijse.Sentura_Work_task.services;

import lk.Ijse.Sentura_Work_task.dto.UserDto;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${weavy.api.url}")
    private String weavyApiUrl;

    @Value("${weavy.api.token}")
    private String weavyApiToken;

    public String createWeavyUser(UserDto user) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        String json = String.format("{\"uid\":\"%s\", \"display_name\":\"%s\"}",
                user.getUid(), user.getDisplay_name());

        RequestBody body = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url(weavyApiUrl + "/api/users")
                .addHeader("Authorization", "Bearer " + weavyApiToken)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return "Error: " + response.message();
            }
            System.out.println("Weavy API URL: " + weavyApiUrl);

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public List<UserDto> getAllUsers() {
        Request request = new Request.Builder()
                .url(weavyApiUrl + "/api/users")
                .addHeader("Authorization", "Bearer " + weavyApiToken)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String jsonResponse = response.body().string();
            System.out.println("Weavy API URL: " + weavyApiUrl);

            return Arrays.asList(objectMapper.readValue(jsonResponse, UserDto[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
