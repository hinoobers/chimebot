package org.hinoob.chimebot.util.ai;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.chat.OllamaChatMessage;
import io.github.ollama4j.models.chat.OllamaChatMessageRole;
import io.github.ollama4j.models.chat.OllamaChatRequest;
import io.github.ollama4j.models.chat.OllamaChatResult;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AIUtil {

    @SneakyThrows
    public static CompletableFuture<String> getResponse(ChatHistory history, String prompt) {
        return CompletableFuture.supplyAsync(() -> {
            String host = "http://localhost:11434"; // Replace with your server URL
            OllamaAPI ollamaAPI = new OllamaAPI(host);
            ollamaAPI.setRequestTimeoutSeconds(222222);
            history.addMessage(OllamaChatMessageRole.USER, prompt);

            List<OllamaChatMessage> messages = history.getMessages();
            OllamaChatResult rs;
            try {
                rs = ollamaAPI.chat("qwqi", messages);
            } catch (OllamaBaseException | IOException | InterruptedException e) {
                e.printStackTrace();
                return "An error occurred while processing your request.";
            }
            String response = rs.getResponse();
            history.addMessage(OllamaChatMessageRole.ASSISTANT, response);
            return response;
        });
    }

}
