import 'package:flutter/material.dart';
import 'package:applovin/applovin.dart';

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

  listener(AppLovinAdListener event, bool isInter){
    if(event == AppLovinAdListener.adReceived){
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
            RaisedButton(onPressed: () => AppLovin.requestInterstitial((AppLovinAdListener event) => listener(event, true), interstitial: true), child: Text('Show Interstitial'),),
            RaisedButton(onPressed: () => AppLovin.requestInterstitial((AppLovinAdListener event) => listener(event, false), interstitial: true), child: Text('Show Interstitial Reward'),),
          ],
        ),
      ),
    );
  }
}
