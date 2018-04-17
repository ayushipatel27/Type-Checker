package typechecker

import scala.io.Source
import scala.collection.mutable.HashMap

class typeChecker(lines: List[String], env: List[String]){
    var envRule = env.map(str => {
        var spl = str.replaceAll("\\s","").replaceAll("&","").split('(')
        val operator = spl(0).trim()
        spl = spl(1).split(')')
        var temp = spl(0).split(',')
        val parameter = temp.toList
        spl = spl(1).split('>')
        val result = spl(1).trim()
        ops(operator, parameter, result)
    })
    
    var linesRule = lines.map(str => {
        var spl = str.replaceAll("\\s","").replaceAll("&","").split('(')
        val operator = spl(0).trim()
        spl = spl(1).split(')')
        var temp = spl(0).split(',')
        val parameter = temp.toList
        spl = spl(1).split('>')
        val result = spl(1).trim()
        ops(operator, parameter, result)
    })
    
    var hash = collection.mutable.Map[String, String]()
    
    while(!linesRule.isEmpty){
        var item = linesRule.head
        var newlist = envRule.filter(x => x.operator == item.operator)
        newlist = newlist.filter(x => x.parameters.size == item.parameters.size)
        println(newlist)
        println(item)
        linesRule = linesRule.tail
    }
    
    println("All operations type matched")
    System.exit(0)
}

object Main{
    def main(args: Array[String]): Unit = {
        val filename = scala.io.Source.fromFile(args(0))
        val fn = scala.io.Source.fromFile(args(1))
        
        val env = fn.getLines.toList
        val lines = filename.getLines.toList
        
        val tc = new typeChecker(lines, env)
    }
}

case class ops(operator:String, parameters:List[String], result: String){}

// head takes the first element
// tail takes the rest

// to compile scalac test.scale
// to execute scala test.scala input.txt env.txt
// this will compile and run it at the same time