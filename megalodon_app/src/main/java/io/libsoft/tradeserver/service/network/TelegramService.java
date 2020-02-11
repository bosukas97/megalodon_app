package io.libsoft.tradeserver.service.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.libsoft.tradeserver.service.pojo.Message;
import io.libsoft.tradeserver.utils.Utils;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TelegramService {



    @POST("/bot{key}/sendMessage")
    Single<ResponseBody> sendMessage(@Path("key") String apiKey, @Body Message message);


    static TelegramService getInstance() {

        return TelegramService.InstanceHolder.INSTANCE;
    }


    class InstanceHolder {


        private static final TelegramService INSTANCE;

        static {


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            Gson gson = new GsonBuilder()
//          .excludeFieldsWithoutExposeAnnotation()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Utils.fetchProperties().get("telegram-base-url").toString())
                    .client(client)
                    .build();
            INSTANCE = retrofit.create(TelegramService.class);
        }
    }


}
