package com.developgadget.applovin;
import android.content.Context;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import io.flutter.Log;

@SuppressWarnings("ALL")
public class Interstitial implements AppLovinAdLoadListener,
        AppLovinAdDisplayListener, AppLovinAdClickListener, AppLovinAdVideoPlaybackListener {

    private AppLovinInterstitialAdDialog interstitialAd;
    private AppLovinAd currentAd;
    private final Context context;

    Interstitial(Context context) {
        this.context = context;
        this.interstitialAd = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(this.context), this.context);
        this.interstitialAd.setAdLoadListener(this);
        this.interstitialAd.setAdDisplayListener(this);
        this.interstitialAd.setAdClickListener(this);
        this.interstitialAd.setAdVideoPlaybackListener(this);
    }


    public void Request() {
        if (this.interstitialAd != null && this.context != null && this.currentAd == null)
            AppLovinSdk.getInstance(this.context).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, this);
    }

    public void Show() {
        if (this.currentAd != null)
            this.interstitialAd.showAndRender(this.currentAd);
        else
            Log.d("AppLovin", "Ad not load instance null");
    }

    @Override
    public void adReceived(AppLovinAd ad) {
        this.currentAd = ad;
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
        this.currentAd = null;
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
