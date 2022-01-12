package com.mwaibanda.data.model.jobs

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.STRING)
enum class Benefits {
    HealthInsurance,
    FlexibleSchedule,
    DentalInsurance,
    EmployeeDiscount,
    LifeInsurance
}