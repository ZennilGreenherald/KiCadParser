@main def main(): Unit =

    println("Please enter the filename of the .pro file:")
    val filename = io.Source.stdin.getLines().next()

    var values = Map[String, String]()

    io.Source.fromFile(filename).getLines.foreach { _line =>
        val line = _line.trim()
        var section = ""
        if (line.head == '[' && line.last == ']') {
            section = line.tail.init
        }
        else if (line.contains('=')) {
            line.split("=") match
                case Array(key, value) =>
                    values = values + ((s"$section.$key", value))
                case Array(key) =>
                    values = values + ((s"$section.$key", ""))
        }
        else
            println(s"Bad input line: $line")
    }

    println("Values in file " + filename + ":")
    values.foreach{ case (k, v) =>
        println(s"$k = $v")
    }
