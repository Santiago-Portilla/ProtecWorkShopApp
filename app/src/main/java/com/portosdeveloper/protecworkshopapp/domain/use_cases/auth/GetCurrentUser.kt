package com.portosdeveloper.protecworkshopapp.domain.use_cases.auth

import com.portosdeveloper.protecworkshopapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser  @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke() = repository.currentUser

}