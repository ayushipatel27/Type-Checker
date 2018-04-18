import scala.io.Source
import scala.collection.mutable.HashMap

class typeChecker(expressionLines: List[String], environmentLines: List[String]){
	var envRule = environmentLines.map(str => {
		var spl = str.replaceAll("\\s","").replaceAll("&","").split('(')
				val operator = spl(0).trim()
				spl = spl(1).split(')')

				var temp = spl(0).split(',')
				val parameter = temp.toList

				spl = spl(1).split('>')
				val result = spl(1).trim()

				ops(operator, parameter, result)
	})

			var linesRule = expressionLines.map(str => {
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

			// head takes the first element
			// tail takes the rest
			while(!linesRule.isEmpty){
				var item = linesRule.head
						var newlist = envRule.filter(x => x.operator == item.operator)
						newlist = newlist.filter(x => x.parameters.size == item.parameters.size)

						var size = item.parameters.size - 1
						var index = 0
						for(index <- 0 to size){
							var value = item.parameters(index)
									if (value.charAt(0) == '$') {
										if (hash.contains(value)) {
											var hashedvalue = hash(value)
													if (hashedvalue == newlist.head.parameters(index)){ 
														println("Already in map")
													}
													else {
														println("FAILURE.")
														System.exit(1)
													}
										}
										else {
											hash.put(value, newlist.head.parameters(index))
											println("inserted into map")
										}
									}
						}
				var results = item.result
						if (hash.contains(results)) {
							var hashedvalue = hash(results)
									if (hashedvalue == newlist.head.result){
										println("Already in map")
									}
									else {
										println("results: " + results)
										println("hashedvalue: " + hashedvalue)
										println("FAILURE.")
										System.exit(1)
									}
						}
						else {
							hash.put(results, newlist.head.result)
							println("inserted into map")
						}

				println(newlist)
				println(item)        
				println()


				linesRule = linesRule.tail
			}

	println("All operations type matched")
	System.exit(0)
}

object Main{
	def main(args: Array[String]): Unit = {
			val filename = scala.io.Source.fromFile(args(0)) // Expression text file

					val fn = scala.io.Source.fromFile(args(1)) // Environment Text file input
					val environmentLines = fn.getLines.toList

					val expressionLines = filename.getLines.toList

					val tc = new typeChecker(expressionLines, environmentLines)
	}
}

// Creates a case class for different parameters needed for the operations
case class ops(operator:String, parameters:List[String], result: String){


}