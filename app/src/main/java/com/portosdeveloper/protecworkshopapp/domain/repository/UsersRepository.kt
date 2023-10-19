package com.portosdeveloper.protecworkshopapp.domain.repository

import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.domain.module.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {
    suspend fun update(user: User, newCamp: String):Response<Boolean>
    suspend fun updateSalary(user: User, workDay: String, workMonth: String, workWeek: String, workLife: String):Response<Boolean>
    suspend fun updateFinish(user: User, newCamp: String):Response<Boolean>
    suspend fun updateProcess(user: User, newList: List<String>):Response<Boolean>
    suspend fun getUser(id:String): User
    fun getUserById(id:String): Flow<Response<User>>
    fun getLastUserById(id:String): Flow<Response<User>>
    fun getUserList(): Flow<List<String>>
    fun getMergedUser(): Flow<List<String>>

}