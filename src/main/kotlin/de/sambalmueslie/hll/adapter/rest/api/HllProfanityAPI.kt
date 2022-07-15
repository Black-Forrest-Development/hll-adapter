package de.sambalmueslie.hll.adapter.rest.api

import io.micronaut.security.authentication.Authentication

interface HllProfanityAPI {
    fun getProfanityWords(auth: Authentication, serverId: Long): Set<String>
    fun profanityWordsAdd(auth: Authentication, serverId: Long, words: List<String>): Any
    fun profanityWordsRemove(auth: Authentication, serverId: Long, word: String): Any
}
