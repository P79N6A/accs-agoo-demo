package push.agtech.com.agtechpush;

import android.app.Application;
import android.util.Log;

import com.taobao.accs.ACCSClient;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.AccsException;
import com.taobao.accs.common.Constants;
import com.taobao.agoo.IRegister;
import com.taobao.agoo.TaobaoRegister;


/**
 * Created by wangxinjue on 2019/3/11.
 */

public class PushApplication extends Application{
    public static String ttid = "ali_intell_Hw";
    public static final String LOCAL_SERVICE = "push.agtech.com.agtechpush.PushService";
    public static final String SECRET = "2ed40f65c693cc74cf77ef0ff6a13ff3";

    public static String AppID = "60042700";
    public static String SERVICE_NAME = "accs.id.service";

    public static ACCSClient accsClient;
//    public static String AppID = "60042701";
//    public static String SERVICE_NAME = "com.agtech.ailot.device.service.TerminalOperationService";

    @Override
    public void onCreate() {
        super.onCreate();
        initAccs();
        initAgoo();
    }

    private void initAccs(){
        //env参数: Constants.DEBUG = 2; Constants.PREVIEW = 1; Constants.RELEASE = 0;
        ACCSClient.setEnvironment(this, Constants.DEBUG);
        AccsClientConfig.Builder clientBuilder = new AccsClientConfig.Builder()
                .setAppKey(AppID) //必选, 所属appkey
                .setConfigEnv(Constants.DEBUG) //必选, 所属env
                .setTag(AccsClientConfig.DEFAULT_CONFIGTAG)
                .setAppSecret(SECRET);
        try {
            ACCSClient.init(this, clientBuilder.build());
            //如果不用accs的推送，只用agoo的推送，下面两句就可以不用了写了
            //这两句是绑定accs推送服务的
            accsClient = ACCSClient.getAccsClient(AccsClientConfig.DEFAULT_CONFIGTAG);
            accsClient.bindApp(ttid,new PushReciver());
        } catch (AccsException e) {
            e.printStackTrace();
        }
    }

    private void initAgoo(){
        try {
            TaobaoRegister.setEnv(this,Constants.DEBUG);
            TaobaoRegister.setAgooMsgReceiveService("push.agtech.com.agtechpush.DemoAgooService");
            TaobaoRegister.register(this, AccsClientConfig.DEFAULT_CONFIGTAG, AppID, SECRET, "", new IRegister() {
                @Override
                public void onSuccess(String deviceID) {
                    Log.d("wxj", "onSuccess: deviceID="+deviceID);
                }
    
                @Override
                public void onFailure(String errorCode, String errorMessage) {
                    Log.d("wxj", "onFailure: errorCode="+errorCode);
                    Log.d("wxj", "onFailure: errorMessage="+errorMessage);
                }
            });
        } catch (AccsException e) {
            e.printStackTrace();
        }
    }
}
