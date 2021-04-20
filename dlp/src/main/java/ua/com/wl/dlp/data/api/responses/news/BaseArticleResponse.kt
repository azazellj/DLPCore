package ua.com.wl.dlp.data.api.responses.news

@Deprecated(
    message = "Moved all to ArticleResponse",
    replaceWith = ReplaceWith(
        expression = "ArticleResponse",
        imports = ["ua.com.wl.dlp.data.api.responses.news.ArticleResponse"],
    ),
    level = DeprecationLevel.ERROR
)
class BaseArticleResponse