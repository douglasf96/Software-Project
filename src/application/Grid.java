package application;

import java.util.ArrayList;

/**
 * @author Jiexin.Luo
 * @date {2020/5/11}
 */
public class Grid {
    private int posx;
    private int posy;
    private boolean isentry;
    private boolean isexit;
    private boolean isForCar;
    private boolean isForNonCar;
    private ArrayList<Grid> nextGridsForCar;
    private ArrayList<Grid> nextGridsForNonCar;
    private ArrayList<Traffic> traffics;
    private ArrayList<Traffic> intendTraffics;

    Grid(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
        isentry = false;
        isexit = false;
        isForCar = false;
        isForNonCar = false;
        nextGridsForCar = new ArrayList<>();
        nextGridsForNonCar = new ArrayList<>();
        traffics = new ArrayList<>();
        intendTraffics = new ArrayList<>();
    }

    public static ArrayList<Grid> generateGrids() {
        ArrayList<Grid> grids = new ArrayList<Grid>();
        grids = addCrossRoad(grids, 0, 10, 4);
        grids = addCrossRoad(grids, 10, 0, 3);
        grids = addCrossRoad(grids, 0, 10, 9);
        grids = addCrossRoad(grids, 10, 0, 8);

        grids = addStraightRoad(grids, 2);
        grids = addStraightRoad(grids, 7);

        grids = addSideWalk(grids, 1, 5);
        grids = addSideWalk(grids, 6, 5);
        grids = addSideWalk(grids, 1, 10);
        grids = addSideWalk(grids, 6, 10);
        return grids;
    }

    private static ArrayList<Grid> addCrossRoad(ArrayList<Grid> grids, int startpoint_posx, int endpoint_posx, int posy) {
        Grid previous = new Grid(endpoint_posx, posy);
        previous.isexit = true;
        previous.isForCar = true;
        grids.add(previous);
        if (endpoint_posx > startpoint_posx) {
            for (int i = endpoint_posx - 1; i >= startpoint_posx; i--) {
                Grid temp = new Grid(i, posy);
                temp.isForCar = true;
                temp.nextGridsForCar.add(previous);
                grids.add(temp);
                previous = temp;
            }
        } else {
            for (int i = endpoint_posx + 1; i <= startpoint_posx; i++) {
                Grid temp = new Grid(i, posy);
                temp.isForCar = true;
                temp.nextGridsForCar.add(previous);
                grids.add(temp);
                previous = temp;
            }
        }
        previous.isentry = true;
        return grids;
    }

    //Generate the the sideWalk
    private static ArrayList<Grid> addSideWalk(ArrayList<Grid> grids, int left_top_posx, int left_top_posy) {
        Grid startPoint = new Grid(left_top_posx, left_top_posy);
        startPoint.isentry = true;
        startPoint.isexit = true;
        startPoint.isForNonCar = true;
        grids.add(startPoint);
        Grid temp1 = findGrid(grids, left_top_posx + 1, left_top_posy);
        temp1.isForNonCar = true;
        Grid temp2 = findGrid(grids, left_top_posx, left_top_posy - 1);
        temp2.isForNonCar = true;
        startPoint.nextGridsForNonCar.add(temp1);
        temp1.nextGridsForNonCar.add(startPoint);
        startPoint.nextGridsForNonCar.add(temp2);
        temp2.nextGridsForNonCar.add(startPoint);

        startPoint = new Grid(left_top_posx, left_top_posy - 3);
        startPoint.isentry = true;
        startPoint.isexit = true;
        startPoint.isForNonCar = true;
        grids.add(startPoint);
        temp1 = findGrid(grids, startPoint.posx, startPoint.posy + 1);
        temp1.isForNonCar = true;
        temp2 = findGrid(grids, startPoint.posx + 1, startPoint.posy);
        temp2.isForNonCar = true;
        startPoint.nextGridsForNonCar.add(temp1);
        temp1.nextGridsForNonCar.add(startPoint);
        startPoint.nextGridsForNonCar.add(temp2);
        temp2.nextGridsForNonCar.add(startPoint);

        startPoint = new Grid(left_top_posx + 3, left_top_posy);
        startPoint.isentry = true;
        startPoint.isexit = true;
        startPoint.isForNonCar = true;
        grids.add(startPoint);
        temp1 = findGrid(grids, startPoint.posx - 1, startPoint.posy);
        temp1.isForNonCar = true;
        temp2 = findGrid(grids, startPoint.posx, startPoint.posy - 1);
        temp2.isForNonCar = true;
        startPoint.nextGridsForNonCar.add(temp1);
        temp1.nextGridsForNonCar.add(startPoint);
        startPoint.nextGridsForNonCar.add(temp2);
        temp2.nextGridsForNonCar.add(startPoint);

        startPoint = new Grid(left_top_posx + 3, left_top_posy - 3);
        startPoint.isentry = true;
        startPoint.isexit = true;
        startPoint.isForNonCar = true;
        grids.add(startPoint);
        temp1 = findGrid(grids, startPoint.posx - 1, startPoint.posy);
        temp1.isForNonCar = true;
        temp2 = findGrid(grids, startPoint.posx, startPoint.posy + 1);
        temp2.isForNonCar = true;
        startPoint.nextGridsForNonCar.add(temp1);
        temp1.nextGridsForNonCar.add(startPoint);
        startPoint.nextGridsForNonCar.add(temp2);
        temp2.nextGridsForNonCar.add(startPoint);

        temp1 = findGrid(grids, left_top_posx + 1, left_top_posy);
        temp2 = findGrid(grids, temp1.posx + 1, temp1.posy);
        temp1.nextGridsForNonCar.add(temp2);
        temp2.nextGridsForNonCar.add(temp1);

        temp1 = findGrid(grids, left_top_posx, left_top_posy - 1);
        temp2 = findGrid(grids, temp1.posx, temp1.posy - 1);
        temp1.nextGridsForNonCar.add(temp2);
        temp2.nextGridsForNonCar.add(temp1);

        temp1 = findGrid(grids, left_top_posx + 1, left_top_posy - 3);
        temp2 = findGrid(grids, temp1.posx + 1, temp1.posy);
        temp1.nextGridsForNonCar.add(temp2);
        temp2.nextGridsForNonCar.add(temp1);

        temp1 = findGrid(grids, left_top_posx + 3, left_top_posy - 2);
        temp2 = findGrid(grids, temp1.posx, temp1.posy + 1);
        temp1.nextGridsForNonCar.add(temp2);
        temp2.nextGridsForNonCar.add(temp1);

        return grids;
    }

