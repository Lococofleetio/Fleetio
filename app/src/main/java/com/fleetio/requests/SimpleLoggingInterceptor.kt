package com.fleetio.requests



import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException


class SimpleLoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        LOGGER.info("Intercepted headers: {} from URL: {}", request.headers(), request.url())
        return chain.proceed(request)
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(SimpleLoggingInterceptor::class.java)
    }
}