package com.xiq.app.util

/**
 * Developed by hasham on 3/11/18.
 */

class PrettyPrintingMap<K, V>(private val map: Map<K, V>) {

    override fun toString(): String {
        val sb = StringBuilder()
        val iter = map.entries.iterator()
        while (iter.hasNext()) {
            val entry = iter.next()
            sb.append(entry.key)
            sb.append('=').append('"')
            sb.append(entry.value)
            sb.append('"')
            if (iter.hasNext()) {
                sb.append(',').append(' ')
            }
        }
        return sb.toString()

    }
}
