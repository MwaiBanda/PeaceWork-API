package com.mwaibanda.data.model.job

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.STRING)
enum class EmploymentType {
    Contract,
    Temporary,
    Internship,
}