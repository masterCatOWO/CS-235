import java.util.ArrayList;

class Kbots {

    class Kbot {
        private char[][]map;

        public Kbot(char[][]map) {
            this.map = map;
        }

        public String report() {
            String report = "";
            int[]scoutsCoordinates = coordinatesScouts();
            report += "Scouts in ("+scoutsCoordinates[0]+", "+scoutsCoordinates[1]+")\n";
            Path path = mostSecurePath();
            int mines = path.numberOfMinesFound();
            report += "The mines in the path are: "+mines+"\n";
            report +="The most SECURE path is: "+path;
            return report;
        }

        public Path mostSecurePath() {
            int[]scoutsCoordinates = coordinatesScouts();
            return mostSecurePath(scoutsCoordinates[0], scoutsCoordinates[1], '_');
        }

        private Path mostSecurePath(int row, int col, char from) {
            Path mostSecurePath = new Path(), sPath, nPath, ePath, wPath, betterPath;
            char previousState;
            if (valid(row, col)) {
                previousState = map[row][col];
                map[row][col] = 'P';
                mostSecurePath.addStep(new Step(from, previousState));
                if (!mostSecurePath.foundDocument()) {
                    sPath = mostSecurePath(row+1, col, 'S');
                    nPath = mostSecurePath(row-1, col, 'N');
                    ePath = mostSecurePath(row, col+1, 'E');
                    wPath = mostSecurePath(row, col-1, 'W');
                    betterPath = Paths.mostSecurePathWithDocument(Paths.mostSecurePathWithDocument(sPath, nPath), 
                        Paths.mostSecurePathWithDocument(ePath, wPath));
                    mostSecurePath.aggregatePath(betterPath);
                }  
                map[row][col] = previousState;              
            }
            return mostSecurePath;
        }

        private boolean valid(int row, int col) {
            boolean valid = row >= 0 && row < map.length && col >= 0 && col < map[0].length; 
            return valid ? map[row][col] != 'P' : false;
        }

        private int[] coordinatesScouts() {
            int []coords = new int[2];
            findCoordinates(0, 0, coords);
            return coords;
        }

        public void findCoordinates(int row, int col, int[]coordinates) {
            if(row < map.length) {
                if(col < map[0].length) {
                    if(map[row][col] == 'E') {
                        coordinates[0] = row; coordinates[1] = col;
                        return;
                    } else {
                        findCoordinates(row, col+1, coordinates);
                    }
                } else {
                    findCoordinates(row+1, 0, coordinates);
                }
            }
        }
    }

    class Path {
        private ArrayList<Step> steps;
        private boolean foundDocument;
        private int numberOfMinesFound;

        public Path() {
            steps = new ArrayList<>();
            foundDocument = false;
            numberOfMinesFound = 0;
        } 

        public void aggregatePath(Path p) {
            foundDocument = p.foundDocument;
            numberOfMinesFound += p.numberOfMinesFound;
            steps.addAll(p.steps);
        }

        public void addStep(Step s) {
            if (s.containsMine()) {
                numberOfMinesFound++;
            }
            if (s.containsDocument()) {
                foundDocument = true;
            }
            steps.add(s);
        }

        public int numberOfMinesFound() {
            return numberOfMinesFound;
        }

        public boolean foundDocument() {
            return foundDocument;
        }

        @Override
        public String toString() {
            return steps.toString();
        }
    }

    static class Paths {
        public static Path mostSecurePathWithDocument(Path a, Path b) {
            if (a.foundDocument() && b.foundDocument()) {
                return a.numberOfMinesFound() < b.numberOfMinesFound() ? a : b;
            } else {
                return a.foundDocument() ? a : b;
            }
        }
    }

    class Step {
        private char direction, state;

        public Step(char direction, char state) {
            this.direction = direction;
            this.state = state;
        }

        @Override
        public String toString() {
            return "("+direction+", "+state+")";
        }

        public boolean containsMine() {
            return state == 'M';
        }

        public boolean containsDocument() {
            return state == 'D';
        }
    }

    public char[][] toArray2Dim(String[]lines) {
        char[][]array2Dim = new char[lines.length][lines[0].length()];
        for(int index = 0; index < lines.length; index++) {
            array2Dim[index] = lines[index].toCharArray();
        }
        return array2Dim;
    }

    public void run() {
        String[] lines = {"PPPPPMPPMM",
                        "PESSSSMSSP",
                        "SSSPPPPSSS",
                        "SMSSPPPMSM",
                        "SSSSSDSSMS",
                        "PPPSSSSMSS",
                        "SSMSMSMSMS"};

        Kbot kbot = new Kbot(toArray2Dim(lines));
        System.out.println(kbot.report());
    }

    public static void main(String[]arg) {
        new Kbots().run();
    }
}