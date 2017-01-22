package com.cutv.ningbo.ui.util;

import android.os.Environment;


import java.io.File;
import java.util.HashMap;

/**
 * project：nbtv_ningbo
 * description：
 * create developer： Arvin
 * create time：2015/12/17 10:14.
 * modify developer：  Arvin
 * modify time：2015/12/17 10:14.
 * modify remark：
 *
 * @version 2.0
 */
public class SetInfo {
    public static final String FORMAT_HOUR_MIN = "HH:mm";
    public static final String FORMAT_DATE_MONTH_MIN = "MM-dd HH:mm";
    public static final String FORMAT_DATE_DAY = "yyyy-MM-dd";
    public static final String FORMAT_TIME = "HH:mm:ss";
    public static final String FORMAT_DATE_MIN = "yyyy-MM-dd HH:mm";


//    public static final String FORMAT_COLOR = "<font color='#f95a12'>dd</font>天<font color='#f95a12'>HH</font>时" +
//            "<font color='#f95a12'>mm</font>分<font color='#f95a12'>ss</font>秒";
    public static final String FORMAT_COLOR = "天-HH:时-mm:分-ss:秒";
    public static final String FORMAT_DATE_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_ALL = "yyyy-MM-dd HH:mm:ss:SSS";

    public static final String FORMAT_CRASG_TIME = "MM-dd HH:mm:ss:SSS";
    public static final String FileUrl = "http://upload.tools.nbtv.cn/";

    public static final String user = "user/";
    public static final String IMG_PATH = "img_path";
    public static boolean isLogin = false;

    public static String webstyle = "<style> p img{width:100%;height:auto}\n" +
            "h1{padding:5px 0; font-size:20px;}.info{font-size:14px; color:#666;}.info span{display:inline-block; margin-right:10px;}</style>\n" +
            "<h1>标题</h1> <div class=\"info\"><span class=\"date\">日期</span><span class=\"orginal\">出版社</span></div>\n";
    public static final String TITLE = "title";
    public static final String TITLE_RIGHT = "title right";
    public static final String JavaScriptDto = "script";
//    public static final String NEWSSLIDE = "slide";
    public static final String BROADCAST = "";
    public static final String PATH = Environment.getExternalStorageDirectory() + File.separator + "dknb" + File.separator;
    public static final String OUTURL = "out_url";
    public static final String INTENTID = "intent_id";
    public static HashMap<String, Integer> SHAREMAP = new HashMap<>();
    public static final String USERID = "tczeidt";
    public static final String DTO ="dknb_dto";
    public static boolean important = false;
    public static int userid;
    public static final String confirm = "confirm";

    public static final String AddressDto = "address";
    public static final int selectAddress = 120;

    static {
        SHAREMAP.put("Wechat", 1);
        SHAREMAP.put("QQ", 2);
        SHAREMAP.put("SinaWeibo", 3);
    }

    public static boolean MEDIA = true;
    public static boolean mail;
    //    public static boolean check_type;
    public static boolean mediaFlag = true;

    public static int NOTICEID = 0;
    public static final String UPDATEAPK = "update_apk";
    public static final String NINGBO = "ningbo";
    public static final String USER = "user";
    public static final String OLDER = "my.config";


    private static final String DKNBUrl = "http://cmsapi.tools.nbtv.cn/";
    private static final String SHAKEURL = "http://apishake.dknb.nbtv.cn/";
    private static final String UserUrl = "http://api.dknb.nbtv.cn/";
    private static final String MallUrl = "http://apimall.dknb.nbtv.cn/";
    private static final String TOPICURL = "http://apitopic.dknb.nbtv.cn/";

    /**
     * news interface 新闻相关接口
     */
    public static final String DKNBListUrl = DKNBUrl + "?task=get-articles&channelId=";
    public static final String VideoListUrl = DKNBUrl + "?task=get-live&channelName=";
    public static final String NewsColumnUrl = DKNBUrl + "?task=get-news-column";
    public static final String NewsPageUrl = DKNBUrl + "?task=get-articles&channelId=11684&count=3&offset=0";
    public static final String TVHomeUrl = DKNBUrl + "?task=get-vods&type=nbtv";
    public static final String FMHomeUrl = DKNBUrl + "?task=get-vods&type=fm";



    /**
     * user interface 用户相关接口                  api.dknb.nbtv.cn/
     */
//    http://api.dknb.nbtv.cn/live/gift-list

    public static final String BroadHomeUrl = UserUrl+"live/home";
    public static final String BroadListGiftUrl = UserUrl+"live/gift-list";

    public static final String BroadGiftUrl = UserUrl+"live/give-gift";//http://api.dknb.nbtv.cn/live/give-gift
    public static final String OLDERUSER =UserUrl+ "user/cutv-auto-login";

