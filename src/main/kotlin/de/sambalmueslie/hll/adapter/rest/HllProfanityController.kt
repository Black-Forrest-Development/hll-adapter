package de.sambalmueslie.hll.adapter.rest


import de.sambalmueslie.hll.adapter.rest.api.HllProfanityAPI
import io.micronaut.http.annotation.*
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("api/profanity")
@Tag(name = "Profanity API")
class HllProfanityController(private val service: RconService) : HllProfanityAPI {
    @Get()
    override fun getProfanityWords(auth: Authentication): Set<String> = service.getProfanityWords(auth)

    @Put()
    override fun profanityWordsAdd(auth: Authentication, @Body words: List<String>) = service.profanityWordsAdd(auth, words)

    @Delete()
    override fun profanityWordsRemove(auth: Authentication, @QueryValue word: String) = service.profanityWordsRemove(auth, word)
}
