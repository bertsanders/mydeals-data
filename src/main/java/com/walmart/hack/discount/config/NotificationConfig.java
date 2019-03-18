package com.walmart.hack.discount.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class NotificationConfig {

//  @Bean
//  @Scope("prototype")
//  public String firebaseAccessToken() throws IOException {
//    ByteArrayInputStream inputStream = new ByteArrayInputStream("test".getBytes());
//    GoogleCredential googleCredential = GoogleCredential.fromStream(inputStream);
//    googleCredential.refreshToken();
//    return googleCredential.getAccessToken();
//  }

  @Bean
  public FirebaseOptions options(
      @Value("${GOOGLE_APPLICATION_CREDENTIALS}") String credentials,
      @Value("${messaging.firebase.uri}") String databaseUri) throws IOException {

    return new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(credentials.getBytes())))
        .setDatabaseUrl(databaseUri)
        .build();
  }

  @Bean
  public FirebaseApp app(FirebaseOptions firebaseOptions) {
    return FirebaseApp.initializeApp(firebaseOptions);
  }
}
