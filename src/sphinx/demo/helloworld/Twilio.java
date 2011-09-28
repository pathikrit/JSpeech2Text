package sphinx.demo.helloworld;


import com.google.common.collect.Maps;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Sms;

import java.util.HashMap;
import java.util.Map;

public class Twilio {

    private final static Map<String, String> phoneBook = Maps.newHashMap();
    {
        phoneBook.put("rick", "8177155168");
        phoneBook.put("vasily", "");
    }

    private final static String ACCOUNT_SID = "AC57abda8020a34c6795573dd43430d4ed",
                                AUTH_TOKEN = "-- sekrit --";

    private final static TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
    private final static Account mainAccount = client.getAccount();

    private final static String twilioHomeNum = "(503) 406-6639";

    public static void sendSms(String to, String message) {
        SmsFactory smsFactory = mainAccount.getSmsFactory();
        Map<String, String> smsParams = new HashMap<String, String>();
        smsParams.put("From", twilioHomeNum);
        smsParams.put("To", to);
        smsParams.put("Body", message);
        try {
            Sms sms = smsFactory.create(smsParams);
        } catch (TwilioRestException e) {
            e.printStackTrace();
        }
    }
}

