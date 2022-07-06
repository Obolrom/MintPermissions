package ru.mintrocket.lib.mintpermissions.flows

import android.content.Context
import ru.mintrocket.lib.mintpermissions.flows.model.DialogContent
import ru.mintrocket.lib.mintpermissions.flows.model.DialogRequest

class DefaultDialogRequestMapperImpl : DialogContentMapper {

    override suspend fun map(context: Context, request: DialogRequest): DialogContent {
        return DialogContent(
            "Some title",
            "Request ${request.type} with results ${request.results.map { it.status }}",
            "Action",
            "Cancel"
        )
    }
}