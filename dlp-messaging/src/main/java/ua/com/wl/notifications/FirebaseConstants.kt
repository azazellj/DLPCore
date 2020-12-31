package ua.com.wl.notifications

object FirebaseConstants {

    const val EXTRA_TOKEN = "token"

    const val FCM_CHANNEL_ID_DEFAULT = "channel_general"

    const val REQUEST_CODE_PUSH_NOTIFICATION = 1522

    const val DATA_KEY_SHOW_PUSH = "show_push_type"

    //---- Push types
    // shop
    const val PUSH_TYPE_SHOP = "show_shop"

    // rank
    const val PUSH_TYPE_RANK = "rank_reached"

    // offer
    const val PUSH_TYPE_OFFER = "show_offer"

    // order
    const val PUSH_TYPE_ORDER = "new_order"

    // pre order & reservation
    const val PUSH_TYPE_PRE_ORDER = "show_pre_order"
    const val PUSH_TYPE_RESERVATION = "show_table_reservation"

    // news
    const val PUSH_TYPE_ARTICLE = "show_news_item"

    // cash back
    const val PUSH_TYPE_SHOP_REWARD = "shop_reward"
    const val PUSH_TYPE_BONUSES_REWARD = "bonuses_reward"
    const val PUSH_TYPE_CASH_BACK_ACCRUED = "cash_back"
    const val PUSH_TYPE_BIRTHDAY_CASH_BACK = "show_qr_code"
    //---------------

    //- Data title(and args) and body(and args) keys
    const val DATA_KEY_TITLE = "title"
    const val DATA_KEY_BODY = "body"
    const val DATA_KEY_TITLE_LOC_KEY = "title_loc_key"
    const val DATA_KEY_TITLE_LOC_ARGS = "title_loc_args"
    const val DATA_KEY_BODY_LOC_KEY = "body_loc_key"
    const val DATA_KEY_BODY_LOC_ARGS = "body_loc_args"

    //- Data arguments keys
    const val DATA_KEY_ARG_SHOP = "shop"
    const val DATA_KEY_ARG_SHOP_ID = "shop_id"
    const val DATA_KEY_ARG_OFFER_ID = "offer_id"
    const val DATA_KEY_ARG_ORDER_ID = "rate_id"
    const val DATA_KEY_ARG_PRE_ORDER_ID = "pre_order_id"
    const val DATA_KEY_ARG_RESERVATION_ID = "table_reservation_id"
    const val DATA_KEY_ARG_ARTICLE_ID = "news_item_id"
    //---------------
}