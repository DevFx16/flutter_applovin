package developgadget.com.applovin;

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

public class RewardedVideo implements MethodChannel.MethodCallHandler, AppLovinAdRewardListener,
        AppLovinAdLoadListener, AppLovinAdVideoPlaybackListener, AppLovinAdDisplayListener, AppLovinAdClickListener {

    private AppLovinIncentivizedInterstitial Rewarded;
    private final MethodChannel channel;

    RewardedVideo(MethodChannel channel, PluginRegistry.Registrar registrar) {
        this.channel = channel;
        this.registrar = registrar;
        if (registrar != null)
            this.Rewarded = AppLovinIncentivizedInterstitial.create(registrar.context());
    }

    private final PluginRegistry.Registrar registrar;

    private void CallBack(final String method) {
        if (this.registrar.activity() != null) {
            this.registrar.activity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    RewardedVideo.this.channel.invokeMethod(method, null);
                }
            });
        }
    }

    private void Request() {
        if (this.Rewarded == null && this.registrar.activity() != null) {
            this.Rewarded.preload(this);
        }
    }

    private void Show() {
        if (this.Rewarded != null && this.Rewarded.isAdReadyToDisplay())
            this.Rewarded.show(this.registrar.context(), this, this, this);
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

    @Override
    public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
        this.CallBack("UserRewardVerified");
    }

    @Override
    public void userOverQuota(AppLovinAd ad, Map<String, String> response) {
        this.CallBack("UserOverQuota");
    }

    @Override
    public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {
        this.CallBack("UserRewardRejected");
    }

    @Override
    public void validationRequestFailed(AppLovinAd ad, int errorCode) {
        this.CallBack("ValidationRequestFailed");
    }

    @Override
    public void userDeclinedToViewAd(AppLovinAd ad) {
        this.CallBack("UserDeclinedToViewAd");
    }

    @Override
    public void adReceived(AppLovinAd ad) {
        this.CallBack("AdReceived");
    }

    @Override
    public void failedToReceiveAd(int errorCode) {
        this.CallBack("FailedToReceiveAd");
    }

    @Override
    public void adClicked(AppLovinAd ad) {
        this.CallBack("AdClicked");
    }

    @Override
    public void adDisplayed(AppLovinAd ad) {
        this.CallBack("AdDisplayed");
    }

    @Override
    public void adHidden(AppLovinAd ad) {
        this.CallBack("AdHidden");
    }

    @Override
    public void videoPlaybackBegan(AppLovinAd ad) {
        this.CallBack("VideoPlaybackBegan");
    }

    @Override
    public void videoPlaybackEnded(AppLovinAd ad, double percentViewed, boolean fullyWatched) {
        this.CallBack("VideoPlaybackEnded");
    }
}
