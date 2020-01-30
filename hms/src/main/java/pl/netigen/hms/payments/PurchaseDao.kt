package pl.netigen.hms.payments

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {
    @Query("SELECT * FROM purchase_table")
    fun getPurchasesFlow(): Flow<List<CachedPurchase>>

    @Insert
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
    fun deleteAll()

    @Query("DELETE FROM purchase_table WHERE data = :purchase")
    fun delete(purchase: Purchase)

}
