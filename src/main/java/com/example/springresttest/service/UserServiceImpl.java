package com.example.springresttest.service;

import com.example.springresttest.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;




public class UserServiceImpl{
    private static final RestTemplate restTemplate = new RestTemplate();
    private static String sessionId;



    public static void getUsers(String url) {
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, null, String.class);

        // Сохранить session ID
        HttpHeaders headers = response.getHeaders();
        sessionId = headers.getFirst(HttpHeaders.SET_COOKIE);
        System.out.println("Session ID: " + sessionId);
    }


    public static String addUser(User user, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", sessionId);

        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, request, String.class);

        System.out.println("Add User Response: " + response.getBody());
        return response.getBody();
    }


    public static String updateUser(User user, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", sessionId);

        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.PUT, request, String.class);

        System.out.println("Update User Response: " + response.getBody());
        return response.getBody();
    }


    public static String deleteUser(Long id, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url + "/" + id, HttpMethod.DELETE, request, String.class);

        System.out.println("Delete User Response: " + response.getBody());
        return response.getBody();
    }

}
