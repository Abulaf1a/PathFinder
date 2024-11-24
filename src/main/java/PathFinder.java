public class PathFinder {




        private static class Pos{
            int x, y;
            Pos(int x, int y) {this.x = x; this.y = y;}
            int getX() {return x;}
            int getY() {return y;}
        }

        static boolean pathFinder(String maze) {
            if(maze.length()<2) return true; //deals with single '.';
            String[] mazeArr = maze.split("\\\n");//split into an array, separating each line.
            Pos p = new Pos(0,0);
            Boolean exit = false;
            exit = traverseMaze(mazeArr, p, mazeArr.length,'S');
            if(!exit){
                p.x = 0; p.y = 0;
                exit = traverseMaze(mazeArr, p, mazeArr.length,'N'); //go once again but this time East first - if caught out by a loop sending back to start
            }
            return exit;
        }
        static boolean traverseMaze(String[] mazeArr, Pos pos, int length, char orient){
            switch(orient){
                case 'S': orient = south(mazeArr, pos, length); break;
                case 'E': orient = east(mazeArr, pos, length); break;
                case 'N': orient = north(mazeArr, pos, length); break;
                case 'W': orient = west(mazeArr, pos, length); break;
            }
            switch(orient){
                case 'S': pos.y+=1; break;
                case 'E': pos.x+=1; break;
                case 'N': pos.y-=1; break;
                case 'W': pos.x-=1; break;
                case 'F': return false; //orientation set to F if direction functions can't find any direction to go in e.g. in mazes where only the first place is a '.'
            }
            if(pos.getX() <= 0 && pos.getY() <= 0) return false; //returning to start 0,0 returns false to prevent infinite loop
            else if(pos.getX() == (mazeArr.length-1) && pos.getY() == (mazeArr.length -1)) return true; //found end
            return traverseMaze(mazeArr, pos, length, orient); //if not at start or end, continue recursion

        }

        static char south(String[]mazeArr, Pos pos, int length){
            if(tryWest(mazeArr,  pos,  length)) return 'W';
            else if(trySouth(mazeArr,  pos,  length)) return 'S';
            else if(tryEast(mazeArr, pos,  length)) return 'E';
            else if(tryNorth(mazeArr, pos, length)) return 'N';
            else return 'F'; //F for fail to move anywhere

        }
        static char east(String[]mazeArr, Pos pos, int length){

            if(trySouth(mazeArr, pos, length)) return 'S';
            else if(tryEast(mazeArr, pos, length)) return 'E';
            else if(tryNorth(mazeArr,  pos, length)) return 'N';
            else if(tryWest(mazeArr, pos, length)) return 'W';
            else return 'F';

        }
        static char north(String[]mazeArr, Pos pos, int length){

            if(tryEast(mazeArr, pos, length)) return 'E';
            else if(tryNorth(mazeArr, pos,  length)) return 'N';
            else if(tryWest(mazeArr, pos,  length)) return 'W';
            else if(trySouth(mazeArr, pos, length)) return 'S';
            else return 'F';

        }
        static char west(String[]mazeArr, Pos pos, int length){

            if(tryNorth(mazeArr, pos, length)) return 'N';
            else if(tryWest(mazeArr, pos, length)) return 'W';
            else if(trySouth(mazeArr, pos, length)) return 'S';
            else if(tryEast(mazeArr, pos, length)) return 'E';
            else return 'F';

        }

        static boolean trySouth(String[] mazeArr, Pos pos, int length){
            if(((pos.getY() + 1) < length) && (mazeArr[pos.getY()+1].charAt(pos.getX()) != 'W')){
                return true;
            }
            return false;
        }
        static  boolean tryEast(String[] mazeArr, Pos pos, int length){
            if(((pos.getX()+1) < length) && (mazeArr[pos.getY()].charAt((pos.getX()+1)) != 'W')){
                return true;
            }
            return false;
        }
        static boolean tryNorth(String[] mazeArr, Pos pos, int length){
            if(((pos.getY()) > 0) && (mazeArr[pos.getY()-1].charAt(pos.getX()) != 'W')){
                return true;
            }
            return false;
        }
        static boolean tryWest(String[] mazeArr, Pos pos, int length){
            if(((pos.getX()-1) > -1)&& (mazeArr[pos.getY()].charAt((pos.getX() -1)) != 'W')){
                return true;
            }
            return false;
        }

    }

