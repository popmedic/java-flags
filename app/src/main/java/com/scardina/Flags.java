package com.scardina;

import java.nio.ByteBuffer;

/**
 * Flags 
 * A class for creating a 4 byte integer that we can encode and then send lots of 
 * info in an integer JSON field.
 * 
 * - 8 boolean values
 *     - registered
 *     - userDisabled
 *     - suspended
 *     - wifi
 *     - airlytics
 *     - sim
 *     - eventsLogging
 * - 3 values between 0-127
 *     - networkCount
 *     - networkSuggestedCount
 *     - batteryPercentage
 */
public class Flags {
    // -- Private Constants
    // FLAGS
    private static final byte REGISTERED_FLAG     = 0b00000001;
    private static final byte USER_DISABLED_FLAG  = 0b00000010;
    private static final byte SUSPENDED_FLAG      = 0b00000100;
    private static final byte WIFI_FLAG           = 0b00001000;
    private static final byte AIRLYTICS_FLAG      = 0b00010000;
    private static final byte SIM_FLAG            = 0b00100000;
    private static final byte EVENTS_LOGGING_FLAG = 0b01000000;
    // BYTE Indexes
    private static final int NETWORK_COUNT_INDEX = 0;
    private static final int NETWORK_SUGGESTED_COUNT_INDEX = 1;
    private static final int BATTERY_PERCENTAGE_INDEX = 2;
    private static final int FLAGS_INDEX = 3;
    // MAX Sizes
    private static final int MAX_NETWORKCOUNTS = 128;
    private static final int MAX_PERCENTAGE = 100;

    // -- Public Constants
    public static final String NETWORK_COUNT_OOR_ERROR = "network count out of range, must be 0 - 127";
    public static final String NETWORK_SUGGESTED_COUNT_OOR_ERROR = "network suggested count out of range, must be 0 - 127";
    public static final String BATTERY_PERCENTAGE_OOR_ERROR = "battery percentage out of range, must be 0 - 100";

    // -- Private final members
    private final int value;

    public Flags(
        boolean registered, 
        boolean userDisabled, 
        boolean suspended, 
        boolean wifi, 
        boolean airlytics, 
        boolean sim, 
        boolean eventsLogging, 
        int networkCount, 
        int networkSuggestedCount, 
        int batteryPercentage
    ) throws Error {
        // Make sure the values are within limits
        if (networkCount > MAX_NETWORKCOUNTS || networkCount < 0) { throw new Error(NETWORK_COUNT_OOR_ERROR); } 
        else if (networkSuggestedCount > MAX_NETWORKCOUNTS || networkSuggestedCount < 0) { throw new Error(NETWORK_SUGGESTED_COUNT_OOR_ERROR); } 
        else if (batteryPercentage > MAX_PERCENTAGE || batteryPercentage < 0) { throw new Error(BATTERY_PERCENTAGE_OOR_ERROR); }
        
        // set up value
        int value = ByteBuffer
                    .allocate(8)
                    .put(NETWORK_COUNT_INDEX, (byte)networkCount)
                    .put(NETWORK_SUGGESTED_COUNT_INDEX, (byte)networkSuggestedCount)
                    .put(BATTERY_PERCENTAGE_INDEX, (byte)batteryPercentage)
                    .getInt();

        // set the boolean bytes
        if (registered) value = value | REGISTERED_FLAG;
        if (userDisabled) value = value | USER_DISABLED_FLAG;
        if (suspended) value = value | SUSPENDED_FLAG;
        if (wifi) value = value | WIFI_FLAG;
        if (airlytics) value = value | AIRLYTICS_FLAG;
        if (sim) value = value | SIM_FLAG;
        if (eventsLogging) value = value | EVENTS_LOGGING_FLAG;

        // set the member value
        this.value = value;
    }

    public Flags(int value) {
        this.value = value;
    }

    public int getNetworkCount() {
        return getByte(NETWORK_COUNT_INDEX);
    }

    public int getNetworkSuggestedCount() {
        return getByte(NETWORK_SUGGESTED_COUNT_INDEX);
    }

    public int getBatteryPercentage() {
        return getByte(BATTERY_PERCENTAGE_INDEX);
    }

    public boolean getRegistered() {
        return (int)(getByte(FLAGS_INDEX) & REGISTERED_FLAG) != 0;
    }

    public boolean getUserDisabled() {
        return (int)(getByte(FLAGS_INDEX) & USER_DISABLED_FLAG) != 0;
    }

    public boolean getSuspended() {
        return (int)(getByte(FLAGS_INDEX) & SUSPENDED_FLAG) != 0;
    }

    public boolean getWiFi() {
        return (int)(getByte(FLAGS_INDEX) & WIFI_FLAG) != 0;
    }

    public boolean getAirlytics() {
        return (int)(getByte(FLAGS_INDEX) & AIRLYTICS_FLAG) != 0;
    }

    public boolean getSIM() {
        return (int)(getByte(FLAGS_INDEX) & SIM_FLAG) != 0;
    }

    public boolean getEventLogging() {
        return (int)(getByte(FLAGS_INDEX) & EVENTS_LOGGING_FLAG) != 0;
    }

    public int getValue() {
        return this.value;
    }

    public String toJSON() {
        return new StringBuilder()
        .append("{\"networkCount\":")
        .append(getNetworkCount())
        .append(",\"networkSuggestedCount\":")
        .append(getNetworkSuggestedCount())
        .append(",\"batteryPercentage\":")
        .append(getBatteryPercentage())
        .append(",\"registered\":")
        .append(getRegistered())
        .append(",\"userDisabled\":")
        .append(getUserDisabled())
        .append(",\"suspended\":")
        .append(getSuspended())
        .append(",\"wifi\":")
        .append(getWiFi())
        .append(",\"airlytics\":")
        .append(getAirlytics())
        .append(",\"sim\":")
        .append(getSIM())
        .append(",\"eventLogging\":")
        .append(getEventLogging())
        .append("}")
        .toString();
    }

    private byte getByte(int index) {
        return (byte)ByteBuffer.allocate(8).putInt(this.value).get(index);
    }
}
