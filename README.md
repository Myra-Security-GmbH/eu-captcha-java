# EU CAPTCHA Java Clients

This repository contains three Java client variants for the EU CAPTCHA verification API, generated
using `openapi-generator-cli` version 7.18.0.

## Variants

### java-resttemplate

**Use this when:** your application is a standard Spring MVC (servlet-based) application using
Spring 6 without reactive programming. This variant uses `RestTemplate` for synchronous HTTP.

- Spring Framework 6 / Java 17
- Synchronous API: `VerifyResponse verifyClientToken(VerifyRequest)`
- Supports configurable retry with exponential backoff on 5xx / 429 responses
- [README](java-resttemplate/README.md)

### java-webflux-boot2

**Use this when:** you want a non-blocking, reactive client for use in a non-Boot application,
or you are integrating into a Spring WebFlux pipeline without Spring Boot 3.

- Spring WebFlux (`WebClient`) / Java 8+
- Reactive API: `Mono<VerifyResponse> verifyClientToken(VerifyRequest)`
- [README](java-webflux-boot2/README.md)

### java-webflux-boot3

**Use this when:** your application is built on Spring Boot 3 with Jakarta EE 10 (the Jakarta
namespace instead of the old `javax` namespace). Uses Spring Boot's WebFlux starter.

- Spring Boot 3 / Jakarta EE / Java 17
- Reactive API: `Mono<VerifyResponse> verifyClientToken(VerifyRequest)`
- Uses `jakarta.*` annotations throughout (not `javax.*`)
- [README](java-webflux-boot3/README.md)

## Quick decision guide

| Scenario | Recommended variant |
|---|---|
| Spring Boot 3 (Jakarta EE) | `java-webflux-boot3` |
| Spring Boot 2 / Spring 5, reactive | `java-webflux-boot2` |
| Spring MVC (servlet stack), Spring 6 | `java-resttemplate` |

## Regenerating

The clients were generated with the following `openapi-generator-cli` commands:

```bash
# java-resttemplate
openapi-generator-cli generate -i openapi.yaml -g java \
  --additional-properties=packageName=eucaptcha,groupId=com.myrasec,\
invokerPackage=com.myrasec.client,apiPackage=com.myrasec.client.api,\
modelPackage=com.myrasec.client.model,library=resttemplate

# java-webflux-boot2
openapi-generator-cli generate -i openapi.yaml -g java \
  --additional-properties=packageName=eucaptcha,groupId=com.myrasec,\
invokerPackage=com.myrasec.client,apiPackage=com.myrasec.client.api,\
modelPackage=com.myrasec.client.model,library=webclient

# java-webflux-boot3
openapi-generator-cli generate -i openapi.yaml -g java \
  --additional-properties=packageName=eucaptcha,groupId=com.myrasec,\
invokerPackage=com.myrasec.client,apiPackage=com.myrasec.client.api,\
modelPackage=com.myrasec.client.model,library=webclient,useSpringBoot3=true,useJakartaEe=true
```
