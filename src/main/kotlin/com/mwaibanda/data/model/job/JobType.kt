package com.mwaibanda.data.model.job

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.STRING)
enum class JobType {
    FullTime,
    PartTime,
    Either,
}