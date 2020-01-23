package io.libsoft.tradeserver.model.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class FullTickerId implements Serializable {


  private static final long serialVersionUID = -8443495765204959020L;

  private String symbol;
  private long time;

}
