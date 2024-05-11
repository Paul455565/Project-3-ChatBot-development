package za.ac.cput.util;

import za.ac.cput.service.GPTClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Chatbot {
    private final GPTClient gptClient;

    public Chatbot() {
        this.gptClient = new GPTClient();
    }

    public void startChat() {
        System.out.println("Chatbot is ready. Type 'exit' to quit.");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while (true) {
                System.out.print("> ");
                input = reader.readLine();
                if ("exit".equalsIgnoreCase(input)) break;

                String response = gptClient.getResponse(input);
                System.out.println("Chatbot: " + response.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Chatbot chatbot = new Chatbot();
        chatbot.startChat();
    }
}

