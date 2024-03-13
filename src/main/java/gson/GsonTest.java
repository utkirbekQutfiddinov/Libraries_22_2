package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonTest {
    public static void main(String[] args) throws Exception {

       String json=getJsonFromApi();

        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy")
                .create();

        Type type = TypeToken.getParameterized(ArrayList.class, Currency.class).getType();

        List<Currency> currencies = gson.fromJson(json, type);

        Map<String, Double> rates=new HashMap<>();

        for (Currency cur : currencies) {
            rates.put(cur.getCcy(),cur.getRate());
        }


    }

    private static String getJsonFromApi() {
        try {
            String url="https://cbu.uz/uz/arkhiv-kursov-valyut/json/";

            HttpClient client= HttpClient.newBuilder().build();

            HttpRequest request= HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void withoutTypeToken() throws IOException {
        String jsontext = Files.readString(Path.of("users.json"));

        Gson gson = new Gson();

        CurrencyObject object = gson.fromJson(jsontext, CurrencyObject.class);


        for (Currency cur : object.getCurrencies()) {
            System.out.println(cur);
        }
    }

    private static void fromJsonObject() {
        String oneCurr = "{\n" +
                "\"id\": 69,\n" +
                "\"Code\": \"840\",\n" +
                "\"Ccy\": \"USD\",\n" +
                "\"CcyNm_RU\": \"Доллар США\",\n" +
                "\"CcyNm_UZ\": \"AQSH dollari\",\n" +
                "\"CcyNm_UZC\": \"АҚШ доллари\",\n" +
                "\"CcyNm_EN\": \"US Dollar\",\n" +
                "\"Nominal\": \"1\",\n" +
                "\"Rate\": \"12496.80\",\n" +
                "\"Diff\": \"11.86\",\n" +
                "\"Date\": \"01.03.2024\"\n" +
                "}";

        Gson gson = new Gson();
        Currency currency = gson.fromJson(oneCurr, Currency.class);

        System.out.println(currency);
    }

    private static void fromJsonList() throws IOException {
        String jsontext = Files.readString(Path.of("users.json"));

        Gson gson = new Gson();

        Type type = TypeToken.getParameterized(ArrayList.class, Currency.class).getType();

        List<Currency> currencies = gson.fromJson(jsontext, type);


        for (Currency cur : currencies) {
            System.out.println(cur);
        }
    }

    private static void toJson() {
        User user = new User();
        user.setAge(10);
        user.setFullname("Eshmatov Toshmat");
        user.setIsActive(false);


        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);

        Gson gson = new Gson();
        String json = gson.toJson(userList);

        System.out.println(json);
    }
}
