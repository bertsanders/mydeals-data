package com.walmart.hack.discount.deal;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Message.Builder;
import com.google.firebase.messaging.Notification;
import com.walmart.hack.discount.model.Deal;
import com.walmart.hack.discount.model.DeviceSubscription;
import com.walmart.hack.discount.subscription.SubscriptionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DealService {

  @NonNull
  private DealRepository dealRepository;
  @NonNull
  private SubscriptionService subscriptionService;

  public Page<Deal> getAllDeals(Pageable pageable) {
    return dealRepository.findAll(pageable);
  }

  public Deal createDeal(@RequestBody Deal deal, @RequestParam("timeToLive") long timeToLiveSeconds) {
    deal.setExpiration(Instant.now().plusSeconds(timeToLiveSeconds));
    deal = dealRepository.save(deal);
    sendNotifications(deal);
    return deal;
  }

  public void sendNotifications(Deal deal) {
    BigDecimal markdownPercent = deal.getOldPrice().subtract(deal.getNewPrice())
        .multiply(BigDecimal.valueOf(100)).divide(deal.getOldPrice(), 0, RoundingMode.HALF_UP);

    //TODO query Item table for item details
    StringBuilder body = new StringBuilder(Long.toString(deal.getItem()));
    body.append(" has been discounted to $");
    body.append(deal.getNewPrice().toPlainString());
    body.append(" (");
    body.append(markdownPercent);
    body.append("% off!)");

    List<DeviceSubscription> matchingSubscriptions = subscriptionService.findMatchingSubscriptions(deal.getStore(), deal.getDepartment(), deal.getCategory());
    matchingSubscriptions.stream()
        .map(m -> buildMessage(body, m.getToken(), deal, markdownPercent))
        .forEach(m -> {
          try {
            String responswe = FirebaseMessaging.getInstance().send(m);
            log.info("Sent message with response " + responswe);
          } catch (FirebaseMessagingException e) {
            log.error("Failed to send message: " + e.toString(), e);
          }
        });
  }

  private Message buildMessage(StringBuilder body, String deviceToken, Deal deal, BigDecimal markdownPercent) {
    Builder builder = Message.builder()
        .setNotification(new Notification(
            "My Walmart Deals"
            , body.toString()));
    builder = buildAndroidMessage(builder);
    builder = putData(builder, deal, markdownPercent);

    Message message = builder.setToken(deviceToken).build();
//        .setToken("eG5khVZyWuc:APA91bHRkOqQJZ9PGfzAnQWIWLg3D6gmPsNFaTMrcevArsBI2LwaQyEp7fXIPwdrXfGN-yaE3Ecrcc5bXHUZvAxAG_-hbP9E1L5rAWV7mEGL0OeYDcLcGq_TF6u2C1af8jB4YCN7Pd6d")

    return message;
  }

  private Message.Builder buildAndroidMessage(Message.Builder builder) {
    return builder.setAndroidConfig(AndroidConfig.builder()
        .setTtl(3600 * 1000 * 60)
        .setNotification(AndroidNotification.builder()
            .setIcon("test")
            .setColor("#f45342")
            .build())
        .build());
  }

  private Message.Builder putData(Message.Builder builder, Deal deal, BigDecimal markdownPercent) {
    return builder
        .putData("item", deal.getItem().toString())
        .putData("store", deal.getStore().toString())
        .putData("expiration", deal.getExpiration().toString())
        .putData("created", deal.getCreated().toString())
        .putData("newPrice", deal.getNewPrice().toString())
        .putData("oldPrice", deal.getOldPrice().toString())
        .putData("markdownPercent", markdownPercent.toPlainString());

  }

}
