package faker;

import com.github.javafaker.Faker;
import com.github.javafaker.ProgrammingLanguage;

import java.util.Locale;
import java.util.Random;

public class FakerTest {
    public static void main(String[] args) {
        Faker faker=new Faker();
//        Faker faker=new Faker(new Locale("de"));
//        Faker faker=new Faker(new Random(2));


        System.out.println(faker.address().state());
        System.out.println(faker.address().state());



    }
}
