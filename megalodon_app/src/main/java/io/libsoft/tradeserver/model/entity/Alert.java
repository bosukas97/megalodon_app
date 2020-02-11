package io.libsoft.tradeserver.model.entity;


import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.libsoft.tradeserver.model.view.AlertView;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;


@Entity
@Component
@Data
@Table(indexes = @Index(columnList = "alert_id"))
@JsonSerialize(as = AlertView.class)
public class Alert implements AlertView {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "alert_id", updatable = false, nullable = false)
  private Long id;


  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;


  @JsonSetter("Fib")
  private BigDecimal fibonacci;

  @JsonSetter("Asset")
  private String asset;

  @JsonSetter("Price")
  private BigDecimal price;

  @JsonSetter("SetUps")
  private BigDecimal longerSetup;

  @JsonSetter("Buy SetUps")
  private Integer shorterBuySetup;

  @JsonSetter("Sell SetUps")
  private Integer shorterSellSetup;

  //private BigDecimal bollingerBand;

  @JsonSetter("Longer Timer")
  private BigDecimal longerTimer;

  @JsonSetter("Shorter Timer")
  private BigDecimal shorterTimer;
}
