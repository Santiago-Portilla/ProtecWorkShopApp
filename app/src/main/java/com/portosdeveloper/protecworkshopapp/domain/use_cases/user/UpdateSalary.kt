package com.portosdeveloper.protecworkshopapp.domain.use_cases.user

import com.portosdeveloper.protecworkshopapp.domain.module.User
import com.portosdeveloper.protecworkshopapp.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateSalary @Inject constructor(private val repository: UsersRepository) {
    suspend operator fun invoke(user: User, workDay: String, workMonth: String, workWeek: String, workLife: String) = repository.updateSalary(user, workDay, workWeek, workMonth, workLife)
}