package com.developgadget.applovin;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;

import io.flutter.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.common.StandardMethodCodec;

public class ApplovinPlugin implements FlutterPlugin, MethodCallHandler {
    private static ApplovinPlugin instance;
    private static Interstitial instanceInter;
    private static RewardedVideo instanceReward;
    private static Context context;
    private static MethodChannel channel;

    public static ApplovinPlugin getInstance() {
        return instance;
    }

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        this.onAttachedToEngine(flutterPluginBinding.getApplicationContext(), flutterPluginBinding.getBinaryMessenger());
    }

    public static void registerWith(Registrar registrar) {
        if (instance == null) {
            instance = new ApplovinPlugin(registrar.activeContext());
        }
        instance.onAttachedToEngine(registrar.context(), registrar.messenger());
    }

    public void onAttachedToEngine(Context applicationContext, BinaryMessenger messenger) {
        if (channel != null) {
            return;
        }
        instance = new ApplovinPlugin(applicationContext);
        Log.i("AppLovin Plugin", "onAttachedToEngine");
        this.context = applicationContext;
        channel = new MethodChannel(messenger, "AppLovin", StandardMethodCodec.INSTANCE);
        channel.setMethodCallHandler(this);
    }

    public ApplovinPlugin(Context context){
        if(this.instanceInter == null){
            instance.instanceInter = new Interstitial(context);
            instance.instanceReward = new RewardedVideo(context);
            Log.i("AppLovin Plugin", "Instances created");
        }
    }

    public ApplovinPlugin(){}

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
                    if (call.argument("IsInter"))
                        instanceInter.Show();
                    else
                        instanceReward.Show();
                    result.success(Boolean.TRUE);
                    break;
                case "RequestInterstitial":
                    if (call.argument("IsInter"))
                        instanceInter.Request();
                    else
                        instanceReward.Request();
                    result.success(Boolean.TRUE);
                    break;
                default:
                    result.notImplemented();
            }
        } catch (Exception err) {
            Log.i("Method error", err.toString());
            result.notImplemented();
        }
    }

    static public void Callback(final String method) {
        if (instance.context != null && instance.channel != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    instance.channel.invokeMethod(method, null);
                }
            }).start();
        }else{
            Log.i("AppLovin", "instance method channel not created");
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        this.context = null;
        this.channel.setMethodCallHandler(null);
        this.channel = null;
    }
}