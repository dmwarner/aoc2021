import groovy.transform.EqualsAndHashCode

class ProblemOne {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day4',
            'input.txt').collect {it.trim()} //as int[]
        //input = input[0..10]
        println "input: size:${input.size()}"
        println input
        println '--------'

        def draws = input[0].split(',').collect { Integer.parseInt(it) }
        println draws

        def row = 0
        BingoCard card
        List<BingoCard> myCards = []
        for (int i in 1 .. input.size()) {
            println input[i]
            def currentInput = input[i]
            if (currentInput) {
                //println currentInput
                card.addRow(row, currentInput)
                row++
            } else {
                if (card) { myCards << card }
                card = new BingoCard()
                row = 0
            }
        }

        println ("Let's Play! (I have ${myCards.size()} bingo cards")


        for (int i in 0 .. draws.size()-1) {
            println "draw ${draws[i]}"

            BingoCard winningCard

            myCards.each {
                it.play(draws[i])
                if (it.checkForBingo()) {
                    winningCard = it
                    return
                }
            }
            if (winningCard) {
                println winningCard
                println winningCard.score(draws[i])
                return
            }
        }
    }

    static private String mostCommon(def bits) {
        if (bits.sum() > (bits.size() / 2)) {
            return '1'
        }
        return '0'
    }

    static private String leastCommon(def bits) {
        if (mostCommon(bits) == '1') {
            return '0'
        }
        return '1'
    }
}

class BingoCard {

    Map<Cell, Integer> board = [:]

    void addRow(int rowNum, def values) {
        values.split(' ').findAll { it }.collect { Integer.parseInt(it) }.eachWithIndex {it, index->
            board.put(
                new Cell([x:rowNum, y:index]),
                it
            )
        }
    }

    void play(int num) {
        //println "play ${num}"
        board.findAll {k,v ->  v == num }.each {
            it.key.picked = true
            println "found ${num} in ${it}"
        }
    }

    boolean checkForBingo() {
        boolean ret = false

        for (int i in 0 .. 4) {

            def matches = board.findAll {k,v ->
                k.picked && k.x == i
            }
            if (matches.size() == 5) {
                println "BINGO FOUND"
                ret = true
                //println matches
            }
            matches = board.findAll {k,v ->
                k.picked && k.y == i
            }
            if (matches.size() == 5) {
                println "BINGO FOUND"
                ret = true
                //println matches
            }
        }
        ret
    }

   int score(int draw) {
        def unpicked = board.findAll {k,v ->
            ! k.picked
        }

        int subTotal = 0
        unpicked.each {k,v ->
            subTotal += v
        }

        subTotal * draw
   }

    String toString() {
        for (int i in 0 .. 4) {
            def row = board.findAll {k, v -> k.x == i}
            println row.collect {k,v -> v }
        }
    }
}

@EqualsAndHashCode
class Cell {
    int x
    int y
    boolean picked = false

    String toString() {
        "(${x}, ${y} : ${picked})"
    }
}
