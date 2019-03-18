package com.walmart.hack.discount.deal;


import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.PostPersist;

import org.springframework.stereotype.Component;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.walmart.hack.discount.model.Deal;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DealListener {

  @PostPersist
  public void dealPersisted(Deal deal) throws FirebaseMessagingException {
    BigDecimal markdownPercent = deal.getOldPrice().subtract(deal.getNewPrice())
        .multiply(BigDecimal.valueOf(100)).divide(deal.getOldPrice(), 0, RoundingMode.HALF_UP);

    //TODO query Item table for item details
    StringBuilder body = new StringBuilder(Long.toString(deal.getItem()));
    body.append(" has been discounted to $");
    body.append(deal.getNewPrice().toPlainString());
    body.append(" (");
    body.append(markdownPercent);
    body.append("% off!)");

    Message message = Message.builder()
        .setNotification(new Notification(
            "My Walmart Deals"
            ,body.toString()))

        .setAndroidConfig(AndroidConfig.builder()
            .setTtl(3600 * 1000 * 60)
            .setNotification(AndroidNotification.builder()
                .setIcon("test")
                .setColor("#f45342")
                .build())
            .build())

        .putData("item", deal.getItem().toString())
        .putData("store", deal.getStore().toString())
        .putData("expiration", deal.getExpiration().toString())
        .putData("created", deal.getCreated().toString())
        .putData("newPrice", deal.getNewPrice().toString())
        .putData("oldPrice", deal.getOldPrice().toString())
        .putData("markdownPercent", markdownPercent.toPlainString())

        .setToken("eG5khVZyWuc:APA91bHRkOqQJZ9PGfzAnQWIWLg3D6gmPsNFaTMrcevArsBI2LwaQyEp7fXIPwdrXfGN-yaE3Ecrcc5bXHUZvAxAG_-hbP9E1L5rAWV7mEGL0OeYDcLcGq_TF6u2C1af8jB4YCN7Pd6d")
        .build();

    String response = FirebaseMessaging.getInstance().send(message);

    log.info("PostPersist: " + response);
  }

}
