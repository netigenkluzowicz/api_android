package pl.netigen.hms.payments

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.huawei.hms.iap.entity.InAppPurchaseData
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {
    @Query("SELECT * FROM purchase_table")
    fun getPurchasesFlow(): Flow<List<CachedPurchase>>

    @Insert
    suspend fun insert(purchase: CachedPurchase)

    @Delete
    suspend fun delete(vararg purchases: CachedPurchase)

    @Query("DELETE FROM purchase_table")
    fun deleteAll()

    @Query("DELETE FROM purchase_table WHERE data = :purchase")
    fun delete(purchase: InAppPurchaseData)

}
