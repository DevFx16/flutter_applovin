library applovin;

import 'package:flutter/services.dart';

export 'package:applovin/src/interstitial.dart';

class AppLovin {
  static const MethodChannel _channel = const MethodChannel('AppLovin');

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

  static Future<dynamic> isAgeRestrictedUser({bool enable = true}) async {
    try {
      return Future.value(await _channel
          .invokeListMethod('IsAgeRestrictedUser', {'Enable': enable}));
    } catch (e) {
      return Future.error(e);
    }
  }
}
