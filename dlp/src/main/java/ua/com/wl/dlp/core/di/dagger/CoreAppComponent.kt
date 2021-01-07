package ua.com.wl.dlp.core.di.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        CoreApiModule::class,
        CoreAppModule::class,
        CoreDbModule::class,
        CoreInteractorsModule::class,
        CorePreferencesModule::class
    ]
)
interface CoreAppComponent {
    @Component.Builder
    interface CoreAppComponentBuilder {
        @BindsInstance
        fun application(application: Application): CoreAppComponentBuilder

        fun apiModule(apiModule: CoreApiModule): CoreAppComponentBuilder
        fun appModule(appModule: CoreAppModule): CoreAppComponentBuilder
        fun dbModule(dbModule: CoreDbModule): CoreAppComponentBuilder
        fun interactorsModule(interactorsModule: CoreInteractorsModule): CoreAppComponentBuilder
        fun preferencesModule(preferencesModule: CorePreferencesModule): CoreAppComponentBuilder

        fun build(): CoreAppComponent
    }
}