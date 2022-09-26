package pl.netigen.gms.payments

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

@Database(
    entities = [NetigenSkuDetails::class, CachedPurchase::class],
    version = 3,
    exportSchema = false,
)
@TypeConverters(PurchaseTypeConverter::class)
abstract class LocalBillingDb : RoomDatabase() {
    abstract fun skuDetailsDao(): NetigenSkuDetailsDao
    abstract fun purchaseDao(): PurchaseDao

    companion object {
        @Volatile
        private var INSTANCE: LocalBillingDb? = null
        private const val DATABASE_NAME = "gms_purchase_db"

        fun getInstance(context: Context): LocalBillingDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context.applicationContext).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(appContext: Context): LocalBillingDb {
            return Room.databaseBuilder(appContext, LocalBillingDb::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
