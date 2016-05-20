import sx.blah.discord.api.*;
import sx.blah.discord.util.*;

public class bot {
        public static IDiscordClient getClient(String token) throws DiscordException
        {
            return new ClientBuilder().withToken(token).login();
        }
    }
