package yuol.secondary.market.toake.Utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
public class NetworkUtils {

    //该类使用OkHttp向服务器发出请求，可以获得相应的接口
    public static void request(String url,okhttp3.Callback callback){ 
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
