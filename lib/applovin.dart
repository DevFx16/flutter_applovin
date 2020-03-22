library applovin;

import 'package:flutter/services.dart';

enum AppLovinAdListener {
  adReceived,
  failedToReceiveAd,
  adDisplayed,
  adHidden,
  adClicked,
  adOpenedFullscreen,
  adClosedFullscreen,
  adLeftApplication,
  adFailedToDisplay
}

typedef AppLovinListener(AppLovinAdListener listener);

class AppLovin {
  static final MethodChannel _channel = MethodChannel('AppLovin');
  static final Map<String, AppLovinAdListener> appLovinAdListener = {
    'adReceived': AppLovinAdListener.adReceived,
    'failedToReceiveAd': AppLovinAdListener.failedToReceiveAd,
    'adDisplayed': AppLovinAdListener.adDisplayed,
    'adHidden': AppLovinAdListener.adHidden,
    'adClicked': AppLovinAdListener.adClicked,
    'adOpenedFullscreen': AppLovinAdListener.adOpenedFullscreen,
    'adClosedFullscreen': AppLovinAdListener.adClosedFullscreen,
    'adLeftApplication': AppLovinAdListener.adLeftApplication,
    'adFailedToDisplay': AppLovinAdListener.adFailedToDisplay,
  };

  static Future<dynamic> init() async {
    try {
      return Future.value(await _channel.invokeListMethod('Init'));
    } catch (e) {
      return Future.error(e);
    }
  }

  static Future<dynamic> hasUserConsent({bool enable = true}) async {
    try {
      return Future.value(await _channel
          .invokeListMethod('HasUserConsent', {'Enable': enable}));
    } catch (e) {
      return Future.error(e);
    }
  }

  static Future<dynamic> requestInterstitial(AppLovinListener listener,
      {bool interstitial = true}) async {
    try {
      _channel.setMethodCallHandler(
          (MethodCall call) async => _handleMethod(call, listener));
      return Future.value(await _channel.invokeListMethod(
          interstitial ? 'RequestInterstitial' : 'RequestReward'));
    } catch (e) {
      return Future.error(e);
    }
  }

  static Future<dynamic> showInterstitial({bool interstitial = true}) async {
    try {
      return Future.value(await _channel
          .invokeListMethod(interstitial ? 'ShowInterstitial' : 'ShowReward'));
    } catch (e) {
      return Future.error(e);
    }
  }

  static Future<dynamic> isAgeRestrictedUser({bool enable = true}) async {
    try {
      return Future.value(await _channel
          .invokeListMethod('IsAgeRestrictedUser', {'Enable': enable}));
    } catch (e) {
      return Future.error(e);
    }
  }

  static Future<dynamic> _handleMethod(
      MethodCall call, AppLovinListener listener) async {
    listener(appLovinAdListener[call.method]);
    return Future<dynamic>.value(null);
  }
}
