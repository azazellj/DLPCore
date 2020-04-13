package ua.com.wl.dlp.data.api.requests.consumer.referral

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class InvitationRequest(
    @SerializedName("invite_code")
    val code: String
) {

    class Builder {

        private var code: String = ""

        fun code(init: () -> String) {
            code = init()
        }

        fun build(init: Builder.() -> Unit): InvitationRequest {
            init()
            return InvitationRequest(code)
        }
    }
}

fun invitationRequest(init: InvitationRequest.Builder.() -> Unit): InvitationRequest {
    return InvitationRequest.Builder().build(init)
}