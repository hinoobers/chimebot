package org.hinoob.chimebot.util.ai;

import io.github.ollama4j.models.chat.OllamaChatMessage;
import io.github.ollama4j.models.chat.OllamaChatMessageRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatHistory {

    public static Map<String, ChatHistory> chatHistories = new HashMap<>();

    private final List<ChatMessage> messages = new ArrayList<>();


    public class ChatMessage {
        private OllamaChatMessageRole role;
        private String message;

        public ChatMessage(OllamaChatMessageRole role, String message) {
            this.role = role;
            this.message = message;
        }

        public OllamaChatMessageRole getRole() {
            return role;
        }

        public String getMessage() {
            return message;
        }
    }

    public List<OllamaChatMessage> map() {
        List<OllamaChatMessage> messages = new ArrayList<>();
        for (ChatMessage message : this.messages) {
            messages.add(new OllamaChatMessage(message.getRole(), message.getMessage()));
        }
        return messages;
    }

    public void addMessage(OllamaChatMessageRole role, String message) {
        messages.add(new ChatMessage(role, message));
    }

}
