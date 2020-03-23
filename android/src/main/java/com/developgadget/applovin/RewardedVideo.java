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
            Log.i("AppLovin", "Ad not load");
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
        Log.i("AppLovin", "ValidationRequestFailed error sdk code " + errorCode);
        ApplovinPlugin.getInstance().Callback("ValidationRequestFailed");
    }

    @Override
    public void userDeclinedToViewAd(AppLovinAd ad) {
        ApplovinPlugin.getInstance().Callback("UserDeclinedToViewAd");
    }

    @Override
    public void adReceived(AppLovinAd ad) {
        ApplovinPlugin.getInstance().Callback("AdReceived");
    }

    @Override
    public void failedToReceiveAd(int errorCode) {
        Log.i("AppLovin","FailedToReceiveAd error sdk code " + errorCode);
        ApplovinPlugin.getInstance().Callback("FailedToReceiveAd");
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

    @Override
    public void videoPlaybackBegan(AppLovinAd ad) {
        ApplovinPlugin.getInstance().Callback("VideoPlaybackBegan");
    }

    @Override
    public void videoPlaybackEnded(AppLovinAd ad, double percentViewed, boolean fullyWatched) {
        ApplovinPlugin.getInstance().Callback("VideoPlaybackEnded");
    }
}
