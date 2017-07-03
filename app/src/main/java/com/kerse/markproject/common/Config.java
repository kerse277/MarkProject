package com.kerse.markproject.common;

import com.kerse.markproject.R;
import com.kerse.markproject.model.CollectionDefaultMapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kerse on 30.03.2017.
 */

public abstract class Config {

    public static final String ROOT_URL = "http://192.168.10.70:8081";
    public static final String SERVER_URL = "http://192.168.10.70";

    public static final String GOOGLE_API_KEY = "AIzaSyD_sYoYOxtb8X6176HqDcOY6HO8Fr1aymY";
    public static final String REQUEST_RECEIVED = "request.received";
    public static final String REQUEST_RECEIVED_REFRESHMARKS = "request.received.refreshmarks";

    public static final List<String> getPlaces() {
        List<String> places = new ArrayList<>();
        places.add("accounting");
        places.add("airport");
        places.add("amusement_park");
        places.add("aquarium");
        places.add("art_gallery");
        places.add("atm");
        places.add("bakery");
        places.add("bar");
        places.add("beauty_salon");
        places.add("book_store");
        places.add("bus_station");
        places.add("cafe");
        places.add("car_dealer");
        places.add("car_rental");
        places.add("car_repair");
        places.add("church");
        places.add("city_hall");
        places.add("clothing_store");
        places.add("dentist");
        places.add("doctor");
        places.add("electronics_store");
        places.add("embassy");
        places.add("finance");
        places.add("fire_station");
        places.add("florist");
        places.add("food");
        places.add("furniture_store");
        places.add("gas_station");
        places.add("gym");
        places.add("grocery_or_supermarket");
        places.add("hair_care");
        places.add("health");
        places.add("home_goods_store");
        places.add("hospital");
        places.add("jewelry_store");
        places.add("lawyer");
        places.add("library");
        places.add("local_government_office");
        places.add("lodging");
        places.add("meal_delivery");
        places.add("meal_takeaway");
        places.add("mosque");
        places.add("movie_theater");
        places.add("museum");
        places.add("night_club");
        places.add("park");
        places.add("parking");
        places.add("pet_store");
        places.add("pharmacy");
        places.add("post_office");
        places.add("real_estate_agency");
        places.add("restaurant");
        places.add("school");
        places.add("shopping_mall");
        places.add("spa");
        places.add("stadium");
        places.add("store");
        places.add("subway_station");
        places.add("taxi_stand");
        places.add("train_station");
        places.add("travel_agency");
        places.add("university");
        places.add("zoo");
        return places;
    }

