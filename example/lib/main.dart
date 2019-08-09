import 'package:flutter/material.dart';
import 'dart:async';
import 'package:applovin/applovin.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  static AdManager _adManager = AdManager(listener: (MobileAdEvent event){
    if(event == MobileAdEvent.adReceived) _adManager.showInterstitial();
  });

  @override
  void initState() {
    super.initState();
    Applovin.init();
    _adManager.loadInterstitial();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: $_platformVersion\n'),
        ),
      ),
    );
  }
}
