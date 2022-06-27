package com.thegreatapi.fundamentals.core;

import com.thegreatapi.fundamentals.core.model.Fundamentals;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@ApplicationScoped
final class BrazilFundamentalsService implements FundamentalsService {

    @Override
    public Fundamentals getFundamentals(String stock) {
        Document document;
        try {
            document = Jsoup.connect("https://www.fundamentus.com.br/detalhes.php?papel=" + stock).get();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        Elements spans = document.select("span[class=txt]");

        Element dySpan = spans.stream()
                              .filter(e -> "Div. Yield".equals(e.html()))
                              .findAny()
                              .orElseThrow();

        var dyString = dySpan.parent().nextElementSibling().getElementsByClass("txt").html();

        BigDecimal dy = new BigDecimal(dyString.replace(',', '.')
                                               .replace("%", ""))
                .divide(new BigDecimal("100"), RoundingMode.HALF_UP);

        return new Fundamentals(stock.toUpperCase(), dy);
    }
}
