package com.cherry.guessthething.util

import com.cherry.guessthething.model.Cartoon
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.trimHtmlCode(): String {
    return this.substringAfter("sect-content").substringBefore("bottom-nav clr ignore-select")
}

 fun parseHtmlToCartoonList(trimmedHtmlCode: String): ArrayList<Cartoon> {
    val imagePattern: Pattern = Pattern.compile("<img src=\"(.*?)\" alt=\"")
    val imageMatcher: Matcher = imagePattern.matcher(trimmedHtmlCode)

    val namePattern: Pattern = Pattern.compile("alt=\"(.*?)\">")
    val nameMatcher: Matcher = namePattern.matcher(trimmedHtmlCode)
    val imgList: ArrayList<String> = arrayListOf()
    val nameList: ArrayList<String> = arrayListOf()
    val fullCartoonList: ArrayList<Cartoon> = arrayListOf()
    while (imageMatcher.find()) {
        imageMatcher.group(1)?.let {
            imgList.add(it)
        }
    }
    while (nameMatcher.find()) {
        nameMatcher.group(1)?.let {
            nameList.add(it)
        }
    }
    for (i in 0 until imgList.size) {
        fullCartoonList.add(Cartoon(imgList[i], nameList[i]))
    }
    return fullCartoonList
}