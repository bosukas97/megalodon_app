package io.libsoft.tradeserver.service.pojo;


import io.libsoft.tradeserver.model.entity.PairingKuCoin;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ResponseKuCoin<T> {

  private String code;
  private T data;

}
