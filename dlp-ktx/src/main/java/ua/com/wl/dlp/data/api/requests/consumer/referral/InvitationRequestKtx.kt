package ua.com.wl.dlp.data.api.requests.consumer.referral

class InvitationRequestBuilder {
    private var code: String = ""

    fun code(init: () -> String) {
        code = init()
    }

    fun build(init: InvitationRequestBuilder.() -> Unit): InvitationRequest {
        init()
        return InvitationRequest(code)
    }
}

fun invitationRequest(init: InvitationRequestBuilder.() -> Unit): InvitationRequest {
    return InvitationRequestBuilder().build(init)
}