package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllProfanityAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/profanity")
@Tag(name = "Profanity API")
class HllProfanityController(private val service: HllProfanityService) : HllProfanityAPI {
    @Get("/{serverId}")
    override fun getProfanityWords(auth: Authentication, @PathVariable serverId: Long): Set<String> = service.getProfanityWords(auth,serverId)

    @Put("/{serverId}")
    override fun profanityWordsAdd(auth: Authentication, @PathVariable serverId: Long, @Body words: List<String>) = service.profanityWordsAdd(auth,serverId, words)

    @Delete("/{serverId}")
    override fun profanityWordsRemove(auth: Authentication, @PathVariable serverId: Long, @QueryValue word: String) = service.profanityWordsRemove(auth,serverId, word)
}
