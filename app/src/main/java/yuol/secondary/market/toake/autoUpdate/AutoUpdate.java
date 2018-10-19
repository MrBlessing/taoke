package yuol.secondary.market.toake.autoUpdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import yuol.secondary.market.toake.Utils.LogUtil;
import yuol.secondary.market.toake.Utils.NetworkUtils;

public class AutoUpdate {
    private static final String TAG="AutoUpdate";
    public static boolean state = false;//用于外界获取更新状态

    //发送请求，获取包含版本信息的xml文件
    public static void Update(final Context context, final LocalBroadcastManager LBmanager){

        NetworkUtils.request("http://192.168.137.1/taoke/update.xml", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.d(TAG,"网络链接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                //解析更新文档
                List<String> versionInfo  = parseXML(res);
                //获取当前应用版本
                String versionNow=null;
                PackageManager manager;
                PackageInfo packageInfo;
                try {
                    manager = context.getPackageManager();
                    packageInfo = manager.getPackageInfo(context.getPackageName(),0);
                    versionNow = packageInfo.versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                //判断是否有版本更新
               if(!versionInfo.get(0).equals(versionNow)){
                   Intent intent = new Intent("yuol.secondary.market.toake.update");
                   state = true;
                   intent.putExtra("state",true);
                   intent.putExtra("url",versionInfo.get(2));
                   LBmanager.sendBroadcast(intent);
               }else{
                   Intent intent = new Intent("yuol.secondary.market.toake.update");
                   intent.putExtra("state",false);
                   LBmanager.sendBroadcast(intent);
               }
            }
        });


    }

    private static List<String> parseXML(String content){
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
}
