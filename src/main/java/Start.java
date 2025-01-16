import org.hinoob.chimebot.ChimeBot;

public class Start {

    public static void main(String[] args) {
        ChimeBot.setInstance(new ChimeBot());
        ChimeBot.getInstance().start();
    }
}
