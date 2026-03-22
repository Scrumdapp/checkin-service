package com.scrumdapp.checkinservice.utils.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import java.time.LocalDate
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DateRangeValidator::class])
annotation class DateRange(
    val message: String = "Date was either too long ago or too far into the future",
    val maxPastDays: Long = 7,
    val maxFutureDays: Long = 2,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class DateRangeValidator: ConstraintValidator<DateRange, LocalDate> {
    private var maxPastDays: Long = 1
    private var maxFutureDays: Long = 1
    override fun initialize(constraint: DateRange) {
        maxPastDays = constraint.maxPastDays
        maxFutureDays = constraint.maxFutureDays
    }
    override fun isValid(date: LocalDate?, ctx: ConstraintValidatorContext?): Boolean {
        if (date == null) return true

        val date = LocalDate.now()
        val minDate = date.minusDays(maxPastDays)
        val maxDate = date.plusDays(maxFutureDays)
        return !date.isBefore(minDate) && !date.isAfter(maxDate)
    }
}
