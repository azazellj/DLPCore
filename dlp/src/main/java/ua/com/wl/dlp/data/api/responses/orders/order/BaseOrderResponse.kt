package ua.com.wl.dlp.data.api.responses.orders.order

@Deprecated(
    message = "Moved all to OrderResponse",
    replaceWith = ReplaceWith(
        expression = "OrderResponse",
        imports = ["ua.com.wl.dlp.data.api.responses.orders.order.OfferResponse"],
    ),
    level = DeprecationLevel.ERROR
)
class BaseOrderResponse