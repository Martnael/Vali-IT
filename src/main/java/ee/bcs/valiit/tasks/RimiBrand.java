package ee.bcs.valiit.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class RimiBrand {
    public static void main(String[] args) throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";
        String url = "https://www.rimi.ee/epood/ee/tooted/p/7232068";  /// 266894     7145207    1354576
        Document pageRimi = Jsoup.connect(url).userAgent(userAgent).get();
        String page = pageRimi.outerHtml();
        String[] parse = page.split("\"detail\": ");
        String[] parse2 = parse[1].split("}\n" +
                "}\\);");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(parse2[0]);
        String brand = actualObj.get("brand").textValue();
        HashMap<String, Object> elements = new HashMap<>();
        BigDecimal quantity;
        String measurement;
        Element productDetails = pageRimi.select("div.product__list-wrapper").get(0);
        int count = 0;
        int kogusIndex=0;
        String quantityAndMeasurement;
        for (Element p : productDetails.select("span")) {
            if (p.text().equals("Kogus")) {
                kogusIndex = count;
            }
            count++;
        }
        if (kogusIndex == 0){
            quantityAndMeasurement = "1000 g";
        } else {
            quantityAndMeasurement = productDetails.select("p").get(kogusIndex).text();
        }
        String[] quantityPlusUnit = quantityAndMeasurement.split(" ");
        quantity = new BigDecimal(quantityPlusUnit[0]);
        measurement = quantityPlusUnit[1];

        if (measurement.equals("l")) {
            quantity = quantity.multiply(new BigDecimal(1000));
            measurement = "ml";
        }
        if (measurement.equals("kg")) {
            quantity = quantity.multiply(new BigDecimal(1000));
            measurement = "g";
        }
        elements.put("brand", brand);
        elements.put("quantity", quantity);
        elements.put("unitOfMeasurement", measurement);
        System.out.println(elements.toString());
    }
}

