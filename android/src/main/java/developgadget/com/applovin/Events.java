package developgadget.com.applovin;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewDisplayErrorCode;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

import java.util.List;
import java.util.Map;

import io.flutter.Log;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class Events implements AppLovinAdLoadListener, AppLovinAdDisplayListener,
        AppLovinAdClickListener, AppLovinAdRewardListener,
        AppLovinAdVideoPlaybackListener, AppLovinAdViewEventListener,
        AppLovinNativeAdLoadListener, AppLovinNativeAdPrecacheListener {

    private final Registrar registrar;
    private final MethodChannel channel;

    public Events(Registrar registrar, MethodChannel channel) {
        this.registrar = registrar;
        this.channel = channel;
    }

    private void CallBack(String Message, String Method){
        this.channel.invokeMethod(Method, null);
        Log.d("AppLovin", Message);
    }

    @Override
    public void adClicked(AppLovinAd ad) {
        Events.this.CallBack("adClicked success", "adClicked");
    }

    @Override
    public void adDisplayed(AppLovinAd ad) {
        Events.this.CallBack("adDisplayed success", "adDisplayed");
    }

    @Override
    public void adHidden(AppLovinAd ad) {
        ApplovinPlugin.Ad = null;
        Events.this.CallBack("adHidden success", "adHidden");
    }

    @Override
    public void adReceived(AppLovinAd ad) {
        ApplovinPlugin.Ad = ad;
        Events.this.CallBack("adReceived success", "adReceived");
    }

    @Override
    public void failedToReceiveAd(int errorCode) {
        Events.this.CallBack("failedToReceiveAd success", "failedToReceiveAd");
    }

    @Override
    public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
        Events.this.CallBack("userRewardVerified success", "userRewardVerified");
    }

    @Override
    public void userOverQuota(AppLovinAd ad, Map<String, String> response) {
        Events.this.CallBack("userOverQuota success", "userOverQuota");
    }

    @Override
    public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {
        Events.this.CallBack("userRewardRejected success", "userRewardRejected");
    }

    @Override
    public void validationRequestFailed(AppLovinAd ad, int errorCode) {
        Events.this.CallBack("validationRequestFailed success", "validationRequestFailed");
    }

    @Override
    public void userDeclinedToViewAd(AppLovinAd ad) {
        Events.this.CallBack("userDeclinedToViewAd success", "userDeclinedToViewAd");
    }

    @Override
    public void adOpenedFullscreen(AppLovinAd ad, AppLovinAdView adView) {
        Events.this.CallBack("adOpenedFullscreen success", "adOpenedFullscreen");
    }

    @Override
    public void adClosedFullscreen(AppLovinAd ad, AppLovinAdView adView) {
        Events.this.CallBack("adClosedFullscreen success", "adClosedFullscreen");
    }

    @Override
    public void adLeftApplication(AppLovinAd ad, AppLovinAdView adView) {
        Events.this.CallBack("adLeftApplication success", "adLeftApplication");
    }

    @Override
    public void adFailedToDisplay(AppLovinAd ad, AppLovinAdView adView, AppLovinAdViewDisplayErrorCode code) {
        Events.this.CallBack("adFailedToDisplay success", "adFailedToDisplay");
    }

    @Override
    public void videoPlaybackBegan(AppLovinAd ad) {
        Events.this.CallBack("videoPlaybackBegan success", "videoPlaybackBegan");
    }

    @Override
    public void videoPlaybackEnded(AppLovinAd ad, double percentViewed, boolean fullyWatched) {
        Events.this.CallBack("videoPlaybackEnded success", "videoPlaybackEnded");
    }

    @Override
    public void onNativeAdsLoaded(List<AppLovinNativeAd> nativeAds) {
        ApplovinPlugin.NativeAds = nativeAds;
        Events.this.CallBack("onNativeAdsLoaded success", "onNativeAdsLoaded");
    }

    @Override
    public void onNativeAdsFailedToLoad(int errorCode) {
        Events.this.CallBack("onNativeAdsFailedToLoad success", "onNativeAdsFailedToLoad");
    }

    @Override
    public void onNativeAdImagesPrecached(AppLovinNativeAd ad) {
        Events.this.CallBack("onNativeAdImagesPrecached success", "onNativeAdImagesPrecached");
    }

    @Override
    public void onNativeAdVideoPreceached(AppLovinNativeAd ad) {
        Events.this.CallBack("onNativeAdVideoPreceached success", "onNativeAdVideoPreceached");
    }

    @Override
    public void onNativeAdImagePrecachingFailed(AppLovinNativeAd ad, int errorCode) {
        Events.this.CallBack("onNativeAdImagePrecachingFailed success", "onNativeAdImagePrecachingFailed");
    }

    @Override
    public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd ad, int errorCode) {
        Events.this.CallBack("onNativeAdVideoPrecachingFailed success", "onNativeAdVideoPrecachingFailed");
    }
}
