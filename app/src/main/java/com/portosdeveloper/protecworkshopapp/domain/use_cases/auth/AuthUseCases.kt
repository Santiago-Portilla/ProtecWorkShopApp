package com.portosdeveloper.protecworkshopapp.domain.use_cases.auth

data class AuthUseCases(
    val login: Login,
    val getCurrentUser: GetCurrentUser
)