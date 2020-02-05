package pl.netigen.hms.payments

import androidx.room.*
import com.huawei.hms.iap.entity.InAppPurchaseData
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {
    @Query("SELECT * FROM purchase_table")
    fun getPurchasesFlow(): Flow<List<CachedPurchase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(purchase: CachedPurchase)

    @Delete
    suspend fun delete(vararg purchases: CachedPurchase)

    @Query("DELETE FROM purchase_table")
    suspend fun deleteAll()

    @Query("DELETE FROM purchase_table WHERE data = :purchase")
    suspend fun delete(purchase: InAppPurchaseData)

}
