package io.libsoft.tradeserver;

import io.reactivex.disposables.CompositeDisposable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TradeServerApplication {



//  @Value("${api-keys.properties}")
//  String telegramApiKey;

  public static void main(String[] args) {
    SpringApplication.run(TradeServerApplication.class, args);
  }


  @Bean
  public CompositeDisposable getCompositeDisposable() {
    return new CompositeDisposable();
  }

}
