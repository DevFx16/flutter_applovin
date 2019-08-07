import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

enum MobileAdEvent {
  adClicked,
  adDisplayed,
  adHidden,
  adReceived,
  failedToReceiveAd,
  userRewardVerified,
  userOverQuota,
  userRewardRejected,
  validationRequestFailed,
  userDeclinedToViewAd,
  adOpenedFullscreen,
  adClosedFullscreen,
  adLeftApplication,
  adFailedToDisplay,
  videoPlaybackBegan,
  videoPlaybackEnded,
  onNativeAdsLoaded,
  onNativeAdsFailedToLoad,
  onNativeAdImagesPrecached,
  onNativeAdVideoPreceached,
  onNativeAdImagePrecachingFailed,
  onNativeAdVideoPrecachingFailed
}

class Applovin {
  static const MethodChannel _channel = const MethodChannel('applovin');
  static Map<String, MobileAdEvent> _methodEvent = {
    'adClicked': MobileAdEvent.adClicked,
    'adDisplayed': MobileAdEvent.adDisplayed,
    'adHidden': MobileAdEvent.adHidden,
    'adReceived': MobileAdEvent.adReceived,
    'failedToReceiveAd': MobileAdEvent.failedToReceiveAd,
    'userRewardVerified': MobileAdEvent.userRewardVerified,
    'userOverQuota': MobileAdEvent.userOverQuota,
    'userRewardRejected': MobileAdEvent.userRewardRejected,
    'validationRequestFailed': MobileAdEvent.validationRequestFailed,
    'userDeclinedToViewAd': MobileAdEvent.userDeclinedToViewAd,
    'adOpenedFullscreen': MobileAdEvent.adOpenedFullscreen,
    'adClosedFullscreen': MobileAdEvent.adClosedFullscreen,
    'adLeftApplication': MobileAdEvent.adLeftApplication,
    'adFailedToDisplay': MobileAdEvent.adFailedToDisplay,
    'videoPlaybackBegan': MobileAdEvent.videoPlaybackBegan,
    'videoPlaybackEnded': MobileAdEvent.videoPlaybackEnded,
    'onNativeAdsLoaded': MobileAdEvent.onNativeAdsLoaded,
    'onNativeAdsFailedToLoad': MobileAdEvent.onNativeAdsFailedToLoad,
    'onNativeAdImagesPrecached': MobileAdEvent.onNativeAdImagesPrecached,
    'onNativeAdVideoPreceached': MobileAdEvent.onNativeAdVideoPreceached,
    'onNativeAdImagePrecachingFailed': MobileAdEvent.onNativeAdImagePrecachingFailed,
    'onNativeAdVideoPrecachingFailed': MobileAdEvent.onNativeAdVideoPrecachingFailed
  };

  static Future<dynamic>  init() async {
    _channel.setMethodCallHandler(_handleMethod);
    return await _invokeBooleanMethod('Init');
  }

  static Future _handleMethod(MethodCall call){
    assert(call.arguments is String);
    final MobileAdEvent mobileAdEvent = _methodEvent[call.method];
    return Future.value(null);
  }

}

typedef void MobileAdListener(MobileAdEvent event);

class AdManager {
  AdManager({@required this.listener});

  MobileAdListener listener;

  AdManager get instance => this;

}

Future<bool> _invokeBooleanMethod(String method, [dynamic arguments]) async {
  final bool result = await Applovin._channel.invokeMethod<bool>(
    method,
    arguments,
  );
  return result;
}
