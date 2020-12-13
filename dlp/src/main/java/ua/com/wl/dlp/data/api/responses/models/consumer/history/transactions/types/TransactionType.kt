package ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.types

import com.squareup.moshi.Json

enum class TransactionType {
    @Json(name = "REGISTRATION")
    REGISTRATION,

    @Json(name = "PROVIDE_PERSONAL_FIRST_NAME")
    PROVIDE_PERSONAL_FIRST_NAME,

    @Json(name = "PROVIDE_PERSONAL_LAST_NAME")
    PROVIDE_PERSONAL_LAST_NAME,

    @Json(name = "PROVIDE_PERSONAL_PATRONYMIC")
    PROVIDE_PERSONAL_PATRONYMIC,

    @Json(name = "PROVIDE_PERSONAL_GENDER")
    PROVIDE_PERSONAL_GENDER,

    @Json(name = "PROVIDE_PERSONAL_EMAIL")
    PROVIDE_PERSONAL_EMAIL,

    @Json(name = "PROVIDE_PERSONAL_MOBILE_PHONE")
    PROVIDE_PERSONAL_MOBILE_PHONE,

    @Json(name = "PROVIDE_PERSONAL_ADDRESS")
    PROVIDE_PERSONAL_ADDRESS,

    @Json(name = "PROVIDE_PERSONAL_BIRTH_DATE")
    PROVIDE_PERSONAL_BIRTH_DATE,

    @Json(name = "PROVIDE_PERSONAL_IS_MARRIED")
    PROVIDE_PERSONAL_IS_MARRIED,

    @Json(name = "REFSYS_LEADER_REWARD")
    REFSYS_LEADER_REWARD,

    @Json(name = "REFSYS_LEADER_REGULAR_REWARD")
    REFSYS_LEADER_REGULAR_REWARD,

    @Json(name = "REFSYS_FOLLOWER_REWARD")
    REFSYS_FOLLOWER_REWARD,

    @Json(name = "REFSYS_SHOP_FOLLOWER_REWARD")
    REFSYS_SHOP_FOLLOWER_REWARD,

    @Json(name = "IN_SYSTEM")
    IN_SYSTEM,

    @Json(name = "SYSTEM_GIFT")
    SYSTEM_GIFT,

    @Json(name = "OFFER_VIEW")
    OFFER_VIEW,

    @Json(name = "OFFER_SHARE")
    OFFER_SHARE,

    @Json(name = "NEWS_ITEM_VIEW")
    NEWS_ITEM_VIEW,

    @Json(name = "NEWS_ITEM_SHARE")
    NEWS_ITEM_SHARE,

    @Json(name = "REFILL")
    REFILL,

    @Json(name = "WITHDRAWN")
    WITHDRAWN,

    @Json(name = "PAYMENT")
    PAYMENT,

    @Json(name = "CANCELLATION")
    CANCELLATION,

    @Json(name = "CASH_BACK")
    CASH_BACK,

    @Json(name = "SHOP_REWARD")
    SHOP_REWARD,

    @Json(name = "BIRTHDAY_REWARD")
    BIRTHDAY_REWARD,

    @Json(name = "BUSINESS_REWARD")
    BUSINESS_REWARD
}