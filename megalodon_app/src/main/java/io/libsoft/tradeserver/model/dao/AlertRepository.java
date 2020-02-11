package io.libsoft.tradeserver.model.dao;

import io.libsoft.tradeserver.model.entity.Alert;
import org.springframework.data.repository.CrudRepository;

public interface AlertRepository extends CrudRepository<Alert, Long> {

    Alert  findTopByAssetOrderByIdDesc(String asset);


}
