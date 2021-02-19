package ee.bcs.valiit.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrismaScrap {
    public static void main(String[] args) throws IOException {
        int[] categories = {16972, 16973, 16974, 16975, 16976, 16977, 17336, 19273, 19274, 19275, 19276, 19290, 19281, 19282, 19283, 19284, 19286, 19287, 19288, 19289,
                19291, 17152, 17156, 17158, 17159, 17160, 17161, 17162, 17163, 17164, 17157, 17097, 17098, 17099, 17100, 17101, 17102, 17103, 17104, 17105, 17106, 17107,
                17108, 17109, 17147, 17148, 17149, 17150, 17117, 17118, 17119, 17115, 17179, 17180, 17181, 17182, 17183, 17199, 17200, 17201, 17203, 17204, 17316, 17317, 17318, 17319,
                17320, 17321, 17322, 17323, 17324, 17325, 17326, 17327, 17328, 17205, 17206, 17207, 17208, 17209, 17210, 17211, 17213, 17214, 17216, 17217, 17218, 17219, 17554,
                17557, 17560, 17561, 17562, 17563, 17564, 17565, 17566, 17567, 17568, 17569, 17570, 17571, 17573, 17574, 17252, 17253, 17254, 17255, 17256, 17258, 17260,
                17261, 17262, 17263, 17264, 19211, 19210, 19213, 19208, 17833, 17834, 17835, 17836, 17837, 17838, 17839, 18051, 18052, 18056, 18057, 18059, 18060, 18061,
                18062, 18063, 18064, 18065, 18066, 18097, 18086, 18087, 18088, 18089, 18090, 18091, 18092, 18093, 18094, 18096, 18095, 17329, 17330, 17331, 17332, 17333, 17334, 18098, 18099,
                18100, 18102, 18107, 18104, 18101, 18105, 18106, 18103, 18108, 18168, 18169, 18170, 18171, 18172, 18174, 18175, 18177, 18178, 18179, 18180, 18182, 18184,
                18185, 17295, 17296, 17297, 17298, 17299, 17301, 17302, 17303, 17304, 18190, 18191, 18192, 18193, 18194, 18195, 18196, 18197, 18198, 18199, 18200, 18201,
                18202, 18203, 18204, 18207};
        int[] test = {16972};
        int productCount = 1;
        for (int category : test) {
            int pageNumber = 1;
            Boolean isPage= true;
            while(isPage) {
                String url = "https://www.prismamarket.ee/products/" + category + "/page/" + pageNumber;
                String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";
                Document pagePrisma = Jsoup.connect(url).userAgent(userAgent).get();
                List<String> urls = new ArrayList<>();
                List<String> pictureUrls = new ArrayList<>();
                for (Element webPage : pagePrisma.select("li.relative.item.effect.fade-shadow.js-shelf-item a")) {
                    urls.add(webPage.attr("href"));
                }
                for (Element imageElement : pagePrisma.select("div.image-wrapper.relative.js-image-wrapper.clearfix img")) {
                   pictureUrls.add(imageElement.attr("src"));
                }

                if (urls.size()<48) {
                    isPage = false;
                }
                String[] parse1 = pagePrisma.outerHtml().split("entries\":\\[");
                String[] parse2 = parse1[1].split("\\);");
                String replace1 = parse2[0].replace("},{", "}|{");
                String[] jsonStrings = replace1.split("\\|");

                int lstCounter = 0;
                for (String jsonString : jsonStrings) {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode actualObj = mapper.readTree(jsonString);
                    System.out.println(actualObj.get("ean"));
                    System.out.println(actualObj.get("name").textValue());
                    System.out.println(actualObj.get("aisle").textValue());
                    System.out.println(actualObj.get("price"));
                    System.out.println("https://www.prismamarket.ee/" + urls.get(lstCounter));
                    if(pictureUrls.get(lstCounter).startsWith("/")) {
                        System.out.println("https://www.prismamarket.ee/" + pictureUrls.get(lstCounter));
                    } else {
                        System.out.println(pictureUrls.get(lstCounter));
                    }

                    System.out.println(productCount);
                    System.out.println("<--------------------------------------------->");
                    lstCounter++;
                    productCount++;
                }
                pageNumber++;
            }

        }
    }


}
