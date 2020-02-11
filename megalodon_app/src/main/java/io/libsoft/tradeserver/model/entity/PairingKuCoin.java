package io.libsoft.tradeserver.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;


@Entity
@Component
@Data
@Table(
    indexes = @Index(columnList = "date_created"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"symbol"})}
)
public class PairingKuCoin {

  @Id
  private String symbol;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_created", updatable = false)
  private Date date;

  private long quoteMaxSize;
  private boolean enableTrading;
  private BigDecimal priceIncrement;
  private String feeCurrency;
  private BigDecimal baseMaxSize;
  private String baseCurrency;
  private String quoteCurrency;
  private String market;
  private BigDecimal quoteIncrement;
  private BigDecimal baseMinSize;
  private BigDecimal quoteMinSize;
  private String name;
  private BigDecimal baseIncrement;
  private boolean isMarginEnabled;

}
