package de.sambalmueslie.hll.adapter.rcon.api

data class HllRconResponse(
    val success: Boolean,
    val content: String,
    val error: String
) {
    fun getInt() = content.toIntOrNull() ?: -1
    fun getBoolean() = content == "on"
    fun getSet() = getArray().toSet()
    fun getList() = getArray()
    fun getString() = content

    private fun getArray(): List<String> {
        val fields = content.split("\t")
        if (fields.isEmpty()) return emptyList()
        val length = fields.first().toIntOrNull() ?: return emptyList()
        return fields.slice(1..length)
    }


}
