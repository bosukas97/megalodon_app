package io.libsoft.tradeserver.model.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(
    indexes = @Index(columnList = "time")
)
public class SingleTickerKuCoin {

  @Id
  private long sequence;
  private long time;
  private String pair;
  private BigDecimal bestAsk;
  private BigDecimal size;
  private BigDecimal price;
  private BigDecimal bestBidSize;
  private BigDecimal bestBid;
  private BigDecimal bestAskSize;

}
