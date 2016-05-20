import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

import java.util.ArrayList;
import java.util.List;

public class incogbot extends bot{
    public static IDiscordClient discordClient;
    public static String GENERAL = "";
    public static String INCOGNITO = "";

    private static config c = new config();

    public static void main(String[] args) throws Exception {
        c.getPropValues();

        discordClient = getClient(c.getToken());

        if(c.getBuild() == 0) {
            GENERAL = c.getTestGeneral();
            INCOGNITO = c.getTestIncognito();
        }
        else if(c.getBuild() == 1) {
            GENERAL = c.getRealGeneral();
            INCOGNITO = c.getRealIncognito();
        }

        while(true) {
            run();
            System.out.println("Sleeping for " + Integer.toString(c.getRefresh()/1000) + " seconds.");
            Thread.sleep(c.getRefresh());
        }
    }

    public static void sendMessage(IDiscordClient client, String ch, String msg) throws HTTP429Exception, DiscordException, MissingPermissionsException {
        new MessageBuilder(client).withChannel(ch).withContent(msg).build();
    }

    public static void run() throws Exception {
        holder h = new holder();
        List<post> posts = new ArrayList<>();

        posts.addAll(h.fetchPosts(c.getLimit(), c.getSubreddit()));

        links pl = new links();

        Thread.sleep(2000);
        System.out.println();
        System.out.println("Sending links...");
        sendMessage(discordClient, GENERAL, "Good evening kind sirs. I have updated the Incognito chat. Enjoy you FUCKS!");
        Thread.sleep(1000);

        System.out.println();

        for(post p : posts) {
            if(pl.readAndCompare(p.getURL()) == true) {
                System.out.println("Sending URL: " + p.getURL());
                sendMessage(discordClient, INCOGNITO, p.getURL());

                pl.write(p.getURL());

                Thread.sleep(1000);
            }
            else {
                System.out.println("Link has been sent today (" + p.getURL() + ").");
            }
        }

        System.out.println();
        System.out.println("Sending complete.");
    }
}
