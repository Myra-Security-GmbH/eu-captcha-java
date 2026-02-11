package com.myrasec.client.api;

import com.myrasec.client.ApiClient;

import com.myrasec.client.model.VerifyRequest;
import com.myrasec.client.model.VerifyResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2026-02-11T09:46:30.132777091+01:00[Europe/Berlin]", comments = "Generator version: 7.18.0")
public class EuCaptchaApi {
    private ApiClient apiClient;

    public EuCaptchaApi() {
        this(new ApiClient());
    }

    public EuCaptchaApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Verify submitted client token
     * The client of the end-user have to make certain calculations as a so-called **proof-of-work** mechanism. The end point determines whether those calculations have been performed correctly. Also if the token has been used before, among other validation functions.  As a result the body returns a JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; as true or false (boolean). 
     * <p><b>200</b> - A JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; with the value 0 (&#x60;&#x60;false&#x60;&#x60;) or 1 (&#x60;&#x60;true&#x60;&#x60;).
     * @param verifyRequest The verifyRequest parameter
     * @return VerifyResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec verifyClientTokenRequestCreation(@jakarta.annotation.Nonnull VerifyRequest verifyRequest) throws WebClientResponseException {
        Object postBody = verifyRequest;
        // verify the required parameter 'verifyRequest' is set
        if (verifyRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'verifyRequest' when calling verifyClientToken", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<VerifyResponse> localVarReturnType = new ParameterizedTypeReference<VerifyResponse>() {};
        return apiClient.invokeAPI("/verify", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Verify submitted client token
     * The client of the end-user have to make certain calculations as a so-called **proof-of-work** mechanism. The end point determines whether those calculations have been performed correctly. Also if the token has been used before, among other validation functions.  As a result the body returns a JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; as true or false (boolean). 
     * <p><b>200</b> - A JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; with the value 0 (&#x60;&#x60;false&#x60;&#x60;) or 1 (&#x60;&#x60;true&#x60;&#x60;).
     * @param verifyRequest The verifyRequest parameter
     * @return VerifyResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<VerifyResponse> verifyClientToken(@jakarta.annotation.Nonnull VerifyRequest verifyRequest) throws WebClientResponseException {
        ParameterizedTypeReference<VerifyResponse> localVarReturnType = new ParameterizedTypeReference<VerifyResponse>() {};
        return verifyClientTokenRequestCreation(verifyRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Verify submitted client token
     * The client of the end-user have to make certain calculations as a so-called **proof-of-work** mechanism. The end point determines whether those calculations have been performed correctly. Also if the token has been used before, among other validation functions.  As a result the body returns a JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; as true or false (boolean). 
     * <p><b>200</b> - A JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; with the value 0 (&#x60;&#x60;false&#x60;&#x60;) or 1 (&#x60;&#x60;true&#x60;&#x60;).
     * @param verifyRequest The verifyRequest parameter
     * @return ResponseEntity&lt;VerifyResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<VerifyResponse>> verifyClientTokenWithHttpInfo(@jakarta.annotation.Nonnull VerifyRequest verifyRequest) throws WebClientResponseException {
        ParameterizedTypeReference<VerifyResponse> localVarReturnType = new ParameterizedTypeReference<VerifyResponse>() {};
        return verifyClientTokenRequestCreation(verifyRequest).toEntity(localVarReturnType);
    }

    /**
     * Verify submitted client token
     * The client of the end-user have to make certain calculations as a so-called **proof-of-work** mechanism. The end point determines whether those calculations have been performed correctly. Also if the token has been used before, among other validation functions.  As a result the body returns a JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; as true or false (boolean). 
     * <p><b>200</b> - A JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; with the value 0 (&#x60;&#x60;false&#x60;&#x60;) or 1 (&#x60;&#x60;true&#x60;&#x60;).
     * @param verifyRequest The verifyRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec verifyClientTokenWithResponseSpec(@jakarta.annotation.Nonnull VerifyRequest verifyRequest) throws WebClientResponseException {
        return verifyClientTokenRequestCreation(verifyRequest);
    }
}
