package com.kerse.markproject.common;

import com.kerse.markproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kerse on 30.03.2017.
 */

public abstract class Config {

    public static final String ROOT_URL = "http://30.30.30.11:8081";
    public static final String SERVER_URL = "http://30.30.30.11";

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

    public static final Map<String, Integer> getMarkIcons() {
        Map<String, Integer> icons = new HashMap<>();
        icons.put("accounting", R.drawable.acconting);
        icons.put("airport", R.drawable.airport);
        icons.put("amusement_park", R.drawable.amusement_park);
        icons.put("aquarium", R.drawable.aquarium);
        icons.put("art_gallery", R.drawable.art_gallery);
        icons.put("atm", R.drawable.bank);
        icons.put("bakery", R.drawable.bakery);
        icons.put("bar", R.drawable.bar);
        icons.put("beauty_salon", R.drawable.beauty_salon);
        icons.put("book_store", R.drawable.book_store);
        icons.put("bus_station", R.drawable.bus_station);
        icons.put("cafe", R.drawable.cafe);
        icons.put("car_dealer", R.drawable.car_dealer);
        icons.put("car_rental", R.drawable.car_dealer);
        icons.put("car_repair", R.drawable.car_repair);
        icons.put("church", R.drawable.church);
        icons.put("city_hall", R.drawable.city_hall);
        icons.put("clothing_store", R.drawable.clothing_store);
        icons.put("dentist", R.drawable.acconting);
        icons.put("doctor", R.drawable.doctor);
        icons.put("electronics_store", R.drawable.electronics_store);
        icons.put("embassy", R.drawable.embassy);
        icons.put("finance", R.drawable.bank);
        icons.put("fire_station", R.drawable.fire_station);
        icons.put("florist", R.drawable.florist);
        icons.put("food", R.drawable.food);
        icons.put("furniture_store", R.drawable.store);
        icons.put("gas_station", R.drawable.gas_station);
        icons.put("gym", R.drawable.gym);
        icons.put("grocery_or_supermarket", R.drawable.grocery_or_supermarket);
        icons.put("hair_care", R.drawable.hair_care);
        icons.put("health", R.drawable.hospital);
        icons.put("home_goods_store", R.drawable.store);
        icons.put("hospital", R.drawable.hospital);
        icons.put("jewelry_store", R.drawable.jevelry_store);
        icons.put("lawyer", R.drawable.lawyer);
        icons.put("library", R.drawable.library);
        icons.put("local_government_office", R.drawable.local_government_office);
        icons.put("lodging", R.drawable.lodging);
        icons.put("meal_delivery", R.drawable.meal_delivery);
        icons.put("meal_takeaway", R.drawable.meal_takeaway);
        icons.put("mosque", R.drawable.mosque);
        icons.put("movie_theater", R.drawable.movie_theater);
        icons.put("museum", R.drawable.museum);
        icons.put("night_club", R.drawable.night_club);
        icons.put("park", R.drawable.park);
        icons.put("parking", R.drawable.parking);
        icons.put("pet_store", R.drawable.pet_store);
        icons.put("pharmacy", R.drawable.pharmacy);
        icons.put("post_office", R.drawable.post_office);
        icons.put("real_estate_agency", R.drawable.real_estate_agency);
        icons.put("restaurant", R.drawable.restaurant);
        icons.put("school", R.drawable.school);
        icons.put("shopping_mall", R.drawable.shopping_mall);
        icons.put("spa", R.drawable.spa);
        icons.put("stadium", R.drawable.stadium);
        icons.put("store", R.drawable.store);
        icons.put("subway_station", R.drawable.train_station);
        icons.put("taxi_stand", R.drawable.taxi_stand);
        icons.put("train_station", R.drawable.train_station);
        icons.put("travel_agency", R.drawable.travel_agency);
        icons.put("university", R.drawable.school);
        icons.put("zoo", R.drawable.zoo);
        icons.put("empty", R.drawable.empty_mark);
        return icons;
    }
}
