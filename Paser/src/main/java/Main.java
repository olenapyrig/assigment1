package main.java;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Scanner;


public class Main {


    @SneakyThrows
    public static void main(String[] args) {
        String url = "https://uk.wikipedia.org/wiki/%D0%9C%D1%96%D1%81%D1%82%D0%B0_%D0%A3%D0%BA%D1%80%D0%B0%D1%97%D0%BD%D0%B8_(%D0%B7%D0%B0_%D0%B0%D0%BB%D1%84%D0%B0%D0%B2%D1%96%D1%82%D0%BE%D0%BC)";
        Document doc = Jsoup.connect(url).get();
        //System.out.println(doc.title());
        Elements cities = doc.select("table tr");
        Scanner scan = new Scanner(System.in);
        String find_city = scan.nextLine();
        find_city = find_city.toLowerCase();
        City[] parsedCities = new City[cities.size()]; // You can use List`s or other java Collections
        int counter = 0;
        for (Element city : cities) {
            City myCity = City.parse(city);
            if (myCity != null) {
                System.out.println(myCity);
                parsedCities[counter] = myCity;
                counter++;
            }
        }
        for (int i =0; i<parsedCities.length; i++){
            if (parsedCities[i].getName().toLowerCase().equals(find_city)){
                String city_name = parsedCities[i].getName();
                String city_numberofCitizens = parsedCities[i].getnumberOfCitizens();
                String city_yearOfFound = parsedCities[i].getyearOfFound();
                String city_administrativeArea = parsedCities[i].getadministrativeArea();
                String city_area = parsedCities[i].getarea();
                System.out.print("City: "+ city_name +
                        "\nArea: "+ city_area +
                        "\nAdministrative Area: "+ city_administrativeArea +
                        "\nNumber of citizen: "+ city_numberofCitizens +
                        "\nYear of found: "+ city_yearOfFound);
                System.out.print(WeatherForecaster.forecast(parsedCities[i]));
            }
        }
    }

}
