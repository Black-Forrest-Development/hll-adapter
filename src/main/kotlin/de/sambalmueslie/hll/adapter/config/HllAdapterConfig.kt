package de.sambalmueslie.hll.adapter.config


import io.micronaut.context.annotation.ConfigurationProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.validation.constraints.NotBlank

@ConfigurationProperties("app")
class HllAdapterConfig {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(HllAdapterConfig::class.java)
    }



}
