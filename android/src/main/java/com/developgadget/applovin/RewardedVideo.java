package com.developgadget.applovin;

import android.content.Context;

import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

import java.util.Map;

import io.flutter.Log;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

@SuppressWarnings("NullableProblems")
public class RewardedVideo implements AppLovinAdRewardListener,
        AppLovinAdLoadListener, AppLovinAdVideoPlaybackListener, AppLovinAdDisplayListener, AppLovinAdClickListener {

    private AppLovinIncentivizedInterstitial Rewarded;
    private final Context context;

    RewardedVideo(Context context) {
        this.context = context;
        this.Rewarded = AppLovinIncentivizedInterstitial.create(context);
    }


    public void Request() {
        if (this.Rewarded != null && this.context != null)
            this.Rewarded.preload(this);
        else
            Log.i("AppLovin", "Applovin request not instance");
    }

    public void Show() {
        if (this.Rewarded != null && this.Rewarded.isAdReadyToDisplay())
            this.Rewarded.show(this.context, this, this, this);
        else
            Log.d("AppLovin", "Ad not load");
    }

    @Override
    public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
        ApplovinPlugin.Callback("UserRewardVerified");
    }

    @Override
    public void userOverQuota(AppLovinAd ad, Map<String, String> response) {
        ApplovinPlugin.Callback("UserOverQuota");
    }

    @Override
    public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {
        ApplovinPlugin.Callback("UserRewardRejected");
    }

    @Override
    public void validationRequestFailed(AppLovinAd ad, int errorCode) {
        ApplovinPlugin.Callback("ValidationRequestFailed error sdk code " + errorCode);
    }

    @Override
    public void userDeclinedToViewAd(AppLovinAd ad) {
        ApplovinPlugin.Callback("UserDeclinedToViewAd");
    }

    @Override
    public void adReceived(AppLovinAd ad) {
        ApplovinPlugin.Callback("AdReceived");
    }

    @Override
    public void failedToReceiveAd(int errorCode) {
        ApplovinPlugin.Callback("FailedToReceiveAd error sdk code " + errorCode);
    }

    @Override
    public void adClicked(AppLovinAd ad) {
        ApplovinPlugin.Callback("AdClicked");
    }

    @Override
    public void adDisplayed(AppLovinAd ad) {
        ApplovinPlugin.Callback("AdDisplayed");
    }

    @Override
    public void adHidden(AppLovinAd ad) {
        ApplovinPlugin.Callback("AdHidden");
    }

    @Override
    public void videoPlaybackBegan(AppLovinAd ad) {
        ApplovinPlugin.Callback("VideoPlaybackBegan");
    }

    @Override
    public void videoPlaybackEnded(AppLovinAd ad, double percentViewed, boolean fullyWatched) {
        ApplovinPlugin.Callback("VideoPlaybackEnded");
    }
}
