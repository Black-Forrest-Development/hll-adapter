package de.sambalmueslie.hll.adapter.server.db

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
interface ServerConfigRepository : PageableRepository<ServerConfigData, Long> {
    fun findByName(name: String): ServerConfigData?
}
