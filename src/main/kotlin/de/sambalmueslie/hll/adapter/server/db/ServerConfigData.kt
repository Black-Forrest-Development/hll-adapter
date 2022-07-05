package de.sambalmueslie.hll.adapter.server.db

import de.sambalmueslie.hll.adapter.server.api.ServerConfig
import de.sambalmueslie.hll.adapter.server.api.ServerConfigChangeRequest
import jakarta.persistence.*

@Entity(name = "ServerConfig")
@Table(name = "server_config")
data class ServerConfigData(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0,
    @Column()
    var name: String = "",
    @Column()
    var host: String = "",
    @Column()
    var port: Int = 0,
    @Column()
    var password: String = ""
) {
    companion object {
        fun create(request: ServerConfigChangeRequest) = ServerConfigData(0, request.name, request.host, request.port, request.password)
    }

    fun update(request: ServerConfigChangeRequest): ServerConfigData {
        name = request.name
        host = request.host
        port = request.port
        password = request.password
        return this
    }

    fun convert(): ServerConfig {
        return ServerConfig(id, name, host, port, password)
    }
}
