import java.io.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class links {
    public static  Boolean readAndCompare(String url) throws IOException {
        String line;

        String filename = getFileName();

        if(new File("./exec/links/"+filename).exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader("./exec/links/"+filename));

                while ((line = in.readLine()) != null) {
                    if(url.equals(line)) {
                        in.close();
                        return false;
                    }
                }

                in.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    public static void write(String url) throws UnsupportedEncodingException, FileNotFoundException {
        String filename = getFileName();

        try {
            FileWriter fw = new FileWriter("./exec/links/"+filename,true);
            fw.write(url+"\n");
            fw.close();
        }
        catch(IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public static String getFileName() {
        DateFormat df = new SimpleDateFormat("dd-MM-yy");
        Date dateobj = new Date();
        String filename = df.format(dateobj) + ".txt";

        return filename;
    }
}
