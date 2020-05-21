package com.github.server.mocks.filters

import com.github.server.mocks.utils.IdsGenerator
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

@Component
class ClearIdsFilter(private var idsGenerator: IdsGenerator) : Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        this.idsGenerator.clear()
        chain?.doFilter(request, response)
    }

}