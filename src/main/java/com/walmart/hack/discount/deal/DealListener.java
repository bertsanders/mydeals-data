package com.walmart.hack.discount.deal;


import javax.persistence.PostPersist;

import org.springframework.stereotype.Component;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.walmart.hack.discount.model.Deal;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DealListener {

  @NonNull
  private FirebaseOptions firebaseOptions;

  @PostPersist
  public void dealPersisted(Deal deal) throws FirebaseMessagingException {
    Message message = Message.builder()
        .putData("test", "123")
        .setToken("eG5khVZyWuc:APA91bHRkOqQJZ9PGfzAnQWIWLg3D6gmPsNFaTMrcevArsBI2LwaQyEp7fXIPwdrXfGN-yaE3Ecrcc5bXHUZvAxAG_-hbP9E1L5rAWV7mEGL0OeYDcLcGq_TF6u2C1af8jB4YCN7Pd6d")
        .build();

    String response = FirebaseMessaging.getInstance().send(message);

    log.info("PostPersist: " + response);
  }

}
