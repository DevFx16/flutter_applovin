package developgadget.com.applovin;

import android.app.Activity;
import android.content.Context;
import java.util.HashMap;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class BannerFactory extends PlatformViewFactory {

    final private BinaryMessenger messenger;
    final private Activity activity;

    public BannerFactory(BinaryMessenger messenger, Activity activity) {
        super(StandardMessageCodec.INSTANCE);
        this.activity = activity;
        this.messenger = messenger;
    }

    @Override
    public PlatformView create(Context context, int viewId, Object args) {
        return new BannerView((HashMap) args, context, viewId, messenger, activity);
    }
}
