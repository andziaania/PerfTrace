package com.persistence;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author ania.pawelczyk
 * @since 13.11.2018.
 */

@Entity
public class Webapp {
  @Id
  @GeneratedValue
  private Long id;

  private String url;

  @UpdateTimestamp
  private Date timestamp;

  public Webapp(){}

  public Webapp(String url) {
    this.url = url;
  }

  public Long getId() {
    return id;
  }

  @Override
  public boolean equals(Object other) {
    if (!this.getClass().equals(other.getClass())) return false;
    Webapp otherWebapp = (Webapp) other;
    return this.url.equals(otherWebapp.url)
            && this.id == otherWebapp.id;
  }

  @ Override
  public String toString() {
    return String.format("%s %d", url, id);
  }
}
