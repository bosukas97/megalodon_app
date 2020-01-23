package io.libsoft.tradeserver.service.populater;


import io.libsoft.tradeserver.model.dao.FullTickerKuCoinRepository;
import io.libsoft.tradeserver.model.dao.PairingKuCoinRepository;
import io.libsoft.tradeserver.model.dao.TickerKuCoinRepository;
import io.libsoft.tradeserver.model.entity.FullTickerKuCoin;
import io.libsoft.tradeserver.service.network.RequestsKuCoinService;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableBuilderKuCoin {

  private final PairingKuCoinRepository pairingKuCoinRepository;
  private final TickerKuCoinRepository tickerKuCoinRepository;
  private final FullTickerKuCoinRepository fullTickerKuCoinRepository;
  private final CompositeDisposable pending;


  @Autowired
  public TableBuilderKuCoin(PairingKuCoinRepository pairingKuCoinRepository,
      TickerKuCoinRepository tickerKuCoinRepository,
      FullTickerKuCoinRepository fullTickerKuCoinRepository,
      CompositeDisposable pending) {
    this.pairingKuCoinRepository = pairingKuCoinRepository;
    this.tickerKuCoinRepository = tickerKuCoinRepository;
    this.fullTickerKuCoinRepository = fullTickerKuCoinRepository;
    this.pending = pending;
  }

  public void populateCoinPairings() {
    pending.add(
        RequestsKuCoinService.getInstance()
            .getCoinInfos()
            .subscribeOn(Schedulers.io())
            .subscribe((coinPairingKuCoin) -> {
                pairingKuCoinRepository.saveAll(coinPairingKuCoin.getData());
              }, (throwable) -> {
              System.out.println(throwable.getMessage());
            }));
  }

  public void getTicker(String symbol){

    pending.add(
        RequestsKuCoinService.getInstance()
            .getTickerCoin(symbol)
        .subscribeOn(Schedulers.io())
        .subscribe((tickerKuCoinResponseKuCoin) -> {
          tickerKuCoinResponseKuCoin.getData().setPair(symbol);
          tickerKuCoinRepository.save(tickerKuCoinResponseKuCoin.getData());
        },throwable -> {
          System.out.println(throwable.getMessage());
        })
    );
  }

  public void fullTickerQuery() {
    pending.add(
        RequestsKuCoinService.getInstance()
            .getAllTickers()
            .subscribeOn(Schedulers.io())
            .subscribe((tickerKuCoinResponseKuCoin) -> {
              long time = tickerKuCoinResponseKuCoin.getData().getTime();
              for (FullTickerKuCoin ticker : tickerKuCoinResponseKuCoin.getData().getTicker()) {
                ticker.setTime(time);
                fullTickerKuCoinRepository.save(ticker);
              }
            },throwable -> {
              System.out.println(throwable.getMessage());
            })
    );
  }
}
