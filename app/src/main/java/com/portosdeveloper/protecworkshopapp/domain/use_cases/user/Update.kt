package com.portosdeveloper.protecworkshopapp.domain.use_cases.user

import com.portosdeveloper.protecworkshopapp.domain.module.User
import com.portosdeveloper.protecworkshopapp.domain.repository.UsersRepository
import javax.inject.Inject

class Update @Inject constructor(private val repository: UsersRepository){
    suspend operator fun invoke(user: User, newCamp: String) = repository.update(user, newCamp)
}