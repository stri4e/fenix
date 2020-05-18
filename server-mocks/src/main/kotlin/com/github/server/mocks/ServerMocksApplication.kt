package com.github.server.mocks

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServerMocksApplication

fun main(args: Array<String>) {
	runApplication<ServerMocksApplication>(*args)
}
