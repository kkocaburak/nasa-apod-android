package com.bkarakoca.core.base

interface ListAdapterItem {
    val id: Long?
    override fun equals(other: Any?): Boolean
}
