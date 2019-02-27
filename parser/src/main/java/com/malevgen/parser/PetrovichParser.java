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

public class PetrovichParser implements Parser {
    private static Logger logger = LogManager.getLogger(PetrovichParser.class.getName());
    private List<Catalog> catalogs = new ArrayList<>();
    private List<RentItem> rentItems = new ArrayList<>();

    @Override
    public void run() {
        logger.info("{}, PetrovichParser: Start: {}", Thread.currentThread().getName(), new Date());
        try {
            Document rentDocument = Jsoup.connect("https://petrovich.ru/services/equipment-rent/").get();
            Elements rentElements = rentDocument.getElementsByClass("rent__wrapper").select("a[href]");
            for (Element rentElement : rentElements) {
                String urlKey = rentElement.absUrl("href");
                String value = rentElement.select("[alt]").attr("alt");
                Catalog catalog = new Catalog(value, urlKey);
                refreshCatalog(catalog);
            }
            catalogs.forEach(this::refreshRentItem);
        } catch (IOException e) {
            logger.warn("Ошибка PetrovichParser:", e);
        }
        logger.info("PetrovichParser: catalogs.size = {}, rentItems.size = {}", this.catalogs.size(), this.rentItems.size());
    }

    private void refreshRentItem(Catalog catalog) {
        try {
            Document subRentDocument = Jsoup.connect(catalog.getProviderURl()).get();
            Elements rentElements = subRentDocument.getElementsByClass("rent__more_item");

            for (Element rentElement : rentElements) {
                String name = rentElement.getElementsByAttributeValue("class", "more__title").text();
                String description = rentElement.getElementsByAttributeValue("class", "more__snip").text();
                Elements cost = rentElement.getElementsByAttributeValue("class", "price__line");
                Map<String, String> rentItemPrice = createPrice(cost);
                RentItem rentItem = new RentItem(catalog, name, rentItemPrice, description, null);
                if (!this.rentItems.contains(rentItem)) {
                    this.rentItems.add(rentItem);
                }
            }
        } catch (IOException e) {
            logger.warn(e);
        }
    }

    private Map<String, String> createPrice(Elements cost) {
        Map<String, String> map = new HashMap<>(cost.size());
        for (Element element : cost) {
            map.put(element.getAllElements().get(1).text(), element.getAllElements().get(2).text());
        }
        return map;
    }

    private void refreshCatalog(Catalog catalog) {
        if (!this.catalogs.contains(catalog)) {
            this.catalogs.add(catalog);
        }
    }

}
