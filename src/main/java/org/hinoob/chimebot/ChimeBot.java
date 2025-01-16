package org.hinoob.chimebot;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.hinoob.chimebot.listener.MessageListener;
import org.hinoob.chimebot.util.FileUtil;

@Getter
public class ChimeBot {

    @Getter @Setter private static ChimeBot instance;

    private JDA jda;

    public void start() {
        this.jda = JDABuilder.createDefault(FileUtil.read("bot.token"))
                .addEventListeners(new MessageListener())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT).build();

        try {
            this.jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
