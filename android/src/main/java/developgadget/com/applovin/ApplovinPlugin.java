package developgadget.com.applovin;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** ApplovinPlugin */
public class ApplovinPlugin implements MethodCallHandler {
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "applovin");
    channel.setMethodCallHandler(new ApplovinPlugin());
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
  }
  
}
