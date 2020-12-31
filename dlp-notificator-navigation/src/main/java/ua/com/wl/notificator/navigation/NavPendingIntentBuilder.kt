package ua.com.wl.notificator.navigation

import android.app.Activity
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavGraph
import ua.com.wl.notificator.dsl.PendingIntentMarker
import ua.com.wl.notificator.utils.buildMessage

@PendingIntentMarker
class NavPendingIntentBuilder {

    private var graph: NavGraph? = null
    private var graphId: Int = 0
    private var arguments: Bundle? = null
    private var destination: Int = 0
    private var componentName: ComponentName? = null
    private var activityClass: Class<out Activity>? = null
    private var packageContext: Context? = null

    fun graph(init: () -> NavGraph?) {
        graph = init()
    }

    fun graphId(init: () -> Int?) {
        graphId = init() ?: 0
    }

    fun arguments(init: () -> Bundle?) {
        arguments = init()
    }

    fun destination(init: () -> Int?) {
        destination = init() ?: 0
    }

    fun componentName(init: () -> ComponentName?) {
        componentName = init()
    }

    fun activityClass(init: () -> Class<out Activity>?) {
        activityClass = init()
    }

    fun packageContext(init: () -> Context) {
        packageContext = init()
    }

    fun build(): PendingIntent {
        val context = requireNotNull(packageContext) {
            buildMessage(
                NavPendingIntentBuilder::class,
                "To create pending intent, please pass your package context"
            )
        }
        return NavDeepLinkBuilder(context)
            .also { builder ->
                graph?.let { builder.setGraph(it) }
                if (graphId != 0) builder.setGraph(graphId)
                arguments?.let { builder.setArguments(it) }
                if (destination != 0) builder.setDestination(destination)
                componentName?.let { builder.setComponentName(it) }
                activityClass?.let { builder.setComponentName(it) }
            }.createPendingIntent()
    }

    internal fun build(init: NavPendingIntentBuilder.() -> Unit): PendingIntent {
        init()
        return build()
    }
}