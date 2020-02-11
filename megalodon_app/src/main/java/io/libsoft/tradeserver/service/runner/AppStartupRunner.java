package io.libsoft.tradeserver.service.runner;


import io.libsoft.tradeserver.service.populater.TableBuilderKuCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {



  private TableBuilderKuCoin tb;

  @Autowired
  public AppStartupRunner(TableBuilderKuCoin tb) {
    this.tb = tb;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
//    tb.populateCoinPairings();
//    new Thread(()->{
//      while (true){
//        tb.fullTickerQuery();
//        try {
//          Thread.sleep(2000);
//        } catch (InterruptedException ignored) { }
//      }
//
//
//    }).start();

  }
}
