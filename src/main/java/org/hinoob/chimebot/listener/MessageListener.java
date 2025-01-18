package org.hinoob.chimebot.listener;

import io.github.ollama4j.models.chat.OllamaChatMessageRole;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import org.hinoob.chimebot.util.ai.AIUtil;
import org.hinoob.chimebot.util.ai.ChatHistory;

import java.util.function.Consumer;

public class MessageListener extends ListenerAdapter {

    @SubscribeEvent
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;

        ChatHistory chatHistory = ChatHistory.chatHistories.get(event.getAuthor().getId());
        if(chatHistory == null) {
            chatHistory = new ChatHistory();
            ChatHistory.chatHistories.put(event.getAuthor().getId(), chatHistory);
        }


        String message = event.getMessage().getContentRaw();
        if(event.getMessage().getMentions().getMembers().stream().anyMatch(s -> s.getId().equals(event.getJDA().getSelfUser().getId())) || message.startsWith(".chat")) {
            // Check if bot sent last message
            if(!chatHistory.getMessages().isEmpty() && chatHistory.getMessages().getLast().getRole() == OllamaChatMessageRole.USER) {
                event.getMessage().reply("Wait before I reply to your last message...").queue(new Consumer<Message>() {
                    @Override
                    public void accept(Message message) {
                        message.delete().queueAfter(2, java.util.concurrent.TimeUnit.SECONDS);
                    }
                });
                return;
            }
            if(message.startsWith(".chat")) {
                message = message.replace(".chat", "").trim();
            } else {
                message = message.replace("<@" + event.getJDA().getSelfUser().getId() + ">", "").trim();
            }
            event.getChannel().sendTyping().queue();
            AIUtil.getResponse(chatHistory, "Keep your response to this prompt very short: " + message).thenAccept(response -> {
                event.getMessage().reply(response).queue();
            });
        } else if(message.equals(".clearhistory")) {
            ChatHistory.chatHistories.remove(event.getAuthor().getId());
            event.getMessage().reply("Chat history/memory has been cleared!").queue();
        }
    }
}
