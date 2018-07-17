class Prob105{
    class LittleNumber{
        private int[][]map;

        public LittleNumber(int[][]map) {
            this.map = map;
        }

        public String report() {
            String report = "";
            report = "Maximum amount: " + maximumAmount(0, 0, 3, 5);
            return report;
        }

        public int maximumAmount(int filaIni, int colIni, int filaFin, int colFin) {
          return (searchMaximumAmount(filaIni,colIni,filaFin,colFin)).getAmount();
        }

        private Sum searchMaximumAmount(int fil, int col, int filFin, int colFin) {
            Sum sum = new Sum(0), no, ne, se, so, bestSum;
            int previousValue;
            if (valid(fil, col)) {
                previousValue = map[fil][col];
                map[fil][col] = -1;
                sum.add(previousValue);
                if(fil == filFin && col == colFin) {
                    sum.setReachedPosition();
                } else {
                    no = searchMaximumAmount(fil-1, col-1, filFin, colFin);
                    ne = searchMaximumAmount(fil-1, col+1, filFin, colFin);
                    so = searchMaximumAmount(fil+1, col-1, filFin, colFin);
                    se = searchMaximumAmount(fil+1, col+1, filFin, colFin);
                    bestSum = maximumSum(maximumSum(no, ne), maximumSum(so, se));
                    if(bestSum.reachedPosition()) {
                        sum.add(bestSum.getAmount());
                        sum.setReachedPosition();
                    }
                }                
                map[fil][col] = previousValue;
            }
            return sum;
        }

        private boolean valid(int row, int col) {
            boolean valid = row >= 0 && row < map.length && col >= 0 && col < map[0].length; 
            return valid ? map[row][col] > -1  : false;
        }
        
        private Sum maximumSum(Sum a, Sum b) {
            if(a.reachedPosition() && b.reachedPosition()) {
                return a.getAmount() > b.getAmount() ? a : b;
            } else {
                return a.reachedPosition() ? a : b;
            }
        }
          
    }
    
    class Sum{
        int amount;
        boolean reachedPosition;

        public Sum(int amount) {
            this.amount = amount;
            this.reachedPosition = false;
        }
        
        public void setReachedPosition() {
            reachedPosition = true;
        }
        
        public void add(int value) {
            amount += value;
        }
        
        public int getAmount() {
            return amount;
        }
        
        public boolean reachedPosition() {
            return reachedPosition;
        }
    }

    public void run() {
        int[][] map = {{1, -1, 2, 4, 6, 7, 8},
                        {-3, 1, 6, 7, -1, -1,-4},
                        {6, 5, 3, 3, 2, 8, -5},
                        {3, 6, 8, -4, 6, 7, 1}};

        LittleNumber number = new LittleNumber(map);
        System.out.println(number.report());
    }

    public static void main(String[]arg) {
        new Prob105().run();
    }
}