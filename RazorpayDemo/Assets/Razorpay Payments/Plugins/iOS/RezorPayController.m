#import "RezorPayController.h"


static NSString *const KEY_ID =@"rzp_test_WgWgxdCwnSipHv";

@interface RezorPayController () <RazorpayPaymentCompletionProtocol, ExternalWalletSelectionProtocol,RazorpayPaymentCompletionProtocolWithData>

@end

@implementation RezorPayController
- (void)viewDidLoad
{
  [super viewDidLoad];
//  [self startPaymentProcess];
}


- (void)startPaymentProcess:(NSString *)paymentData
{
    NSData *data = [paymentData dataUsingEncoding:NSUTF8StringEncoding];
    id json = [NSJSONSerialization JSONObjectWithData:data options:0 error:nil];
    
  self.razorpay = [Razorpay initWithKey:KEY_ID andDelegate:self];
  [self.razorpay setExternalWalletSelectionDelegate:self];
//    UIImage *logo = [UIImage imageNamed:@"logo"];
    NSDictionary *options = @{
          @"amount" : [json objectForKey:@"amount"],
          @"currency" : [json objectForKey:@"currency"],
          @"description" : [json objectForKey:@"description"],
    //      @"image" : logo,
          @"name" : [json objectForKey:@"name"],
    //      @"external" : @{@"wallets" : @[ @"paytm" ]},
          @"prefill" :
              @{@"email" : [json objectForKey:@"email"], @"contact" : [json objectForKey:@"contact"]},
          @"theme" : @{@"color" : @"#3594E2"}
        };
    [self.razorpay open:options];
}


- (void)onPaymentSuccess:(NSString *)payment_id
{
    NSLog(@"#### IOS onPaymentSuccess %@", payment_id);
    const char *successData = [payment_id cStringUsingEncoding:NSUTF8StringEncoding];
    UnitySendMessage("RazorpayManager", "OnPaymentSuccess",successData);
}

- (void)onPaymentError:(int)code description:(NSString *)str
{
    NSLog(@"#### IOS onPaymentError %@", str);
    const char *failData = [str cStringUsingEncoding:NSUTF8StringEncoding];
    UnitySendMessage("RazorpayManager", "OnPaymentError",failData);
}

- (void)onExternalWalletSelected:(NSString *)walletName WithPaymentData:(NSDictionary *)paymentData
{
    NSLog(@"#### onExternalWalletSelected");
}

- (void)showAlertWithTitle:(NSString *)title andMessage:(NSString *)message
{
  NSLog(@"#### showAlertWithTitle");
}

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

@end
