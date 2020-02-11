package io.libsoft.tradeserver.controller;


import io.libsoft.tradeserver.model.dao.AlertRepository;
import io.libsoft.tradeserver.model.entity.Alert;
import io.libsoft.tradeserver.service.network.TelegramService;
import io.libsoft.tradeserver.service.pojo.Message;
import io.libsoft.tradeserver.utils.Utils;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/alerts")
public class AlertController {


  private final AlertRepository alertRepository;
//  public Alert saved_btc = new Alert();
//  public Alert saved_eth = new Alert();
//  public Alert saved_ltc = new Alert();
//  public Alert saved_xrp = new Alert();
//  public Alert saved = new Alert();
    public BigDecimal default_value = new BigDecimal("0");

  @Autowired
  public AlertController(AlertRepository alertRepository) {
    this.alertRepository = alertRepository;

  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Alert> addAlert(@RequestBody Alert alert) {
    //Once the alert is received find the right saved message for the alerts

//    //Once the alert is received change the null values to the previous values]
//    if(alert.getAsset().equals("BTCUSDT")){saved = saved_btc;}
//    if(alert.getAsset().equals("ETHUSDT")){saved = saved_eth;}
//    if(alert.getAsset().equals("LTCUSDT")){saved = saved_ltc;}
//    if(alert.getAsset().equals("XRPUSDT")){saved = saved_xrp;}
//
//
//    if(saved!=null){ System.out.println(saved);}
//    if(alert.getFibonacci()==null){alert.setFibonacci(saved.getFibonacci());}
//    if(alert.getLongerSetup()==null){alert.setLongerSetup(saved.getLongerSetup());}
//    if(alert.getLongerTimer()==null){alert.setLongerTimer(saved.getLongerTimer());}
//    if(alert.getShorterTimer()==null){alert.setShorterTimer(saved.getShorterTimer());}
//
//    //
//    save_assets_individually(alert);
//    saved = alert;
//    System.out.println(alert);
//    alertRepository.save(alert);

    if ((alert.getShorterBuySetup() != null || alert.getShorterSellSetup() != null) && (alert.getShorterBuySetup() < -321 || alert.getShorterSellSetup() > 321)) {
      alert.setFibonacci(alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getFibonacci());
      alert.setLongerTimer(alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getLongerTimer());
      alert.setShorterTimer(alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getShorterTimer());
      alert.setLongerSetup(alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getLongerSetup());
      Message message = Message.messageBuilder(alert);
      alertRepository.save(alert);
      TelegramService.getInstance().sendMessage(Utils.getTelegramApiKey(), message)
              .subscribeOn(Schedulers.io())
              .subscribe((responseBody, throwable) -> {
                System.out.println(responseBody);
                System.out.println(throwable);
              });
    } else {
      System.out.println(alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getLongerSetup());
      if (alert.getLongerSetup()==null && alert.getFibonacci()==null) {
        if (alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getLongerSetup() != null) { //If Longer is equal to null then Setups will be null as well
          alert.setLongerSetup(alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getLongerSetup());
          alert.setFibonacci(alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getFibonacci());
        } else {
          alert.setLongerSetup(default_value);
          alert.setFibonacci(default_value);
        }
      }
      if (alert.getShorterTimer()==null) {
        if (alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getShorterTimer() != null) { //If Longer is equal to null then Setups will be null as well
          alert.setShorterTimer(alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getShorterTimer());
        } else {
          alert.setShorterTimer(default_value);
        }
      }
      if (alert.getLongerTimer()==null) {
        if (alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getLongerTimer() != null) { //If Longer is equal to null then Setups will be null as well
          alert.setLongerTimer(alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()).getLongerTimer());
            }
        else {
          alert.setLongerTimer(default_value);
            }
      }
      alertRepository.save(alert);
      }
      return ResponseEntity.accepted().body(alert);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alert> connectionTest (@RequestBody Alert alert){
      System.out.println(alert.getAsset());
      // System.out.println(ResponseEntity.ok().build());
      return ResponseEntity.accepted().body(alertRepository.findTopByAssetOrderByIdDesc(alert.getAsset()));
    }
  }

