package ua.com.wl.notificator.navigation

import ua.com.wl.notificator.data.Intention

class NavigationIntentionBuilder : Intention.Builder() {

    fun navDeleteIntent(builder: NavPendingIntentBuilder) {
        deleteIntent = builder.build()
    }

    fun navDeleteIntent(init: NavPendingIntentBuilder.() -> Unit) {
        deleteIntent = NavPendingIntentBuilder().build(init)
    }

    fun navContentIntent(builder: NavPendingIntentBuilder) {
        contentIntent = builder.build()
    }

    fun navContentIntent(init: NavPendingIntentBuilder.() -> Unit) {
        contentIntent = NavPendingIntentBuilder().build(init)
    }

    internal fun build(init: NavigationIntentionBuilder.() -> Unit): Intention {
        init()
        return build()
    }
}

fun navigationNotificationIntention(init: NavigationIntentionBuilder.() -> Unit): Intention =
    NavigationIntentionBuilder().build(init)