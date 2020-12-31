package ua.com.wl.notifications

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.core.os.bundleOf
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import ua.com.wl.constants.Extras
import ua.com.wl.constants.IntentActions
import ua.com.wl.messaging.R
import ua.com.wl.notifications.ext.*
import ua.com.wl.notifications.utils.jsonToStringArray
import ua.com.wl.notificator.data.*
import ua.com.wl.notificator.utils.defaultNotificationSound

open class NotificationsService : FirebaseMessagingService() {

    protected open lateinit var gson: Gson

    var defaultNotificationAlarm: Alarm = notificationAlarm {
        sound { defaultNotificationSound() }
        vibrate { longArrayOf(500L, 500L, 500L, 500L) }
        ledLight { LEDLight(argb = Color.YELLOW) }
        capturePolicy { CapturePolicy.ALLOW_BY_ALL }
    }

    var defaultNotificationIcons: Icons = notificationIcons {
        smallIcon { iconFromMetaData() }
        smallTint { colorFromMetaData() }
    }

    var defaultNotificationChannel: Channel = notificationChannel {
        importance { Importance.HIGH }
        channelInfo {
            channelId { FirebaseConstants.FCM_CHANNEL_ID_DEFAULT }
            channelName { getString(R.string.fcm_channel_name_default) }
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.data.let { data ->
            if (data.containsKey(FirebaseConstants.DATA_KEY_SHOW_PUSH)) {
                when (data[FirebaseConstants.DATA_KEY_SHOW_PUSH]) {
                    FirebaseConstants.PUSH_TYPE_SHOP -> createShopNotification(data)
                    FirebaseConstants.PUSH_TYPE_RANK -> createRankNotification(data)
                    FirebaseConstants.PUSH_TYPE_OFFER -> createOfferNotification(data)
                    FirebaseConstants.PUSH_TYPE_ORDER -> createOrderNotification(data)
                    FirebaseConstants.PUSH_TYPE_PRE_ORDER -> createPreOrderNotification(data)
                    FirebaseConstants.PUSH_TYPE_RESERVATION -> createReservationNotification(data)
                    FirebaseConstants.PUSH_TYPE_ARTICLE -> createArticleNotification(data)
                    FirebaseConstants.PUSH_TYPE_SHOP_REWARD -> createShopRewardNotification(data)
                    FirebaseConstants.PUSH_TYPE_BONUSES_REWARD -> createBonusesRewardNotification(
                        data
                    )
                    FirebaseConstants.PUSH_TYPE_CASH_BACK_ACCRUED -> createCashBackAccruedNotification(
                        data
                    )
                    FirebaseConstants.PUSH_TYPE_BIRTHDAY_CASH_BACK -> createBirthdayCashBackNotification(
                        data
                    )
                }

                baseContext.sendLocalBroadcastMessage(IntentActions.RECEIVER_ACTION_CONSUMER_PROFILE)
            }
        }
    }

    override fun onNewToken(token: String) {
        baseContext.sendLocalBroadcastMessage(
            IntentActions.RECEIVER_ACTION_CONSUMER_PROFILE,
            bundleOf(FirebaseConstants.EXTRA_TOKEN to token)
        )
    }

    private fun showNotification(
        title: String? = null,
        message: String? = null,
        intentExtras: Bundle? = null,
        launchInSingleTask: Boolean = false
    ) {
        notification {
            alarm = defaultNotificationAlarm
            icons = defaultNotificationIcons
            content {
                title { title }
                plainText { message }
                withTextStyle {
                    title { title }
                    bigText { message }
                    behaviour { StyleBehaviour.OVERRIDE }
                }
            }
            channel = defaultNotificationChannel
            intention {
                autoCancel { true }
                contentIntent {
                    requestCode { FirebaseConstants.REQUEST_CODE_PUSH_NOTIFICATION }
                    targetIntent { From.ACTIVITY }
                    packageContext { applicationContext }
                    taskStackElement {
                        howPut { HowPut.ONLY_NEXT_INTENT }
                        intent {
                            from { ConstructFrom.COMPONENT_NAME }
                            context { applicationContext }
                            targetClass { findAppLauncherClass() }
                            intentExtras { intentExtras }
                            intentBehaviour {
                                if (launchInSingleTask) {
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                } else {
                                    Intent.FLAG_ACTIVITY_SINGLE_TOP
                                }
                            }
                        }
                    }
                    pendingFlag {
                        if (launchInSingleTask) {
                            PendingIntent.FLAG_ONE_SHOT
                        } else {
                            PendingIntent.FLAG_UPDATE_CURRENT
                        }
                    }
                }
            }
            identifier {
                id { randomId() }
            }
        }.show(applicationContext)
    }

    protected open fun createRankNotification(data: Map<String, String>) {
        showNotification(
            title = data[FirebaseConstants.DATA_KEY_TITLE],
            message = data[FirebaseConstants.DATA_KEY_BODY],
            intentExtras = bundleOf(Extras.EXTRA_PUSH_TYPE to Extras.PUSH_TYPE_RANK)
        )
    }

    protected open fun createShopNotification(data: Map<String, String>) {
        data[FirebaseConstants.DATA_KEY_BODY_LOC_ARGS]?.let { json ->
            val args = jsonToStringArray(json, gson)
            if (args.size == 1) {
                var shopId = data[FirebaseConstants.DATA_KEY_ARG_SHOP]
                if (shopId == null) {
                    shopId = data[FirebaseConstants.DATA_KEY_ARG_SHOP_ID]
                }
                if (shopId != null) {
                    showNotification(
                        title = stringByName(data[FirebaseConstants.DATA_KEY_TITLE_LOC_KEY]),
                        message = stringByName(data[FirebaseConstants.DATA_KEY_BODY_LOC_KEY], args),
                        intentExtras = bundleOf(
                            Extras.EXTRA_SHOP_ID to shopId.toInt(),
                            Extras.EXTRA_PUSH_TYPE to Extras.PUSH_TYPE_SHOP
                        )
                    )
                }
            }
        }
    }

    protected open fun createOfferNotification(data: Map<String, String>) {
        val offerId = data[FirebaseConstants.DATA_KEY_ARG_OFFER_ID]
        if (offerId != null) {
            showNotification(
                title = data[FirebaseConstants.DATA_KEY_TITLE],
                message = data[FirebaseConstants.DATA_KEY_BODY],
                intentExtras = bundleOf(
                    Extras.EXTRA_OFFER_ID to offerId.toInt(),
                    Extras.EXTRA_PUSH_TYPE to Extras.PUSH_TYPE_OFFER
                )
            )
        }
    }

    protected open fun createOrderNotification(data: Map<String, String>) {
        data[FirebaseConstants.DATA_KEY_BODY_LOC_ARGS]?.let { json ->
            val args = jsonToStringArray(json, gson)
            if (args.size == 1) {
                val orderId = data[FirebaseConstants.DATA_KEY_ARG_ORDER_ID]
                if (orderId != null) {
                    showNotification(
                        title = stringByName(data[FirebaseConstants.DATA_KEY_TITLE_LOC_KEY]),
                        message = stringByName(data[FirebaseConstants.DATA_KEY_BODY_LOC_KEY], args),
                        intentExtras = bundleOf(
                            Extras.EXTRA_ORDER_ID to orderId.toInt(),
                            Extras.EXTRA_PUSH_TYPE to Extras.PUSH_TYPE_ORDER
                        ),
                        launchInSingleTask = true
                    )
                }
            }
        }
    }

    protected open fun createPreOrderNotification(data: Map<String, String>) {
        data[FirebaseConstants.DATA_KEY_BODY_LOC_ARGS]?.let { json ->
            val args = jsonToStringArray(json, gson)
            val preOrderId = data[FirebaseConstants.DATA_KEY_ARG_PRE_ORDER_ID]
            if (preOrderId != null) {
                showNotification(
                    title = stringByName(data[FirebaseConstants.DATA_KEY_TITLE_LOC_KEY]),
                    message = stringByName(data[FirebaseConstants.DATA_KEY_BODY_LOC_KEY], args),
                    intentExtras = bundleOf(
                        Extras.EXTRA_PRE_ORDER_ID to preOrderId.toInt(),
                        Extras.EXTRA_PUSH_TYPE to Extras.PUSH_TYPE_PRE_ORDER
                    )
                )
            }
        }
    }

    protected open fun createReservationNotification(data: Map<String, String>) {
        data[FirebaseConstants.DATA_KEY_BODY_LOC_ARGS]?.let { json ->
            val args = jsonToStringArray(json, gson)
            if (args.size == 1) {
                val reservationId = data[FirebaseConstants.DATA_KEY_ARG_RESERVATION_ID]
                if (reservationId != null) {
                    showNotification(
                        title = stringByName(data[FirebaseConstants.DATA_KEY_TITLE_LOC_KEY]),
                        message = stringByName(data[FirebaseConstants.DATA_KEY_BODY_LOC_KEY], args),
                        intentExtras = bundleOf(
                            Extras.EXTRA_RESERVATION_ID to reservationId.toInt(),
                            Extras.EXTRA_PUSH_TYPE to Extras.PUSH_TYPE_RESERVATION
                        )
                    )
                }
            }
        }
    }

    protected open fun createArticleNotification(data: Map<String, String>) {
        val articleId = data[FirebaseConstants.DATA_KEY_ARG_ARTICLE_ID]
        if (articleId != null) {
            showNotification(
                title = data[FirebaseConstants.DATA_KEY_TITLE],
                message = data[FirebaseConstants.DATA_KEY_BODY],
                intentExtras = bundleOf(
                    Extras.EXTRA_ARTICLE_ID to articleId.toInt(),
                    Extras.EXTRA_PUSH_TYPE to Extras.PUSH_TYPE_ARTICLE
                )
            )
        }
    }

    protected open fun createShopRewardNotification(data: Map<String, String>) {
        data[FirebaseConstants.DATA_KEY_BODY_LOC_ARGS]?.let { json ->
            val args = jsonToStringArray(json, gson)
            if (args.size == 2) {
                showNotification(
                    title = stringByName(data[FirebaseConstants.DATA_KEY_TITLE_LOC_KEY]),
                    message = stringByName(data[FirebaseConstants.DATA_KEY_BODY_LOC_KEY], args),
                    intentExtras = bundleOf(Extras.EXTRA_PUSH_TYPE to Extras.PUSH_TYPE_SHOP_REWARD)
                )
            }
        }
    }

    protected open fun createBonusesRewardNotification(data: Map<String, String>) {
        data[FirebaseConstants.DATA_KEY_BODY_LOC_ARGS]?.let { json ->
            val args = jsonToStringArray(json, gson)
            if (args.size == 1) {
                showNotification(
                    title = stringByName(data[FirebaseConstants.DATA_KEY_TITLE_LOC_KEY]),
                    message = stringByName(data[FirebaseConstants.DATA_KEY_BODY_LOC_KEY], args),
                    intentExtras = bundleOf(Extras.EXTRA_PUSH_TYPE to Extras.PUSH_TYPE_BONUSES_REWARD)
                )
            }
        }
    }

    protected open fun createCashBackAccruedNotification(data: Map<String, String>) {
        data[FirebaseConstants.DATA_KEY_BODY_LOC_ARGS]?.let { json ->
            val args = jsonToStringArray(json, gson)
            if (args.size == 3) {
                showNotification(
                    title = stringByName(data[FirebaseConstants.DATA_KEY_TITLE_LOC_KEY]),
                    message = stringByName(data[FirebaseConstants.DATA_KEY_BODY_LOC_KEY], args),
                    intentExtras = bundleOf(Extras.EXTRA_PUSH_TYPE to Extras.PUSH_TYPE_CASH_BACK_ACCRUED)
                )
            }
        }
    }

    protected open fun createBirthdayCashBackNotification(data: Map<String, String>) {
        showNotification(
            title = stringByName(data[FirebaseConstants.DATA_KEY_TITLE_LOC_KEY]),
            message = data[FirebaseConstants.DATA_KEY_BODY],
            intentExtras = bundleOf(Extras.EXTRA_PUSH_TYPE to Extras.PUSH_TYPE_BIRTHDAY_CASH_BACK)
        )
    }

    private fun randomId(): Int = (Math.random() * (Short.MAX_VALUE - Short.MIN_VALUE)).toInt()
}