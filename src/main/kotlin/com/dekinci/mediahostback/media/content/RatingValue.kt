package com.dekinci.mediahostback.media.content

enum class RatingValue(val value: Int?) {
    NONE(null),
    AWFUL(1),
    BAD(2),
    OK(3),
    GOOD(4),
    EXCELLENT(5);
}