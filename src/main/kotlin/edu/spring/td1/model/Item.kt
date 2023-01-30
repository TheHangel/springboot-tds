package edu.spring.td1.model

class Item {
    var nom: String? = null
    var evaluation : Int? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if(o !is Item) return false
        val item = o as Item
        return if (evaluation != item.evaluation) false else nom == item.nom
    }

    override fun hashCode(): Int {
        return nom.hashCode()
    }

}
