[![Release](https://jitpack.io/v/iotsens/iotsens-api-sdk.svg)]
(https://jitpack.io/iotsens/iotsens-api-sdk)

# Java Client for IOTSENS Public API

The main class is com.iotsens.sdk.IoTSensApiClient which contains the actual methods to invoke to the REST services. The following is a snippet of how to initialize this class:

```java
public static final String APPLICATION_ID = "APPID"; // must be proper application identifier
public static final String SECRET = "SECRETWORD"; // must be proper secret 
public static final String DEFAULT_USER = "USER.NAME"; // must be a proper user


IoTSensApiClient apiClient = IoTSensApiClientBuilder.aIoTSensClient()
		.withApplication(APPLICATION_ID)
		.withSecret(SECRET)
		.withDefaultUser(DEFAULT_USER)
		.build();
```


## Example:

Retrieve all sensors matching the conditions:
*	belong to category 6
*	are located in geographical coordinates (0.085239, 40.887784, 0.082239, 39.887784) 
* have the word 'A00' in its uniqueId
* belong to templateId 18

```java
SensorsRequest sensorsRequest = SensorRequestBuilder.aSensorRequest()
		.withNorth(40.887784)
		.withWest(0.082239)
		.withSouth(39.887784)
		.withEast(0.085239)
		.withCategoryId(6)
		.withTemplateId(18)
		.withQuery("A000")
		.build();

for (SensorBasic sensorBasic :  apiClient.getSensors(sensorsRequest)) {
	System.out.println("Sensor = " + sensorBasic.toString());
}
```

For more information about the SDK and using the REST API directly, check the [documentation](IoTsens-API.pdf)
