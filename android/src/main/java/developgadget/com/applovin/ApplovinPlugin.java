package developgadget.com.applovin;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;

import java.util.List;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * ApplovinPlugin
 */
public class ApplovinPlugin implements MethodCallHandler {

    private final Registrar registrar;
    private final MethodChannel channel;
    private final Events listeners;
    static AppLovinAd Ad;
    static List<AppLovinNativeAd> NativeAds;

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "applovin");
        channel.setMethodCallHandler(new ApplovinPlugin(registrar, channel));
    }

    public ApplovinPlugin(Registrar registrar, MethodChannel channel) {
        this.registrar = registrar;
        this.channel = channel;
        this.listeners = new Events(this.registrar, this.channel);
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        switch (call.method) {
            case "Init":
                AppLovinSdk.initializeSdk(this.registrar.context());
                result.success(Boolean.TRUE);
                break;
            case "HasUserConsent":
                AppLovinPrivacySettings.setHasUserConsent((Boolean) call.argument("Enable"),
                        this.registrar.context());
                result.success(Boolean.TRUE);
                break;
            case "IsAgeRestrictedUser":
                AppLovinPrivacySettings.setIsAgeRestrictedUser((Boolean) call.argument("Enable"),
                        this.registrar.context());
                result.success(Boolean.TRUE);
                break;
            case "LoadInterstitial":
                AppLovinSdk.getInstance(this.registrar.context()).getAdService().
                        loadNextAd(AppLovinAdSize.INTERSTITIAL, this.listeners);
                result.success(Boolean.TRUE);
                break;
            case "ShowInterstitial":
                callShowInterstitial(call, result);
                break;
            default:
                result.notImplemented();
        }
        return;
    }

    private void callShowInterstitial(MethodCall call, Result result) {
        if(Ad != null){
            AppLovinInterstitialAdDialog interstitialAd = AppLovinInterstitialAd.create(
                    AppLovinSdk.getInstance(this.registrar.context()), this.registrar.context());
            interstitialAd.setAdDisplayListener(this.listeners);
            interstitialAd.setAdClickListener( this.listeners );
            interstitialAd.setAdVideoPlaybackListener(this.listeners);
            interstitialAd.showAndRender(Ad);
        }
        result.success(Boolean.TRUE);
    }

}
