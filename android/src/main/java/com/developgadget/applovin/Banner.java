package com.developgadget.applovin;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewDisplayErrorCode;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdkUtils;

import java.util.HashMap;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;

public class Banner implements AppLovinAdDisplayListener, AppLovinAdLoadListener, AppLovinAdViewEventListener {

    @Override
    public void adOpenedFullscreen(AppLovinAd ad, AppLovinAdView adView) {
        ApplovinPlugin.Callback("AdOpenedFullscreen");
    }

    @Override
    public void adClosedFullscreen(AppLovinAd ad, AppLovinAdView adView) {
        //this.adView.loadNextAd();
        ApplovinPlugin.Callback("AdClosedFullscreen");
    }

    @Override
    public void adLeftApplication(AppLovinAd ad, AppLovinAdView adView) {
        ApplovinPlugin.Callback("AdLeftApplication");
    }

    @Override
    public void adFailedToDisplay(AppLovinAd ad, AppLovinAdView adView, AppLovinAdViewDisplayErrorCode code) {
        //this.adView.loadNextAd();
        ApplovinPlugin.Callback("AdFailedToDisplay error sdk code " + code.ordinal());
    }

    @Override
    public void adDisplayed(AppLovinAd ad) {
        ApplovinPlugin.Callback("AdDisplayed");
    }

    @Override
    public void adHidden(AppLovinAd ad) {
        //this.adView.loadNextAd();
        ApplovinPlugin.Callback("AdHidden");
    }

    @Override
    public void adReceived(AppLovinAd ad) {
        ApplovinPlugin.Callback("AdReceived");
    }

    @Override
    public void failedToReceiveAd(int errorCode) {
        //this.adView.loadNextAd();
        ApplovinPlugin.Callback("FailedToReceiveAd error code " + errorCode);
    }
}
