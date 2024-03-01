package gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GsonTest {
    public static void main(String[] args) throws Exception {


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
