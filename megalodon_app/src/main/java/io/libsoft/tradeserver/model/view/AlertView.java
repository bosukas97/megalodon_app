package io.libsoft.tradeserver.model.view;

import java.math.BigDecimal;
import java.util.Date;

public interface AlertView {

  Date getCreated();

  String getAsset();

  BigDecimal getFibonacci();

  BigDecimal getPrice();

  Integer getShorterBuySetup();

  Integer getShorterSellSetup();
}
