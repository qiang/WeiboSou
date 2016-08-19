/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package febsky.me.weibosou.common;

/**
 * Author:  liuqiang
 * Date:    2015/3/9.
 * Description:
 */
public class ApiConstants {

    public final static String SCHEME_HTTPS = "http://";
    public static final String HOST_NAME = "127.0.0.1";
    public final static String DOMAIN_NAME = SCHEME_HTTPS + HOST_NAME;

    public static final class Urls {

        public static final String DICT_INIT = DOMAIN_NAME + "/dictInit";
    }

    public static final String getUserList(int page) {
        return "https://api.weibo.cn/2/guest/cardlist?" +
                "networktype=wifi&uicode=10000327" +
                "&moduleID=708&checktoken=c1959aa237ffb163ac351a08bb8d9784" +
                "&c=android&i=a52f1e4&s=c1108e87" +
                "&ua=OPPO-OPPO%20R9m__weibo__6.8.2__android__android5.1" +
                "&wm=9847_0002&aid=01AilAKZLB81znjKciZxofmqIMYg52EReWuEaQL7hIDXj6IR4." +
                "&did=b87cc255f19b91ff8e202968adab0eb9fc159a2e&uid=1005508234880" +
                "&v_f=2&v_p=33" +
                "&from=1068295010" +
                "&gsid=_2AkMg6ZSSf8NhqwJRmP0QzGPgb4l_wgjEieLBAH7sJRM3HRl-3T9jqnUstRUyD-wT6lM3A4HWHM1fFXBWuOYnxg.." +
                "&lang=zh_CN&page=" + page + "&skin=default&count=20" +
                "&oldwm=9893_0044&sflag=1&containerid=1087030002_2982_2_50&need_head_cards=0";
    }

}