    public static final String RefreshURL = UserUrl + "user/exchange-token";
    public static final String AdvertisementUrl = UserUrl + "advertise/normal-advertise?position_id=";
    public static final String DiscussUrl = UserUrl + "comment/create";
    public static final String CommentDiscussUrl = UserUrl + "comment/list";
    public static final String DeleteAddressUrl = UserUrl + "address/delete";
    public static final String UpdatePsdUrl = UserUrl + "user/update-password";
    public static final String DeclareListUrl = UserUrl + "investigation/list";
    public static final String LoginUrl = UserUrl + "user/login";
//    public static final String CheckTypeUrl = UserUrl + "user/view-step-sign-status";
    public static final String CheckTypeUrl = UserUrl + "user/view-step-sign-status";
    public static final String RandomNickName = UserUrl + "user/get-random-nickname";

    //http://api.dknb.nbtv.cn/user/get-random-nickname

    public static final String ThirdLoginURL = UserUrl + "user/third-party-login";
    public static final String SmsUrl = UserUrl + "user/get-sms";
    public static final String ThirdRegisterUrl = UserUrl + "user/third-party-register";
    public static final String ScoreUrl = UserUrl + "user/get-integral";
    public static final String NavigationUrl = UserUrl + "common/quick-navigation";
//    public static final String CheckSignUrl = UserUrl + "user/sign";
    public static final String CheckSignUrl = UserUrl+"user/sign-step";

    public static final String ShowSubjectUrl = UserUrl+"show/subject-entry";
    //http://api.dknb.nbtv.cn/user/sign-step
    //http://api.dknb.nbtv.cn/user/view-step-sign-status
    public static final String MaLlSCOREUrl = SHAKEURL + "shake/get-shake-goods";
//http://apishake.dknb.nbtv.cn/shake/get-shake-goods
    public static final String AddressDefaultURL = UserUrl + "address/set-default";
    public static final String AddressAddUrl = UserUrl + "address/create";
    public static final String AddressEditUrl = UserUrl + "address/update";
    public static final String AddressListUrl = UserUrl + "address/list";
    public static final String AddressUpdateUrl = UserUrl + "user/update-user-message";

    public static final String UserHeadUrl = UserUrl + "user/upload-avatar";
    public static final String RegisterUrl = UserUrl + "user/register";

    public static final String ShakeBeginUrl = SHAKEURL + "shake/task-begin";
    public static final String ShakeChannelUrl = SHAKEURL + "shake/get-shake-status";
    public static final String ShakeInfoUrl = SHAKEURL + "shake/get-shake-information";
    public static final String ShakeListUrl = SHAKEURL + "shake/get-my-shakelog";
    public static final String ShakeCommitUrl =SHAKEURL+ "shake/shake-answers";

    public static final String ShowPraiseURL = UserUrl + "show/praise";
    public static final String ShowVisitUrl = UserUrl + "show/visit-video";
    public static final String ShowForwardUrl = UserUrl + "show/forward";
    public static final String ShowMySelfUrl = UserUrl + "show/my-show";
    public static final String ShowCodeUrl = UserUrl + "show/tags-list";
    public static final String ShowUploadUrl = UserUrl + "show/create";
    public static final String ShowTagUrl = UserUrl + "show/tags-list?is_recommend=1";
    public static final String ShowTagListUrl = UserUrl + "show/list-by-tid";
    public static final String ShowDetailUrl = UserUrl +"show/detail";
//    api.dknb.nbtv.cn/show/detail?id=%@
    public static final String AdvertiseUrl = UserUrl + "advertise/boot-advertise";
    public static final String NoticeUrl = UserUrl + "common/system-notice";
    public static final String HomePageUrl = UserUrl + "common/get-home";
    public static final String HomeShakeTimeUrl = UserUrl + "common/get-shake-time";
    public static final String InteractHomeUrl = UserUrl + "common/get-hudong-home";
    public static final String InteractListUrl = UserUrl + "activity/list?";

    public static final String FEEDBACKQUESTION = UserUrl+"question/common-question-list";
    public static final String FEEDBACKCLASSIFY = UserUrl+"question/category";
    public static final String FEEDBACKNOTICEURL = UserUrl+"common/question-inform";
    public static final String FEEDBACKMYQUESTION = UserUrl+"question/my-question-list";
    public static final String FEEDBACKCOMMITQUESTION = UserUrl+"question/create";
    public static final String FEEDBACKTAGSURL = UserUrl+"question/tags";
    public static final String SYSTEMMESSAGEURL = UserUrl+"common/get-system-message";
    public static final String FEEDBACKANSWERURL = UserUrl +"question/my-detail-answer";
    public static final String FriendListUrl = UserUrl+"user-friend/my-friends";
    public static final String FriendApplyUrl = UserUrl+"user-friend/apply-friend";//http://api.dknb.nbtv.cn/user-friend/apply-friend
    public static final String AnswerApplyFriend = UserUrl +"user-friend/operation-relation";//http://api.dknb.nbtv.cn/user-friend/operation-relation
    public static final String FriendInfoUrl = UserUrl+"user-friend/get-user-message";
//http://api.dknb.nbtv.cn/user-friend/get-user-message
    public static final String LiveUrl = "http://live.dknb.nbtv.cn/";
    public static final String LiveTrailerUrl = LiveUrl+"video/notices";
    public static final String LiveContentUrl = LiveUrl+"video/room";
    public static final String LiveSayUrl = LiveUrl+"video/say";//http://live.dknb.nbtv.cn/video/say
    public static final String LiveListWholeUrl = LiveUrl+"video/list";
    public static final String LiveListUrl = LiveUrl+"video/more-rooms";
    public static final String LiveRankUrl = LiveUrl+"video/refresh-rank";
    public static final String LiveGiftListUrl = LiveUrl+"video/gift-list";
    public static final String LiveMoreChat = LiveUrl+"video/more-chat";
    public static final String LiveSendGiftUrl = LiveUrl+"video/give-gift";
    public static final String LiveRelevantUrl = LiveUrl+"video/correlations";
    public static final String LiveWatchUrl = LiveUrl+"video/watch-log";
    public static final String LiveReportUrl = LiveUrl+"video/report-chat";
    public static final String LiveTextUrl = LiveUrl+"text/list";
    public static final String LiveMoreTextUrl = LiveUrl+"text/more-list";
    public static final String LiveSendTextUrl = LiveUrl+"text/create";
    public static final String LiveModelDto = LiveUrl + "activity/detail";
    public static final String LiveModelSign = LiveUrl + "activity/sign";
    public static final String LiveModelVote = LiveUrl + "activity/vote";



