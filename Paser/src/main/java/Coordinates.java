package main.java;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Getter
@Setter
@ToString
public class Coordinates {
    public static String ln;
    public static String lt;

    public static String getLn(){return ln;}
    public static String getLt(){return lt;}

    Coordinates(String url) throws java.io.IOException{
        Document coor_doc = Jsoup.connect(url).get();
        ln = coor_doc.select(".mw-kartographer-maplink").first().attr("data-lon");
        lt = coor_doc.select(".mw-kartographer-maplink").first().attr("data-lat");
    }
}
