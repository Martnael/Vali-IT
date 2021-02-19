package ee.bcs.valiit.tasks;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;

public class ScrappTest {
    public static void main(String[] args) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";
        int count = 0;
        int maxCategoryNr = 17;
        for (int j = 1; j <= 13 ; j++ ) {
            int pageNumber = 1;
            Boolean isPage = true;
            while(isPage) {
                String url = "https://www.rimi.ee/epood/ee/tooted/c/SH-" + j + "?page=" + pageNumber + "&pageSize=100";
                Document pageRimi = Jsoup.connect(url).userAgent(userAgent).get();
                String page = pageRimi.outerHtml();
                String[] parse = page.split("\"impressions\": \\[");
                String[] parse2 = parse[1].split("]");
                String replace1 = parse2[0].replace(" },\n" + "            {", "}|{");
                String[] parse3 = replace1.split("\\|");
                int length = parse3.length;
                for (String s : parse3) {
                    System.out.println(s);
                }
                if (length < 100) {
                    isPage = false;
                }
                for (int i = 0; i < length; i++) {

                    String jsonString = parse3[i].stripLeading().stripTrailing();
                    System.out.println(jsonString);
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode actualObj = mapper.readTree(jsonString);
                    try {
                        System.out.println(actualObj.get("id").textValue());
                        System.out.println(actualObj.get("name").textValue());
                        System.out.println(actualObj.get("price").decimalValue());
                        System.out.println(actualObj.get("list").textValue());
                        String url2 = "https://www.rimi.ee/epood/ee/tooted/p/" + actualObj.get("id").textValue();
                        System.out.println(url2);



                        System.out.println("<--------------------------------------------------------------->");
                        count++;
                    }
                    catch (NullPointerException e) {
                        System.out.println(url);
                        continue;

                    }
                }
                pageNumber++;
            }
        }
        System.out.println(count);
    }
}

