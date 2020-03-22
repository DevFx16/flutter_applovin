#import "ApplovinPlugin.h"
#if __has_include(<applovin/applovin-Swift.h>)
#import <applovin/applovin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "applovin-Swift.h"
#endif

@implementation ApplovinPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftApplovinPlugin registerWithRegistrar:registrar];
}
@end
