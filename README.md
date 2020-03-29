# AppLovin

**Note: Currently only Android platform is supported.**

**Note: only show interstitial and video rewarded ads.**

## Getting Started

1. Initialization

Call `Applovin.init();` during app initialization.

```dart
  Applovin.init();
```

Add inside the `<application>` tag

```xml
<meta-data android:name="applovin.sdk.key"
    android:value="xxxx"  />
```

### 2. Request Interstitial Ad and Rewarded Video Ad

```dart
AppLovin.requestInterstitial((AppLovinAdListener event) => listener(event, false), interstitial: true)
```

### 3. Show Interstitial Ad and Rewarded Video Ad

```dart
listener(AppLovinAdListener event){
    if(event == AppLovinAdListener.adReceived) AppLovin.showInterstitial(interstitial: true);
  }
```

### 4. Show Banner Widget

```dart
BannerView((AppLovinAdListener event) => print(event), BannerAdSize.banner),
BannerView((AppLovinAdListener event) => print(event), BannerAdSize.mrec),
BannerView((AppLovinAdListener event) => print(event), BannerAdSize.leader),
```

**true for interstitial false for Rewarded**

### Events

```dart
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
```

### Banners Sizes 

```dart
enum BannerAdSize {
  banner,
  mrec,
  leader,
}
```

## Future Work
Implement for iOS platform, NativeAds.