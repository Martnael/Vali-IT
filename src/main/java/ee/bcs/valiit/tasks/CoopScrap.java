package ee.bcs.valiit.tasks;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class CoopScrap {
    public static void main(String[] args) throws IOException {
        String url = "https://api.ecoop.ee/supermarket/products?page=6&category=1&language=et";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";
        Document pageCoop = Jsoup.connect(url).userAgent(userAgent).get();
        System.out.println(pageCoop);
    }
}
