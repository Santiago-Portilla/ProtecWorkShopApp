package com.portosdeveloper.protecworkshopapp.domain.use_cases.user

data class UserUseCases (
    val update: Update,
    val updateFinish: UpdateFinish,
    var getUserById: GetUserById,
    var getLastUserById: GetLastUserById,
    var getUserList: GetUserList,
    var updateSalary: UpdateSalary,
    var getMergedUser: GetMergedUser,
    var updateProcess: UpdateProcess
)