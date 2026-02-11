package com.myrasec.client.api;

import com.myrasec.client.ApiClient;
import com.myrasec.client.BaseApi;

import com.myrasec.client.model.VerifyRequest;
import com.myrasec.client.model.VerifyResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2026-02-11T13:29:19.732646793+01:00[Europe/Berlin]", comments = "Generator version: 7.18.0")
public class EuCaptchaApi extends BaseApi {

    public EuCaptchaApi() {
        super(new ApiClient());
    }

    public EuCaptchaApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Verify submitted client token
     * The client of the end-user have to make certain calculations as a so-called **proof-of-work** mechanism.   The end point determines whether those calculations have been performed correctly. Also if the token has been used before, among other validation functions.      As a result the body returns a JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; as true or false (boolean). 
     * <p><b>200</b> - A JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; with the value 0 (&#x60;&#x60;false&#x60;&#x60;) or 1 (&#x60;&#x60;true&#x60;&#x60;).
     * @param verifyRequest  (required)
     * @return VerifyResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public VerifyResponse verifyClientToken(VerifyRequest verifyRequest) throws RestClientException {
        return verifyClientTokenWithHttpInfo(verifyRequest).getBody();
    }

    /**
     * Verify submitted client token
     * The client of the end-user have to make certain calculations as a so-called **proof-of-work** mechanism.   The end point determines whether those calculations have been performed correctly. Also if the token has been used before, among other validation functions.      As a result the body returns a JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; as true or false (boolean). 
     * <p><b>200</b> - A JSON object with the attribute &#x60;&#x60;success&#x60;&#x60; with the value 0 (&#x60;&#x60;false&#x60;&#x60;) or 1 (&#x60;&#x60;true&#x60;&#x60;).
     * @param verifyRequest  (required)
     * @return ResponseEntity&lt;VerifyResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<VerifyResponse> verifyClientTokenWithHttpInfo(VerifyRequest verifyRequest) throws RestClientException {
        Object localVarPostBody = verifyRequest;
        
        // verify the required parameter 'verifyRequest' is set
        if (verifyRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'verifyRequest' when calling verifyClientToken");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<VerifyResponse> localReturnType = new ParameterizedTypeReference<VerifyResponse>() {};
        return apiClient.invokeAPI("/verify", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }

    @Override
    public <T> ResponseEntity<T> invokeAPI(String url, HttpMethod method, Object request, ParameterizedTypeReference<T> returnType) throws RestClientException {
        String localVarPath = url.replace(apiClient.getBasePath(), "");
        Object localVarPostBody = request;

        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
