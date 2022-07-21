package de.sambalmueslie.hll.adapter.rcon.builder

import de.sambalmueslie.hll.adapter.rcon.api.HllRconRequest


class HllRconRequestBuilder(command: String) {
    private val content = StringBuilder(command)

    fun add(value: Boolean): HllRconRequestBuilder {
        content.append(" ${if (value) "on" else "off"}")
        return this
    }

    fun add(value: Int): HllRconRequestBuilder {
        content.append(" $value")
        return this
    }

    fun add(value: String): HllRconRequestBuilder  {
        content.append(" $value")
        return this
    }

    fun add(value: List<String>): HllRconRequestBuilder {
        content.append(" ${value.joinToString(",")}")
        return this
    }

    fun escape(value: String): HllRconRequestBuilder {
        content.append(" \"$value\"")
        return this
    }

    fun build(): HllRconRequest {
        return HllRconRequest(content.toString())
    }


}
