package com.developgadget.applovin;

import android.content.Context;

import androidx.annotation.NonNull;

import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class ApplovinPlugin implements FlutterPlugin, MethodCallHandler {
    private static ApplovinPlugin instance;
    private static Interstitial instanceInter;
    private static RewardedVideo instanceReward;
    private static Registrar registrar;
    private Context context;
    private MethodChannel channel;
    private Object initializationLock = new Object();

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        onAttachedToEngine(flutterPluginBinding.getApplicationContext(), flutterPluginBinding.getBinaryMessenger());
    }

    public static void registerWith(Registrar registrar) {
        if (instance == null) {
            instance = new ApplovinPlugin();
            instanceInter = new Interstitial(registrar.context());
            instanceReward = new RewardedVideo(registrar.context());
            instance.registrar = registrar;
        }
        instance.onAttachedToEngine(registrar.context(), registrar.messenger());
    }

    public void onAttachedToEngine(Context applicationContext, BinaryMessenger messenger) {
            if (channel != null) {
                return;
            }
            this.context = applicationContext;
            channel = new MethodChannel(messenger, "AppLovin");
            channel.setMethodCallHandler(this);
        }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        try {
            switch (call.method) {
                case "Init":
                    AppLovinSdk.initializeSdk(context);
                    result.success(Boolean.TRUE);
                    break;
                case "HasUserConsent":
                    AppLovinPrivacySettings.setHasUserConsent((Boolean) call.argument("Enable"), context);
                    result.success(Boolean.TRUE);
                    break;
                case "IsAgeRestrictedUser":
                    AppLovinPrivacySettings.setIsAgeRestrictedUser((Boolean) call.argument("Enable"), context);
                    result.success(Boolean.TRUE);
                    break;
                case "ShowInterstitial":
                    instanceInter.Show();
                    result.success(Boolean.TRUE);
                    break;
                case "RequestInterstitial":
                    instanceInter.Request();
                    result.success(Boolean.TRUE);
                    break;
                case "ShowReward":
                    instanceReward.Show();
                    result.success(Boolean.TRUE);
                    break;
                case "RequestReward":
                    instanceReward.Request();
                    result.success(Boolean.TRUE);
                    break;
                default:
                    result.notImplemented();
            }
        } catch (Exception err) {
            result.notImplemented();
        }
    }

    static public void Callback(final String method) {
        if (instance.registrar != null) {
            instance.registrar.activity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    instance.channel.invokeMethod(method, null);
                }
            });
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        context = null;
        channel.setMethodCallHandler(null);
        channel = null;
    }
}