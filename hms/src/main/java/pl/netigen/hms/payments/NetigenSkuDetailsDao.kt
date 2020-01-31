package pl.netigen.hms.payments

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NetigenSkuDetailsDao {
    @Query("SELECT * FROM purchase_table")
    fun cachedPurchases(): LiveData<List<CachedPurchase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(cachedPurchase: CachedPurchase)
}