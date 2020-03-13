package developgadget.com.applovin;
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

public class BannerView implements PlatformView, AppLovinAdDisplayListener, AppLovinAdLoadListener,
        AppLovinAdViewEventListener {

    private final MethodChannel channel;
    private final Activity activity;
    private final AppLovinAdSize adSize;
    private final AppLovinAdView adView;
    private LinearLayout Parent;

    BannerView(HashMap args, Context ctx, int id, BinaryMessenger messenger, Activity activity) {
        this.channel = new MethodChannel(messenger, "AdColony/Banner" + id);
        ;
        this.activity = activity;
        this.adSize = AppLovinSdkUtils.isTablet(activity.getApplicationContext()) ?
                AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
        this.adView = new AppLovinAdView(adSize, activity.getApplicationContext());
        this.adView.setId( ViewCompat.generateViewId() );
        this.Parent = new LinearLayout(ctx);
        this.Parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        this.Parent.setOrientation(LinearLayout.HORIZONTAL);
        this.Parent.setGravity(Gravity.CENTER);
        this.Parent.addView(this.adView);
    }

    private void CallBack(final String method) {
        if (this.activity != null) {
            this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    BannerView.this.channel.invokeMethod(method, null);
                }
            });
        }
    }

    @Override
    public View getView() {
        return this.Parent;
    }

    @Override
    public void dispose() {
        if(this.adView != null)
            this.adView.destroy();
    }

    @Override
    public void adOpenedFullscreen(AppLovinAd ad, AppLovinAdView adView) {
        this.CallBack("AdOpenedFullscreen");
    }

    @Override
    public void adClosedFullscreen(AppLovinAd ad, AppLovinAdView adView) {
        this.adView.loadNextAd();
        this.CallBack("AdClosedFullscreen");
    }

    @Override
    public void adLeftApplication(AppLovinAd ad, AppLovinAdView adView) {
        this.CallBack("AdLeftApplication");
    }

    @Override
    public void adFailedToDisplay(AppLovinAd ad, AppLovinAdView adView, AppLovinAdViewDisplayErrorCode code) {
        this.adView.loadNextAd();
        this.CallBack("AdFailedToDisplay");
    }

    @Override
    public void adDisplayed(AppLovinAd ad) {
        this.CallBack("AdDisplayed");
    }

    @Override
    public void adHidden(AppLovinAd ad) {
        this.adView.loadNextAd();
        this.CallBack("AdHidden");
    }

    @Override
    public void adReceived(AppLovinAd ad) {
        this.CallBack("AdReceived");
    }

    @Override
    public void failedToReceiveAd(int errorCode) {
        this.adView.loadNextAd();
        this.CallBack("FailedToReceiveAd");
    }
}