    //add two lines of straight road with opposite direction into the map
    private static ArrayList<Grid> addStraightRoad(ArrayList<Grid> grids, int line) {
        Grid previous = new Grid(line, 12);
        previous.isexit = true;
        previous.isForCar = true;
        grids.add(previous);
        for (int i = 11; i >= 0; i--) {
            Grid temp = findGrid(grids, line, i);
            if (temp == null) {
                temp = new Grid(line, i);
                temp.isForCar = true;
                grids.add(temp);
            }
            temp.nextGridsForCar.add(previous);
            previous = temp;
        }
        previous.isentry = true;

        previous = new Grid(line + 1, 0);
        previous.isexit = true;
        previous.isForCar = true;
        grids.add(previous);
        for (int i = 1; i <= 12; i++) {
            Grid temp = findGrid(grids, line + 1, i);
            if (temp == null) {
                temp = new Grid(line + 1, i);
                temp.isForCar = true;
                grids.add(temp);
            }
            temp.nextGridsForCar.add(previous);
            previous = temp;
        }
        previous.isentry = true;
        return grids;
    }

    public static Grid findGrid(ArrayList<Grid> grids, int posx, int posy) {
        for (Grid g : grids) {
            if (g.posx == posx && g.posy == posy) {
                return g;
            }
        }
        return null;
    }

    public static ArrayList<Grid> findEmptyEntry(ArrayList<Grid> grids, boolean isForCar) {
        ArrayList<Grid> emptyGrids = new ArrayList<>();
        for (Grid g : grids) {
            if (isForCar && g.traffics.isEmpty() && g.isentry && g.isForCar) {
                emptyGrids.add(g);
                continue;
            }
            if ((!isForCar) && g.isForNonCar && g.isentry && g.traffics.isEmpty()) {
                emptyGrids.add(g);
            }
        }
        return emptyGrids;
    }

    public static ArrayList<Grid> findExitsForCar(ArrayList<Grid> grids){
        ArrayList<Grid> exits = new ArrayList<>();
        for (Grid g:grids){
            if(g.isForCar&&g.isExit()){
                exits.add(g);
            }
        }
        return exits;
    }

    public static ArrayList<Grid> checkSelfLoop(ArrayList<Grid> grids) {
        ArrayList<Grid> deadlocks = new ArrayList<>();
        for (Grid g : grids) {
            if (g.getNextGridsForCar().contains(g) || g.getNextGridsForNonCar().contains(g)) {
                deadlocks.add(g);
            }
        }
        return deadlocks;
    }

    public static ArrayList<Grid> checkDeadEnd(ArrayList<Grid> grids) {
        ArrayList<Grid> deadends = new ArrayList<>();
        for (Grid g : grids) {
            if (((g.getNextGridsForCar().isEmpty() && g.isForCar) || (g.getNextGridsForNonCar().isEmpty() && g.isForNonCar)) && (!g.isExit())) {
                deadends.add(g);
            }
        }
        return deadends;
    }

    public void enterGrid(Traffic t) {
        traffics.add(t);
    }

    public void exitGrid(Traffic t) {
        traffics.remove(t);
    }

    public int[] getAxis() {
        int[] axis = new int[2];
        axis[0] = posx;
        axis[1] = posy;
        return axis;
    }

    public boolean isExit() {
        return isexit;
    }

    public boolean equals(Grid g) {
        return g.posx == posx && g.posy == posy;
    }

    public void intendToGo(Traffic t) {
        this.intendTraffics.add(t);
    }

    public void intendToLeave(Traffic t) {
        this.intendTraffics.remove(t);
    }

    public ArrayList<Grid> getNextGridsForNonCar() {
        return nextGridsForNonCar;
    }

    public ArrayList<Traffic> getIntendTraffics() {
        return intendTraffics;
    }

    public ArrayList<Traffic> getTraffics() {
        return traffics;
    }

    public ArrayList<Grid> getNextGridsForCar() {
        return nextGridsForCar;
    }

    public boolean equals(Object o) {
        if (o instanceof Grid) {
            return this.equals((Grid) o);
        }
        return false;
    }
}
