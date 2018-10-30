package yuol.secondary.market.toake.Utils;

import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtils {

    //该类使用OkHttp向服务器发出请求，可以获得相应的数据
    public static void request(String url,okhttp3.Callback callback){ 
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //更新信息专门用的xml解析
    public static List<String> parseXML(String content){
        List<String> info = new ArrayList<>();
        try {
            XmlPullParserFactory factor = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factor.newPullParser();
            //setInput要传入一个流，但是如果要传入普通流就要加入对应的编码格式
            //该方法还可以传入一个Reader流，不清楚这个流，StringReader(content)可以将字符串变成流的格式
            pullParser.setInput(new StringReader(content));
            int eventType = pullParser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                String tagName = pullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT :
                        //表示此时读取的状态在流的首端
                        break;

                    case XmlPullParser.START_TAG:
                        //表示到达标签的开始
                        if("version".equals(tagName)){
                            //当标签是version的时候将pullparse指向该标签的属性
                            eventType = pullParser.next();
                            //获取内容
                            info.add(pullParser.getText());
                        }
                        if("name".equals(tagName)){
                            eventType = pullParser.next();
                            info.add(pullParser.getText());
                        }
                        if("url".equals(tagName)){
                            eventType = pullParser.next();
                            info.add(pullParser.getText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        //表示到达标签尾部
                        break;
                }
                eventType = pullParser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }


    //提前加载图片Json数据,并保存
    public static void loadJsonImageUrl() {
        //向服务器请求数据JSON
        NetworkUtils.request("http://192.168.137.1/taoke/ImageUrl", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ActivityCollector.currentActivity(), "图片更新失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                FileUtils.saveByPeference(ActivityCollector.currentActivity(), "Json_imageUrl", response.body().string());
            }
        });
    }
}
