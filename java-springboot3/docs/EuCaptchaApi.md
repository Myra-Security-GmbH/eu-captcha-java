# EuCaptchaApi

All URIs are relative to *https://api.eu-captcha.eu/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**verifyClientToken**](EuCaptchaApi.md#verifyClientToken) | **POST** /verify | Verify submitted client token |



## verifyClientToken

> VerifyResponse verifyClientToken(verifyRequest)

Verify submitted client token

The client of the end-user have to make certain calculations as a so-called **proof-of-work** mechanism. The end point determines whether those calculations have been performed correctly. Also if the token has been used before, among other validation functions.  As a result the body returns a JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; as true or false (boolean). 

### Example

```java
// Import classes:
import com.myrasec.client.ApiClient;
import com.myrasec.client.ApiException;
import com.myrasec.client.Configuration;
import com.myrasec.client.models.*;
import com.myrasec.client.api.EuCaptchaApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.eu-captcha.eu/v1");

        EuCaptchaApi apiInstance = new EuCaptchaApi(defaultClient);
        VerifyRequest verifyRequest = new VerifyRequest(); // VerifyRequest | 
        try {
            VerifyResponse result = apiInstance.verifyClientToken(verifyRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling EuCaptchaApi#verifyClientToken");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **verifyRequest** | [**VerifyRequest**](VerifyRequest.md)|  | |

### Return type

[**VerifyResponse**](VerifyResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; with the value 0 (&#x60;&#x60;false&#x60;&#x60;) or 1 (&#x60;&#x60;true&#x60;&#x60;). |  -  |

