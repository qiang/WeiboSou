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

package me.febsky.weibosou.common;

/**
 * Author:  liuqiang
 * Date:    2015/3/9.
 * Description:
 */
public class Api {

    public final static String SCHEME_HTTPS = "http://";
    public static final String HOST_NAME = "127.0.0.1";
    public final static String DOMAIN_NAME = SCHEME_HTTPS + HOST_NAME;

    public static String requestUserList(int page) {
        return "https://api.weibo.cn/2/guest/cardlist?" +
                "networktype=wifi&uicode=10000327" +
                "&moduleID=708" +
                "&checktoken=c1959aa237ffb163ac351a08bb8d9784" +
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

    public static String requestFid(String uid) {
        return "http://api.weibo.cn/2/profile?" +
                "networktype=wifi&uicode=10000198&moduleID=708" +
                "&user_domain=" + uid +
//                "&checktoken=d9012c0e4aa8a7ab2e8e2b0cab9d8169" +
                "&lcardid=4013903199307220&c=android&i=a52f1e4&s=c1108e87" +
                "&ua=OPPO-OPPO%20R9m__weibo__6.9.0__android__android5.1" +
                "&wm=9847_0002&aid=01AilAKZLB81znjKciZxofmqIMYg52EReWuEaQL7hIDXj6IR4." +
                "&did=b87cc255f19b91ff8e202968adab0eb92640d513" +
                "&uid=3283836867" + uid +
                "&v_f=2&v_p=34&from=1069095010" +
                "&gsid=_2AkMgma5of8NhqwJRmP0QzGPgb4l_wgjEieLBAH7sJRM3HRl-3T9jqlYvtRVLv0wphdOwMcXvm9nBFI4vXAS62Q.." +
                "&lang=zh_CN&lfid=230584" +
                //"&nick=%E5%8C%97%E7%94%B5%E4%B8%AD%E6%88%8F%E7%9A%84%E7%BE%8E%E5%A5%B3%E4%BB%AC" +
                "&skin=default&oldwm=9893_0044&sflag=1&cover_width=1080&profile_entry_key=1&luicode=10000228";
    }


    public static String requestUserPhotoFistPage(String uid, String fid) {
        return "http://api.weibo.cn/2/guest/cardlist?" +
                "networktype=wifi&uicode=10000198&moduleID=708" +
                "&checktoken=d9012c0e4aa8a7ab2e8e2b0cab9d8169" +
                "&lcardid=4013899802264408&c=android&i=a52f1e4&s=c1108e87" +
                "&ua=OPPO-OPPO%20R9m__weibo__6.9.0__android__android5.1" +
                "&wm=9847_0002&aid=01AilAKZLB81znjKciZxofmqIMYg52EReWuEaQL7hIDXj6IR4.&did=b87cc255f19b91ff8e202968adab0eb92640d513" +
                "&fid=" + fid +
                "&uid=" + uid +
                "&v_f=2&v_p=34&from=1069095010&gsid=_2AkMgma5of8NhqwJRmP0QzGPgb4l_wgjEieLBAH7sJRM3HRl-3T9jqlYvtRVLv0wphdOwMcXvm9nBFI4vXAS62Q.." +
                "&lang=zh_CN&lfid=230584&page=1&skin=default&count=20&oldwm=9893_0044&sflag=1" +
                "&containerid=" + fid +
                "&luicode=10000228&need_head_cards=0";
    }

    public static String requestUserPhotoMorePage(String uid, String fid, String since_id) {
        return "http://api.weibo.cn/2/guest/cardlist?" +
                "networktype=wifi&uicode=10000198&moduleID=708" +
                "&checktoken=d9012c0e4aa8a7ab2e8e2b0cab9d8169" +
                "&lcardid=4013899802264408&c=android&i=a52f1e4&s=c1108e87" +
                "&ua=OPPO-OPPO%20R9m__weibo__6.9.0__android__android5.1" +
                "&wm=9847_0002&aid=01AilAKZLB81znjKciZxofmqIMYg52EReWuEaQL7hIDXj6IR4." +
                "&did=b87cc255f19b91ff8e202968adab0eb92640d513" +
                "&fid=" + fid +
                "&uid=" + uid +
                "&v_f=2&v_p=34&from=1069095010" +
                "&gsid=_2AkMgma5of8NhqwJRmP0QzGPgb4l_wgjEieLBAH7sJRM3HRl-3T9jqlYvtRVLv0wphdOwMcXvm9nBFI4vXAS62Q.." +
                "&lang=zh_CN&lfid=230584&skin=default&count=20&oldwm=9893_0044&sflag=1" +
                "&containerid=" + fid +
                "&since_id=" + since_id +
                "&luicode=10000228&need_head_cards=0";
    }

}