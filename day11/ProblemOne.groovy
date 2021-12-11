import groovy.transform.EqualsAndHashCode

class ProblemOne {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day11',
            'input.txt').collect {it} //as int[]
        //input = input[0..10]
        println "input: size:${input.size()}"
        println input

        def colony = loadColony(input)
        println "colony loaded, population = ${colony.size()}"
        println colony.first()
        println colony.last()

        def stepLimit = 1000

        displayColony(colony)

        for (int step in 1 .. stepLimit) {
            println "running step #${step}"
            colony.each {
                it.powerUp()
            }

            def flashDetected = true
            while (flashDetected) {
                flashDetected = false
                colony.each {octopus ->
                    if (octopus.canFlash() && !octopus.hasFlashed) {
                        flashDetected = true
                        octopus.flash()
                        //Adjacents
                        colony.find{it.p.x == (octopus.p.x-1)  && it.p.y == (octopus.p.y-1)}?.powerUp() //nw
                        colony.find{it.p.x == (octopus.p.x-1)  && it.p.y == (octopus.p.y)}?.powerUp() //w
                        colony.find{it.p.x == (octopus.p.x-1)  && it.p.y == (octopus.p.y+1)}?.powerUp() //sw
                        colony.find{it.p.x == (octopus.p.x)  && it.p.y == (octopus.p.y-1)}?.powerUp() //n
                        colony.find{it.p.x == (octopus.p.x)  && it.p.y == (octopus.p.y+1)}?.powerUp() //s
                        colony.find{it.p.x == (octopus.p.x+1)  && it.p.y == (octopus.p.y-1)}?.powerUp() //ne
                        colony.find{it.p.x == (octopus.p.x+1)  && it.p.y == (octopus.p.y)}?.powerUp() //e
                        colony.find{it.p.x == (octopus.p.x+1)  && it.p.y == (octopus.p.y+1)}?.powerUp() //se
                    }
                }
                println "--"
            }

            println colony.findAll { it.hasFlashed }.size()
            if (colony.findAll { it.hasFlashed }.size() == colony.size()) {
                println "all flashed on step ${step}"
                colony.each {
                    it.reset()
                }
                displayColony(colony)
                return
            }

            colony.each {
                it.reset()
            }
            displayColony(colony)
        }

        println "total flashes = ${colony.collect { it.flashCount }.sum()}"
    }

    static displayColony(def colony) {
        for (int y in 0 .. colony.last().p.y) {
            for (int x in 0 .. colony.last().p.x) {
                print colony.find{ it.p.x == x && it.p.y == y }.energy
            }
            println ''
        }
        println ''
    }

    static def loadColony(def input) {
        def ret = []
        for (int y in 0 .. input.size() - 1) {
            int x = 0
            input[y].each {
                ret << new Octopus(
                    [
                        energy: (it as int),
                        flashCount: 0,
                        p: new Position([x:x, y:y])
                    ]
                )
                x++
            }
        }
        ret
    }
}

class Octopus {

    int energy
    int flashCount
    boolean hasFlashed = false

    Position p

    def flash() {
        hasFlashed = true
        flashCount++
        //println "FLASH for ${this}"
    }

    boolean canFlash() {
        energy > 9
    }

    def powerUp() {
        this.energy++
    }

    def reset() {
        if (canFlash()) {
            energy=0
            hasFlashed = false
        }
    }

    String toString() {
        "${p} energy=${energy} flashes=${flashCount} hasFlashed=${hasFlashed}"
    }
}

@EqualsAndHashCode
class Position {
    int x
    int y

    String toString() {
        "(${x}, ${y})"
    }
}
