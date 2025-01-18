package org.hinoob.chimebot.util.ai;

import io.github.ollama4j.models.chat.OllamaChatMessage;
import io.github.ollama4j.models.chat.OllamaChatMessageRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatHistory {

    public static Map<String, ChatHistory> chatHistories = new HashMap<>();

    private final List<OllamaChatMessage> messages = new ArrayList<>();

    public List<OllamaChatMessage> getMessages() {
        return messages;
    }

    public void addMessage(OllamaChatMessageRole role, String message) {
        OllamaChatMessage chatMessage = new OllamaChatMessage(role, message);
//        if(images != null) {
//            chatMessage.setImages(images);
//        }

        messages.add(chatMessage);
    }

}
