package ru.mintrocket.lib.mintpermissions.flows.uirequests

import androidx.activity.ComponentActivity
import ru.mintrocket.lib.mintpermissions.MintPermissions
import ru.mintrocket.lib.mintpermissions.flows.*
import ru.mintrocket.lib.mintpermissions.tools.initializer.ManagerInitializer
import ru.mintrocket.lib.mintpermissions.tools.uirequests.UiRequestZygote

object SomeLib {

    private const val KEY_REQUESTS_SETTINGS = "mintpermissions_settings"
    private const val KEY_REQUESTS_DIALOGS = "mintpermissions_dialogs"

    private val dialogsConsumer by lazy {
        val consumer = DefaultDialogContentConsumerImpl()
        val mapper = DefaultDialogRequestMapperImpl()
        DialogsConsumer(consumer, mapper)
    }
    private val dialogsZygote by lazy { UiRequestZygote(KEY_REQUESTS_DIALOGS, dialogsConsumer) }

    private val settingsConsumer by lazy { AppSettingsConsumer() }
    private val settingsZygote by lazy { UiRequestZygote(KEY_REQUESTS_SETTINGS, settingsConsumer) }

    val controller by lazy {
        MintPermissionsDialogFlowImpl(
            MintPermissions.controller,
            dialogsZygote.controller,
            settingsZygote.controller
        )
    }

    fun createManager(): SomeLibManager = SomeLibManager(
        dialogsZygote.createManager(),
        settingsZygote.createManager()
    )
}

class SomeLibManager(
    private val dialogsManager: ManagerInitializer,
    private val settingsManager: ManagerInitializer,
) {

    fun init(activity: ComponentActivity) {
        dialogsManager.init(activity)
        settingsManager.init(activity)
    }
}

