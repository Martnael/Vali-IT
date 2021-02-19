package ee.bcs.valiit.tasks;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class PrismaCategories {
    public static void main(String[] args) throws IOException {
        String url = "https://www.prismamarket.ee/products/selection";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";
        Document pagePrisma = Jsoup.connect(url).userAgent(userAgent).get();
        System.out.println(pagePrisma.outerHtml());
        for (Element webPage : pagePrisma.select("ul.clear.left-navigation.menu.filters.dropdown.effect a")) {
            System.out.println(webPage.attr("href"));
        }
    }
}
