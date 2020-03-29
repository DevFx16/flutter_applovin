import 'package:flutter/material.dart';
import 'package:applovin/applovin.dart';
import 'package:applovin/banner.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    AppLovin.init();
    super.initState();
  }

  listener(AppLovinAdListener event, bool isInter) {
    print(event);
    if (event == AppLovinAdListener.adReceived) {
      AppLovin.showInterstitial(interstitial: isInter);
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            RaisedButton(
              onPressed: () => AppLovin.requestInterstitial(
                  (AppLovinAdListener event) => listener(event, true),
                  interstitial: true),
              child: Text('Show Interstitial'),
            ),
            RaisedButton(
              onPressed: () => AppLovin.requestInterstitial(
                  (AppLovinAdListener event) => listener(event, false),
                  interstitial: true),
              child: Text('Show Interstitial Reward'),
            ),
            BannerView((AppLovinAdListener event) => print(event), BannerAdSize.banner),
            BannerView((AppLovinAdListener event) => print(event), BannerAdSize.mrec),
            BannerView((AppLovinAdListener event) => print(event), BannerAdSize.leader),
          ],
        ),
      ),
    );
  }
}
