package org.hinoob.chimebot.util.ai;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.chat.OllamaChatMessage;
import io.github.ollama4j.models.chat.OllamaChatMessageRole;
import io.github.ollama4j.models.chat.OllamaChatResult;
import lombok.SneakyThrows;

import java.util.List;

public class AIUtil {

    @SneakyThrows
    public static String getResponse(ChatHistory history, String prompt) {
        String host = "http://192.168.1.173:11434"; // Replace with your server URL
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(60);
        history.addMessage(OllamaChatMessageRole.USER, prompt);

        List<OllamaChatMessage> messages = history.map();
        OllamaChatResult rs = ollamaAPI.chat("qwen2.5:1.5b", messages);
        history.addMessage(OllamaChatMessageRole.ASSISTANT, rs.getResponse());
        return rs.getResponse();
    }

}
