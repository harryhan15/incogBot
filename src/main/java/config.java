import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class config extends settings {
    InputStream inputStream;

    public void getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            }
            else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property value and print it out
            limit = prop.getProperty("limit");
            subreddit = prop.getProperty("subreddit");
            build = Integer.parseInt(prop.getProperty("build"));
            test_general = prop.getProperty("test_general");
            test_incognito = prop.getProperty("test_incognito");
            real_general = prop.getProperty("real_general");
            real_incognito = prop.getProperty("real_incognito");
            token = prop.getProperty("token");

            System.out.println(token);

            refresh = Integer.parseInt(prop.getProperty("refresh"));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
     }

    public String getLimit() {
        if(limit.equals("1")) {
            return "50";
        }
        else if(limit.equals("2")) {
            return "100";
        }
        else {
            return "25";
        }
    }

    public String getSubreddit() {
        if(subreddit.equals("")) {
            return "reactiongifs";
        }
        else {
            return subreddit;
        }
    }

    public int getBuild() {
        if(build == 0) {
            return build;
        }
        else {
            return 1;
        }
    }

    public String getTestGeneral() {
        return test_general;
    }

    public String getTestIncognito() {
        return test_incognito;
    }

    public String getRealGeneral() {
        return real_general;
    }

    public String getRealIncognito() {
        return real_incognito;
    }

    public String getToken() {
        return token;
    }

    public int getRefresh() {
        return refresh;
    }
}