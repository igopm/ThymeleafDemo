package org.example;

import com.github.javafaker.Faker;
import org.example.http.MyHttpHelper;
import org.example.model.Back;
import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String apiUrl = "https://picsum.photos/v2/list?page=2&limit=25";
        MyHttpHelper myHttpHelper = new MyHttpHelper(apiUrl);
        List<Person> persons = new ArrayList<>();
        Faker faker = new Faker();
        Random random = new Random();
        int picListSize = myHttpHelper.getUrlPictures().size();
        int rIndex = random.nextInt(picListSize);

        for (int index = 0; index < picListSize; index++) {
            if (index != rIndex) {
                persons.add(new Person(faker, myHttpHelper.getUrlPictures().get(index)));
            } else {
                persons.add(new Person(faker.name().fullName(), null, null, null, null));
            }
        }
        for (Person person : persons) {
            System.out.println(person);
        }

        HtmlGenerator html = new HtmlGenerator();
        html.getContext().setVariable("persons", persons);
        html.getContext().setVariable("back", new Back("https://picsum.photos/id/924/3000/2250"));
        html.renderedHtml("personlist");
        System.out.println(html.getHtmlPage());
        html.spoolToOutput();
        ///new MailSender().sendMail("REPOSITORY XML TESTS RESULTS", html.getHtmlPage());
    }
}