    public static final String MaLlAwardUrl = MallUrl + "mall/my-purchase-goods";
    public static final String MallAdvertiseUrl = MallUrl + "mall/get-recomslide";
    public static final String MallListUrl = MallUrl + "mall/goods-list";
    public static final String MallPicUrl = MallUrl + "mall/get-promotion";
    public static final String MallDetailUrl = MallUrl + "mall/detail?goid=";
    public static final String MallBugUrl = MallUrl + "mall/purchase";
//    public static final String MaLlAwardUrl = UserUrl + "mall/my-purchase-goods";
//    public static final String MallAdvertiseUrl = MallUrl + "mall/promotion";
//    public static final String MallListUrl = MallUrl + "mall/goods-list";
//    public static final String MallPicUrl = MallUrl + "mall/get-promotion";
//    public static final String MallDetailUrl = MallUrl + "mall/detail?goid=";
//    public static final String MallBugUrl = MallUrl + "mall/purchase";
    /**
     * news provide interface 报料接口
     */
//    private static final String ProvideUrl = "http://apiexplosive.dknb.nbtv.cn/";
    public static final String ProvideProgram = UserUrl + "common/get-programgroup";
    public static final String ProvideUploadUrl = UserUrl + "explosive/create";
    public static final String ProvideMySelfUrl = UserUrl + "explosive/my-explosive";
    public static final String ProvideDetailUrl = UserUrl+"explosive/my-explosive-detail";
//    private static final String ProvideDetailUrl = "http://api.explosive.tools.nbtv.cn/explosive/my-explosive-detail";

    /**
     * webView h5 view H5相关界面
     */
    private static final String WebViewURL = "http://h5.dknb.nbtv.cn/";
    public static final String InviteUrl = WebViewURL + "invitation/code";
    public static final String DeclareDetailUrl = WebViewURL + "vote/detail?inid=";
    public static final String GetPsdUrl = WebViewURL + "user/get-password2";
    public static final String MallDetailWebViewUrl = WebViewURL + "mall/detail?goid=";
    public static final String FeedBackUrl = WebViewURL + "feedback";
    public static final String AboutUrl = WebViewURL + "about.html";
    public static final String ShowShareUrl = WebViewURL + "show/detail?showId=";
    public static final String NewsDetailUrl = WebViewURL + "news/detail?articleId=";
    public static final String HomeDetailUrl = WebViewURL + "activity/detail?activityId=";

    public static final String GGKUrl = WebViewURL + "lottery/ggk";
    public static final String DZPUrl = WebViewURL + "lottery/dzp";
    public static final String CityServiceUrl = WebViewURL + "service/list";
    public static final String LiveApplyUrl = WebViewURL+"live/apply";

    //http://live.dknb.nbtv.cn/video/report-chat
    public static final String LiveShareUrl = WebViewURL+"live?room_id=";

//    http://h5.dknb.nbtv.cn/live/apply



    public static final String TOPIC_REPLY_MORE = TOPICURL+"topic/reply-detail";
    public static final String TOPIC_PARISE_STATUS = TOPICURL+"topic/like-status";
//    public static final String TOPIC_REPLY = "http://apitopic.cutvapp.nbtv.cn/topic/create-reply";
    public static final String TOPIC_REPLY = TOPICURL+"topic/create-reply";
    public static final String TOPIC_COMMENT = TOPICURL+"topic/create-comments-reply";
    public static final String TOPIC_PARISE = TOPICURL+"topic/like";
    public static final String TOPIC_REPLY_PARISE = TOPICURL+"topic/reply-like";
    //http://apitopic.cutvapp.nbtv.cn/topic/reply-list?count=10&offset=1&t=1
    public static final String TOPIC_DISCUSS = TOPICURL+"topic/reply-list";
    public static final String TOPIC_CONTENT = TOPICURL+"topic/detail?t=";

    public static final String TOPIC = TOPICURL+"topic/list";

}