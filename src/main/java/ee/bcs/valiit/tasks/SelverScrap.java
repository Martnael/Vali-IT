package ee.bcs.valiit.tasks;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class SelverScrap {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, String> selverCategories = new HashMap<>();
//        selverCategories.put(1, "puu-ja-koogiviljad");
//        selverCategories.put(2, "liha-ja-kalatooted");
//        selverCategories.put(3, "piimatooted-munad-void");
//        selverCategories.put(4, "juustud");
//        selverCategories.put(5, "leivad-saiad-kondiitritooted");
//        selverCategories.put(6, "valmistoidud");
//        selverCategories.put(7, "kuivained-hoidised");
//        selverCategories.put(9, "maitseained-ja-puljongid");
//        selverCategories.put(10, "kastmed-olid");
//        selverCategories.put(11, "maiustused-kupsised-naksid");
//        selverCategories.put(12, "kulmutatud-toidukaubad");
        selverCategories.put(13, "joogid");
//        selverCategories.put(14, "lemmiklooma-kaubad");
//        selverCategories.put(15, "enesehooldustarbed");
//        selverCategories.put(16, "majapidamis-ja-kodukaubad");

        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";

        for (String pageCategoryValue : selverCategories.values()) {
            Boolean isNextPage = true;
            int pageNumber = 1;
            while (isNextPage) {
                String selverPageUrl = "https://www.selver.ee/" + pageCategoryValue + "?limit=96&p=" + pageNumber;
                Document pageSelver = Jsoup.connect(selverPageUrl).userAgent(userAgent).get();
                //System.out.println(pageSelver.outerHtml());
                int itemsOnPageCaount = 1;
                for (Element productinfos : pageSelver.select("ol.products-grid li")) {
                    String productId = productinfos.select("li").attr("data-product-id");
                    String productUrl = productinfos.select("a").attr("href");
                    String productPictureUrl = "";
                    for (Element imgElement : productinfos.select("img")) {
                        productPictureUrl = imgElement.attr("src");
                    }
                    String productName = productinfos.select("a").attr("title");
                    String[] priceField = productinfos.select("div.price-box").select("span.price").text().split("€");
                    String price = priceField[0].stripTrailing();
                    System.out.println(price);
                    System.out.println(productId);
                    System.out.println(productUrl);
                    System.out.println(productPictureUrl.replace("//", "").replace("m1.", "www."));
                    System.out.println(productName);
                    HashMap <String, String> information = proov(productUrl);
                    System.out.println(information.get("brand"));
                    System.out.println(information.get("ean"));
                    System.out.println(information.get("quantity"));
                    System.out.println(information.get("unitOfMeasure"));
                    System.out.println(itemsOnPageCaount);
                    System.out.println("<---------.");
                    itemsOnPageCaount++;
                }
                if (itemsOnPageCaount < 96) {
                    isNextPage = false;
                }
                pageNumber++;
            }


            //String url = "https://www.selver.ee/joogid?limit=96&p=1";
            //String url2 = "https://www.selver.ee/liha-ja-kalatooted?limit=96&p=2";
            //String url3 = "https://www.selver.ee/piimatooted-munad-void/piimad-koored?limit=96&p=2";

//            Document pageSelver = Jsoup.connect(url3).userAgent(userAgent).get();
//            HashMap<String, String> productsIdUrl = new HashMap<>();
//            int count = 1;
//            for (Element productinfos : pageSelver.select("ol.products-grid li")) {
//                String productId = productinfos.select("li").attr("data-product-id");
//                String productUrl = productinfos.select("a").attr("href");
//                String productName = productinfos.select("a").attr("title");
//                String productPic = productinfos.select("img").attr("src");
//                System.out.println(productId);
//                System.out.println(productUrl);
//                System.out.println(productName);
//                System.out.println(productPic);
//                proov(productUrl);
//                System.out.println("<---------->");
//                productsIdUrl.put(productId, productUrl);
//                for (String productUrl1 : productsIdUrl.values()) {
//                    Document productPageSelver = Jsoup.connect(productUrl1).userAgent(userAgent).get();
//                    for (Element divElement : productPageSelver.select("div.bundleSummary")) {
//                        System.out.println(divElement.outerHtml());
//                    }
//                }


            //   String[] priceField = productinfos.select("span.price").select("span").get(0).text().split("€");
            // System.out.println(priceField[0].stripTrailing());
            //count++;
            // System.out.println(productinfos.select("a").attr("title"));
            // System.out.println(productinfos.select("li").attr("data-product-id"));
            // System.out.println(productinfos.outerHtml());
        }
        ;

    }

    public void getPriceUpdateSelver() throws IOException {
        String url = "https://www.selver.ee/joogid?limit=96&p=1";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";
        Document pageSelver = Jsoup.connect(url).userAgent(userAgent).get();
        for (Element productinfos : pageSelver.select("ol.products-grid li")) {
            String productId = productinfos.select("li").attr("data-product-id");
            String productUrl = productinfos.select("a").attr("href");
            String[] priceField = productinfos.select("span.price").select("span").get(0).text().split("€");
            String productPrice = priceField[0].stripTrailing();
        }
    }

    public static HashMap<String, String> proov(String url) throws IOException {
        HashMap<String, String> information = new HashMap<>();
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";
        Document productPageSelver = Jsoup.connect(url).userAgent(userAgent).get();
        Elements divProduct = productPageSelver.select("div.product");
        String unitOfMeasure = "Määramata";
        String quantity = "Määramata";
        String producer = "Määramata";
        String ean = "Määramata";
        String[] uomSplit = divProduct.select("div.price-box").attr("data-unit-symbol").split("/");// unit of measure
        if (uomSplit.length > 1) {
            unitOfMeasure = uomSplit[1];
        }
        String quantitycheck = divProduct.select("div.price-box").attr("data-unit-factor");// quantity
        if (quantitycheck.length() > 1) {
            quantity = quantitycheck;
        }
        Elements divBox = productPageSelver.select("div.product-attributes-box");
        for (Element drElement : divBox.select("tr")) {
            if (drElement.select("th").text().equals("Ribakood")) {
                ean = drElement.select("td").text();
            }
            if (drElement.select("th").text().equals("Tootja")) {
                producer = drElement.select("td").text();
            }
        }
        information.put("brand", producer);
        information.put("ean", ean);
        information.put("quantity", quantity);
        information.put("unitOfMeasure", unitOfMeasure);
        return information;
    }

    public void getQtyAndUom(String name) {
        String replace = "name";

    }


}
