package yuol.secondary.market.toake.Utils;

import android.util.Log;

public class LogUtil {
        private static final int VERBOSE = 1;
        private static final int DEBUG = 2;
        private static final int INFO = 3;
        private static final int WARN  = 4;
        private static final int ERROR = 5;
        private static final int RELEASE = 6;
        private static int level = 0;

        public static void v(String TAG , String msg){
            if(VERBOSE >= level){
                Log.v(TAG ,"* "+ msg);
            }
        }
        public static void d(String TAG , String msg){
            if(DEBUG >= level){
                Log.d(TAG ,"* "+  msg);
            }
        }
        public static void i(String TAG , String msg){
            if(INFO >= level){
                Log.i(TAG ,"* "+  msg);
            }
        }
        public static void w(String TAG , String msg){
            if(WARN >= level){
                Log.w(TAG ,"* "+  msg);
            }
        }
        public static void e(String TAG , String msg){
            if(ERROR >= level){
                Log.e(TAG ,"* "+  msg);
            }
        }
}
