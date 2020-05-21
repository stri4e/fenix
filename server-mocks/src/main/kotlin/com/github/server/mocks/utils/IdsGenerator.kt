package com.github.server.mocks.utils

import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicLong

@Component
class IdsGenerator {

    var increment = AtomicLong();

    fun getId(): Long {
        return increment.incrementAndGet()
    }

    fun clear() {
        increment.set(0)
    }

}