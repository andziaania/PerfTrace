package com.persistence;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author ania.pawelczyk
 * @since 16.11.2018.
 */
@Entity
public class UserOnWebapp {
  @Id
  @GeneratedValue
  private Long id;

  @UpdateTimestamp
  private Date timestamp;

  @ManyToOne
  private Webapp webapp;

  private int clickNumber;

  public UserOnWebapp() {
  }

  public UserOnWebapp(Webapp webapp) {
    this.webapp = webapp;
  }

  public int getClickNumber() {
    return clickNumber;
  }

  public void setClickNumber(int clickNumber) {
    this.clickNumber = clickNumber;
  }
}
