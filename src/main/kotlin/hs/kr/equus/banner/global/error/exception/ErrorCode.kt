package hs.kr.equus.banner.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    FEIGN_BAD_REQUEST(400, "Feign Bad Request"),
    FEIGN_UNAUTHORIZED(401, "Feign UnAuthorized"),
    FEIGN_FORBIDDEN(403, "Feign Forbidden"),
    FEIGN_SERVER_ERROR(500, "Feign Server Error"),

    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // Not Found
    BANNER_NOT_FOUND(404, "Banner Not Found"),
    IMAGE_NOT_FOUND(404, "Image Not Found"),

    //Bad Request
    FILE_IS_EMPTY(400, "File does not exist"),
    BAD_FILE_EXTENSION(400, "File Extension is invalid")
}