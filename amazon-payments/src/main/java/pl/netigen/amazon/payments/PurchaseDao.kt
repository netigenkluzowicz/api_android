package pl.netigen.amazon.payments

import androidx.room.*
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


    @Query("DELETE FROM purchase_table WHERE sku = :sku")
    fun delete(sku: String)
}
