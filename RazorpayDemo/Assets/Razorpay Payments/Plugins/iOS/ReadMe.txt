File 1:
---------------------------------------------------------------------------------------------
RezorPayController.m
---------------------------------------------------------------------------------------------
// Line to edit in above script in Unity
static NSString *const KEY_ID =@"rzp_test_WgWgxdCwnSipHv";
---------------------------------------------------------------------------------------------
*********************************************************************************************



File 2:
---------------------------------------------------------------------------------------------
UnityAppController.mm
---------------------------------------------------------------------------------------------
// Line to add in above script
self.rezorPayController = [[RezorPayController alloc] init]; (line number 280)
---------------------------------------------------------------------------------------------
// Sample Code

[self createUI];
[self preStartUnity];
    
self.rezorPayController = [[RezorPayController alloc] init];
    
// if you wont use keyboard you may comment it out at save some memory
[KeyboardDelegate Initialize];

return YES;
---------------------------------------------------------------------------------------------
*********************************************************************************************



File 3:
---------------------------------------------------------------------------------------------
UnityAppController.h
---------------------------------------------------------------------------------------------
// Line to add in above script
#import "RezorPayController.h" (line number 7)
---------------------------------------------------------------------------------------------
// Sample Code

#import <QuartzCore/CADisplayLink.h>

#include "PluginBase/RenderPluginDelegate.h"

#import "RezorPayController.h"

---------------------------------------------------------------------------------------------
*********************************************************************************************
#import "RezorPayController.h"
---------------------------------------------------------------------------------------------
// Line to add in above script
@property (nonatomic, strong) RezorPayController *rezorPayController; (line number 71)
---------------------------------------------------------------------------------------------
// Sample Code

@property (nonatomic, retain) id                            renderDelegate;
@property (nonatomic, copy)                                 void(^quitHandler)();
@property (nonatomic, strong) RezorPayController *rezorPayController;

@end
---------------------------------------------------------------------------------------------
*********************************************************************************************