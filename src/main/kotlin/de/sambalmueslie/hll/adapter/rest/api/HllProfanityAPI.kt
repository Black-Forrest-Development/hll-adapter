package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllProfanityAPI {
    fun getProfanityWords(auth: Authentication): Set<String>
    fun profanityWordsAdd(auth: Authentication, words: List<String>): Any
    fun profanityWordsRemove(auth: Authentication, word: String): Any
}
