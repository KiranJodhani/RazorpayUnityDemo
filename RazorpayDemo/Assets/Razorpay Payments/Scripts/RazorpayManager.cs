using System;
using System.Runtime.InteropServices;
using UnityEngine;

// Put this script on RazorpayManager Gameobject.
public class RazorpayManager : MonoBehaviour
{
    public PaymentData paymentDataInstance;

#if UNITY_IOS
    [DllImport("__Internal")]
    private static extern void LetThePaymentBegins(string Paymentdata);
#endif

    public void OnButtonClicked()
    {
        string paymentData = JsonUtility.ToJson(paymentDataInstance, true);
        print("#### paymentData : " + paymentData);
#if UNITY_ANDROID
        print("#### CALLING Android NativeActivity");
        ShowRazorPayPaymentModel(paymentData);
#endif

#if UNITY_IOS
        print("#### CALLING iOS NativeActivity");
        LetThePaymentBegins(paymentData);
#endif
    }

    public void ShowRazorPayPaymentModel(string Data)
    {
        AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
        AndroidJavaObject plugin = new AndroidJavaObject("com.razorpay.payments.java.PaymentActivity");
        plugin.Call("LetThePaymentBegins", activity, Data);
        print("#### CALLING AndroidJavaObject DONE");
    }

    /// <summary>
    /// Callback from Razorpay. DO NOT EDIT FUNCTION NAMES.
    /// </summary>
    /// <param name="razorpayPaymentID"></param>
    public void OnPaymentSuccess(string razorpayPaymentID)
    {
        print("#### PaymentCallBack Success : " + razorpayPaymentID);
    }
    /// <summary>
    /// Callback from Razorpay. DO NOT EDIT FUNCTION NAMES.
    /// </summary>
    /// <param name="response"></param>
    public void OnPaymentError(string response)
    {
        print("#### PaymentCallBack Error : " + response);
    }
}

[Serializable]
public class PaymentData
{
    public string key;
    public string name;
    public string description;
    public string image;
    public string currency;
    public string amount;
    public string email;
    public string contact;
}

// {
//    "key": "rzp_live_ILgsfZCZoFIKMb"
//    "name": "Razorpay",                                               TODO: Merchant Name
//    "description": "Payment",                                         TODO: Merchant/Payment Description
//    "image": "https://s3.amazonaws.com/rzp-mobile/images/rzp.png",    TODO: Merchant Image URL
//    "currency": "INR",                                                TODO: Payment currency
//    "amount": "100",                                                  TODO: Amount in smallest currency e.g.- paisa in INR
//    "email": "user@email.com",                                        TODO: User/Client email
//    "contact": "+919999999999"                                        TODO: User/Client contact number
// }