package de.sambalmueslie.hll.adapter.permission.db

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
interface PermissionSchemeEntryRepository : PageableRepository<PermissionSchemeEntryData, Long> {
    fun findBySchemeId(schemeId: Long): List<PermissionSchemeEntryData>
}
