package push.agtech.com.agtechpush;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.taobao.agoo.TaobaoBaseIntentService;

import org.android.agoo.common.AgooConstants;

public class DemoAgooService extends TaobaoBaseIntentService {

    @Override
    protected void onError(Context context, String errorId) {

    }

    @Override
    protected void onRegistered(Context context, String registrationId) {

    }

    @Override
    protected void onUnregistered(Context context, String registrationId) {

    }

    @Override
    protected void onMessage(Context context, Intent intent) {
        String messageId = intent.getStringExtra(AgooConstants.MESSAGE_ID);
        String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
    }
}
