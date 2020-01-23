package io.libsoft.tradeserver.model.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Table
@Entity
@IdClass(FullTickerId.class)
public class FullTickerKuCoin {

  @Id
  private long time;
  @Id
  private String symbol;

  private BigDecimal high;
  private BigDecimal vol;
  private BigDecimal last;
  private BigDecimal low;
  private BigDecimal buy;
  private BigDecimal sell;
  private BigDecimal changePrice;
  private String symbolName;
  private BigDecimal averagePrice;
  private float changeRate;
  private float volValue;
}
