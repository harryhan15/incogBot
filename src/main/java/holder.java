import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class holder {
    private String URL = "https://www.reddit.com/r/";

    List<post> fetchPosts(String limit, String subreddit) {
        URL = URL + subreddit + "/top.json?after=AFTER?limit=" + limit;

        String raw = remoteData.readContents(URL);
        List<post> list = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(raw)
                    .getJSONObject("data");
            JSONArray children = data.getJSONArray("children");

            for (int i = 0; i < children.length(); i++) {
                JSONObject cur = children.getJSONObject(i)
                        .getJSONObject("data");

                post p = new post();

                p.title = cur.optString("title");
                p.url = cur.optString("url");
                p.numComments = cur.optInt("num_comments");
                p.points = cur.optInt("score");
                p.author = cur.optString("author");
                p.subreddit = cur.optString("subreddit");
                p.permalink = cur.optString("permalink");
                p.domain = cur.optString("domain");
                p.id = cur.optString("id");

                if (p.title != null)
                    list.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}