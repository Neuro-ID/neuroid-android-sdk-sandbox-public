package com.sample.neuroidapp.us.service.domain.network

data class Signal(
    val model: String,
    val version: String,
    val score: Double,
    val label: String,
    val attributes: String?,
)