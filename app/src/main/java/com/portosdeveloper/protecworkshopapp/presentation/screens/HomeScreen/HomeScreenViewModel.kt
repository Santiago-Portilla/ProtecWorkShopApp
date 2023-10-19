package com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecworkshopapp.domain.use_cases.packageShirt.PackageUseCases
import com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.components.HomeState
import com.portosdeveloper.protecworkshopapp.presentation.utils.ActualDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.portosdeveloper.protecworkshopapp.domain.module.Package
import com.portosdeveloper.protecworkshopapp.domain.module.Prices
import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.domain.module.User
import com.portosdeveloper.protecworkshopapp.domain.use_cases.user.UserUseCases
import com.portosdeveloper.protecworkshopapp.domain.use_cases.utils.UtilsUseCases

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val packageUseCases: PackageUseCases,
    private val userUseCases: UserUseCases,
    private val utilsUseCases: UtilsUseCases
): ViewModel(){
    var state by mutableStateOf(HomeState())

    var getUserResponse by mutableStateOf<Response<User>?>(null)
    var getLastUserResponse by mutableStateOf<Response<User>?>(null)
    var getPackageResponse by mutableStateOf<Response<Package>?>(null)
    var updatePackageResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateUserResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateLastUserResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateSalaryUserResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateProcessResponse by mutableStateOf<Response<Boolean>?>(null)
    var appearPackageInfo by mutableStateOf<Boolean>(false)
    var appearButtonSearch by mutableStateOf<Boolean>(false)
    var appearButtonConfirm by mutableStateOf<Boolean>(false)
    var restartActivity by mutableStateOf<Boolean>(false)
    var errorMessageUser by mutableStateOf<String>("Ingrese un Usuario porfavor")
    var errorMessageUserNotMatch by mutableStateOf<String>("El Usuario no existe")
    var isValidUser by mutableStateOf(false)
    var errorMessagePackage by mutableStateOf<String>("Ingrese un Paquete porfavor")
    var errorMessagePackageNotMatch by mutableStateOf<String>("El Paquete no existe")
    var isValidPackage by mutableStateOf(false)
    var isValidClose by mutableStateOf(false)
    var mergedNeckFist by mutableStateOf(false)
    var mergedClosed by mutableStateOf(false)
    var selectedNecksOrClosed by mutableStateOf(true)

    var packageshirtInBBDDIsOk by mutableStateOf(false)
    var userInBBDDIsOk by mutableStateOf(false)
    var confirmUpdatePU by mutableStateOf(false)



    var userInBBDD = User()
    var lastUserInBBDD = User()
    var packageShirtInBBDD = Package()
    var prices = Prices()
    val actualDate = ActualDate()

    init{
        getUsersList()
        getPackageList()
        getPrices()
        getMergedUser()
    }

    fun jobInput(job : String){
        state = state.copy(job = job)
    }
    fun idInput(id : String){
        state = state.copy(id = id)
    }
    fun shirtPackageInput(shirtPackage : String){
        state = state.copy(shirtPackage = shirtPackage)
    }
    fun listClosedResponseInput(listClosedResponse: String){
        state = state.copy(listClosedResponse = listClosedResponse)
    }

    fun getUserById(id: String) = viewModelScope.launch {
        getUserResponse = Response.Loading
        userUseCases.getUserById(id).collect(){
            getUserResponse = it
        }
    }
    private fun getMergedUser() = viewModelScope.launch{
        userUseCases.getMergedUser().collect(){
            state = state.copy(mergedUserList = it)
        }
    }

    fun getPackageById(id: String) = viewModelScope.launch {
        getPackageResponse = Response.Loading
        packageUseCases.getPackageById(id).collect(){
           getPackageResponse = it
        }
    }

    private fun updatePackage(packageShirt:Package, personalId: String, job: String) = viewModelScope.launch {
        updatePackageResponse = Response.Loading
        val result = packageUseCases.updatePackage(packageShirt,personalId, job)
        updatePackageResponse = result
    }

    private fun updateUser(user: User, newCamp: String) = viewModelScope.launch {
        updateUserResponse = Response.Loading
        val result = userUseCases.update(user,newCamp)
        updateUserResponse = result
    }

    fun getLastUserById(id: String) = viewModelScope.launch {
        getLastUserResponse = Response.Loading
        userUseCases.getLastUserById(id).collect(){
            getLastUserResponse = it
        }
    }
    fun updateProcess(user: User, newList: List<String>) = viewModelScope.launch {
        updateProcessResponse = Response.Loading
        val result = userUseCases.updateProcess(user, newList)
        updateProcessResponse = result
    }
    private fun updateLastUser(user: User, newCamp: String) = viewModelScope.launch {
        updateLastUserResponse = Response.Loading
        val result = userUseCases.updateFinish(user,newCamp)
        updateLastUserResponse = result
    }

    private fun getUsersList() = viewModelScope.launch {
        userUseCases.getUserList().collect(){
            state = state.copy(userList = it)
        }
    }
    private fun getPackageList() = viewModelScope.launch {
        packageUseCases.getPackageList().collect(){
            state = state.copy(packageList = it)
        }
    }

    private fun getPrices() = viewModelScope.launch {
        utilsUseCases.getPrices().collect{
            prices = it
        }
    }

    private fun updateSalary(user: User, workDay: String, workMonth: String, workWeek: String, workLife: String) = viewModelScope.launch {
        updateSalaryUserResponse = Response.Loading
        val result = userUseCases.updateSalary(user, workDay, workWeek, workMonth, workLife)
        updateSalaryUserResponse = result
    }

    fun onUpdatePackage(){
        updatePackage(packageShirtInBBDD,userInBBDD.id,userInBBDD.job)
    }

    fun onUpdateUser(){
        if(userInBBDD.job == "Fusionado"){
            if(lastUserInBBDD.job =="Corte"){
                updateUser(userInBBDD,"${packageShirtInBBDD.id},${actualDate.actualDate}")
            }
        }else{
            updateUser(userInBBDD,"${packageShirtInBBDD.id},${actualDate.actualDate}")
        }
    }

    fun onUpdateLastUser(){
        onUpdateSalary()
    }

    private fun newProcessList(user: User, shirtPackage: Package) : List<String>{
        return user.workListInProgress.filterNot { it.contains(shirtPackage.id, ignoreCase = true)  }
    }


    private fun onUpdateSalary(){
        var priceJob = ""
        if(lastUserInBBDD.job == "CuellosPuños"){
            priceJob = prices.necks_fists
        }else {
            when (packageShirtInBBDD.ubication) {
                "Corte" -> {
                    priceJob = prices.cutLine
                }
                "Fusionado" -> {
                    priceJob = prices.merged
                }
                "Cuerpos" -> {
                    priceJob = prices.bodies
                }
                "Cerradora" -> {
                    when (packageShirtInBBDD.name[0]) {
                        'C' -> {
                            if (state.listClosedResponse == "Con Pespunte") priceJob =
                                prices.sleeve_mounter_seamer
                            else priceJob = prices.sleeve_mounter
                        }
                        'B' -> {
                            priceJob = prices.fillet
                        }
                    }
                }
                "Terminacion" -> {
                    priceJob = prices.end
                }
                "Ojal y Boton" -> {
                    priceJob = prices.button
                }
                "Remate"->{
                    priceJob = prices.polish
                }
            }
        }

        updateSpecific(priceJob)

    }

    fun updateSpecific(priceJob: String){

        when(userInBBDD.job){
            "CuellosPuños" -> {
                if(packageShirtInBBDD.ubication == "Fusionado" && packageShirtInBBDD.bodiesJob == ""){
                    updateLastUser(lastUserInBBDD,"${packageShirtInBBDD.id},${actualDate.actualDate},${packageShirtInBBDD.quantity},${priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}")
                    updateSalary(
                        lastUserInBBDD,
                        "${lastUserInBBDD.workDay.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workMonth.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workWeek.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workLife.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                    )
                }else if(packageShirtInBBDD.ubication == " Cerradora" && packageShirtInBBDD.mergedFistsNecks == ""){
                    updateLastUser(lastUserInBBDD,"${packageShirtInBBDD.id},${actualDate.actualDate},${packageShirtInBBDD.quantity},${priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}")
                    updateSalary(
                        lastUserInBBDD,
                        "${lastUserInBBDD.workDay.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workMonth.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workWeek.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workLife.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                    )
                }else if(lastUserInBBDD.job == "CuellosPuños"){
                    updateLastUser(lastUserInBBDD,"${packageShirtInBBDD.id},${actualDate.actualDate},${packageShirtInBBDD.quantity},${priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}")
                    updateSalary(
                        lastUserInBBDD,
                        "${lastUserInBBDD.workDay.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workMonth.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workWeek.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workLife.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                    )
                }
                else{
                    restartActivity = true
                    confirmUpdatePU = true
                }
            }
            "Cuerpos" -> {
                if (packageShirtInBBDD.ubication == "Fusionado" && packageShirtInBBDD.cutNecksFistsJob == "") {
                    updateLastUser(
                        lastUserInBBDD,
                        "${packageShirtInBBDD.id},${actualDate.actualDate},${packageShirtInBBDD.quantity},${priceJob.toInt() * packageShirtInBBDD.quantity.toInt()}"
                    )
                    updateSalary(
                        lastUserInBBDD,
                        "${lastUserInBBDD.workDay.toInt() + priceJob.toInt() * packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workMonth.toInt() + priceJob.toInt() * packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workWeek.toInt() + priceJob.toInt() * packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workLife.toInt() + priceJob.toInt() * packageShirtInBBDD.quantity.toInt()}",
                    )
                }else{
                    restartActivity = true
                    confirmUpdatePU = true
                }
            }
            "Terminacion" ->{
                if(packageShirtInBBDD.ubication == "CerradoraOk"){
                    restartActivity = true
                    confirmUpdatePU = true
                }
            }
            else ->{
                if(packageShirtInBBDD.ubication == "Cerradora"){
                    if(userInBBDD.job == "Fusionado"){
                        updateLastUser(lastUserInBBDD,"${packageShirtInBBDD.id},${actualDate.actualDate},${packageShirtInBBDD.quantity},${priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}")
                        updateSalary(
                            lastUserInBBDD,
                            "${lastUserInBBDD.workDay.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                            "${lastUserInBBDD.workMonth.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                            "${lastUserInBBDD.workWeek.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                            "${lastUserInBBDD.workLife.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        )
                    }else{
                        restartActivity = true
                        confirmUpdatePU = true
                    }
                }else{
                    updateLastUser(lastUserInBBDD,"${packageShirtInBBDD.id},${actualDate.actualDate},${packageShirtInBBDD.quantity},${priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}")
                    updateSalary(
                        lastUserInBBDD,
                        "${lastUserInBBDD.workDay.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workMonth.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workWeek.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                        "${lastUserInBBDD.workLife.toInt()+priceJob.toInt()*packageShirtInBBDD.quantity.toInt()}",
                    )
                }

            }

        }
    }

    fun onUpdateProcess(){
        if(userInBBDD.job == "CuellosPuños"){
            if(lastUserInBBDD.job == "CuellosPuños"){
                updateProcess(lastUserInBBDD,newProcessList(lastUserInBBDD,packageShirtInBBDD))
            }
        }else{
            updateProcess(lastUserInBBDD,newProcessList(lastUserInBBDD,packageShirtInBBDD))
        }

    }
}