package ua.com.wl.dlp.data.db

import android.content.Context

import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import ua.com.wl.dlp.data.db.dao.shops.ShopsDao
import ua.com.wl.dlp.data.db.dao.shops.OffersDao
import ua.com.wl.dlp.data.db.dao.shops.OrdersDao
import ua.com.wl.dlp.data.db.entities.shops.ShopEntity
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.OrderEntity
import ua.com.wl.dlp.data.db.converters.PromoTypeConverter
import ua.com.wl.dlp.data.db.converters.DeliveryTypeConverter
import ua.com.wl.dlp.data.db.converters.OperatorCallTypeConverter
import ua.com.wl.dlp.data.db.converters.PaymentMethodTypeConverter

/**
 * @author Denis Makovskyi
 */

@Database(
    entities = [
        ShopEntity::class,
        OfferEntity::class,
        OrderEntity::class
    ],
    version = ShopsDatabase.VERSION,
    exportSchema = false
)
@TypeConverters(value = [
    PromoTypeConverter::class,
    DeliveryTypeConverter::class,
    OperatorCallTypeConverter::class,
    PaymentMethodTypeConverter::class
])
abstract class ShopsDatabase : RoomDatabase() {

    abstract fun getShopsDao(): ShopsDao

    abstract fun getOffersDao(): OffersDao

    abstract fun getShopsOffersDao(): OrdersDao

    companion object {

        private const val NAME = "Shops.db"
        internal const val VERSION = 2

        @Volatile
        private var INSTANCE: ShopsDatabase? = null

        operator fun invoke(context: Context): ShopsDatabase {
            return synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context: Context): ShopsDatabase {
            return Room.databaseBuilder(context, ShopsDatabase::class.java, NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}