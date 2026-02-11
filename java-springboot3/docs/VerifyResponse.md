

# VerifyResponse


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**success** | **Boolean** | The status of the verification.  Possible values:&lt;ul&gt;&lt;li&gt;true&lt;/li&gt;&lt;li&gt;false&lt;/li&gt;&lt;/ul&gt;  |  [optional] |
|**train** | **Boolean** | If null or false, actual validation was performed.  If true, the field success will always be true. This happens, for example, if the sitekey does not exist, if the secret is incorrect, if the sitekey is set to train&#x3D;true, or some other condition prevents the server to perform the operation.  |  [optional] |



