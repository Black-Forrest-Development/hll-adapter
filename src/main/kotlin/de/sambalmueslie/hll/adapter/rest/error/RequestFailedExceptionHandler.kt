package de.sambalmueslie.hll.adapter.rest.error


import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton

@Produces
@Singleton
@Requires(classes = [RequestFailedException::class, ExceptionHandler::class])
class RequestFailedExceptionHandler : ExceptionHandler<RequestFailedException, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: RequestFailedException): HttpResponse<*> {
        return HttpResponse.badRequest(exception.message)
    }

}
