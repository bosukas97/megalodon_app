package io.libsoft.tradeserver.service.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.libsoft.tradeserver.model.entity.FullTickerKuCoin;
import io.libsoft.tradeserver.model.entity.PairingKuCoin;
import io.libsoft.tradeserver.model.entity.SingleTickerKuCoin;
import io.libsoft.tradeserver.service.pojo.ResponseKuCoin;
import io.libsoft.tradeserver.service.pojo.TickerWrapper;
import io.libsoft.tradeserver.utils.Utils;
import io.reactivex.Single;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


@Component
public interface RequestsKuCoinService {


  @GET("/api/v1/symbols")
  Single<ResponseKuCoin<List<PairingKuCoin>>> getCoinInfos();


  @GET("/api/v1/market/orderbook/level1")
  Single<ResponseKuCoin<SingleTickerKuCoin>> getTickerCoin(@Query("symbol") String symbol);

  @GET("/api/v1/market/allTickers")
  Single<ResponseKuCoin<TickerWrapper>> getAllTickers();


  static RequestsKuCoinService getInstance() {
    return InstanceHolder.INSTANCE;
  }


  class InstanceHolder {


    private static final RequestsKuCoinService INSTANCE;

    static {


      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(Level.BODY);

      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();

      Gson gson = new GsonBuilder()
//          .excludeFieldsWithoutExposeAnnotation()
          .create();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .baseUrl(Utils.fetchProperties().get("ku-coin-base-url").toString())
//          .client(client)
          .build();
      INSTANCE = retrofit.create(RequestsKuCoinService.class);
    }
  }
}

