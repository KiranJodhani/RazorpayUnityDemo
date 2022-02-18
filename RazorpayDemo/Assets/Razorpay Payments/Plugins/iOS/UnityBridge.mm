//** This import is required in order for Swift functions & properties to be exposed to this class
#include "Bridging-Header.h"
//#import "ViewController.h"
#import "RezorPayController.h"
#import "UnityAppController.h"
//#include <string>


#pragma mark - C interface

extern "C" {
    
#pragma mark - Functions

    void LetThePaymentBegins(char* paymentData)
    {
        NSString* string = [NSString stringWithFormat:@"%s" , paymentData];
        NSLog(@"#### %s", paymentData);
        UnityAppController *appDelegate = (UnityAppController *)[[UIApplication sharedApplication] delegate];
        [appDelegate.rezorPayController startPaymentProcess:string];
    }
    
}

