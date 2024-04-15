package com.pocketdimen.room.appconfig

import android.app.Application
import com.pocketdimen.room.appconfig.AppContainer
import com.pocketdimen.room.appconfig.AppDataContainer

class InventoryApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}