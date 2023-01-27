package edu.spring.td1.model

class Item {
    var nom: String
        get() = nom
        set(value) {nom=value}
    var evaluation : Int
        get() = evaluation
        set(value) {evaluation=value}

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        if(o !is Item) return false
        val item = o as Item
        return if (evaluation != item.evaluation) false else nom == item.nom
    }

    override fun hashCode(): Int {
        return nom.hashCode()
    }

}
