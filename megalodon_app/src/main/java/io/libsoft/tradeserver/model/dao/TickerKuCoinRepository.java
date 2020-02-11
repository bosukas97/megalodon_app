package io.libsoft.tradeserver.model.dao;

import io.libsoft.tradeserver.model.entity.SingleTickerKuCoin;
import org.springframework.data.repository.CrudRepository;

public interface TickerKuCoinRepository extends CrudRepository<SingleTickerKuCoin, Long> {

}
