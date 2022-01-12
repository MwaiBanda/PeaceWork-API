package com.mwaibanda.data.model.jobs

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.STRING)
enum class Schedule {
    WeekendAvailability,
    Holidays,
    MondayToFriday,
    DayShift,
    NightShift,
    EightHourShift,
    TenHourShift,
    TwelveHourShift,
    EveryWeekend,
    EveningShift,
    OverTime,
    Other
}