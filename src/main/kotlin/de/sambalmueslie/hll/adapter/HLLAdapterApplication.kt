package de.sambalmueslie.hll.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.micronaut.context.event.BeanCreatedEvent
import io.micronaut.context.event.BeanCreatedEventListener
import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import jakarta.inject.Singleton

@OpenAPIDefinition(
    info = Info(
        title = "hll-adapter",
        version = "\${api.version}",
        description = "\${openapi.description}",
        license = License(name = "Apache 2.0", url = "https://github.com/sambalmueslie/hll-adapter/blob/master/LICENSE"),
        contact = Contact(url = "https://github.com/sambalmueslie/hll-adapter"),
    )
)
class HLLAdapterApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Micronaut.build()
                .args(*args)
                .packages("de.sambalmueslie.hll.adapter")
                .start()
        }
    }

    @Singleton
    internal class ObjectMapperBeanEventListener : BeanCreatedEventListener<ObjectMapper> {
        override fun onCreated(event: BeanCreatedEvent<ObjectMapper>): ObjectMapper {
            val mapper: ObjectMapper = event.bean
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS)
            return mapper
        }
    }
}

