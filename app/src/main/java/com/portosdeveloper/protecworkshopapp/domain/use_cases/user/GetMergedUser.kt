package com.portosdeveloper.protecworkshopapp.domain.use_cases.user

import com.portosdeveloper.protecworkshopapp.domain.repository.UsersRepository
import javax.inject.Inject

class GetMergedUser @Inject constructor(private val repository: UsersRepository) {

    operator fun invoke() = repository.getMergedUser()

}