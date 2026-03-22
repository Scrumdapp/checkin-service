package com.scrumdapp.checkinservice.utils.filters

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJacksonValue
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import tools.jackson.databind.ObjectMapper
import tools.jackson.databind.ser.std.SimpleBeanPropertyFilter
import tools.jackson.databind.ser.std.SimpleFilterProvider


// This is still a work in progress. Might revert back to a more manual solution if I fail to bind the jsonmapper to a filter.

@ControllerAdvice
class CheckinFieldFilterAdvice(private val mapper: ObjectMapper): ResponseBodyAdvice<Any> {

    private val fields = mapOf(
        "presence_comment" to listOf("presence_comment"),
        "checkin" to listOf("checkin_stars", "checkin_comment"),
        "checkup" to listOf("checkup_stars", "checkup_comment"),
        "checkout" to listOf("checkout_stars", "checkout_comment"),
    )

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        // Could filter on certain controllers or methods, but idc atm
        return true
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        if (body == null) return null

        val param = (request as ServletServerHttpRequest).servletRequest.getParameter("fields")?.trim()
        if (param.isNullOrBlank()) return body

        val paramFields = param
            .split(",")
            .map { it.trim() }
            .toSet()

        val filter = SimpleBeanPropertyFilter.filterOutAllExcept(paramFields)
        val filterProvider = SimpleFilterProvider().addFilter("checkinFilter", filter)

        // Still searching for a better option here ):
        return MappingJacksonValue(body).apply {
        }
    }
}