    public static final Map<String, CollectionDefaultMapObject> getMarkIcons() {
        Map<String, CollectionDefaultMapObject> icons = new HashMap<>();
        icons.put("accounting", new CollectionDefaultMapObject().setMarkpath(R.drawable.acconting).setAlpha(0).setMarkDesc("Sayıların Efendisi"));
        icons.put("airport", new CollectionDefaultMapObject().setMarkpath(R.drawable.airport).setAlpha(0).setMarkDesc("İpsiz Uçurtma"));
        icons.put("amusement_park", new CollectionDefaultMapObject().setMarkpath(R.drawable.amusement_park).setAlpha(0).setMarkDesc("Eğlence Tutkunu"));
        icons.put("aquarium", new CollectionDefaultMapObject().setMarkpath(R.drawable.aquarium).setAlpha(0).setMarkDesc("Sualtı Kaşifi"));
        icons.put("art_gallery",new CollectionDefaultMapObject().setMarkpath(R.drawable.art_gallery).setAlpha(0).setMarkDesc("Kadifeli Artist"));
        icons.put("atm",new CollectionDefaultMapObject().setMarkpath(R.drawable.atm).setAlpha(0).setMarkDesc("........."));
        icons.put("bakery",new CollectionDefaultMapObject().setMarkpath(R.drawable.bakery).setAlpha(0).setMarkDesc("Fırıncının Küreği"));
        icons.put("bar",new CollectionDefaultMapObject().setMarkpath(R.drawable.bar).setAlpha(0).setMarkDesc("Ayyaş"));
        icons.put("beauty_salon",new CollectionDefaultMapObject().setMarkpath(R.drawable.beauty_salon).setAlpha(0).setMarkDesc("Süslü Unicorn"));
        icons.put("book_store",new CollectionDefaultMapObject().setMarkpath(R.drawable.book_store).setAlpha(0).setMarkDesc("Kitap Kurdu"));
        icons.put("bus_station",new CollectionDefaultMapObject().setMarkpath(R.drawable.bus_station).setAlpha(0).setMarkDesc("Yolcu"));
        icons.put("cafe",new CollectionDefaultMapObject().setMarkpath(R.drawable.cafe).setAlpha(0).setMarkDesc("........."));
        icons.put("car_dealer",new CollectionDefaultMapObject().setMarkpath(R.drawable.car_dealer).setAlpha(0).setMarkDesc("Araba Sevdalısı"));
        icons.put("car_rental",new CollectionDefaultMapObject().setMarkpath(R.drawable.car_rental).setAlpha(0).setMarkDesc(".........."));
        icons.put("car_repair",new CollectionDefaultMapObject().setMarkpath(R.drawable.car_repair).setAlpha(0).setMarkDesc("Tamirci Çırağı"));
        icons.put("church",new CollectionDefaultMapObject().setMarkpath(R.drawable.church).setAlpha(0).setMarkDesc("Yüce İsa"));
        icons.put("city_hall",new CollectionDefaultMapObject().setMarkpath(R.drawable.city_hall).setAlpha(0).setMarkDesc("Başkan"));
        icons.put("clothing_store",new CollectionDefaultMapObject().setMarkpath(R.drawable.clothing_store).setAlpha(0).setMarkDesc("Süslü Püslü"));
        icons.put("dentist",new CollectionDefaultMapObject().setMarkpath(R.drawable.dentist).setAlpha(0).setMarkDesc("Diş Dostu"));
        icons.put("doctor",new CollectionDefaultMapObject().setMarkpath(R.drawable.doctor).setAlpha(0).setMarkDesc("Check-Up"));
        icons.put("electronics_store",new CollectionDefaultMapObject().setMarkpath(R.drawable.electronics_store).setAlpha(0).setMarkDesc("Teknoloji Meraklısı"));
        icons.put("embassy",new CollectionDefaultMapObject().setMarkpath(R.drawable.embassy).setAlpha(0).setMarkDesc("Büyükelçi"));
        icons.put("finance",new CollectionDefaultMapObject().setMarkpath(R.drawable.finance).setAlpha(0).setMarkDesc("............"));
        icons.put("fire_station",new CollectionDefaultMapObject().setMarkpath(R.drawable.fire_station).setAlpha(0).setMarkDesc("Ateşli"));
        icons.put("florist",new CollectionDefaultMapObject().setMarkpath(R.drawable.florist).setAlpha(0).setMarkDesc("Çiçekçi Gacı"));
        icons.put("food",new CollectionDefaultMapObject().setMarkpath(R.drawable.food).setAlpha(0).setMarkDesc("Gurme"));
        icons.put("furniture_store",new CollectionDefaultMapObject().setMarkpath(R.drawable.furniture_store).setAlpha(0).setMarkDesc("........"));
        icons.put("gas_station",new CollectionDefaultMapObject().setMarkpath(R.drawable.gas_station).setAlpha(0).setMarkDesc("Pompacı"));
        icons.put("gym",new CollectionDefaultMapObject().setMarkpath(R.drawable.gym).setAlpha(0).setMarkDesc("Steroid"));
        icons.put("grocery_or_supermarket",new CollectionDefaultMapObject().setMarkpath(R.drawable.grocery_or_supermarket).setAlpha(0).setMarkDesc("Esnaf"));
        icons.put("hair_care",new CollectionDefaultMapObject().setMarkpath(R.drawable.hair_care).setAlpha(0).setMarkDesc("Dolgun Saçlar"));
        icons.put("health",new CollectionDefaultMapObject().setMarkpath(R.drawable.health).setAlpha(0).setMarkDesc("......"));
        icons.put("hospital",new CollectionDefaultMapObject().setMarkpath(R.drawable.hospital).setAlpha(0).setMarkDesc("........"));
        icons.put("jewelry_store",new CollectionDefaultMapObject().setMarkpath(R.drawable.jevelry_store).setAlpha(0).setMarkDesc("........."));
        icons.put("lawyer",new CollectionDefaultMapObject().setMarkpath(R.drawable.lawyer).setAlpha(0).setMarkDesc("........"));
        icons.put("library",new CollectionDefaultMapObject().setMarkpath(R.drawable.library).setAlpha(0).setMarkDesc("........"));
        icons.put("local_government_office",new CollectionDefaultMapObject().setMarkpath(R.drawable.local_government_office).setAlpha(0).setMarkDesc("........"));
        icons.put("lodging",new CollectionDefaultMapObject().setMarkpath(R.drawable.lodging).setAlpha(0).setMarkDesc("........"));
        icons.put("meal_delivery",new CollectionDefaultMapObject().setMarkpath(R.drawable.meal_delivery).setAlpha(0).setMarkDesc("........"));
        icons.put("meal_takeaway",new CollectionDefaultMapObject().setMarkpath(R.drawable.meal_takeaway).setAlpha(0).setMarkDesc("........"));
        icons.put("mosque",new CollectionDefaultMapObject().setMarkpath(R.drawable.mosque).setAlpha(0).setMarkDesc("........"));
        icons.put("movie_theater",new CollectionDefaultMapObject().setMarkpath(R.drawable.movie_theater).setAlpha(0).setMarkDesc("........"));
        icons.put("museum",new CollectionDefaultMapObject().setMarkpath(R.drawable.museum).setAlpha(0).setMarkDesc("........"));
        icons.put("night_club",new CollectionDefaultMapObject().setMarkpath(R.drawable.night_club).setAlpha(0).setMarkDesc("........"));
        icons.put("park",new CollectionDefaultMapObject().setMarkpath(R.drawable.park).setAlpha(0).setMarkDesc("........"));
        icons.put("parking",new CollectionDefaultMapObject().setMarkpath(R.drawable.parking).setAlpha(0).setMarkDesc("........"));
        icons.put("pet_store",new CollectionDefaultMapObject().setMarkpath(R.drawable.pet_store).setAlpha(0).setMarkDesc("........"));
        icons.put("pharmacy",new CollectionDefaultMapObject().setMarkpath(R.drawable.pharmacy).setAlpha(0).setMarkDesc("........"));
        icons.put("post_office",new CollectionDefaultMapObject().setMarkpath(R.drawable.post_office).setAlpha(0).setMarkDesc("........"));
        icons.put("real_estate_agency",new CollectionDefaultMapObject().setMarkpath(R.drawable.real_estate_agency).setAlpha(0).setMarkDesc("........"));
        icons.put("restaurant",new CollectionDefaultMapObject().setMarkpath(R.drawable.restaurant).setAlpha(0).setMarkDesc("........"));
        icons.put("school",new CollectionDefaultMapObject().setMarkpath(R.drawable.school).setAlpha(0).setMarkDesc("........"));
        icons.put("shopping_mall",new CollectionDefaultMapObject().setMarkpath(R.drawable.shopping_mall).setAlpha(0).setMarkDesc("........"));
        icons.put("spa",new CollectionDefaultMapObject().setMarkpath(R.drawable.spa).setAlpha(0).setMarkDesc("........"));
        icons.put("stadium",new CollectionDefaultMapObject().setMarkpath(R.drawable.stadium).setAlpha(0).setMarkDesc("........"));
        icons.put("store",new CollectionDefaultMapObject().setMarkpath(R.drawable.store).setAlpha(0).setMarkDesc("........"));
        icons.put("subway_station",new CollectionDefaultMapObject().setMarkpath(R.drawable.train_station).setAlpha(0).setMarkDesc("........"));
        icons.put("taxi_stand",new CollectionDefaultMapObject().setMarkpath(R.drawable.taxi_stand).setAlpha(0).setMarkDesc("........"));
        icons.put("train_station",new CollectionDefaultMapObject().setMarkpath(R.drawable.train_station).setAlpha(0).setMarkDesc("........"));
        icons.put("travel_agency",new CollectionDefaultMapObject().setMarkpath(R.drawable.travel_agency).setAlpha(0).setMarkDesc("........"));
        icons.put("university",new CollectionDefaultMapObject().setMarkpath(R.drawable.university).setAlpha(0).setMarkDesc("........"));
        icons.put("zoo",new CollectionDefaultMapObject().setMarkpath(R.drawable.zoo).setAlpha(0).setMarkDesc("........"));
        icons.put("empty",new CollectionDefaultMapObject().setMarkpath(R.drawable.empty_mark).setAlpha(0).setMarkDesc("iz"));
        return icons;
    }
}
