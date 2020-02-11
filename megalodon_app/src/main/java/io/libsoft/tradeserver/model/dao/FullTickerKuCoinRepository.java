package io.libsoft.tradeserver.model.dao;

import io.libsoft.tradeserver.model.entity.FullTickerId;
import io.libsoft.tradeserver.model.entity.FullTickerKuCoin;
import org.springframework.data.repository.CrudRepository;

public interface FullTickerKuCoinRepository extends CrudRepository<FullTickerKuCoin, FullTickerId> {

}
