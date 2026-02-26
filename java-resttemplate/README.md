# eu-captcha-java-resttemplate

EU CAPTCHA verification client for Spring MVC applications (synchronous, servlet-based).

For full documentation see **[docs.eu-captcha.eu](https://docs.eu-captcha.eu/)**.
You can [sign up for free](https://app.eu-captcha.eu/user-registration) to get a sitekey.

**Requirements:** Java 17, Spring Framework 6

## Installation

### Maven

```xml
<dependency>
  <groupId>com.myrasec</groupId>
  <artifactId>eu-captcha-java-resttemplate</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle

```groovy
implementation "com.myrasec:eu-captcha-java-resttemplate:1.0.0"
```

### Building from source

```shell
mvn clean install
```

## Getting Started

```java
import com.myrasec.client.ApiClient;
import com.myrasec.client.api.EuCaptchaApi;
import com.myrasec.client.model.VerifyRequest;
import com.myrasec.client.model.VerifyResponse;
import org.springframework.web.client.RestClientException;

ApiClient client = new ApiClient();
// client.setMaxAttemptsForRetry(3); // optional: retry up to 3 times on 5xx / 429

EuCaptchaApi api = new EuCaptchaApi(client);

VerifyRequest request = new VerifyRequest()
        .sitekey("YOUR_SITEKEY")
        .secret("YOUR_SECRET")
        .clientIp(clientIp)              // real end-user IP — see below
        .clientToken(euCaptchaToken)     // value of the "eu-captcha-response" POST field
        .clientUserAgent(userAgent);     // value of the User-Agent request header

try {
    VerifyResponse response = api.verifyClientToken(request);

    if (response.isTrainingMode()) {
        // Training mode: real validation was not performed — always allow.
        // Occurs when the sitekey does not exist, the secret is wrong,
        // or the sitekey is configured with train=true.
        allowAccess();
    } else if (Boolean.TRUE.equals(response.getSuccess())) {
        allowAccess();
    } else {
        denyAccess();
    }
} catch (RestClientException e) {
    // Network error or unexpected HTTP status.
    // Decide your fallback policy: allow or deny.
    log.error("EU CAPTCHA verification failed: {}", e.getMessage());
}
```

## Extracting the real client IP

Always pass the **real end-user IP address**, not the IP of your reverse proxy or CDN.

```java
// Without a proxy
String clientIp = httpServletRequest.getRemoteAddr();

// Behind a reverse proxy or CDN (use the header your provider documents)
String forwarded = httpServletRequest.getHeader("X-Forwarded-For");
if (forwarded != null && !forwarded.isBlank()) {
    // X-Forwarded-For is a comma-separated list; the leftmost entry is the client IP
    clientIp = forwarded.split(",")[0].trim();
}
```

## Security

- **Do not enable `ApiClient.setDebugging(true)` in production.** Debug mode logs the full
  JSON request body. While `VerifyRequest.toString()` redacts the secret, the raw HTTP payload
  sent over the wire is not redacted.
- Store the secret key in an environment variable or a secrets manager, never in source code.
- `ApiClient` is thread-safe. Create one instance and share it across your application — do not
  create a new instance per request.

## API reference

All URIs are relative to `https://api.eu-captcha.eu/v1`.

| Class | Method | HTTP request | Description |
|-------|--------|--------------|-------------|
| `EuCaptchaApi` | `verifyClientToken` | `POST /verify` | Verify a client token |

### VerifyRequest fields

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| `sitekey` | String | yes | Public sitekey from the EU CAPTCHA Dashboard |
| `secret` | String | yes | Secret key associated with the sitekey |
| `clientIp` | String | yes | IPv4 or IPv6 address of the end user |
| `clientToken` | String | yes | Token from `verify.js` (may be empty string) |
| `clientUserAgent` | String | yes | `User-Agent` header from the end-user request |

### VerifyResponse fields

| Field | Type | Description |
|-------|------|-------------|
| `success` | Boolean | `true` if the CAPTCHA was solved correctly |
| `train` | Boolean | `true` if training mode was active (see `isTrainingMode()`) |

## Endpoints do not require authorization.
