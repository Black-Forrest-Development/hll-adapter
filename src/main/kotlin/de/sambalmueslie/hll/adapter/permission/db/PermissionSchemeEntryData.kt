package de.sambalmueslie.hll.adapter.permission.db

import de.sambalmueslie.hll.adapter.permission.api.PermissionSchemeEntryChangeRequest
import jakarta.persistence.*

@Entity(name = "PermissionSchemeEntry")
@Table(name = "permission_scheme_entry")
data class PermissionSchemeEntryData(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0,
    @Column()
    var action: String = "",
    @Column()
    val schemeId: Long = 0,
) {
    companion object {
        fun create(schemeId: Long, request: PermissionSchemeEntryChangeRequest) = PermissionSchemeEntryData(0, request.action, schemeId)
    }

    fun update(request: PermissionSchemeEntryChangeRequest): PermissionSchemeEntryData {
        action = request.action
        return this
    }
}
