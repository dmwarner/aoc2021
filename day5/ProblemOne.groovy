import groovy.transform.EqualsAndHashCode


class ProblemOne {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day5',
            'sample.txt').collect {it} //as int[]
        //input = input[0..10]
        println "input: size:${input.size()}"

        VentMap vm = new VentMap()
        input.each {

            def coords = it.split('->')
            Point start = new Point(
                [
                    x: coords[0].split(',')[0] as int,
                    y: coords[0].split(',')[1] as int
                ]
            )

            Point end = new Point(
                [
                    x: coords[1].split(',')[0] as int,
                    y: coords[1].split(',')[1] as int
                ]
            )

            Line line = new Line(
                [
                    start: start,
                    end: end
                ]
            )

            if (line.isHorzOrVert()) {
                //println "will consider ${it}"
                vm.addLine(line)
            }
        }
        println "Answer is: ${vm.dangerIndex(2)}"
    }

}

class VentMap {

    Map<Point, Integer> hotspots = [:]

    void addLine(Line line) {
        line.points().each {
            //println "adding line point ${it}"

            if (hotspots.containsKey(it)) {
                //println "increase danger for ${it}"
                hotspots[it]++
            } else {
                hotspots.put(it, 1)
            }
            //println hotspots
        }
    }

    int dangerIndex(int threshhold) {
        hotspots.findAll { k,v -> v >= threshhold }.size()
    }

}

class Hotspot {

}

class Line {
    Point start
    Point end
    List<Point> points() {
        List<Point> ret = []
        for (int i in start.x .. end.x) {
            for (int j in  start.y .. end.y) {
                ret << new Point([x:i, y:j])
            }
        }
        //println ret
        ret
    }

    boolean isHorzOrVert() {
        if ((start.x == end.x) || (start.y == end.y)) {
            return true
        }
        false
    }
}

@EqualsAndHashCode
class Point {
    int x
    int y

    //@Override
    //public boolean equals(Point p) {
    //    ((x==p.x) && (y==p.y))
    //}
    String toString() {
        "(${x}, ${y})"
    }
}

class Points {
    Map<Point, Integer> grid = [:]

    void add(Point) {

    }
}
