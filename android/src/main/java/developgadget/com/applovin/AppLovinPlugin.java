package developgadget.com.applovin;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

@SuppressWarnings("NullableProblems")
public class AppLovinPlugin implements MethodCallHandler {

    private final Registrar registrar;

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "AppLovin");
        final MethodChannel channelInter = new MethodChannel(registrar.messenger(), "AppLovin/Interstitial");
        final MethodChannel channelReward = new MethodChannel(registrar.messenger(), "AppLovin/RewardedVideo");
        channel.setMethodCallHandler(new AppLovinPlugin(registrar));
        channelInter.setMethodCallHandler(new Interstitial(registrar, channelInter));
        channelInter.setMethodCallHandler(new RewardedVideo(channelReward, registrar));
        registrar.platformViewRegistry().registerViewFactory("/BannerAd", new BannerFactory(registrar.messenger(), registrar.activity()));
    }

    private AppLovinPlugin(Registrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        try {
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
                default:
                    result.notImplemented();
            }
        }catch (Exception err){
            result.notImplemented();
        }
    }
}
