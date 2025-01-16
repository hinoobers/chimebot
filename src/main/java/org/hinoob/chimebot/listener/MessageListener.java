package org.hinoob.chimebot.listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import org.hinoob.chimebot.util.ai.AIUtil;
import org.hinoob.chimebot.util.ai.ChatHistory;

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
            if(message.startsWith(".chat")) {
                message = message.replace(".chat", "").trim();
            } else {
                message = message.replace("<@" + event.getJDA().getSelfUser().getId() + ">", "").trim();
            }

            event.getMessage().reply(AIUtil.getResponse(chatHistory, message)).queue();
        }
    }
}
