package com.developgadget.applovin;

import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

import java.util.Map;

import io.flutter.Log;

public class RewardedVideo implements AppLovinAdLoadListener, AppLovinAdRewardListener,
        AppLovinAdVideoPlaybackListener, AppLovinAdDisplayListener, AppLovinAdClickListener {

    private AppLovinIncentivizedInterstitial RewardedAd;

    public RewardedVideo() {
        if (ApplovinPlugin.getInstance().activity != null)
            this.RewardedAd = AppLovinIncentivizedInterstitial.create(ApplovinPlugin.getInstance().activity);
    }

    public void Show() {
        try {
            if (this.RewardedAd != null && this.RewardedAd.isAdReadyToDisplay() && ApplovinPlugin.getInstance().activity != null)
                this.RewardedAd.show(ApplovinPlugin.getInstance().activity, this, this,
                        this, this);
        } catch (Exception e) {
            Log.e("AppLovin", e.toString());
        }
    }

    public void Request() {
        this.RewardedAd.preload(this);
    }

    @Override
    public void adReceived(AppLovinAd ad) {
        ApplovinPlugin.getInstance().Callback("AdReceived");
    }

    @Override
    public void failedToReceiveAd(int errorCode) {
        Log.e("AppLovin", "FailedToReceiveAd sdk error " + errorCode);
        ApplovinPlugin.getInstance().Callback("FailedToReceiveAd");
    }

    @Override
    public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
        ApplovinPlugin.getInstance().Callback("UserRewardVerified");
    }

    @Override
    public void userOverQuota(AppLovinAd ad, Map<String, String> response) {
        ApplovinPlugin.getInstance().Callback("UserOverQuota");
    }

    @Override
    public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {
        ApplovinPlugin.getInstance().Callback("UserRewardRejected");
    }

    @Override
    public void validationRequestFailed(AppLovinAd ad, int errorCode) {
        Log.e("AppLovin", "ValidationRequestFailed sdk error " + errorCode);
        ApplovinPlugin.getInstance().Callback("ValidationRequestFailed");
    }

    @Override
    public void userDeclinedToViewAd(AppLovinAd ad) {
        ApplovinPlugin.getInstance().Callback("UserDeclinedToViewAd");
    }

    @Override
    public void videoPlaybackBegan(AppLovinAd ad) {
        ApplovinPlugin.getInstance().Callback("VideoPlaybackBegan");
    }

    @Override
    public void videoPlaybackEnded(AppLovinAd ad, double percentViewed, boolean fullyWatched) {
        ApplovinPlugin.getInstance().Callback("VideoPlaybackEnded");
    }

    @Override
    public void adClicked(AppLovinAd ad) {
        ApplovinPlugin.getInstance().Callback("AdClicked");
    }

    @Override
    public void adDisplayed(AppLovinAd ad) {
        ApplovinPlugin.getInstance().Callback("AdDisplayed");
    }

    @Override
    public void adHidden(AppLovinAd ad) {
        ApplovinPlugin.getInstance().Callback("AdHidden");
    }
}
