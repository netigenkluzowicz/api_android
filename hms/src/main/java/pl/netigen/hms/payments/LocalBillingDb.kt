/**
 * Copyright (C) 2018 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.netigen.hms.payments

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [CachedPurchase::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(InAppPurchaseDataTypeConverter::class)
abstract class LocalBillingDb : RoomDatabase() {
    abstract fun purchaseDao(): PurchaseDao

    companion object {
        @Volatile
        private var INSTANCE: LocalBillingDb? = null
        private const val DATABASE_NAME = "hms_purchase_db"

        fun getInstance(context: Context): LocalBillingDb =
            INSTANCE ?: synchronized(this) { INSTANCE ?: buildDatabase(context.applicationContext).also { INSTANCE = it } }

        private fun buildDatabase(appContext: Context): LocalBillingDb =
            Room.databaseBuilder(appContext, LocalBillingDb::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
