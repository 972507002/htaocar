package study.common;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;

/**
 * Created by sin on 2017/11/12.
 * @class:OSSClientConstants
 */
public class SmsInstrument {

    public static void sendCode(String telphoneNum,String code) {

        /**
         * Step 1. 获取主题引用
         */
        CloudAccount account = new CloudAccount(
                OSSClientConstants.ACCESS_KEY_ID, OSSClientConstants.ACCESS_KEY_SECRET, OSSClientConstants.ACCOUNTENDPOINT);
        MNSClient client = account.getMNSClient();
        CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");
        /**
         * Step 2. 设置SMS消息体
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         */
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");
        /**
         * Step 3. 生成SMS消息属性
         */
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName("汇淘二手车");
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode("SMS_69140086");
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
//            smsReceiverParams.setParam("$YourSMSTemplateParamKey1", "$value1");
        smsReceiverParams.setParam("code", code);
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver(telphoneNum, smsReceiverParams);
//            batchSmsAttributes.addSmsReceiver("$YourReceiverPhoneNumber2", smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        try {
            /**
             * Step 4. 发布SMS消息
             */
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            System.out.println("MessageId: " + ret.getMessageId());
            System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
        } catch (ServiceException se) {
            System.out.println(se.getErrorCode() + se.getRequestId());
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }

    public static void main(String[] args) {
        sendCode("17606516052","6452");
    }
}

