package com.persistence;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * @author ania.pawelczyk
 * @since 16.11.2018.
 */
@Entity
public class UserOnWebapp {
  @Id
  @GeneratedValue
  private Long id;

  private String sessionId;

  private String currentUrl;

  @UpdateTimestamp
  private LocalDateTime timestamp;

  @ManyToOne
  private Webapp webapp;

  private int clickCount;

  public UserOnWebapp() {}

  public UserOnWebapp(Webapp webapp, String sessionId, String currentUrl) {
    this.webapp = webapp;
    this.sessionId = sessionId;
    this.currentUrl = currentUrl;
  }

  public int getClickCount() {
    return clickCount;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setClickCount(int clickCount) {
    this.clickCount = clickCount;
  }

  public void setCurrentUrl(String currentUrl) {
    this.currentUrl = currentUrl;
  }
}
