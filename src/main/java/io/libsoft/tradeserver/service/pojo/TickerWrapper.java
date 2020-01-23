package io.libsoft.tradeserver.service.pojo;

import io.libsoft.tradeserver.model.entity.FullTickerKuCoin;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TickerWrapper {
  List<FullTickerKuCoin> ticker;
  long time;
}
