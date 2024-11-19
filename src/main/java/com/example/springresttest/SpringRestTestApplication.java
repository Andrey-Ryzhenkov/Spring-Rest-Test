package com.example.springresttest;

import com.example.springresttest.model.User;
import org.springframework.web.client.RestTemplate;

import static com.example.springresttest.service.UserServiceImpl.*;


public class SpringRestTestApplication{

    private static final String BASE_URL = "http://94.198.50.185:7081/api/users";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static String sessionId;

    public static void main(String[] args) {
        // Получить всех пользователей
        getUsers(BASE_URL);

        // Добавить пользователя
        User newUser = new User(3L, "James", "Brown", (byte) 25);
        String part1 = addUser(newUser,BASE_URL);

        // Изменить пользователя
        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        String part2 = updateUser(newUser,BASE_URL);

        // Удалить пользователя
        String part3 = deleteUser(3L,BASE_URL);

        // Итоговый код
        System.out.println("Result Code: " + part1 + part2 + part3);
    }
}

