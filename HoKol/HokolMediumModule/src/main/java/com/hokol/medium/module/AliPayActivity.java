package com.hokol.medium.module;

import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;
import com.yline.base.BaseAppCompatActivity;

import java.util.Map;

public abstract class AliPayActivity extends BaseAppCompatActivity {
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APP_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "";

    public static final String RSA_PRIVATE = "";

    protected boolean doPay(final String orderInfo) {
        new AsyncTask<Void, Void, Map<String, String>>() {
            @Override
            protected Map<String, String> doInBackground(Void... voids) {
                PayTask aliPay = new PayTask(AliPayActivity.this);
                Map<String, String> result = aliPay.payV2(orderInfo, true);
                return result;
            }

            @Override
            protected void onPostExecute(Map<String, String> stringStringMap) {
                super.onPostExecute(stringStringMap);
                onPayBack(stringStringMap.get(AliPayBean.KeyMemo), stringStringMap.get(AliPayBean.KeyResultStatus), stringStringMap.get(AliPayBean.KeyResult));
            }
        }.execute();
        return false;
    }

    /**
     * 支付宝支付之后，回掉处理
     *
     * @param memo       描述信息(类型为字符串)
     * @param status     结果码(类型为字符串)
     * @param jsonResult 处理结果(类型为json结构字符串)
     */
    public abstract void onPayBack(String memo, String status, String jsonResult);


}
