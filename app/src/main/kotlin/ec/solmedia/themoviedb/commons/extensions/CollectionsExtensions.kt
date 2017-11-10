package ec.solmedia.themoviedb.commons.extensions

fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V?>> =
        map({ Pair(it.key, it.value) }).toTypedArray()