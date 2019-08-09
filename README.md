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

**Only instance of AdManager**

```dart
static AdManager _adManager = AdManager(listener: listener);
_adManager.loadInterstitial();
```

### 3. Show Interstitial Ad and Rewarded Video Ad

```dart
listener(MobileAdEvent event){
    if(event == MobileAdEvent.adReceived) _adManager.showInterstitial();
  }
```

### Events

```dart
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
```
## Future Work
Implement for iOS platform, Banner Ads, NativeAds,