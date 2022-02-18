//
//  RezorPayController.h
//  Unity-iPhone
//

#import <UIKit/UIKit.h>
#import "Razorpay/Razorpay-Swift.h"

NS_ASSUME_NONNULL_BEGIN

@interface RezorPayController : UIViewController
@property (nonatomic, strong) Razorpay *razorpay;
- (void)startPaymentProcess:(NSString *)paymentData;
@end


NS_ASSUME_NONNULL_END
