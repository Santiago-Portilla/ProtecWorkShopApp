package com.portosdeveloper.protecworkshopapp.domain.module

data class User(
    var id: String = "",
    var image : String = "",
    var name: String = "",
    var surName: String = "",
    var email: String = "",
    var birthday: String = "",
    var identificationType: String = "",
    var identificationNumber: String = "",
    var password: String = "",
    var job: String = "",
    var workListInProgress: List<String> = listOf(),
    var workListFinish: List<String> = listOf(),
    var workListPaid: List<String> = listOf(),
    var workDay: String = "",
    var workWeek: String = "",
    var workMonth: String = "",
    var workLife: String = "",
    var dateStart: String = "",
    var actualDate: String = ""
)
