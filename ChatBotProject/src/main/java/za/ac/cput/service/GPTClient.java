package za.ac.cput.service;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Duration;

public class GPTClient {
    private static final String API_KEY = "sk-proj-7VvUiGV7UU1jnNAqmJBGT3BlbkFJsN10g6zYnfsNwayQkfeR";
    private static final String GPT_URL = "https://api.openai.com/v1/chat/completions";
    private final OkHttpClient httpClient;

    public GPTClient() {
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(30))
                .build();
    }

    public String getResponse(String prompt) throws IOException {
        // Build the JSON body
        JSONObject jsonBody = new JSONObject()
                .put("model", "gpt-3.5-turbo")
                .put("messages", new JSONArray()
                        .put(new JSONObject().put("role", "system").put("content", "You are a helpful assistant."))
                        .put(new JSONObject().put("role", "user").put("content", prompt))
                );

        // Build the request
        RequestBody body = RequestBody.create(
                jsonBody.toString(),
                MediaType.parse("application/json")
        );
        Request request = new Request.Builder()
                .url(GPT_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        // Retry mechanism with exponential backoff
        int retryCount = 0;
        int maxRetries = 3;
        long backoffTime = 5000; // Start with 5 seconds

        while (retryCount < maxRetries) {
            try (Response response = httpClient.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    JSONObject jsonResponse = new JSONObject(response.body().string());
                    return jsonResponse
                            .getJSONArray("choices")
                            .getJSONObject(0)
                            .getJSONObject("message")
                            .getString("content");
                } else if (response.code() == 429) {
                    retryCount++;
                    System.out.println("Rate limited. Retrying in " + backoffTime + " ms...");
                    Thread.sleep(backoffTime);
                    backoffTime *= 2; // Exponential backoff
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            } catch (InterruptedException e) {
                throw new IOException("Interrupted while sleeping", e);
            }
        }

        throw new IOException("Exceeded maximum retry attempts");
    }
}




