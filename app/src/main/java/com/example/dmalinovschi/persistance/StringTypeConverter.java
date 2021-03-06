package com.example.dmalinovschi.persistance;

import android.arch.persistence.room.TypeConverter;

import com.example.dmalinovschi.persistance.models.MeasurementType;

public class StringTypeConverter {
    @TypeConverter
    public static String fromMeasurementType(MeasurementType value) {
        return value == null? null : value.toString();
    }

    @TypeConverter
    public static MeasurementType toMeasurementType(String value) {
        return value == null? null : MeasurementType.valueOf(value);
    }
}
