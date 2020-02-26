package ru.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import ru.test.rest.domain.Client;
import ru.test.rest.domain.ClientRepository;
import ru.test.rest.domain.ClientValidator;
import ru.test.rest.util.Password;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Configuration
public class ApplicationConfiguration {

    private static final SecureRandom random = new SecureRandom();

    private static final String[] FIRSTNAMES = new String[] {
            "Андрей",
            "Борис",
            "Виктор",
            "Геннадий",
            "Денис",
            "Евгений",
            "Жорж",
            "Зиновий",
            "Игорь",
            "Кирилл",
            "Леонид",
            "Максим",
            "Николай",
            "Олег",
            "Павел",
            "Роман"
    };

    private static final String[] SECONDNAMES = new String[] {
            "Яковлев",
            "Южный",
            "Эйдельман",
            "Щеглов",
            "Шаров",
            "Чистый",
            "Цапкин",
            "Харитонов",
            "Фомин",
            "Уткин",
            "Тополев",
            "Соловьев",
            "Репин",
            "Петров",
            "Окунев",
            "Никитин"
    };

    @Autowired
    private ClientRepository clientRepository;

    public void load() {
        int amount = 10;

        IntStream.range(0, amount)
                .forEach(i -> {
                    Client client = generateClient();
                    clientRepository.save(client);
                });
    }

    private Client generateClient() {
        Client client = new Client();
        client.setFirstName(FIRSTNAMES[random.nextInt(FIRSTNAMES.length)]);
        client.setSecondName(SECONDNAMES[random.nextInt(SECONDNAMES.length)]);
        client.setLogin("client" + random.nextInt(99999));
        client.setBirthday(generateRandomDate());

        String salt = Password.generateSalt(512).get();
        client.setSalt(salt);
        client.setPassword(Password.hashPassword("password", salt).get());

        client.setAbout("");
        client.setAddress("");

        return client;
    }

    private static LocalDate generateRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2005, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    @Bean
    CommandLineRunner commandLineRunner(ApplicationConfiguration dataLoader) {
        return (o) -> dataLoader.load();
    }

    @Configuration
    public static class CustomRepositoryRestMvcConfiguration implements RepositoryRestConfigurer {
        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            config.exposeIdsFor(Client.class);
        }

        @Override
        public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
            validatingListener.addValidator("beforeCreate", new ClientValidator());
        }
    }
}
