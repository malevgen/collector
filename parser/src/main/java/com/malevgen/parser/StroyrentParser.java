package com.malevgen.parser;

import com.malevgen.model.Catalog;
import com.malevgen.model.RentItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class StroyrentParser implements Parser {
    private static Logger logger = LogManager.getLogger(StroyrentParser.class.getName());
    private List<Catalog> catalogs = new ArrayList<>();
    private List<RentItem> rentItems = new ArrayList<>();

    @Override
    public void run() {
        logger.info("{}, StroyrentParser: Start: {}", Thread.currentThread().getName(), new Date());
        try {
            Document rentDocument = Jsoup.connect("https://stroyrent.ru/rent/").get();
            Elements rentElements = rentDocument.getElementsByClass("col-lg-2 col-md-4 col-sm-6 col-xs-6").attr("class", "item-title");
            for (Element rentElement : rentElements) {
                String urlKey = rentElement.getAllElements().select("a[href]").get(0).absUrl("href");
                String value = rentElement.text();
                Catalog catalog = new Catalog(value, urlKey);
                refreshCatalog(catalog);
            }
            catalogs.forEach(this::refreshRentItem);

        } catch (IOException e) {
            logger.warn("Ошибка StroyrentParser:", e);
        }
        logger.info("StroyrentParser: catalogs.size = {}, rentItems.size = {}", this.catalogs.size(), this.rentItems.size());

    }

    private void refreshRentItem(Catalog catalog) {
        String countRentItem = "?pagenum=512";
        try {
            Document subRentDocument = Jsoup.connect(catalog.getProviderURl() + countRentItem).get();
            Elements rentElements = subRentDocument.getElementsByClass("item");
            for (Element rentElement : rentElements) {
                String name = rentElement.getElementsByClass("item-name").text();
                String description = rentElement.getElementsByClass("desc-product").text();
//                Elements cost = rentElement.getElementsByAttributeValue("class", "price__line");
                Map<String, String> rentItemPrice = new HashMap<>();
                String summa = "111";//rentElement.getElementsByClass("want_this_tool").get(0).dataset().get("item-price");
                rentItemPrice.put("Сутки",summa);

                RentItem rentItem = new RentItem(catalog, name, rentItemPrice, description, null);
                if (!this.rentItems.contains(rentItem)) {
                    this.rentItems.add(rentItem);
                }

            }
        } catch (IOException e) {
            logger.warn(e);
        }


    }

    private void refreshCatalog(Catalog catalog) {
        if (!this.catalogs.contains(catalog)) {
            this.catalogs.add(catalog);
        }
    }


}
