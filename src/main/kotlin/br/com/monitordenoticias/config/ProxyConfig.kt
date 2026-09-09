package br.com.monitordenoticias.config

import java.net.Authenticator
import java.net.InetSocketAddress
import java.net.PasswordAuthentication
import java.net.Proxy
import java.net.URL
import java.net.URLConnection

/** Configuração de proxy compartilhada por todas as rotinas de rede. */
data class ProxyConfig(
    val enabled: Boolean = false,
    val host: String = DEFAULT_HOST,
    val port: Int = DEFAULT_PORT,
    val username: String = "",
    val password: String = "",
) {
    init { require(port in 1..65535) { "Porta de proxy inválida" } }

    fun toJavaProxy(): Proxy = if (enabled) {
        Proxy(Proxy.Type.HTTP, InetSocketAddress(host.trim(), port))
    } else Proxy.NO_PROXY

    companion object {
        const val DEFAULT_HOST = "proxy-7dn.mb"
        const val DEFAULT_PORT = 6060
    }
}

object ProxyRuntime {
    @Volatile private var current = ProxyConfig()

    fun apply(config: ProxyConfig) {
        current = config
        Authenticator.setDefault(if (config.enabled && config.username.isNotBlank()) {
            object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication? {
                    if (requestorType != RequestorType.PROXY) return null
                    if (!requestingHost.equals(config.host, ignoreCase = true)) return null
                    if (requestingPort != config.port) return null
                    return PasswordAuthentication(config.username, config.password.toCharArray())
                }
            }
        } else null)
    }

    fun current(): ProxyConfig = current

    fun open(url: URL): URLConnection = url.openConnection(current.toJavaProxy())
}
