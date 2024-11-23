# Flags

 A class for creating a 4 byte integer that we can encode and then send lots of 
 info in an integer JSON field.
 
 - 8 boolean values
     - registered
     - userDisabled
     - suspended
     - wifi
     - airlytics
     - sim
     - eventsLogging
 - 3 values between 0-255
     - networkCount
     - networkSuggestedCount
     - batteryPercentage

## Run

`./gradlew run --args='<arguments>'`

### arguments
```bash
# will set the flags value by a int
-value
# if option is present, sets value to true, leave out for false
-registered 
-registered
-userDisabled
-suspended
-wifi
-airlytics
-sim
-eventsLogging
# sets the value for the flag, 0 is default
-networkCount
-networkSuggestedCount
-batteryPercentage
```

### Example

```bash

```
## Test

`./gradlew test`

## [Class Link](https://github.com/popmedic/java-flags/blob/main/app/src/main/java/com/scardina/Flags.java)
