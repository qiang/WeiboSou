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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Author:  liuqiang
 * Date:    2015/3/9.
 * Description:
 */
public class Api {

    //00c6b670f5585dfce086ce136393f121
    public static final String CHECK_TOKEN = "";

    public static String requestUserList(int page) {
        return "https://api.weibo.cn/2/guest/cardlist?" +
                "networktype=wifi&uicode=10000327" +
                "&moduleID=708" +
                "&checktoken=" + CHECK_TOKEN +
                "&c=android&i=a52f1e4&s=c1108e87" +
                "&ua=OPPO-OPPO%20R9m__weibo__6.8.2__android__android5.1" +
                "&wm=9847_0002&aid=01AilAKZLB81znjKciZxofmqIMYg52EReWuEaQL7hIDXj6IR4." +
                "&did=b87cc255f19b91ff8e202968adab0eb9fc159a2e&" +
//                "uid=1005508234880" +
                "&v_f=2&v_p=33" +
                "&from=1068295010" +
                "&gsid=_2AkMg6ZSSf8NhqwJRmP0QzGPgb4l_wgjEieLBAH7sJRM3HRl-3T9jqnUstRUyD-wT6lM3A4HWHM1fFXBWuOYnxg.." +
                "&lang=zh_CN&page=" + page + "&skin=default&count=20" +
                "&oldwm=9893_0044&sflag=1&containerid=1087030002_2982_2_50&need_head_cards=0";
    }

    public static String requestFid(String uid, String lcardid) {
        return "https://api.weibo.cn/2/profile?" +
                "networktype=wifi&uicode=10000198&moduleID=708" +
                "&user_domain=" + uid +
                "&checktoken=" + CHECK_TOKEN +
                "&lcardid=" + lcardid +
                "&c=android&i=a52f1e4&s=c1108e87" +
                "&ua=OPPO-OPPO%20R9m__weibo__6.9.0__android__android5.1&wm=9847_0002" +
                "&aid=01AilAKZLB81znjKciZxofmqIMYg52EReWuEaQL7hIDXj6IR4.&did=b87cc255f19b91ff8e202968adab0eb9c6ca4538" +
                "&uid=" + uid +
                "&v_f=2&v_p=34&from=1069095010" +
                "&gsid=_2AkMgmj9df8NhqwJRmP0QzGPgb4l_wgjEieLBAH7sJRM3HRl-3T9kqmwFtRUGcYGOhZ32ozb7A5YdbeytwvBaMA.." +
                "&lang=zh_CN&lfid=1087030002_2982_2_50&skin=default&oldwm=9893_0044&sflag=1&cover_width=1080&luicode=10000327";
    }


    public static String requestUserPhotoFistPage(String uid, String fid, String lcardid) {
        return "http://api.weibo.cn/2/guest/cardlist?" +
                "networktype=wifi&uicode=10000198&moduleID=708" +
                "&checktoken=" + CHECK_TOKEN +
                "&lcardid=" + lcardid +
                "&c=android&i=a52f1e4&s=c1108e87" +
                "&ua=OPPO-OPPO%20R9m__weibo__6.9.0__android__android5.1" +
                "&wm=9847_0002&aid=01AilAKZLB81znjKciZxofmqIMYg52EReWuEaQL7hIDXj6IR4.&did=b87cc255f19b91ff8e202968adab0eb92640d513" +
                "&fid=" + fid +
                "&uid=" + uid +    //这个uid不是这个照片用户的uid
                "&v_f=2&v_p=34&from=1069095010&gsid=_2AkMgma5of8NhqwJRmP0QzGPgb4l_wgjEieLBAH7sJRM3HRl-3T9jqlYvtRVLv0wphdOwMcXvm9nBFI4vXAS62Q.." +
                "&lang=zh_CN" +
                "&lfid=230584" +
                "&page=1&skin=default&count=20&oldwm=9893_0044&sflag=1" +
                "&containerid=" + fid +
                "&luicode=10000228&need_head_cards=0";
    }

    public static String requestUserPhotoMorePage(String uid, String fid, String since_id, String lcardid) {

        return "https://api.weibo.cn/2/guest/cardlist?" +
                "networktype=wifi&uicode=10000198&moduleID=708" +
                "&checktoken=" + CHECK_TOKEN +
                "&lcardid=" + lcardid +
                "&c=android&i=a52f1e4&s=c1108e87" +
                "&ua=OPPO-OPPO%20R9m__weibo__6.9.0__android__android5.1" +
                "&wm=9847_0002&aid=01AilAKZLB81znjKciZxofmqIMYg52EReWuEaQL7hIDXj6IR4." +
                "&did=b87cc255f19b91ff8e202968adab0eb9655879de" +
                "&fid=" + fid +
                "&uid=" + uid +    //这个uid不是这个照片用户的uid
                "&v_f=2&v_p=34&from=1069095010" +
                "&gsid=_2AkMgmj9df8NhqwJRmP0QzGPgb4l_wgjEieLBAH7sJRM3HRl-3T9kqmwFtRUGcYGOhZ32ozb7A5YdbeytwvBaMA.." +
                "&lang=zh_CN" +
                "&lfid=1087030002_2982_2_50" +
                "&skin=default&count=20&oldwm=9893_0044&sflag=1" +
                "&containerid=" + fid +
                "&since_id=" + since_id +
                "&luicode=10000327&need_head_cards=0";
    }


    /**
     * @param keyword 搜索关键字
     * @param page    当前页数
     * @return
     */
    public static String requestSearchPage(String keyword, int page) {
        String fid = "100303type=3&q=" + keyword + "&t=0";
        try {
            fid = URLEncoder.encode(fid, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "https://api.weibo.cn/2/guest/cardlist?" +
                "networktype=wifi&uicode=10000003&moduleID=708" +
                "&checktoken=" + CHECK_TOKEN +
                "&featurecode=10000085&c=android&i=a52f1e4&s=c1108e87" +
                "&ua=OPPO-OPPO%20R9m__weibo__6.9.1__android__android5.1" +
                "&wm=9847_0002&aid=01AilAKZLB81znjKciZxofmqIMYg52EReWuEaQL7hIDXj6IR4." +
                "&did=b87cc255f19b91ff8e202968adab0eb947582531&" +
                "fid=" + fid +//
                "&uid=1005508234880&v_f=2&v_p=35&from=1069195010" +
                "&gsid=_2AkMgiF7Tf8NhqwJRmP0QzGPgb4l_wgjEieLBAH7sJRM3HRl-3T9jqnRStRVfc18OG95HeQFMPdzK0L3CZAfaSg.." +
                "&lang=zh_CN&lfid=230584" +
                "&page=" + page +
                "&skin=default&count=20&oldwm=9893_0044&sflag=1" +
                "&containerid=" + fid +
                "&luicode=10000228&container_ext=nettype%3Awifi&need_head_cards=1";
    }


    //网易新闻客户端美女
    //http://c.m.163.com/recommend/getChanRecomNews?channel=T1456112189138&size=20

}