package org.hinoob.chimebot.listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import org.hinoob.chimebot.util.AIUtil;

public class MessageListener extends ListenerAdapter {

    @SubscribeEvent
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw();
        event.getChannel().sendMessage(AIUtil.getResponse(message)).queue();
    }
}
