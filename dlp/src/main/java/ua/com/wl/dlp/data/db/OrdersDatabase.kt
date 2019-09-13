package ua.com.wl.dlp.data.db

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import ua.com.wl.dlp.data.db.converters.PromoTypeConverter
import ua.com.wl.dlp.data.db.dao.orders.OffersDao
import ua.com.wl.dlp.data.db.dao.orders.ShopsDao
import ua.com.wl.dlp.data.db.entities.orders.OfferEntity
import ua.com.wl.dlp.data.db.entities.orders.ShopEntity

/**
 * @author Denis Makovskyi
 */

@Database(
    entities = [ShopEntity::class, OfferEntity::class],
    version = OrdersDatabase.VERSION, exportSchema = false)
@TypeConverters(PromoTypeConverter::class)
abstract class OrdersDatabase : RoomDatabase() {

    abstract fun getShopsDao(): ShopsDao

    abstract fun getOffersDao(): OffersDao

    companion object {

        private const val NAME = "Orders.db"
        internal const val VERSION = 1

        @Volatile
        private var INSTANCE: OrdersDatabase? = null

        operator fun invoke(context: Context): OrdersDatabase =
            synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context): OrdersDatabase =
            Room.databaseBuilder(context, OrdersDatabase::class.java, NAME)
                .build()
    }
}