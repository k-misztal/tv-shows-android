package com.misztal.tvshows.common

import com.google.gson.*
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.lang.reflect.Type

class DateTimeSerializer : JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    override fun serialize(src: DateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val date = ISODateTimeFormat.dateTime().print(src)
        return JsonPrimitive(date)
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext?): DateTime? {
        // Do not try to deserialize null or empty values
        if (json.asString == null || json.asString.isEmpty()) {
            return null
        }

        return ISODateTimeFormat.dateTimeParser().parseDateTime(json.asString)
    }
}