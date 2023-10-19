package com.portosdeveloper.protecworkshopapp.domain.use_cases.user

import com.portosdeveloper.protecworkshopapp.domain.repository.UsersRepository
import javax.inject.Inject

class GetLastUserById @Inject constructor(private val repository: UsersRepository) {
    operator fun invoke(id: String) = repository.getLastUserById(id)
}