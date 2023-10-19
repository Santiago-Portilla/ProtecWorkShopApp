package com.portosdeveloper.protecworkshopapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.portosdeveloper.protecworkshopapp.core.Constants.BUTTON
import com.portosdeveloper.protecworkshopapp.core.Constants.CLOTH
import com.portosdeveloper.protecworkshopapp.core.Constants.PACKAGE
import com.portosdeveloper.protecworkshopapp.core.Constants.PACKING
import com.portosdeveloper.protecworkshopapp.core.Constants.PLOTTER
import com.portosdeveloper.protecworkshopapp.core.Constants.REFLECTIVE
import com.portosdeveloper.protecworkshopapp.core.Constants.ROLLWADDING
import com.portosdeveloper.protecworkshopapp.core.Constants.THREAD
import com.portosdeveloper.protecworkshopapp.core.Constants.TOTALCLOTH
import com.portosdeveloper.protecworkshopapp.core.Constants.USERSWORKSHOP
import com.portosdeveloper.protecworkshopapp.core.Constants.UTILS
import com.portosdeveloper.protecworkshopapp.core.Constants.WADDING
import com.portosdeveloper.protecworkshopapp.core.Constants.YARN
import com.portosdeveloper.protecworkshopapp.data.repository.AuthRepositoryImpl
import com.portosdeveloper.protecworkshopapp.data.repository.PackageRepositoryImpl
import com.portosdeveloper.protecworkshopapp.data.repository.UsersRepositoryImpl
import com.portosdeveloper.protecworkshopapp.data.repository.UtilsRepositoryImpl
import com.portosdeveloper.protecworkshopapp.domain.repository.AuthRepository
import com.portosdeveloper.protecworkshopapp.domain.repository.PackageRepository
import com.portosdeveloper.protecworkshopapp.domain.repository.UsersRepository
import com.portosdeveloper.protecworkshopapp.domain.repository.UtilsRepository
import com.portosdeveloper.protecworkshopapp.domain.use_cases.auth.*
import com.portosdeveloper.protecworkshopapp.domain.use_cases.packageShirt.GetPackageById
import com.portosdeveloper.protecworkshopapp.domain.use_cases.packageShirt.GetPackageList
import com.portosdeveloper.protecworkshopapp.domain.use_cases.packageShirt.PackageUseCases
import com.portosdeveloper.protecworkshopapp.domain.use_cases.packageShirt.UpdatePackage
import com.portosdeveloper.protecworkshopapp.domain.use_cases.user.*
import com.portosdeveloper.protecworkshopapp.domain.use_cases.utils.GetPrices
import com.portosdeveloper.protecworkshopapp.domain.use_cases.utils.UtilsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesFireBaseFireStore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERSWORKSHOP)
    fun providesUsersRef(db : FirebaseFirestore) : CollectionReference = db.collection(USERSWORKSHOP)

    @Provides
    @Named(USERSWORKSHOP)
    fun provideStorageUsersRef(storage : FirebaseStorage): StorageReference = storage.reference.child(USERSWORKSHOP)

    @Provides
    @Named(CLOTH)
    fun providesClothRef(db : FirebaseFirestore) : CollectionReference = db.collection(CLOTH)

    @Provides
    @Named(TOTALCLOTH)
    fun providesTotalClothRef(db : FirebaseFirestore) : CollectionReference = db.collection(TOTALCLOTH)

    @Provides
    @Named(PLOTTER)
    fun providesPlotterRef(db : FirebaseFirestore) : CollectionReference = db.collection(PLOTTER)

    @Provides
    @Named(WADDING)
    fun providesWaddingRef(db : FirebaseFirestore) : CollectionReference = db.collection(WADDING)

    @Provides
    @Named(ROLLWADDING)
    fun providesRollWaddingRef(db : FirebaseFirestore) : CollectionReference = db.collection(ROLLWADDING)

    @Provides
    @Named(THREAD)
    fun providesThreadRef(db : FirebaseFirestore) : CollectionReference = db.collection(THREAD)

    @Provides
    @Named(YARN)
    fun providesYarnRef(db : FirebaseFirestore) : CollectionReference = db.collection(YARN)

    @Provides
    @Named(REFLECTIVE)
    fun providesReflectiveRef(db : FirebaseFirestore) : CollectionReference = db.collection(REFLECTIVE)

    @Provides
    @Named(BUTTON)
    fun providesButtonRef(db : FirebaseFirestore) : CollectionReference = db.collection(BUTTON)

    @Provides
    @Named(PACKING)
    fun providesPackingRef(db : FirebaseFirestore) : CollectionReference = db.collection(PACKING)

    @Provides
    @Named(PACKAGE)
    fun providesPackageRef(db : FirebaseFirestore) : CollectionReference = db.collection(PACKAGE)

    @Provides
    @Named(UTILS)
    fun providesUtilsRef(db : FirebaseFirestore) : CollectionReference = db.collection(UTILS)

    @Provides
    fun provideAuthRepository(Impl : AuthRepositoryImpl) : AuthRepository = Impl

    @Provides
    fun providesUsersRepository(Impl : UsersRepositoryImpl) : UsersRepository = Impl

    @Provides
    fun providesPackageRepository(Impl : PackageRepositoryImpl) : PackageRepository = Impl

    @Provides
    fun providesUtilsRepository(Impl : UtilsRepositoryImpl) : UtilsRepository = Impl


    @Provides
    fun provideAuthUseCases(repository : AuthRepository) = AuthUseCases(
        login = Login(repository),
        getCurrentUser = GetCurrentUser(repository) 
    )

    @Provides
    fun provideUserUseCases(repository : UsersRepository) = UserUseCases(
        update = Update(repository),
        updateFinish = UpdateFinish(repository),
        getUserById = GetUserById(repository),
        getLastUserById = GetLastUserById(repository),
        getUserList = GetUserList(repository),
        updateSalary = UpdateSalary(repository),
        getMergedUser = GetMergedUser(repository),
        updateProcess = UpdateProcess(repository)
    )

    @Provides
    fun providePackageUseCases(repository : PackageRepository) = PackageUseCases(
        updatePackage = UpdatePackage(repository),
        getPackageById = GetPackageById(repository),
        getPackageList = GetPackageList(repository)
    )

    @Provides
    fun provideUtilsUseCases(repository: UtilsRepository) = UtilsUseCases(
        getPrices = GetPrices(repository)
    )

    /*
    @Provides
    fun provideClothUseCases(repository : ClothRepository) = ClothUseCases(
        createCloth = CreateCloth(repository),
        createTotalCloth = CreateTotalCloth(repository),
        getTotalMeasureById = GetTotalMeasureById(repository),
        updateTotalCloth = UpdateTotalCloth(repository),
        stockTotalCloth = StockTotalCloth(repository)
    )

    @Provides
    fun providePlotterUseCases(repository : PlotterRepository) = PlotterUseCases(
        createPlotter = CreatePlotter(repository),
        getIdPlotter = GetIdPlotter(repository),
        stockPlotter = StockPlotter(repository)

    )

    @Provides
    fun provideWaddingUseCases(repository : WaddingRepository) = WaddingUseCases(
        createWadding = CreateWadding(repository),
        updateWadding = UpdateWadding(repository),
        getTotalWadding = GetTotalWadding(repository),
        addDateWadding = AddDateWadding(repository),
        addTotalWaddingDay = AddTotalWaddingDay(repository),
        stockWadding = StockWadding(repository)
    )

    @Provides
    fun provideRollWaddingUseCases(repository : RollWaddingRepository) = RollWaddingUseCases(
        createRollWadding = CreateRollWadding(repository),
        updateRollWadding = UpdateRollWadding(repository),
        getTotalRollWadding = GetTotalRollWadding(repository),
        addDateRollWadding = AddDateRollWadding(repository),
        addTotalRollWaddingDay = AddTotalRollWaddingDay(repository),
        stockRollWadding = StockRollWadding(repository)
    )

    @Provides
    fun provideThreadUseCases(repository : ThreadRepository) = ThreadUseCases(
        createThread = CreateThread(repository),
        updateThread = UpdateThread(repository),
        getTotalThread = GetTotalThread(repository),
        addDateThread = AddDateThread(repository),
        addTotalThreadDay = AddTotalThreadDay(repository),
        stockThread = StockThread(repository)
    )

    @Provides
    fun provideYarnUseCases(repository : YarnRepository) = YarnUseCases(
        createYarn = CreateYarn(repository),
        updateYarn = UpdateYarn(repository),
        getTotalYarn = GetTotalYarn(repository),
        addDateYarn = AddDateYarn(repository),
        addTotalYarnDay = AddTotalYarnDay(repository),
        stockYarn = StockYarn(repository)
    )

    @Provides
    fun provideReflectiveUseCases(repository : ReflectiveRepository) = ReflectiveUseCases(
        createReflective = CreateReflective(repository),
        updateReflective = UpdateReflective(repository),
        getTotalReflective = GetTotalReflective(repository),
        addDateReflective = AddDateReflective(repository),
        addTotalReflectiveDay = AddTotalReflectiveDay(repository),
        stockReflective = StockReflective(repository)
    )

    @Provides
    fun provideButtonUseCases(repository : ButtonRepository) = ButtonUseCases(
        createButton = CreateButton(repository),
        updateButton = UpdateButton(repository),
        getTotalButton = GetTotalButton(repository),
        addDateButton = AddDateButton(repository),
        addTotalButtonDay = AddTotalButtonDay(repository),
        stockButton = StockButton(repository)
    )

    @Provides
    fun providePackingUseCases(repository : PackingRepository) = PackingUseCases(
        createPacking = CreatePacking(repository),
        updatePacking = UpdatePacking(repository),
        getTotalPacking = GetTotalPacking(repository),
        addDatePacking = AddDatePacking(repository),
        addTotalPackingDay = AddTotalPackingDay(repository),
        stockPacking = StockPacking(repository)
    )


     */


}