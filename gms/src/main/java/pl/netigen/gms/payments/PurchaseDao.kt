package pl.netigen.gms.payments

import androidx.room.*
import com.android.billingclient.api.Purchase
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {
    @Query("SELECT * FROM purchase_table")
    fun getPurchasesFlow(): Flow<List<CachedPurchase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(purchase: CachedPurchase)

    @Transaction
    suspend fun insert(vararg purchases: Purchase) {
        purchases.forEach {
            insert(CachedPurchase(data = it))
        }
    }

    @Delete
    suspend fun delete(vararg purchases: CachedPurchase)

    @Query("DELETE FROM purchase_table")
    suspend fun deleteAll()

    @Query("DELETE FROM purchase_table WHERE data = :purchase")
    suspend fun delete(purchase: Purchase)

    @Query("SELECT * FROM purchase_table WHERE data = :purchase")
    suspend fun get(purchase: Purchase): CachedPurchase?

}
