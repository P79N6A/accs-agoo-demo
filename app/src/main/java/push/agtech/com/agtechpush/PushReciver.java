package push.agtech.com.agtechpush;

import android.text.TextUtils;
import android.util.Log;

import com.taobao.accs.IAppReceiver;

import java.util.HashMap;
import java.util.Map;

import static push.agtech.com.agtechpush.PushApplication.LOCAL_SERVICE;
import static push.agtech.com.agtechpush.PushApplication.SERVICE_NAME;

/**
 * Created by wangxinjue on 2019/3/13.
 */

public class PushReciver implements IAppReceiver {

    public static Map<String,String> serviceMap;

    static {
        //key为serviceId, value为继承TaobaseService实现的接收消息service
        serviceMap = new HashMap<>();
        serviceMap.put(SERVICE_NAME, LOCAL_SERVICE);
    }

    @Override
    public String getService(String serviceId) {
        String service = serviceMap.get(serviceId);
        if(!TextUtils.isEmpty(service)){
            Log.d("wxj", "getService: service="+service);
            return service;
        }
        return "";
    }

    @Override
    public Map<String, String> getAllServices() {
        Log.d("wxj", "getAllServices: ");
        return serviceMap;
    }

    @Override
    public void onData(String s, String s1, byte[] bytes) {
        Log.d("wxj", "onData: ");
    }

    @Override
    public void onBindApp(int i) {
        Log.d("wxj", "onBindApp: ");
    }

    @Override
    public void onUnbindApp(int i) {
        Log.d("wxj", "onUnbindApp: ");
    }

    @Override
    public void onBindUser(String s, int i) {
        Log.d("wxj", "onBindUser: ");
    }

    @Override
    public void onUnbindUser(int i) {
        Log.d("wxj", "onUnbindUser: ");
    }

    @Override
    public void onSendData(String s, int i) {
        Log.d("wxj", "onSendData: ");
    }
}
