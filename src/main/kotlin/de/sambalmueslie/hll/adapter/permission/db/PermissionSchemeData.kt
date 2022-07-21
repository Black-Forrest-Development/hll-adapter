package de.sambalmueslie.hll.adapter.permission.db

import de.sambalmueslie.hll.adapter.permission.api.PermissionScheme
import de.sambalmueslie.hll.adapter.permission.api.PermissionSchemeChangeRequest
import jakarta.persistence.*

@Entity(name = "PermissionScheme")
@Table(name = "permission_scheme")
data class PermissionSchemeData(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0,
    @Column()
    var name: String = "",
    @Column()
    var description: String = ""
) {

    companion object {
        fun create(request: PermissionSchemeChangeRequest) = PermissionSchemeData(0, request.name, request.description)
    }

    fun update(request: PermissionSchemeChangeRequest): PermissionSchemeData {
        name = request.name
        description = request.description
        return this
    }

    fun convert(): PermissionScheme {
        return PermissionScheme(id, name, description)
    }
}
