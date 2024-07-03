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

`./gradlew run`

## Test

`./gradlew test`

## [Class Link](app/src/main/java/com/scardina/Flags.java)

