package push.agtech.com.agtechpush;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.taobao.accs.ACCSManager;
import com.taobao.accs.base.TaoBaseService;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static push.agtech.com.agtechpush.PushApplication.SERVICE_NAME;
import static push.agtech.com.agtechpush.PushApplication.accsClient;

/**
 * Created by wangxinjue on 2019/3/11.
 */

public class PushService extends TaoBaseService{
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("wxj", "PushService onBind: intent= "+intent);
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("wxj", "onCreate: ");
    }

    @Override
    public void onData(String serviceId, String userId, String dataId, byte[] data, TaoBaseService.ExtraInfo info) {
        Log.d("wxj", "onResponse:serviceId"+serviceId);
        Log.d("wxj", "onResponse:userId"+userId);
        Log.d("wxj", "onResponse:dataId"+dataId);

        try {
            String srt2 = new String(data,"UTF-8");
            Log.d("wxj", "onResponse: str="+ srt2);
            Toast.makeText(getApplicationContext(),"serverid="+serviceId+"  data="+srt2,Toast.LENGTH_SHORT).show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        ACCSManager.AccsRequest request = new ACCSManager.AccsRequest("userid",serviceId,data,dataId);
        accsClient.sendData(request);

        ACCSManager.AccsRequest requestAck = new ACCSManager.AccsRequest("userid",serviceId,data,dataId);
        accsClient.sendRequest(requestAck);

;
    }

    @Override
    public void onBind(String s, int i, ExtraInfo extraInfo) {
        Log.d("wxj", "PushService onBind: s="+s);
        Toast.makeText(getApplicationContext(),"onBind="+s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnbind(String s, int i, ExtraInfo extraInfo) {
        Log.d("wxj", "onUnbind:");
    }

    @Override
    public void onSendData(String s, String s1, int i, ExtraInfo extraInfo) {
        Log.d("wxj", "onSendData:");
        Toast.makeText(getApplicationContext(),"onSendData="+s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String s, String s1, int i, byte[] bytes, ExtraInfo extraInfo) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
        Log.d("wxj", "onResponse:s"+s);
        Log.d("wxj", "onResponse:s1"+s1);
        Log.d("wxj", "onResponse:i"+i);
        try {
            String srt2 = new String(bytes,"UTF-8");
            Log.d("wxj", "onResponse: str="+ srt2);
            Toast.makeText(getApplicationContext(),"data="+srt2,Toast.LENGTH_SHORT).show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
