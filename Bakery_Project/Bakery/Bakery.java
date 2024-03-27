import java.util.*;

public class Bakery {
    static Stack<Integer>findUpperbound(List<Stack<Integer>>mincaList,int upperBound){
        for(Stack<Integer>temp:mincaList){
            if(temp.peek()==upperBound){
                return temp;
            }
        }
        return null;
    }
    static int solve(ArrayList<Integer> cakes){
        // TO be completed by students
        int answer = 0;
        SkipList list=new SkipList();
        for(int i:cakes){
            list.insert(i);
        }
        List<Stack<Integer>>mincaList=new ArrayList<>();
        for(int i=0;i<cakes.size();i++){
            int teir=cakes.get(i);
            int upperBound=list.upperBound(teir);
            if(mincaList.isEmpty()||findUpperbound(mincaList,upperBound)==null){
                Stack<Integer>temp=new Stack<>();
                temp.add(teir);
                mincaList.add(temp);
            }
            else{
                List<Integer>temp=findUpperbound(mincaList, upperBound);
                temp.add(teir);
            }
        }
        answer = mincaList.size();
        return answer;
    }
    public static void main(String[] args) {
        ArrayList<Integer>cake=new ArrayList<>();
        cake.add(2);
        cake.add(2);
        cake.add(2);
        cake.add(2);
        cake.add(1);
        System.out.println(solve(cake));
    }
}
