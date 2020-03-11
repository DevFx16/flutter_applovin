package developgadget.com.applovin;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

import io.flutter.Log;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

@SuppressWarnings("ALL")
public class Interstitial implements MethodChannel.MethodCallHandler, AppLovinAdLoadListener,
        AppLovinAdDisplayListener, AppLovinAdClickListener, AppLovinAdVideoPlaybackListener {

    private final MethodChannel channel;
    private final PluginRegistry.Registrar registrar;
    private AppLovinInterstitialAdDialog interstitialAd;

    Interstitial(PluginRegistry.Registrar registrar, MethodChannel channel) {
        this.registrar = registrar;
        this.channel = channel;
    }

    private void Request() {
        if (this.interstitialAd == null && this.registrar.activity() != null) {
            this.interstitialAd = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(
                    this.registrar.activity()), this.registrar.context());
            this.interstitialAd.setAdLoadListener(this);
            this.interstitialAd.setAdDisplayListener(this);
            this.interstitialAd.setAdClickListener(this);
            this.interstitialAd.setAdVideoPlaybackListener(this);
        }
    }

    private void Show() {
        if (this.interstitialAd != null && this.interstitialAd.isAdReadyToDisplay())
            this.interstitialAd.show();
        else
            Log.d("AppLovin", "Ad not load");
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        switch (call.method) {
            case "Load":
                this.Request();
                result.success(Boolean.TRUE);
                break;
            case "Show":
                this.Show();
                result.success(Boolean.TRUE);
                break;
        }
    }

    private void CallBack(final String method) {
        if (this.registrar.activity() != null) {
            this.registrar.activity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Interstitial.this.channel.invokeMethod(method, null);
                }
            });
        }
    }

    @Override
    public void adReceived(AppLovinAd ad) {
        CallBack("AdReceived");
    }

    @Override
    public void failedToReceiveAd(int errorCode) {
        CallBack("FailedToReceiveAd");
    }

    @Override
    public void adClicked(AppLovinAd ad) {
        CallBack("AdClicked");
    }

    @Override
    public void adDisplayed(AppLovinAd ad) {
        CallBack("AdDisplayed");
    }

    @Override
    public void adHidden(AppLovinAd ad) {
        CallBack("AdHidden");
    }

    @Override
    public void videoPlaybackBegan(AppLovinAd ad) {
        CallBack("VideoPlaybackBegan");
    }

    @Override
    public void videoPlaybackEnded(AppLovinAd ad, double percentViewed, boolean fullyWatched) {
        CallBack("VideoPlaybackEnded");
    }
}
