package com.complete.newsreporter.model

import java.io.Serializable

data class Situations(
    val name : String,
    val desc :String
):Serializable{
    override fun toString(): String {
        return "Situations(name='$name', desc='$desc')"
    }
}