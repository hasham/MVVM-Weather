package com.example.mvvmweather.data.local.db





/**
 * Developed by hasham on 21-Nov-17.
 */
//@Dao
//abstract class AlertCategoryDao {
//
//    @Transaction
//    open fun updateData(alerts: List<AlertCategory>) {
//        deleteAlertCategory()
//        insertAlertCategory(alerts)
//    }
//
//    @Query("SELECT * FROM AlertCategory")
//    abstract fun getAlertCategories(): List<AlertCategory>
//
//    @Query("SELECT * FROM AlertCategory WHERE id = :id")
//    abstract fun getAlertCategoryById(id: Long): List<AlertCategory>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insertAlertCategory(alerts: List<AlertCategory>)
//
//    @Query("DELETE FROM AlertCategory")
//    abstract fun deleteAlertCategory()
//}
//
//
//@Dao
//abstract class UserDao {
//
//    @Transaction
//    open fun updateUser(user: User) {
//        deleteUser()
//        insertUser(user)
//    }
//
//    @Query("SELECT * FROM User")
//    abstract fun getUser(): List<User>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insertUser(user: User)
//
//    @Query("DELETE FROM User")
//    abstract fun deleteUser()
//}
//
//@Dao
//abstract class RecentAccountsDao {
//
//    @Transaction
//    open fun updateData(accounts: List<RecentAccount>) {
//        deleteRecentAccount()
//        insertRecentAccount(accounts)
//    }
//
//    @Query("SELECT * FROM RecentAccount ORDER BY created_at DESC")
//    abstract fun getRecentAccounts(): List<RecentAccount>
//
//    @Query("SELECT * FROM RecentAccount WHERE company_id = :id")
//    abstract fun getRecentAccountById(id: Long): List<RecentAccount>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insertRecentAccount(alerts: List<RecentAccount>)
//
//    @Query("DELETE FROM RecentAccount where company_id NOT IN (SELECT company_id from RecentAccount ORDER BY created_at DESC LIMIT 4)")
//    abstract fun deleteRecentAccount()
//
//    @Query("DELETE FROM RecentAccount")
//    abstract fun deleteAllRecentAccount()
//}
//
//@Dao
//abstract class RecentIndustryDao {
//
//    @Transaction
//    open fun updateData(industries: List<RecentIndustry>) {
//        deleteRecentIndustry()
//        insertRecentIndustry(industries)
//    }
//
//    @Query("SELECT * FROM RecentIndustry ORDER BY created_at DESC")
//    abstract fun getRecentIndustry(): List<RecentIndustry>
//
//    @Query("SELECT * FROM RecentIndustry WHERE industry_id = :id")
//    abstract fun getRecentIndustryById(id: Long): List<RecentIndustry>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insertRecentIndustry(alerts: List<RecentIndustry>)
//
//    @Query("DELETE FROM RecentIndustry where industry_id NOT IN (SELECT industry_id from RecentIndustry ORDER BY created_at DESC LIMIT 4)")
//    abstract fun deleteRecentIndustry()
//
//    @Query("DELETE FROM RecentIndustry")
//    abstract fun deleteAllRecentIndustry()
//}
//
//@Dao
//abstract class RecentServiceLineDao {
//
//    @Transaction
//    open fun updateData(serviceLines: List<RecentServiceLine>) {
//        deleteRecentServiceLine()
//        insertRecentServiceLine(serviceLines)
//    }
//
//    @Query("SELECT * FROM RecentServiceLine ORDER BY created_at DESC")
//    abstract fun getRecentServiceLine(): List<RecentServiceLine>
//
//    @Query("SELECT * FROM RecentServiceLine WHERE industry_id = :id")
//    abstract fun getRecentServiceLineId(id: Long): List<RecentServiceLine>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insertRecentServiceLine(alerts: List<RecentServiceLine>)
//
//    @Query("DELETE FROM RecentServiceLine where industry_id NOT IN (SELECT industry_id from RecentServiceLine ORDER BY created_at DESC LIMIT 4)")
//    abstract fun deleteRecentServiceLine()
//
//    @Query("DELETE FROM RecentServiceLine")
//    abstract fun deleteAllRecentServiceLines()
//}
//
//@Dao
//abstract class RecentNetworkDao {
//
//    @Transaction
//    open fun updateData(networks: List<RecentNetwork>) {
//        deleteRecentNetwork()
//        insertRecentNetwork(networks)
//    }
//
//    @Query("SELECT * FROM RecentNetwork ORDER BY created_at DESC")
//    abstract fun getRecentNetwork(): List<RecentNetwork>
//
//    @Query("SELECT * FROM RecentNetwork WHERE network_id = :id")
//    abstract fun getRecentNetworkById(id: Long): List<RecentNetwork>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insertRecentNetwork(alerts: List<RecentNetwork>)
//
//    @Query("DELETE FROM RecentNetwork where network_id NOT IN (SELECT network_id from RecentNetwork ORDER BY created_at DESC LIMIT 9)")
//    abstract fun deleteRecentNetwork()
//
//    @Query("DELETE FROM RecentNetwork")
//    abstract fun deleteAllRecentNetwork()
//}
//
//object DateConverter {
//    @TypeConverter
//    fun fromTimestamp(value: Long?): Date? {
//        return value?.let { Date(it) }
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: Date?): Long? {
//        return date?.time
//    }
//}