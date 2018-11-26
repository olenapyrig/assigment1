
package main.java;
import lombok.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Getter
@Setter
@ToString
public class City {
    private String name;
    private String url;
    private String administrativeArea;
    private String numberOfCitizens;
    private String yearOfFound;
    private Coordinates coordinates; // Set this
    private String area;

    private static final int INFO_SIZE = 6;

    private void setName(String new_name) {this.name = new_name;}
    private void setUrl(String new_url) {this.url = new_url;}
    private void setadministrativeArea(String new_administrative_area) {this.administrativeArea = new_administrative_area;}
    private void setnumberOfCitizens(String new_number_of_citizens) {this.numberOfCitizens = new_number_of_citizens;}
    private void setyearOfFound(String new_year_of_found) {this.yearOfFound = new_year_of_found;}
    private void setarea(String new_area) {this.area = new_area;}

    String getName() {return name;}
    String getadministrativeArea() {return administrativeArea;}
    String getnumberOfCitizens() {return numberOfCitizens;}
    String getyearOfFound() {return yearOfFound;}
    String getUrl() {return url;}
    String getarea() {return area;}

    public static City parse(Element city) {
        Elements info = city.select("td");
        if (info.size() == INFO_SIZE) {
            Element anchor = info.get(1).select("a").get(0);
            City myCity = new City();
            myCity.setName(anchor.attr("title"));
            myCity.setUrl(String.format("https://uk.wikipedia.org%s", anchor.attr("href")));
            myCity.setadministrativeArea(info.get(2).text());
            myCity.setnumberOfCitizens(info.get(3).text().split("!")[0]);
            myCity.setyearOfFound(info.get(4).select("a").get(0).text());
            myCity.setarea(info.get(5).text());
            return myCity;
        }
        return null;
    }



}