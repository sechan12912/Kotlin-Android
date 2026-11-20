package com.androidbook.sqlitedatabaseproject

class HouseKeeping {
    var seq:Int
    var category:String
    var purpose:String
    var date:String
    var money:Int
    var memo:String

    constructor(seq:Int, category:String,purpose:String, date:String,money:Int, memo:String ) {
        this.seq = seq
        this.category = category
        this.purpose = purpose
        this.date = date
        this.money = money
        this.memo = memo

    }

}
